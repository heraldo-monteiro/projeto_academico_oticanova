
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">  
        <meta http-equiv="X-UA-Compatible" content="IE-edge, chrome=1">  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/menu.css" type="text/css">
        <link rel="stylesheet" href="css/stylePage.css" type="text/css"> 
         
        <title>Sistema Interno - Otica nova</title>
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
                    
                    <fieldset> 
                        <form action="#" method="POST">    
                            <input type="hidden" name="idMenu" value="${menu.idMenu}">
                            <h3 class="text-center mt-3"> Nome do Formulário </h3> 
            
                        </form>
                    </fieldset>
                </div> <!-- fim da div Class CONTAINER-->  
            </div> <!-- fim da div CONTEÚDO-->    
        </div> <!-- fim da div CONTAINER-->    
        
        <script src="js/jquery-3.6.0.min.js"></script>          
        <script src="bootstrap/bootstrap.min.js"></script> 
            
    </body>
</html>