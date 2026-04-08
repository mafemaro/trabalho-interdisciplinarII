package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Produto;

public class ProdutoDAO extends DAO {

    public ProdutoDAO() {
        super();
        conectar();
    }

    public boolean insert(Produto produto) {
        boolean status = false;

        try {
            String sql = "INSERT INTO produto (nome, preco, descricao) VALUES (?,?,?)";

            PreparedStatement st = conexao.prepareStatement(sql);

            st.setString(1, produto.getNome());
            st.setFloat(2, produto.getPreco());
            st.setString(3, produto.getDescricao());

            st.executeUpdate();
            st.close();

            status = true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return status;
    }

    public ArrayList<Produto> get() {

        ArrayList<Produto> lista = new ArrayList<>();

        try {
            Statement st = conexao.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM produto");

            while (rs.next()) {
                Produto p = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getFloat("preco"),
                        rs.getString("descricao")
                );

                lista.add(p);
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return lista; // ✅ ESSA LINHA ERA O ERRO
    }
}