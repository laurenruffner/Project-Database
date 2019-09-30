package project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import project1.antlr4.MyRulesBaseListener;
import project1.antlr4.RulesLexer;
import project1.antlr4.RulesParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

class Main{
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/project1/input2.txt");
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.length() != 0) { lines.add(line); }
        }

        MyRulesBaseListener listener = new MyRulesBaseListener();
        for (String line : lines) {

            // CREATE A FOR LOOP TO ITERATE THROUGH EACH STRING OBJECT
            // WE need to do this

            //This sets us the lexer(tokenizer) and parser
            CharStream charStream = CharStreams.fromString(line);
            RulesLexer lexer = new RulesLexer(charStream);
            CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
            RulesParser parser = new RulesParser(commonTokenStream);

            //This disables the lexer and parser warnings
            lexer.removeErrorListeners();
            parser.removeErrorListeners();

            //This creates the parse tree listener and  the parse tree walker
            RulesParser.ProgramContext programContext = parser.program();
            ParseTreeWalker walker = new ParseTreeWalker();

            //This calls this thing over and over and over
//            MyRulesBaseListener listener = new MyRulesBaseListener();
            walker.walk(listener, programContext);


        }

    }
}

