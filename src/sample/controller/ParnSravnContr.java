package sample.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Organization.AlternativesForMarks;
import sample.action.FuncWithAtlernatives.AltActionImpl;
import sample.action.MakePairComparison;
import sample.dao.alternatives.AlternativesDAO;
import sample.dao.DAOFactory;
import sample.Organization.AlternativesForMarksProperty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParnSravnContr {

    @FXML
    private TableView table;
    @FXML
    private TableColumn<AlternativesForMarksProperty,String> columnDetailName;
    @FXML
    private TableColumn<AlternativesForMarksProperty,String> columnDetailCountry;
    @FXML
    private TableColumn<AlternativesForMarksProperty,String> columnDetailCost;
    @FXML
    private TableColumn<AlternativesForMarksProperty,String> columnTypeOf;
    @FXML
    private TableColumn<AlternativesForMarksProperty,Integer> columnFirstEx;
//    @FXML
//    private TableColumn<AlternativesForMarksProperty,Integer> columnSecondEx;
//    @FXML
//    private TableColumn<AlternativesForMarksProperty,Integer> columnThirdEx;
    @FXML
    private Label labelCount;

    private Parent fxmlEdit; //для изменеия записи
    private TestController testController; //edit dialog controller
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private Stage editDialogStage;
    private Stage mainStage;
    private AltActionImpl detailAction;

    private void initLoader() { //иницзагрузчика
        try {
            fxmlLoader.setLocation(getClass().getResource("../fxml/Test.fxml"));
            fxmlEdit = fxmlLoader.load();
            testController = fxmlLoader.getController();
            //testController.initialize();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    private AltActionImpl alternativeAction;

    private ObservableList<AlternativesForMarksProperty> list = FXCollections.observableArrayList();

    @FXML
    private void initialize(){
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        AlternativesDAO alternativeDAO = DAOFactory.getInstance().getAlternativeDAO();
        alternativeAction = new AltActionImpl(alternativeDAO);
        InitColumns();
        initListener();
        setAlternativeList();
        table.refresh();
        initLoader();
    }


    public void showResult(ActionEvent actionEvent) {
        list.clear();
        list.addAll(table.getSelectionModel().getSelectedItems());
        if (!list.isEmpty()) {
            MakePairComparison makePairComparison = new MakePairComparison();
            makePairComparison.makeDecision(list);
        }

    }

    public void Back(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource(); // из общего графа ищем родителя
        Stage stage2 = (Stage) source.getScene().getWindow(); //возвращемсятуда откуда вызыывались
        stage2.close();
    }



    private void InitColumns() {

        columnDetailName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnDetailCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        columnTypeOf.setCellValueFactory(new PropertyValueFactory<>("typeOf"));
        columnDetailCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        columnFirstEx.setCellValueFactory(new PropertyValueFactory<>("firstEx"));
//        columnSecondEx.setCellValueFactory(new PropertyValueFactory<>("secondEx"));
//        columnThirdEx.setCellValueFactory(new PropertyValueFactory<>("thirdEx"));

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void setAlternativeList() {
        table.setItems(alternativeAction.getObservableList());
    }


    private void updateCountLabel() {

        Platform.runLater(() -> {
            labelCount.setText(" Количество записей: " + alternativeAction.getObservableList().size());
        });
    }

    private void initListener() {

        alternativeAction.getObservableList().addListener(new ListChangeListener<AlternativesForMarksProperty>() {
            @Override
            public void onChanged(Change<? extends AlternativesForMarksProperty> c) {
                updateCountLabel();
            }
        });

        TableView.TableViewSelectionModel<AlternativesForMarksProperty> selectionModel = table.getSelectionModel();
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                list = selectionModel.getSelectedItems();
            }
        });


        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    List<AlternativesForMarksProperty> list = new ArrayList<>();
                    list.clear();
                    list.addAll(table.getSelectionModel().getSelectedItems());
                    testController.setTestProperty(list);
                    showDialog();
                    table.refresh();
                    for (AlternativesForMarksProperty c : list)
                    {
                        alternativeAction.udpate(c);
                    }

                 //   ArrayList<AlternativesForMarks> arr = new ArrayList<AlternativesForMarks>(table.getItems());
                    //alternativeAction.setAlternativeList(arr);
                    // alternativeAction.setAlternativeList(new ArrayList<AlternativesForMarks>(table.getItems()));

                }

            }
        });

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
    public void res(ActionEvent actionEvent)
    {
        try {

            Stage stage = new Stage();

            //   InputStream stream = getClass().getResourceAsStream("../fxml/admin/workWithPatients.fxml");
            //Parent rootAdmin= new FXMLLoader().load(stream);
            FXMLLoader fxmll = new FXMLLoader(getClass().getResource("../fxml/Grafic.fxml"));
            Parent rootAdmin = fxmll.load();
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList
                    (

                    );
            list.clear();
            list.addAll(table.getSelectionModel().getSelectedItems());
            for(AlternativesForMarksProperty c : list) {
                PieChart.Data a = new PieChart.Data(" ",0);
                a.setName(c.getName());
                a.setPieValue(Double.parseDouble(c.getFirstEx()));
                pieChartData.add(a);
            }
            PieChart pieChart = new PieChart(pieChartData);
            pieChart.setTitle("Диаграмма");
            pieChart.setClockwise(true);
            pieChart.setLabelLineLength(50);
            pieChart.setLabelsVisible(true);
            pieChart.setStartAngle(180);
            //Creating a Group object
            Group root = new Group(pieChart);

            //Creating a scene object
            Scene scene = new Scene(root, 600, 400);
            stage.show();
            stage.setResizable(false);
            stage.setTitle("Просмотр результатов");
            stage.setScene(scene);

        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }


}
