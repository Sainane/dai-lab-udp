package ch.heig.dai.udp.musician;

public enum Instrument {
    piano("ti-ta-ti"),
    trumpet("pouet"),
    flute("trulu"),
    violin("gzi-gzi"),
    drum("boum-boum");

   final String sound;

    Instrument(String sound) {
        this.sound = sound;
    }
}
