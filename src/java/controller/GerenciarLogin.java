
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Menu;
import model.Usuario;
import dao.UsuarioDAO;
import java.sql.SQLException;


@WebServlet(name = "GerenciarLogin", urlPatterns = {"/gerenciarLogin"})
public class GerenciarLogin extends HttpServlet {

    private static HttpServletResponse response;
    RequestDispatcher dispatcher = null;  
    Usuario u = null;
    UsuarioDAO udao = null;
    
     
    @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession sessao = request.getSession();
        if(sessao.getAttribute("ulogado") != null){
            sessao.removeAttribute("ulogado");
            sessao.invalidate();
            response.sendRedirect("index.jsp");
        }
    }
            
    @Override
    protected void doPost(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        GerenciarLogin.response = response;
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String message = "";        
        u = new Usuario();
        udao = new UsuarioDAO();
        
        if(login.equals("") || login.isEmpty()){
            request.setAttribute("msg", "Informe o login do usuário!");
            exibirMensagem(request, response);
        }else{
            u.setLogin(login);
        }
        
        if(senha.equals("") || senha.isEmpty()){
            request.setAttribute("msg", "Informe a senha do usuário!");
            exibirMensagem(request, response);
        }else{
           u.setSenha(senha);
        }
        
        try {
            u = udao.getRecuperarUsuario(login);
            if((u.getIdUsuario() > 0) && (u.getSenha().equals(senha))){
                HttpSession sessao = request.getSession();
                sessao.setAttribute("ulogado", u);
                response.sendRedirect("index.jsp");
            }else{
                message = "Login ou senha inválidos!";                
            }            
        } catch (SQLException erro) {
            message = "Erro: "+erro.getMessage();
            erro.printStackTrace();
        }
        
        out.println(
                "<script type='text/javascript'>" +
                "alert('" +message+ "');" +
                "location.href='login.jsp';" +
                "</script>");
        
        
    }
    
    private void exibirMensagem(HttpServletRequest request, 
        HttpServletResponse response)
        throws IOException, ServletException{
            dispatcher = 
                getServletContext().
                    getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
    }
    
    public static Usuario verificarAcesso(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException{
            GerenciarLogin.response = response;
            Usuario usuario = null;
            
            try {  
                HttpSession sessao = request.getSession();        
                if(sessao.getAttribute("ulogado") == null){
                    //request.setAttribute("msg", "Usuário não auteticado no sistema!");
                    response.sendRedirect("login.jsp");
                }else{
                    String uri = request.getRequestURI(); // identificado universal de recursos
                    String queryString = request.getQueryString();

                    if(queryString != null){
                        uri += "?" + queryString;
                    }

                    usuario = (Usuario)request.getSession().getAttribute("ulogado");                
                    if(usuario == null){
                        request.setAttribute("msg", "Usuário não altenticado no sistema!");
                        response.sendRedirect("ligin.jsp");
                    }else{
                        boolean possuiAcesso = false;
                        for(Menu m: usuario.getPerfil().getMenus()){
                            if(uri.contains(m.getLink())){
                                possuiAcesso = true;
                                break;                            
                            }
                        }
                        if(!possuiAcesso){
                            request.setAttribute("msg", "Usuário não autorizado!");
                        }
                    }                
                }

                
            }catch (Exception erro) {
                System.out.println("Erro: "+ erro.getMessage());
                erro.printStackTrace();
            }
            return usuario;
    }
    
    public static boolean verificarPermissao(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException{
        GerenciarLogin.response = response;          
        Usuario usuario = null;
        boolean possuiAcesso = false;
        
        try {
            HttpSession sessao = request.getSession();
            if(sessao.getAttribute("ulogado") == null){
                sessao.setAttribute("msg", "Usuário não altenticado no sistema!");
                response.sendRedirect("login.jsp");
            }else{
                String uri = request.getRequestURI();
                String queryString = request.getQueryString();
                if(queryString != null){
                    uri += "?" +queryString;
                }
                
                usuario = (Usuario)request.getSession().getAttribute("ulogado");
                if(usuario == null){
                    sessao.setAttribute("msg", "Usuário não altentivcado no sistema");
                    response.sendRedirect("login.jsp");
                }else{
                    for(Menu m: usuario.getPerfil().getMenus()){
                        if(uri.contains(m.getLink())){
                            possuiAcesso = true;
                            break;
                        }
                    }
                }
            }            
        } catch (Exception erro) {
            System.out.println("Erro: "+erro.getMessage());
            erro.printStackTrace();
        }       
        return possuiAcesso;
    }
    
  
}
