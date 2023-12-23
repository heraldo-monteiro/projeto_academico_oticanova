<%-- 
    Document   : script_ativar_desativar_menu
    Created on : 29 de out. de 2023, 15:40:26
    Author     : Heraldo
--%>

   <script type="text/javascript">
        function confirmDesativar(id, nome){
            if(confirm('Deseja desativar o menu (' +nome+ ')?')){
                location.href="gerenciarMenu?acao=desativar&idMenu="+id;
            } 
        }                                    
        function confirmAtivar(id, nome){
            if(confirm('Deseja ativar o menu (' +nome+ ')?')){
            location.href="gerenciarMenu?acao=ativar&idMenu="+id;                                            
            }                                        
        }                                    
    </script>   