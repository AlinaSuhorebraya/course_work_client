package sample.Organization;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class AlternativesForMarksProperty
{
    private SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private transient SimpleStringProperty name = new SimpleStringProperty("");
    private transient SimpleStringProperty country =  new SimpleStringProperty("");
    private transient SimpleDoubleProperty cost =  new SimpleDoubleProperty(0);
    private transient SimpleStringProperty typeOf =  new SimpleStringProperty("");
    private transient SimpleStringProperty firstEx =  new SimpleStringProperty("");
  //  private transient SimpleStringProperty secondEx =  new SimpleStringProperty("");
  //  private transient SimpleStringProperty thirdEx =  new SimpleStringProperty("");
    public AlternativesForMarksProperty() { }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getCountry() {
        return country.get();
    }

    public SimpleStringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public double getCost() {
        return cost.get();
    }

    public SimpleDoubleProperty costProperty() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost.set(cost);
    }

    public String getTypeOf() {
        return typeOf.get();
    }

    public SimpleStringProperty typeOfProperty() {
        return typeOf;
    }

    public void setTypeOf(String typeOf) {
        this.typeOf.set(typeOf);
    }

    public String getFirstEx() {
        return firstEx.get();
    }

    public SimpleStringProperty firstExProperty() {
        return firstEx;
    }

    public void setFirstEx(String firstEx) {
        this.firstEx.set(firstEx);
    }

  /*  public String getSecondEx() {
        return secondEx.get();
    }

    public SimpleStringProperty secondExProperty() {
        return secondEx;
    }

    public void setSecondEx(String secondEx) {
        this.secondEx.set(secondEx);
    }

    public String getThirdEx() {
        return thirdEx.get();
    }

    public SimpleStringProperty thirdExProperty() {
        return thirdEx;
    }

    public void setThirdEx(String thirdEx) {
        this.thirdEx.set(thirdEx);
    }
*/
    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public SimpleStringProperty getPropertyName() { return this.name;}
    public SimpleStringProperty getPropertyFirst() { return this.firstEx;}
   /* public SimpleStringProperty getPropertySecond() { return this.secondEx;}
    public SimpleStringProperty getPropertyThird() { return this.thirdEx;}*/
    public SimpleStringProperty getPropertyCountry() { return this.country;}
    public SimpleStringProperty getPropertyRange() { return this.typeOf;}
    public SimpleDoubleProperty getPropertyCost() { return this.cost;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlternativesForMarksProperty that = (AlternativesForMarksProperty) o;
        return name.equals(that.name) &&
                country.equals(that.country) &&
                cost.equals(that.cost) &&
                typeOf.equals(that.typeOf) &&
                firstEx.equals(that.firstEx);
              /*  secondEx.equals(that.secondEx) &&
                thirdEx.equals(that.thirdEx);*/
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, cost, typeOf, firstEx/*, secondEx, thirdEx*/);
    }
}
