  <%-- 
    Document   : cadastrarLente
    Created on : 3 de nov. de 2023, 20:18:01
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
                        <form action="gerenciarLente" method="POST">    
                            <input type="hidden" name="idLente" value="${lente.idLente}">
                            <h3 class="text-center mt-3"> Cadastrar Lente</h3><hr>
                            
                            <div class="form-group row offset-md-2 mt-2">
                                <label class="col-md-2"> Marca</label>
                                <div class="col-md-4">
                                    <input type="text" 
                                           name="nome"  
                                           value="${lente.nome}"
                                           id="nome"
                                           class="form-control">
                                </div>  
                            </div>
                            
                            <div class="form-group row offset-md-2 mt-2">
                                <label class="col-md-2"> Modelo</label>
                                <div class="col-md-4">
                                    <input type="text" 
                                           name="modelo"
                                           value="${lente.modelo}"
                                           id="modelo"
                                           class="form-control">
                                </div>
                            </div>
                            
                            <div class="form-group row offset-md-2 mt-2">
                                <label class="col-md-2"> Fabricante</label>
                                <div class="col-md-4">
                                    <input type="text" 
                                           name="fabricante"
                                           value="${lente.fabricante}"
                                           id="fabricante"
                                           class="form-control">
                                </div>
                            </div>
                             
                            <div class="form-group row offset-md-2 mt-2">
                                <jsp:include page="scripts/format_input_ponto_virgula.jsp"></jsp:include>
                                <label class="col-md-2"> Preço</label>
                                <div class="col-md-4">
                                    <input type="text" 
                                           name="preco"
                                           value="${lente.preco}"                                           
                                           id="preco"   
                                           oninput="formatValue(this)"
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
                                                <c:if test="${lente.status  == 1}">
                                                    selected=""
                                                </c:if>> Ativo                                            
                                        </option>
                                        <option value="0"
                                                <c:if test="${lente.status == 0}">                                                   
                                                    selected=""
                                                </c:if>> Desativado                                            
                                        </option>  
                                    </select>
                                </div>
                            </div>
                            <div class="btn d-md-flex justify-content-md-end mt-3">
                                <button class="btn btn-primary btn-md mr-3"> Registar</button>
                                <a href="gerenciarLente?acao=listar"
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