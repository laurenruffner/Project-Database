package project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
//TEST of NEW BRANCH
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
    // Checks for duplicates within the table
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
    // Creates a blank table with a name
    public void createTable(String name) {
        String table_name = name;
        Table int1 = new Table(name);
        table_list.add(int1);
        table_names.add(table_name);
    }
    // Creates a temporary table named temp + a number that increases with each new temp table made
    public Table createTempTable() {
        String table_name = "temp" + temp_table;
        Table int1 = new Table(table_name);
        temp_table++;
        return int1;
    }

    // Conjunction function AND for comparisons
    public void andand(){
        Table temp1 = temp_table_stack.pop();
        Table temp2 = temp_table_stack.pop();

        //temp1.printTable();
        //temp2.printTable();

        create_empty_temp_clone(temp1);
        Table temp = temp_table_stack.pop();
        int columns1  = temp1.table.size();
        int rows1 = temp1.table.get(0).size();
        int rows2 = temp2.table.get(0).size();
        for (int i = 0; i <  rows1; i++) {

            List<Object> temp1_row = new ArrayList();
            for (int j = 0; j < columns1; j++) {
                temp1_row.add(temp1.table.get(j).get(i));
            }
            for (int k = 0; k < rows2; k++) {
                List<Object> temp2_row = new ArrayList();
                for (int l = 0; l < columns1; l++) {
                    temp2_row.add(temp2.table.get(l).get(k));
                }
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

    // Conjunction function OR for comparison
    public void oror(){
        //System.out.println("OROR ----------------------------------------");
        Table temp1 = temp_table_stack.pop();
        Table temp2 = temp_table_stack.pop();

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

    // Difference function for atomic expressions
    public void difference(Table table1, Table table2){
        Table temp1 = table1;
        Table temp2 = table2;

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

    // Union function for atomic expressions
    public void union(Table table1, Table table2){
        Table temp1 = table1;
        Table temp2 = table2;

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

    // Operation function for "=="
    public void equality(String operand1, String operand2, String table_name){
        //System.out.println(table_name);
        //System.out.println(operand1);
        //System.out.println(operand2);
        int index_table = indexOfTable(table_name);
        //System.out.println(index_table);
        int index_column = table_list.get(index_table).getColumnNumber(operand1);
        List<Integer> index_list = new ArrayList<>();
        if(table_list.get(index_table).getColumnNumber(operand2) != -1){
            int column_index2 = table_list.get(index_table).getColumnNumber(operand2);
            index_list = table_list.get(index_table).findIndicies_columns(index_column, column_index2);
            create_empty_temp_clone(table_list.get(index_table));
            Table temp = temp_table_stack.pop();

            for (int i = 0; i < index_list.size(); i++) {
                for (int j = 0; j < table_list.get(index_table).table.size(); j++) {  //gives you the column size
                    if (table_list.get(index_table).table.get(j).get(index_list.get(i)).getClass().getSimpleName().equals("Integer")) {
                        int data = (Integer) table_list.get(index_table).table.get(j).get(index_list.get(i));
                        temp.insertData(j, Integer.toString(data), true);
                    } else {
                        String data = (String) table_list.get(index_table).table.get(j).get(index_list.get(i));
                        temp.insertData(j, data, false);
                    }
                }
            }
            temp_table_stack.push(temp);
        }
        else {
            index_list = table_list.get(index_table).findIndicies(index_column, operand2);
            create_empty_temp_clone(table_list.get(index_table));
            Table temp = temp_table_stack.pop();

            for (int i = 0; i < index_list.size(); i++) {
                for (int j = 0; j < table_list.get(index_table).table.size(); j++) {  //gives you the column size
                    if (table_list.get(index_table).table.get(j).get(index_list.get(i)).getClass().getSimpleName().equals("Integer")) {
                        int data = (Integer) table_list.get(index_table).table.get(j).get(index_list.get(i));
                        temp.insertData(j, Integer.toString(data), true);
                    } else {
                        String data = (String) table_list.get(index_table).table.get(j).get(index_list.get(i));
                        temp.insertData(j, data, false);
                    }
                }
            }
            temp_table_stack.push(temp);
        }
    }

    // Checks equality "==" for temp tables
    public void equality_from_temp(String operand1, String operand2, Table table){

        //System.out.println("**************** EQUALITY FROM TEMP*********************");
        int index_column = table.getColumnNumber(operand1);

        List<Integer> index_list = new ArrayList<>();
        if(table.getColumnNumber(operand2) != -1) {
            int index_column2 = table.getColumnNumber(operand2);
            index_list = table.findIndicies_columns(index_column, index_column2);

            create_empty_temp_clone(table);
            Table temp = temp_table_stack.pop();

            for (int i = 0; i < index_list.size(); i++) {
                for (int j = 0; j < table.table.size(); j++) {  //gives you the column size
                    if (table.table.get(j).get(index_list.get(i)).getClass().getSimpleName().equals("Integer")) {
                        int data = (Integer) table.table.get(j).get(index_list.get(i));
                        temp.insertData(j, Integer.toString(data), true);
                    } else {
                        String data = (String) table.table.get(j).get(index_list.get(i));
                        temp.insertData(j, data, false);
                    }
                }
            }
            temp_table_stack.push(temp);
        }
        else{
            index_list = table.findIndicies(index_column, operand2);
            create_empty_temp_clone(table);
            Table temp = temp_table_stack.pop();

            for (int i = 0; i < index_list.size(); i++) {
                for (int j = 0; j < table.table.size(); j++) {  //gives you the column size
                    if (table.table.get(j).get(index_list.get(i)).getClass().getSimpleName().equals("Integer")) {
                        int data = (Integer) table.table.get(j).get(index_list.get(i));
                        temp.insertData(j, Integer.toString(data), true);
                    } else {
                        String data = (String) table.table.get(j).get(index_list.get(i));
                        temp.insertData(j, data, false);
                    }
                }
            }
            temp_table_stack.push(temp);
        }
    }

    // Checks inequality "!=" for temp tables
    public void not_equality_from_temp(String operand1, String operand2, Table table){
        int index_column = table.getColumnNumber(operand1);

        List<Integer> index_list = new ArrayList<>();
        // FIND everything but operand2
        if (table.getColumnNumber(operand2) != -1){
            int column_index2 = table.getColumnNumber(operand2);
            index_list = table.findAllButIndicies_column(index_column, column_index2);

            create_empty_temp_clone(table);
            Table temp = temp_table_stack.pop();

            for (int i = 0; i < index_list.size(); i++) {
                for (int j = 0; j < table.table.size(); j++) {  //gives you the column size
                    if (table.table.get(j).get(index_list.get(i)).getClass().getSimpleName().equals("Integer")) {
                        int data = (Integer) table.table.get(j).get(index_list.get(i));
                        temp.insertData(j, Integer.toString(data), true);
                    } else { // string
                        String data = (String) table.table.get(j).get(index_list.get(i));
                        temp.insertData(j, data, false);
                    }
                }
            }
            temp_table_stack.push(temp);
        }
        else {
            index_list = table.findAllButIndicies(index_column, operand2);
            create_empty_temp_clone(table);
            Table temp = temp_table_stack.pop();

            for (int i = 0; i < index_list.size(); i++) {
                for (int j = 0; j < table.table.size(); j++) {  //gives you the column size
                    if (table.table.get(j).get(index_list.get(i)).getClass().getSimpleName().equals("Integer")) {
                        int data = (Integer) table.table.get(j).get(index_list.get(i));
                        temp.insertData(j, Integer.toString(data), true);
                    } else { // string
                        String data = (String) table.table.get(j).get(index_list.get(i));
                        temp.insertData(j, data, false);
                    }
                }
            }
            temp_table_stack.push(temp);
        }
    }

    // Inequality function "!="
    public void not_equality(String operand1, String operand2, String table_name){

        int index_table = indexOfTable(table_name);
        int index_column = table_list.get(index_table).getColumnNumber(operand1);

        List<Integer> index_list = new ArrayList<>();
        // FIND everything but operand2
        if (table_list.get(index_table).getColumnNumber(operand2) != -1){
            int index_column2 = table_list.get(index_table).getColumnNumber(operand2);
            index_list = table_list.get(index_table).findAllButIndicies_column(index_column, index_column2);

            create_empty_temp_clone(table_list.get(index_table));
            Table temp = temp_table_stack.pop();

            for (int i = 0; i < index_list.size(); i++) {
                for (int j = 0; j < table_list.get(index_table).table.size(); j++) {  //gives you the column size
                    if (table_list.get(index_table).table.get(j).get(index_list.get(i)).getClass().getSimpleName().equals("Integer")) {
                        int data = (Integer) table_list.get(index_table).table.get(j).get(index_list.get(i));
                        temp.insertData(j, Integer.toString(data), true);
                    } else { // string
                        String data = (String) table_list.get(index_table).table.get(j).get(index_list.get(i));
                        temp.insertData(j, data, false);
                    }
                }
            }
            temp_table_stack.push(temp);
        }
        else {
            index_list = table_list.get(index_table).findAllButIndicies(index_column, operand2);
            create_empty_temp_clone(table_list.get(index_table));
            Table temp = temp_table_stack.pop();

            for (int i = 0; i < index_list.size(); i++) {
                for (int j = 0; j < table_list.get(index_table).table.size(); j++) {  //gives you the column size
                    if (table_list.get(index_table).table.get(j).get(index_list.get(i)).getClass().getSimpleName().equals("Integer")) {
                        int data = (Integer) table_list.get(index_table).table.get(j).get(index_list.get(i));
                        temp.insertData(j, Integer.toString(data), true);
                    } else { // string
                        String data = (String) table_list.get(index_table).table.get(j).get(index_list.get(i));
                        temp.insertData(j, data, false);
                    }
                }
            }
            temp_table_stack.push(temp);
        }

    }

    // Compares two operands using the operator
    public void compares(String operand1, String operand2, String operator, String table_name){
        int index_table = indexOfTable(table_name);
        int index_column = table_list.get(index_table).getColumnNumber(operand1);

        List<Integer> index_list = new ArrayList<>();
        if (table_list.get(index_table).getColumnNumber(operand2) != -1){
            int index_column2 = table_list.get(index_table).getColumnNumber(operand2);
            index_list = table_list.get(index_table).findIndiciesCompare_column(index_column, index_column2, operator);
            create_empty_temp_clone(table_list.get(index_table));
            Table temp = temp_table_stack.pop();
            for (int i = 0; i < index_list.size(); i++) {
                for (int j = 0; j < table_list.get(index_table).table.size(); j++) {  //gives you the column size
                    if (table_list.get(index_table).table.get(j).get(index_list.get(i)).getClass().getSimpleName().equals("Integer")) { //integer
                        int data = (Integer) table_list.get(index_table).table.get(j).get(index_list.get(i));
                        temp.insertData(j, Integer.toString(data), true);
                    } else { //string
                        String data = (String) table_list.get(index_table).table.get(j).get(index_list.get(i));
                        temp.insertData(j, data, false);
                    }
                }
            }
            temp_table_stack.push(temp);
        }
        else {
            index_list = table_list.get(index_table).findIndiciesCompare(index_column, operand2, operator);
            create_empty_temp_clone(table_list.get(index_table));
            Table temp = temp_table_stack.pop();
            for (int i = 0; i < index_list.size(); i++) {
                for (int j = 0; j < table_list.get(index_table).table.size(); j++) {  //gives you the column size
                    if (table_list.get(index_table).table.get(j).get(index_list.get(i)).getClass().getSimpleName().equals("Integer")) { //integer
                        int data = (Integer) table_list.get(index_table).table.get(j).get(index_list.get(i));
                        temp.insertData(j, Integer.toString(data), true);
                    } else { //string
                        String data = (String) table_list.get(index_table).table.get(j).get(index_list.get(i));
                        temp.insertData(j, data, false);
                    }
                }
            }
            temp_table_stack.push(temp);
        }
    }

    // Compares two operands using operator in temp table
    public void compares_from_temp(String operand1, String operand2, String operator, Table table){
        //System.out.println("TABLE: ");
        //table.printTable();
        int index_column = table.getColumnNumber(operand1);
        //System.out.println("index_column: " + index_column);
        List<Integer> index_list = new ArrayList<>();
        if (table.getColumnNumber(operand2) !=  -1){
            //System.out.println("IN TABLE");
            int index_column2 = table.getColumnNumber(operand2);
            index_list = table.findIndiciesCompare_column(index_column, index_column2, operator);
            create_empty_temp_clone(table);
            Table temp = temp_table_stack.pop();
            for (int i = 0; i < index_list.size(); i++) {
                //System.out.println("IN SIZE");
                for (int j = 0; j < table.table.size(); j++) {  //gives you the column size
                    if (table.table.get(j).get(index_list.get(i)).getClass().getSimpleName().equals("Integer")) { //integer
                        int data = (Integer) table.table.get(j).get(index_list.get(i));
                        temp.insertData(j, Integer.toString(data), true);
                    } else { //string
                        String data = (String) table.table.get(j).get(index_list.get(i));
                        temp.insertData(j, data, false);
                    }
                }
                //temp.printTable();
            }
            temp_table_stack.push(temp);
        }
        else {
            index_list = table.findIndiciesCompare(index_column, operand2, operator);
            create_empty_temp_clone(table);
            Table temp = temp_table_stack.pop();
            System.out.println("");
            for (int i = 0; i < index_list.size(); i++) {
                for (int j = 0; j < table.table.size(); j++) {  //gives you the column size
                    if (table.table.get(j).get(index_list.get(i)).getClass().getSimpleName().equals("Integer")) { //integer
                        int data = (Integer) table.table.get(j).get(index_list.get(i));
                        temp.insertData(j, Integer.toString(data), true);
                    } else { //string
                        String data = (String) table.table.get(j).get(index_list.get(i));
                        temp.insertData(j, data, false);
                    }
                }
            }
            temp_table_stack.push(temp);
        }
    }

    // The product of two tables function "*"
    public void product(Table table_name1, Table table_name2) {
        Table temp1 = table_name1;
        Table temp2 = table_name2;
        //System.out.println("Table1: " + table_name1.table_name + " Table 2: " + table_name2.table_name);

        create_empty_temp_clone(temp1);
        Table temp = temp_table_stack.pop();


        int amountToADD = temp2.column_name.size();
        int addColumnIndex = temp.column_name.size() - 1;
        for (int i = 0; i < amountToADD; i++) {
            addColumnIndex++;
            String type = temp2.table.get(i).get(0).getClass().getSimpleName();
            if (type.equals("String")){
                temp.enterColumns(addColumnIndex,temp2.column_name.get(i), "VARCHAR");
            }
            else{
                temp.enterColumns(addColumnIndex,temp2.column_name.get(i), "INTEGER");
            }
        }
        //temp.printTable();

        int columns1 = temp1.table.size();
        int columns2 = temp2.table.size();
        int rows1 = temp1.table.get(0).size();
        int rows2 = temp2.table.get(0).size();
        //System.out.println(rows1*rows2);
        for(int m = 0; m < rows2; m++) {
            for (int i = 0; i < rows1; i++) {
                List<Object> row_of_temp1 = new ArrayList<>();

                //System.out.println(columns1);
                //System.out.println(rows1);
                for (int j = 0; j < columns1; j++) {
                    row_of_temp1.add(temp1.table.get(j).get(i));
                }

                for (int l = 0; l < columns2; l++) {
                    row_of_temp1.add(temp2.table.get(l).get(m));
                }
                //System.out.println(row_of_temp1);
                for(int z = 0; z < row_of_temp1.size(); z++) {
                    boolean integerData = row_of_temp1.get(z).getClass().getSimpleName().equals("Integer");
                    if (!integerData) {
                        temp.insertData(z, row_of_temp1.get(z).toString(), false);
                    } else {
                        temp.insertData(z, Integer.toString((Integer) row_of_temp1.get(z)), true);

                    }
                }
            }
        }
        temp_table_stack.push(temp);
    }

    // Clones a table with every single data that was in the original
    public Table clone_table(Table table){
            Table temp = createTempTable();
            for (int i = 0; i < table.column_name.size(); i++){
                String type = table.table.get(i).get(0).getClass().getSimpleName();
                //System.out.println("Type: " + type +  " Column Name: " + table.column_name.get(i));
                if (type.equals("String")){ // is string
                    temp.enterColumns(i,table.column_name.get(i), "VARCHAR");
                }
                else{ // is integer
                    temp.enterColumns(i,table.column_name.get(i), "INTEGER");
                }
            }
        int columns = table.table.size();
        int rows = table.table.get(0).size();
        for (int i=0; i < columns; i++){
            String input_type = table.table.get(i).get(0).getClass().getSimpleName();
            for (int j = 0; j < rows; j++){
                if (input_type.equals("Integer")){ // integer
                    int data = (Integer) table.table.get(i).get(j);
                    String input =  Integer.toString(data);
                    temp.insertData(i, input , true);
                }
                else{
                    String data = table.table.get(i).get(j).toString();
                    temp.insertData(i, data , false);
                }
            }
        }
        return temp;
    }

    // Creates a temp table that clones the names and types of the original table but includes none of the original data
    public void create_empty_temp_clone(Table table) {
        Table temp = createTempTable();
        for (int i = 0; i < table.column_name.size(); i++) {
            String type = table.table.get(i).get(0).getClass().getSimpleName();
            if (type.equals("String")) { // string
                temp.enterColumns(i, table.column_name.get(i), "VARCHAR");
            } else { // integer
                temp.enterColumns(i, table.column_name.get(i), "INTEGER");
            }
        }
        temp_table_stack.push(temp);
    }


}

//test