
package dao;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Date;
import model.Menu;
import model.Perfil;


public class PerfilDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql = "";
    
    public ArrayList<Perfil> getListarPerfil() throws SQLException{
        ArrayList<Perfil> perfis = new ArrayList<>();        
        sql = "SELECT idPerfil, nome, dataCadastro, status FROM  perfil";
        
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        while(rs.next()){
            Perfil perfil = new Perfil();            
            perfil.setIdPerfil(rs.getInt("idPerfil"));
            perfil.setNome(rs.getString("nome"));
            perfil.setDataCadastro(rs.getDate("dataCadastro"));
            perfil.setStatus(rs.getInt("status"));
            perfis.add(perfil);            
        }
        
        ConexaoFactory.close(con);
        return perfis;
    }
    
    public boolean registrarPerfil (Perfil perfil) throws SQLException{
        con = ConexaoFactory.conectar();        
        if(perfil.getIdPerfil() == 0){
            sql = "INSERT INTO perfil(nome, dataCadastro, status) VALUES(?, ?, ?)";   
            
            ps = con.prepareStatement(sql);            
            ps.setString(1, perfil.getNome());
            ps.setDate(2, new Date(perfil.getDataCadastro().getTime()));
            ps.setInt(3, perfil.getStatus());           
        }else{
            sql = "UPDATE perfil SET nome = ?, dataCadastro = ?, status = ? WHERE idPerfil = ?"; 
            
            ps = con.prepareStatement(sql);
            ps.setString(1, perfil.getNome());
            ps.setDate(2, new Date(perfil.getDataCadastro().getTime()));
            ps.setInt(3, perfil.getStatus());
            ps.setInt(4, perfil.getIdPerfil());
        } 
        
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;
    }
    
    public Perfil getCarregarPerfil(int idPerfil) throws SQLException{
        Perfil perfil = new Perfil();        
        sql = "SELECT idPerfil, nome, dataCadastro, status FROM perfil WHERE idPerfil = ?";  
        
        con = ConexaoFactory.conectar();        
        ps = con.prepareStatement(sql);
        ps.setInt(1, idPerfil);
        rs = ps.executeQuery();
        
        if(rs.next()){
            perfil.setIdPerfil(rs.getInt("idPerfil"));
            perfil.setNome(rs.getString("nome"));
            perfil.setDataCadastro(rs.getDate("dataCadastro"));
            perfil.setStatus(rs.getInt("status"));   
            perfil.setMenus(menusVinculados(idPerfil));
            perfil.setNaoMenus(menusDesvinculados(idPerfil));   
        }  
        
        ConexaoFactory.close(con);
        return perfil;        
        
    }   
    
    public boolean ativarPerfil(Perfil perfil) throws SQLException{
            sql = "UPDATE perfil SET status  = 1 WHERE idPerfil = ?";
            
            con = ConexaoFactory.conectar();            
            ps = con.prepareStatement(sql);
            ps.setInt(1, perfil.getIdPerfil());
            ps.executeUpdate();  
            
        ConexaoFactory.close(con);
        return true;        
    }    
    
    public boolean desativarPerfil(Perfil perfil) throws SQLException{
        sql = "UPDATE perfil SET status = 0 WHERE idPerfil = ?";
        
        con = ConexaoFactory.conectar();        
        ps = con.prepareStatement(sql);
        ps.setInt(1, perfil.getIdPerfil());
        ps.executeUpdate();      
        
    ConexaoFactory.close(con);
    return true;
    }
   

    
//----- Menus Vinculados ao Perfil ---------------------------------------------       
    
    public ArrayList<Menu> menusVinculados(int idPerfil) throws SQLException{
        ArrayList<Menu> menus = new ArrayList<>();
        
        sql = "SELECT m.idMenu, m.nome, m.link, m.exibir, m.status " +
                "FROM menu_perfil AS mp, menu AS m " +
                "WHERE mp.idMenu = m.idMenu " +
                "AND mp.idPerfil = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idPerfil);
        rs = ps.executeQuery();
        
        while(rs.next()){
            Menu menu = new Menu();
            menu.setIdMenu(rs.getInt("m.idMenu"));
            menu.setNome(rs.getString("m.nome"));
            menu.setLink(rs.getString("m.link"));
            menu.setExibir(rs.getInt("m.exibir"));
            menu.setStatus(rs.getInt("m.status"));
            menus.add(menu);
        }
        
        ConexaoFactory.close(con);
        return menus;
    }
    
//----- Menus não Vinculados ao Perfil -----------------------------------------
    public ArrayList<Menu> menusDesvinculados(int idPerfil) throws SQLException{
        ArrayList<Menu> menus = new ArrayList<>();        
        sql = "SELECT m.idMenu, m.nome, m.link, m.exibir, m.status "+
                "FROM menu AS m "+
                "WHERE m.idMenu "+
                "NOT IN(SELECT mp.idMenu FROM menu_perfil AS mp WHERE mp.idPerfil = ?)";
                
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idPerfil);
        rs = ps.executeQuery();           
        while(rs.next()){
            Menu menu = new Menu();
            menu.setIdMenu(rs.getInt("m.idMenu"));
            menu.setNome(rs.getString("m.nome"));
            menu.setLink(rs.getString("m.link"));
            menu.setExibir(rs.getInt("m.exibir"));
            menu.setStatus(rs.getInt("m.status"));
            menus.add(menu);
            
        }
        ConexaoFactory.close(con);
        return menus;        
    }
    
//----- Vincular Menu ao Perfil ------------------------------------------------
    public boolean vincularMenu(int idMenu, int idPerfil) throws SQLException{
        sql = "INSERT INTO menu_perfil(idMenu, idPerfil) VALUES(?, ?)";
        
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idMenu);
        ps.setInt(2, idPerfil);
        ps.executeUpdate();
        
        ConexaoFactory.close(con);
        return true;
    }
       
    
    public boolean desvincularMenu(int idMenu, int idPerfil) throws SQLException{
        sql = "DELETE FROM menu_perfil WHERE idMenu = ? AND idPerfil = ?";
        
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idMenu);
        ps.setInt(2, idPerfil);
        ps.executeUpdate();
        
        ConexaoFactory.close(con);        
        return true;
    }
    
}
