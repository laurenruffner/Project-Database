package project1.antlr4;
import project1.Dbms;

public class MyRulesBaseListener extends RulesBaseListener{
    //default constructor
    public MyRulesBaseListener() {
        myDbms = new Dbms();
    }
}

