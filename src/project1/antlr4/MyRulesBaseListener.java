package project1.antlr4;
import org.antlr.v4.runtime.tree.ParseTree;
import project1.Dbms;

import java.util.ArrayList;
import java.util.List;

public class MyRulesBaseListener extends RulesBaseListener{
    ArrayList<String> stack;
    Dbms myDbms;
    //default constructor
    public MyRulesBaseListener() {
        myDbms = new Dbms();
    }

//    public void iterate(ParseTree tree){
//        List<ParseTree> children = tree.;
//        //System.out.println(children.size());
//
//        String relationName;
//        int i = 0;
//        while (i < children.size()){
//            if (i == 0){
//                ParseTree _test = children.get(0);
//                //System.out.println(_test);
//            }
//            else {
//                if (children.get(i).getChildCount() != 0){
//
//                }
//                relationName = children.get(i).getText();
//                //System.out.println(relationName);
//            }
//            i++;
//        }
//    }




//    @Override public void exitShow_cmd(RulesParser.Show_cmdContext ctx){
//        //System.out.println(ctx);
//        //System.out.println ("SHOW");
//        List<ParseTree> children = ctx.children;
//        //System.out.println(children.size());
//
//        //ParseTree _test = children.get(0);  //Ignore
//
//        String relationName;
//        int i = 0;
//        while (i < children.size()){
//            if (i == 0){
//                ParseTree _test = children.get(0);
//                //System.out.println(_test);
//            }
//            else {
//                if (children.get(i).getChildCount() != 0){
//
//                }
//                relationName = children.get(i).getText();
//                //System.out.println(relationName);
//            }
//            i++;
//        }
//
//        //String relationName = children.get(1).getText();
//
//        //System.out.println(relationName);
//
//        //dbms.getTable(relationName);
//
//    }


    @Override public void enterCreate_cmd(RulesParser.Create_cmdContext ctx) {

        List<ParseTree> children = ctx.children;

        String table_name = children.get(1).getText();

        System.out.println(table_name);

        myDbms.createTable(table_name);


//        System.out.println(table_name);
//        ParseTree new_tree = children.get(3);
//        //System.out.println(children.get(3).getText());
//        int children_num = children.get(3).getChildCount();
//
//        String relationName;
//        String type = null;
//        int attr_iteration = 0;
//        int i = 0;
//        int count = 0;
//        String name = null;
//        while (i < children_num) {
//            // System.out.println(children.get(i).getChildCount());
//            if (count == 0) {
//                name = new_tree.getChild(i).getText();
//                //System.out.println(name);
//                count++;
//            } else if (count == 1 && new_tree.getChild(i).getChildCount() > 1) {
//                int x = 1;
//                type = new_tree.getChild(i).getChild(0).getText();
//                //System.out.print(type);
//                count++;
//                //        "VARCHAR";
//            } else if (count == 1) {
//                type = new_tree.getChild(i).getText();
//                count++;
//            } else if (count == 2 && new_tree.getChild(i).getText() == ",") {
//                myDbms.entercolumns(attr_iteration, name, type);
//                count = 0;
//                attr_iteration++;
//                //relationName = new_tree.getChild(i).getText();
//                //System.out.println(relationName);
//            }
//            i++;
//            //System.out.println("Name: " + name + " Type: " + type);
//        }
//
//        myDbms.iterateTable();
    }

//    @Override public void enterTyped_attribute_list(RulesParser.Typed_attribute_listContext ctx) {
//        List<ParseTree> children = ctx.children;
//        System.out.println(children.size());
//
//        String relationName;
//        String type = null;
//        int i = 0;
//        int count = 0;
//        String name = null;
//        while (i < children.size()) {
//           // System.out.println(children.get(i).getChildCount());
//
//            if (count == 0) {
//                name = children.get(i).getText();
//                System.out.println(name);
//                count++;
//            }
//            else if (count == 1 && children.get(i).getChildCount() > 1){
//                int  x = 1;
//                type = children.get(i).getChild(0).getText();
//                System.out.print(type);
//                count++;
//                //        "VARCHAR";
//            }
//            else if (count == 1){
//                type = children.get(i).getText();
//                count++;
//            }
//            else if (count == 2 && children.get(i).getText() == "," ){
//                count = 0;
//                relationName = children.get(i).getText();
//                System.out.println(relationName);
//            }
//            i++;
//            System.out.println("Name: " + name + " Type: " + type);
//        }
//    }

}

