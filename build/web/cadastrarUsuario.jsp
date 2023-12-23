
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">  
        <meta http-equiv="X-UA-Compatible" content="IE-edge, chrome=1">  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/menu.css" type="text/css">
        <link rel="stylesheet" href="css/styles.css" type="text/css">                
        <title>Otica nova</title>
    </head>
    <body>
        <div id="container">                
            <div id="header"> 
                <jsp:include page="template/banner.jsp"></jsp:include><p>   

            </div><!-- Fim da div HEADER-->   
            <div id="menu"> 
                <jsp:include page="template/menu.jsp"></jsp:include>

            </div><!-- Fim da div MENU-->   
            <div id="conteudo" class="bg-background"> 
                <div class="container">                     
                                    
                    <jsp:include page="scripts/alert_edit_sessao.jsp"></jsp:include>  
                    <form action="gerenciarUsuario" method="POST"> 
                        <h3 class="text-center mt-3"> Cadastro de Usuários </h3>
                        <input type="hidden" name="idUsuario" value="${usuario.idUsuario}">

                        <div class="form-group row offset-md-3 mt-5">
                            <label class="col-md-1"> Nome</label>
                            <div class="col-md-6">
                                <input type="text" 
                                       name="nome" 
                                       value="${usuario.nome}" 
                                       class="form-control">                                
                            </div>
                        </div>

                        <div class="form-group row offset-md-3 mt-3">
                            <label class="col-md-1"> Login</label>
                            <div class="col-md-4">
                                <input type="text" 
                                       name="login" 
                                       value="${usuario.login}" 
                                       class="form-control">                                
                            </div>
                        </div>

                        <div class="form-group row offset-md-3 mt-3">
                            <label class="col-md-1"> Senha</label>
                            <div class="col-md-4">
                                <input type="password" 
                                       name="senha" 
                                       value="${usuario.senha}" 
                                       class="form-control">                                
                            </div>
                        </div>

                        <div class="form-group row offset-md-3 mt-3">
                            <label class="col-md-1"> Status</label>
                            <div class="col-md-4">
                                <select class="form-control" name="status"> 
                                    <option value="">Escolha uma opção </option>                                    
                                    <option value="1"
                                        <c:if test="${usuario.status == 1}">
                                            selected=""
                                        </c:if>>Ativado
                                    </option>                                    
                                    <option value="0"
                                        <c:if test="${usuario.status == 0}">
                                            selected=""
                                        </c:if>>Desativado
                                    </option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group row offset-md-3 mt-3">
                            <label class="col-md-1"> Perfil</label>
                            <div class="col-md-4">
                                <select class="form-control" name="idPerfil"> 
                                        <option value="">Escolha uma opção </option>
                                    <jsp:useBean class="dao.PerfilDAO" id="pdao"/>
                                    <c:forEach items="${pdao.listarPerfil}" var="p">
                                        <option value="${p.idPerfil}"
                                                <c:if test="${p.idPerfil == usuario.perfil.idPerfil}">
                                                    selected="" </c:if>>${p.nome} 
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div id="btn-cadastrar-usuario" 
                            class="d-md-flex justify-content-md-end mt-5" >
                            <button class="btn btn-primary btn-md mr-3">Gravar</button>
                            <a href="gerenciarUsuario?acao=listar"
                               class="btn btn-warning btn-md">Voltar</a>
                        </div>
                    </form>  
                                   
                </div> <!-- fim da class CONTAINER -->
            </div><!-- Fim da div CONTEUDO-->    
        </div><!-- Fim da div CONTAINER-->          
         
        <script src="js/jquery-3.6.0.min.js"></script>          
        <script src="bootstrap/bootstrap.min.js"></script> 
            
    </body>
</html>
