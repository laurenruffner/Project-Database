package project1.antlr4;
import project1.Dbms;

public class MyRulesBaseListener extends RulesBaseListener{
    Dbms myDbms;
    //default constructor
    public MyRulesBaseListener() {
        myDbms = new Dbms();
    }
    @Override public void exitShow_cmd(RulesParser.Show_cmdContext ctx){
        System.out.println ("SHOW");
    }

}

