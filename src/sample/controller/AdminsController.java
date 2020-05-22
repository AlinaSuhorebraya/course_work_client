package sample.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.io.File;
import sample.action.FuncWithAdmin.AdminActionImpl;
import sample.dao.DAOFactory;
import sample.dao.Admin.AdminDAO;
import sample.Organization.AdminProperty;


import java.io.IOException;

public class AdminsController {

    @FXML
    private Button btnAddNewAdmin;
    @FXML
    private Button btnChangeAdmin;
    @FXML
    private Button btnDeleteAdmin;
    @FXML
    private Button btnFindAdmin;
    @FXML
    private TextField textFindAdmin;
    @FXML
    private TableView tableAdmins;
    @FXML
    private TableColumn<AdminProperty, String> columnLogin;
    @FXML
    private TableColumn<AdminProperty, String> columnPassword;
    @FXML
    private TableColumn<AdminProperty, String> columnName;
    @FXML
    private TableColumn<AdminProperty, String> columnSurname;
    @FXML
    private Label labelAdminCount;

    private Parent fxmlEdit; //для изменеия записи
    private DialogAdminsController dialogWorkWithAdmins; //edit dialog controller
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private Stage editDialogStage;
    private Stage mainStage;
    private AdminActionImpl adminCollection;


    @FXML
    private void initialize() {

        AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
        adminCollection = new AdminActionImpl(adminDAO);
        InitColumns();
        initListener();
        adminCollection.fillTestData();
        setAdminList();
        initLoader();

    }

    private void setAdminList() {
        tableAdmins.setItems(adminCollection.getAdminList());
    }



    private void InitColumns() {
        // то что в ковычках этого будет искать геттер в калссе
        columnLogin.setCellValueFactory(new PropertyValueFactory<AdminProperty, String>("login"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<AdminProperty, String>("password"));
        columnName.setCellValueFactory(new PropertyValueFactory<AdminProperty, String>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<AdminProperty, String>("surname"));
        textFindAdmin.setPromptText("введите логин");
    }

    private void initLoader() {
        try {
            fxmlLoader.setLocation(getClass().getResource("../fxml/DialogAdmins.fxml"));
            fxmlEdit = fxmlLoader.load();
            dialogWorkWithAdmins = fxmlLoader.getController();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private void initListener() {
        //добавляем слушатель и ананомнио реалзиуем интерфес слушателя  (срабыватывает псоле обновления коллекции
        adminCollection.getAdminList().addListener(new ListChangeListener<AdminProperty>() {
            @Override
            public void onChanged(Change<? extends AdminProperty> c) {
                updateCountAdminLabel();
            }
        });

        tableAdmins.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    textFindAdmin.clear();
                    tableAdmins.refresh();
                }
            }
        });


    }





    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    private void updateCountAdminLabel() {
        ///выкидаем в другой поток тк работа идет не в потоке javaFX
        Platform.runLater(() -> {
            labelAdminCount.setText("Количество записей: " + adminCollection.getAdminList().size());
        });
    }


    public void actionButtonPressed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        //разрешаю выбират только одно рыло
        AdminProperty selectedAdminProperty = (AdminProperty) tableAdmins.getSelectionModel().getSelectedItem();
        Button clickedButton = (Button) source;
        //System.out.println(clickedButton.getId());

        switch (clickedButton.getId()) {
            //working
            case "btnAddNewAdmin": {
                dialogWorkWithAdmins.setAdminProperty(new AdminProperty());
                showDialog();
                adminCollection.addAdmin(dialogWorkWithAdmins.getAdminProperty());
            }
            break;
            case "btnChangeAdmin": {
                AdminProperty adminProperty = new AdminProperty();
                adminProperty = selectedAdminProperty;
                dialogWorkWithAdmins.setAdminProperty(selectedAdminProperty);
                showDialog();
                tableAdmins.refresh();
                adminCollection.deleteAdmin(adminProperty);
                adminCollection.addAdmin(selectedAdminProperty);
            }
            break;
            case "btnDeleteAdmin": {
                adminCollection.deleteAdmin(selectedAdminProperty);
            }
            break;
            case "btnText": {
                File file1 = new File("D:\\kursachSuhorebraya\\Kursach4sem\\Files\\admins.txt");
                if (!file1.exists())
                {
                    Formatter nfile1;
                    try
                    {
                        nfile1 = new Formatter("D:\\kursachSuhorebraya\\Kursach4sem\\Files\\admins.txt");
                        nfile1.close();
                    } catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                    }

                Formatter nfile1;
                try {
                    nfile1 = new Formatter("D:\\kursachSuhorebraya\\Kursach4sem\\Files\\admins.txt");
                    nfile1.format("|  Логин |     Пароль     | Имя | Фамилия |\r\n");
                    nfile1.format("|========|==================|=======|=========|\r\n");
                    for (AdminProperty c : adminCollection.getAdminList()) {
                        nfile1.format("|%7s |%17s  |%8s  |%5s |\r\n", c.getLogin(), c.getPassword(), c.getName(), c.getSurname());
                    }
                    nfile1.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Отчет создан!");
                    alert.showAndWait();
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();

                }


            }
            break;
        }
    }



    private void showDialog() {
        if(editDialogStage==null){
            editDialogStage=new Stage();
            editDialogStage.setTitle("Пользователи");
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(this.mainStage);
        }
        editDialogStage.showAndWait();
    }


    public void actionSearch(ActionEvent actionEvent) {
        String text = textFindAdmin.getText();
        System.out.println(text);
        ObservableList<AdminProperty> newList = FXCollections.observableArrayList();
        for (AdminProperty c : adminCollection.getAdminList()) {
            if (c.getLogin().trim().equals( text.trim() )) {
                newList.add(c);
            }
        }
        //  System.out.println("Получили лист: " + newList.toString());
        tableAdmins.setItems(newList);
        tableAdmins.refresh();

    }

    public void ShowAll(ActionEvent actionEvent) {
        textFindAdmin.clear();
        setAdminList();
        tableAdmins.refresh();
    }
}
