package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.action.FuncWithAtlernatives.AltActionImpl;

import sample.action.FuncWithAdmin.AdminActionImpl;
import sample.connector.Connector;
import sample.dao.alternatives.AlternativesDAO;
import sample.dao.DAOFactory;
import sample.dao.Admin.AdminDAO;
import sample.Organization.AdminProperty;
import sample.dao.alternatives.AlternativesDAO;

import java.io.IOException;


public class Autoriz {

    @FXML
    private TextField textLogin;

    @FXML
    private PasswordField textPassword;

    private AdminActionImpl adminCollection;
    @FXML
    private void initialize(){
        textLogin.setPromptText("введите логин");
        textPassword.setPromptText("введите пароль");
        AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
       adminCollection = new AdminActionImpl(adminDAO);
    }

    public void Autorization(ActionEvent actionEvent) {

        if (textLogin.getText().equals("") || textPassword.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Все поля должны быть заполнены!");
            alert.showAndWait();
            textLogin.clear();
            textPassword.clear();
        } else {

           if(adminCollection.getAdminList().size() ==0){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Нет зарегистированных пользователей!Пройдите процедуру регистрации!");
                alert.showAndWait();
                textLogin.clear();
                textPassword.clear();

            }
            else {
                boolean flag = false;
                for (AdminProperty c : adminCollection.getAdminList()) {
                    if (c.getLogin().trim().equals(textLogin.getText().trim()) &&
                            c.getPassword().trim().equals(textPassword.getText().trim())) {
                        if(textLogin.getText().equals("admin"))
                            AdminMenuContr.i =1;
                        else
                            AdminMenuContr.i =0;

                        showAdminMenu(actionEvent);
                        flag = true;

                    }
                }
                if (!flag) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Вы ввели неверно данные!");
                    alert.showAndWait();
                    textLogin.clear();
                    textPassword.clear();
                }
            }
        }
    }



    private void showAdminMenu(ActionEvent actionEvent) {

        close(actionEvent);
        try{
            AlternativesDAO alternativeDAO = DAOFactory.getInstance().getAlternativeDAO();
            AltActionImpl alternativeAction = new AltActionImpl(alternativeDAO);
            Stage stage = new Stage();
            Parent rootAdmin = FXMLLoader.load(getClass().getResource("../fxml/adminMenu.fxml"));
           if(AdminMenuContr.i==1) stage.setTitle("Меню админитратора" );
           else  stage.setTitle("Меню пользователя" );
            stage.setResizable(false); //запрещаем изменять размер окна

            stage.setScene(new Scene(rootAdmin,442,390));
            stage.initModality(Modality.WINDOW_MODAL); //отраатывает в момент нажтия кнопки //модальное окно(выход после д)
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow()) ;
            stage.show();

        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void Exit(ActionEvent actionEvent) {
        close(actionEvent);
        Connector.getInstance().connectionClose(Connector.getConnection());
        System.exit(0);
    }

    public void Registaration(ActionEvent actionEvent) {

        try{
            Stage stage = new Stage();
            Parent rootAdmin = FXMLLoader.load(getClass().getResource("../fxml/registration.fxml"));
            stage.setTitle("Регистрация" );

            stage.setScene(new Scene(rootAdmin,640,400));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow()) ;
            stage.showAndWait();
            AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
            adminCollection = new AdminActionImpl(adminDAO);
        }
        catch(IOException ex){
            ex.printStackTrace();
        }


    }

    private void close(ActionEvent actionEvent){
        Node source = (Node) actionEvent.getSource();
        Stage stage2 = (Stage) source.getScene().getWindow();
        stage2.close();
    }
}
