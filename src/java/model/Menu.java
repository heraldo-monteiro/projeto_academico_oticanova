
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

public class Menu {
    private int idMenu;
    private String nome;
    private String link;
    private int exibir;
    private int status;
    
    
}
