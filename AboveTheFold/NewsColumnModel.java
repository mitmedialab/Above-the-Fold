import java.util.*;

public class NewsColumnModel{
  public ArrayList columnLines;
  public int currentKey;
  public int columnRows;

  public NewsColumnModel(int rows){
    columnLines = new ArrayList(columnRows);
    currentKey = 0;
    columnRows = rows;
  }

  //populate the column, and return the number not filled
  public int populateColumn(int number, String type){
//    System.out.println("START: " + Integer.toString(number));//
//    System.out.println("currentKey: " + Integer.toString(currentKey));
    int populationLimit = currentKey + number;
    for(int i = currentKey; i < populationLimit; i++){
      if(i >= columnRows){
        break;
      }
      System.out.print(".");
      columnLines.add(type);
      currentKey++;
      number--;
    }
//    System.out.println("END: " + Integer.toString(number));
    return number;
  }
}
