package Server.packet;

import Server.ServerLoader;

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

    }

    @Override
    public void read(DataInputStream dis) throws IOException {
            nickname = dis.readUTF();
    }

    @Override
    public void persons() {
        ServerLoader.getPersons(getSocket()).setNickname(nickname);
        System.out.println("Out nickname is "+ nickname );
        try{
            Thread.sleep(2000);
        }catch (InterruptedException ex){

        }
        ServerLoader.end();
    }

}
