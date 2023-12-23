
package date;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class Date_Format {  

    public static Date dataAtual() {        
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");        
        Date dataAtual = new Date();        
        System.out.println(df.format(dataAtual));        
        return  dataAtual;   
        
    }
    
    
    
    


}