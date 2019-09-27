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
        column_name.add(column_num, column);

        //ArrayList<String> arrayList = new ArrayList<String>();
        if (data_type == "VARCHAR"){
            ArrayList<String> arrayList = new ArrayList<String>();
            table.add(column_num, arrayList);
        }
        else if (data_type == "INTEGER") {
            System.out.println("Enter Integer");
            ArrayList<Integer> arrayInteger = new ArrayList<>();
            table.add(column_num, arrayInteger);
        }

    }

    // takes the column_num and the input (always parsed into a string) and then use boolean to know what to cast an int from string.
    public void insertData(int column_num, String input, boolean isInteger){

    }
}
