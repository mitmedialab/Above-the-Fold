import java.util.*;

public class NewsColumnModel{
  public ArrayList columnLines;
  public int currentKey;
  public int columnRows;

  public NewsColumnModel(int rows){
    columnLines = new ArrayList(columnRows);
    currentKey = 0;
    columnRows = rows;
    for(int i =0; i < columnRows; i++){
      columnLines.add("Neither");
    }
  }

  //populate the column, and return the number not filled
  public int populateColumn(int number, String type){
    if(currentKey>= columnRows){
      return number;
    }
    //System.out.println("START: " + Integer.toString(number));//
    //System.out.println("Rows: " + Integer.toString(columnRows) + "currentKey: " + Integer.toString(currentKey));
    int populationLimit = currentKey + number;
    for(int i = currentKey; i < populationLimit; i++){
      if(i >= columnRows){
        //System.out.println("BREAK");
        break;
      }
      //System.out.print(".");
      columnLines.set(i, type);
      currentKey++;
      number--;
    }
    //System.out.println("END: " + Integer.toString(number));
    return number;
  }
}
