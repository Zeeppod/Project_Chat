package Client.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Authorize extends OPacket {

    private String nickname;

    public Authorize(){

    }

    public Authorize(String nickname){
        this.nickname = nickname;
    }

    @Override
    public short getID() {
        return 1;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeUTF(nickname);
    }

    @Override
    public void read(DataInputStream dis) throws IOException {

    }

    public void persons(){

    }
}
