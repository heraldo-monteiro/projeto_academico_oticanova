<%-- 
    Document   : Login
    Created on : 10 de out. de 2023, 19:43:42
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
        <meta name="viewport" content="width=device-width, user-scale=no, shrink-to-fit=no">
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css" type="text/css">    
        <link rel="stylesheet" href="css/menu.css" type="text/css">  
        <link rel="stylesheet" href="css/styles.css" type="text/css">    
        <link rel="stylesheet" href="css/login.css" type="text/css">  
        
        <title>Sistema Interno</title>
    </head>
    <body>  
        <div id="container">
            <div id="header"> 
                <jsp:include page="template/banner.jsp"></jsp:include>  

            </div><!-- fim da div HEADER-->   
            <div id="menu"> 
          
            </div><!-- fim da div MENU-->   
            <div id"conteudo" class="bg-background">  
                <div class=" container">     
                    
                <jsp:include page="scripts/alert_edit_request.jsp"></jsp:include>
                    <fieldset class="mt-5">
                        
                        <form action="gerenciarLogin" method="POST">
                            <h3 class="text-center mt-5"> Página de Login</h3>
                            
                            <div class="form-group row offset-md-2 mt-5">
                                <label class="col-md-2">Usuário</label>                                          
                                <div class="col-md-4">    
                                    <input type="text" 
                                           name="login"
                                           value=""
                                           class="form-control">
                                </div>
                            </div> 

                            <div class="form-group row offset-md-2 mt-2">  
                                <label class="col-md-2"> Senha</label>   
                                <div class="col-md-4">
                                    <input type="password" 
                                           name="senha"
                                           value=""
                                           class="form-control">
                                </div>
                            </div> 
                                <div class="d-md-flex justify-content-md-end mt-3">                                        
                                    <button class="btn btn-primary btn-md"> Logar</button>                                                         
                                </div>                             
                        </form>
                        
                    </fieldset>
                </div> <!-- fim da div Class CONTAINER-->   
            </div><!-- Fim da div Id CONTEUDO-->             
        </div><!-- Fim da div Id CONTAINER-->   

        <script src="js/jquery-3.6.0.min.js"></script>
        <script src="bootstrap/bootstrap.min.js"></script>  
        
    </body>
</html>
