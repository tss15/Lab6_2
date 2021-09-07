package commands.specificCommands;

import commands.Command;
import commands.seriallizedCommands.SerializedCombinedCommand;
import data.LabWork;
import utilities.Logging;
import utilities.Receiver;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;

public class Update extends Command implements Serializable {
    private static final long serialVersionUID = 1502L;

    @Override
    public void execute(Object o, DatagramSocket datagramSocket, DatagramPacket datagramPacket) throws IOException {
        SerializedCombinedCommand serializedCombinedCommand = (SerializedCombinedCommand) o;
        String arg = serializedCombinedCommand.getArg();
        Object obj = serializedCombinedCommand.getObject();
        Receiver receiver = new Receiver(datagramSocket, datagramPacket);
        long id = Long.parseLong(arg);
        Logging.log(Level.INFO, "Server is executing UpdateCommand....");
        receiver.update(id, (LabWork) obj);
    }
}
