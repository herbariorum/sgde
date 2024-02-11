package util;


import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Util {
    private final String pPassword = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

    Pattern regex;

    public boolean isValidPwd(String pwd) {
        this.regex = Pattern.compile(pPassword);
        Matcher m = this.regex.matcher(pwd);
        return m.matches();
    }

    public LocalDate dateToLocalDate(String value){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld = null;
        try {
             ld = LocalDate.parse(value, formatter);
        } catch (Exception e) {
            // Trate a exceção (por exemplo, imprima uma mensagem de erro ou faça algo apropriado para sua aplicação)
        }
        return ld;
    }

    public String formatDate(LocalDate valor){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTimeFormatter.format(valor);
    }
    public LocalDate formatDateToUs(String valor){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(valor, dateTimeFormatter);
        return localDate;
    }

    public MaskFormatter createFormatter(String s){
        MaskFormatter formatter = null;
        try{
         formatter = new MaskFormatter(s);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }

    public boolean isDouble(String str) {
        try {
            // check if it can be parsed as any double
            double x = Double.parseDouble(str);
            // check if the double can be converted without loss to an int
            if (x == (int) x)
                // if yes, this is an int, thus return false
                return false;
            // otherwise, this cannot be converted to an int (e.g. "1.2")
            return true;
            // short version: return x != (int) x;
        }
        catch(NumberFormatException e) {
            return false;
        }

    }
}
