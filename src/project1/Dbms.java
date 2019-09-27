package project1;

import java.util.ArrayList;

public class Dbms {
    public ArrayList<Table> table_list;
    public int table_num = 0;

    public void createTable(String name) {
        table_list = new ArrayList<Table>();
        Table int1 = new Table(name);
        //WTF IS GOING ON
        table_list.add(int1);
    }

    public String getTable(){
        return table_list.get(0).table_name;
    }

    public void iterateTable(){
        table_num++;
    }


}

//test