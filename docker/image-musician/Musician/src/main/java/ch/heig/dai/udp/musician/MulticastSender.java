package ch.heig.dai.udp.musician;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import static java.nio.charset.StandardCharsets.*;

class MulticastSender {
    final static String IPADDRESS = "localhost";
    final static int PORT = 9904;

    public static void main(String[] args) {
        Instrument instrument = Instrument.valueOf(args[0]);
        Musician musician = new Musician(instrument);
        MusicianJson musicianJson = new MusicianJson(musician);
        try (DatagramSocket socket = new DatagramSocket()) {

            String message = musicianJson.toJson();
            byte[] payload = message.getBytes(UTF_8);
            InetSocketAddress dest_address = new InetSocketAddress(IPADDRESS, PORT);
            DatagramPacket packet = new DatagramPacket(payload, payload.length, dest_address);
            socket.send(packet);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
