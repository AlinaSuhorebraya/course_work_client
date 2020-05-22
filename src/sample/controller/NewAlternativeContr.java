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
import sample.Organization.AdminProperty;
import sample.Organization.AlternativesForMarks;
import sample.action.FuncWithAtlernatives.AltActionImpl;
import sample.dao.alternatives.AlternativesDAO;
import sample.dao.DAOFactory;
import sample.Organization.AlternativesForMarksProperty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;

public class NewAlternativeContr {


    @FXML
    private Button btnAddNew;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnChange;
    @FXML
    private Button btnFind;
    @FXML
    private TextField textFind;
    @FXML
    private TableView table;

    @FXML
    private TableColumn<AlternativesForMarksProperty, String> columnDetailName;
    @FXML
    private TableColumn<AlternativesForMarksProperty, String> columnDetailCountry;
    @FXML
    private TableColumn<AlternativesForMarksProperty, String> columnDetailCost;
    @FXML
    private TableColumn<AlternativesForMarksProperty, String> columnTypeOf;
    @FXML
    private TableColumn<AlternativesForMarksProperty, Integer> columnFirstEx;
    @FXML
    private TableColumn<AlternativesForMarksProperty, Integer> columnSecondEx;
    @FXML
    private TableColumn<AlternativesForMarksProperty, Integer> columnThirdEx;
    @FXML

    private Label labelCount;


    private Parent fxmlEdit; //для изменеия записи
    private DialogAlternativesController dialogWorkWithAlternative; //edit dialog controller
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private Stage editDialogStage;
    private Stage mainStage;
    private AltActionImpl detailAction;


    @FXML
    private void initialize() {

        AlternativesDAO alternativeDAO = DAOFactory.getInstance().getAlternativeDAO();
        detailAction = new AltActionImpl(alternativeDAO);
        InitColumns();
        initListener();
        setAlternativeList();
        initLoader();

    }


    private void setAlternativeList() {
        table.setItems(detailAction.getObservableList());
    }


    private void InitColumns() {
        // то что в ковычках этого будет искать геттер в калссе
        columnDetailName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnDetailCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        columnTypeOf.setCellValueFactory(new PropertyValueFactory<>("typeOf"));
        columnDetailCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        columnFirstEx.setCellValueFactory(new PropertyValueFactory<>("firstEx"));
       /* columnSecondEx.setCellValueFactory(new PropertyValueFactory<>("secondEx"));
        columnThirdEx.setCellValueFactory(new PropertyValueFactory<>("thirdEx"));*/
        textFind.setPromptText("введите стоимость");
    }

