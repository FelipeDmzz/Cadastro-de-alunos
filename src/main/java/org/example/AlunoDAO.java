package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO implements AutoCloseable {

    private final Connection conexao;

    public AlunoDAO() {
        this.conexao = BancoDeDados.conectar();
    }

    public void inserirAluno(Aluno aluno) {
        String sql = "INSERT INTO Alunos (nome, matricula, email, curso, telefone, data_nascimento, turno, instituicao, observacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, aluno.getNome());
            pstmt.setInt(2, aluno.getMatricula());
            pstmt.setString(3, aluno.getEmail());
            pstmt.setString(4, aluno.getCurso());
            pstmt.setString(5, aluno.getTelefone());
            pstmt.setString(6, aluno.getDataNascimento());
            pstmt.setString(7, aluno.getTurno());
            pstmt.setString(8, aluno.getInstituicao());
            pstmt.setString(9, aluno.getObservacao());

            pstmt.executeUpdate();
            System.out.println("Aluno inserido com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir aluno: " + e.getMessage(), e);
        }
    }

    public List<Aluno> listarTodos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM Alunos";

        try (PreparedStatement pstmt = conexao.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Aluno aluno = new Aluno(
                        rs.getString("nome"),
                        rs.getInt("matricula"),
                        rs.getString("email"),
                        rs.getString("curso"),
                        rs.getString("telefone"),
                        rs.getString("data_nascimento"),
                        rs.getString("turno"),
                        rs.getString("instituicao"),
                        rs.getString("observacao")
                );
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar alunos: " + e.getMessage(), e);
        }

        return alunos;
    }

    public Aluno obterAlunoPorMatricula(int matricula) {
        String sql = "SELECT * FROM Alunos WHERE matricula = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, matricula);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Aluno(
                            rs.getString("nome"),
                            rs.getInt("matricula"),
                            rs.getString("email"),
                            rs.getString("curso"),
                            rs.getString("telefone"),
                            rs.getString("data_nascimento"),
                            rs.getString("turno"),
                            rs.getString("instituicao"),
                            rs.getString("observacao")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter aluno: " + e.getMessage(), e);
        }
        return null;
    }

    public void atualizarAluno(Aluno aluno) {
        String sql = "UPDATE Alunos SET nome = ?, email = ?, curso = ?, telefone = ?, data_nascimento = ?, turno = ?, instituicao = ?, observacao = ? WHERE matricula = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, aluno.getNome());
            pstmt.setString(2, aluno.getEmail());
            pstmt.setString(3, aluno.getCurso());
            pstmt.setString(4, aluno.getTelefone());
            pstmt.setString(5, aluno.getDataNascimento());
            pstmt.setString(6, aluno.getTurno());
            pstmt.setString(7, aluno.getInstituicao());
            pstmt.setString(8, aluno.getObservacao());
            pstmt.setInt(9, aluno.getMatricula());

            pstmt.executeUpdate();
            System.out.println("Aluno atualizado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar aluno: " + e.getMessage(), e);
        }
    }

    public void apagarAluno(int matricula) {
        String sql = "DELETE FROM Alunos WHERE matricula = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, matricula);
            pstmt.executeUpdate();
            System.out.println("Aluno apagado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar aluno: " + e.getMessage(), e);
        }
    }

    @Override
    public void close() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão com o banco de dados: " + e.getMessage());
        }
    }
}