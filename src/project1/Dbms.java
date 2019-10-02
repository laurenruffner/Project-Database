package project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Dbms {
    public ArrayList<Table> table_list;
    public Stack<Table> temp_table_stack;
    public ArrayList<String> table_names;
    public int temp_table = 0;

    public Dbms(){
        table_list = new ArrayList<>();
        table_names = new ArrayList<>();
        temp_table_stack = new Stack<>();
    }

    //get empty table input with table_num ----- table_list.size()
    public int emptyTableLocation(){
        return table_list.size();
    }

    //get table num with specific name -- look through table_nums
    public int indexOfTable(String name){
        int index = -1;
        for(int i = 0; i < table_names.size(); i++){
            if (name.compareTo(table_names.get(i)) == 0){
                 index = i;
            }
        }
        return index;
    }

    public boolean is_duplicate(List<Object> row_table, Table table){
        int column = table.table.size();
        int row = table.table.get(0).size();
        for(int i=0; i < row; i++){
            List<Object> row_table2= new ArrayList<>();
            for(int j=0; j < column; j++){
                row_table2.add(table.table.get(j).get(i));
            }
            if (row_table.equals(row_table2)){
                return true;
            };
        }
        return false;
    }

    public void createTable(String name) {
        String table_name = name;
        Table int1 = new Table(name);
        table_list.add(int1);
        table_names.add(table_name);
    }

    public Table createTempTable() {
        String table_name = "temp" + temp_table;
        //System.out.println("Temp Table Name: " + table_name);
        Table int1 = new Table(table_name);
        temp_table++;
        return int1;
    }

    public void andand(){
        //System.out.println("IN ANDAND");
        Table temp1 = temp_table_stack.pop();
        Table temp2 = temp_table_stack.pop();
        //temp1.printTable();
        //temp2.printTable();
        create_empty_temp_clone(temp1);
        Table temp = temp_table_stack.pop();
        int columns1  = temp1.table.size();
        int rows1 = temp1.table.get(0).size();
        int rows2 = temp2.table.get(0).size();

        //System.out.println("Columns: " + columns1 + " Rows: " +  rows1);
        for (int i = 0; i <  rows1; i++) {

            List<Object> temp1_row = new ArrayList();
            for (int j = 0; j < columns1; j++) {
                temp1_row.add(temp1.table.get(j).get(i));
                //System.out.println(temp1_row);
            }
            for (int k = 0; k < rows2; k++) {
                List<Object> temp2_row = new ArrayList();
                for (int l = 0; l < columns1; l++) {
                    temp2_row.add(temp2.table.get(l).get(k));
                    //System.out.println(temp1_row);
                }
                //System.out.println("Temp1: " + temp1_row + " Temp2: " + temp2_row);
                if (temp1_row.equals(temp2_row)) {
                    for (int m = 0; m < temp1_row.size(); m++) {
                        if (temp1_row.get(m).getClass().getSimpleName().equals("Integer")) {
                            temp.insertData(m, Integer.toString((Integer) temp1_row.get(m)), true);
                        } else {
                            temp.insertData(m, (String) temp1_row.get(m), false);
                        }
                    }
                    break;
                }
            }
        }
        temp_table_stack.push(temp);
    }

    public void oror(){
        //System.out.println("OROR ----------------------------------------");
        Table temp1 = temp_table_stack.pop();
        Table temp2 = temp_table_stack.pop();

        //temp1.printTable();
        //temp2.printTable();

        create_empty_temp_clone(temp1);
        Table temp = temp_table_stack.pop();
        int columns1  = temp1.table.size();
        int rows1 = temp1.table.get(0).size();
        int rows2 = temp2.table.get(0).size();

        for (int i=0; i < rows1; i++){
            List<Object> row_of_temp1 = new ArrayList<>();
            for (int j=0; j < columns1; j++){
                row_of_temp1.add(temp1.table.get(j).get(i));
            }
            if (is_duplicate(row_of_temp1, temp2) == false){
                for (int m = 0; m < row_of_temp1.size(); m++) {
                    if (row_of_temp1.get(m).getClass().getSimpleName().equals("Integer")) {
                        temp.insertData(m, Integer.toString((Integer) row_of_temp1.get(m)), true);
                    } else {
                        temp.insertData(m, (String) row_of_temp1.get(m), false);
                    }
                }
            }
        }
        for (int n=0; n < rows2; n++){
            List<Object> row_of_temp2 = new ArrayList<>();
            for (int o=0; o < columns1; o++){
                row_of_temp2.add(temp2.table.get(o).get(n));
            }
            for (int p = 0; p < row_of_temp2.size(); p++) {
                if (row_of_temp2.get(p).getClass().getSimpleName().equals("Integer")) {
                    temp.insertData(p, Integer.toString((Integer) row_of_temp2.get(p)), true);
                } else {
                    temp.insertData(p, (String) row_of_temp2.get(p), false);
                }
            }
        }
        temp_table_stack.push(temp);
    }

    public void difference(Table table1, Table table2){
        Table temp1 = table1;
        Table temp2 = table2;
        temp1.printTable();
        temp2.printTable();

        create_empty_temp_clone(temp1);
        Table temp = temp_table_stack.pop();
        int columns1  = temp1.table.size();
        int rows1 = temp1.table.get(0).size();
        int rows2 = temp2.table.get(0).size();

        for (int i=0; i < rows1; i++){
            List<Object> row_of_temp1 = new ArrayList<>();
            for (int j=0; j < columns1; j++){
                row_of_temp1.add(temp1.table.get(j).get(i));
            }
            if (is_duplicate(row_of_temp1, temp2) == false){
                for (int m = 0; m < row_of_temp1.size(); m++) {
                    if (row_of_temp1.get(m).getClass().getSimpleName().equals("Integer")) {
                        temp.insertData(m, Integer.toString((Integer) row_of_temp1.get(m)), true);
                    } else {
                        temp.insertData(m, (String) row_of_temp1.get(m), false);
                    }
                }
            }
        }
        for (int n=0; n < rows2; n++){
            List<Object> row_of_temp2 = new ArrayList<>();
            for (int o=0; o < columns1; o++){
                row_of_temp2.add(temp2.table.get(o).get(n));
            }
            if (is_duplicate(row_of_temp2, temp1) == false){
                for (int m = 0; m < row_of_temp2.size(); m++) {
                    if (row_of_temp2.get(m).getClass().getSimpleName().equals("Integer")) {
                        temp.insertData(m, Integer.toString((Integer) row_of_temp2.get(m)), true);
                    } else {
                        temp.insertData(m, (String) row_of_temp2.get(m), false);
                    }
                }
            }
        }
        temp_table_stack.push(temp);
    }

    public void union(Table table1, Table table2){
        Table temp1 = table1;
        Table temp2 = table2;
        //temp1.printTable();
        //temp2.printTable();

        create_empty_temp_clone(temp1);
        Table temp = temp_table_stack.pop();
        int columns1  = temp1.table.size();
        int rows1 = temp1.table.get(0).size();
        int rows2 = temp2.table.get(0).size();

        for (int i=0; i < rows1; i++){
            List<Object> row_of_temp1 = new ArrayList<>();
            for (int j=0; j < columns1; j++){
                row_of_temp1.add(temp1.table.get(j).get(i));
            }
            if (is_duplicate(row_of_temp1, temp2) == false){
                for (int m = 0; m < row_of_temp1.size(); m++) {
                    if (row_of_temp1.get(m).getClass().getSimpleName().equals("Integer")) {
                        temp.insertData(m, Integer.toString((Integer) row_of_temp1.get(m)), true);
                    } else {
                        temp.insertData(m, (String) row_of_temp1.get(m), false);
                    }
                }
            }
        }
        for (int n=0; n < rows2; n++){
            List<Object> row_of_temp2 = new ArrayList<>();
            for (int o=0; o < columns1; o++){
                row_of_temp2.add(temp2.table.get(o).get(n));
            }
            for (int p = 0; p < row_of_temp2.size(); p++) {
                if (row_of_temp2.get(p).getClass().getSimpleName().equals("Integer")) {
                    temp.insertData(p, Integer.toString((Integer) row_of_temp2.get(p)), true);
                } else {
                    temp.insertData(p, (String) row_of_temp2.get(p), false);
                }
            }
        }
        temp_table_stack.push(temp);
    }

    public void equality(String operand1, String operand2, String table_name){

        int index_table = indexOfTable(table_name);
        if(index_table != -1) {
            int index_column = table_list.get(index_table).getColumnNumber(operand1);

            List<Integer> index_list = new ArrayList<>();
            index_list = table_list.get(index_table).findIndicies(index_column, operand2);

            create_empty_temp_clone(table_list.get(index_table));
            Table temp = temp_table_stack.pop();

            for (int i = 0; i < index_list.size(); i++) {
                for (int j = 0; j < table_list.get(index_table).table.size(); j++) {  //gives you the column size
                    if (table_list.get(index_table).table.get(j).get(index_list.get(i)).getClass().getSimpleName().equals("Integer")) {
                        //System.out.println("Integer");
                        int data = (Integer) table_list.get(index_table).table.get(j).get(index_list.get(i));
                        //System.out.println("Data is integer: " + data + "Column: [" + j + "] " + temp.column_name.get(j) );
                        temp.insertData(j, Integer.toString(data), true);
                    } else {
                        //System.out.println("String");
                        String data = (String) table_list.get(index_table).table.get(j).get(index_list.get(i));
                        //System.out.println("Data is String: " + data + "Column: [" + j + "] " + temp.column_name.get(j) );
                        temp.insertData(j, data, false);
                    }//data = table_list.get(index_table).table.get(j).get(index_list.get(i));
                }
                //table_list.get(index_table).dataAtIndex(index_list.get(i));
            }
            //temp.printTable();
            temp_table_stack.push(temp);
        }
        else{
            Table temp_table = temp_table_stack.pop();
            int index_column = temp_table.getColumnNumber(operand1);

            List<Integer> index_list = new ArrayList<>();
            index_list = temp_table.findIndicies(index_column, operand2);

            create_empty_temp_clone(temp_table);
            Table temp = temp_table_stack.pop();

            for (int i = 0; i < index_list.size(); i++) {
                for (int j = 0; j < temp_table.table.size(); j++) {  //gives you the column size
                    if (temp_table.table.get(j).get(index_list.get(i)).getClass().getSimpleName().equals("Integer")) {
                        int data = (Integer) temp_table.table.get(j).get(index_list.get(i));
                        temp.insertData(j, Integer.toString(data), true);
                    } else {
                        String data = (String) temp_table.table.get(j).get(index_list.get(i));
                        temp.insertData(j, data, false);
                    }
                }
            }
            temp_table_stack.push(temp);
        }
        //temp_table_stack.get(0).printTable();
    }
    public void not_equality(String operand1, String operand2, String table_name){
//        System.out.println("$ " + operand1);
//        System.out.println("$ "+ operand2);
        //System.out.println("$ " + table_name);

        int index_table = indexOfTable(table_name);
        if (index_table != -1) {
            //System.out.println(table_names.get(index_table));
            int index_column = table_list.get(index_table).getColumnNumber(operand1);
            //System.out.println(table_list.get(index_table).column_name.get(index_column));

            List<Integer> index_list = new ArrayList<>();
            // FIND everything but operand2
            index_list = table_list.get(index_table).findAllButIndicies(index_column, operand2);
            //System.out.println(index_list);

            create_empty_temp_clone(table_list.get(index_table));
            Table temp = temp_table_stack.pop();

            for (int i = 0; i < index_list.size(); i++) {
                for (int j = 0; j < table_list.get(index_table).table.size(); j++) {  //gives you the column size
                    if (table_list.get(index_table).table.get(j).get(index_list.get(i)).getClass().getSimpleName().equals("Integer")) {
                        //System.out.println("Integer");
                        int data = (Integer) table_list.get(index_table).table.get(j).get(index_list.get(i));
                        //System.out.println("Data is integer: " + data + "Column: [" + j + "] " + temp.column_name.get(j) );
                        temp.insertData(j, Integer.toString(data), true);
                    } else {
                        //System.out.println("String");
                        String data = (String) table_list.get(index_table).table.get(j).get(index_list.get(i));
                        //System.out.println("Data is String: " + data + "Column: [" + j + "] " + temp.column_name.get(j) );
                        temp.insertData(j, data, false);
                    }//data = table_list.get(index_table).table.get(j).get(index_list.get(i));
                }
                //table_list.get(index_table).dataAtIndex(index_list.get(i));
            }
            //temp.printTable();
            temp_table_stack.push(temp);
            //temp_table_stack.get(0).printTable();
        }
        else{
            Table temp_table = temp_table_stack.pop();
            int index_column = temp_table.getColumnNumber(operand1);

            List<Integer> index_list = new ArrayList<>();
            index_list = temp_table.findAllButIndicies(index_column, operand2);

            create_empty_temp_clone(temp_table);
            Table temp = temp_table_stack.pop();

            for (int i = 0; i < index_list.size(); i++) {
                for (int j = 0; j < temp_table.table.size(); j++) {  //gives you the column size
                    if (temp_table.table.get(j).get(index_list.get(i)).getClass().getSimpleName().equals("Integer")) {
                        int data = (Integer) temp_table.table.get(j).get(index_list.get(i));
                        temp.insertData(j, Integer.toString(data), true);
                    } else {
                        String data = (String) temp_table.table.get(j).get(index_list.get(i));
                        temp.insertData(j, data, false);
                    }
                }
            }
            temp_table_stack.push(temp);
        }
    }

    public void compares(String operand1, String operand2, String operator, String table_name){
        int index_table = indexOfTable(table_name);
        //System.out.println("Index of table: " +  index_table);
        if (!(index_table == -1)) {
            //System.out.println("NOT A TEMP TABLE");
            int index_column = table_list.get(index_table).getColumnNumber(operand1);
            //System.out.println("index_column: " + index_column);
            List<Integer> index_list = new ArrayList<>();

            index_list = table_list.get(index_table).findIndiciesCompare(index_column, operand2, operator);
            create_empty_temp_clone(table_list.get(index_table));
            Table temp = temp_table_stack.pop();
            for (int i = 0; i < index_list.size(); i++) {
                for (int j = 0; j < table_list.get(index_table).table.size(); j++) {  //gives you the column size
                    if (table_list.get(index_table).table.get(j).get(index_list.get(i)).getClass().getSimpleName().equals("Integer")) { //integer
                        int data = (Integer) table_list.get(index_table).table.get(j).get(index_list.get(i));
                       // System.out.println("Column Num: " + j);
                        temp.insertData(j, Integer.toString(data), true);
                    } else { //string
                        String data = (String) table_list.get(index_table).table.get(j).get(index_list.get(i));
                        temp.insertData(j, data, false);
                    }
                }
            }
            //temp.printTable();
            temp_table_stack.push(temp);
            //temp_table_stack.get(0).printTable();
        }
        else{
            Table temp_table = temp_table_stack.pop();
            int index_column = temp_table.getColumnNumber(operand1);
            List<Integer> index_list = new ArrayList<>();
            index_list = temp_table.findIndiciesCompare(index_column, operand2, operator);
            create_empty_temp_clone(temp_table);
            Table temp = temp_table_stack.pop();
            for (int i = 0; i < index_list.size(); i++) {
                for (int j = 0; j < temp_table.table.size(); j++) {  //gives you the column size
                    if (temp_table.table.get(j).get(index_list.get(i)).getClass().getSimpleName().equals("Integer")) { //integer
                        int data = (Integer) temp_table.table.get(j).get(index_list.get(i));
                        temp.insertData(j, Integer.toString(data), true);
                    } else { //string
                        String data = (String) temp_table.table.get(j).get(index_list.get(i));
                        temp.insertData(j, data, false);
                    }
                }
            }
            //temp.printTable();
            temp_table_stack.push(temp);
        }
    }

    public Table clone_table(Table table){
            //System.out.println("Column Names to duplicate: ");
            //table.getColumnNames();
            Table temp = createTempTable();
            for (int i = 0; i < table.column_name.size(); i++){
                // System.out.println("Column the is cloned: " + table.column_name.get(i));
                //System.out.println("First Data Point for Array: " + table.table.get(i).get(0));
                String type = table.table.get(i).get(0).getClass().getSimpleName();
                if (type.equals("String")){
                    //System.out.println("Column is a string");
                    temp.enterColumns(i,table.column_name.get(i), "VARCHAR");
                }
                else{
                    //System.out.println("Column is integer");
                    temp.enterColumns(i,table.column_name.get(i), "INTEGER");
                }
                //Integer.parseInt(table.table.get(i).get(0));
            }

        //System.out.println("Current Column Names in temp: ");
        //temp.getColumnNames();
        int columns = table.table.size();
        int rows = table.table.get(0).size();
        for (int i=0; i < columns; i++){
            String input_type = table.table.get(i).get(0).getClass().getSimpleName();
            for (int j = 0; j < rows; j++){
                if (input_type.equals("Integer")){
                    //System.out.println("Integer");
                    int data = (Integer) table.table.get(i).get(j);
                    //System.out.println("Original: " + table.table.get(i).get(j) + " New: " + data);
                    String input =  Integer.toString(data);
                    temp.insertData(i, input , true);
                }
                else{
                    String data = (String) table.table.get(i).get(j);
                    //System.out.println("Original: " + table.table.get(i).get(j) + " New: " + data);
                    temp.insertData(i, data , false);
                }
            }
        }
        table.printTable();
        temp.printTable();
        return temp;
    }

    public void create_empty_temp_clone(Table table) {
        //System.out.println("Column Names to duplicate: ");
        //table.getColumnNames();
        Table temp = createTempTable();
        for (int i = 0; i < table.column_name.size(); i++) {
            // System.out.println("Column the is cloned: " + table.column_name.get(i));
            //System.out.println("First Data Point for Array: " + table.table.get(i).get(0));
            String type = table.table.get(i).get(0).getClass().getSimpleName();
            if (type.equals("String")) {
                //System.out.println("Column is a string");
                temp.enterColumns(i, table.column_name.get(i), "VARCHAR");
            } else {
                //System.out.println("Column is integer");
                temp.enterColumns(i, table.column_name.get(i), "INTEGER");
            }
        }
        temp_table_stack.push(temp);
    }


}

//test