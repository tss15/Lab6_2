import utilities.CollectionManager;
import utilities.Logging;
import utilities.ServerController;

import java.util.logging.Level;


public class Server {

    public static void main(String[] args) {
        try {
            String fileName = args[0];
            Integer port = Integer.valueOf(args[1]);
            Logging.log(Level.WARNING, "Port must be int number! Please notice about it!");
            CollectionManager.setFileName(fileName);
            CollectionManager.readInputFromJsonFile();
            Runtime.getRuntime().addShutdownHook(new Thread(CollectionManager::save));
            ServerController serverController = new ServerController(port);
            serverController.run();
        } catch(ArrayIndexOutOfBoundsException e){
            Logging.log(Level.INFO, "Invalid fileName and port");
        }
    }
}