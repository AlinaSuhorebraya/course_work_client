package sample.action.FuncWithAtlernatives;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.alternatives.AlternativesDAO;
import sample.Organization.AlternativesForMarks;
import sample.Organization.AlternativesForMarksProperty;

import java.util.ArrayList;
public class AltActionImpl implements AltAction {
    static ObservableList<AlternativesForMarksProperty> detailPropertiesList = FXCollections.observableArrayList();

    private AlternativesDAO alternativeDAO;


    public AltActionImpl(AlternativesDAO alternativeDAO){
        this.alternativeDAO = alternativeDAO;
        this.alternativeDAO.getList();
    }


    public  ObservableList<AlternativesForMarksProperty> getObservableList() { return this.detailPropertiesList; }


    //установка листа в таблицу
    public static void setAlternativeList(ArrayList<AlternativesForMarks> list){
        detailPropertiesList.clear();

        for(AlternativesForMarks c : list){

            AlternativesForMarksProperty alternativeProperty = new AlternativesForMarksProperty();

            alternativeProperty.setId(c.getId());
            alternativeProperty.setName(c.getName());
            alternativeProperty.setCountry(c.getCountry());
            alternativeProperty.setTypeOf(c.getTypeOf());
            alternativeProperty.setCost(c.getCost());
            alternativeProperty.setFirstEx(c.getFirstEx());
          //  alternativeProperty.setSecondEx(c.getSecondEx());
          //  alternativeProperty.setThirdEx(c.getThirdEx());
            detailPropertiesList.add(alternativeProperty);
        }
    }

    @Override
    public void add(AlternativesForMarksProperty alternativeProperty) {

        // проверку на ввод
        detailPropertiesList.add(alternativeProperty);
        alternativeDAO.insert(alternativeProperty);
    }

    @Override
    public void udpate(AlternativesForMarksProperty alternativeProperty) {
        alternativeDAO.update(alternativeProperty);
    }

    @Override
    public void delete(AlternativesForMarksProperty alternativeProperty) {
        detailPropertiesList.remove(alternativeProperty);
        alternativeDAO.delete(alternativeProperty);
    }

       /*public void fillTestData(){
            detailPropertiesList.add(new AlternativeProperty(1,"1",2,3,1,1,1,1));
        }*/

}
