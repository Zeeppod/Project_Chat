package Client.packet;

import java.util.HashMap;
import java.util.Map;

public class PacketManager {

    private final static Map<Short, Class<? extends OPacket>> packet = new HashMap<>();

    static {
        packet.put((short) 1, PacketAuthorize.class);
        packet.put((short) 2, PacketMessege.class);
    }

    public static OPacket getPacket(short id){
        try {
            return packet.get(id).newInstance();
        }catch (InstantiationException | IllegalAccessException ex){
            ex.printStackTrace();
            return null;
        }
    }
}
