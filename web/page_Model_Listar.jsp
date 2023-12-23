

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
        <link rel="stylesheet" href="datatables/dataTables.bootstrap4.min.css" type="text/css">
        <link rel="stylesheet" href="datatables/jquery.dataTables.min.css" type="text/css">
        <title>Sistema Interno</title>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page="template/banner.jsp"></jsp:include>
            </div> <!-- fim da div Id HEADER-->
            <div id="menu">
                <jsp:include page="template/menu.jsp"></jsp:include>
            </div><!-- fim da div Id MENU-->
            <div id="conteudo" class="">
                <div class="container">
                    <h3 class=""> </h3>
                    <a href=""
                       class=""
                       role="button"> </a>    
                    
                    <table>                       
                        <thead>
                            <tr>
                                <th> </th>
                                <th> </th>
                            </tr>                            
                        </thead> 
                        <tbody>
                        <c:forEach items="" var="">
                                <tr>
                                    <td> &nbsp;</td>
                                    <td> &nbsp;</td>
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
                    $('#listarPerfis').dataTable({
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
