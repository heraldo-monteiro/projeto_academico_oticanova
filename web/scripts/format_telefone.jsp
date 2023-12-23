  
<script type="text/javascript">
    function format_telefone(fone) {
        let value = fone.value.replace(/\D/g, '');
        if (value.length < 15) {
            let g = value.match(/(\d{0,2})(\d{0,1})(\d{0,4})(\d{0,4})/);
            fone.value = 
                (!g[2] ? ''+ g[1] : '(' + g[1] + ')') +
                (g[2] ? g[2] : '') +
                (g[3] ? '' + g[3] : '') +
                (g[4] ? '-' + g[4] : '');
        }
    }
</script>