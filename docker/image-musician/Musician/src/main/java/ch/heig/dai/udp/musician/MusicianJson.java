package ch.heig.dai.udp.musician;
import com.google.gson.Gson;
import java.util.UUID;

public class MusicianJson {
    private final String uuid;
    private  final String sound;

    public MusicianJson(Musician musician) {
        this.uuid = UUID.randomUUID().toString();
        this.sound = musician.getInstrument().sound;
    }

    public String getUuid() {
        return uuid;
    }

    public String getSound() {
        return sound;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


}
