package Server.packet;


import java.util.HashMap;
import java.util.Map;

public class PacketMenager {

    private final static Map<Short, Class<? extends  OPacket>> packets = new HashMap<>();

    static {
        packets.put((short) 1, PacketAuthorize.class);
        packets.put((short) 2, PacketMessege.class);
    }

    public static OPacket getPacket(short id){
        try {
            return packets.get(id).newInstance();
        } catch (InstantiationException | IllegalAccessException  ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
