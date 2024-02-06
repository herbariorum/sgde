import com.formdev.flatlaf.FlatLightLaf;
import modulo.servidores.Dao.ExceptionDAO;
import modulo.servidores.View.EmployeeView;

public class sgde {
    public static void main(String[] args) throws ExceptionDAO {
        FlatLightLaf.setup();

        new EmployeeView("Manutenção de Servidores").start();
    }
}
