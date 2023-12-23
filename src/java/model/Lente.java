
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

public class Lente {
    private int idLente;
    private String nome; 
    private String modelo;
    private String fabricante;
    private Double preco;
    private int status;
           
}
