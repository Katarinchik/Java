import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Main class program
 */
public class Main {
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        PropertyConfigurator.configure("C:\\Eclips\\work_space\\lab10\\src\\main\\java\\log4j.properties");
        log.info("Start program");
        Window window = new Window();
        window.createWindow();
        log.info("End program");
    }
}
