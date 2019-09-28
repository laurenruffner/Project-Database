package project1.antlr4;
import org.antlr.v4.runtime.tree.ParseTree;
import org.jetbrains.annotations.NotNull;
import project1.Dbms;

import java.util.ArrayList;
import java.util.List;

public class MyRulesBaseListener extends RulesBaseListener {
    Dbms myDbms;
    int count_inserts = 0;

    //default constructor
    public MyRulesBaseListener() {
        myDbms = new Dbms();
    }



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

    @Override public void exitInsert_cmd(RulesParser.Insert_cmdContext ctx) {
        //CURRENTLY DOES NOTHING WITH THE SECOND CASE OF INSERT JUST DEALS WITH CREATING THE TABLES

        System.out.println("**********************************INSERT CMD**************************************");
        count_inserts++;
        List<ParseTree> children = ctx.children;
        int children_size = children.size();
        System.out.println("Table Name: " + children.get(1).getText());
        int table_index = myDbms.indexOfTable(children.get(1).getText());

        //System.out.println("Table Index: " + table_index);
        int count = 0;
        if (children.get(2).getText().compareTo("VALUES FROM (") == 0){
            int i = 3;

            boolean isInteger;
            String data;
            while (i <  (children_size-1)){

                if (children.get(i).getText().compareTo(",") == 0){
                    count++;
                }
                else if(children.get(i).getChild(0).getChildCount() >  1) {
                    //System.out.println(children.get(i).getChild(0).getChildCount());
                    data = children.get(i).getChild(0).getChild(1).getText();
                    //System.out.println("String: " + data);
                    //System.out.println("Column #: " + count);
                    myDbms.table_list.get(table_index).insertData(count, data, false);

                }
                else{
                    data = children.get(i).getChild(0).getText();
                    //System.out.println("Number: " + data);
                    //System.out.println("Column #: " + count);
                    myDbms.table_list.get(table_index).insertData(count, data, true);
                }
                i++;
            }

        }

        myDbms.table_list.get(table_index).printTable();

        //TEST TO FIND PRIMARY ID
//        if (count_inserts > 4) {
//            ArrayList<String> Primary_test = new ArrayList<>();
//            Primary_test.add("Tweety");
//            Primary_test.add("bird");
//            int index_prim = myDbms.table_list.get(table_index).getPrimaryIdIndex(Primary_test);
//            myDbms.table_list.get(table_index).dataAtIndex(index_prim);
//        }

//        String relationName;
////        int i = 0;
////        while (i < children.size()){
////            if (i == 0){
////                ParseTree _test = children.get(0);
////                System.out.println(_test);
////            }
////            else {
////                if (children.get(i).getChildCount() != 0){
////
////                }
////                relationName = children.get(i).getText();
////                System.out.println(relationName);
////            }
////            i++;

    }


