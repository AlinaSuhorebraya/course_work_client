package sample.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Organization.AlternativesForMarksProperty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GrafContr  {
    private List<AlternativesForMarksProperty> list = new ArrayList<>();
    @FXML
    protected void initialize() {


        initLoader();

    }
    private void initLoader() { //иницзагрузчика
        System.out.println("initLoader graph");


    }
    public void setGrProperty(List<AlternativesForMarksProperty> propertyList){

      /*  if(propertyList.isEmpty()){
            return;
        }
        this.list.addAll(propertyList);
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList
                (

                );
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

        //Setting title to the Stage
        stage.setTitle("Pie chart");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();*/
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

            //Setting title to the Stage
            stage.setTitle("Pie chart");

            //Adding scene to the stage
            stage.setScene(scene);

            //Displaying the contents of the stage
            stage.show();
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
}