package ch.heig.dai.udp.auditor;


public class Main {
    public static void main(String[] args) {

        MusicianJson musicianJson = new MusicianJson(Instrument.drum);
        MusicianToSend musician = new MusicianToSend(musicianJson);

        Thread tcp = Thread.startVirtualThread(new TcpSendMusicians());
        Thread udp = Thread.startVirtualThread(new MultiCastReceiver());

        // tcp.start();
        // udp.start();
        try {
            tcp.join();
        } catch (InterruptedException e) {
            System.out.println("Exception: " + e);
        }
        try {
            udp.join();
        } catch (InterruptedException e) {
            System.out.println("Exception: " + e);
        }
    }
}