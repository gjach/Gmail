import java.text.SimpleDateFormat;
import java.util.Date;
public class Config {

    public static String username = "testname2137@gmail.com";
    public static String password = "Pa$$word123!.";
    public static String recipients = "testname2137@gmail.com";
    public static String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
    public static  String subject = "Test message sent on " + timeStamp;
    public static String message = "This is the test message sent on " + timeStamp;
}
