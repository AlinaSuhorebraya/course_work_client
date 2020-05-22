package sample.controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Organization.AlternativesForMarks;
import sample.action.FuncWithAtlernatives.AltActionImpl;
import sample.dao.alternatives.AlternativesDAO;
import sample.dao.DAOFactory;
import sample.Organization.AlternativesForMarksProperty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PosledSranvContr {

    @FXML
    private ChoiceBox choise1;
    @FXML
    private ChoiceBox choise2;
    @FXML
    private ChoiceBox choise3;
    @FXML
    private ChoiceBox choise4;
    @FXML
    private TextField text1;
    @FXML
    private TextField text2;
    @FXML
    private TextField text3;
    @FXML
    private TextField text4;
    @FXML
    private TextField textRez1;
    @FXML
    private TextField textRez2;
    @FXML
    private TextField textRez3;
    @FXML
    private TextField textRez4;

    private AltActionImpl alternativeAction;

    private ArrayList<String> name= new ArrayList<>();

    @FXML
    public void initialize(){

        name.clear();
        setDetailNamesInBox();
        setPromtAndEditable();

    }

    private void setPromtAndEditable() {
        text1.setPromptText("введите балл");
        text2.setPromptText("введите балл");
        text3.setPromptText("введите балл");
        text4.setPromptText("введите балл");

        textRez1.setPromptText("после нажатия ОК появятся результаты");
        textRez2.setPromptText("после нажатия ОК появятся результаты");
        textRez3.setPromptText("после нажатия ОК появятся результаты");
        textRez4.setPromptText("после нажатия ОК появятся результаты");

        textRez1.setEditable(false);
        textRez2.setEditable(false);
        textRez3.setEditable(false);
        textRez4.setEditable(false);
    }

    public void setDetailNamesInBox() {
        AlternativesDAO alternativeDAO = DAOFactory.getInstance().getAlternativeDAO();
        alternativeAction = new AltActionImpl(alternativeDAO);
//действия с уже подключенной бд
        Platform.runLater( () ->{//отдельный поток чтоюы не зависал интерфейс
            name.clear();
            if(alternativeAction.getObservableList().size()>0) {
                for (AlternativesForMarksProperty i : alternativeAction.getObservableList()) {
                    System.out.println(i);
                    name.add(i.getName());
                }

                choise1.getItems().setAll(name);
                choise2.getItems().setAll(name);
                choise3.getItems().setAll(name);
                choise4.getItems().setAll(name);
            }
            else{
                choise1.getItems().setAll("-");
                choise2.getItems().setAll("-");
                choise3.getItems().setAll("-");
                choise4.getItems().setAll("-");
            }
        });

    }


    public void ClearAll(ActionEvent actionEvent) {
        text1.clear();
        text2.clear();
        text3.clear();
        text4.clear();

        textRez1.clear();
        textRez2.clear();
        textRez3.clear();
        textRez4.clear();

        choise1.getSelectionModel().clearSelection();
        choise2.getSelectionModel().clearSelection();
        choise3.getSelectionModel().clearSelection();
        choise4.getSelectionModel().clearSelection();
    }



    public void Save(ActionEvent actionEvent) {

        if(     text1.getText().trim().equals("")||
                text2.getText().trim().equals("")||
                text3.getText().trim().equals("")||
                text4.getText().trim().equals("")
        )
        {
            showAllertMistake(Alert.AlertType.ERROR, "Выберите цели и введите баллы для всех ячеек!");//alert-oshibka

        }else {
            String firstName = (String) choise1.getSelectionModel().getSelectedItem();
            String secondName = (String) choise2.getSelectionModel().getSelectedItem();
            String thirdName = (String) choise3.getSelectionModel().getSelectedItem();
            String fourthName = (String) choise4.getSelectionModel().getSelectedItem();
            try {

                if (firstName.trim().equals("") || secondName.trim().equals("") || thirdName.trim().equals("") || fourthName.trim().equals("")) {
                    showAllertMistake(Alert.AlertType.ERROR, "Должно быть выбрано 4 альтенативы!");
                } else {

                    if (    firstName.trim().equals(secondName) || firstName.trim().equals(thirdName)  || firstName.trim().equals(fourthName) ||
                            secondName.trim().equals(firstName) || secondName.trim().equals(thirdName) || secondName.trim().equals(fourthName)||
                            thirdName.trim().equals(secondName) || thirdName.trim().equals(firstName)  || thirdName.trim().equals(fourthName) ||
                            fourthName.trim().equals(secondName)|| fourthName.trim().equals(thirdName) || fourthName.trim().equals(firstName)
                    ) {
                        showAllertMistake(Alert.AlertType.ERROR, "Выбор повторяющихся альтернатив недопустим!");
                    } else {


                        try {

                            System.out.println(text1.getText());
                            ///////////////////////
                            double firstValue = Double.parseDouble(text1.getText().trim());
                            double secondValue = Double.parseDouble(text2.getText().trim());//преобразуем текст в число
                            double thirdValue = Double.parseDouble(text3.getText().trim());
                            double fourthValue = Double.parseDouble(text4.getText().trim());
                            ///////////////////////
                            double SUM_OF_ALL_VALUES = (firstValue + secondValue + thirdValue + fourthValue);
                            ///////////////////////
                            double value23 = secondValue + thirdValue;
                            double value24 = secondValue + fourthValue;
                            double value34 = thirdValue + fourthValue;
                            ///////////////////////

                            setResult(firstValue, secondValue, thirdValue, fourthValue, value23, value24, value34);


                            if (firstValue <= value23 || firstValue <= value24 || firstValue <= value34 || secondValue <= value34) {
                                showAllertMistake(Alert.AlertType.ERROR, "Скорректируйте оценки!\nПроверьте правильность расстановки целей!");
                            } else {
                                ///////////////////////
                                double w1 = firstValue / SUM_OF_ALL_VALUES;
                                double w2 = secondValue / SUM_OF_ALL_VALUES;
                                double w3 = thirdValue / SUM_OF_ALL_VALUES;
                                double w4 = fourthValue / SUM_OF_ALL_VALUES;
                                Double MAX = new Double(0);
                                String finalInfo = new String();
                                Map<Double, String> map = new HashMap<Double, String>();//ключ/значение
                                ///////////////////////

                                ///////////////////////
                                workWithMap(w1, w2, w3, w4, map, firstName, secondName, thirdName, fourthName);
                                ///////////////////////

                                MAX = findMaxResult(w1, w2, w3, w4, MAX);


                                ///////////////////////
                                finalInfo = makeFinalInfoString(w1, w2, w3, w4, MAX, finalInfo, map, firstName, secondName, thirdName, fourthName);
                                ///////////////////////
                                showAllertMistake(Alert.AlertType.INFORMATION, finalInfo);
                                ///////////////////////
                            }
                        } catch (Exception ex) {
                            showAllertMistake(Alert.AlertType.ERROR, "Ввод букв запрещен!");
                            //ClearAll(actionEvent);
                        }
                    }
                }
            }
            catch(Exception ex){
                showAllertMistake(Alert.AlertType.ERROR, "Должно быть выбрано 4 альтенативы!");
                ClearAll(actionEvent);
            }
        }
    }

    private void showAllertMistake(Alert.AlertType error, String s) {
        Alert alert = new Alert(error, s);
        alert.showAndWait();
    }

    private void workWithMap(double w1, double w2, double w3, double w4, Map<Double, String> map, String firstName, String secondName, String thirdName, String fourthName) {
        map.put(w1,firstName);
        map.put(w2,secondName);
        map.put(w3,thirdName);
        map.put(w4,fourthName);
        map.entrySet().stream().sorted(Map.Entry.<Double, String>comparingByValue().reversed());//по убыванию или возр
        //forEach(System.out::println)
    }

    private String makeFinalInfoString(double w1, double w2, double w3, double w4, Double MAX, String finalInfo, Map<Double, String> map, String firstName, String secondName, String thirdName, String fourthName) {

        double LAST_SUM= w1+w2+w3+w4;
        finalInfo+="Результаты:\n\n";
        finalInfo += firstName;
        finalInfo+= " :  ";
        finalInfo +=w1;
        finalInfo +="\n";
        finalInfo += secondName;
        finalInfo+= " :  ";
        finalInfo +=w2;
        finalInfo +="\n";
        finalInfo +=thirdName;
        finalInfo+= " :  ";
        finalInfo +=w3;
        finalInfo +="\n";
        finalInfo += fourthName;
        finalInfo+= " :  ";
        finalInfo +=w4;
        finalInfo +="\n";
        finalInfo+="\n";
        finalInfo+="Самая выгодная перспектива:  " + map.getOrDefault(MAX,"none");
        finalInfo += ".\nЗначение: " + MAX;
        finalInfo+="\n";
        finalInfo+="\n  Итоговая сумма всех значений: " + LAST_SUM  + " .\n";
        return finalInfo;
    }

    private Double findMaxResult(double w1, double w2, double w3, double w4, Double max) {
        double[] mas = new double[5];
        mas[0] = w1;
        mas[1] = w2;
        mas[2] = w3;
        mas[3] = w4;
        max = mas[0];
        for (int i = 1; i < 4; i++) {
            if (max < mas[i]) {
                max = mas[i];
            }
        }
        System.out.println("МАКС " + max);
        return max;
    }

    private void setResult(double firstValue, double secondValue, double thirdValue, double fourthValue, double value23, double value24, double value34) {
        String textResult1 = firstValue + " > " + secondValue +" + " + thirdValue +  " = " + value23;
        String textResult2 = firstValue + " > " + secondValue +" + " + fourthValue + " = " + value24;
        String textResult3 = firstValue + " > " + thirdValue + " + " + thirdValue +  " = " + value34;
        String textResult4 = secondValue +" > " + thirdValue + " + " + fourthValue + " = " + value34;

        textRez1.setText(textResult1);
        textRez2.setText(textResult2);
        textRez3.setText(textResult3);
        textRez4.setText(textResult4);
    }


    public void Back(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource(); // из общего графа ищем родителя
        Stage stage2 = (Stage) source.getScene().getWindow(); //возвращемсятуда откуда вызыывались
        stage2.close();
    }
}
