
package date;
import java.text.*;
import java.time. *;
       
import java.util.Date;
import java.util.Calendar;



public class FormatDate {
    public static Date dataAtual;
    private Date dataVencimento;
    
    String message;
    
    
    
    
    public String vencimento(Date data){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendarioAtual = calendario(dataAtual, true);
        Calendar calendarioVencimento = calendario(dataVencimento, true);
        
        String dateOne = df.format(data);        
        
        try {
           dataVencimento = df.parse(dateOne);
        } catch (ParseException erro) {
            message = "Erro !:"+ erro.getMessage();
            erro.printStackTrace();
        }
            
        return dateOne;
    }
   
    private static Calendar calendario(Date date, boolean setTimeToZero) {
       Calendar calendario = Calendar.getInstance();
       calendario.setTime(date);
       if (setTimeToZero) {
           calendario.set(Calendar.HOUR_OF_DAY, 0);
           calendario.set(Calendar.MINUTE, 0);
           calendario.set(Calendar.SECOND, 0);
           calendario.set(Calendar.MILLISECOND, 0);
       }
       return calendario;
    }
      
    
}
