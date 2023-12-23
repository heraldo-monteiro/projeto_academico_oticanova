
package model;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Perfil {
    private int idPerfil;
    private String nome;
    private Date dataCadastro;
    private int status;
    private ArrayList<Menu> menus;
    private ArrayList<Menu> naoMenus;
        
}
