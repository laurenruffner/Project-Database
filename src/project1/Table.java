package project1;

import java.util.ArrayList;

public class Table {
    public String table_name;
    public ArrayList<ArrayList> table;
    public ArrayList<String> column_name;

    public Table(String name){
        table_name = name;
        table = null;
        column_name = null;
    }
}
