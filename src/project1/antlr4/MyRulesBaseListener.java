package project1.antlr4;
import org.antlr.v4.runtime.tree.ParseTree;
import project1.Dbms;

import java.util.List;

public class MyRulesBaseListener extends RulesBaseListener{
    Dbms myDbms;
    //default constructor
    public MyRulesBaseListener() {
        myDbms = new Dbms();
    }



    @Override public void exitShow_cmd(RulesParser.Show_cmdContext ctx){
        //System.out.println(ctx);
        //System.out.println ("SHOW");
        List<ParseTree> children = ctx.children;
        System.out.println(children.size());

        //ParseTree _test = children.get(0);  //Ignore

        String relationName;
        int i = 0;
        while (i < children.size()){
            if (i == 0){
                ParseTree _test = children.get(0);
                System.out.println(_test);
            }
            else {
                if (children.get(i).getChildCount() != 0){

                }
                relationName = children.get(i).getText();
                System.out.println(relationName);
            }
            i++;
        }

        //String relationName = children.get(1).getText();

        //System.out.println(relationName);

        //dbms.getTable(relationName);

    }


    @Override public void exitCreate_cmd(RulesParser.Create_cmdContext ctx) {
        List<ParseTree> children = ctx.children;
        System.out.println(children.size());

        //ParseTree _test = children.get(0);  //Ignore

        String relationName;
        int i = 0;
        while (i < children.size()){
            if (i == 0){
                ParseTree _test = children.get(0);
                System.out.println(_test);
            }
            else {

                relationName = children.get(1).getText();
                System.out.println(relationName);
            }
            i++;
        }
    }

}

