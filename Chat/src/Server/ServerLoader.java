package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServerLoader {

    private static ServerSocket server;
    private static ServerPersons persons;
    static Map<Socket, Clientpersons> personss = new HashMap<>();


    public static void main(String[] args){
        start();
        persons();
        end();
    }

    private static void persons() {
        persons = new ServerPersons(server);
        persons.start();
        readChat();
    }

    private static void readChat(){
        Scanner scan = new Scanner(System.in);
         while (true){
             if (scan.hasNextLine()){
                 String line = scan.nextLine();
                 if (line.equals("/end"))
                     end();
                 else {
                     System.out.println("Неизвестная команда");
                 }
             }else
                 try{
                     Thread.sleep(10);
                 }catch (InterruptedException ex){

                 }
         }
    }

    public static ServerPersons getServerPersons(){
        return persons;
    }

    private static void start(){
        try{
            server = new ServerSocket(8889);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public static void end(){
        try{
            server.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        System.exit(0);
    }

    public static Clientpersons getPersons(Socket socket){
        return personss.get(socket);
    }

    public static void invalidate(Socket socket){
        personss.remove(socket);
    }
}
