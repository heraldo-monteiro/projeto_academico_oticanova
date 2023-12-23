<%-- 
    Document   : cadastrarOrdemServico
    Created on : 9 de nov. de 2023, 16:22:16
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
                        <form class="formOS" action="gerenciarOrdemServico" method="POST">    
                            <input type="hidden" name="idOrdemServico" value="${ordemServico.idOrdemServico}">                           
                            <h3 class="text-center mt-3"> Cadastrar Ordem de Serviço</h3> <hr>                         
                        
                            <div class="form-group row offset-md-1 mt-3">
                                <label class="col-md-2"> Atendente</label>
                                <div class="col-md-5">
                                    <select class="form-control" name="idUsuario">
                                        <option value=""> Selecilone o atendente</option>
                                        <jsp:useBean class="dao.UsuarioDAO" id="udao"/>
                                        <c:forEach items="${udao.listarUsuario}" var="u" >
                                            <option value="${u.idUsuario}"                                              
                                                    <c:if test="${u.idUsuario == ordemServico.usuario.idUsuario}">
                                                        selected="" 
                                                    </c:if>>${u.nome} 
                                            </option>                                   
                                        </c:forEach>
                                    </select> 
                                </div>                                
                            </div> 

                            <div class="form-group row offset-md-1 mt-1">
                                <label class="col-md-2"> Cliente</label>                                
                                
                                <div class="col-md-5">
                                    <select class="form-control" name="idCliente">
                                        <option value=""> Selecilone o cliente</option>
                                        <jsp:useBean class="dao.ClienteDAO" id="cdao"/>
                                        <c:forEach items="${cdao.listaCliente}" var="c" >                                           
                                                <option value="${c.idCliente}"
                                                    <c:if test="${c.idCliente == ordemServico.cliente.idCliente}">
                                                        selected=""
                                                    </c:if>>${c.nome} 
                                            </option>                                   
                                        </c:forEach>
                                    </select>  
                                </div>                                
                            </div>     

                            <div class="form-group row offset-md-1 mt-1">
                                <label class="col-md-2"> Lente</label>
                                <div class="col-md-5">
                                    <select class="form-control" name="idLente">
                                        <option value=""> Selecilone a lente</option>
                                        <jsp:useBean class="dao.LenteDAO" id="ldao"/>
                                        <c:forEach items="${ldao.listarLente}" var="l" >                                            
                                                <option value="${l.idLente}"
                                                    <c:if test="${l.idLente == ordemServico.lente.idLente}">
                                                        selected="" 
                                                    </c:if>>${l.nome} 
                                            </option>                                   
                                        </c:forEach>
                                    </select> 
                                </div>                                
                            </div>  

                            <div class="form-group row offset-md-1 mt-1">
                                <label class="col-md-2"> Laboratório</label>
                                <div class="col-md-5">
                                    <select class="form-control" name="idLaboratorio">
                                        <option value=""> Selecilone o laboratório</option>
                                        <jsp:useBean class="dao.LaboratorioDAO" id="labdao"/>
                                        <c:forEach items="${labdao.listarLaboratorio}" var="lab" >                                           
                                                <option value="${lab.idLaboratorio}"
                                                    <c:if test="${lab.idLaboratorio == ordemServico.laboratorio.idLaboratorio}">
                                                        selected="" 
                                                    </c:if>>${lab.nome} 
                                            </option>                                   
                                        </c:forEach>
                                    </select> 
                                </div>                                
                            </div>

                            <div class="form-group row offset-md-1 mt-1">
                                <label class="col-md-2"> Solicitaçao</label>
                                <div class="col-md-3">
                                    <input type="Date" 
                                        name="dataSolicitacao"
                                        value="${ordemServico.dataSolicitacao}"
                                        id="dataSolicitacao"
                                        class="form-control">                                    
                                </div>                                
                            </div>

                            <div class="form-group row offset-md-1 mt-1">
                                <label class="col-md-2"> Vencimento</label>
                                <div class="col-md-3">
                                    <input type="Date" 
                                        name="dataVencimento"
                                        value="${ordemServico.dataVencimento}"
                                        id="dataVencimento"
                                        class="form-control">                                    
                                </div>                                
                            </div> 
                                        
                            <div class="form-group row offset-md-1 mt-1">
                                <label class="col-md-2"> Status </label>
                                <div class="col-md-3">
                                    <select class="form-control" 
                                            name="status">                                     
                                        <option value="1"
                                            <c:if test="${ordemServico.status == 1}">
                                                selected=""                                           
                                            </c:if>>
                                                Encaminhar
                                        </option>
                                       <option value="1"
                                            <c:if test="${ordemServico.status == 0}">
                                                selected=""                                           
                                            </c:if>>
                                                Indeferir
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div  class="rc d-md-flex justify-content-md-end mt-5">
                                <button class="btn btn-primary btn-md mr-3"> Registar</button>
                                <a href="gerenciarOrdemServico?acao=listar"
                                   class="btn  btn-danger btn-md"> Cancelar</a>
                            </div>
                        </form>
                </div> <!-- fim da div Class CONTAINER-->  
            </div> <!-- fim da div CONTEÚDO-->    
        </div> <!-- fim da div CONTAINER-->    
        
        <script src="js/jquery-3.6.0.min.js"></script>          
        <script src="bootstrap/bootstrap.min.js"></script> 
            
    </body>
</html>