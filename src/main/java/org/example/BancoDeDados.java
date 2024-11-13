package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDeDados {

    private static final String URL = "jdbc:sqlite:banco.db";


    public static Connection conectar() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return conn;
    }
    public static void criarTabelas() {
        criarTabelaAluno();
        criarTabelaEstagio();
    }

    public static void criarTabelaAluno() {
        String sql = "CREATE TABLE IF NOT EXISTS Alunos (" +
                "nome TEXT NOT NULL, " +
                "matricula INTEGER PRIMARY KEY, " +
                "email TEXT NOT NULL, " +
                "curso TEXT, " +
                "telefone TEXT, " +
                "data_nascimento TEXT, " +
                "turno TEXT, " +
                "instituicao TEXT, " +
                "observacao TEXT" +
                ");";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela Alunos criada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela Alunos: " + e.getMessage());
        }
    }


    public static void criarTabelaEstagio() {
        String sql = "CREATE TABLE IF NOT EXISTS Estagios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "aluno_matricula INTEGER, " +
                "local TEXT NOT NULL, " +
                "supervisor TEXT, " +
                "horarios TEXT, " +
                "instituicao TEXT, " +
                "endereco TEXT, " +
                "periodo TEXT, " +
                "FOREIGN KEY (aluno_matricula) REFERENCES Alunos(matricula)" +
                ");";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela Estagios criada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela Estagios: " + e.getMessage());
        }
    }




}
