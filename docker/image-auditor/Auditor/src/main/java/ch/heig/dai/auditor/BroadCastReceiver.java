package ch.heig.dai.auditor;

import ch.heig.dai.udp.musician.Musician;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class BroadCastReceiver {

    public static ArrayList<Musician> musicians = new ArrayList<>();

    final static int PORT = 9904;

    public void receive() {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);

            Gson gson = new Gson();
            Musician musician = gson.fromJson(message, Musician.class);
            musicians.add(musician);

        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

}
