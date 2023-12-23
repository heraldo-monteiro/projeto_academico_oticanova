
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import dao.PerfilDAO;
import model.Perfil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "GerenciarPerfil", urlPatterns = {"/gerenciarPerfil"})
public class GerenciarPerfil extends HttpServlet { 
    RequestDispatcher dispatcher = null;
    PerfilDAO pdao =  null;
    Perfil perfil = null;

    
    @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String acao =  request.getParameter("acao");
        String idPerfil = request.getParameter("idPerfil");
        String mensagem = "";
               
        pdao = new PerfilDAO();
        perfil = new Perfil();
        
        try {
            if(acao.equals("listar")){     
                if(GerenciarLogin.verificarPermissao(request, response)){
                    ArrayList<Perfil> perfis = new ArrayList<>();
                    perfis = pdao.getListarPerfil();
                    dispatcher = getServletContext().getRequestDispatcher("/listarPerfis.jsp");
                    request.setAttribute("perfis", perfis);
                    dispatcher.forward(request, response);  
                }else{
                    mensagem = "Acesso não altorizado!";
                }                              
                    
            }else if(acao.equals("alterar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    perfil = pdao.getCarregarPerfil(Integer.parseInt(idPerfil));                 
                    if(perfil.getIdPerfil() > 0){
                        dispatcher = 
                            getServletContext().
                                getRequestDispatcher("/cadastrarPerfil.jsp");
                        request.setAttribute("perfil", perfil);
                        dispatcher.forward(request, response);  
                    }else{
                        mensagem = "Perfil não encontrado na base de dados!";
                    }
                }else{
                    mensagem = "Acesso não altorizado!";
                }   
                
            }else if(acao.equals("ativar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    perfil.setIdPerfil(Integer.parseInt(idPerfil));
                    if(pdao.ativarPerfil(perfil)){
                        mensagem = "Perfil ativado com sucesso! ";                    
                    }else{
                        mensagem = "Falha ao ativar o Perfil!";
                    } 
                }else{
                    mensagem = "Acesso não altorizado!";
                } 
                
            }else if(acao.equals("desativar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    perfil.setIdPerfil(Integer.parseInt(idPerfil));
                    if(pdao.desativarPerfil(perfil)){
                        mensagem = "Perfil Desativado com sucesso!";
                    }else{
                        mensagem = "Falha ao desativar o Perfil";
                    }
                }else{
                    mensagem = "Acesso não altorizado!";
                }                                                   
            } else{                
                response.sendRedirect("index.jsp"); 
            }          
                    
        } catch (SQLException erro) {    
            mensagem = "Erro: " + erro.getMessage();
            erro.printStackTrace(); // mostra por onde o erro possa ter se propagado!
                  
        }
        
        out.println(
                "<script type='text/javascript'>"+
                    "alert('" + mensagem + "');"+
                    "location.href='gerenciarPerfil?acao=listar';"+
                "</script>");  
        
     
    }
      
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");         
        
        String idPerfil = request.getParameter("idPerfil");
        String nome = request.getParameter("nome");
        String dataCadastro = request.getParameter("dataCadastro");
        String status = request.getParameter("status");
        String mensagem = "";
        String msg = "";    
        
        perfil = new Perfil();
        pdao = new PerfilDAO();  
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        if(!idPerfil.isEmpty()){         
            perfil.setIdPerfil(Integer.parseInt(idPerfil));            
        }
        
        if(nome.isEmpty() || nome.equals("")){
            request.setAttribute("msg", "Informe o nome do Perfil!");
            exibirMensagem(request, response);
        }else{
            perfil.setNome(nome);
        }
        
        if(dataCadastro.isEmpty() || dataCadastro.equals("")){
            request.setAttribute("msg", "Informe a data de registro do Perfil!");          
            exibirMensagem(request, response);
        }else{
            try {
                perfil.setDataCadastro(df.parse(dataCadastro));  
                
            } catch (ParseException erro) {
                mensagem = "Erro!:"+erro.getMessage();
                erro.printStackTrace();
            }
        }
        
        if(status.isEmpty() || status.equals("")){
            request.setAttribute("msg", "Informe o status do Perfil!");
            exibirMensagem(request, response);
        }else{
            perfil.setStatus(Integer.parseInt(status));
        }        
    
    //--------------------------------------------------------------------------
        try {
            if(pdao.registrarPerfil(perfil)){
                mensagem = "Perfil registrado com sucesso!";                
            }else{
                mensagem = "Erro!: Falha ao registrar o Perfil!";
            }           
            
        } catch (SQLException erro) {
            mensagem = "Erro!: "+erro.getMessage();
            erro.printStackTrace();
        }       
        
        out.println("<script type='text/javascript'>"+
                    "alert('" + mensagem + "');"+
                    "location.href='gerenciarPerfil?acao=listar';"+
                "</script>"
        ); 
        
    }
            
    private void exibirMensagem(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException{
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrarPerfil.jsp");
        dispatcher.forward(request, response);
        
    }
        

}
