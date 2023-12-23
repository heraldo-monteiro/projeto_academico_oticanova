<%-- 
    Document   : altivar_desativar_laboratorio
    Created on : 6 de nov. de 2023, 21:25:21
    Author     : Heraldo
--%>


    <script type="text/javascript">
        function confirmDesativar(id){
            if(confirm('Deseja desativar  o Laboratorio!')){
                location.href="gerenciarLaboratorio?acao=desativar&idLaboratorio="+id;
            } 
        }                                    
        function confirmAtivar(id){
            if(confirm('Deseja Ativar o Laboratorio!')){
            location.href="gerenciarLaboratorio?acao=ativar&idLaboratorio="+id;                                            
            }                                        
        }                                    
    </script>     