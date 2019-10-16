package project1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//class used in query2 to output a costar name into a table
public class Costar {
    private final SimpleStringProperty name;

    public Costar(SimpleStringProperty name) {
        this.name = name;
    }

    public Costar(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String val) {
        name.set(val);
    }

    public StringProperty nameProperty() {
        return name;
    }

}