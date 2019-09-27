package project1;

import java.util.ArrayList;

public class Dbms {
    public ArrayList<Table> table_list;
    public ArrayList<String> table_names;
    public int table_num = 0;

    public Dbms(){
        table_list = new ArrayList<Table>();
        table_names = new ArrayList<String>();
    }

    public void createTable(String name) {

        Table int1 = new Table(name);
        //WTF IS GOING ON
        //String table_nam = new String(name);
        table_list.add(int1);
        table_names.add(name);
    }

    public String getTableName(){
        return table_list.get(table_num).table_name;
    }

    public void iterateTable(){
        table_num++;
    }


}

//test