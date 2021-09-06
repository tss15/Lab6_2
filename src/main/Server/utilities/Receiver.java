package utilities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;
import java.util.logging.Level;
import data.*;

public class Receiver {

    private final DatagramSocket datagramSocket;
    private final DatagramPacket datagramPacket;

    public Receiver(DatagramSocket datagramSocket, DatagramPacket datagramPacket){
        this.datagramSocket = datagramSocket;
        this.datagramPacket = datagramPacket;
    }
    public void info() throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.info());
        Logging.log(Level.INFO, "Server executed InfoCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        //System.out.println("Server: byteStream" + Arrays.toString(b));
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }

    public void show() throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.show());
        Logging.log(Level.INFO, "Server executed ShowCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);

    }

    public void clear() throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.clear());
        Logging.log(Level.INFO, "Server executed ClearCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);

    }

    public void add(Object o) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.add(o));
        Logging.log(Level.INFO, "Server executed AddCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }


    public void countByDifficulty() throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.countByDifficulty());
        Logging.log(Level.INFO, "Server executed CountByDifficultyCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }

    public void printField() throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.printField());
        Logging.log(Level.INFO, "Server executed PrintFieldCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }

    public void removeByKey(long id) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.removeByKey(id));
        Logging.log(Level.INFO, "Server executed RemoveByKeyCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }

    public void replaceIfGreater(Object o) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.replaceIfGreater((LabWork) o));
        Logging.log(Level.INFO, "Server executed ReplaceIfGreaterCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }

    public void replaceIfLower(Object o) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.replaceIfLower((LabWork) o));
        Logging.log(Level.INFO, "Server executed ReplaceIfCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }

    public void update(long id, LabWork o) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.update(id, o));
        Logging.log(Level.INFO, "Server executed UpdateCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }
}
