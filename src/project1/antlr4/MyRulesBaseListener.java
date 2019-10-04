package project1.antlr4;
import org.antlr.v4.runtime.tree.ParseTree;
import project1.Dbms;
import project1.Table;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.List;
import java.io.*;

public class MyRulesBaseListener extends RulesBaseListener {
    Dbms myDbms;
    int count_inserts = 0;
    List<String> ConditionList;
    Stack<String> OperatorStack;
    List<String> PostFix;

    //STUFF FOR DIJKSTRA'S SHUNTING YARD ALGORITHM
    private enum Operator {
        AND(2), OR(2), EQUALS(1), NOTEQUALS(1), GREATER(1), LESS(1), GREATEREQUAL(1), LESSEQUAL(1),
        ADD(3), SUBTRACT(3), MULTIPLY(3);
        final int precedence;

        Operator(int p) {
            precedence = p;
        }
    }

    private static Map<String, Operator> ops = new HashMap<String, Operator>() {{
        put("&&", Operator.AND);
        put("||", Operator.OR);
        put("==", Operator.EQUALS);
        put("!=", Operator.NOTEQUALS);
        put(">", Operator.GREATER);
        put("<", Operator.LESS);
        put(">=", Operator.GREATEREQUAL);
        put("<=", Operator.LESSEQUAL);
        put("+", Operator.ADD);
        put("-", Operator.SUBTRACT);
        put("*", Operator.MULTIPLY);
    }};

    private static boolean isHigherPrec(String op, String sub) {
        //System.out.println("Greater" +  (boolean) (ops.get(sub).precedence >= ops.get(op).precedence));
        return (ops.containsKey(sub) && ops.get(sub).precedence >= ops.get(op).precedence);
    }

    private void postfix() {
        if (!PostFix.isEmpty()) {
            PostFix.clear();
        }
        for (String condition : ConditionList) {
            //System.out.println("Condition: " + condition);
            //System.out.println("Operator Stack: " + OperatorStack);
            //System.out.println("PostFix Form: " + PostFix);

            if (ops.containsKey(condition)) {
                //Higher Precedence Operator going on stack
                if ((!OperatorStack.isEmpty()) && isHigherPrec(condition, OperatorStack.peek())) {
                    OperatorStack.push(condition);
                }
                // There's no operator in stack
                else if (OperatorStack.isEmpty()) {
                    OperatorStack.push(condition);
                }
                //lower precedence going on stack
                else {
                    PostFix.add(OperatorStack.pop());
                    OperatorStack.push(condition);
                }
            }
            //If Open Parenthesis
            else if (condition.compareTo("(") == 0) {
                OperatorStack.push(condition);
            }
            //If Closed Parenthesis
            else if (condition.compareTo(")") == 0) {
                while (!(OperatorStack.peek().compareTo("(") == 0)) {
                    PostFix.add(OperatorStack.pop());
                }
                OperatorStack.pop();
            }
            //All other cases
            else {
                PostFix.add(condition);
            }
        }
        //The rest of the operators in the stack
        while (!OperatorStack.isEmpty()) {
            PostFix.add(OperatorStack.pop());
        }
    }

    ;

    //Recursion that retrieves the leaf nodes
    private void getLeafNodes(ParseTree node) {
        if (node.getChildCount() == 0) {
            //System.out.println(node.getText());
            if (node.getText().compareTo("\"") != 0) {
                ConditionList.add(node.getText());
            }
        } else {
            for (int i = 0; i < node.getChildCount(); i++) {
                ParseTree child = node.getChild(i);
                getLeafNodes(child);
            }
        }
    }


    //default constructor
    public MyRulesBaseListener() {
        myDbms = new Dbms();
        ConditionList = new ArrayList<>();
        OperatorStack = new Stack<String>();
        PostFix = new ArrayList<>();
    }

