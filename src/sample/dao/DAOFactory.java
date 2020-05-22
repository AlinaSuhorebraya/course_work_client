package sample.dao;

import sample.connector.Connection;
import sample.connector.Connector;
import sample.dao.Admin.AdminDAO;
import sample.dao.Admin.AdminDAOImpl;
import sample.dao.alternatives.AlternativesDAO;
import sample.dao.alternatives.AlternativesDAOImpl;

public class DAOFactory {

    public static final DAOFactory instance = new DAOFactory();

    public static DAOFactory getInstance() {
        return instance;
    }

    private DAOFactory() {
    }
    //////////////////////////

    private static Connection connection = Connector.getInstance().createConnection();


    public AlternativesDAO getAlternativeDAO() {
        return new AlternativesDAOImpl(connection);
    }



    public AdminDAO getAdminDAO(){
        return new AdminDAOImpl((connection));
    }

}
