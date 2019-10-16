package project1;

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
    //query3
    @FXML
    private TextField actorG;
    @FXML
    private Text actor_name;
    @FXML
    private Text genre;
    //query4
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
    //query5
    @FXML
    private Text actor;
    @FXML
    private TextField actorR;
    @FXML
    private Text bestMovie;
    @FXML
    private Text director;
    @FXML
    private Text director2;
    @FXML
    private Text worstMovie;


    private String query3(String actorName) throws Exception{
        String actor_name = actorName.replaceAll("[^\\p{ASCII}]", "").replaceAll(" '\\.\\*'", "")
                .replace(" ", "_").replace(".", "").replace("'", "").replace("-", "_");

        File file = new File("src/Files/input_query3.txt");

        String fileContent = "OPEN movies;\n" +
                "OPEN cast;\n" +
                "actor <- select (Name == \"" + actor_name + "\") cast;\n" + //table of all of te instances of this actor
                "actor_and_movies <- select (M_ID == Movie_ID) (movies * actor)\n" +//table where ids are the same
                "actors_genres <- project (M_ID, Genre1, Genre2, Genre3) actor_and_movies;"; //table of genres for actor

        FileWriter fileWriter = new FileWriter("src/Files/input_query3.txt");
        fileWriter.write(fileContent);
        fileWriter.close();

        File query_five_file = new File("src/Files/input_query3.txt");
        Scanner scanner = new Scanner(query_five_file);
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

        Table output = listener.myDbms.table_list.get(listener.myDbms.indexOfTable("actors_genres"));
        //output.printTable();
        output.remove_duplicates();
        //output.printTable();
        ArrayList<String> genres = new ArrayList<String>();
        ArrayList<Integer> gCount = new ArrayList<Integer>();
        int nullamt = 0;
        //output.printTable();
        // nested for loops to look at each genre in the table
        for(int j = 1; j < 4; j++) {
            for (int i = 0; i < output.table.get(j).size(); i++) {
                String genre = (String)output.table.get(j).get(i);
                int x = genres.indexOf(genre);
                // if genre is NULL do nothing
                if(genre.compareTo("NULL") == 0) {
                    nullamt++;
                }
                // if genre is in the list we increase the amount by 1
                else if(x != -1){
                    int inc = gCount.get(x) + 1;
                    gCount.set(x, inc);
                }
                // if genre is not already in the list of genres
                else{
                    genres.add(genre);
                    gCount.add(1);
                }
            }
        }
        // sets first genre to max
        int maxGenre = gCount.get(0);
        int maxInd = 0;
        // finds max in amount list
        int k;
        for(k = 0; k < gCount.size(); k++){
            if(maxGenre < gCount.get(k)){
                maxInd = k;
                maxGenre = gCount.get(k);
            }
        }
        // gets genre name from max in amount list
        String genre = genres.get(maxInd);
        //System.out.println("Null amt: " + nullamt);
        //System.out.println(Actor_Name_Q3 + "'s appears most in movies of genre: " + genre);
        //System.out.println("Amount: " + maxGenre);
        return genre;
    }

    //query3
    @FXML
    private void mostCommonGenre(){
        String actor = actorG.getText(); //stores user input
        actorG.clear();

        String mostCommonGenre = "";
        try {
            mostCommonGenre = query3(actor); //uses user input in query3 function to get most common genre
            mostCommonGenre = mostCommonGenre.replace("_"," "); //add spaces back in
            actor_name.setText(actor + "'s");
            genre.setText(mostCommonGenre.toLowerCase() + ".");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public Table query4(String character) throws Exception{
        String character_name = character.replaceAll("[^\\p{ASCII}]", "").replaceAll(" '\\.\\*'", "")
                .replace(" ", "_").replace(".", "").replace("'", "").replace("-", "_")
                .replace("#", "").replace("\"", "");

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

        Table output = listener.myDbms.table_list.get(listener.myDbms.indexOfTable("movie_actor")); //entries of table have movie and an actor in that movie
        return output;
    }

    //query4
    @FXML
    private void fillCharTable(){
        String character = charNameR.getText(); //stores user input
        charNameR.clear();
        tableView.getItems().clear(); //clears table
        charName.setText("");

        try {
            Table output = query4(character); //receives table with movies and actors that have played user inputted character
            String _name;
            String _movie;
            for (int i = 0; i < output.getNumberofRows(); ++i) { //go through table
                _name = output.getData(1,i);
                _name = _name.replace("_"," "); //add spaces back in
                _movie = output.getData(0,i);
                _movie = _movie.replace("_"," "); //add spaces back in
                tableView.getItems().add(new Character(_name,_movie)); //add entry to GUI table
            }
            charName.setText(character);
            tableView.sort(); //sorts table based on (first) name
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public ArrayList<String> query5(String actor) throws Exception{
        String actor_name = actor.replaceAll("[^\\p{ASCII}]", "").replaceAll(" '\\.\\*'", "")
                .replace(" ", "_").replace(".", "").replace("'", "").replace("-", "_");

        File file = new File("src/Files/input_query5.txt");
        String fileContent = "OPEN movies;\n" +
                "OPEN cast;\n" +
                "OPEN crew;\n" +
                "actor <- select (Name == \"" + actor_name + "\") cast;\n" + //table of all of te instances of this actor
                "actor_and_movies <- select (M_ID == Movie_ID) (movies * actor)\n" +//table where ids are the same
                "actors_movies <- project (Title, M_ID, Rating) actor_and_movies;"; //table of movies for actor

        FileWriter fileWriter = new FileWriter("src/Files/input_query5.txt");
        fileWriter.write(fileContent);
        fileWriter.close();

        File query_five_file = new File("src/Files/input_query5.txt");
        Scanner scanner = new Scanner(query_five_file);
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

        Table output = listener.myDbms.table_list.get(listener.myDbms.indexOfTable("actors_movies"));
        //output.printTable();
        Table crew = listener.myDbms.table_list.get(listener.myDbms.indexOfTable("crew"));
        Table movies = listener.myDbms.table_list.get(listener.myDbms.indexOfTable("movies"));

        int highestRatedMovieID = (int)output.table.get(1).get(0);
        int highest_rank = (int) output.table.get(2).get(0);
        String highestRatedMovieName = (String)output.table.get(0).get(0);
        //finds the highest rated movie in the actorMovie table
        //IF TWO MOVIES ARE THE HIGHEST RATED, THE LAST ONE IN THE TABLE IS USED
        for(int i = 0; i < output.table.get(0).size() - 1; i++) {
            if ((int)output.table.get(2).get(i + 1) > highest_rank) {
                highestRatedMovieID = (int)output.table.get(1).get(i + 1);
                highestRatedMovieName = (String)output.table.get(0).get(i + 1);
                highest_rank = (int)output.table.get(2).get(i + 1);
            }
        }

        // System.out.println("Highest Rated Movie ID: " + highestRatedMovieID);

        //gets id of director
        int directorID = -1;
        for(int i = 0; i < crew.table.get(0).size(); i++) {
            String job = (String)crew.table.get(4).get(i);
            if(((int)crew.table.get(0).get(i) == (int)highestRatedMovieID) && (job.compareTo("Director") == 0)) {
                directorID = (int)crew.table.get(1).get(i);
            }
        }

        //System.out.println("Director of Highest Rated Movie: " + directorID);

        Table director = new Table("director");
        director.enterColumns(0, "Movie_ID","INTEGER");
        director.enterColumns(1,"ID", "INTEGER");
        director.enterColumns(2, "Job", "VARCHAR");
        director.enterColumns(3, "Name", "VARCHAR");

        //gets table of all instances of director
        for(int i = 0; i < crew.table.get(0).size(); i++) {
            int crewID = (int)crew.table.get(1).get(i);
            String job = (String)crew.table.get(4).get(i);
            if(crewID == directorID && (job.compareTo("Director") == 0)) {
                director.insertData(0, Integer.toString((int)crew.table.get(0).get(i)), true);
                director.insertData(1, Integer.toString((int)crew.table.get(1).get(i)), true);
                director.insertData(2, (String)crew.table.get(4).get(i), false);
                director.insertData(3, (String)crew.table.get(2).get(i), false);
            }
        }
        //director.printTable();

        //gets table of movies for each instance of director
        Table directorMovies = new Table("directorMovies");
        directorMovies.enterColumns(0, "M_ID","INTEGER");
        directorMovies.enterColumns(1,"Title", "VARCHAR");
        directorMovies.enterColumns(2, "Rating", "INTEGER");

        for(int j = 0; j < movies.table.get(0).size(); j++) {
            for (int i = 0; i < director.table.get(0).size(); i++) {
                if ((int) movies.table.get(0).get(j) == (int) director.table.get(0).get(i)) {
                    directorMovies.insertData(0, Integer.toString((int) movies.table.get(0).get(j)), true);
                    directorMovies.insertData(1, (String) movies.table.get(1).get(j), false);
                    directorMovies.insertData(2, Integer.toString((int) movies.table.get(7).get(j)), true);
                }
            }
        }

        //directorMovies.printTable();

        //finds worst ranked movie
        int worst_rank = (int)directorMovies.table.get(2).get(0);
        String worstRankedMovie = (String)directorMovies.table.get(1).get(0);
        for(int i = 0; i < directorMovies.table.get(0).size() - 1; i++) {
            if ((int)directorMovies.table.get(2).get(i + 1) < worst_rank) {
                worstRankedMovie = (String)directorMovies.table.get(1).get(i +1);
                worst_rank = (int)directorMovies.table.get(2).get(i + 1);
            }
        }
        //directorMovies.printTable();
        ArrayList<String> q5 = new ArrayList<String>();
        q5.add(highestRatedMovieName);
        q5.add((String)director.table.get(3).get(0));
        q5.add(worstRankedMovie);
        //System.out.println(actor + "'s highest rated movie is " + highestRatedMovieName + ", directed by " + (String)director.table.get(3).get(0) + ".");
        //System.out.println("The worst ranked movie directed by " + (String)director.table.get(3).get(0) + " is " + worstRankedMovie +".");
        return q5;
    }

    //query5
    @FXML
    public void directorWorstMovie() {
        String actorN = actorR.getText(); //stores user input
        actorR.clear();

        ArrayList<String> q5;
        String direct = "";
        String best = "";
        String worst = "";
        try {
            q5 = query5(actorN); //receives arraylist that contains highest rated movie, director, and worst rated movie (in that order)
            best = q5.get(0);
            best = best.replace("_"," "); //add spaces back in
            direct = q5.get(1);
            direct = direct.replace("_"," "); //add spaces back in
            worst = q5.get(2);
            worst = worst.replace("_"," "); //add spaces back in
            actor.setText(actorN + "'s");
            bestMovie.setText(best);
            director.setText(direct + ".");
            director2.setText(direct);
            worstMovie.setText(worst + ".");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set up GUI table columns
        actorName.setCellValueFactory(new PropertyValueFactory<Character, String>("name"));
        movieName.setCellValueFactory(new PropertyValueFactory<Character, String>("movie"));
        tableView.getSortOrder().add(actorName); //sort by actor (first) name
    }
}

