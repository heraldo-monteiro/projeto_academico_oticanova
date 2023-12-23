
package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Lente;
import dao.LenteDAO;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.sql.SQLException;

@WebServlet(name = "GerenciarLente", urlPatterns = {"/gerenciarLente"})
public class GerenciarLente extends HttpServlet {
    RequestDispatcher dispatcher = null;    
    Lente lente = null;
    LenteDAO ldao = null;
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String acao = request.getParameter("acao");
        String idLente = request.getParameter("idLente");
        String message = "";
        
        lente = new Lente();
        ldao = new LenteDAO();
        
        try {
            if(acao.equals("listar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    ArrayList<Lente> lentes = new ArrayList<>();
                    lentes = ldao.getListarLente();            
                    dispatcher = getServletContext().getRequestDispatcher("/listarLentes.jsp");
                    request.setAttribute("lentes", lentes);
                    dispatcher.forward(request, response);
                }else{
                    message = "Usuário não autorizado";
                }                
                        
            }else if(acao.equals("alterar")){   
                if(GerenciarLogin.verificarPermissao(request, response)){
                    lente = ldao.getCarregarLente(Integer.parseInt(idLente));             
                    if(lente.getIdLente() > 0){
                        dispatcher = getServletContext().getRequestDispatcher("/cadastrarLente.jsp");
                        request.setAttribute("lente", lente);
                        dispatcher.forward(request, response);                
                    }else{
                        message = "Lente não encontrada no banco de dados!";                
                    }
                }else{
                    message = "Usuário não autorizado";
                }            

            }else if(acao.equals("ativar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    lente.setIdLente(Integer.parseInt(idLente));
                    if(ldao.ativarLente(lente)){
                        message = "Lente foi ativado com sucesso!";
                    }else{
                       message = "Falha ao ativar a lente!"; 
                    }
                }else{
                    message = "Usuário não autorizado";
                }                        

            }else if(acao.equals("desativar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    lente.setIdLente(Integer.parseInt(idLente));
                    if(ldao.desativarLente(lente)){
                        message = "Lente desativada com sucesso!";                
                    }else{
                        message = "Falha ao desativar a lente!";
                    }
                }else{
                    message = "Usuário não autorizado";
                }                       

            }else{
                response.sendRedirect("index.jsp");
            }            
        } catch (SQLException erro) {
            message = "Erro!: "+ erro.getMessage(); 
            erro.printStackTrace();            
        }
        
        out.println(
                "<script type='text/javascript'>" +
                    "alert('"+message+"');"+
                    "location.href='gerenciarLente?acao=listar';"+
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
        
        String idLente = request.getParameter("idLente");
        String nome = request.getParameter("nome");
        String modelo = request.getParameter("modelo");
        String fabricante = request.getParameter("fabricante");
        String preco = request.getParameter("preco");
        String status = request.getParameter("status");
        String message = "";
        String msg = "";
        
        lente = new Lente();
        ldao = new LenteDAO();
        
        if(!idLente.isEmpty()){
            lente.setIdLente(Integer.parseInt(idLente));
        }
        
        if(nome.isEmpty() || nome.equals("")){
            request.setAttribute("msg", "Nome da lente não informado!");
            exibirMessage(request, response);
        }else{
            lente.setNome(nome);
        }
       
        if(modelo.isEmpty() || modelo.equals("")){
            request.setAttribute("msg", "Modelo da lente não informado!");
            exibirMessage(request, response);
        }else{
            lente.setModelo(modelo);
        }
        
        if(fabricante.isEmpty() || fabricante.equals("")){
            request.setAttribute("msg", "Fabricante da lente não informado!");
            exibirMessage(request, response);
        }else{
            lente.setFabricante(fabricante);
        }
        
        if(preco.isEmpty() || preco.equals("")){
           preco = "0.0";
           lente.setPreco(Double.valueOf(preco));
        }else{
            lente.setPreco(Double.valueOf(preco));            
            request.setAttribute("msg", "O formato do preço estar invalido!");
            exibirMessage(request, response);
        }
        
        if(status.isEmpty() || status.equals("")){
            request.setAttribute("msg", "Status da lenta não selecionado!");
            exibirMessage(request, response);
        }else{
            lente.setStatus(Integer.parseInt(status));
        }
        
    //--------------------------------------------------------------------------          
        try {
            if(ldao.registrarLente(lente)){
                message = "Cadastro realizado com sucesso!";
            }else{
                message = "Falha ao cadastra a lente!";
            }                
        } catch (SQLException erro) {
            message = "Erro!: "+erro.getMessage();
            erro.printStackTrace();
        }        
                
        out.println(
              "<script type='text/javascript'>"+
                  "alert('" + message + "');"+
                  "location.href='gerenciarLente?acao=listar';"+
              "</script>"
          ); 
    }
    
    public void exibirMessage(HttpServletRequest request, 
            HttpServletResponse response) throws IOException, ServletException{
        dispatcher = getServletContext().getRequestDispatcher("/cadastrarLente.jsp");
        dispatcher.forward(request, response);
    }


}
