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

    public void getColumnNames(){
        System.out.println("In Columns: ");
        System.out.println("    Total Columns: " + column_name.size());
        for(int i=0; i  <  column_name.size(); i++){
            System.out.println("        Column Name:  " + column_name.get(i));
        }
    }

    public void entercolumns(int column_num, String column, String data_type){
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
}
