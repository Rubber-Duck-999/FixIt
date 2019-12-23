package team.FixIt;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main 
{
    private static Model myModel;
    private static View myView;
    private static Controller myController;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void startUI()
    {
        int start_value = 0;
        myModel = new Model();
        myView = new View();

        myController = new Controller(myModel, myView);
        //myController.initmodel(start_value);
        myView.addController(myController);
    }

    public static void main(String[] args) 
    {
        LOGGER.setLevel(Level.INFO);
        LOGGER.info("Starting application");
        startUI();
    }
}