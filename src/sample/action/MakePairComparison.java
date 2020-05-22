package sample.action;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import sample.Organization.AlternativesForMarks;
import sample.Organization.AlternativesForMarksProperty;

public class MakePairComparison
{
    public MakePairComparison() { }
    ///////////////
    private String finalInfo ;
    private double[] finalWeigth;
    private double[] weight;
    private double[][] mas;
    private int normalizationFactor = 0;
    private int place = 0;
    private double maxWeight = 0;

    public void makeDecision(ObservableList<AlternativesForMarksProperty> list){
        finalInfo = new String();
        mas = new double[list.size()][1];
        weight = new double[list.size()]; //вес каждый цели
        finalWeigth = new double[list.size()];
//////////////////////////////////////////////////////////
        //получаем оценки эксертов
        normalizationFactor = getExspertMarks(list, mas, weight, normalizationFactor);
        //получаем финальный вес с учетом нормирующего коэфа
        getFinalWeight(list, weight, finalWeigth, normalizationFactor);
        ///ищем максимальный результат и определяем имя цели
        findMaxWeight();
        //собираемитоговую информацию
        MakeInfoString(list, finalWeigth, place, maxWeight);
        //показывем алерт с итогом
        ShowAlert();

    }

    private void findMaxWeight() {
        maxWeight = finalWeigth[0];

        for (int n = 1; n < finalWeigth.length; n++) {
            if (maxWeight < finalWeigth[n]) {
                maxWeight = finalWeigth[n];
                place = n;
            }
        }
    }

    private void ShowAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(finalInfo);
        alert.showAndWait();
    }

    private void MakeInfoString(ObservableList<AlternativesForMarksProperty> list, double[] finalWeigth, int mesto, double max) {
        finalInfo += "Результаты:\n";
        for(int h=0;h<finalWeigth.length;h++){
            finalInfo +="Название: ";
            finalInfo += list.get(h).getName() ;
            finalInfo +=". Результат: ";
            finalInfo += finalWeigth[h];
            finalInfo +=".\n";
            System.out.println(finalInfo);
        }
        finalInfo+="\n";
        finalInfo +="Наиболее выгодная перспектива:\n";
        finalInfo +="Название: ";
        finalInfo += list.get(mesto).getName() ;
        finalInfo += ". Значение: " + max;
        finalInfo+="\n";
    }

    private void getFinalWeight(ObservableList<AlternativesForMarksProperty> list, double[] weight, double[] finalWeigth, int normCoef) {
        for(int i=0;i<list.size();i++){
            finalWeigth[i] = (double) weight[i]/normCoef;
        }
    }

    private int getExspertMarks(ObservableList<AlternativesForMarksProperty> list, double[][] mas, double[] weight, int normCoef) {

        for(int i=0;i<list.size();i++){
            if(list.get(i).getFirstEx().trim().equals("-")){
                mas[i][0]=0;
            }else{
                mas[i][0]=Integer.valueOf(list.get(i).getFirstEx().trim());
            }
           /* if(list.get(i).getSecondEx().trim().equals("-")){
                mas[i][1]=0;
            }
            else{
                mas[i][1]=Integer.valueOf(list.get(i).getSecondEx().trim());
            }
            if(list.get(i).getThirdEx().trim().equals("-")){
                mas[i][2]=0;
            }
            else {
                mas[i][2] = Integer.valueOf(list.get(i).getThirdEx().trim());
            }*/
            weight[i] = mas[i][0]/*+ mas[i][1]+mas[i][2]*/;
            normCoef+=weight[i];
        }
        return normCoef;
    }
}
