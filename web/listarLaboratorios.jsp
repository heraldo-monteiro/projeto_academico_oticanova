
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
    <head>        
        <meta charset="UTF-8">  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        <meta http-equiv="X-UA-Compatible" content="IE-edge, chrome=1">         
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
                <jsp:include page="template/banner.jsp"></jsp:include>
            
            </div> <!-- fim da div Id HEADER-->
            <div id="menu">
                <jsp:include page="template/menu.jsp"></jsp:include>
            
                </div><!-- fim da div Id MENU-->
            <div id="conteudo" class="bg-background">
                <div class="container">
                    <h3 class="text-center mt-5">Lista do Laboratórios</h3>
                    <a href="cadastrarLaboratorio.jsp"
                       class="btn btn-primary btn-md mb-3 "
                       role="button">Novo Cadastro </a>    
                    
                    <table id="tableLaboratorio"
                           class="table
                           table-hover
                           table-striped">                       
                        <thead>
                            <tr>
                                <th> Código</th>
                                <th> Nome</th>
                                <th> Edenreço</th>
                                <th> Telefone</th>
                                <th> E-mail</th>
                                <th> Status</th>
                                <th> Ação</th>
                            </tr>                            
                        </thead> 
                        <tbody>  
                            <c:forEach items="${laboratorios}" var="lab">
                                <tr>
                                    <td> ${lab.idLaboratorio}</td>
                                    <td> ${lab.nome}</td>
                                    <td> ${lab.endereco}</td>
                                    <td> ${lab.telefone}</td>
                                    <td> ${email}</td>
                                    <td> 
                                        <c:choose>
                                            <c:when test="${lab.status == 1}">
                                                Ativo
                                            </c:when>
                                            <c:otherwise>
                                                Desativado
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    
                                    <td>                                        
                                        <jsp:include page="scripts/ativar_desativar_laboratorio.jsp"></jsp:include>                                   
                                        <a href="gerenciarLaboratorio?acao=alterar&idLaboratorio=${lab.idLaboratorio}"
                                           class="btn btn-warning btn-sm"
                                           role="button"> Alterar</a>
                                           
                                        <c:choose>
                                            <c:when test="${lab.status == 1}">
                                                <button class="btn btn-danger btn-sm"
                                                        onclick="confirmDesativar('${lab.idLaboratorio}')">
                                                    Desativar
                                                </button>                           
                                            </c:when>
                                            <c:otherwise>
                                                <button class="btn btn-success btn-sm"
                                                        onclick="confirmAtivar('${lab.idLaboratorio}')">
                                                    Ativar
                                                </button>
                                            </c:otherwise>
                                        </c:choose>                                        
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
                $(document).ready( function () {
                    $('#tableLaboratorio').dataTable({
                        "bJQueryUI":true,
                        
                        "lenghtMenu":[[5, 10 ,20, 25, -1], [5,10,20,25,"Todos"]],
                        "oLanguage":{
                            "sProcessing": "Processando",
                            "sLanghtMenu": "Mostrar_MENU_registros",
                            "sZeroRecords": "Não foram encontrados resultados",
                            "sInfo": "Mostrando _REGISTROS_ _END_ de _TOTAL_ registros",
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
