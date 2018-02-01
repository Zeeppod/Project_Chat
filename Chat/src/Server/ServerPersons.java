package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerPersons extends Thread{

    private final ServerSocket server;

    ServerPersons(ServerSocket server){
        this.server = server;
    }
    @Override
    public void run(){
        while (true) {
            try {
                Socket client = server.accept();
                Clientpersons persons = new Clientpersons(client);
                persons.start();
                ServerLoader.personss.put(client, persons);
            } catch (SocketException ex) {
                return;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
            }
        }
    }
}