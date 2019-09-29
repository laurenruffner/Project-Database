package project1;

import java.util.ArrayList;
import java.util.List;

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
        int index = -1;
        for(int i = 0; i < table_names.size(); i++){
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

    public void equality(String operand1, String operand2, String table_name){
        System.out.println("$ " + operand1);
        System.out.println("$ "+ operand2);
        System.out.println("$ " + table_name);

        int index_table = indexOfTable(table_name);
        System.out.println(table_names.get(index_table));
        int index_column = table_list.get(index_table).getColumnNumber(operand1);
        System.out.println(table_list.get(index_table).column_name.get(index_column));

        List<Integer> index_list = new ArrayList<>();
        index_list = table_list.get(index_table).findIndicies(index_column, operand2);
        System.out.println(index_list);

        for (int i =0; i < index_list.size(); i++){
            table_list.get(index_table).dataAtIndex(index_list.get(i));
        }

        //createTable(table_name);
    }


}

//test