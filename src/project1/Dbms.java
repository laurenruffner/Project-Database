package project1;

import java.util.ArrayList;

//I need to implement primary id in the create table - Lauren

public class Dbms {
    public ArrayList<Table> table_list;
    public ArrayList<Table> temp_table_list;
    public ArrayList<String> table_names;
    public int table_num = 0;

    public Dbms(){
        table_list = new ArrayList<>();
        table_names = new ArrayList<>();
        temp_table_list = new ArrayList<>();
        //primary_id = new ArrayList<>();
    }

    //get empty table input with table_num ----- table_list.size()
    public int emptyTableLocation(){
        return table_list.size();
    }

    //get table num with specific name -- look through table_nums
    public int indexOfTable(String name){
        int index = -1;
        for(int i = 0; i < table_names.size(); i++){
            //System.out.println("What we are checking: " + table_names.get(i));
            if (name.compareTo(table_names.get(i)) == 0){
                 index = i;
            }
        }

        //-1 if the table doesn't exist
        return index;
    }


    public void createTable(String name) {
        String table_name = name;
        Table int1 = new Table(name);
        table_list.add(int1);
        table_names.add(table_name);
    }

    public String getTableName(){
        return table_list.get(table_num).table_name;
    }


}

//test