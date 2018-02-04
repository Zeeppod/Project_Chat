package Client.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketMessege extends OPacket{

    private String sender, messege;

    public PacketMessege() {

    }

    public PacketMessege(String sender, String messege){
        this.sender = sender;
        this.messege = messege;
    }

    @Override
    public short getId() {
        return 2;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeUTF(messege);
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        sender = dis.readUTF();
        messege = dis.readUTF();
    }

    @Override
    public void handle() {
        System.out.println(String.format("[%s] %s", sender, messege));
    }
}
