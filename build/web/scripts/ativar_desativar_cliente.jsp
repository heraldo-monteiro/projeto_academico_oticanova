<%-- 
    Document   : altivar_desativar_cliente
    Created on : 6 de nov. de 2023, 21:29:58
    Author     : Heraldo
--%>

<script type="text/javascript">
        function confirmDesativar(id, nome){
            if(confirm('Deseja desativar  o cliente ' +
                    nome + '?')){
                location.href="gerenciarCliente?acao=desativar&idCliente="+id;
            } 
        }                                    
        function confirmAtivar(id, nome){
            if(confirm('Deseja Ativar o cliente ' +
                nome+ '?')){
            location.href="gerenciarCliente?acao=ativar&idCliente="+id;                                            
            }                                        
        }                                    
    </script>   