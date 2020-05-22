package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMenuContr
{
public static int i=0;
    private void createTableRang(ActionEvent actionEvent){
        try {

            Stage stage = new Stage();

            //   InputStream stream = getClass().getResourceAsStream("../fxml/admin/workWithPatients.fxml");
            //Parent rootAdmin= new FXMLLoader().load(stream);
            FXMLLoader fxmll = new FXMLLoader(getClass().getResource("../fxml/TableWithRangExspert.fxml"));
            Parent rootAdmin = fxmll.load();

            stage.setTitle("Работа с экспертами");

            stage.setScene(new Scene(rootAdmin, 745, 402));
            //stage.initModality(Modality.WINDOW_MODAL); //отраатывает в момент нажтия кнопки //модальное окно(выход после д)
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());

            stage.show();

        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void AddNewAlternative(ActionEvent actionEvent) {
        try{
            Stage stage = new Stage();

            //   InputStream stream = getClass().getResourceAsStream("../fxml/admin/workWithPatients.fxml");
            //Parent rootAdmin= new FXMLLoader().load(stream);
            Parent rootAdmin = FXMLLoader.load(getClass().getResource("../fxml/addNewAlternatives.fxml"));

            stage.setTitle("Работа с экспертами" );

            stage.setResizable(false); //запрещаем изменять размер окна

            stage.setScene(new Scene(rootAdmin,1020,605));
            stage.initModality(Modality.WINDOW_MODAL); //отраатывает в момент нажтия кнопки //модальное окно(выход после д)
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow()) ;
            stage.show();

        }
        catch(IOException ex){
            ex.printStackTrace();
        }

    }


    public void showPairComparison(ActionEvent actionEvent){
        try {

            Stage stage = new Stage();

            //   InputStream stream = getClass().getResourceAsStream("../fxml/admin/workWithPatients.fxml");
            //Parent rootAdmin= new FXMLLoader().load(stream);
            FXMLLoader fxmll = new FXMLLoader(getClass().getResource("../fxml/ParnSravn.fxml"));
            Parent rootAdmin = fxmll.load();
            stage.setResizable(false);
            stage.setTitle("Просмотр результатов");
            stage.setScene(new Scene(rootAdmin, 1010, 541));
            stage.initModality(Modality.WINDOW_MODAL); //отраатывает в момент нажтия кнопки //модальное окно(выход после д)
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.showAndWait();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void Back(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource(); // из общего графа ищем родителя
        Stage stage2 = (Stage) source.getScene().getWindow(); //возвращемсятуда откуда вызыывались
        stage2.close();
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/Authorization.fxml"));
            stage.setTitle("Авторизация" );
            //  stage.setResizable(false); //запрещаем изменять размер окна
            stage.setScene(new Scene(root, 545, 415));
            stage.showAndWait();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void WorkWithAdmins(ActionEvent actionEvent) {
        if (i!=0) {
            try {

                Stage stage = new Stage();

                //   InputStream stream = getClass().getResourceAsStream("../fxml/admin/workWithPatients.fxml");
                //Parent rootAdmin= new FXMLLoader().load(stream);
                FXMLLoader fxmll = new FXMLLoader(getClass().getResource("../fxml/AllAdmins.fxml"));
                Parent rootAdmin = fxmll.load();
                stage.setTitle("Просмотр результатов");
                stage.setScene(new Scene(rootAdmin, 630, 500));
                //stage.initModality(Modality.WINDOW_MODAL); //отраатывает в момент нажтия кнопки //модальное окно(выход после д)
                stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());

                stage.show();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else
        {

                Alert alert = new Alert(Alert.AlertType.ERROR, "Эта функция только для администратора!");
                alert.showAndWait();

        }
    }



    public void showSuccessiveComparisons(ActionEvent actionEvent) {
        try {

            Stage stage = new Stage();

            //   InputStream stream = getClass().getResourceAsStream("../fxml/admin/workWithPatients.fxml");
            //Parent rootAdmin= new FXMLLoader().load(stream);
            FXMLLoader fxmll = new FXMLLoader(getClass().getResource("../fxml/PosledSravn.fxml"));
            Parent rootAdmin = fxmll.load();
            stage.setResizable(false);
            stage.setTitle("Просмотр результатов");
            stage.setScene(new Scene(rootAdmin, 725, 425));
            stage.initModality(Modality.WINDOW_MODAL); //отраатывает в момент нажтия кнопки //модальное окно(выход после д)
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.showAndWait();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
