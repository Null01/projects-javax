package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private final int portNumber = 1234; //Integer.parseInt(args[0]);

    private PrintWriter writer;
    private BufferedReader reader;
    private Socket clientSocket;
    private ServerSocket serverSocket;

    public void start() {
        try {

            serverSocket = new ServerSocket(portNumber);

            System.out.println("Esperando una conexión:");
            clientSocket = serverSocket.accept();
            System.out.println("Un cliente se ha conectado.");

            writer = new PrintWriter(clientSocket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String message;
            boolean isCommand = false;
            List<String> resultSet = new ArrayList<>();
            while ((message = reader.readLine()) != null) {

                // CARGA LOS DATOS DE UN USUARIO.
                if (message.compareTo(EnumCommand.SCORE_USERNAME.getCommmand()) == 0) {
                    ArrayList<String> scoreByPlayer = scoreByPlayer(resultSet.get(0));
                    for (String string : scoreByPlayer) {
                        writer.println(string);
                        message = reader.readLine();
                    }
                    writer.println(EnumCommand.SCORE_USERNAME.getCommmand());
                    isCommand = true;
                    resultSet.clear();
                }

                // GUARDA EL SCORE DE UN USUARIO
                if (message.compareToIgnoreCase(client.EnumCommand.SAVE_SCORE.getCommmand()) == 0) {
                    String username = resultSet.get(0);
                    int save_point = 0;
                    Date date = new Date();
                    for (int i = 1; i < resultSet.size(); i++) {
                        save_point += Integer.parseInt(resultSet.get(i));
                    }
                    saveScoreInRecordFile(username, save_point + "", date);
                    writer.println(date + " | " + save_point); // A partir del  |  lee el archivo.

                    isCommand = true;
                    resultSet.clear();
                }

                // Default, el message ingresado no es un commando.
                if (!isCommand) {
                    resultSet.add(message);
                    writer.println("");
                }

                isCommand = false;
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }

    private ArrayList<String> scoreByPlayer(String username) {
        ArrayList<String> ls = new ArrayList<>();
        File f = new File(username);
        if (f.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line;
                StringTokenizer st;
                while ((line = br.readLine()) != null) {
                    ls.add(line);
                }
            } catch (FileNotFoundException ex) {
                System.err.println(ex);
            } catch (IOException ex) {
                System.err.println(ex);
            }

        } else {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
        return ls;
    }

    private void saveScoreInRecordFile(String username, String score, Date date) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(username, true));
            bw.append(date + " | " + score);
            bw.newLine();
            bw.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();
    }

}
