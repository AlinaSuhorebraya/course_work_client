package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Organization.AlternativesForMarks;
import sample.Organization.AlternativesForMarksProperty;

public class DialogAlternativesController {



    @FXML
    private TextField textName;
    @FXML
    private ChoiceBox<String> choiseEx1;
    @FXML
    private ChoiceBox<String>  choiseEx2;
    @FXML
    private ChoiceBox<String>  choiseEx3;
    @FXML
    private TextField  textCountry;
    @FXML
    private TextField textTypeOf;
    @FXML
    private TextField  textCost;

    private AlternativesForMarksProperty alternativeProperty;


    @FXML
    void initialize()  {

        textTypeOf.setPromptText("введите тип постройки");
        textCost.setPromptText("введите стоимость");
        textCountry.setPromptText("введите страну заказчика");
    }


    public AlternativesForMarksProperty getAlternativeProperty() {
        return this.alternativeProperty;
    }

    public void p(){
        choiseEx1.setValue("-");

    }

    public void setAlternativeProperty(AlternativesForMarksProperty alternativeProperty){

        if(alternativeProperty == null){
            return;
        }
        this.alternativeProperty = alternativeProperty;

        choiseEx1.setValue(alternativeProperty.getFirstEx());
       /* choiseEx2.setValue(alternativeProperty.getSecondEx());
        choiseEx3.setValue(alternativeProperty.getThirdEx());*/

        textName.setText(alternativeProperty.getName());
        textCountry.setText(String.valueOf((alternativeProperty.getCountry())));
        textTypeOf.setText(String.valueOf((alternativeProperty.getTypeOf())));
        textCost.setText(String.valueOf((alternativeProperty.getCost())));


    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource(); // из общего графа ищем родителя
        Stage stage = (Stage) source.getScene().getWindow(); //возвращемсятуда откуда вызыывались
        stage.hide();
        choiseEx1.setValue("-");

    }

    public void actionSave(ActionEvent actionEvent){


        try {


            if(
                    textCountry.getText().equals("")||
                            textTypeOf.getText().equals("")||
                            Double.parseDouble(textCost.getText())<0||
                            textName.getText().equals("")
            ){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Вы ввели число меньше нуля, либо букву!\nТакже вы могли не ввести текст в какое либо поле!");
                alert.showAndWait();
            }else {
                alternativeProperty.setFirstEx(String.valueOf(choiseEx1.getValue()));
              /*  alternativeProperty.setSecondEx(String.valueOf(choiseEx2.getValue()));
                alternativeProperty.setThirdEx(String.valueOf(choiseEx3.getValue()));*/
                alternativeProperty.setName(textName.getText());
                alternativeProperty.setCountry(textCountry.getText());
                alternativeProperty.setTypeOf((textTypeOf.getText()));
                alternativeProperty.setCost(Double.parseDouble(textCost.getText()));
                actionClose(actionEvent);
            }

        }
        catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Вы ввели  букву вместо числа!");
            alert.showAndWait();
        }


    }


}
