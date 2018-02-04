package Server.packet;

import java.io.DataInputStream;
import java.util.HashMap;
import java.util.Map;

public class PacketManeher {

    private final static Map<Short, Class<? extends OPacket>> packets = new HashMap<>();

    static {
        packets.put((short) 1, Authorize.class);
    }

    public static OPacket getPacket(short id) {
        try {
            return packets.get(id).newInstance();
        }catch (InstantiationException | IllegalAccessException ex){
            ex.printStackTrace();
            return null;
        }
    }
}
