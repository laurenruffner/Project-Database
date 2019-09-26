package project1;

import java.util.ArrayList;
import java.util.Hashtable;

public class Dbms {
    Hashtable<Integer, ArrayList> table;

    public void entercolumns(int column_num, String column, String data_type){

        //HOW TO DEAL WITH DATA TYPES VARCHAR AND INTEGER;

        ArrayList<String> arrayList = new ArrayList<String>();
//        if (data_type == "VARCHAR"){
//            ArrayList<String> arrayList = new ArrayList<String>();
//        }
//        else {
//            ArrayList<Integer> arrayList = new ArrayList<Integer>();
//        }
        arrayList.add(0,column);
        table.put(column_num, arrayList);
    }

    public void enterdata(int entry_order_num){

    }


}

//test