package project1;

import java.util.ArrayList;

public class Dbms {
    public ArrayList<Table> table_list;
    public ArrayList<Table> temp_table_list;
    public ArrayList<String> table_names;
    public int table_num = 0;



    public Dbms(){
        table_list = new ArrayList<>();
        table_names = new ArrayList<>();
        temp_table_list = new ArrayList<>();
    }

    //get empty table input with table_num ----- table_list.size()
    public int emptyTableLocation(){
        return table_list.size();
    }

    //get table num with specific name -- look through table_nums
    public int indexOfTable(String name){
        for(int i = 0; i < table_names.size(); i++){
            if (name == table_names.get(i)){
                return i;
            }
        }
        //-1 if the table doesn't exist
        return -1;
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