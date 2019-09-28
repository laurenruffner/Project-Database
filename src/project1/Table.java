package project1;

import java.util.ArrayList;

public class Table {
    public String table_name;
    public ArrayList<ArrayList> table;
    public ArrayList<String> column_name;

    public Table(String name){
        table = new ArrayList<ArrayList>();
        column_name = new ArrayList<String>();
        table_name = name;
    }

    // This returns the index of the column of a specific name.
    // This can be used to pull the column from the table due to both tables having the same indices
    public int getColumnNumber(String c_name){
        for(int i=0; i < column_name.size(); i++){
            if (c_name == column_name.get(i)){
                return i;
            }
        }
        return -1;
    }

    //Print Statement to test the column names of a certain table
    public void getColumnNames(){
        System.out.println("In Columns: ");
        System.out.println("    Total Columns: " + column_name.size());
        for(int i=0; i  <  column_name.size(); i++){
            System.out.println("        Column Name:  " + column_name.get(i));
        }
    }

    // this allows you to enter columns into the table
    public void enterColumns(int column_num, String column, String data_type){
        //HOW TO DEAL WITH DATA TYPES VARCHAR AND INTEGER;
        //System.out.println("Enter Columns");
        column_name.add(column_num, column);

        //ArrayList<String> arrayList = new ArrayList<String>();
        if (data_type.compareTo("VARCHAR") == 0){
            //System.out.println("Adding string arrayList");
            ArrayList<String> arrayList = new ArrayList<String>();
            table.add(column_num, arrayList);
        }
        else if (data_type.compareTo("INTEGER") == 0) {
            //System.out.println("Adding integer ArrayList");
            ArrayList<Integer> arrayInteger = new ArrayList<>();
            table.add(column_num, arrayInteger);
        }


    }

    // takes the column_num and the input (always parsed into a string) and then use boolean to know what to cast an int from string.
    public void insertData(int column_num, String input, boolean isInteger){
        //System.out.println("Column num" + column_num);
        //getColumnNames();
        //System.out.println("Size of table: " + table.size());
        //System.out.println("In insert data");
        if (Boolean.compare(isInteger, true) == 0){
            //System.out.println("Is Integer");
            Integer int_input = Integer.valueOf(input);
            table.get(column_num).add(int_input);
        }
        else {
            //System.out.println("Is string");
            table.get(column_num).add(input);
        }
        //System.out.println("End of insert");
    }

    public void printTable(){
        int total_rows = table.get(0).size();
        int total_columns = column_name.size();
        System.out.println("--------------------" + table_name + "---------------");
        for (int j=0; j< column_name.size(); j++){
            System.out.print("|" + column_name.get(j) + "|");
        }
        System.out.println();
        for (int k=0; k < total_rows; k++){
            for (int i = 0; i < total_columns; i++) {
                System.out.print("|" + table.get(i).get(k) + "|" );
            }
            System.out.println();
        }
    }
}
