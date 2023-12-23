<%-- 
    Document   : cadastrarCliente
    Created on : 2 de nov. de 2023, 17:35:09
    Author     : Heraldo
--%>
<%@page import="java.util.Date"%>
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
        <link rel="shortcut icon" href="./imagens/oculos.jpeg">
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
                        <form action="gerenciarCliente" method="POST">    
                            <input type="hidden" name="idCliente" value="${cliente.idCliente}">
                            <input type="hidden" name="idade" value="${cliente.idade}"> 
                            
                            <h3 class="text-center mt-3"> Cadastro de Cliente</h3><hr>
                            
                            <div class="form-group row offset-md-2 mt-5">
                                <label class="col-md-2"> Nome</label>
                                <div class="col-md-7">
                                    <input  type="text" 
                                            name="nome"
                                            value="${cliente.nome}"  
                                            id="nome"
                                            class="form-control">  
                                </div>
                            </div>                                                        
                                                              
                            <div class="form-group row offset-md-2 mt-2">  
                                <jsp:include page="scripts/format_cpf.jsp"></jsp:include>
                                <label class="col-md-2">CPF</label>                               
                                <div class="col-md-4">
                                    <input  type="text" 
                                            name="cpf"
                                            value="${cliente.cpf}"  
                                            id="cpf"
                                            class="form-control"                                            
                                            maxlength="14"
                                            oninput="format_cpf(this)">  
                                </div>
                            </div>                                                                                            
                                            
                            <div class="form-group row offset-md-2 mt-2">
                                <jsp:include page="scripts/format_telefone.jsp"></jsp:include>
                                <label class="col-md-2">Telefone</label>
                                <div class="col-md-4">
                                    <input  type="text" 
                                            name="telefone"
                                            value="${cliente.telefone}"  
                                            id="telefone"
                                            class="form-control"
                                            maxlength="14"
                                            oninput="format_telefone(this)">  
                                </div>
                            </div>                                    
                           

                            <div class="form-group row offset-md-2 mt-2">
                                <label class="col-md-2">Data Nasc</label>
                                <div class="col-md-4">
                                    <input  type="Date" 
                                            name="dataNasc"
                                            value="${cliente.dataNasc}"  
                                            id="dataNasc"
                                            class="form-control">  
                                </div>
                            </div>   
                                            
                            <div class="form-group row offset-md-2 mt-2"> 
                                <label class="col-md-2"> Status</label>
                                <div class="col-md-4">
                                    <select class="form-control" 
                                            name="status">
                                        <option value=""> Selecione uma opção</option>
                                        <option value="1"
                                            <c:if test="${cliente.status == 1}">   
                                                selected=""
                                            </c:if>> 
                                            Ativo                                    
                                        </option>
                                         
                                        <option value="0"
                                            <c:if test="${cliente.status == 0}">
                                                selected=""
                                            </c:if>> 
                                            Suspensso                                      
                                        </option>                              
                                    </select>
                                </div>
                            </div>   

                            <div class="btn d-md-flex justify-content-md-end mt-3">                         
                                <button class="btn btn-primary btn-sm mr-3"> Registar</button>                                                 
                                <a href="gerenciarCliente?acao=listar"
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
