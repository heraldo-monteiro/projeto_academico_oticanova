<%-- 
    Document   : listarMenu
    Created on : 12 de out. de 2023, 14:53:57
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
        <link rel="stylesheet" href="datatables/dataTables.bootstrap4.min.css" type="text/css">
        <link rel="stylesheet" href="datatables/jquery.dataTables.min.css" type="text/css">
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
            <main>
            <div id="conteudo" class="bg-background">    
                <div class="container">
                    <h3 class="text-center mt-5"> Lista de Menus</h3>
                    <a href="cadastrarMenu.jsp" 
                       class="btn btn-primary btn-md mb-3"
                       role="button">Novo Cadastro</a> 
                    
                    <table id="listarMenu" class="table table-hover table-striped">                    
                        <thead>
                            <tr>
                                <th> Código</th>
                                <th> Nome</th>
                                <th> Link</th>
                                <th> Exibição</th>
                                <th> Status</th>
                                <th> Ação</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${menus}" var="m">
                                <tr>
                                    <td> ${m.idMenu}</td>
                                    <td> ${m.nome}</td>
                                    <td> ${m.link}</td>                                    
                                    <td> 
                                        <c:choose>
                                            <c:when test="${m.exibir == 1}">
                                                Sim
                                            </c:when>
                                            <c:otherwise>
                                                Não
                                            </c:otherwise>
                                        </c:choose>
                                    </td>                                    
                                    <td> 
                                        <c:choose>
                                            <c:when test="${m.status == 1}">
                                                Ativado
                                            </c:when>
                                            <c:otherwise>
                                                Desativado
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    
                                    <td>                                                                               
                                        <a href="gerenciarMenu?acao=alterar&idMenu=${m.idMenu}"
                                           class="btn btn-warning btn-sm" 
                                           role="button"> Altera</a>
                                        
                                        <jsp:include page="scripts/ativar_desativar_menu.jsp"></jsp:include>
                                        <c:choose>
                                            <c:when test="${m.status == 1}">
                                                <button class="btn btn-danger btn-sm"
                                                        onclick="confirmDesativar('${m.idMenu}', '${m.nome}')">   
                                                    Desativar
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button class="btn btn-success btn-sm"
                                                        onclick="confirmAtivar('${m.idMenu}', '${m.nome}')">   
                                                    Ativar
                                                </button>
                                            </c:otherwise>                                            
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>   
                        </tbody>                     
                    </table>         
                </div>   
            </div><!-- Fim da div CONTEUDO-->      
            </main>
        </div><!-- Fim da div CONTAINER-->   
        
            <!-- 1º --><script src="js/jquery-3.6.0.min.js"></script>
            <!-- 2º --><script src="datatables/jquery.dataTables.min.js"></script>
            <!-- 3º --><script src="bootstrap/bootstrap.min.js"></script> 
            <!-- 4º --><script src="datatables/dataTables.bootstrap4.min.js"></script>  
            <script>
                $(document).ready( function () {
                    $('#listarMenu').dataTable({
                        "bJQueryUI":true,
                        "lengthMenu":[[5, 10 ,20, 25, -1], [5,10,20,25,"Todos"]],
                        "oLanguage":{
                            "sProcessing": "Processando",
                            "sLangthMenu": "Mostrar_MENU_registros",
                            "sZeroRecords": "Não foram encontrados resultados",
                            "sInfo": "Mostrando de _SATATUS_ até _END_ de _TOTAL_ registros",
                            "sInfoEmpty": "Mostrado de 0 até 0 de 0 registros",
                            "sInfoFiltered": "", 
                            "sInfoPostFix": "",
                            "sSearch": "Pesquisar",
                            "sUrl": "",
                            "oPaginate": {                                
                                "sfirst": "Primeiro",
                                "sPrevious": "Anterior",
                                "sNext": "Próximo",
                                "sLast": "Último"
                            }
                        }
                    });
                } );                
            </script>    
    </body>
</html>

