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

    public void createTable(String name) {
        String table_name = name;
        Table int1 = new Table(name);
        table_list.add(int1);
        table_names.add(table_name);
    }

    public Table createTempTable() {
        String table_name = "temp" + temp_table;
        System.out.println("Temp Table Name: " + table_name);
        Table int1 = new Table(table_name);
        temp_table++;
        return int1;
    }

    public void equality(String operand1, String operand2, String table_name){
//        System.out.println("$ " + operand1);
//        System.out.println("$ "+ operand2);
//        System.out.println("$ " + table_name);

        int index_table = indexOfTable(table_name);
 //       System.out.println(table_names.get(index_table));
        int index_column = table_list.get(index_table).getColumnNumber(operand1);
//        System.out.println(table_list.get(index_table).column_name.get(index_column));

        List<Integer> index_list = new ArrayList<>();
        index_list = table_list.get(index_table).findIndicies(index_column, operand2);
//        System.out.println(index_list);

        create_empty_temp_clone(table_list.get(index_table));
        Table temp = temp_table_stack.pop();

//        for (int i =0; i < index_list.size(); i++){
//            table_list.get(index_table).dataAtIndex(index_list.get(i));
//            if ()
//            temp.insertData();
//        }



        //temp.

        //createTable(table_name);
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
                    System.out.println("Column is integer");
                    temp.enterColumns(i,table.column_name.get(i), "INTEGER");
                }
                //Integer.parseInt(table.table.get(i).get(0));
            }

        System.out.println("Current Column Names in temp: ");
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
                System.out.println("Column is integer");
                temp.enterColumns(i, table.column_name.get(i), "INTEGER");
            }
        }
        temp_table_stack.push(temp);
    }

}

//test