    @Override
    public void exitCreate_cmd(RulesParser.Create_cmdContext ctx) {
        System.out.println("**********************************CREATE CMD*******************************************");
        int table_index = myDbms.emptyTableLocation();

        List<ParseTree> children = ctx.children;
        String table_name = children.get(1).getText();
        myDbms.createTable(table_name);
        myDbms.getTableName();

        //This fills the Primary Key into the ArrayList of strings called Primary ID

        ParseTree primary_key = children.get(7);
        int primary_key_num = children.get(7).getChildCount();

        int pri_count = 0;
        int k = 0;
        while (k < primary_key_num){
            if (pri_count == 0){
                pri_count++;
                myDbms.table_list.get(table_index).primary_id.add(primary_key.getChild(k).getText());
            }
            else if (pri_count == 1 && primary_key.getChild(k).getText().compareTo(",") == 0){
                pri_count = 0;
            }
            k++;
        }

        //This adds the proper columns due to what the attribute list includes

        ParseTree new_tree = children.get(3);
        int children_num = children.get(3).getChildCount();

        //used variables
        String type = null;
        int attr_iteration = 0;
        int i = 0;
        int count = 0;
        String name = null;

        while (i < children_num) {
            // THESE WERE TESTS FOR CASES
            //System.out.println("child count: " + children_num);
            //System.out.println("count: " + count);
            //System.out.println("getText: " + new_tree.getChild(i).getText());
            //System.out.println("Attribute iteration: " + attr_iteration);

            if (count == 0) {
                name = new_tree.getChild(i).getText();
                count++;
            } else if (count == 1 && i == (children_num - 1) &&  new_tree.getChild(i).getChildCount() > 1){
                type = new_tree.getChild(i).getChild(0).getText();
                System.out.println("End of attribute lists-------------");
                myDbms.table_list.get(table_index).enterColumns(attr_iteration, name, type);
            } else if (count == 1 && i == (children_num - 1)) {
                type = new_tree.getChild(i).getText();
                System.out.println("End of attribute lists-------------");
                myDbms.table_list.get(table_index).enterColumns(attr_iteration, name, type);
            } else if (count == 1 && new_tree.getChild(i).getChildCount() > 1) {
                type = new_tree.getChild(i).getChild(0).getText();
                count++;
            } else if (count == 1) {
                type = new_tree.getChild(i).getText();
                count++;
            } else if (count == 2 && new_tree.getChild(i).getText().compareTo(",") == 0) {
                System.out.println("There's another Attribute ----------");
                myDbms.table_list.get(table_index).enterColumns(attr_iteration, name, type);
                count = 0;
                attr_iteration++;
            }
            i++;
            System.out.println("Name: " + name + " Type: " + type);
        }
        System.out.println("Get columns from table: " + myDbms.getTableName());
        myDbms.table_list.get(table_index).getColumnNames();

        System.out.println("-------------------end of table creation ------------------");
    }

/**
    @Override public void enterRelation_name(RulesParser.Relation_nameContext ctx) {
        System.out.println("Enter Relation_Name");
    }

    @Override public void exitRelation_name(RulesParser.Relation_nameContext ctx) {
        System.out.println("Exit Relation_Name");
    }

    @Override public void enterAttribute_name(RulesParser.Attribute_nameContext ctx) {
        System.out.println("Enter Attribute_Name");
    }

    @Override public void exitAttribute_name(RulesParser.Attribute_nameContext ctx) {
        System.out.println("Exit Attribute_Name");
    }

    @Override public void enterOperand(RulesParser.OperandContext ctx) {
        System.out.println("Enter Operand");
    }

    @Override public void exitOperand(RulesParser.OperandContext ctx) {
        System.out.println("Exit Operand");
    }

    @Override public void enterType(RulesParser.TypeContext ctx) {
        System.out.println("Enter Type");
    }

    @Override public void exitType(RulesParser.TypeContext ctx) {
        System.out.println("Exit Type");
    }

    @Override public void enterAttribute_list(RulesParser.Attribute_listContext ctx) {
        System.out.println("Enter Attribute_List");
    }

    @Override public void exitAttribute_list(RulesParser.Attribute_listContext ctx) {
        System.out.println("Exit Attribute_List");
    }

    @Override public void enterOpen_cmd(RulesParser.Open_cmdContext ctx) {
        System.out.println("Enter Open Cmd");
    }

    @Override public void exitOpen_cmd(RulesParser.Open_cmdContext ctx) {
        System.out.println("Exit Open Cmd");
    }

    @Override public void enterClose_cmd(RulesParser.Close_cmdContext ctx) {
        System.out.println("Enter Close Cmd");
    }

    @Override public void exitClose_cmd(RulesParser.Close_cmdContext ctx) {
        System.out.println("Exit Close Cmd");
    }

    @Override public void enterWrite_cmd(RulesParser.Write_cmdContext ctx) {
        System.out.println("Enter Write Cmd");
    }

    @Override public void exitWrite_cmd(RulesParser.Write_cmdContext ctx) {
        System.out.println("Exit Write Cmd");
    }

    @Override public void enterExit_cmd(RulesParser.Exit_cmdContext ctx) {
        System.out.println("Enter Exit Cmd");
    }

    @Override public void exitExit_cmd(RulesParser.Exit_cmdContext ctx) {
        System.out.println("Exit Exit Cmd");
    }

    @Override public void enterCondition(RulesParser.ConditionContext ctx) {
        System.out.println("Enter Condition");
    }

    @Override public void exitCondition(RulesParser.ConditionContext ctx) {
        System.out.println("Exit Condition");
    }

    @Override public void enterConjunction(RulesParser.ConjunctionContext ctx) {
        System.out.println("Enter Conjunction");
    }

    @Override public void exitConjunction(RulesParser.ConjunctionContext ctx) {
        System.out.println("Exit Conjunction");
    }

    @Override public void enterComparison(RulesParser.ComparisonContext ctx) {
        System.out.println("Enter Comparison");
    }

    @Override public void exitComparison(RulesParser.ComparisonContext ctx) {
        System.out.println("Exit Comparison");
    }

    @Override public void enterExpr(RulesParser.ExprContext ctx) {
        System.out.println("Enter Expr");
    }

    @Override public void exitExpr(RulesParser.ExprContext ctx) {
        System.out.println("Exit Expr");
    }

    @Override public void enterAtomic_expr(RulesParser.Atomic_exprContext ctx) {
        System.out.println("Enter Atomic_Expr");
    }

    @Override public void exitAtomic_expr(RulesParser.Atomic_exprContext ctx) {
        System.out.println("Exit Atomic_Expr");
    }

    @Override public void enterSelection(RulesParser.SelectionContext ctx) {
        System.out.println("Enter Selection");
    }

    @Override public void exitSelection(RulesParser.SelectionContext ctx) {
        System.out.println("Exit Selection");
    }

    @Override public void enterProjection(RulesParser.ProjectionContext ctx) {
        System.out.println("Enter Projection");
    }

    @Override public void exitProjection(RulesParser.ProjectionContext ctx) {
        System.out.println("Exit Projection");
    }

    @Override public void enterRenaming(RulesParser.RenamingContext ctx) {
        System.out.println("Enter Renaming");
    }

    @Override public void exitRenaming(RulesParser.RenamingContext ctx) {
        System.out.println("Exit Renaming");
    }

    @Override public void enterUnion(RulesParser.UnionContext ctx) {
        System.out.println("Enter Union");
    }

    @Override public void exitUnion(RulesParser.UnionContext ctx) {
        System.out.println("Exit Union");
    }

    @Override public void enterDifference(RulesParser.DifferenceContext ctx) {
        System.out.println("Enter Difference");
    }

    @Override public void exitDifference(RulesParser.DifferenceContext ctx) {
        System.out.println("Exit Difference");
    }

    @Override public void enterProduct(RulesParser.ProductContext ctx) {
        System.out.println("Enter Product");
    }

    @Override public void exitProduct(RulesParser.ProductContext ctx) {
        System.out.println("Exit Product");
    }

    @Override public void enterShow_cmd(RulesParser.Show_cmdContext ctx) {
        System.out.println("Enter Show Cmd");
    }

    @Override public void exitShow_cmd(RulesParser.Show_cmdContext ctx) {
        System.out.println("Exit Show Cmd");
    }


    @Override public void enterUpdate_cmd(RulesParser.Update_cmdContext ctx) {
        System.out.println("Enter Update Cmd");
    }

    @Override public void exitUpdate_cmd(RulesParser.Update_cmdContext ctx) {
        System.out.println("Exit Update Cmd");
    }

    @Override public void enterInsert_cmd(RulesParser.Insert_cmdContext ctx) {
        System.out.println("Enter Insert Cmd");
    }

    @Override public void enterDelete_cmd(RulesParser.Delete_cmdContext ctx) {
        System.out.println("Enter Delete Cmd");
    }

    @Override public void exitDelete_cmd(RulesParser.Delete_cmdContext ctx) {
        System.out.println("Exit Delete Cmd");
    }

    @Override public void enterCommand(RulesParser.CommandContext ctx) {
        System.out.println("Enter Command");
    }

    @Override public void exitCommand(RulesParser.CommandContext ctx) {
        System.out.println("Exit Command");
    }

    @Override public void enterQuery(RulesParser.QueryContext ctx) {
        System.out.println("Enter Query");
    }

    @Override public void exitQuery(RulesParser.QueryContext ctx) {
        System.out.println("Exit Query");
    }

    @Override public void enterProgram(RulesParser.ProgramContext ctx) {
        System.out.println("Enter Program");
    }

    @Override public void exitProgram(RulesParser.ProgramContext ctx) {
        System.out.println("Exit Program");
    }
    **/
}


