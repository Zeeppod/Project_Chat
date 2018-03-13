package Client.GUI;

import Client.DB.Send;
import Client.packet.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;



public class ClientLauncher {

    public static String massege;
    public static JTextArea ta;
    public static JTextField t;
    public static JButton b;
    public static Socket socket;
    public static String IP;
    public static int PORT;
    private static boolean sentNickname = false;
    public static JTextField tf3;

    public static void main(String[] args){
        go();

    }

    public static void go() {

        JFrame avt = new JFrame("Авторизация");

        avt.setBounds(250, 250, 570, 100);
        avt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        avt.setVisible(true);

        JButton but = new JButton("Авторизоваться");
        JTextField tf1 = new JTextField("", 5);
        JTextField tf2 = new JTextField("", 5);
        tf3 = new JTextField("", 5);
        JLabel l1 = new JLabel("Введите IP:");
        JLabel l2 = new JLabel("Введите Port:");
        JLabel l3 = new JLabel("Введите ваш ник:");

        Container cont = avt.getContentPane();
        cont.setLayout(new GridLayout(3, 3, 3, 3));
        cont.add(l1);
        cont.add(l2);
        cont.add(l3);
        cont.add(tf1);
        cont.add(tf2);
        cont.add(tf3);
        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                IP = tf1.getText();
                PORT = Integer.parseInt(tf2.getText());
                try {
                    avt.setVisible(false);
                    connect();
                    Thread.sleep(10);
                    Chat();
                    Thread.sleep(100);
                    Send.History();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        cont.add(but);

    }

    public static void Chat(){
        JFrame st = new JFrame("Chat");
        st.setResizable(false);
        st.setLocationRelativeTo(null);
        JPanel p = new JPanel();
        ta = new JTextArea(15,30);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setEditable(false);
        JScrollPane sp = new JScrollPane(ta);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        t = new JTextField(20);
        b = new JButton("Отправить");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                massege = t.getText();
                sendpacket(new PacketMessege(null, massege));
                t.setText("");
                t.requestFocus();
            }
        });

        p.add(sp);
        p.add(t);
        p.add(b);


        st.getContentPane().add(BorderLayout.CENTER, p);
        st.setSize(400,340);
        st.setVisible(true);
        st.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void sendpacket(OPacket packet){
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
        try {
            socket = new Socket(IP, Integer.parseInt(String.valueOf(PORT)));
            handle();
        }catch ( IOException ex){
            ex.printStackTrace();
        }
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

    private static void readChat() {
        String line = tf3.getText();
        if (!sentNickname){
            sentNickname = true;
            sendpacket(new PacketAuthorize(line));
        }
    }
}