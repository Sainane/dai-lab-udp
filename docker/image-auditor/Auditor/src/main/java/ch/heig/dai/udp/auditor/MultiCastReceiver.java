package ch.heig.dai.udp.auditor;


import com.google.gson.Gson;
import java.io.IOException;
import java.net.DatagramPacket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.net.MulticastSocket;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;



public class MultiCastReceiver extends Thread {

    public static volatile ArrayList<MusicianToSend> musicians = new ArrayList<>();

    final static String IPADDRESS = "239.255.22.5";

    final static int PORT = 9904;

    public void run() {
        System.out.println("From the MultiCast Thread: " + musicians);

            try (MulticastSocket socket = new MulticastSocket(PORT)) {
                InetSocketAddress group_address =  new InetSocketAddress(IPADDRESS, PORT);
                // in container eth0 et mac en0
                NetworkInterface netif = NetworkInterface.getByName("eth0");
                socket.joinGroup(group_address, netif);

                while (!Thread.interrupted()) {
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
            }
        }  catch (IOException e) {
                System.out.println("Exception: " + e);
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