    @Override public void exitOpen_cmd(RulesParser.Open_cmdContext ctx) {
        List<ParseTree> children = ctx.children;
        String table_name = children.get(1).getText();
        try{
            String filename = "src/Files/" +  table_name  + ".db";
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String str;
            int line_num = 0;
            int table_index = -1;
            int column_count = 0;
            List<String> columns = new ArrayList<>();
            List<String> data = new ArrayList<>();
            while ((str = in.readLine()) != null) {
                if (line_num == 0) {
                    myDbms.createTable(table_name);
                } else if (line_num == 1) {
                    table_index = myDbms.indexOfTable(table_name);
                    String[] arrOfStr = str.split(" \\| ", -2);
                    for (String a : arrOfStr) {
                        //System.out.println(a);
                        columns.add(a);
                    }
                    //System.out.println(columns);
                } else if(line_num == 2){
                    String[] arrOfStr = str.split(" \\| ", -2);
                    for (String a : arrOfStr) {
                        //System.out.println(a);
                        data.add(a);
                    }
                    for (int i=0; i < data.size(); i++){
                        try {
                            //System.out.println("Comparing: " + find + " to: " + table.get(column_num).get(j));
                            Integer.parseInt(data.get(i));
                            myDbms.table_list.get(table_index).enterColumns(column_count,columns.get(i),"INTEGER");
                            myDbms.table_list.get(table_index).insertData(column_count, data.get(i), true);
                            column_count++;
                        }
                        catch (NumberFormatException e){
                            myDbms.table_list.get(table_index).enterColumns(column_count,columns.get(i), "VARCHAR");
                            myDbms.table_list.get(table_index).insertData(column_count, data.get(i), false);
                            column_count++;
                        }
                    }
                    column_count = 0;
                    data.clear();
                } else{
                    String[] arrOfStr = str.split(" \\| ", -2);
                    for (String a : arrOfStr) {
                        data.add(a);
                    }
                    for (int i=0; i < data.size(); i++){
                        try {
                            //System.out.println("Comparing: " + find + " to: " + table.get(column_num).get(j));
                            Integer.parseInt(data.get(i));
                            myDbms.table_list.get(table_index).insertData(column_count, data.get(i), true);
                            column_count++;
                        }
                        catch (NumberFormatException e){
                            myDbms.table_list.get(table_index).insertData(column_count, data.get(i), false);
                            column_count++;
                        }
                    }
                    column_count =0;
                    data.clear();
                }
                line_num++;
            }
            in.close();
            myDbms.table_list.get(myDbms.indexOfTable(table_name)).printTable();
        }
        catch(Exception e){
            System.out.println("You suck at typing");
        }
    }

