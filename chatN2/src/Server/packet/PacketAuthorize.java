package Server.packet;

import Server.ServerLoader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketAuthorize extends OPacket {

    private String nickname;

    public PacketAuthorize(){

    }

    public PacketAuthorize(String nickname){
        this.nickname = nickname;
    }



    @Override
    public short getId() {
        return 1;
    }

    @Override
    public void write(DataOutputStream dos) {

    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        nickname = dis.readUTF();
    }

    @Override
    public void handle() throws Exception {
        ServerLoader.getHandler(getSocket()).setNickname(nickname);
    }
}
