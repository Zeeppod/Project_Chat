package Client;


import Client.packet.Authorize;
import Client.packet.OPacket;
import Client.packet.PacketManeher;
import Client.packet.PacketMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientLoader {

    private static Socket socket;
    private static boolean sentNickname = false;

    public static void main(String[] args){
        connect();
        readChat();
        try{
            Thread.sleep(1000);
        }catch (InterruptedException ex){

        }
        end();
    }

    public static void sendPacket(PacketMessage packet){
        try{
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeShort(packet.getID());
            packet.write(dos);
            dos.flush();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private static void connect(){
        try {
            socket = new Socket("localhost", 8889);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private static void persons(){
        Thread persons = new Thread() {

            @Override
            public void run(){
                while (true){
                    try {
                        DataInputStream dis =  new DataInputStream(socket.getInputStream());
                        if(dis.available() <= 0){
                            try{
                                Thread.sleep(10);
                            }catch (InterruptedException ex){}
                            continue;}
                        short id = dis.readShort();
                        OPacket packet = PacketManeher.getPacket(id);
                        packet.read(dis);
                        packet.persons();
                    }catch (IOException ex){
                        ex.printStackTrace();
                    }

                }
            }
        };
        persons.start();

        readChat();
    }

    private static void readChat(){
        Scanner scan = new Scanner(System.in);
        while (true){
            if (scan.hasNextLine()){
                String line = scan.nextLine();
                if (!sentNickname){
                    sentNickname = true;
                    sendPacket(new Authorize(line));
                }
                sendPacket(new PacketMessage(null, line));

            }else
                try{
                    Thread.sleep(10);
                }catch (InterruptedException ex){

                }
        }
    }

    private static void end(){
        try{
            socket.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

}
