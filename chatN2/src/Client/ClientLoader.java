package Client;

import Client.packet.OPacket;
import Client.packet.PacketAuthorize;
import Client.packet.PacketManager;
import Client.packet.PacketMessege;

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
         handle();
        end();

     }

     public static void sendPacket(OPacket packet){
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeShort(packet.getId());
            packet.write(dos);
            dos.flush();
        }catch (IOException ex){
            ex.printStackTrace();
        }
     }

     private static void connect() {
         Scanner scan = new Scanner(System.in);
         System.out.println("Введите Ip: ");
         String IP = scan.nextLine();
         System.out.println("Введите порт: ");
         String PORT = scan.nextLine();
         try {
             socket = new Socket(IP, Integer.parseInt(PORT));
         }catch (IOException ex){
             ex.printStackTrace();
         }
         System.out.println("Введите ваш ник: ");
     }

     private static void handle(){
         Thread handler = new Thread(){

             @Override
             public void run(){
                 while (true){
                     try{
                        DataInputStream dis = new DataInputStream(socket.getInputStream());
                        if (dis.available() <= 0){
                           try {
                               Thread.sleep(10);
                           }catch (InterruptedException ex){}
                            continue;
                        }
                        short id = dis.readShort();
                        OPacket packet = PacketManager.getPacket(id);
                        packet.read(dis);
                        packet.handle();
                     }catch (IOException ex){
                         ex.printStackTrace();
                     }
                 }
             }
         };
         handler.start();

         readChat();
     }

    private static void readChat(){
        Scanner scan = new Scanner(System.in);
        while (true) {
            if (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.equals("/end")){
                    end();
                }
                if (!sentNickname){
                    sentNickname = true;
                    sendPacket(new PacketAuthorize(line));
                    continue;
                }
                sendPacket(new PacketMessege(null, line));
            } else
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {

                }
        }
    }

      private static void end(){
        try {
            socket.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        System.exit(0);
      }

}
