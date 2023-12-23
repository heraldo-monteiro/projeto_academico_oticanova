<%-- 
    Document   : ativar_desativar_usuario
    Created on : 31 de out. de 2023, 19:39:01
    Author     : Heraldo
--%>

<script type="text/javascript">
    function confirmDesativar(id, nome) {
        if(confirm('Deseja desativar esso o usuário (' +nome+ ')?')){
            location.href="gerenciarUsuario?acao=desativar&idUsuario="+id;
        }    
    }
    
    function confirmAtivar(id, nome){
        if(confirm('Deseja ativar o usuário (' +nome+ ')?')){
            location.href="gerenciarUsuario?acao=ativar&idUsuario="+id;            
        }        
    }    
</script>