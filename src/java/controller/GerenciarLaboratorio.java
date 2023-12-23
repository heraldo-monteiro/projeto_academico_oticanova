
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Laboratorio;
import dao.LaboratorioDAO;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import java.sql.SQLException;


@WebServlet(name = "GerenciarLaboratorio", urlPatterns = {"/gerenciarLaboratorio"})
public class GerenciarLaboratorio extends HttpServlet {
    RequestDispatcher dispatcher = null;
    Laboratorio lab = null;
    LaboratorioDAO labdao = null;


    @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String acao = request.getParameter("acao");
        String idLaboratorio = request.getParameter("idLaboratorio");
        String message = "";
        
        lab = new Laboratorio();
        labdao = new LaboratorioDAO();
        
        try {
            if(acao.equals("listar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    ArrayList<Laboratorio> laboratorios = new ArrayList<>();
                    laboratorios = labdao.getListarLaboratorio();
                    dispatcher = getServletContext().getRequestDispatcher("/listarLaboratorios.jsp");
                    request.setAttribute("laboratorios", laboratorios);
                    dispatcher.forward(request, response);
                }else{
                    message = "Usuário não autorizado!";
                }                
                                
            }else if(acao.equals("alterar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    lab = labdao.getCarregarLaboratorio(Integer.parseInt(idLaboratorio));                
                    if(lab.getIdLaboratorio() > 0){
                        dispatcher = getServletContext().getRequestDispatcher("/cadastrarLaboratorio.jsp");
                        request.setAttribute("laboratorio", lab);
                        dispatcher.forward(request, response);
                    }else{
                        message = "Laboratório não encontrado na base de dados!";
                    }
                }else{
                    message = "Usuário não autorizado!";
                }                               

            }else if(acao.equals("ativar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    lab.setIdLaboratorio(Integer.parseInt(idLaboratorio));
                    if(labdao.ativarLaboratorio(lab)){
                        message = "Laboratório ativado com sucesso!";
                    }else{
                        message = "Falha ao ativar o laboratórios!"; 
                    }
                }else{
                    message = "Usuário não autorizado!";
                }                                

            }else if(acao.equals("desativar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    lab.setIdLaboratorio(Integer.parseInt(idLaboratorio));
                    if(labdao.desativarLaboratorio(lab)){
                        message = "Laboratório desativado com sucesso!";
                    }else{
                        message = "Falha ao desativar o laboratório!";
                    }
                }else{
                    message = "Usuário não autorizado!";
                }
                                
            }else{
                response.sendRedirect("/index.jsp");
            } 
            
        } catch (SQLException erro) {
            message = "Erro!: "+erro.getMessage();
            erro.printStackTrace();
        }      
        
        out.println(
                "<script type='text/javascript'>"+
                    "alert('" + message + "');"+
                    "location.href='gerenciarLaboratorio?acao=listar';"+
                "</script>");        
       
    }

   
    @Override
    protected void doPost(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        
        String idLaboratorio = request.getParameter("idLaboratorio");
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        String status = request.getParameter("status");
        String message = "";
        String msg = "";
        
        lab = new Laboratorio();
        labdao = new LaboratorioDAO();
        
        if(!idLaboratorio.isEmpty()){
            lab.setIdLaboratorio(Integer.parseInt(idLaboratorio));
        }
        
        if(nome.isEmpty() || nome.equals("")){
            request.setAttribute("msg", "Nome laboratório não informado!");
            exibirMensagem(request, response);
        }else{
            lab.setNome(nome);
        }
        
        if(endereco.isEmpty() || endereco.equals("")){
            request.setAttribute("msg", "Endereço não informado!");
            exibirMensagem(request, response);
        }else{
            lab.setEndereco(endereco);
        }
        
        if(telefone.isEmpty() || telefone.equals("")){
            request.setAttribute("msg", "Telefone não informado!");
            exibirMensagem(request, response);
        }else{
            lab.setTelefone(telefone);
        }
        
        if(email.isEmpty() || email.equals("")){
            request.setAttribute("msg", "E-mail não informado!");
            exibirMensagem(request, response);
        }else{
            lab.setEmail(email);
        }
        
        if(status.isEmpty() || status.equals("")){
            request.setAttribute("msg", "Status não informado!");
            exibirMensagem(request, response);
        }else{
            lab.setStatus(Integer.parseInt(status));
        }
        
    //--------------------------------------------------------------------------
        try {
            if(labdao.registrarLaboratorio(lab)){
                message = "Laboratório cadastrado com sucesso!";                
            }else{
                message = "Falha ao cadastrar laboratório";
            }
        } catch (SQLException erro) {
            message = "Erro!:" +erro.getMessage();
            erro.printStackTrace();
        }    
        
        out.println("<script type='text/javascript'>"+
                    "alert('" + message + "');"+
                    "location.href='gerenciarLaboratorio?acao=listar';"+
                "</script>");
        
    }
    
    private void exibirMensagem(HttpServletRequest request, 
        HttpServletResponse response)
        throws IOException, ServletException{        
        dispatcher = getServletContext().getRequestDispatcher("/cadastrarLaboratorio.jsp");
        dispatcher.forward(request, response);
    } 


}
