package sample.connector;

import sample.action.FuncWithAtlernatives.AltActionImpl;
import sample.action.FuncWithAdmin.AdminActionImpl;
import sample.SendMessage.SendMessageClass;
import sample.SendMessage.SendMessageClassImpl;

import java.io.*;
import java.net.Socket;

public class ConnectionListenerImpl implements Connection,Runnable {
    private boolean needToRun=true;
    //
    private Socket socket;

    private OutputStream out;
    private InputStream in;

    public ConnectionListenerImpl(Socket socket, ConnectionListener connectionListener) throws  Exception{
        super();
        this.socket=socket;
        out = socket.getOutputStream();
        in = socket.getInputStream();
        Thread t =  new Thread(this);
        t.setPriority(Thread.MIN_PRIORITY);
        t.start();
    }

    @Override //фкнция для отправки смс
    public void send(SendMessageClass sendMessageClass) {
        try{
            ObjectOutputStream objOut = new ObjectOutputStream(out);//создет объект выходногоипотока и записывает сообщение
            objOut.writeObject(sendMessageClass);
            objOut.flush();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    //закрыть соединеие
    @Override
    public void close() {
        try {
            this.needToRun = false;
            this.socket.close();
            this.in.close();
            this.out.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        finally {
            System.out.println("Соединение закрыто!");
        }
    }

    @Override
    public void run() {
        System.out.println("run");

        while (needToRun) {
            try {
                int amount = in.available();

                if (amount != 0) {

                    System.out.println("Catch answers");
                    System.out.println("Listening socket");

                    ObjectInputStream objIn = new ObjectInputStream(in);

                    SendMessageClass msq = (SendMessageClassImpl)objIn.readObject();

                    System.out.println("We takes sample.SendMessage with type: " + msq.getType());
                    System.out.println( );
                    // System.out.println(msq.getAdminList().toString());
                    readMessage(msq);

                } else {
                    Thread.sleep(300);
                }
            } catch (IOException | InterruptedException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        }
    }

    private void readMessage(SendMessageClass msq){

        if(msq.getType() == SendMessageClass.SHOW_ALL_DETAIL){
            AltActionImpl.setAlternativeList(msq.getAlternatives());
        }

        if(msq.getType() == SendMessageClass.ADMIN_SHOW_ALL){
            AdminActionImpl.setAdminPropertyList(msq.getAdminList());
        }
    }

}
