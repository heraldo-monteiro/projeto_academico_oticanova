
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class OrdemServico {
    private int idOrdemServico;
    private Date dataSolicitacao;
    private Date dataVencimento;
    private Date dataEntrega;
    private int status;
    private Usuario usuario;
    private Cliente cliente;
    private Lente lente;
    private Laboratorio laboratorio;
    
    
    
   
}
