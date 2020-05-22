package sample.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.action.FuncWithAdmin.AdminActionImpl;
import sample.dao.DAOFactory;
import sample.action.FuncWithAdmin.AdminActionImpl;
import sample.dao.DAOFactory;
import sample.dao.Admin.AdminDAO;
import sample.Organization.AdminProperty;

public class Registration {
    @FXML
    private TextField textLogin;
    @FXML
    private TextField textPassword;
    @FXML
    private TextField textName;
    @FXML
    private TextField textSurname;

    private AdminActionImpl adminCollection;
    @FXML
    private void initialize(){
        textLogin.setPromptText("Введите логин: ");
        textPassword.setPromptText("Введите пароль: ");
        textName.setPromptText("Введите имя: ");
        textSurname.setPromptText("Введите фамилию: ");
    }
    public void actionSave(ActionEvent actionEvent) {
        if(textLogin.getText().equals("") || textPassword.getText().equals("") || textName.getText().equals("")||
                textSurname.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Все поля должны быть заполнены!");
            alert.showAndWait();
        }
        else {
            AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
            adminCollection = new AdminActionImpl(adminDAO);

            AdminProperty adminProperty = new AdminProperty();
            adminProperty.setLogin(textLogin.getText());
            adminProperty.setPassword(textPassword.getText());
            adminProperty.setSurname(textSurname.getText());
            adminProperty.setName(textName.getText());
            adminCollection.addAdmin(adminProperty);
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Регистрация прошла успешно!");
            alert.showAndWait();
            Node source = (Node) actionEvent.getSource(); // из общего графа ищем родителя
            Stage stage = (Stage) source.getScene().getWindow(); //возвращемсятуда откуда вызыывались
            stage.close();
        }
    }

    public void actionClose(ActionEvent actionEvent) {

        Node source = (Node) actionEvent.getSource(); // из общего графа ищем родителя
        Stage stage = (Stage) source.getScene().getWindow(); //возвращемсятуда откуда вызыывались
        stage.close();
    }
}
