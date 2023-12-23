<%-- 
    Document   : cadastrarPerfil
    Created on : 28 de out. de 2023, 19:31:14
    Author     : Heraldo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <form action="gerenciarPerfil" method="POST">  
                        <input type="hidden" name="idPerfil" value="${perfil.idPerfil}"/>
                        <h3 class="text-center mt-3"> Cadastro de Perfil </h3>

                        <div class="form-group row mt-5 offset-md-2">
                            <label class="col-md-3"> Nome </label>                                          
                            <div class="col-md-5">
                                <input type="text" 
                                    name="nome" 
                                    value="${perfil.nome}"
                                    id="nome"
                                    class="form-control" /> 
                            </div>                        
                        </div> 

                        <div class="form-group row offset-md-2 mt-2">
                            <label  class="col-md-3"> Data de Cadastro </label>
                            <div class="col-md-5">
                                <input type="Date"
                                    name="dataCadastro"
                                    value="${perfil.dataCadastro}"
                                    id="dataCadastro"
                                    class="form-control"/>
                            </div>
                        </div>    

                        <div class="form-group row offset-md-2 mt-2">
                            <label class="col-md-3"> Status </label>
                            <div class="col-md-4">
                                <select class="form-control" 
                                        name="status">
                                    <option value="">Escolha um opçao </option>
                                    <option value="1"
                                        <c:if test="${perfil.status == 2}">
                                            selected=""                                           
                                        </c:if>>
                                        Ativado
                                    </option>
                                    <option value="0"
                                        <c:if test="${perfil.status == 0}">
                                            selected=""
                                        </c:if>>
                                        Desativado
                                    </option> 
                                </select>
                            </div>

                            <div class="btn d-md-flex justify-content-md-end mt-5"> 
                                <button class="btn btn-primary btn-md mr-3"> Cadastrar</button>                                
                                <a href="gerenciarPerfil?acao=listar"
                                   class="btn btn-warning btn-md"
                                   role="button">Voltar</a>
                            </div>
                        </div>
                    </form>
                      
                </div><!-- Fim da div Class CONTAINER-->                               
            </div><!-- Fim da div CONTEUDO-->    
        </div><!-- Fim da div CONTAINER-->          
         
        <script src="js/jquery-3.6.0.min.js"></script>          
        <script src="bootstrap/bootstrap.min.js"></script> 
            
    </body>
</html>
