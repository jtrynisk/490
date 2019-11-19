package Logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.*;

public class MyLogger{

    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public MyLogger()
    {
        try {
            SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");
            FileHandler fileHandler = new FileHandler("/Users/jondntryniski/490/src/main/resources/Log_"
                                                        + format.format(Calendar.getInstance().getTime()) + ".txt");
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        }catch(SecurityException | IOException e)
        {
            e.printStackTrace();
        }
    }

   public void makeLog(String message)
   {
       logger.log(Level.INFO, message);
   }

}
