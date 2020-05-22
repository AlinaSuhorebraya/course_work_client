package sample.dao.Admin;

import sample.connector.Connection;
import sample.Organization.Admin;
import sample.Organization.AdminProperty;
import sample.SendMessage.SendMessageClass;
import sample.SendMessage.SendMessageClassImpl;


public class AdminDAOImpl implements AdminDAO
{
    public static AdminDAOImpl instance = new AdminDAOImpl();

    public static AdminDAOImpl getInstance() {
        return instance;
    }

    private AdminDAOImpl() {
    }


    private Connection connection;


    ///


    public AdminDAOImpl(Connection connection) {
        this.connection = connection;

    }

    public void getListFromServer() {

        SendMessageClass mes = new SendMessageClassImpl(SendMessageClass.ADMIN_SHOW_ALL);
        connection.send(mes);
    }

    @Override
    public void insertAdmin(AdminProperty adminProperty) {


        System.out.println("Sending: " + adminProperty);

        Admin admin = new Admin();

        admin.setLogin(adminProperty.getLogin());
        admin.setPassword(adminProperty.getPassword());
        admin.setName(adminProperty.getName());
        admin.setSurname(adminProperty.getSurname());

        SendMessageClass msq = new SendMessageClassImpl(admin, SendMessageClass.ADMIN_ADD_NEW);
        connection.send(msq);

    }


    @Override
    public void changeAdmin(AdminProperty adminProperty) {

        Admin admin = new Admin();
        admin.setLogin(adminProperty.getLogin());
        admin.setPassword(adminProperty.getPassword());
        admin.setName(adminProperty.getName());
        admin.setSurname(adminProperty.getSurname());
        SendMessageClass msq = new SendMessageClassImpl(admin, SendMessageClass.ADMIN_CHANGE);
        connection.send(msq);
    }



    @Override
    public void deleteAdmin(AdminProperty adminProperty) {
        Admin admin = new Admin();
        admin.setLogin(adminProperty.getLogin());
        admin.setPassword(adminProperty.getPassword());
        admin.setName(adminProperty.getName());
        admin.setSurname(adminProperty.getSurname());

        SendMessageClass msq = new SendMessageClassImpl(admin, SendMessageClass.ADMIN_DELETE);
        connection.send(msq);
    }

}
