<%-- 
    Document   : listarPerfil
    Created on : 26 de out. de 2023, 16:28:53
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
                
            </div> <!-- fim da div HEADER-->
            <div id="menu">
                <jsp:include page="template/menu.jsp"></jsp:include>
            
            </div><!-- fim da div MENU-->
            <div id="conteudo" class="bg-background">
                <div class="container">
                    <h3 class="text-center mt-5"> Lista de Perfis</h3>
                    <a href="cadastrarPerfil.jsp"
                       class="btn btn-primary btn-md mb-3"
                       role="button"> Novo Cadastro</a>
                    
                    <table id="listarPerfis"
                           class="table 
                           table-hover
                           table-striped mt-3">                        
                        <thead>
                            <tr>
                                <th> Código</th>
                                <th> Nome</th>
                                <th> Data de Cadastro</th>
                                <th> Status</th>
                                <th> Acão</th>
                            </tr>                            
                        </thead>
                        <tbody>
                        <c:forEach items="${perfis}" var="p">
                                <tr>
                                    <td>${p.idPerfil}</td>
                                    <td>${p.nome}</td>
                                    <td>
                                        <fmt:formatDate pattern="dd/MM/yyyy"
                                        value="${p.dataCadastro}"/>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${p.status == 1}">
                                                Ativado
                                            </c:when>
                                            <c:otherwise>
                                                Desativado
                                            </c:otherwise>
                                        </c:choose>                                         
                                    </td>                                    
                                    <td>                                                                              
                                        <a href="gerenciarPerfil?acao=alterar&idPerfil=${p.idPerfil}"
                                           class="btn btn-warning btn-sm"
                                           role="button"> Alterar
                                        </a>
                                        
                                        <jsp:include page="scripts/ativar_desativar_perfil.jsp"></jsp:include>    
                                        <c:choose>
                                            <c:when test="${p.status == 1}">
                                                <button 
                                                    class="btn btn-danger btn-sm a"
                                                    onclick="confirmDesativar('${p.idPerfil}','${p.nome}')">
                                                    Desativar                                                    
                                                </button>                                                
                                            </c:when>
                                            <c:otherwise>
                                                <button 
                                                    class="btn btn-success btn-sm a"
                                                    onclick="confirmAtivar('${p.idPerfil}','${p.nome}')">  
                                                    Ativar
                                                </button>                                                
                                            </c:otherwise>
                                        </c:choose>
                                        <a href="gerenciarMenuPerfil?acao=vincular&idPerfil=${p.idPerfil}"
                                           class="btn btn-secondary btn-sm"
                                           role="button"> Vincular</a>
                                    </td>
                                </tr>   
                            </c:forEach>
                        </tbody>                        
                    </table>                    
                    
                </div> <!-- fim da div Class CONTAINER -->                               
            </div> <!-- fim da div Id CONTEUDO -->      
        </div> <!-- fim da div Id CONTAINER -->    
        
            <!-- 1º --><script src="js/jquery-3.6.0.min.js"></script>
            <!-- 2º --><script src="datatables/jquery.dataTables.min.js"></script>
            <!-- 3º --><script src="bootstrap/bootstrap.min.js"></script> 
            <!-- 4º --><script src="datatables/dataTables.bootstrap4.min.js"></script>  
   
        <script>
            $(document).ready(function () {
                $('#listarPerfis').dataTable({
                    "bJQueryUI": true,

                    "lengthMenu": [[5, 10, 20, 25, -1], [5, 10, 20, 25, "Todos"]],
                    "oLanguage": {
                        "sProcessing": "Processando",
                        "sLengthMenu": "Mostrar _MENU_ registros",
                        "sZeroRecords": "Não foram encontrados resultados",
                        "sInfo": "Mostrando _START_ até _END_ de _TOTAL_ registros",
                        "sInfoEmpty": "Mostrado de 0 até 0 de 0 registros",
                        "sInfoFiltered": "",
                        "sInfoPostFix": "",
                        "sSearch": "Pesquisar",
                        "sUrl": "",
                        "oPaginate": {
                            "sFirst": "Primeiro",
                            "sPrevious": "Anterior",
                            "sNext": "Próximo",
                            "sLast": "Último"
                        }
                    }
                });
            });
        </script>      
    </body>
</html>
