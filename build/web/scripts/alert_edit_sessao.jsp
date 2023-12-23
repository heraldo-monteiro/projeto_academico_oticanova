

<%    
    HttpSession sessao = request.getSession();
    if (sessao.getAttribute("msg") != null) {
        String msg = (String) sessao.getAttribute("msg");
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

        if(sessao.getAttribute("ulogado") == null){
            response.sendRedirect("index.jsp");

        }
    sessao.removeAttribute("msg");    
    }    
%>     


