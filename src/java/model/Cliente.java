
package model;

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

public class Cliente {
    private int idCliente;
    private String nome;
    private String cpf;
    private String telefone;     
    private Date dataNasc;  
    private int idade; 
    private int status;


    
}
