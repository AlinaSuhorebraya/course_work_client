package sample.dao.alternatives;

import sample.Organization.AlternativesForMarks;
import sample.Organization.AlternativesForMarksProperty;
import sample.connector.Connection;

import sample.SendMessage.SendMessageClass;
import sample.SendMessage.SendMessageClassImpl;

public class AlternativesDAOImpl implements AlternativesDAO
{
    public static AlternativesDAOImpl instance  = new AlternativesDAOImpl();
    public static AlternativesDAOImpl getInstance( ) { return instance; }
    private AlternativesDAOImpl() { }

    private Connection connection;

    public AlternativesDAOImpl(Connection connection) {
        this.connection = connection;
    }



    @Override
    public void insert(AlternativesForMarksProperty alternativesForMarksProperty) {
        System.out.println("Sending: "+ alternativesForMarksProperty);

        AlternativesForMarks alternative = new AlternativesForMarks();
        alternative.setId(alternativesForMarksProperty.getId());
        alternative.setName(alternativesForMarksProperty.getName());
        alternative.setCountry(alternativesForMarksProperty.getCountry());
        alternative.setTypeOf(alternativesForMarksProperty.getTypeOf());
        alternative.setCost(alternativesForMarksProperty.getCost());
        alternative.setFirstEx(alternativesForMarksProperty.getFirstEx());
       /* alternative.setSecondEx(alternativesForMarksProperty.getSecondEx());
        alternative.setThirdEx(alternativesForMarksProperty.getThirdEx());*/

        SendMessageClass msq= new SendMessageClassImpl(SendMessageClass.ADD_NEW_DETAIL,alternative);
        connection.send(msq);
    }
    @Override
    public void delete(AlternativesForMarksProperty alternativeProperty) {

        int id = alternativeProperty.getId();
        SendMessageClass msq = new SendMessageClassImpl(SendMessageClass.DELETE_DETAIL,id);
        connection.send(msq);
    }

    @Override
    public void update(AlternativesForMarksProperty alternativeProperty) {
        AlternativesForMarks alternative = new AlternativesForMarks();
        alternative.setId(alternativeProperty.getId());
        alternative.setName(alternativeProperty.getName().trim());
        alternative.setCountry(alternativeProperty.getCountry().trim());
        alternative.setTypeOf(alternativeProperty.getTypeOf().trim());
        alternative.setCost(alternativeProperty.getCost());
        alternative.setFirstEx(alternativeProperty.getFirstEx().trim());
       /* alternative.setSecondEx(alternativeProperty.getSecondEx().trim());
        alternative.setThirdEx(alternativeProperty.getThirdEx().trim());*/

        SendMessageClass msq = new SendMessageClassImpl(SendMessageClass.UDDATE_DETAIL, alternative);
        connection.send(msq);
    }

    @Override
    public void getList() {
        SendMessageClass mes = new SendMessageClassImpl(SendMessageClass.SHOW_ALL_DETAIL);
        connection.send(mes);
    }

}
