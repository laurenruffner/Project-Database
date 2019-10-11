package project1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

class Main{
    public static void main(String[] args) throws IOException {
        MovieDatabaseParser movie_parser = new MovieDatabaseParser();

        //MAKE SURE TO ADD JSON FILES TO THE FILES FOLDER
        List<Movie> moviesList = movie_parser.deserializeMovies("src/Files/movies.json");
        List<Credits> creditsList = movie_parser.deserializeCredits("src/Files/credits.json");

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
        movies.enterColumns(11, "Description", "VARCHAR");

        Table cast = new Table("cast");
        cast.enterColumns(0, "Movie_ID", "INTEGER");
        cast.enterColumns(1, "ID", "INTEGER");
        cast.enterColumns(2, "Name", "VARCHAR");
        cast.enterColumns(3, "Character", "VARCHAR");

        Table crew = new Table("crew");
        crew.enterColumns(0, "Movie_ID", "INTEGER");
        crew.enterColumns(1, "ID", "INTEGER");
        crew.enterColumns(2, "Name", "VARCHAR");
        crew.enterColumns(3, "Department", "VARCHAR");
        crew.enterColumns(4, "Job", "VARCHAR");

        for (int i=0; i < moviesList.size(); i++){
            //M_id
            movies.insertData(0, Integer.toString(moviesList.get(i).getId()), true);
            //Title
            movies.insertData(1, moviesList.get(i).getTitle(), false);
            //release date
            String date = moviesList.get(i).getRelease_date().replace("-", "");
            if (date.compareTo("") == 0 ){
                movies.insertData(2, "-1", true);
            }
            else {
                movies.insertData(2, date, true);
            }
            //Genres
            for (int j=0; j < 3; j++){
                if (moviesList.get(i).getGenres().size() == 3){
                    movies.insertData(3, moviesList.get(i).getGenres().get(0).getName(), false);
                    movies.insertData(4, moviesList.get(i).getGenres().get(1).getName(), false);
                    movies.insertData(5, moviesList.get(i).getGenres().get(2).getName(), false);
                }
                else if(moviesList.get(i).getGenres().size() == 2){
                    movies.insertData(3, moviesList.get(i).getGenres().get(0).getName(), false);
                    movies.insertData(4, moviesList.get(i).getGenres().get(1).getName(), false);
                    movies.insertData(5, "NULL", false);
                }
                else if(moviesList.get(i).getGenres().size() == 1){
                    movies.insertData(3, moviesList.get(i).getGenres().get(0).getName(), false);
                    movies.insertData(4, "NULL", false);
                    movies.insertData(5, "NULL", false);
                }
                else{
                    movies.insertData(3, "NULL", false);
                    movies.insertData(4, "NULL", false);
                    movies.insertData(5, "NULL", false);

                }
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
            //Description

            String description = moviesList.get(i).getOverview().replace("|", "/");
            if (description.compareTo("")==0){
                movies.insertData(11, "NULL", false);
            }
            else {
                movies.insertData(11, description, false);
            }

            for (int l = 0; l < creditsList.get(i).getCastMember().size(); l++){
                String characters = creditsList.get(i).getCastMember().get(l).getCharacter().replace("|", "/");
                String[] arrofCharacters = characters.split(" / ", -2);
                for (String charact : arrofCharacters) {
                    //movie id
                    cast.insertData(0, creditsList.get(i).getId(), true);
                    //ID
                    cast.insertData(1, Integer.toString(creditsList.get(i).getCastMember().get(l).getId()), true);
                    //Name
                    cast.insertData(2, creditsList.get(i).getCastMember().get(l).getName(), false);
                    //Character
                    if (charact.compareTo("") == 0) {
                        cast.insertData(3, "NULL", false);
                    } else {
                        cast.insertData(3, charact, false);
                    }
                }

            }
            for (int m=0; m < creditsList.get(i).getCrewMember().size(); m++){
                //movie id
                crew.insertData(0, creditsList.get(i).getId(), true);
                //ID
                crew.insertData(1, Integer.toString(creditsList.get(i).getCrewMember().get(m).getId()), true);
                //Name
                crew.insertData(2, creditsList.get(i).getCrewMember().get(m).getName(), false);
                //Department
                crew.insertData(3, creditsList.get(i).getCrewMember().get(m).getDepartment(), false);
                //Job
                crew.insertData(4, creditsList.get(i).getCrewMember().get(m).getJob(), false);
            }
        }

        cast.table_to_file();
        movies.table_to_file();
        crew.table_to_file();


        //GUI INPUTS THAT DO THINGS
        //PUT THE INPUTS HERE
        //DEPENDING ON INPUTS IT CREATES AN INPUT.TXT

        boolean query1 = false;
        boolean query2 = false;
        boolean query3 = false;
        boolean query4 = true;
        boolean query5 = false;


        String Character_Name_Q4 = "Woody (voice)"; //Eventually this will be input from GUI

        //QUERY1
        if(query1){

        }
        //QUERY2
        else if(query2){

        }
        //QUERY3
        else if(query3){

        }
        //QUERY4
        else if(query4){

//            File file = new File("src/Files/input_query4.txt");
//
//            String fileContent = "OPEN movies;\n" +
//                    "OPEN cast;\n" +
//                    "actors <- select (Character == \"" + Character_Name_Q4 + "\") cast;\n" +
//                    "actor_and_movies <- select (M_ID == Movie_ID) (movies * actors);\n" +
//                    "movie_actor <- project (Title, Name) actor_and_movies;";
//
//            FileWriter fileWriter = new FileWriter("src/Files/input_query4.txt");
//            fileWriter.write(fileContent);
//            fileWriter.close();
//
//            File query_4_file = new File("src/Files/input_query4.txt");
//            Scanner scanner = new Scanner(query_4_file);
//            List<String> lines = new ArrayList<>();
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                if (line.length() != 0) { lines.add(line); }
//            }
//            MyRulesBaseListener listener = new MyRulesBaseListener();
//            for (String line : lines) {
//                CharStream charStream = CharStreams.fromString(line);
//                RulesLexer lexer = new RulesLexer(charStream);
//                CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
//                RulesParser parser = new RulesParser(commonTokenStream);
//                lexer.removeErrorListeners();
//                parser.removeErrorListeners();
//                RulesParser.ProgramContext programContext = parser.program();
//                ParseTreeWalker walker = new ParseTreeWalker();
//                walker.walk(listener, programContext);
//            }

        }
        //QUERY5
        else if(query5){

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

