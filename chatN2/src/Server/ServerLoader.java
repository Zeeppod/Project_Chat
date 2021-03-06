package Server;

import Server.packet.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServerLoader {

    public static ServerSocket serverSocket;
    private static ServerHandler handler;
    public static Map<Socket, ClientHandler> handlers = new HashMap<>();


    public static void main(String[] args){
        start();
        handle();
        end();
    }

    private static void handle(){
        handler = new ServerHandler(serverSocket);
        handler.start();
        readChat();
    }

     public static void sendPacket(Socket receiver, OPacket packet){
        try{
            DataOutputStream dos = new DataOutputStream(receiver.getOutputStream());
            dos.writeShort(packet.getId());
            packet.write(dos);
            dos.flush();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private static void readChat(){
        Scanner scan = new Scanner(System.in);
        while (true) {
            if (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.equals("/end")){
                    end();}
                else {
                    System.out.println("Неизвестная команда");
                }
            } else
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {}
        }
    }

    public static ServerHandler getServerHandler(){
        return handler;
    }

    private static void start() {

        try{
            serverSocket = new ServerSocket(666);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void end(){
        try{
            serverSocket.close();
    }catch (IOException ex){}
    System.exit(0);
    }

    public static ClientHandler getHandler(Socket socket){
        return handlers.get(socket);
    }

    public static void invalidate(Socket socket){
        handlers.remove(socket);
    }

}