    @Override public void exitWrite_cmd(RulesParser.Write_cmdContext ctx) {
        List<ParseTree> children = ctx.children;
        String tableName = children.get(1).getText(); //tableName will also be the file name
        int index = myDbms.indexOfTable(tableName);
        try {
            Table t = myDbms.table_list.get(index);
            FileWriter fw = new FileWriter("src/Files/" + tableName + ".db");
            int total_rows = t.table.get(0).size();
            int total_columns = t.column_name.size();
            fw.write("----------- " + t.table_name + " -----------\n");
            for (int j=0; j< t.column_name.size(); j++){
                if (j == t.column_name.size()-1){
                    fw.write(t.column_name.get(j));
                }
                else {
                    fw.write(t.column_name.get(j) + " | ");
                }
            }
            fw.write("\n");
            for (int k=0; k < total_rows; k++){
                for (int i = 0; i < total_columns; i++) {
                    if(i  == total_columns -1){
                        fw.write(t.table.get(i).get(k)+"");
                    }
                    else {
                        fw.write(t.table.get(i).get(k) + " | ");
                    }
                }
                fw.write("\n");
            }
            fw.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @Override public void exitClose_cmd(RulesParser.Close_cmdContext ctx) {
        List<ParseTree> children = ctx.children;
        String tableName = children.get(1).getText(); //tableName will also be the file name
        int index = myDbms.indexOfTable(tableName);
        try {
            Table t = myDbms.table_list.get(index);
            FileWriter fw = new FileWriter("src/Files/" + tableName + ".db");
            int total_rows = t.table.get(0).size();
            int total_columns = t.column_name.size();
            fw.write("----------- " + t.table_name + " -----------\n");
            for (int j=0; j< t.column_name.size(); j++){
                if (j == t.column_name.size()-1){
                    fw.write(t.column_name.get(j));
                }
                else {
                    fw.write(t.column_name.get(j) + " | ");
                }
            }
            fw.write("\n");
            for (int k=0; k < total_rows; k++){
                for (int i = 0; i < total_columns; i++) {
                    if(i  == total_columns -1){
                        fw.write(t.table.get(i).get(k)+"");
                    }
                    else {
                        fw.write(t.table.get(i).get(k) + " | ");
                    }
                }
                fw.write("\n");
            }
            fw.close();
            myDbms.table_list.remove(index);//remove table from myDbms object
            myDbms.table_names.remove(index);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @Override public void exitExit_cmd(RulesParser.Exit_cmdContext ctx) {
        System.exit(0);
    }

    @Override
    public void exitShow_cmd(RulesParser.Show_cmdContext ctx) {
        //System.out.println("***********************************SHOW CMD********************************************");
        List<ParseTree> children = ctx.children;
        String table_name = children.get(1).getText();


        if (myDbms.indexOfTable(table_name) != -1) {
            int index = myDbms.indexOfTable(table_name);
            myDbms.table_list.get(index).printTable();
        } else {
            Table table = myDbms.temp_table_stack.pop();
            table.printTable();
        }
    }

    @Override
    public void exitInsert_cmd(RulesParser.Insert_cmdContext ctx) {
        //CURRENTLY DOES NOTHING WITH THE SECOND CASE OF INSERT JUST DEALS WITH CREATING THE TABLES

        //System.out.println("**********************************INSERT CMD**************************************");
        count_inserts++;
        List<ParseTree> children = ctx.children;
        int children_size = children.size();
        //System.out.println("Table Name: " + children.get(1).getText());
        int table_index = myDbms.indexOfTable(children.get(1).getText());
        int count = 0;

        //CASE ONLY WHEN IT IS INSERTING RAW DATA NOT VALUES FROM RELATION
        if (children.get(2).getText().compareTo("VALUES FROM (") == 0) {
            int i = 3;
            boolean isInteger;
            String data;
            while (i < (children_size - 1)) {
                if (children.get(i).getText().compareTo(",") == 0) {
                    count++;
                } else if (children.get(i).getChild(0).getChildCount() > 1) {
                    data = children.get(i).getChild(0).getChild(1).getText();
                    myDbms.table_list.get(table_index).insertData(count, data, false);
                } else {
                    data = children.get(i).getChild(0).getText();
                    myDbms.table_list.get(table_index).insertData(count, data, true);
                }
                i++;
            }

        } else {
            // table_index -> index of the table inserting into
            // table is the table that we are taking the data from
            //System.out.println(myDbms.table_names.get(table_index));
            Table table = myDbms.temp_table_stack.pop();
            int table_columns = table.table.size();
            int table_rows = table.table.get(0).size();
            for (int i = 0; i < table_columns; i++) {
                int copy_into_column_index = myDbms.table_list.get(table_index).getColumnNumber(table.column_name.get(i));
                for (int j = 0; j < table_rows; j++) {
                    if (table.table.get(i).get(j).getClass().getSimpleName().equals("Integer")) {
                        myDbms.table_list.get(table_index).insertData(copy_into_column_index, Integer.toString((Integer) table.table.get(i).get(j)), true);
                    } else {
                        myDbms.table_list.get(table_index).insertData(copy_into_column_index, (String) table.table.get(i).get(j), false);
                    }
                }
            }

            //myDbms.table_list.get(table_index).printTable();
        }

        //TEST TO FIND PRIMARY ID Info -- It works
//        if (count_inserts > 4) {
//            ArrayList<String> Primary_test = new ArrayList<>();
//            Primary_test.add("Tweety");
//            Primary_test.add("bird");
//            int index_prim = myDbms.table_list.get(table_index).getPrimaryIdIndex(Primary_test);
//            myDbms.table_list.get(table_index).dataAtIndex(index_prim);
//        }
    }

    @Override public void exitProduct(RulesParser.ProductContext ctx) {
        //System.out.println("Exit Product*******************");
        List<ParseTree> children = ctx.children;
        Table table1 = null;
        Table table2 = null;
        String table1_name = children.get(0).getText();
        String table2_name = children.get(2).getText();
        if (myDbms.indexOfTable(table2_name) == -1){
            table2 = myDbms.temp_table_stack.pop();
        }
        else{
            table2 = myDbms.table_list.get(myDbms.indexOfTable(table2_name));
        }
        if(myDbms.indexOfTable(table1_name) == -1){
            table1 = myDbms.temp_table_stack.pop();
        }
        else{
            table1 = myDbms.table_list.get(myDbms.indexOfTable(table1_name));
        }
        myDbms.product(table1,table2);
    }

    @Override public void exitDifference(RulesParser.DifferenceContext ctx) {
        //System.out.println("Exit Difference________________");
        List<ParseTree> children = ctx.children;
        Table table1 = null;
        Table table2 = null;
        String table1_name = children.get(0).getText();
        String table2_name = children.get(2).getText();
        if (myDbms.indexOfTable(table2_name) == -1){
            table2 = myDbms.temp_table_stack.pop();
        }
        else{
            table2 = myDbms.table_list.get(myDbms.indexOfTable(table2_name));
        }
        if(myDbms.indexOfTable(table1_name) == -1){
            table1 = myDbms.temp_table_stack.pop();
        }
        else{
            table1 = myDbms.table_list.get(myDbms.indexOfTable(table1_name));
        }
        myDbms.difference(table1,table2);
    }

    @Override public void exitUnion(RulesParser.UnionContext ctx) {
        //System.out.println("Exit Union++++++++++++++++");
        List<ParseTree> children = ctx.children;
        Table table1 = null;
        Table table2 = null;
        String table1_name = children.get(0).getText();
        String table2_name = children.get(2).getText();
        if (myDbms.indexOfTable(table2_name) == -1){
            table2 = myDbms.temp_table_stack.pop();
        }
        else{
            table2 = myDbms.table_list.get(myDbms.indexOfTable(table2_name));
        }
        if(myDbms.indexOfTable(table1_name) == -1){
            table1 = myDbms.temp_table_stack.pop();
        }
        else{
            table1 = myDbms.table_list.get(myDbms.indexOfTable(table1_name));
        }
        myDbms.union(table1,table2);
    }

    @Override
    public void exitCreate_cmd(RulesParser.Create_cmdContext ctx) {
        //System.out.println("**********************************CREATE CMD*******************************************");
        int table_index = myDbms.emptyTableLocation();

        List<ParseTree> children = ctx.children;
        String table_name = children.get(1).getText();
        myDbms.createTable(table_name);
        //myDbms.getTableName();

        //This fills the Primary Key into the ArrayList of strings called Primary ID

        ParseTree primary_key = children.get(7);
        int primary_key_num = children.get(7).getChildCount();

        int pri_count = 0;
        int k = 0;
        while (k < primary_key_num) {
            if (pri_count == 0) {
                pri_count++;
                myDbms.table_list.get(table_index).primary_id.add(primary_key.getChild(k).getText());
            } else if (pri_count == 1 && primary_key.getChild(k).getText().compareTo(",") == 0) {
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
            if (count == 0) {
                name = new_tree.getChild(i).getText();
                count++;
            } else if (count == 1 && i == (children_num - 1) && new_tree.getChild(i).getChildCount() > 1) {
                type = new_tree.getChild(i).getChild(0).getText();
                //System.out.println("End of attribute lists-------------");
                myDbms.table_list.get(table_index).enterColumns(attr_iteration, name, type);
            } else if (count == 1 && i == (children_num - 1)) {
                type = new_tree.getChild(i).getText();
                //System.out.println("End of attribute lists-------------");
                myDbms.table_list.get(table_index).enterColumns(attr_iteration, name, type);
            } else if (count == 1 && new_tree.getChild(i).getChildCount() > 1) {
                type = new_tree.getChild(i).getChild(0).getText();
                count++;
            } else if (count == 1) {
                type = new_tree.getChild(i).getText();
                count++;
            } else if (count == 2 && new_tree.getChild(i).getText().compareTo(",") == 0) {
                //System.out.println("There's another Attribute ----------");
                myDbms.table_list.get(table_index).enterColumns(attr_iteration, name, type);
                count = 0;
                attr_iteration++;
            }
            i++;
            //System.out.println("Name: " + name + " Type: " + type);
        }
        //System.out.println("Get columns from table: " + myDbms.table_names.get(table_index));
        //myDbms.table_list.get(table_index).getColumnNames();
        //System.out.println("-------------------end of table creation ------------------");
    }


    @Override
    public void exitSelection(RulesParser.SelectionContext ctx) {
        //System.out.println("*************************SELECT********************************");
        List<ParseTree> children = ctx.children;

        //System.out.println(ConditionList);
        postfix();
        //System.out.println(PostFix);
        String table_name = children.get(4).getText();
        int table_index = myDbms.indexOfTable(table_name);
        //System.out.println(table_name + " " + table_index);
        if (table_index == -1){
            Table workon = myDbms.temp_table_stack.pop();
            for (int i = 0; i < PostFix.size(); i++) {
                String element = PostFix.get(i);
                //System.out.println("Element: " + element );
                if (element.equals("&&") || element.equals("||")) {
                    //tables and more tables
                    if (element.equals("&&")) {
                        myDbms.andand();
                    } else {
                        myDbms.oror();
                    }
                    continue;
                } else if (ops.containsKey(PostFix.get(i))) {
                    String operand1 = PostFix.get(i - 2);
                    String operand2 = PostFix.get(i - 1);
                    //System.out.println("Operand1: " + operand1);
                    //System.out.println("Operand2: " + operand2);
                    if (element.equals("==")) {
                        myDbms.equality_from_temp(operand1, operand2, workon);
                    } else if (element.equals("!=")) {
                        myDbms.not_equality_from_temp(operand1, operand2, workon);
                    } else if (element.equals(">=")) {
                        myDbms.compares_from_temp(operand1, operand2, element, workon);
                    } else if (element.equals("<=")) {
                        myDbms.compares_from_temp(operand1, operand2, element, workon);
                    } else if (element.equals(">")) {
                        myDbms.compares_from_temp(operand1, operand2, element, workon);
                    } else if (element.equals("<")) {
                        myDbms.compares_from_temp(operand1, operand2, element, workon);
                    }
                }
            }
        }
        else {
            for (int i = 0; i < PostFix.size(); i++) {
                String element = PostFix.get(i);
                //System.out.println("Element: " + element );
                if (element.equals("&&") || element.equals("||")) {
                    //tables and more tables
                    if (element.equals("&&")) {
                        myDbms.andand();
                    } else {
                        myDbms.oror();
                    }
                    continue;
                } else if (ops.containsKey(PostFix.get(i))) {
                    String operand1 = PostFix.get(i - 2);
                    String operand2 = PostFix.get(i - 1);
                    //System.out.println("Operand1: " + operand1);
                    //System.out.println("Operand2: " + operand2);
                    if (element.equals("==")) {
                        myDbms.equality(operand1, operand2, table_name);
                    } else if (element.equals("!=")) {
                        myDbms.not_equality(operand1, operand2, table_name);
                    } else if (element.equals(">=")) {
                        myDbms.compares(operand1, operand2, element, table_name);
                    } else if (element.equals("<=")) {
                        myDbms.compares(operand1, operand2, element, table_name);
                    } else if (element.equals(">")) {
                        myDbms.compares(operand1, operand2, element, table_name);
                    } else if (element.equals("<")) {
                        myDbms.compares(operand1, operand2, element, table_name);
                    }
                }
            }
        }
        ConditionList.clear();
        PostFix.clear();
    }

    @Override public void exitQuery(RulesParser.QueryContext ctx) {
        List<ParseTree> children = ctx.children;
        String relationName;
        String new_table_name = children.get(0).getText();
        if (myDbms.indexOfTable(children.get(2).getText()) != -1){
            int index_of_table_cloning = myDbms.indexOfTable(children.get(2).getText());
            Table new_table = myDbms.clone_table(myDbms.table_list.get(index_of_table_cloning));
            new_table.table_name = new_table_name;
            //new_table.printTable();
            myDbms.table_list.add(new_table);
            myDbms.table_names.add(new_table_name);
            //int index_of_new_table = myDbms.indexOfTable(new_table_name);
            //System.out.println(index_of_new_table);
            //myDbms.table_list.get(index_of_new_table).printTable();
        }
        else{
            Table old_table = myDbms.temp_table_stack.pop();
            old_table.table_name = new_table_name;
            //old_table.printTable();
            myDbms.table_list.add(old_table);
            myDbms.table_names.add(new_table_name);
            int index_of_new_table = myDbms.indexOfTable(new_table_name);
            //System.out.println(index_of_new_table);
            //myDbms.table_list.get(index_of_new_table).printTable();

        }

    }

    @Override
    public void exitUpdate_cmd(RulesParser.Update_cmdContext ctx) {
        //System.out.println("************Exit Update Cmd******************");

        List<ParseTree> children = ctx.children;
        //System.out.println(ConditionList);
        postfix();
        //System.out.println(PostFix);
        String table_name = children.get(1).getText();
        //System.out.println(table_name);
        for (int i = 0; i < PostFix.size(); i++) {
            String element = PostFix.get(i);
            //System.out.println("Element: " + element );
            if (element.equals("&&") || element.equals("||")) {
                //tables and more tables
                if (element.equals("&&")) {
                    myDbms.andand();
                } else {
                    myDbms.oror();
                }
                continue;
            } else if (ops.containsKey(PostFix.get(i))) {
                String operand1 = PostFix.get(i - 2);
                String operand2 = PostFix.get(i - 1);
                //System.out.println("Operand1: " + operand1);
                //System.out.println("Operand2: " + operand2);
                if (element.equals("==")) {
                    myDbms.equality(operand1, operand2, table_name);
                } else if (element.equals("!=")) {
                    myDbms.not_equality(operand1, operand2, table_name);
                } else if (element.equals(">=")) {
                    myDbms.compares(operand1, operand2, element, table_name);
                } else if (element.equals("<=")) {
                    myDbms.compares(operand1, operand2, element, table_name);
                } else if (element.equals(">")) {
                    myDbms.compares(operand1, operand2, element, table_name);
                } else if (element.equals("<")) {
                    myDbms.compares(operand1, operand2, element, table_name);
                }
            }
        }
        int main_table_index = myDbms.indexOfTable(table_name);
        Table change_elements = myDbms.temp_table_stack.pop();
        //System.out.println("Change these rows: ");
        //change_elements.printTable();
        //System.out.println("FROM: ");
        //myDbms.table_list.get(main_table_index).printTable();
        int rows = change_elements.table.get(0).size();
        int columns = change_elements.table.size();
        for (int k = 0; k < rows; k++) {
            List<Object> row_to_change = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                row_to_change.add(change_elements.table.get(j).get(k));
            }

            int row_index = myDbms.table_list.get(main_table_index).getRowIndex(row_to_change);

            String column_name = null;
            int column_index = 0;
            int count = 0;
            int child_count = 3;
            while (child_count < (children.size()-2)){
                if (count == 0){
                    column_name = children.get(child_count).getText();
                    column_index = myDbms.table_list.get(main_table_index).getColumnNumber(column_name);
                    child_count += 2;
                    count++;
                }
                else if(count == 1 && children.get(child_count).getChild(0).getChildCount() > 1){
                    //is STRING
                    myDbms.table_list.get(main_table_index).table.get(column_index).set(row_index, children.get(child_count).getChild(0).getChild(1).getText());
                    child_count += 2;
                    count = 0;
                }
                else if (count == 1){
                    myDbms.table_list.get(main_table_index).table.get(column_index).set(row_index, Integer.valueOf(children.get(child_count).getChild(0).getText()));
                    child_count += 2;
                    count = 0;
                }
            }

        }
        //System.out.println("NOW: ");
        //myDbms.table_list.get(main_table_index).printTable();

        ConditionList.clear();
        PostFix.clear();
    }

    @Override
    public void exitDelete_cmd(RulesParser.Delete_cmdContext ctx) {
        //System.out.println("*************Exit Delete Cmd*****************");
        List<ParseTree> children = ctx.children;

        //System.out.println(ConditionList);
        postfix();
        //System.out.println(PostFix);

        String table_name = children.get(1).getText();
        //System.out.println(table_name);
        for (int i = 0; i < PostFix.size(); i++) {
            String element = PostFix.get(i);
            //System.out.println("Element: " + element );
            if (element.equals("&&") || element.equals("||")) {
                //tables and more tables
                if (element.equals("&&")) {
                    myDbms.andand();
                } else {
                    myDbms.oror();
                }
                continue;
            } else if (ops.containsKey(PostFix.get(i))) {
                String operand1 = PostFix.get(i - 2);
                String operand2 = PostFix.get(i - 1);
                //System.out.println("Operand1: " + operand1);
                //System.out.println("Operand2: " + operand2);
                if (element.equals("==")) {
                    myDbms.equality(operand1, operand2, table_name);
                } else if (element.equals("!=")) {
                    myDbms.not_equality(operand1, operand2, table_name);
                } else if (element.equals(">=")) {
                    myDbms.compares(operand1, operand2, element, table_name);
                } else if (element.equals("<=")) {
                    myDbms.compares(operand1, operand2, element, table_name);
                } else if (element.equals(">")) {
                    myDbms.compares(operand1, operand2, element, table_name);
                } else if (element.equals("<")) {
                    myDbms.compares(operand1, operand2, element, table_name);
                }
            }
        }

        Table delete_elements = myDbms.temp_table_stack.pop();
        //System.out.println("Delete these rows: ");
        //delete_elements.printTable();
        int main_table_index = myDbms.indexOfTable(table_name);
        //System.out.println("FROM: ");
        // myDbms.table_list.get(main_table_index).printTable();
        int rows = delete_elements.table.get(0).size();
        int columns = delete_elements.table.size();
        for (int k = 0; k < rows; k++) {
            List<Object> row_to_delete = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                row_to_delete.add(delete_elements.table.get(j).get(k));
            }

            int index = myDbms.table_list.get(main_table_index).getRowIndex(row_to_delete);
            for (int n = 0; n < myDbms.table_list.get(main_table_index).table.size(); n++) {
                myDbms.table_list.get(main_table_index).table.get(n).remove(index);
            }
        }
        //System.out.println("NOW: ");
        // myDbms.table_list.get(main_table_index).printTable();

        ConditionList.clear();
        PostFix.clear();

    }


    @Override
    public void exitCondition(RulesParser.ConditionContext ctx) {
        //System.out.println("Exit Condition-----------------------");
        List<ParseTree> children = ctx.children;
        //System.out.println(children.size());
        String relationName;
        if (children.size() > 1) {
            for (int i = 0; i < children.size(); i++) {
                getLeafNodes(children.get(i));
            }
        } else {
            ParseTree condition_node = children.get(0);
            //System.out.println("Condition Node: " + condition_node.getText());
            //System.out.println("\n\nStarting getLeafNodes ------------------------------------");
            getLeafNodes(condition_node);
        }
        //System.out.println(ConditionList);
        //System.out.println("Exiting leaf nodes \n\n");
    }

    @Override
    public void exitProjection(RulesParser.ProjectionContext ctx) {
        //System.out.println("Exit Projection--------------");
        List<ParseTree> children = ctx.children;
        ParseTree new_tree = children.get(2);
        int children_num = children.get(2).getChildCount();
        //System.out.println(children.get(4).getText());

        Table temp = myDbms.createTempTable();

        int count = 0;
        int column_number = 0;
        //AKA  IT IS AN EXISTING TABLE NOT A JUNK VARIABLE
        if (myDbms.indexOfTable(children.get(4).getText()) != -1) {
            //System.out.println("NOT A TEMP TABLE");
            int table_index = myDbms.indexOfTable(children.get(4).getText());
            //myDbms.table_list.get(table_index).printTable();
            for (int i = 0; i < children_num; i++) {
                if (count == 0) {
                    String column = new_tree.getChild(i).getText();
                    //System.out.println(column);
                    int column_num = myDbms.table_list.get(table_index).getColumnNumber(column);
                    if (myDbms.table_list.get(table_index).table.get(column_num).get(0).getClass().getSimpleName().equals("Integer")) {
                        //System.out.println("Integer Column");
                        temp.enterColumns(column_number, column, "INTEGER");
                        for (int j = 0; j < myDbms.table_list.get(table_index).table.get(column_num).size(); j++) {
                            temp.insertData(column_number, Integer.toString((Integer) myDbms.table_list.get(table_index).table.get(column_num).get(j)), true);
                        }
                        column_number++;
                    } else {
                        temp.enterColumns(column_number, column, "VARCHAR");
                        for (int k = 0; k < myDbms.table_list.get(table_index).table.get(column_num).size(); k++) {
                            temp.insertData(column_number, (String) myDbms.table_list.get(table_index).table.get(column_num).get(k), false);
                        }
                        column_number++;
                    }
                    count++;
                } else if (count == 1 && new_tree.getChild(i).getText().equals(",")) {
                    count = 0;
                }
            }
            //System.out.println("The project was of a table that exists");
            //temp.printTable();
            myDbms.temp_table_stack.push(temp);
        }
        //temp.printTable();
        else {
            //System.out.println("TEMP TABLE AS OUT");
            Table table = myDbms.temp_table_stack.pop();
            //table.printTable();
            Table temp2 = myDbms.createTempTable();
            int count2 = 0;
            int column_number2 = 0;
            for (int i = 0; i < children_num; i++) {
                if (count2 == 0) {
                    String column = new_tree.getChild(i).getText();
                    //System.out.println(column);
                    int column_num = table.getColumnNumber(column);
                    if (table.table.get(column_num).get(0).getClass().getSimpleName().equals("Integer")) {
                        temp2.enterColumns(column_number2, column, "INTEGER");
                        for (int j = 0; j < table.table.get(column_num).size(); j++) {
                            temp2.insertData(column_number2, Integer.toString((Integer) table.table.get(column_num).get(j)), true);
                        }
                        column_number2++;
                    } else {
                        temp2.enterColumns(column_number2, column, "VARCHAR");
                        for (int k = 0; k < table.table.get(column_num).size(); k++) {
                            temp2.insertData(column_number2, (String) table.table.get(column_num).get(k), false);
                        }
                        column_number2++;
                    }
                    count2++;
                } else if (count2 == 1 && new_tree.getChild(i).getText().equals(",")) {
                    count2 = 0;
                }
            }
            //System.out.println("The project was of a temp table");
            //temp2.printTable();
            myDbms.temp_table_stack.push(temp2);
        }
    }

    @Override
    public void exitRenaming(RulesParser.RenamingContext ctx) {
        //System.out.println("Exit Renaming**************************");
        List<ParseTree> children = ctx.children;

        String table_name = children.get(4).getText();
        ParseTree new_tree = children.get(2);
        new_tree.getChildCount();

        if (myDbms.indexOfTable(table_name) != -1) {
            int table_index = myDbms.indexOfTable(table_name);
            int columns = myDbms.table_list.get(table_index).column_name.size();

            myDbms.table_list.get(table_index).printTable();
            int count = 0;
            for (int i = 0; i < columns; i++) {
                myDbms.table_list.get(table_index).column_name.set(i, new_tree.getChild(count).getText());
                count += 2;
            }
            //myDbms.table_list.get(table_index).printTable();
        } else {
            Table table = myDbms.temp_table_stack.pop();
            //table.printTable();
            int columns = table.table.size();
            int count1 = 0;
            for (int i = 0; i < columns; i++) {
                table.column_name.set(i, new_tree.getChild(count1).getText());
                count1 += 2;
            }
            //table.printTable();
            myDbms.temp_table_stack.push(table);
        }
    }


// USEFUL ONLY FOR SEEING WHAT THE CMD SEES

//        String relationName;
//        int i = 0;
//        while (i < children.size()){
//            if (i == 0){
//                ParseTree _test = children.get(0);
//                System.out.println(_test);
//            }
//            else {
//                if (children.get(i).getChildCount() != 0){
//
//                }
//                relationName = children.get(i).getText();
//                System.out.println(relationName);
//            }
//            i++;
//}
}


