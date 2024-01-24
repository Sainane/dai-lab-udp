package ch.heig.dai.udp.auditor;


import com.google.gson.Gson;


public class MusicianToSend {

    public String uuid;
    public Instrument instrument;
    public long lastActivity;

    public MusicianToSend(MusicianJson musicianJson) {
        this.uuid = musicianJson.getUuid();
        this.instrument = Instrument.findInstrumentBySound(musicianJson.getSound());
        this.lastActivity = System.currentTimeMillis();
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public boolean isActive() {
        return System.currentTimeMillis() - lastActivity < 5000;
    }

}
