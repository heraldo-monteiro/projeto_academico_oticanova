<%-- 
    Document   : alert_edit_request
    Created on : 8 de out. de 2023, 01:06:05
    Author     : Heraldo
--%>
<%
    HttpSession sessao = request.getSession();
        if(sessao.getAttribute("ulogado") == null){
            response.sendRedirect("index.jsp");
        }
%>

<%
    if (request.getAttribute("msg") != null) {
        String msg = (String)request.getAttribute("msg");
        if (msg != null) {        
%>
            <div class="alert alert-danger" role="alert">
                <%= msg %>
                <button type="button" 
                    class="close" 
                    data-dismiss="alert"
                    aria-label="Close">&times;</spam>
                </button>
            </div>                
<%   
        }
        request.removeAttribute("msg");          
     
    }    
        
%>     

