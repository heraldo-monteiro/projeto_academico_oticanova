
package dao;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import model.Lente;

public class LenteDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql = "";
    
    public ArrayList<Lente> getListarLente() throws SQLException{
        ArrayList<Lente> lentes = new ArrayList<>();
        
        sql = "SELECT idLente, nome, modelo, fabricante, preco, status FROM lente";
        
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        while(rs.next()){
            Lente lente = new Lente();
            lente.setIdLente(rs.getInt("idLente"));
            lente.setNome(rs.getString("nome"));
            lente.setModelo(rs.getString("modelo"));
            lente.setFabricante(rs.getString("fabricante"));
            lente.setPreco(rs.getDouble("preco"));
            lente.setStatus(rs.getInt("status")); 
            lentes.add(lente);
            
        }   
        
        ConexaoFactory.close(con);
        return lentes;
    }

    public boolean registrarLente(Lente lente) throws SQLException{
        con = ConexaoFactory.conectar();
        
        if(lente.getIdLente() == 0){
            sql = "INSERT INTO lente(nome, modelo, fabricante, preco, status) VALUES(?, ?, ?, ?, ?)";
            
            ps = con.prepareStatement(sql);
            ps.setString(1, lente.getNome());
            ps.setString(2, lente.getModelo());
            ps.setString(3, lente.getFabricante());
            ps.setDouble(4, lente.getPreco());
            ps.setInt(5, lente.getStatus());            
            
        }else{
            sql = "UPDATE lente SET nome = ?, modelo = ?, fabricante = ?, preco = ?, status = ? WHERE idLente = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, lente.getNome());
            ps.setString(2, lente.getModelo());
            ps.setString(3, lente.getFabricante());
            ps.setDouble(4, lente.getPreco());
            ps.setInt(5, lente.getStatus());   
            ps.setInt(6, lente.getIdLente());
        }
        
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;
    }
    
    public Lente getCarregarLente(int idLente) throws SQLException{
        Lente lente = new Lente();
        sql = "SELECT idLente, nome, modelo, fabricante, preco, status FROM lente WHERE idLente = ?";
        
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idLente);
        rs = ps.executeQuery();
        
        if(rs.next()){
            lente.setIdLente(rs.getInt("idLente"));
            lente.setNome(rs.getString("nome"));
            lente.setModelo(rs.getString("modelo"));
            lente.setFabricante(rs.getString("fabricante"));
            lente.setPreco(rs.getDouble("preco"));
            lente.setStatus(rs.getInt("status"));
        }
        
        ConexaoFactory.close(con);
        return lente;
    }
    
    
    public boolean ativarLente(Lente lente) throws SQLException{
        sql = "UPDATE lente SET status = 1 WHERE idLente = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, lente.getIdLente());
        ps.executeUpdate();
        
        ConexaoFactory.close(con);
        return true;             
    }
    
    public boolean desativarLente(Lente lente) throws SQLException{
        sql = "UPDATE lente SET status = 0 WHERE idLente = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, lente.getIdLente());
        ps.executeUpdate();
        
        ConexaoFactory.close(con);
        return true;  
        
    }
    
}
