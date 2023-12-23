<%-- 
    Document   : desvincular_menu_do_perfil
    Created on : 2 de nov. de 2023, 03:10:06
    Author     : Heraldo
--%>

<script type="text/javascript">
    function confirmDesvincular(idMenu, nome, idPerfil) {
        if(confirm("Deseja desvincular o menu"+ nome+"?")){
            location.href="gerenciarMenuPerfil?acao=desvincular&idMenu="+idMenu+"&idPerfil="+idPerfil;            
        }
    } 
</script>