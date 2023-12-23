
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import model.OrdemServico;
import dao.OrdemServicoDAO;
import model.Usuario;
import model.Cliente;
import model.Lente;
import model.Laboratorio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Perfil;


@WebServlet(name = "GerenciarOrdemServico", urlPatterns = {"/gerenciarOrdemServico"})
public class GerenciarOrdemServico extends HttpServlet {
    RequestDispatcher dispatcher = null;
    OrdemServico os = null; 
    OrdemServicoDAO osdao = null;
    
    Usuario usuario = new Usuario();
    Cliente cliente = new Cliente();
    Lente lente = new Lente();
    Laboratorio lab = new Laboratorio(); 
    Perfil perfil = new Perfil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String acao = request.getParameter("acao");
        String idOrdemServico = request.getParameter("idOrdemServico");
        String status = request.getParameter("status");
        String message = "";
        
        os = new OrdemServico();
        osdao = new OrdemServicoDAO();
        
        try {
            if(acao.equals("listar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    ArrayList<OrdemServico> ordemServicos = new ArrayList<>();
                    ordemServicos = osdao.getListarOrdemServico();
                    dispatcher = getServletContext().getRequestDispatcher("/listarOrdemServicos.jsp");
                    request.setAttribute("ordemServicos", ordemServicos);
                    dispatcher.forward(request, response);
                }else{
                    message = "Usuário não autorizado";
                }
                   
            }else if(acao.equals("atualizar")){  
                if(GerenciarLogin.verificarPermissao(request, response)){
                    if(osdao.atualizarEntrga(Integer.parseInt(idOrdemServico))){
                        message = "Entrega cofirmada!";
                    }else{
                        message = "Falha ao atualizar o status da entrega!";
                    }
                }else{
                    message = "Usuário não autorizado";
                }                               
            }
        } catch (SQLException erro) {
            message = "Erro!: "+erro.getMessage();
            erro.printStackTrace();
        }
        
      out.println(
            "<script type='text/javascript'>"+
              "alet('"+message+"')"+
              "location.href='gerenciarOrdemServico?acao=listar';"+
            "</script>"
      
      );
        
      
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        
        String  idOrdemServico = request.getParameter("idOrdemServico");
        String  dataSolicitacao = request.getParameter("dataSolicitacao");
        String  dataVencimento = request.getParameter("dataVencimento");      
        String  status = request.getParameter("status");
        String  idUsuario = request.getParameter("idUsuario");
        String  idCliente = request.getParameter("idCliente");
        String  idLente = request.getParameter("idLente");
        String  idLaboratorio = request.getParameter("idLaboratorio");
        String message = "";             
        
        os = new OrdemServico();
        osdao  = new OrdemServicoDAO();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");               
        
        if(!idOrdemServico.isEmpty()){
            os.setIdOrdemServico(Integer.parseInt(idOrdemServico));
        }
        
        if(dataSolicitacao.isEmpty() || dataSolicitacao.equals("")){
            request.setAttribute("msg", "Data da solicitação não informada!");          
            exibirMensagem(request, response); 
        }else{
            try {
                os.setDataSolicitacao(df.parse(dataSolicitacao));                
            } catch (ParseException erro) {
                message = "Erro!:"+erro.getMessage();
                erro.printStackTrace();
            }            
        }
        
        if(dataVencimento.isEmpty() || dataVencimento.equals("")){
            request.setAttribute("msg", "Data de vencimento não informada!");          
            exibirMensagem(request, response); 
        }else{
            try {                
                os.setDataVencimento(df.parse(dataVencimento));                
            } catch (ParseException erro) {
                message = "Erro!:"+erro.getMessage();
                erro.printStackTrace();
            }            
        }
        
        try {
            Date dataEntreg = null;
            os.setDataEntrega(dataEntreg);
            os.setDataVencimento(df.parse(dataVencimento));
        } catch (ParseException erro) {
            message = "Erro!:"+erro.getMessage();
            erro.printStackTrace();
        }
       
        if(idUsuario.isEmpty() || idUsuario.equals("")){
            request.setAttribute("msg", "O nome do atendente não foi informado!");
            exibirMensagem(request, response);
        }else{    
            usuario.setIdUsuario(Integer.parseInt(idUsuario));
            os.setUsuario(usuario);
        }            
        
        if(idCliente.isEmpty() || idCliente.equals("")){
            request.setAttribute("msg", "O nome do cliente não foi informado!");
            exibirMensagem(request, response);
        }else{
            cliente.setIdCliente(Integer.parseInt(idCliente));
            os.setCliente(cliente);
        }
    
        if(idLente.isEmpty() || idLente.equals("")){
            request.setAttribute("msg", "O nome da lente não foi informado!");
            exibirMensagem(request, response);
        }else{            
            lente.setIdLente(Integer.parseInt(idLente));
            os.setLente(lente);
        }

        if(idLaboratorio.isEmpty() || idLaboratorio.equals("")){
            request.setAttribute("msg", "O nome do laboratório não foi informado!");
            exibirMensagem(request, response);
        }else{
            lab.setIdLaboratorio(Integer.parseInt(idLaboratorio));
            os.setLaboratorio(lab);
        }
        
        try {
            if(osdao.registrarOrdemServico(os)){
                message = "Ordem de serviço foi registrada com sucesso!";
            }else{
                message = "Falha ao cadastrar a ordem de serviço!";
            }
        } catch (SQLException erro) {
            message = "Erro:"+ erro.getMessage();
            erro.printStackTrace();
        }
        
        
        out.println(
                "<script type='text/javascript'>"+
                    "alert('" + message + "');"+
                    "location.href='gerenciarOrdemServico?acao=listar';"+
                "</script>");
        
        
    }
    
    private void exibirMensagem(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException{
        dispatcher = getServletContext().getRequestDispatcher("/cadastrarOrdemServico.jsp");
        dispatcher.forward(request, response);
        
    }

}
