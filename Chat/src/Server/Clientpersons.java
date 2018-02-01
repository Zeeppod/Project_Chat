package Server;

import Client.packet.Authorize;
import Client.packet.OPacket;
import Server.packet.PacketManeher;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Clientpersons extends Thread{


    private final Socket client;
    private  String nickname = "Неизвестный";

    public Clientpersons(Socket client){
        this.client=client;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    @Override
    public void run(){
        while (true){
            if (!readData())
            try{
                Thread.sleep(10);
            }catch (InterruptedException ex){

            }
    }
    }

    private boolean readData(){
        try{
            DataInputStream dis = new DataInputStream(client.getInputStream());
            if (dis.available() <= 0)
                return false;
            short id = dis.readShort();
            Server.packet.OPacket packet = PacketManeher.getPacket(id);
                packet.setSocket(client);
                packet.read(dis);
                packet.persons();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return true;
    }

    public void invalidate() {
        ServerLoader.invalidate(client );
    }
 }
