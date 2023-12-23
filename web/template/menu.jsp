

<%@page contentType="text/html; utf-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="model.Usuario, controller.GerenciarLogin"%>

<% 
    Usuario ulogado = GerenciarLogin.verificarAcesso(request, response);    
    request.setAttribute("ulogado", ulogado);
%>

<c:choose>
    <c:when test="${ulogado != null}">
        <div class="d-md-flex justify-content-sm-between mt-2">
            <h6 class="mt-1"> Bem-vindo, ${ulogado.nome}<h6>
            <a href="gerenciarLogin?"
               class="btn btn-primary btn-sm" role="button"> 
               Sair   
            </a>               
        </div>
    </c:when>
    <c:otherwise>
        <div class="d-md-flex justify-content-sm-end mt-2">
            <a href="login.jsp" 
               class="btn btn-primary btn-sm mr-2"
               role="button"> Login
            </a>
        </div>
    </c:otherwise>
</c:choose>

<header>       
    <nav class="navbar navbar-expand-lg navbar-light">       
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-lg-auto">     
                <c:if test="${ulogado != null && ulogado.perfil != null}">
                    <c:forEach var="menu" items="${ulogado.perfil.menus}">
                        <c:if test="${menu.exibir == 1}">
                            <li class="nav-item">
                                <a class="nav-link" href="${menu.link}">${menu.nome}</a>
                            </li>
                        </c:if>
                    </c:forEach>
                </c:if>               
            </ul>     
        </div>
    </nav>
</header>
