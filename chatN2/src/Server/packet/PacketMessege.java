package Server.packet;

import Server.ServerLoader;

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
        dos.writeUTF(sender);
        dos.writeUTF(messege);
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        messege = dis.readUTF();
    }

    @Override
    public void handle() {
        sender = ServerLoader.getHandler(getSocket()).getNickname();
        ServerLoader.handlers.keySet().forEach(s -> ServerLoader.sendPacket(s,this));
        System.out.println(String.format("[%s] %s", sender, messege ));
    }
}
