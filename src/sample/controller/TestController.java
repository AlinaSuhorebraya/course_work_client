package sample.controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.w3c.dom.Text;
import sample.Organization.AlternativesForMarks;
import sample.Organization.AlternativesForMarksProperty;

import java.util.ArrayList;
import java.util.List;

public class TestController {

    @FXML
    private AnchorPane anchorPaneT;

    @FXML
    private VBox vBox1;

    @FXML
    private HBox hBox1;
    @FXML
    private AnchorPane anchorHbox2;
    @FXML
    private AnchorPane anchorHBox1;
    @FXML
    private AnchorPane anchorVbox1;
    @FXML
    private TableView tableV;

    @FXML
    private GridPane Grid;

    private List<AlternativesForMarksProperty> list = new ArrayList<>();

    @FXML
    protected void initialize() {


        initLoader();

    }

    private TextField f1 = new TextField();
    TextField s1 = new TextField();
    TextField s2 = new TextField();
    TextField s3 = new TextField();
    TextField t1 = new TextField();
    TextField t2 = new TextField();
    TextField t3 = new TextField();
    TextField fr1 = new TextField();
    TextField fr2 = new TextField();
    TextField fr3 = new TextField();
    TextField f2 = new TextField();
    TextField f3 = new TextField();

    private void initLoader() { //иницзагрузчика
     System.out.println("initLoader TEST");


    }

    public void setTestProperty(List<AlternativesForMarksProperty> propertyList){

        if(propertyList.isEmpty()){
            return;
        }
        this.list.addAll(propertyList);
        int layot = 50;
        //for(int i = 0 ; i < list.size(); i++) {
        for(AlternativesForMarksProperty c : list) {
            Label labelVBox = new Label();
            labelVBox.setPrefWidth(200);
            labelVBox.setPrefHeight(200);
            labelVBox.setText(c.getName());
            labelVBox.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            vBox1.getChildren().add(labelVBox);

            Label labelHbox = new Label();
            labelHbox.setPrefWidth(200);
            labelHbox.setPrefHeight(50);
            labelHbox.setText(c.getName());
            labelHbox.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            hBox1.getChildren().add(labelHbox);

        }

       // f1.editableProperty().set(true);
        Grid.add( new Label("         --------------"), 0,0);
        Grid.add( new Label("         --------------"), 1,1);
        Grid.add( new Label("         --------------"), 2,2);
        Grid.add( new Label("         --------------"), 3,3);
        Grid.add(f1,0,1);
        Grid.add(f2,0,2);
        Grid.add(f3,0,3);
        Grid.add(s1,1,0);
        Grid.add(s2,1,2);
        Grid.add(s3,1,3);
        Grid.add(t1,2,0);
        Grid.add(t2,2,1);
        Grid.add(t3,2,3);
        Grid.add(fr1,3,0);
        Grid.add(fr2,3,1);
        Grid.add(fr3,3,2);


             /*HBox hBox2 = new HBox();
             HBox hBox3 = new HBox();

            Label labelHbox2 = new Label();
            labelHbox2.setPrefWidth(50);
            labelHbox2.setPrefHeight(50);
            labelHbox2.setText("TEST");
            labelHbox2.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));*/

            /*Label labelHbox3 = new Label();
            labelHbox3.setPrefWidth(50);
            labelHbox3.setPrefHeight(50);
            labelHbox3.setText("TEST");
            labelHbox3.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

            Label labelHbox4 = new Label();
            labelHbox4.setPrefWidth(50);
            labelHbox4.setPrefHeight(50);
            labelHbox4.setText("TEST");
            labelHbox4.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));*/



            //hBox2.getChildren().addAll(labelHbox2/*,labelHbox3,labelHbox4*/);

           // hBox3.getChildren().addAll(labelHbox2);

          //  anchorHbox2.getChildren().addAll(hBox2)
          // anchorHbox2.getChildren().addAll(HList);
          //  labelHbox2.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));



           /* labelHbox2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 1 ) {
                       labelHbox2.setText("-");
                        labelHbox2.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                    }
                }
            });*/

            System.out.println();
        //}




    }
    public void actionSave(ActionEvent actionEvent) {
        if (!(s1.getText().equals("0") || s1.getText().equals("1")) || !(t1.getText().equals("0") || t1.getText().equals("1")) || !(fr1.getText().equals("0") || fr1.getText().equals("1")) || !(f1.getText().equals("0") || f1.getText().equals("1"))
                || !(s2.getText().equals("0") || s2.getText().equals("1")) || !(t2.getText().equals("0") || t2.getText().equals("1")) || !(fr2.getText().equals("0") || fr2.getText().equals("1")) || !(f2.getText().equals("0") || f2.getText().equals("1"))
                || !(s3.getText().equals("0") || s3.getText().equals("1")) || !(t3.getText().equals("0") || t3.getText().equals("1")) || !(fr3.getText().equals("0") || fr3.getText().equals("1")) ||!(f3.getText().equals("0") || f3.getText().equals("1"))) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Проверьте введенные данные!(только 0 и 1)");
            alert.showAndWait();

        } else {
            list.get(0).setFirstEx(Integer.toString(Integer.parseInt(s1.getText()) + Integer.parseInt(t1.getText()) + Integer.parseInt(fr1.getText())));
            list.get(1).setFirstEx(Integer.toString(Integer.parseInt(f1.getText()) + Integer.parseInt(t2.getText()) + Integer.parseInt(fr2.getText())));
            list.get(2).setFirstEx(Integer.toString(Integer.parseInt(f2.getText()) + Integer.parseInt(s2.getText()) + Integer.parseInt(fr3.getText())));
            list.get(3).setFirstEx(Integer.toString(Integer.parseInt(f3.getText()) + Integer.parseInt(s3.getText()) + Integer.parseInt(t3.getText())));
        }
    }


}
