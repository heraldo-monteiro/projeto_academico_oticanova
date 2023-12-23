
package dao;
import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Usuario;
import java.sql.SQLException;
import model.Perfil;


public class UsuarioDAO {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql = "";
    
    public ArrayList<Usuario> getListarUsuario() throws SQLException{         
       ArrayList<Usuario> usuarios = new ArrayList<>();
        sql = "SELECT  p.idPerfil, p.nome, u.idUsuario, u.nome, u.login, u.senha, u.status, u.idPerfil "+
                "FROM perfil p "+
                "INNER JOIN usuario u "+
                "ON p.idPerfil = u.idPerfil";

        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while(rs.next()){
           Usuario u = new Usuario();           
           u.setIdUsuario(rs.getInt("u.idUsuario"));
           u.setNome(rs.getString("u.nome"));
           u.setLogin(rs.getString("u.login"));
           u.setSenha(rs.getString("u.senha"));
           u.setStatus(rs.getInt("u.status"));  
           
           Perfil p = new Perfil();
           p.setIdPerfil(rs.getInt("p.idPerfil"));
           p.setNome(rs.getString("p.nome"));           
        //Associação entre Usuario e Perfil    
           u.setPerfil(p);
           usuarios.add(u);
          
        }
        ConexaoFactory.close(con);
        return usuarios;       
       
    }   
   
    public Usuario getCarregarUsuario(int idUsuario)throws SQLException{
        Usuario usuario = new Usuario();
        Perfil perfil = new Perfil();
        sql = "SELECT p.idPerfil, p.nome, u.idUsuario, u.nome, "+
                "u.login, u.senha, u.status, u.idPerfil "+
                "FROM perfil p "+
                "INNER JOIN usuario u " +
                "ON p.idPerfil = u.idPerfil "+
                "WHERE idUsuario = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        rs = ps.executeQuery();
       
        if(rs.next()){
           usuario.setIdUsuario(rs.getInt("u.idUsuario"));
           usuario.setNome(rs.getString("u.nome"));
           usuario.setLogin(rs.getString("u.login"));
           usuario.setSenha(rs.getString("u.senha"));
           usuario.setStatus(rs.getInt("u.status"));    
           
           perfil.setIdPerfil(rs.getInt("p.idPerfil"));
           perfil.setNome(rs.getString("p.nome")); 
           
           //Associação entre Usuario e Perfil    
           usuario.setPerfil(perfil);              
           
        }
        ConexaoFactory.close(con);
        return usuario;
    }

   public boolean registrarUsuario(Usuario u)throws SQLException{
        con = ConexaoFactory.conectar();
        if(u.getIdUsuario() == 0){
            sql = "INSERT INTO usuario(nome, login, senha, status, idPerfil) "+
                    "VALUES(?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);

            ps.setString(1, u.getNome());
            ps.setString(2, u.getLogin());
            ps.setString(3, u.getSenha());
            ps.setInt(4, u.getStatus());
            ps.setInt(5, u.getPerfil().getIdPerfil());           
                              
        }else{
            sql = "UPDATE usuario SET nome = ?, login = ?, senha = ?, status = ?, idPerfil = ?  WHERE idUsuario = ?";
            ps = con.prepareStatement(sql);
        
            ps.setString(1, u.getNome());
            ps.setString(2, u.getLogin());
            ps.setString(3, u.getSenha());
            ps.setInt(4, u.getStatus());
            ps.setInt(5, u.getPerfil().getIdPerfil());
            ps.setInt(6, u.getIdUsuario());
 
        }
        ps.executeUpdate();
        ConexaoFactory.close(con);       
        return true;    
    }
   
    public boolean ativarUsuario(Usuario u) throws SQLException{
       sql = "UPDATE Usuario SET status = 1  WHERE idUsuario = ?";       
       con = ConexaoFactory.conectar();
       ps = con.prepareStatement(sql);
       ps.setInt(1, u.getIdUsuario());
       ps.executeUpdate();
       ConexaoFactory.close(con);       
       return true;
       
    }
   
    public boolean desativarUsuario(Usuario u) throws SQLException{
       sql = "UPDATE Usuario set status = 0 WHERE idUsuario = ?";       
       con = ConexaoFactory.conectar();
       ps = con.prepareStatement(sql);
       ps.setInt(1, u.getIdUsuario());       
       ps.executeUpdate();
       ConexaoFactory.close(con);       
       return true;
       
    }
    
    public Usuario getRecuperarUsuario(String login) throws SQLException{
          Usuario usuario  = new Usuario();
          sql = "SELECT u.idUsuario, u.nome, u.login, u.senha, u.status, u.idPerfil " +
                "FROM usuario u " +
                "WHERE u.login = ?";
          con = ConexaoFactory.conectar();
          ps = con.prepareStatement(sql);
          ps.setString(1, login);
          rs = ps.executeQuery();
          
          if(rs.next()){
              usuario.setIdUsuario(rs.getInt("u.idUsuario"));
              usuario.setNome(rs.getString("u.nome"));
              usuario.setLogin(rs.getString("u.login"));
              usuario.setSenha(rs.getString("u.senha"));
              usuario.setStatus(rs.getInt("u.status"));
              PerfilDAO pdao = new PerfilDAO();
              usuario.setPerfil(pdao.getCarregarPerfil(rs.getInt("u.idPerfil")));
              
          }          
          ConexaoFactory.close(con);                            
          return usuario;
      }
   
   
}
