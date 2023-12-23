
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
import model.Perfil;
import model.Usuario;
import dao.UsuarioDAO;



@WebServlet(name = "GerenciarUsuario", urlPatterns = {"/gerenciarUsuario"})
public class GerenciarUsuario extends HttpServlet {    
        UsuarioDAO udao = null;
        Usuario usuario = null;  
        RequestDispatcher dispatcher = null;
  
    @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");        
        String acao = request.getParameter("acao");
        String idUsuario = request.getParameter("idUsuario");        
        String mensagem = "";   
        
        usuario = new Usuario();       
        udao = new UsuarioDAO(); 
        
        try {
        //----- Listar Usuário -------------------------------------------------
            if(acao.equals("listar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    ArrayList<Usuario> usuarios = new ArrayList<>();
                    usuarios = udao.getListarUsuario();
                    dispatcher = getServletContext().
                                getRequestDispatcher("/listarUsuarios.jsp");
                    request.setAttribute("usuarios", usuarios);
                    dispatcher.forward(request, response);
                }else{
                    mensagem = "Usuário não autorizado";
                }               
                       
        //----- Altera Usuário -------------------------------------------------
            }else if(acao.equals("alterar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    usuario = udao.getCarregarUsuario(Integer.parseInt(idUsuario));                
                    if(usuario.getIdUsuario() > 0){  
                        dispatcher = 
                        getServletContext().
                            getRequestDispatcher("/cadastrarUsuario.jsp");
                        request.setAttribute("usuario", usuario);
                        exibirMensagem(request, response);                                                        
                    }else{
                    mensagem = "Usuário não encontrado!";                    
                    }
                }else{
                    mensagem = "Usuário não autorizado";
                }                
                
                
        //----- Ativar Usuário -------------------------------------------------
            }else if(acao.equals("ativar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    usuario.setIdUsuario(Integer.parseInt(idUsuario));
                    if(udao.ativarUsuario(usuario)){
                        mensagem = "Usuário ativado!";
                    }else{
                        mensagem = "Falha ao ativa usuário!";
                    }
                }else{
                    mensagem = "Usuário não autorizado";
                }                                
                
        //----- Desativar Usuário ----------------------------------------------
            }else if(acao.equals("desativar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    usuario.setIdUsuario(Integer.parseInt(idUsuario));
                    if(udao.desativarUsuario(usuario)){
                        mensagem = "Usuário desativado!";
                    }else{
                        mensagem = "Falha ao desativar o usuário!";
                    }                    
                }else{
                    mensagem = "Usuário não autorizado";
                }                
                
        //----- Redirect Usuário -----------------------------------------------
            }else{
                response.sendRedirect("index.jsp");
            }
            
        } catch (SQLException erro) {
            mensagem = "Erro!: "+ erro.getMessage();
            erro.printStackTrace();
        }   
        out.print(
            "<script type='text/javascript'>"+
                "alert(' "+mensagem+ "');"+
                "location.href='gerenciarUsuario?acao=listar';"+
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
        
        String idUsuario = request.getParameter("idUsuario");
        String nome = request.getParameter("nome"); 
        String login = request.getParameter("login");
        String senha  = request.getParameter("senha");
        String status = request.getParameter("status");
        String idPerfil = request.getParameter("idPerfil");
        String mensagem = "";
        String msg = "";
        
        usuario = new Usuario();
        
        if(!idUsuario.isEmpty()){
            usuario.setIdUsuario(Integer.parseInt(idUsuario));            
        }
        
        if(nome.equals("") || nome.isEmpty()){
            request.setAttribute("msg", "Informe o nome do usuário!");
            exibirMensagem(request, response);
        }else{
            usuario.setNome(nome);            
        } 
        
        if(login.equals("") || login.isEmpty()){
            request.setAttribute("msg", "Informe o login do usuário!");
            exibirMensagem(request, response);
        }else{
            usuario.setLogin(login);
        }
        if(senha.equals("") || senha.isEmpty()){
            request.setAttribute("msg", "Informe a senha do usuário!");
            exibirMensagem(request, response);
        }else{
            usuario.setSenha(senha);
        }
        
        if(status.equals("") || status.isEmpty()){
            request.setAttribute("msg", "Informe o status do usuário!");
            exibirMensagem(request, response);
        }else{
            usuario.setStatus(Integer.parseInt(status));
        }
        
        Perfil perfil = new Perfil();
        if(idPerfil.equals("") || idPerfil.isEmpty()){
            request.setAttribute("msg", "Informe o perfil do usuário!");
            exibirMensagem(request, response);
        }else{
            perfil.setIdPerfil(Integer.parseInt(idPerfil));  
        }    
        //Associação entre Usuário é Perfil
        usuario.setPerfil(perfil);
        
        
        //--- METODO GRAVAR DA CLASSE DAO -------------------------------------- 
        try {
            if(udao.registrarUsuario(usuario)){
                mensagem = "Usuário salvo com sucesso!";                
            }else{
                mensagem = "Falha ao cadastrar usuario!";
            }            
        } catch (SQLException erro) {
            mensagem = "Erro: "+ erro.getMessage();
            erro.printStackTrace();
        }
        
        //----------------------------------------------------------------------
        out.println(
                "<script type='text/javascript'>"+
                    "alert('" + mensagem + "');"+
                    "location.href='gerenciarUsuario?acao=listar';"+
                "</script>");
        
    }

    private void exibirMensagem(HttpServletRequest request, 
        HttpServletResponse response)
        throws IOException, ServletException{
        
        dispatcher = getServletContext().getRequestDispatcher("/cadastrarUsuario.jsp");
        dispatcher.forward(request, response);
    }

}
