<%-- 
    Document   : listarOrdemServicos
    Created on : 8 de nov. de 2023, 17:24:51
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
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
                    <h3 class="text-center mt-3">Lista das Ordem de Seviços</h3>
                    <a href="cadastrarOrdemServico.jsp"
                       class="btn btn-primary btn-md mb-3"
                       role="button"> Cadastrar Ordem de Serviço</a>    
                    
                    <table id="tableOrdemServico" 
                           class="table table-hover
                           table-striped">                       
                        <thead>
                            <tr>
                                <th> Codigo</th>
                                <th> Atendente</th>
                                <th> Cliente</th>
                                <th> Lente</th>
                                <th> Laboratório</th>
                                <th> Solicitação</th>
                                <th> Vencimento</th>
                                <th> Entrega</th>
                                <th> Status</th>                            
                            </tr>                            
                        </thead> 
                        <tbody>
                            <c:forEach items="${ordemServicos}" var="os">
                                <tr>
                                    <td> ${os.idOrdemServico}</td>
                                    <td> ${os.usuario.nome}</td>
                                    <td> ${os.cliente.nome}</td>
                                    <td> ${os.lente.nome}</td>
                                    <td> ${os.laboratorio.nome}</td>      
                                    
                                    <td> 
                                        <fmt:formatDate pattern="dd/MM/yyyy"
                                            value="${os.dataSolicitacao}"/>
                                    </td>  
                                    
                                    <td> 
                                        <fmt:formatDate pattern="dd/MM/yyyy"
                                            value="${os.dataVencimento}"/>
                                    </td>   
                                    
                                    <td>
                                        <c:choose>
                                            <c:when test="${empty os.dataEntrega}">
                                                Sem entrada
                                            </c:when>
                                            <c:otherwise>
                                                <fmt:formatDate pattern="dd/MM/yyyy" value="${os.dataEntrega}"></fmt:formatDate>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>  
                                    
                                    <td>                                
                                         <c:choose>
                                            <c:when test="${os.dataEntrega == null}">
                                                <a href="gerenciarOrdemServico?acao=atualizarEntrega&idOrdemServico=${os.idOrdemServico}"                                             
                                                class="btn btn-warning btn-sm"
                                                role="button"> Confirmar</a>  
                                            </c:when>
                                            <c:otherwise>  
                                                <button class="btn btn-success"> 
                                                    Recebido
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
            $(document).ready(function () {
                $('#tableOrdemServico').dataTable({                    
                    "bJQueryUI": true,
                    "lengthMenu": [[5, 10, 20, 25, -1], [5, 10, 20, 25, "Todos"]],
                    "oLanguage": {
                        "sProcessing": "Processando",
                        "sLengthMenu": "Mostrar _MENU_ registros",
                        "sZeroRecords": "Não foram encontrados resultados",
                        "sInfo": "Mostrando _START_ até _END_ de _TOTAL_ registros",
                        "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
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
