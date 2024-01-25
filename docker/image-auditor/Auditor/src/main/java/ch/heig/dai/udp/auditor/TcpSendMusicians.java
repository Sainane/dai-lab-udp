package ch.heig.dai.udp.auditor;

import com.google.gson.Gson;
import java.io.*;
import java.net.*;

import static java.nio.charset.StandardCharsets.*;


public class TcpSendMusicians extends Thread {

    final static int PORT = 2205;

    public void run() {
        long lastActivity = 0;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (!Thread.interrupted()) {
                if(System.currentTimeMillis()-lastActivity < 1000) continue;
                lastActivity = System.currentTimeMillis();
                try (Socket socket = serverSocket.accept();
                     var in = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
                     var out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), UTF_8))) {


                    Gson gson = new Gson();
                    String message = gson.toJson(MultiCastReceiver.activeMusicians());
                    System.out.println("From the TCP Thread: " + MultiCastReceiver.musicians);
                    //System.out.println(message);

                    out.write(message);
                    out.flush();

                } catch (IOException e) {
                    System.out.println("Server: socket ex.: " + e);
                }
            }
        } catch (IOException e) {
            System.out.println("Server: server socket ex.: " + e);
        }
    }
}
