package ch.heig.dai.auditor;


import com.google.gson.Gson;

import java.util.Date;
import java.util.UUID;


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
        if (System.currentTimeMillis() - lastActivity <= 5000) {
            return true;
        }
        return false;
    }

}
