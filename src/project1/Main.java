package project1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import csce315.project1.*;
import project1.antlr4.MyRulesBaseListener;
import project1.antlr4.RulesLexer;
import project1.antlr4.RulesParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("Movie Database GUI");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        }


    public static void main(String[] args) throws IOException {
        MovieDatabaseParser movie_parser = new MovieDatabaseParser();

        //MAKE SURE TO ADD JSON FILES TO THE FILES FOLDER
        List<Movie> moviesList = movie_parser.deserializeMovies("src/Files/movies.json");
        List<Credits> creditsList = movie_parser.deserializeCredits("src/Files/credits.json");

        //movie table
        Table movies = new Table("movies");
        movies.enterColumns(0, "M_ID","INTEGER");
        movies.enterColumns(1,"Title", "VARCHAR");
        movies.enterColumns(2, "Release_Date", "INTEGER");
        movies.enterColumns(3, "Genre1", "VARCHAR");
        movies.enterColumns(4, "Genre2", "VARCHAR");
        movies.enterColumns(5, "Genre3", "VARCHAR");
        movies.enterColumns(6,"Runtime", "INTEGER");
        movies.enterColumns(7, "Rating", "INTEGER");
        movies.enterColumns(8, "Budget", "INTEGER");
        movies.enterColumns(9,"Revenue", "INTEGER");
        movies.enterColumns(10, "Original_Language", "VARCHAR");

        //cast table
        Table cast = new Table("cast");
        cast.enterColumns(0, "Movie_ID", "INTEGER");
        cast.enterColumns(1, "ID", "INTEGER");
        cast.enterColumns(2, "Name", "VARCHAR");
        cast.enterColumns(3, "Character", "VARCHAR");

        //crew table
        Table crew = new Table("crew");
        crew.enterColumns(0, "Movie_ID", "INTEGER");
        crew.enterColumns(1, "ID", "INTEGER");
        crew.enterColumns(2, "Name", "VARCHAR");
        crew.enterColumns(3, "Department", "VARCHAR");
        crew.enterColumns(4, "Job", "VARCHAR");

        for (int i=0; i < moviesList.size(); i++){
            movies.insertData(0, Integer.toString(moviesList.get(i).getId()), true);

            //Title
            String string = Normalizer.normalize(moviesList.get(i).getTitle(), Normalizer.Form.NFD);
            String title = string.replaceAll("[^\\p{ASCII}]", "");

            try {
                Integer.parseInt(title);
                title = title + "_";
                title = title.replace("\"", "")
                        .replace(",", "").replace("#","").replace("&", "and")
                        .replace(":", "").replace(" (voice)" , "").replace(" (uncredited)", "")
                        .replace("'", "").replace("!", "").replace(".", "")
                        .replace("%", " percent").replace("·","").replace(" -","")
                        .replace("-","").replace("?", "").replaceAll(" '\\.\\*'","")
                        .replace(" ", "_").replace("@", "at");

            } catch(NumberFormatException e){
                title = title.replace("\"", "")
                        .replace(",", "").replace("#","").replace("&", "and")
                        .replace(":", "").replace(" (voice)" , "").replace(" (uncredited)", "")
                        .replace("'", "").replace("!", "").replace(".", "")
                        .replace("%", " percent").replace("·","").replace(" -","")
                        .replace("-","").replace("?", "").replaceAll(" '\\.\\*'","")
                        .replace(" ", "_").replace("@", "at");

            }

             title = title.replace("\"", "")
                    .replace(",", "").replace("#","").replace("&", "and")
                    .replace(":", "").replace(" (voice)" , "").replace(" (uncredited)", "")
                    .replace("'", "").replace("!", "").replace(".", "")
                    .replace("%", " percent").replace("·","").replace(" -","")
                    .replace("-","").replace("?", "").replaceAll(" '\\.\\*'","")
                    .replace(" ", "_").replace("@", "at");

            if (title.equals("_")){
                movies.insertData(1, "TITLE_NOT_IN_ALPHA_FORMAT", false);
            }
            else {
                movies.insertData(1, title, false);
            }

            //release date
            String date = moviesList.get(i).getRelease_date().replace("-", "");
            if (date.compareTo("") == 0 ){
                movies.insertData(2, "-1", true);
            }
            else {
                movies.insertData(2, date, true);
            }

            //Genres
            if (moviesList.get(i).getGenres().size() == 3){
                movies.insertData(3, moviesList.get(i).getGenres().get(0).getName().replace(" ", "_"), false);
                movies.insertData(4, moviesList.get(i).getGenres().get(1).getName().replace(" ", "_"), false);
                movies.insertData(5, moviesList.get(i).getGenres().get(2).getName().replace(" ", "_"), false);
            }
            else if(moviesList.get(i).getGenres().size() == 2){
                movies.insertData(3, moviesList.get(i).getGenres().get(0).getName().replace(" ", "_"), false);
                movies.insertData(4, moviesList.get(i).getGenres().get(1).getName().replace(" ", "_"), false);
                movies.insertData(5, "NULL", false);
            }
            else if(moviesList.get(i).getGenres().size() == 1){
                movies.insertData(3, moviesList.get(i).getGenres().get(0).getName().replace(" ", "_"), false);
                movies.insertData(4, "NULL", false);
                movies.insertData(5, "NULL", false);
            }
            else{
                movies.insertData(3, "NULL", false);
                movies.insertData(4, "NULL", false);
                movies.insertData(5, "NULL", false);

            }
            //Runtime
            movies.insertData(6, Integer.toString(moviesList.get(i).getRuntime()), true);
            //Rating
            movies.insertData(7, Integer.toString((int) (moviesList.get(i).getVote_average()*10)), true);
            //Budget
            movies.insertData(8, Integer.toString(moviesList.get(i).getBudget()), true);
            //Revenue
            movies.insertData(9, Integer.toString((int)moviesList.get(i).getRevenue()), true);
            //Original Language
            movies.insertData(10, moviesList.get(i).getOriginal_language(), false);

            for (int l = 0; l < creditsList.get(i).getCastMember().size(); l++){
                String characters = creditsList.get(i).getCastMember().get(l).getCharacter().replace("|", "/");
                String[] arrofCharacters = characters.split(" / ", -2);
                for (String string_string : arrofCharacters) {
                    //movie id
                    String[] arrofCharacters2 = string_string.split("/", -2);
                    for (String charact : arrofCharacters2){
                        cast.insertData(0, creditsList.get(i).getId(), true);

                        //ID
                        cast.insertData(1, Integer.toString(creditsList.get(i).getCastMember().get(l).getId()), true);

                        //Name
                        String name = Normalizer.normalize(creditsList.get(i).getCastMember().get(l).getName(), Normalizer.Form.NFD);
                        name = name.replaceAll("[^\\p{ASCII}]", "").replaceAll(" '\\.\\*'", "")
                                .replace(" ", "_").replace(".", "").replace("'", "").replace("-", "_");
                        cast.insertData(2, name, false);

                        //Character
                        if (charact.compareTo("") == 0) {
                            cast.insertData(3, "NULL", false);
                        } else {
                            //Remove after Comma
                            if (charact.contains(", Jr")) {
                                charact = charact.replace(",", "");
                            } else {
                                charact = charact.split(",")[0].split(" \\(")[0];
                            }

                            charact = Normalizer.normalize(charact, Normalizer.Form.NFD);
                            charact = charact.replaceAll("[^\\p{ASCII}]", "").replaceAll(" '\\.\\*'", "")
                                    .replace(" ", "_").replace(".", "").replace("'", "")
                                    .replace("-", "_").replace("#", "");

                            cast.insertData(3, charact, false);
                        }
                    }
                }
            }
            for (int m=0; m < creditsList.get(i).getCrewMember().size(); m++){
                //movie id
                crew.insertData(0, creditsList.get(i).getId(), true);
                //ID
                crew.insertData(1, Integer.toString(creditsList.get(i).getCrewMember().get(m).getId()), true);
                //Name
                String crew_name = Normalizer.normalize(creditsList.get(i).getCrewMember().get(m).getName(), Normalizer.Form.NFD);
                crew_name = crew_name.replaceAll("[^\\p{ASCII}]", "").replaceAll(" '\\.\\*'","")
                        .replace(" ", "_").replace(".", "").replace("'", "").replace("-", "_");
                crew.insertData(2, crew_name, false);
                //Department
                String depart_name = Normalizer.normalize(creditsList.get(i).getCrewMember().get(m).getDepartment(), Normalizer.Form.NFD);
                depart_name = depart_name.replaceAll("[^\\p{ASCII}]", "").replaceAll(" '\\.\\*'","")
                        .replace("-", "_").replace(" ", "_").replace(".", "").replace("'", "").replace("&", "and");
                crew.insertData(3, depart_name, false);
                //Job
                String job = Normalizer.normalize(creditsList.get(i).getCrewMember().get(m).getJob(), Normalizer.Form.NFD);
                job = job.replaceAll("[^\\p{ASCII}]", "").replaceAll(" '\\.\\*'","")
                        .replace(" ", "_").replace(".", "").replace("'", "").replace("&", "and")
                        .replace("#", "").replace("-", "_");
                crew.insertData(4, job, false);
            }
        }

        cast.table_to_file();
        movies.table_to_file();
        crew.table_to_file();


        launch(args); //launch GUI


        //for quick testing
        boolean query3 = false;
        boolean query4 = false;
        boolean query5 = false;

        String Actor_Name_Q3 = "Tom Hanks";
        String Character_Name_Q4 = "Child";
        String Actor_Name_Q5 = "Kevin Bacon";

        // QUERY3
        if(query3){
            String actor_name = Actor_Name_Q3.replaceAll("[^\\p{ASCII}]", "").replaceAll(" '\\.\\*'", "")
                    .replace(" ", "_").replace(".", "").replace("'", "").replace("-", "_");

            File file = new File("src/Files/input_query3.txt");

            String fileContent = "OPEN movies;\n" +
                    "OPEN cast;\n" +
                    "actor <- select (Name == \"" + actor_name + "\") cast;\n" + //table of all of te instances of this actor
                    "actor_and_movies <- select (M_ID == Movie_ID) (movies * actor)\n" +//table where ids are the same
                    "actors_genres <- project (Genre1, Genre2, Genre3) actor_and_movies;"; //table of genres for actor

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
            ArrayList<String> genres = new ArrayList<String>();
            ArrayList<Integer> gCount = new ArrayList<Integer>();
            int nullamt = 0;
            //output.printTable();
            // nested for loops to look at each genre in the table
            for(int j = 0; j < 3; j++) {
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
            // Finds max in amount list
            int k;
            for(k = 0; k < gCount.size(); k++){
                if(maxGenre < gCount.get(k)){
                    maxInd = k;
                    maxGenre = gCount.get(k);
                }
            }
            // Gets genre name from max in amount list
            String genre = genres.get(maxInd);
            //System.out.println("Null amt: " + nullamt);
            System.out.println(Actor_Name_Q3 + "'s appears most in movies of genre: " + genre);
            //System.out.println("Amount: " + maxGenre);
        }
        //QUERY4
        else if(query4){

            String character_name = Character_Name_Q4.replaceAll("[^\\p{ASCII}]", "").replaceAll(" '\\.\\*'", "")
                    .replace(" ", "_").replace(".", "").replace("'", "")
                    .replace("-", "_");

            File file = new File("src/Files/input_query4.txt");

            String fileContent = "OPEN movies;\n" +
                    "OPEN cast;\n" +
                    "OPEN crew;\n" +
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
            output.printTable();

        }
        //QUERY5
        else if(query5){
            String actor_name = Actor_Name_Q5.replaceAll("[^\\p{ASCII}]", "").replaceAll(" '\\.\\*'", "")
                    .replace(" ", "_").replace(".", "").replace("'", "").replace("-", "_");

            File file = new File("src/Files/input_query5.txt");
            String fileContent = "OPEN movies;\n" +
                    "OPEN cast;\n" +
                    "actor <- select (Name == \"" + actor_name + "\") cast;\n" + //table of all of te instances of this actor
                    "actor_and_movies <- select (M_ID == Movie_ID) (movies * actor);\n" +//table where ids are the same
                    "actors_movies <- project (Title, M_ID, Rating) actor_and_movies;"+
                    "SHOW actors_movies"; //table of movies for actor

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
            // System.out.println(listener.myDbms.indexOfTable("a"));
            Table output = listener.myDbms.table_list.get(listener.myDbms.indexOfTable("actors_movies"));
            //output.printTable();

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
            director.printTable();

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

            directorMovies.printTable();

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
            System.out.println(Actor_Name_Q5 + "'s highest rated movie is " + highestRatedMovieName + ", directed by " + (String)director.table.get(3).get(0) + ".");
            System.out.println("The worst ranked movie directed by " + (String)director.table.get(3).get(0) + " is " + worstRankedMovie +".");
        }



        //DO THIS CHUNK FOR EVERY QUERY ABOVE

//        File file = new File("src/project1/input.txt");
//        Scanner scanner = new Scanner(file);
//        List<String> lines = new ArrayList<>();
//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//            if (line.length() != 0) { lines.add(line); }
//        }
//        MyRulesBaseListener listener = new MyRulesBaseListener();
//        for (String line : lines) {
//            CharStream charStream = CharStreams.fromString(line);
//            RulesLexer lexer = new RulesLexer(charStream);
//            CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
//            RulesParser parser = new RulesParser(commonTokenStream);
//            lexer.removeErrorListeners();
//            parser.removeErrorListeners();
//            RulesParser.ProgramContext programContext = parser.program();
//            ParseTreeWalker walker = new ParseTreeWalker();
//            walker.walk(listener, programContext);
//        }

    }
}

