import com.formdev.flatlaf.FlatLightLaf;
import Database.Dao.ExceptionDAO;

public class sgde {
    public static void main(String[] args) throws ExceptionDAO {
        FlatLightLaf.setup();

        new MenuForm().start();
    }
}
