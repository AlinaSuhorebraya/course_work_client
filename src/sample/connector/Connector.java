package sample.connector;

import javafx.scene.control.Alert;

import java.net.InetAddress;
import java.net.Socket;

public class Connector implements ConnectionListener {
    public static final Connector instance = new Connector();
    public static Connector getInstance() { return instance;}
    private Connector() { }
    ///////////////////////////////////////////
    private static Connection connection;

    public Connection createConnection() {

        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), Connection.PORT);
            connectionCreated( connection = new ConnectionListenerImpl(socket, this));
            return connection;

        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,"не удалось подключиться к серверу");
            alert.showAndWait();
        }
        return connection;
    }

    public static Connection getConnection(){
        return connection;
    }

    @Override
    public void connectionCreated(Connection c) {
        System.out.println("Connection successful!");
    }

    @Override
    public void connectionClose(Connection c) {
        System.out.println("Connection ended");
        c.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Успешное отключение от сервера");
        alert.showAndWait();
    }

    @Override
    public void connectionException(Exception ex) {
        ex.printStackTrace();
    }
}
