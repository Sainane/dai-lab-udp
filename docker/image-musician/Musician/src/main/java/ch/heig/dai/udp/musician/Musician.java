package ch.heig.dai.udp.musician;

public class Musician {
    private Instrument instrument;
    public Musician(Instrument instrument) {
        this.instrument = instrument;
    }
    public Instrument getInstrument() {
        return instrument;
    }
}
