package project1;

import java.util.ArrayList;

public class Dbms {
    ArrayList<Table> tables;
    int table_num = 0;

    public void createTable(String name) {
        Table int1 = new Table(name);
        //WTF IS GOING ON
        tables.add(int1);
    }

    public void iterateTable(){
        table_num++;
    }

    public void entercolumns(int column_num, String column, String data_type){

        //HOW TO DEAL WITH DATA TYPES VARCHAR AND INTEGER;
        tables.get(table_num).column_name.add(column_num, column);

        //ArrayList<String> arrayList = new ArrayList<String>();
        if (data_type == "VARCHAR"){
            ArrayList<String> arrayList = new ArrayList<String>();
            tables.get(table_num).table.add(column_num, arrayList);
        }
        else if (data_type == "INTERGER") {
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            tables.get(table_num).table.add(column_num, arrayList);
        }
    }

}

//test