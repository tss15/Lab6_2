package utilities;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.text.ParseException;
import java.util.logging.Level;

//public class ServerController {
//
//    private DatagramSocket socket;
//    private boolean running;
//    private DatagramSocket port;
//    private byte[] buf = new byte[256];
    public class ServerController {

        private final int port;

        public ServerController(String p) {
            this.port = Integer.parseInt(String.valueOf(p));
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

//    public ServerController(int port) {
//        try {
//            this.port = new DatagramSocket(4445);
//        } catch (SocketException e) {
//            e.printStackTrace();
//        }
//
//     catch(
//    IOException e)
//
//    {
//        e.printStackTrace();
//    }
//
//}

//        while (running) {
//            DatagramPacket packet = new DatagramPacket(buf, buf.length);
//            socket.receive(packet);
//
//            InetAddress address = packet.getAddress();
//            int port = packet.getPort();
//            packet = new DatagramPacket(buf, buf.length, address, port);
//            String received = new String(packet.getData(), 0, packet.getLength());
//
//            if (received.equals("end")){
//                running = false;
//                continue;
//            }
//            socket.send(packet);
//        } socket.close();
//    }

//    private final int MYPORT;
//
//    public ServerController(int p) {
//        this.MYPORT = Integer.parseInt(String.valueOf(p));
//    }
//
//
//    public void run() {
//        try {
//            if (MYPORT < 0) {
//                Logging.log(Level.INFO, "Invalid port! Port must be non-negative!");
//                System.exit(-1);
//            }
//            DatagramSocket serverSocket = new DatagramSocket(MYPORT);
//
//            while (true) {
//                byte[] receivedBuffer = new byte[4096];
//                DatagramPacket receivedPacket = new DatagramPacket(receivedBuffer, receivedBuffer.length);
//                Logging.log(Level.INFO, "Server has started listening on port " + MYPORT);
//                serverSocket.receive(receivedPacket);
//                Logging.log(Level.INFO, "Server received request from Client!");
//                ByteArrayInputStream receivedByteArray = new ByteArrayInputStream(receivedPacket.getData());
//                ObjectInputStream in = new ObjectInputStream(receivedByteArray);
//                try {
//                    Object object = in.readObject();
///*                SerializedSimplyCommand receivedCommand = (SerializedSimplyCommand) object;
//                System.out.println(receivedCommand.getID());*/
//                    CommandDecoder commandDecoder = new CommandDecoder(serverSocket, receivedPacket);
//                    commandDecoder.decode(object);
//                } catch (ParseException | ClassNotFoundException e) {
//                    e.printStackTrace();
//                    break;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

//    public void run() throws IOException {
////        try {
////            if (port < 0) {
////                Logging.log(Level.INFO, "Invalid port! Port must be non-negative!");
////                System.exit(-1);
////            }
//        DatagramSocket serverSocket = new DatagramSocket(port.getPort());
//
//        while (true) {
//            DatagramPacket receivedPacket = new DatagramPacket(buf, buf.length);
//            Logging.log(Level.INFO, "Server has started listening on port " + port);
//            serverSocket.receive(receivedPacket);
//            Logging.log(Level.INFO, "Server received request from Client!");
//            ByteArrayInputStream receivedByteArray = new ByteArrayInputStream(receivedPacket.getData());
//            ObjectInputStream in = new ObjectInputStream(receivedByteArray);
//            try {
//                Object object = in.readObject();
///*                SerializedSimplyCommand receivedCommand = (SerializedSimplyCommand) object;
//                System.out.println(receivedCommand.getID());*/
//                CommandDecoder commandDecoder = new CommandDecoder(serverSocket, receivedPacket);
//                commandDecoder.decode(object);
//            } catch (ParseException | ClassNotFoundException e) {
//                e.printStackTrace();
//                break;
//            }
//        }
//    }
//}
