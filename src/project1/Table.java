package project1;


import javax.json.*;
import java.util.*;

public class Table {

    public String table_name;
    public ArrayList<ArrayList> table;
    public ArrayList<String> column_name;
    public ArrayList<String> primary_id; //This holds how the primary ID exists

    public Table(String name){
        table = new ArrayList<ArrayList>();
        column_name = new ArrayList<String>();
        primary_id = new ArrayList<String>();
        table_name = name;
    }

    //THIS RETURNS THE INDEX THAT CAN THEN BE THROWN INTO dataAtIndex TO RETRIEVE THE DATA WITH A SPECIFIC PRIMARY ID
    // WILL BE USED LATER. CURRENTLY UNUSED
    public int getPrimaryIdIndex(ArrayList<String> search){
        int column = 0;
        int row = 0;
        int total_row = table.get(0).size();
        int column_num = getColumnNumber(primary_id.get(0));
        for (int i = 0; i < total_row; i++) {
            try {
                Integer.parseInt(search.get(column));
                if (Integer.valueOf(search.get(column)) == table.get(column_num).get(row)){
                    column++;
                    for(int j = 1; j < search.size(); j++){
                        int column_num_temp = getColumnNumber(primary_id.get(j));
                        try {
                            Integer.parseInt(search.get(column));
                            if (Integer.valueOf(search.get(column)) == table.get(column_num_temp).get(row)){
                                column++;
                                if (j == (search.size() - 1)){
                                    return row;
                                }
                            }
                            else{
                                column--;
                                row++;
                                break;
                            }
                        }
                        catch (NumberFormatException e) {
                            if (search.get(column).compareTo((String) table.get(column_num_temp).get(row)) == 0){
                                column++;
                                if (j == (search.size() - 1)){
                                    return row;
                                }
                            }
                            else{
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
                    row++;
                }
            }
            catch (NumberFormatException e) {
                if (search.get(column).compareTo((String) table.get(column_num).get(row)) == 0){
                    column++;
                    for(int j = 1; j < search.size(); j++){
                        int column_num_temp = getColumnNumber(primary_id.get(j));
                        try {
                            Integer.parseInt(search.get(column));
                            if (Integer.valueOf(search.get(column)) == table.get(column_num_temp).get(row)){
                                column++;
                                if (j == (search.size() - 1)){
                                    return row;
                                }
                            }
                            else{
                                column--;
                                row++;
                                break;
                            }
                        }
                        catch (NumberFormatException s) {
                            if (search.get(column).compareTo((String) table.get(column_num_temp).get(row)) == 0){
                                column++;
                                if (j == (search.size() - 1)){
                                    return row;
                                }
                            }
                            else{
                                row++;
                                column--;
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
                    row++;
                }
            }
        }
        return row;
    }

    //THIS FUNCTION TAKES IN THE DATA OF A ROW IN THE FORM OF A LIST OF OBJECTS AND RETURNS THE INDEX OF THE ROW THAT IT EXISTS AT IN THE TABLE
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

    //THIS RETURNS A LIST OF OBJECTS AT A CERTAIN ROW INDEX IN THE TABLE AKA THE ROW IN THE FORM OF A LIST OF OBJECTS
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
    public List<Integer> findGreaterThanIndicies(int column_num, int find) {
        List<Integer> index_list = new ArrayList<>();
        int total_row = table.get(0).size();
        for (int j = 0; j < total_row; j++) {
                //System.out.println("Comparing: " + find + " to: " + table.get(column_num).get(j));
                if (find > Integer.parseInt((String) table.get(column_num).get(j))) {
                    System.out.println("comparing to find" + Integer.parseInt((String) table.get(column_num).get(j)));
                    index_list.add(j);
                }
        }
        return index_list;
    }


    //THIS FUNCTION GOES THROUGH THE TABLE AT A COLUMN AND SEARCHES FOR A PARTICULAR VALUE AND RETURNS THE INDICES IN THE FORM OF A LIST
    public List<Integer> findIndicies(int column_num, String find){
        List<Integer> index_list = new ArrayList<>();
        int total_row = table.get(0).size();
        for(int j =0; j < total_row; j++){
            try {
                Integer.parseInt(find);
                if (Integer.valueOf(find) == table.get(column_num).get(j)){
                    index_list.add(j);
                }
            }
            catch (NumberFormatException e) {
                if (find.compareTo((String) table.get(column_num).get(j)) == 0){
                    index_list.add(j);
                }
            }
        }
        return index_list;
    }

    //THIS FUNCTION TAKES TWO COLUMNS AND RETURNS THE INDICES WHERE THE COLUMNS ARE EQUAL
    public List<Integer> findIndicies_columns(int column_num, int column_num_2){
        List<Integer> index_list = new ArrayList<>();
        int total_row = table.get(0).size();
        for(int j =0; j < total_row; j++){
            if (table.get(column_num_2).get(j).equals(table.get(column_num).get(j))){
                index_list.add(j);
            }
        }
        return index_list;
    }

    //FINDS ALL THE INDICES THAT DON'T HAVE THE OBJECT FINS IN THE COLUMN WITH THE COLUMN NUM
    public List<Integer> findAllButIndicies(int column_num, String find){
        List<Integer> index_list = new ArrayList<>();
        int total_row = table.get(0).size();
        for(int j =0; j < total_row; j++){
            try {
                Integer.parseInt(find);
                if (Integer.valueOf(find) != table.get(column_num).get(j)){
                    index_list.add(j);
                }
            }
            catch (NumberFormatException e) {
                if (find.compareTo((String) table.get(column_num).get(j)) != 0){
                    index_list.add(j);
                }
            }
        }
        return index_list;
    }

    //DOES THE SAME THING AS FINDALLBUTINDICIES BUT COMPARES 2 COLUMNS
    public List<Integer> findAllButIndicies_column(int column_num, int column_num2){
        List<Integer> index_list = new ArrayList<>();
        int total_row = table.get(0).size();
        for(int j =0; j < total_row; j++){
            if (!(table.get(column_num2).get(j).equals(table.get(column_num).get(j)))){
                index_list.add(j);
            }
        }
        return index_list;
    }

    //FIND ALL THE INDICES GIVEN A COMPARISON OPERATOR GIVEN A COLUMN AND AN OBJECT IN THE COLUMN CALLED FIND
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


    //DOES THE SAME THING AS FIND INDICIES COMPARE JUST COMPARING TWO COLUMNS IN THE SAME ROW
    public List<Integer> findIndiciesCompare_column(int column_num, int column_num2, String op){
        List<Integer> index_list = new ArrayList<>();
        int total_row = table.get(0).size();
        for(int j = 0; j < total_row; j++){
            if (op.equals(">=")){
                if (table.get(column_num2).get(j).toString().compareTo(table.get(column_num).get(j).toString()) <= 0) {
                    index_list.add(j);
                }
            }
            else if (op.equals("<=")) {
                if (table.get(column_num2).get(j).toString().compareTo(table.get(column_num).get(j).toString()) >= 0) {
                    index_list.add(j);
                }
            }
            else if (op.equals(">")) {
                if (table.get(column_num2).get(j).toString().compareTo(table.get(column_num).get(j).toString()) < 0) {
                    index_list.add(j);
                }
            }
            else if (op.equals("<")) {
                if (table.get(column_num2).get(j).toString().compareTo(table.get(column_num).get(j).toString()) > 0) {
                    index_list.add(j);
                }
            }
        }
        return index_list;
    }


    //SHOWS THE TABLE VISUALLY
    public void printTable(){
        int total_rows = table.get(0).size();
        int total_columns = column_name.size();
        System.out.println("----------- " + table_name + " -----------");
        for (int j=0; j< column_name.size(); j++){
            if (j == column_name.size()-1){
                System.out.print( column_name.get(j));
            }
            else {
                System.out.print(column_name.get(j) + " | ");
            }
        }
        System.out.println();
        for (int k=0; k < total_rows; k++){
            for (int i = 0; i < total_columns; i++) {
                if(i  == total_columns -1){
                    System.out.print(table.get(i).get(k));
                }
                else {
                    System.out.print(table.get(i).get(k) + " | ");
                }
            }
            System.out.println();
        }
    }

}
