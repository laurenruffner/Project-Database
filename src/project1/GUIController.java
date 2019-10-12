package project1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.*;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import project1.antlr4.MyRulesBaseListener;
import project1.antlr4.RulesLexer;
import project1.antlr4.RulesParser;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class GUIController implements Initializable{
    @FXML
    private TextField actorG;
    @FXML
    private Text genre;

    @FXML
    private TextField charNameR;
    @FXML
    private Text charName;
    @FXML
    private TableView <Character> tableView;
    @FXML
    private TableColumn <Character, String> actorName;
    @FXML
    private TableColumn <Character, String> movieName;

    @FXML
    private TextField actorR;
    @FXML
    private Text director;
    @FXML
    private Text worstMovie;


    @FXML
    private void mostCommonGenre(){
        String actor = actorG.getText();
        actorG.clear();

        genre.setText(actor);
    }

    public Table query4(String character) throws Exception{
        String character_name = character.replaceAll("[^\\p{ASCII}]", "").replaceAll(" '\\.\\*'", "")
                .replace(" ", "_").replace(".", "").replace("'", "");

        File file = new File("src/Files/input_query4.txt");

        String fileContent = "OPEN movies;\n" +
                "OPEN cast;\n" +
                "actors <- select (Character == \"" + character_name + "\") cast;\n" +
                "actor_and_movies <- select (M_ID == Movie_ID) (movies * actors);\n" +
                "movie_actor <- project (Title, Name) actor_and_movies;";

        FileWriter fileWriter = new FileWriter("src/Files/input_query4.txt");
        fileWriter.write(fileContent);
        fileWriter.close();

        File query_4_file = new File("src/Files/input_query4.txt");
        Scanner scanner = new Scanner(query_4_file);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.length() != 0) { lines.add(line); }
        }
        MyRulesBaseListener listener = new MyRulesBaseListener();
        for (String line : lines) {
            CharStream charStream = CharStreams.fromString(line);
            RulesLexer lexer = new RulesLexer(charStream);
            CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
            RulesParser parser = new RulesParser(commonTokenStream);
            lexer.removeErrorListeners();
            parser.removeErrorListeners();
            RulesParser.ProgramContext programContext = parser.program();
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(listener, programContext);
        }

        Table output = listener.myDbms.table_list.get(listener.myDbms.indexOfTable("movie_actor"));
        return output;
    }

    @FXML
    private void fillCharTable(){
        charName.setText(charNameR.getText());
        String character = charNameR.getText();
        charNameR.clear();
        tableView.getItems().clear();

        try {
            Table output = query4(character);
            String _name;
            String _movie;
            for (int i = 0; i < output.getNumberofRows(); ++i) { //go through table
                _name = output.getData(1,i);
                _name = _name.replace("_"," ");
                _movie = output.getData(0,i);
                _movie = _movie.replace("_"," ");
                tableView.getItems().add(new Character(_name,_movie));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    public void directorWorstMovie() {
        String direct = "Temp" + "'s";
        String actor = actorR.getText();
        actorR.clear();

        director.setText(direct);
        worstMovie.setText(actor);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actorName.setCellValueFactory(new PropertyValueFactory<Character, String>("name"));
        movieName.setCellValueFactory(new PropertyValueFactory<Character, String>("movie"));
    }
}

