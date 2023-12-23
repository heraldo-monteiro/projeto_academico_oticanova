
package dao;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import model.Cliente;



public class ClienteDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql = ""; 
    
    public ArrayList<Cliente> getListaCliente() throws SQLException{
        ArrayList<Cliente> clientes = new ArrayList<>();
        sql = "SELECT idCliente, nome, cpf, telefone, dataNasc, idade, status FROM cliente";
        
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        while(rs.next()){
            Cliente cliente = new Cliente();
            cliente.setIdCliente(rs.getInt("idCliente"));
            cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setDataNasc(rs.getDate("dataNasc"));   
            cliente.setIdade(rs.getInt("idade"));
            cliente.setStatus(rs.getInt("status"));
            clientes.add(cliente);
        }
        
        ConexaoFactory.close(con);
        return clientes;        
    }
    
   public boolean registrarCliente(Cliente cliente) throws SQLException{
       con = ConexaoFactory.conectar();
       if(cliente.getIdCliente() == 0){
            sql = "INSERT INTO cliente(nome, cpf, telefone, dataNasc, idade, status) VALUES( ?, ?, ?, ?, ?, ?)";                 
       
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getTelefone());
            ps.setDate(4, new Date(cliente.getDataNasc().getTime()));    
            ps.setInt(5, cliente.getIdade());       
            ps.setInt(6, cliente.getStatus());            
       }else{
            sql = "UPDATE cliente SET nome = ?, cpf = ?, telefone = ?, dataNasc = ?, idade = ?, status = ? WHERE idCliente = ?" ;

            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getTelefone());
            ps.setDate(4, new Date(cliente.getDataNasc().getTime()));   
            ps.setInt(5, cliente.getIdade());
            ps.setInt(6, cliente.getStatus());   
            ps.setInt(7, cliente.getIdCliente());
           
       }      
       ps.executeUpdate();
       ConexaoFactory.close(con);
       return true;
   }     
   
   public Cliente getCarregarCliente(int idCliente) throws SQLException {
       Cliente cliente = new Cliente();
       sql = "SELECT idCliente, nome, cpf, telefone, dataNasc, status FROM cliente WHERE idCliente = ?";
       
       con = ConexaoFactory.conectar();
       ps = con.prepareStatement(sql);
       ps.setInt(1, idCliente);
       rs = ps.executeQuery();
       
       if(rs.next()){
           cliente.setIdCliente(rs.getInt("idCliente"));
           cliente.setNome(rs.getString("nome"));
           cliente.setCpf(rs.getString("cpf"));
           cliente.setTelefone(rs.getString("telefone"));
           cliente.setDataNasc(rs.getDate("dataNasc"));           
           cliente.setStatus(rs.getInt("status"));           
           
       }
       
       ConexaoFactory.close(con);
       return cliente;
   }
  
   public boolean ativarCliente(Cliente cliente) throws SQLException{
       sql = "UPDATE cliente SET status = 1 WHERE idCliente = ?";
       
       con = ConexaoFactory.conectar();
       ps = con.prepareStatement(sql);
       ps.setInt(1, cliente.getIdCliente());
       ps.executeUpdate();
       
       ConexaoFactory.close(con);
       return true;
   }
   
   public boolean desativarCliente(Cliente cliente)throws SQLException{
       sql = "UPDATE cliente SET status = 0 WHERE idCliente = ?";
       
       con = ConexaoFactory.conectar();
       ps = con.prepareStatement(sql);
       ps.setInt(1, cliente.getIdCliente());
       ps.executeUpdate();
       
       ConexaoFactory.close(con);
       return true;       
   }
 
            
    
}
       
   
   