    private void initLoader() { //иницзагрузчика
        try {
            fxmlLoader.setLocation(getClass().getResource("../fxml/DialogAlternatives.fxml"));
            fxmlEdit = fxmlLoader.load();
            dialogWorkWithAlternative = fxmlLoader.getController();
            dialogWorkWithAlternative.initialize();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void initListener() {
        //добавляем слушатель и ананомнио реалзиуем интерфес слушателя  (срабыватывает псоле обновления коллекции
        detailAction.getObservableList().addListener(new ListChangeListener<AlternativesForMarksProperty>() {
            @Override
            public void onChanged(Change<? extends AlternativesForMarksProperty> c) {
                updateCountLabel();
            }
        });
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    AlternativesForMarksProperty alternativeProperty = new AlternativesForMarksProperty();
                    alternativeProperty = (AlternativesForMarksProperty) table.getSelectionModel().getSelectedItem();
                    dialogWorkWithAlternative.setAlternativeProperty(alternativeProperty);
                    showDialog();
                    textFind.clear();
                    detailAction.udpate(alternativeProperty);
                    detailAction.udpate(alternativeProperty);
                }
            }
        });

    }


    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    private void updateCountLabel() {
        ///выкидаем в другой поток тк работа идет не в потоке javaFX
        Platform.runLater(() -> {
            labelCount.setText("Количество записей: " + detailAction.getObservableList().size());
        });
    }


    public void actionButtonPressed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }


        AlternativesForMarksProperty selectedProperty = (AlternativesForMarksProperty) table.getSelectionModel().getSelectedItem();

        Button clickedButton = (Button) source;

        System.out.println(clickedButton.getId());

        switch (clickedButton.getId()) {
            //working
            case "btnAdd": {
                if (AdminMenuContr.i == 1) {
                    dialogWorkWithAlternative.setAlternativeProperty(new AlternativesForMarksProperty());
                    dialogWorkWithAlternative.p();
                    showDialog();
                    if (!(dialogWorkWithAlternative.getAlternativeProperty().getName().equals(""))) {
                        detailAction.add(dialogWorkWithAlternative.getAlternativeProperty());
                    }
                    Platform.runLater(() -> {
                        AlternativesDAO alternativeDAO = DAOFactory.getInstance().getAlternativeDAO();
                        setAlternativeList();
                    });
                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR, "Эта функция только для администратора!");
                    alert.showAndWait();

                }
            }
            break;
            case "btnChange": {
                if (AdminMenuContr.i == 1) {
                    System.out.println(selectedProperty.getId());

                    dialogWorkWithAlternative.setAlternativeProperty(selectedProperty);
                    showDialog();
                    table.refresh();

                    detailAction.udpate(selectedProperty);
                    detailAction.udpate(selectedProperty);
                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR, "Эта функция только для администратора!");
                    alert.showAndWait();

                }
            }
            break;
            case "btnDelete": {
                if (AdminMenuContr.i == 1) {

                    detailAction.delete(selectedProperty);
                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR, "Эта функция только для администратора!");
                    alert.showAndWait();

                }
            }
            break;
            case "btnText": {
                File file1 = new File("D:\\kursachSuhorebraya\\Kursach4sem\\Files\\alternatives.txt");
                if (!file1.exists()) {
                    Formatter nfile1;
                    try {
                        nfile1 = new Formatter("D:\\kursachSuhorebraya\\Kursach4sem\\Files\\alternatives.txt");
                        nfile1.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                Formatter nfile1;
                try {
                    nfile1 = new Formatter("D:\\kursachSuhorebraya\\Kursach4sem\\Files\\alternatives.txt");
                    nfile1.format("|   Наименование   |     Страна заказчик    |Стоимость| Тип постройки |Вес|\r\n");
                    nfile1.format("|==================|========================|=========|===============|===|\r\n");
                    for (AlternativesForMarksProperty c : detailAction.getObservableList()) {
                        nfile1.format("|%18s|%24s|%9.3f|%15s|%3b|\r\n", c.getName(), c.getCountry(), c.getCost(), c.getTypeOf(), c.getFirstEx()/*, c.getSecondEx(), c.getThirdEx()*/);
                    }
                    nfile1.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Отчет создан!");
                    alert.showAndWait();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                }


            }
            break;
        }
    }


    private void showDialog() {
        if (editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Меню изменения");
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(this.mainStage);
        }
        try {
            Thread.sleep(250);
            editDialogStage.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void actionSearch(ActionEvent actionEvent) {
        try {
            double text = Double.parseDouble(textFind.getText());
            System.out.println(text);
            ObservableList<AlternativesForMarksProperty> newList = FXCollections.observableArrayList();

            for (AlternativesForMarksProperty c : detailAction.getObservableList()) {

                if (text == (c.getCost())) { // ||text==c.getSecondEx()||text==c.getThirdEx()||text==c.getEx4()){
                    newList.add(c);
                }
            }//cоздаем новый список с теми элементами что нашли
            // System.out.println("Получили лист: " + newList.size());
            // newList.forEach(System.out::println);
            table.setItems(newList);
            table.refresh();
        } catch (Exception ex) {
            textFind.clear();
        }

    }

    public void ShowAll(ActionEvent actionEvent) {
        textFind.clear();
        setAlternativeList();
        table.refresh();
    }


}
