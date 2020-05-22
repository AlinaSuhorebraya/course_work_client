package sample.connector;

import sample.SendMessage.SendMessageClass;
public interface Connection {
    int PORT =3181;

    void send(SendMessageClass sendMessageClass);

    void close();
}
