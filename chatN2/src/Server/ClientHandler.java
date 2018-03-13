package Server;


import Server.packet.OPacket;
import Server.packet.PacketMenager;

import java.io.DataInputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandler extends Thread {

    private  final Socket client;
    public String nickname;
    Date data = new Date();
    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");

    public ClientHandler(Socket client){
        this.client = client;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }


    @Override
    public void run(){
         while (true) {
                if (!readData())
                     try {
                         Thread.sleep(10);
                     }catch (InterruptedException ex){ }
             }
    }

    private boolean readData(){
        try {
            DataInputStream dis = new DataInputStream(client.getInputStream());
            if (dis.available() <= 0 )
                return false;
            short id = dis.readShort();
            OPacket packet = PacketMenager.getPacket(id);
            packet.setSocket(client);
            packet.read(dis);
            packet.handle();
        }catch (Exception ex){}
        return true;
    }


    public void invalidate(){
        ServerLoader.invalidate(client);
        System.out.println("Пользователь " + this.nickname + " отключился"+ " " + format.format(data));
    }

}
