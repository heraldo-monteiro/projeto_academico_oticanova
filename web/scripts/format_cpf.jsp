
<script type="text/javascript">
    function format_cpf(cpf) {
        let value = cpf.value.replace(/\D/g, '');
        if (value.length < 15) {
            let g = value.match(/(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,2})/);
            cpf.value = 
                (!g[2] ? g[1] : g[1] + '.') +
                (g[2] ? g[2] : '') +
                (g[3] ? '.' : '') + 
                (g[3] ? g[3] : '') +
                (g[4] ? '-' + g[4] : '');   
        }   
    }
</script>
