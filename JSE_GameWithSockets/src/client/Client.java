package client;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Client {

    private String hostName;
    private final int portNumber = 1234; //Integer.parseInt(args[1]);

    private Socket echoSocket = null;
    private PrintWriter writer;
    private BufferedReader reader;

    public boolean connection(String hostname) {
        boolean connect = false;
        if (echoSocket == null) {
            this.hostName = hostname;
            try {
                echoSocket = new Socket(hostName, portNumber);
                reader = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                writer = new PrintWriter(echoSocket.getOutputStream(), true);
                connect = true;
            } catch (UnknownHostException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            connect = true;
        }
        return connect;
    }

    public String talk(String message) {
        try {
            writer.println(message);
            System.out.println("enviado y esperando...");
            return reader.readLine();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public boolean disConnection() {
        try {
            echoSocket.close();
            reader.close();
            writer.close();
            this.echoSocket = null;
            System.out.println("Cerrando sockets...");
            return true;
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return false;
    }

    public String newScoreOfGame(ArrayList<Integer> aleatorio, String username) {
        String talk = talk(username);
        for (Integer integer : aleatorio) {
            talk = talk(integer + "");
        }
        talk = talk(EnumCommand.SAVE_SCORE.getCommmand());
        return talk;
    }
}
