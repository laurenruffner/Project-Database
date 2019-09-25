package project1.antlr4;
import project1.Dbms;

public class MyRulesBaseListener extends RulesBaseListener{
    //default constructor
    public MyRulesBaseListener() {
        myDbms = new Dbms();
    }
//    @Override public void exitShowCmd(RulesParser.ShowCmdContext ctx) {
//        System.out.println("SHOW");
//    }

}

