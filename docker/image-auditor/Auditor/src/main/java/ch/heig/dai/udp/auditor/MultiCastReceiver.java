package ch.heig.dai.udp.auditor;


import com.google.gson.Gson;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class MultiCastReceiver implements Runnable {

    public static ArrayList<MusicianToSend> musicians = new ArrayList<>();

    final static int PORT = 9904;

    public void run() {
        while (true) {
            try (DatagramSocket socket = new DatagramSocket(PORT)) {

                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);

                Gson gson = new Gson();
                MusicianJson musician = gson.fromJson(message, MusicianJson.class);
                MusicianToSend musicianToSend = new MusicianToSend(musician);
                if (musicianExists(musicianToSend)) {
                    continue;
                } else {
                    musicians.add(musicianToSend);
                }
                // musicians = activeMusicians();
            } catch (IOException e) {
                System.out.println("Exception: " + e);
            }
        }
    }

    public static ArrayList<MusicianToSend> activeMusicians() {
        ArrayList<MusicianToSend> actives = new ArrayList<>();
        for (MusicianToSend musician : musicians) {
            if (musician.isActive()) {
                actives.add(musician);
            }
        }
        return actives;
    }

    private boolean musicianExists(MusicianToSend newMusician) {
        for (MusicianToSend musician : musicians) {
            if (Objects.equals(newMusician.uuid, musician.uuid)) {
                musician.lastActivity = System.currentTimeMillis();
                return true;
            }
        }
        return false;
    }
}
