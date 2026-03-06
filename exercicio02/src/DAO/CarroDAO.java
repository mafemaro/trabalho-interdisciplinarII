package DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exercicio02.Carro;

public class CarroDAO extends DAO {
 public CarroDAO() {
	 super();
 }
 
 public boolean inserir(Carro carro) {

	    String sql = "INSERT INTO carro (placa, cor, ano, modelo, marca) VALUES (?, ?, ?, ?, ?)";

	    try {
	    	conectar();
	        PreparedStatement ps = conexao.prepareStatement(sql);

	        ps.setString(1, carro.getPlaca());
	        ps.setString(2, carro.getCor());
	        ps.setInt(3, carro.getAno());
	        ps.setString(4, carro.getModelo());
	        ps.setString(5, carro.getMarca());

	        ps.executeUpdate();

	        ps.close();
	        conexao.close();

	        return true;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
 
 public List<Carro> listar() {

     List<Carro> carros = new ArrayList<>();

     try {

         conectar();

         String sql = "SELECT * FROM carro";

         Statement st = conexao.createStatement();
         ResultSet rs = st.executeQuery(sql);

         while (rs.next()) {

             Carro carro = new Carro();

             carro.setPlaca(rs.getString("placa"));
             carro.setCor(rs.getString("cor"));
             carro.setAno(rs.getInt("ano"));
             carro.setModelo(rs.getString("modelo"));
             carro.setMarca(rs.getString("marca"));

             carros.add(carro);
         }

         rs.close();
         st.close();
         close();

     } catch (Exception e) {
         System.out.println("Erro ao listar carros: " + e.getMessage());
     }

     return carros;
 }

 public boolean atualizar(Carro carro) {

     boolean status = false;

     try {

         conectar();

         String sql = "UPDATE carro SET cor=?, ano=?, modelo=?, marca=? WHERE placa=?";

         PreparedStatement ps = conexao.prepareStatement(sql);

         ps.setString(1, carro.getCor());
         ps.setInt(2, carro.getAno());
         ps.setString(3, carro.getModelo());
         ps.setString(4, carro.getMarca());
         ps.setString(5, carro.getPlaca());

         ps.executeUpdate();

         ps.close();
         close();

         status = true;

     } catch (Exception e) {
         System.out.println("Erro ao atualizar carro: " + e.getMessage());
     }

     return status;
 }

 public boolean excluir(String placa) {

     boolean status = false;

     try {

         conectar();

         String sql = "DELETE FROM carro WHERE placa = ?";

         PreparedStatement ps = conexao.prepareStatement(sql);

         ps.setString(1, placa);

         ps.executeUpdate();

         ps.close();
         close();

         status = true;

     } catch (Exception e) {
         System.out.println("Erro ao excluir carro: " + e.getMessage());
     }

     return status;
 }
}
