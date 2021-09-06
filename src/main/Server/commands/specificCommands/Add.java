package commands.specificCommands;

import commands.Command;
import utilities.Logging;
import utilities.Receiver;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;

public class Add extends Command implements Serializable {
    private static final long serialVersionUID = 1234567L;


    @Override
    public void execute(Object o, DatagramSocket datagramSocket, DatagramPacket datagramPacket) throws IOException {
        Logging.log(Level.INFO, "Server is executing AddCommand....");
        Receiver receiver = new Receiver(datagramSocket, datagramPacket);
        receiver.add(o);
    }
}
