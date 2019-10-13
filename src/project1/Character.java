package project1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//class used in query4 to output both an actor name and a movie they were in into a table
public class Character {
    private final SimpleStringProperty name;
    private final SimpleStringProperty movie;

    public Character() {
        this("","");
    }

    public Character(String name, String movie) {
        this.name = new SimpleStringProperty(name);
        this.movie = new SimpleStringProperty(movie);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String val) {
        name.set(val);
    }

    public String getMovie() {
        return movie.get();
    }

    public void setMovie(String val) {
        movie.set(val);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty movieProperty() {
        return movie;
    }
}