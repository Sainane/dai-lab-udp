package ch.heig.dai.auditor;

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

    public static Instrument findInstrumentBySound(String sound) {
        for (Instrument instrument : Instrument.values()) {
            if (instrument.sound.equals(sound)) {
                return instrument;
            }
        }
        return null;
    }
}
