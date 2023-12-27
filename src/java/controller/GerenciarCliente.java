
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.sql.SQLException;
import model.Cliente;
import dao.ClienteDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet(name = "GerenciarCliente", urlPatterns = {"/gerenciarCliente"})
public class GerenciarCliente extends HttpServlet {
    RequestDispatcher dispatcher = null;
    Cliente cliente = null;   
    ClienteDAO cdao = null;  

    @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String acao = request.getParameter("acao");
        String idCliente = request.getParameter("idCliente");
        String message = "";
        
        cliente = new Cliente();
        cdao = new ClienteDAO();        
        
        try {
            if(acao.equals("listar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                     ArrayList<Cliente> clientes = new ArrayList<>();
                    clientes = cdao.getListaCliente();
                    dispatcher = getServletContext().getRequestDispatcher("/listarClientes.jsp");
                    request.setAttribute("clientes",clientes);
                    dispatcher.forward(request, response);
                }else{   
                   message = "Acesso não altorizado!"; 
                }             
               
            }else if(acao.equals("alterar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    cliente = cdao.getCarregarCliente(Integer.parseInt(idCliente));                
                    if(cliente.getIdCliente() > 0){                    
                        dispatcher = getServletContext().getRequestDispatcher("/cadastrarCliente.jsp");
                        request.setAttribute("cliente", cliente);
                        dispatcher.forward(request, response);
                    }else{
                        message = "Cliente não encontrado na bae de dados!";                    
                    }
                }else{
                   message = "Acesso não altorizado!";  
                }
                
                

            }else if(acao.equals("ativar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    cliente.setIdCliente(Integer.parseInt(idCliente));                
                    if(cdao.ativarCliente(cliente)){
                        message = "Cliente ativado com sucesso!";                    
                    }else{
                        message = "Falha ao ativar o cliente!";                    
                    }
                }else{
                   message = "Acesso não altorizado!";  
                }
                

            }else if(acao.equals("desativar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    cliente.setIdCliente(Integer.parseInt(idCliente));                
                    if(cdao.desativarCliente(cliente)){
                        message = "Cliente Desativado com sucesso!";    
                    }else{
                        message = "Falha ao Desativar o cliente!";      
                    }
                }else{
                   message = "Acesso não altorizado!";  
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
                "alert(' "+message+ "');"+
                "location.href='gerenciarCliente?acao=listar';"+
            "</script>"
        );
     
     
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("uft-8");
        
        String idCliente = request.getParameter("idCliente");
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String dataNasc = request.getParameter("dataNasc");
        String idade = request.getParameter("idade");
        String status = request.getParameter("status");
        String mensagem = "";
        String msg = "";
        
        cliente = new Cliente();
        cdao = new ClienteDAO();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        
        if(!idCliente.isEmpty()){
            cliente.setIdCliente(Integer.parseInt(idCliente));
        }
        
        if(nome.isEmpty() || nome.equals("")){
            request.setAttribute("msg", "Nome do cliente não informado!");
            exibirMensagem(request, response);
        }else{
            cliente.setNome(nome);
        }
        
        if(cpf.isEmpty()|| cpf.equals("")){
            request.setAttribute("msg", "CPF não informado!");
            exibirMensagem(request, response);
        }else{
            if(cpf.equals("00000000000") || cpf.equals("000.000.000-00") || cpf.isEmpty()){
                request.setAttribute("msg", "Os dados não representa um cpf valido!");
                exibirMensagem(request, response);
            }else{
               cliente.setCpf(cpf);  
            }    
            cliente.setCpf(cpf);  
        }
        
        if(telefone.isEmpty() || telefone.equals("")){
            request.setAttribute("msg", "Informe o telefone de contato!");
            exibirMensagem(request, response);
        }else{
            cliente.setTelefone(telefone);
        }
        
        
        if(dataNasc.isEmpty() || dataNasc.equals("")){
            request.setAttribute("msg", "Data de nascimento não informada!");
            exibirMensagem(request, response);                
        }else{            
            try {
                cliente.setDataNasc(df.parse(dataNasc));     
                
            } catch (ParseException erro) {
                mensagem = "Erro!:" +erro.getMessage();
                erro.printStackTrace();
            }            
        }        
                
        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(cliente.getDataNasc());        
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        int diaNasc = dataNascimento.get(Calendar.DAY_OF_MONTH);
        int mesNasc = dataNascimento.get(Calendar.MONTH);
        int anoNasc = dataNascimento.get(Calendar.YEAR);
        int idadeAtual = anoAtual - anoNasc;
        cliente.setIdade(idadeAtual);            
            
                     
        if(status.isEmpty() || status.equals("")){
           request.setAttribute("msg", "Status não informado!");
           exibirMensagem(request, response); 
        }else{          
            cliente.setStatus(Integer.parseInt(status));
        }
    
        
    //--------------------------------------------------------------------------
        try {
            if(cdao.registrarCliente(cliente)){
                mensagem = "Cliete registrado com sucesso!";
            }else{
                mensagem = "Falha ao cadastrar cliente!";
            }                
        } catch (SQLException erro) {
            mensagem = "Erro!:" +erro.getMessage();
            erro.printStackTrace();
        }
        
        out.println(
            "<script type='text/javascript'>"+
                "alert('" + mensagem + "');"+
                "location.href='gerenciarCliente?acao=listar';"+
            "</script>"
        ); 
        
    }   
        

    private void exibirMensagem(HttpServletRequest request, 
        HttpServletResponse response)
        throws IOException, ServletException{        
        dispatcher = getServletContext().getRequestDispatcher("/cadastrarCliente.jsp");
        dispatcher.forward(request, response);
    }

}
