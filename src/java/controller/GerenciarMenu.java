 
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Menu;
import dao.MenuDAO;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "GerenciarMenu", urlPatterns = {"/gerenciarMenu"})
public class GerenciarMenu extends HttpServlet {    
    Menu menu = null;
    MenuDAO mdao = null;
    RequestDispatcher dispatcher = null;            

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String acao = request.getParameter("acao");
        String idMenu = request.getParameter("idMenu");
        String mensagem = "";                
            
        menu = new Menu();
        mdao = new MenuDAO();
        
        try {
            if(acao.equals("listar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    ArrayList<Menu> menus = new ArrayList<>();
                    menus = mdao.getListaMenu();
                    dispatcher = 
                    getServletContext().
                        getRequestDispatcher("/listarMenus.jsp");
                    request.setAttribute("menus", menus);
                    dispatcher.forward(request, response);
                }else{
                    mensagem = "Usuário não autorizado";
                }
                
                
        
        //----- Alterar Menu ---------------------------------------------------
            }else if(acao.equals("alterar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    menu = mdao.getCarregarMenu(Integer.parseInt(idMenu));                
                    if(menu.getIdMenu() > 0){                  
                        request.setAttribute("menu", menu);      
                        exibirMensagem(request, response);  
                    }else{
                        mensagem = "Menu não encontrado na base de dados!";
                    }
                }else{
                    mensagem = "Usuário não autorizado";
                }
                
                
                
            }else if(acao.equals("ativar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    menu.setIdMenu(Integer.parseInt(idMenu));
                    if(mdao.ativarMenu(menu)){
                       mensagem = "Menu ativado com sucesso!"; 
                    }else{
                        mensagem = "Flaha ao ativar o menu!"; 
                    }
                }else{
                    mensagem = "Usuário não autorizado";
                }                               

            }else if(acao.equals("desativar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    menu.setIdMenu(Integer.parseInt(idMenu));
                    if(mdao.desativarMenu(menu)){
                        mensagem = "Menu desativado com sucesso!";
                    }else{
                        mensagem = "Flaha ao desativar o menu!";
                    }
                }else{
                    mensagem = "Usuário não autorizado";
                }            
                
            }else{                
                response.sendRedirect("index.jsp");
            }
            
        } catch (SQLException erro) {
            mensagem = "Erro!: " + erro.getMessage();
            erro.printStackTrace();
        }        
        out.print(
                "<script type='text/javascript'>"+                        
                    "alert('"+ mensagem +"');"+
                    "location.href='gerenciarMenu?acao=listar';"+
                "</script>"
        );  
    }    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            String idMenu = request.getParameter("idMenu");
            String nome = request.getParameter("nome");
            String link = request.getParameter("link");
            String exibir = request.getParameter("exibir");
            String status = request.getParameter("status");
            String mensagem = "";
            String msg = "";
                                    
            menu = new Menu();
            mdao = new MenuDAO();
            
            if(!idMenu.isEmpty()){
                menu.setIdMenu(Integer.parseInt(idMenu));                
            }
            
            if(nome.isEmpty() || nome.equals("")){
                request.setAttribute("msg", "Nome do menu não informado!");
                exibirMensagem(request, response);
            }else{
                menu.setNome(nome);                
            }
            
            if(link.isEmpty() || link.equals("")){
                request.setAttribute("msg", "Link do menu não informado!");
                exibirMensagem(request, response);
            }else{
                menu.setLink(link);
            }
            
            if(exibir.isEmpty() || exibir.equals("")){
                request.setAttribute("msg", "Exibição do menu não informado!");
                exibirMensagem(request, response);
            }else{
                try {
                    menu.setExibir(Integer.parseInt(exibir));
                } catch (NumberFormatException erro) {
                    mensagem  = "Erro!: " + erro.getMessage();
                    erro.printStackTrace();
                }                
            }
            
            if(status.isEmpty() || status.equals("")){
                request.setAttribute("msg", "Status do menu não informado!");
                exibirMensagem(request, response);
            }else{
                try {
                    menu.setStatus(Integer.parseInt(status));
                } catch (NumberFormatException erro) {
                    mensagem = "Erro!: "+ erro.getMessage();
                    erro.printStackTrace();
                }   
            }
        //--- Metodo Gravar ----------------------------------------------------    
            try {
                if(mdao.registrarMenu(menu)){                    
                    mensagem = "Menu Registrado com sucesso!";
                }else{
                    mensagem = "Falha ao Registrar o menu na base de dados!";
                }          
        } catch (SQLException erro) {
            mensagem = "Erro!: " + erro.getMessage();
            erro.printStackTrace();           
        }           
        
        out.println(
                "<script type='text/javascript'>"+                        
                    "alert('"+ mensagem +"');"+
                    "location.href='gerenciarMenu?acao=listar';"+
                "</script>");  
    }
    
    private void exibirMensagem(HttpServletRequest request, 
        HttpServletResponse response)
        throws IOException, ServletException{        
        dispatcher = getServletContext().getRequestDispatcher("/cadastrarMenu.jsp");
        dispatcher.forward(request, response);
    }

}
