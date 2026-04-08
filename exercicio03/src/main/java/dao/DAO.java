package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Classe responsável pela conexão com o banco PostgreSQL
public class DAO {

    protected Connection conexao;

    public DAO() {
        conexao = null;
    }

    public boolean conectar() {

        String driverName = "org.postgresql.Driver";
        String serverName = "localhost";
        String database = "teste";
        int porta = 5432;

        String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + database;

        String username = "postgres";
        String password = "1234";

        boolean status = false;

        try {
            Class.forName(driverName);

            conexao = DriverManager.getConnection(url, username, password);

            if (conexao != null) {
                status = true;
                System.out.println("Conexão com PostgreSQL realizada com sucesso!");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Driver do PostgreSQL não encontrado.");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco.");
            e.printStackTrace();
        }

        return status;
    }

    public boolean close() {

        boolean status = false;

        try {
            if (conexao != null) {
                conexao.close();
                status = true;
                System.out.println("Conexão encerrada.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexão.");
            e.printStackTrace();
        }

        return status;
    }
}