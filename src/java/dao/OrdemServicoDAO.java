
package dao;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import factory.ConexaoFactory;
import model.Usuario;
import model.Cliente;
import model.Lente;
import model.Laboratorio;
import model.OrdemServico;
import date.Date_Format;




public class OrdemServicoDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    
    
    
    public ArrayList<OrdemServico> getListarOrdemServico() throws SQLException{
        ArrayList<OrdemServico> ordemServicos = new ArrayList<>();
        sql = "SELECT os.idOrdemServico, os.dataSolicitacao, os.dataVencimento, os.dataEntrega, os.status, u.nome, c.nome, l.nome, lb.nome "+
              "FROM ordemservico AS os "+
              "INNER JOIN usuario AS u ON os.idUsuario = u.idUsuario "+
              "INNER JOIN cliente AS c ON os.idCliente = c.idCliente "+
              "INNER JOIN lente AS l ON os.idLente = l.idLente "+
              "INNER JOIN laboratorio AS lb ON os.idLaboratorio = lb.idLaboratorio "+
              "ORDER BY os.dataSolicitacao DESC";
                
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while(rs.next()){
            OrdemServico os = new OrdemServico();
            os.setIdOrdemServico(rs.getInt("os.idOrdemServico"));
            os.setDataSolicitacao(rs.getDate("os.dataSolicitacao"));
            os.setDataVencimento(rs.getDate("os.dataVencimento"));
            os.setDataEntrega(rs.getDate("os.dataEntrega"));
            os.setStatus(rs.getInt("os.status"));
            
            Usuario u = new Usuario();            
            u.setNome(rs.getString("u.nome"));
            os.setUsuario(u);    
            
            Cliente c = new Cliente();
            c.setNome(rs.getString("c.nome"));
            os.setCliente(c);           
            
            Lente l = new Lente();
            l.setNome(rs.getString("l.nome"));
            os.setLente(l);
                       
            Laboratorio lb = new Laboratorio();
            lb.setNome(rs.getString("lb.nome"));
            os.setLaboratorio(lb);
            
            ordemServicos.add(os);        
            
        }
         ConexaoFactory.close(con);
        return ordemServicos;
    }
    
    public OrdemServico getCarregarOrdemServico(int idOrdemServico)throws SQLException{
        OrdemServico os = new OrdemServico();
        Usuario usuario = new Usuario();
        Cliente cliente = new Cliente();
        Lente lente = new Lente();
        Laboratorio lab = new Laboratorio();
        
        sql = "SELECT os.idOrdemServico, os.dataSolicitacao, os.dataVencimento, os.dataEntrega, os.status, u.nome, c.nome, l.nome, lb.nome "+
              "FROM ordemservico AS os "+
              "INNER JOIN usuario AS u ON os.idUsuario = u.idUsuario "+
              "INNER JOIN cliente AS c ON os.idCliente = c.idCliente "+
              "INNER JOIN lente AS l ON os.idLente = l.idLente "+
              "INNER JOIN laboratorio AS lb ON os.idLaboratorio = lb.idLaboratorio "+
              "WHERE idOrdemServico = ? ";
              
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idOrdemServico);
            rs = ps.executeQuery();
            
            if(rs.next()){
                os.setIdOrdemServico(rs.getInt("os.idOrdemServico"));
                os.setDataSolicitacao(rs.getDate("os.dataSolicitacao"));
                os.setDataVencimento(rs.getDate("os.dataVencimento"));
                os.setDataEntrega(rs.getDate("os.dataEntrega"));
                os.setStatus(rs.getInt("os.status"));
                
                usuario.setNome(rs.getString("u.nome"));
                os.setUsuario(usuario);
                
                cliente.setNome(rs.getString("c.nome"));
                os.setCliente(cliente);
                
                lente.setNome(rs.getString("l.nome"));
                os.setLente(lente);
                
                lab.setNome(rs.getString("lb.nome"));
                os.setLaboratorio(lab); 
                
            }
            ConexaoFactory.close(con);
            return os;
        
    }
    
    public boolean registrarOrdemServico(OrdemServico os)throws SQLException{
        con = ConexaoFactory.conectar();  
        if(os.getIdOrdemServico() == 0){
           sql = "INSERT INTO ordemservico(dataSolicitacao, dataVencimento,dataEntrega, status, idUsuario, idCliente, idlente, idLaboratorio) "+
                  "VALUES(?, ?,? ,? ,? ,? ,? ,?)";
            
            ps  = con.prepareStatement(sql);
            ps.setDate(1, new Date(os.getDataSolicitacao().getTime()));          
            ps.setDate(2, new Date(os.getDataVencimento().getTime()));                      
            if(os.getDataEntrega() == null){
                ps.setString(3, null);
            }else{
                ps.setDate(3, new Date(os.getDataEntrega().getTime()));
            }
            ps.setInt(4, os.getStatus());                        
            ps.setInt(5, os.getUsuario().getIdUsuario());
            ps.setInt(6, os.getCliente().getIdCliente());
            ps.setInt(7, os.getLente().getIdLente());
            ps.setInt(8, os.getLaboratorio().getIdLaboratorio());             
             
        }else{
            sql  = "UPDATE ordemservico SET dataSolicitacao = ?, dataVencimento = ?,dataEntrega = ?, status = ?, idUsuario = ?, idCliente = ?, idlente = ?, idLaboratorio = ? WHERE idOrdemServico = ?";
            
            ps  = con.prepareStatement(sql);
            ps.setDate(1, new Date(os.getDataSolicitacao().getTime()));  
            ps.setDate(2, new Date(os.getDataVencimento().getTime()));     
            
            if(os.getDataEntrega() == null){
                ps.setString(3, "0000-00-00");
            }else{
                ps.setDate(3, new Date(os.getDataEntrega().getTime()));
            }
            ps.setInt(4, os.getStatus());                        
            ps.setInt(5, os.getUsuario().getIdUsuario());
            ps.setInt(6, os.getCliente().getIdCliente());
            ps.setInt(7, os.getLente().getIdLente());
            ps.setInt(8, os.getLaboratorio().getIdLaboratorio());  
        }  
        
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;
    }
    
   public boolean ativarOrdemServico(OrdemServico os) throws SQLException{
       sql = "UPDATE Usuario SET status = 1  WHERE idOrdemServico = ?";       
       con = ConexaoFactory.conectar();
       ps = con.prepareStatement(sql);
       ps.setInt(1, os.getIdOrdemServico());
       ps.executeUpdate();
       ConexaoFactory.close(con);       
       return true;
       
    }
   
    public boolean desativarOrdemServico(OrdemServico os)throws SQLException{
       sql = "UPDATE Usuario set status = 0 WHERE idOrdemServico = ?";       
       con = ConexaoFactory.conectar();
       ps = con.prepareStatement(sql);
       ps.setInt(1, os.getIdOrdemServico());       
       ps.executeUpdate();
       ConexaoFactory.close(con);       
       return true;
       
    }
    
    public boolean atualizarEntrga(int idOrdemServico) throws SQLException{ 
        sql = "UPDATE ordemservico SET dataEntrega = ? WHERE idOrdemServico = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);   
        ps.setDate(1, new Date(Date_Format.dataAtual().getTime()));     
        ps.setInt(2, idOrdemServico);
        
        ps.executeUpdate();
        return true;
    }
    
    
    public boolean atualizarStatus(int idOrdemServico)throws SQLException{
        sql = "UPDATE ordemservico SET status = ? WHERE idOrdemServico = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);  
      
        
            
        return true;           
    }
    
}