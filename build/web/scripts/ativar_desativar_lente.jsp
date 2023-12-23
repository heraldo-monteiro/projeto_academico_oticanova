<%-- 
    Document   : altivar_desativar_lente
    Created on : 6 de nov. de 2023, 21:28:08
    Author     : Heraldo
--%>

 <script type="text/javascript">
        function confirmDesativar(id){
            if(confirm('Deseja desativar  o Lente')){
                location.href="gerenciarLente?acao=desativar&idLente="+id;
            } 
        }                                    
        function confirmAtivar(id){
            if(confirm('Deseja Ativar essa  lente?')){
            location.href="gerenciarLente?acao=ativar&idLente="+id;                                            
            }                                        
        }                                    
    </script> 