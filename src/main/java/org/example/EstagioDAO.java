package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstagioDAO implements AutoCloseable {

    private final Connection conexao;

    public EstagioDAO() {
        this.conexao = BancoDeDados.conectar();
    }

    public void inserirEstagio(Estagio estagio, int alunoMatricula) {
        String sql = "INSERT INTO Estagios (aluno_matricula, local, supervisor, horarios, instituicao, endereco, periodo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, alunoMatricula);
            pstmt.setString(2, estagio.getLocal());
            pstmt.setString(3, estagio.getSupervisor());
            pstmt.setString(4, estagio.getHorarios());
            pstmt.setString(5, estagio.getInstituicao());
            pstmt.setString(6, estagio.getEndereco());
            pstmt.setString(7, estagio.getPeriodo());

            pstmt.executeUpdate();
            System.out.println("Estágio inserido com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir estágio: " + e.getMessage(), e);
        }
    }

    public Estagio obterEstagioPorId(int id) {
        String sql = "SELECT * FROM Estagios WHERE id = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Estagio estagio = new Estagio();
                    estagio.setId(rs.getInt("id"));
                    estagio.setLocal(rs.getString("local"));
                    estagio.setSupervisor(rs.getString("supervisor"));
                    estagio.setHorarios(rs.getString("horarios"));
                    estagio.setInstituicao(rs.getString("instituicao"));
                    estagio.setEndereco(rs.getString("endereco"));
                    estagio.setPeriodo(rs.getString("periodo"));
                    return estagio;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar estágio por ID: " + e.getMessage(), e);
        }
        return null;
    }

    public void atualizarEstagio(Estagio estagio) {
        String sql = "UPDATE Estagios SET local = ?, supervisor = ?, horarios = ?, instituicao = ?, endereco = ?, periodo = ? WHERE id = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, estagio.getLocal());
            pstmt.setString(2, estagio.getSupervisor());
            pstmt.setString(3, estagio.getHorarios());
            pstmt.setString(4, estagio.getInstituicao());
            pstmt.setString(5, estagio.getEndereco());
            pstmt.setString(6, estagio.getPeriodo());
            pstmt.setInt(7, estagio.getId());

            pstmt.executeUpdate();
            System.out.println("Estágio atualizado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar estágio: " + e.getMessage(), e);
        }
    }

    public void apagarEstagio(int id) {
        String sql = "DELETE FROM Estagios WHERE id = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Estágio apagado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar estágio: " + e.getMessage(), e);
        }
    }

    public List<Estagio> obterTodosEstagios() {
        List<Estagio> estagios = new ArrayList<>();
        String sql = "SELECT * FROM Estagios";
        try (Connection conn = BancoDeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Estagio estagio = new Estagio(
                        rs.getInt("id"),
                        rs.getString("local"),
                        rs.getString("supervisor"),
                        rs.getString("horarios"),
                        rs.getString("instituicao"),
                        rs.getString("endereco"),
                        rs.getString("periodo"),
                        rs.getInt("aluno_matricula")
                );
                estagios.add(estagio);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter estágios: " + e.getMessage(), e);
        }
        return estagios;
    }

    @Override
    public void close() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}