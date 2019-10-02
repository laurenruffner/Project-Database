package project1;

import java.util.ArrayList;
import java.util.List;

public class Table {
    public String table_name;
    public ArrayList<ArrayList> table;
    public ArrayList<String> column_name;
    public ArrayList<String> primary_id;

    public Table(String name){
        table = new ArrayList<ArrayList>();
        column_name = new ArrayList<String>();
        primary_id = new ArrayList<String>();
        table_name = name;
    }

    //THIS RETURNS THE INDEX THAT CAN THEN BE THROWN INTO dataAtIndex TO RETRIEVE THE DATA WITH A SPECIFIC PRIMARY ID
    public int getPrimaryIdIndex(ArrayList<String> search){
        int column = 0;
        int row = 0;
        int total_row = table.get(0).size();
        int column_num = getColumnNumber(primary_id.get(0));
        for (int i = 0; i < total_row; i++) {
            //System.out.println("Loop");
            try {
                //System.out.println("Comparing: " + search.get(column));
                //System.out.println("To: " + table.get(column_num).get(row));
                Integer.parseInt(search.get(column));
                if (Integer.valueOf(search.get(column)) == table.get(column_num).get(row)){
                    //System.out.println("Same");
                    column++;
                    for(int j = 1; j < search.size(); j++){
                        int column_num_temp = getColumnNumber(primary_id.get(j));
                        try {
                            //System.out.println("Comparing: " + search.get(column));
                            //System.out.println("To: " + table.get(column_num_temp).get(row));
                            Integer.parseInt(search.get(column));
                            if (Integer.valueOf(search.get(column)) == table.get(column_num_temp).get(row)){
                              //  System.out.println("Same");
                                column++;
                                if (j == (search.size() - 1)){
                                    return row;
                                }
                            }
                            else{
                                //System.out.println("Not Same");
                                column--;
                                row++;
                                break;
                            }
                        }
                        catch (NumberFormatException e) {
                            if (search.get(column).compareTo((String) table.get(column_num_temp).get(row)) == 0){
                                column++;
                                //System.out.println("Same");
                                if (j == (search.size() - 1)){
                                    return row;
                                }
                            }
                            else{
                                //System.out.println("Different");
                                column--;
                                row++;
                                break;
                            }
                        }
                        if (j == (search.size() - 1)){
                            return row;
                        }
                    }
                    if (search.size() == 1){
                        return row;
                    }
                }
                else{
                    //System.out.println("Not Same");
                    row++;
                }
            }
            catch (NumberFormatException e) {
                if (search.get(column).compareTo((String) table.get(column_num).get(row)) == 0){
                    //System.out.println("Here 1");
                    //System.out.println("Same");
                    column++;
                    for(int j = 1; j < search.size(); j++){
                        int column_num_temp = getColumnNumber(primary_id.get(j));
                        try {
                            //System.out.println("Comparing: " + search.get(column));
                            //System.out.println("To: " + table.get(column_num_temp).get(row));
                            Integer.parseInt(search.get(column));
                            if (Integer.valueOf(search.get(column)) == table.get(column_num_temp).get(row)){
                                //System.out.println("Same");
                                column++;
                                if (j == (search.size() - 1)){
                                    return row;
                                }
                            }
                            else{
                                //System.out.println("Not Same");
                                column--;
                                row++;
                                break;
                            }
                        }
                        catch (NumberFormatException s) {
                            if (search.get(column).compareTo((String) table.get(column_num_temp).get(row)) == 0){
                                column++;
                                //System.out.println("Same");
                                if (j == (search.size() - 1)){
                                    return row;
                                }
                            }
                            else{
                                //System.out.println("Different11");
                                //System.out.println("i" + i + "total rows" + total_row);
                                row++;
                                column--;
                                break;
                            }
                        }
                        if (j == (search.size() - 1)){
                            //System.out.println("Here Exit");
                            return row;
                        }
                    }
                    if (search.size() == 1){
                        //System.out.println("Here Exit2");
                        return row;
                    }
                }
                else{
                    //System.out.println("Different");
                    row++;
                }
            }

        }

        //System.out.println("Exit");
        return row;
    }

    public int getRowIndex(List<Object> row_to_look_for){
        int row = -1;
        int columns = table.size();
        int rows = table.get(0).size();
        for (int j=0; j < rows; j++) {
            for (int i = 0; i < columns; i++){
                if((row_to_look_for.get(i).equals(table.get(i).get(j))) && (i == columns-1)){
                    return j;
                }
                else if (row_to_look_for.get(i).equals(table.get(i).get(j))){
                    continue;
                }
                else{
                    break;
                }
            }
        }
        return -1;
    }

    //THIS RETURNS THE DATA IN A VISUAL WAY AT A SPECIFIC INDEX --- Tweety, bird , 1
    public void dataAtIndex (int index){
        for (int j = 0; j < column_name.size(); j++){
            System.out.print("|" + column_name.get(j) + "|");
        }
        System.out.println();
        for(int i = 0; i < column_name.size(); i ++){
            System.out.print("|" + table.get(i).get(index) + "|");
        }
        System.out.println();
    };
    public List<Object> dataReturn (int index){
        List<Object> row = new ArrayList<>();
        for (int j = 0; j < column_name.size(); j++){
            System.out.print("|" + column_name.get(j) + "|");
        }
        System.out.println();
        for(int i = 0; i < column_name.size(); i ++){
            row.add(table.get(i).get(index));
            System.out.print("|" + table.get(i).get(index) + "|");
        }
        System.out.println();
        return row;
    };

