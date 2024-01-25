package ch.heig.dai.udp.musician;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import static java.nio.charset.StandardCharsets.*;

class MulticastSender {
    final static String IPADDRESS = "239.255.22.5";
    final static int PORT = 9904;

    public static void main(String[] args) {
        Instrument instrument = Instrument.valueOf(args[0]);
        long lastActivity = 0;
            MusicianJson musicianJson = new MusicianJson(instrument);
            try (DatagramSocket socket = new DatagramSocket()) {
                while (true) {
                    if (System.currentTimeMillis() - lastActivity < 1000) continue;
                    lastActivity = System.currentTimeMillis();
                    String message = musicianJson.toJson();
                    byte[] payload = message.getBytes(UTF_8);
                    InetSocketAddress dest_address = new InetSocketAddress(IPADDRESS, PORT);
                    DatagramPacket packet = new DatagramPacket(payload, payload.length, dest_address);
                    socket.send(packet);
                    // System.out.println(packet);
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

