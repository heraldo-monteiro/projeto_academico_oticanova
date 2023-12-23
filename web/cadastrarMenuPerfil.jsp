<%-- 
    Document   : cadastrarMenuPerfil
    Created on : 1 de nov. de 2023, 19:59:18
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
        <link rel="stylesheet" href="css/style.css" type="text/css">                
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
            
            <div id="conteudo" class="bg-background">    
                <div class="container mt-5">         
                    
                    <jsp:include page="scripts/alert_edit_sessao.jsp"></jsp:include>  
                    <form action="gerenciarMenuPerfil" method="POST">                        
                        <input type="hidden" name="idPerfil" value="${perfilv.idPerfil}">
                        <h3 class="text-center mt-6"> Cadastro de Menu e Perfil</h3>                    
                        
                        <div class="form-group row offset-md-3 mt-5">
                            <label class="col-md-2">Nome</label>
                            <div class="col-md-5">
                                <input type="text" name="nome"
                                       class="form-control" readonly
                                       value="${perfilv.nome}">
                            </div>
                        </div>
                            
                        <div class="form-group row offset-md-3 mt-3">
                            <label class="col-md-2">Menu</label>
                            <div class="col-md-5">
                                <select class="form-control" name="idMenu">
                                    <option value="">Menus não vinculados</option>                                
                                    <c:forEach var="m" items="${perfilv.naoMenus}">  
                                        <option  value="${m.idMenu}"> ${m.nome}</option>                                                                                
                                    </c:forEach>
                                </select> 
                            
                            </div>

                        </div>
                        <div class="d-md-flex justify-content-md-end mr-3">
                            <button class="btn btn-primary btn-sm mr-4"> Vincular</button>              
                            <a href="gerenciarPerfil?acao=listar"
                               class="btn btn-danger btn-sm"
                               role="button"> Listar Perfis</a>
                        </div>                        
                    </form>    
                                   
                    <div class="container mt-5">
                        <table class="table table-hover table-striped"
                               id="tableMenuPerfil">
                            <thead>
                                <tr>
                                    <th> Código</th>
                                    <th> Nome</th>
                                    <th> Link</th>
                                    <th> Exibir</th>
                                    <th> Ação</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${perfilv.menus}" var="m" >
                                <tr>
                                    <td>${m.idMenu}</td>
                                    <td>${m.nome}</td>
                                    <td>${m.link}</td>
                                    <td>
                                        <c:if test="${m.exibir == 1}">
                                            Sim
                                        </c:if>
                                        <c:if test="${m.exibir == 0}">
                                            Não
                                        </c:if> 
                                    </td>
                                    
                                    <td>
                                        <script type="text/javascript">
                                            function confirmDesvincular(idMenu, nome, idPerfil){
                                                if(confirm("Deseja desvincular o menu " + nome + "?")){
                                                    location.href="gerenciarMenuPerfil?acao=desvincular&idMenu="+idMenu+"&idPerfil="+idPerfil;
                                                }
                                            }
                                        </script>
                                        <button class="btn btn-danger btn-sm"
                                            onclick="confirmDesvincular('${m.idMenu}',
                                                                        '${m.nome}',
                                                                        '${perfilv.idPerfil}')">Desvincular                                            
                                        </button>
                                    </td>                                    
                                </tr>
                                </c:forEach>
                            </tbody>                            
                        </table>              
                    </div>           
                    
                </div> <!-- fim da div Class CONTAINER-->  
            </div><!-- fim da div Id CONTEUDO-->          
        </div><!-- fim da div Id CONTAINER-->   
        
            <!-- 1º --><script src="js/jquery-3.6.0.min.js"></script>          
            <!-- 2º --><script src="datatables/jquery.dataTables.min.js"></script>  
            <!-- 3º --><script src="bootstrap/bootstrap.min.js"></script>             
            <!-- 4º --><script src="datatables/dataTables.bootstrap4.min.js"></script>  
                       
           
            
            <script>
                                 
                $(document).ready( function () {
                    $('#tableMenuPerfil').dataTable({
                        "bJQueryUI":true,
                        "lengthMenu":[[5, 10 ,20, 25, -1], [5,10,20,25,"Todos"]],
                        "oLanguage":{
                            "sProcessing": "Processando",
                            "sLengthMenu": "Mostrar _MENU_ registros",
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
