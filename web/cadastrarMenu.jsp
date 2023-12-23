<%-- 
    Document   : cadastrarMenu
    Created on : 12 de out. de 2023, 16:36:29
    Author     : Heraldo
--%>

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
                    <form action="gerenciarMenu" method="POST">    
                        <input type="hidden" name="idMenu" value="${menu.idMenu}">
                        <h3 class="text-center mt-3"> Cadastrar Menu</h3><hr>

                        <div class="form-group row offset-md-2 mt-5">
                            <label class="col-md-2">Nome </label>
                            <div class="col-md-6">
                                <input type="text" 
                                       class="form-control"                                           
                                       name="nome"
                                       value="${menu.nome}">  
                            </div>
                        </div>  

                        <div class="form-group row offset-md-2 mt-3">
                            <label class="col-md-2"> Link</label>
                            <div class="col-md-6">
                                <input type="text" 
                                       class="form-control"                                                                                     
                                       name="link"
                                       value="${menu.link}">                                          
                            </div>
                        </div>

                        <div class="form-group row offset-md-2 mt-3">
                            <label class="col-md-2">Exibir</label>
                            <div class="col-md-4">
                                <select class="form-control" name="exibir">
                                    <option value=""> Escolha uma opção</option>
                                    <option value="1"
                                        <c:if test="${menu.exibir == 1}">
                                            selected=""
                                        </c:if>>
                                            Sim
                                    </option>
                                    <option value="0"
                                        <c:if test="${menu.exibir == 0}">
                                            selected=""
                                        </c:if>>
                                            Não
                                    </option>                                    
                                </select>    
                            </div>
                        </div>

                        <div class="form-group row offset-md-2 mt-3">
                            <label class="col-md-2"> Status</label>
                            <div class="col-md-4">
                                <select class="form-control" name="status">
                                    <option value=""> Escolha uma opção</option>
                                    <option value="1"
                                        <c:if test="${menu.status == 1}">
                                            selected=""
                                        </c:if>>
                                            Ativado
                                    </option>
                                    <option value="0"
                                        <c:if test="${menu.status == 0}">
                                            selected=""
                                        </c:if>>
                                            Desativado
                                    </option> 
                                </select>
                            </div> 
                        </div>

                        <div class="d-md-flex justify-content-md-end mt-3">
                            <button class="btn btn-primary btn-md mr-3"> Registrar</button>
                            <a href="gerenciarMenu?acao=listar"
                               class="btn btn-warning btn-md"
                               role="button"> Voltar</a>
                        </div>  
                    </form>
      
                </div><!-- Fim da div Class CONTAINER-->  
            </div><!-- Fim da div CONTEUDO-->    
        </div><!-- Fim da div CONTAINER-->    
        
          <script src="js/jquery-3.6.0.min.js"></script>          
          <script src="bootstrap/bootstrap.min.js"></script> 
            
    </body>
</html>
