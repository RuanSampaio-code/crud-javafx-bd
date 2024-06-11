package org.example.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // Método para criar uma conexão com o banco de dados
    public Connection criaConexao() {
        String usuario = "root"; // Nome de usuário do banco de dados
        String senha = "1w3q2e";       // Senha do banco de dados

        try {
            // Tenta estabelecer uma conexão com o banco de dados MySQL
            // usando o DriverManager e retornando a conexão.

            return DriverManager.getConnection("jdbc:mysql://localhost:3306/javafxbd", usuario, senha);

        } catch (SQLException e) {
            // Se ocorrer uma exceção SQL, lança uma RuntimeException com uma mensagem de erro.
            throw new RuntimeException("Erro ao tentar se conectar no banco de dados.", e);
        }


    }
}