    // This returns the index of the column of a specific name.
    // This can be used to pull the column from the table due to both tables having the same indices
    public int getColumnNumber(String c_name){
        for(int i=0; i < column_name.size(); i++){
            if (c_name.compareTo(column_name.get(i)) == 0){
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
        column_name.add(column_num, column);

        if (data_type.compareTo("VARCHAR") == 0){
            ArrayList<String> arrayList = new ArrayList<String>();
            table.add(column_num, arrayList);
        }
        else if (data_type.compareTo("INTEGER") == 0) {
            ArrayList<Integer> arrayInteger = new ArrayList<>();
            table.add(column_num, arrayInteger);
        }
    }

    // takes the column_num and the input (always parsed into a string) and then use boolean to know what to cast an int from string.
    public void insertData(int column_num, String input, boolean isInteger){
        if (Boolean.compare(isInteger, true) == 0){
            Integer int_input = Integer.valueOf(input);
            table.get(column_num).add(int_input);
        }
        else {
            table.get(column_num).add(input);
        }
    }

    public List<Integer> findIndicies(int column_num, String find){
        List<Integer> index_list = new ArrayList<>();
        int total_row = table.get(0).size();
        for(int j =0; j < total_row; j++){
            try {
                //System.out.println("Comparing: " + find + " to: " + table.get(column_num).get(j));
                Integer.parseInt(find);
                if (Integer.valueOf(find) == table.get(column_num).get(j)){
                    //System.out.println("Same");
                    index_list.add(j);
                }
            }
            catch (NumberFormatException e) {
                if (find.compareTo((String) table.get(column_num).get(j)) == 0){
                    index_list.add(j);
                    //System.out.println("Same");
                }
            }
        }
        return index_list;
    }
    public List<Integer> findAllButIndicies(int column_num, String find){
        List<Integer> index_list = new ArrayList<>();
        int total_row = table.get(0).size();
        for(int j =0; j < total_row; j++){
            try {
                //System.out.println("Comparing: " + find + " to: " + table.get(column_num).get(j));
                Integer.parseInt(find);
                if (Integer.valueOf(find) != table.get(column_num).get(j)){
                    //System.out.println("Same");
                    index_list.add(j);
                }
            }
            catch (NumberFormatException e) {
                if (find.compareTo((String) table.get(column_num).get(j)) != 0){
                    index_list.add(j);
                    //System.out.println("Same");
                }
            }
        }
        return index_list;
    }

    public List<Integer> findIndiciesCompare(int column_num, String find, String op){
        List<Integer> index_list = new ArrayList<>();
        int total_row = table.get(0).size();
        for(int j = 0; j < total_row; j++){
            try { //is integer
                Integer.parseInt(find);
                if (op.equals(">=")){
                    if ((Integer)(table.get(column_num).get(j)) >= Integer.valueOf(find) ) {
                        index_list.add(j);
                    }
                }
                else if (op.equals("<=")) {
                    if ((Integer)(table.get(column_num).get(j)) <= Integer.valueOf(find) ) {
                        index_list.add(j);
                    }
                }
                else if (op.equals(">")) {
                    if ((Integer)(table.get(column_num).get(j)) > Integer.valueOf(find) ) {
                        index_list.add(j);
                    }
                }
                else if (op.equals("<")) {
                    if ((Integer)(table.get(column_num).get(j)) < Integer.valueOf(find) ) {
                        index_list.add(j);
                    }
                }
            }
            catch (NumberFormatException e) { //is string
                if (op.equals(">=")){
                    //System.out.println(">= string");
                    //System.out.println(find.compareTo((String) table.get(column_num).get(j)));
                    if (find.compareTo((String) table.get(column_num).get(j)) <= 0){
                        index_list.add(j);
                    }
                }
                else if (op.equals("<=")) {
                    if (find.compareTo((String) table.get(column_num).get(j)) >= 0){
                        index_list.add(j);
                    }
                }
                else if (op.equals(">")) {
                    if (find.compareTo((String) table.get(column_num).get(j)) < 0){
                        index_list.add(j);
                    }
                }
                else if (op.equals("<")) {
                    if (find.compareTo((String) table.get(column_num).get(j)) > 0){
                        index_list.add(j);
                    }
                }
            }
        }
        return index_list;
    }


    public void printTable(){
        int total_rows = table.get(0).size();
        int total_columns = column_name.size();
        System.out.println("--------------------" + table_name + "---------------");
        for (int j=0; j< column_name.size(); j++){
            System.out.print("|" + column_name.get(j) + "|");
        }
        System.out.println();
        for (int k=0; k < total_rows; k++){
            for (int i = 0; i < total_columns; i++) {
                System.out.print("|" + table.get(i).get(k) + "|" );
            }
            System.out.println();
        }
    }
}
