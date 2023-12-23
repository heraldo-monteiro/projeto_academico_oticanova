
package controller;

import dao.PerfilDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Menu;
import model.Perfil;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;


@WebServlet(name = "GerenciarMenuPerfil", urlPatterns = {"/gerenciarMenuPerfil"})
public class GerenciarMenuPerfil extends HttpServlet {
    RequestDispatcher dispatcher = null;
    Perfil perfil = null;     
    PerfilDAO pdao = null;

    @Override
    protected void doGet(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException {        
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");        
        String  acao = request.getParameter("acao");         
        String idPerfil = request.getParameter("idPerfil"); 
        String mensagem = "";        
            System.out.println("Açao: "+acao);
            System.out.println("Id Perfil: "+idPerfil);

            perfil = new Perfil();     
            pdao = new PerfilDAO();
        
        try {                
            if(acao.equals("vincular")){              
                    perfil = pdao.getCarregarPerfil(Integer.parseInt(idPerfil)); 
                    if(perfil.getIdPerfil() > 0){  
                        dispatcher = 
                        getServletContext().
                        getRequestDispatcher("/cadastrarMenuPerfil.jsp");
                        request.setAttribute("perfilv", perfil);
                        dispatcher.forward(request, response);                 
                    }
                    else{
                        mensagem = "Perfil não encontrador na base de dados!";
                    } 
                           
                                 
            }else if(acao.equals("desvincular")){                
                    String idMenu = request.getParameter("idMenu");
                    if(idMenu.equals("") || idMenu.isEmpty()){
                        mensagem = "O Menu deve ser selecionado!";                        
                    }else{
                        if(pdao.desvincularMenu(Integer.parseInt(idMenu), Integer.parseInt(idPerfil))){
                            mensagem = "Menu desvinculado com sucesso";
                        }else{
                            mensagem = "Falha ao desvincular o menu!";
                        }                       
                    }               
                                       
            }else{
                response.sendRedirect("index.jsp");
            }             
        }catch (SQLException erro) {
            mensagem = "Erro!"+ erro.getMessage();
            erro.printStackTrace();
        }   
        
        out.println(
                "<script type='text/javascript'>" +
                    "alert('"+mensagem+"');" +
                    "location.href='gerenciarMenuPerfil?acao=vincular&idPerfil="+idPerfil+"';" +
                "</script>"
        );       
          
    }

    @Override
    protected void doPost(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException {        
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String idMenu = request.getParameter("idMenu");
        String idPerfil = request.getParameter("idPerfil");
        String mensagem = "";         
        System.out.println("Id Menu: "+idMenu);
        System.out.println("Id Perfil: "+idPerfil); 
        
        pdao = new PerfilDAO();
        
        if(idMenu.equals("") || idPerfil.equals("")){
                mensagem = "Os campos são de preencimento obrigatórios!";
        }else{ 
            try {                 
                if(pdao.vincularMenu(Integer.parseInt(idMenu), 
                        Integer.parseInt(idPerfil))){
                    mensagem = "Menu vinculado com sucesso!";
                }else{
                    mensagem = "Falha ao vincular o menu!";
                }
            } catch (SQLException erro) {
                mensagem = "Erro!: "+erro.getMessage();
                erro.printStackTrace();
            }
            out.println(
                "<script type='text/javascript'>" +
                    "alert('"+mensagem+"');" +
                    "location.href='gerenciarMenuPerfil?acao=vincular&idPerfil="+idPerfil+"';" +
                "</script>"
            ); 
        }   
    }
}
    

