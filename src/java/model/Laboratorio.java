
package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Laboratorio {
    private int idLaboratorio;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private int status;    
    
}
