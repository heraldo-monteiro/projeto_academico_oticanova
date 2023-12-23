<%-- 
    Document   : cadastrarLaboratorio
    Created on : 6 de nov. de 2023, 04:11:44
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
                        <form action="gerenciarLaboratorio" method="POST">    
                            <input type="hidden" name="idLaboratorio" value="${laboratorio.idLaboratorio}">
                            <h3 class="text-center mt-3"> Cadastro de Laboratório </h3><hr>
                            
                            <div class="form-group row offset-md-2 mt-2"> 
                                <label class="col-md-2"> Nome</label>
                                <div class="col-md-5">
                                    <input type="text"
                                           name="nome"
                                           value="${laboratorio.nome}"
                                           id="nome"
                                           class="form-control">
                                </div>                            
                            </div>
                            
                            <div class="form-group row offset-md-2 mt-2"> 
                                <label class="col-md-2"> Endereço</label>
                                <div class="col-md-5">
                                    <input type="text" 
                                           name="endereco" 
                                           value="${laboratorio.endereco}"
                                           id="endereco"
                                           class="form-control">                                    
                                </div>                            
                            </div>
                                        
                            <div class="form-group row offset-md-2 mt-2"> 
                                <label class="col-md-2"> E-mail</label>
                                <div class="col-md-5"> 
                                    <input type="email" 
                                           name="email" 
                                           value="${laboratorio.email}"
                                           id="email"
                                           class="form-control">
                                    
                                </div>                            
                            </div>                            
                            
                            <div class="form-group row offset-md-2 mt-2"> 
                                <jsp:include page="scripts/format_telefone.jsp"></jsp:include>
                                <label class="col-md-2"> Telefone</label>
                                <div class="col-md-3">
                                    <input type="text" 
                                           name="telefone" 
                                           value="${laboratorio.telefone}"
                                           id="telefone"
                                           class="form-control"
                                           oninput="format_telefone(this)">   
                                </div>                            
                            </div>
                            
                            <div class="form-group row offset-md-2"> 
                                <label class="col-md-2"> Status</label>
                                <div class="col-md-3">
                                    <select class="form-control" 
                                            name="status">
                                        <option value="">Escolha um opçao </option>
                                        <option value="1"
                                            <c:if test="${laboratorio.status == 1}">
                                                selected=""                                        
                                            </c:if>> Ativado 
                                        </option>
                                        <option value="0"
                                            <c:if test="${laboratorio.status == 0}">
                                                  selected=""                                         
                                            </c:if>> Desativado    
                                        </option> 
                                    </select>
                                </div>                            
                            </div>  
                            
                            <div class="d-md-flex justify-content-md-end mt-3"> 
                                <button class="btn btn-primary btn-md mr-3"> Cadastrar</button>
                                <a href="gerenciarLaboratorio?acao=listar"
                                   class="btn btn-warning btn-md"
                                   role="button"> Voltar</a>                            
                            </div>  
                        </form>
          
                </div> <!-- fim da div Class CONTAINER-->  
            </div> <!-- fim da div CONTEÚDO-->    
        </div> <!-- fim da div CONTAINER-->    
        
        <script src="js/jquery-3.6.0.min.js"></script>          
        <script src="bootstrap/bootstrap.min.js"></script> 
            
    </body>
</html>