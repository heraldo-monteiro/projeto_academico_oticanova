<%-- 
    Document   : altivar_desativar_perfil
    Created on : 25 de out. de 2023, 16:38:55
    Author     : Heraldo
--%>

    <script type="text/javascript">
        function confirmDesativar(id, nome){
            if(confirm('Deseja desativar  o Perfil ' +
                    nome + '?')){
                location.href="gerenciarPerfil?acao=desativar&idPerfil="+id;
            } 
        }                                    
        function confirmAtivar(id, nome){
            if(confirm('Deseja Ativar o perfil ' +
                nome+ '?')){
            location.href="gerenciarPerfil?acao=ativar&idPerfil="+id;                                            
            }                                        
        }                                    
    </script>     