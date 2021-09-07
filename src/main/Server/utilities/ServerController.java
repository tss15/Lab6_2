package utilities;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.text.ParseException;
import java.util.logging.Level;

public class ServerController {

    private final int port;

    public ServerController(Integer p) {
        this.port = Integer.parseInt(p);
    }


    public void run() {
        try {
            if (port < 0) {
                Logging.log(Level.INFO, "Invalid port! Port must be non-negative!");
                System.exit(-1);
            }
            DatagramSocket serverSocket = new DatagramSocket(port);

            while (true) {
                byte[] receivedBuffer = new byte[4096];
                DatagramPacket receivedPacket = new DatagramPacket(receivedBuffer, receivedBuffer.length);
                Logging.log(Level.INFO, "Server has started listening on port " + port);
                serverSocket.receive(receivedPacket);
                Logging.log(Level.INFO, "Server received request from Client!");
                ByteArrayInputStream receivedByteArray = new ByteArrayInputStream(receivedPacket.getData());
                ObjectInputStream in = new ObjectInputStream(receivedByteArray);
                try {
                    Object object = in.readObject();
/*                SerializedSimplyCommand receivedCommand = (SerializedSimplyCommand) object;
                System.out.println(receivedCommand.getID());*/
                    CommandDecoder commandDecoder = new CommandDecoder(serverSocket, receivedPacket);
                    commandDecoder.decode(object);
                } catch (ParseException | ClassNotFoundException e) {
                    e.printStackTrace();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
