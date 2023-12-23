<%-- 
    Document   : deletar_ordem_servico
    Created on : 8 de nov. de 2023, 18:19:03
    Author     : Heraldo
--%>

<script type="text/javascript">
    function confirmExcluir(id){
     if(confirm('Deseja realmente excluir esssa ordem de serviço?')){
        location.href="gerenciarOrdemServico?acao=excluir&idOrdemServico="+id;
     }   
    }   
</script>
