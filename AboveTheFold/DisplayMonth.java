import java.util.*;
import java.lang.Math;

public class DisplayMonth{
  public NewsMonth month;
  public ArrayList columns;
  public int currentColumn;
  public int columnRows;
  public int columnCount; 

  public DisplayMonth(NewsMonth newsMonth){
    currentColumn = 0;
    columnRows = 50;
    columnCount = 6;
    
    month = newsMonth;
    columns = new ArrayList(columnCount);
    for(int i =0; i< columnCount; i++){
      columns.add(new NewsColumnModel(columnRows));
    }

    int USRows = rowsForPercentage(month.USPercentage);
    int WorldRows = rowsForPercentage(month.WorldPercentage);

    int remainderUS    = fillRows(USRows, "U.S.");
    int remainderWorld = fillRows(WorldRows,"World");
    if(remainderUS > 0 || remainderWorld > 0){
      System.out.println("REMAINDER ALERT.");
      System.out.println(Integer.toString(remainderUS));
      System.out.println(Integer.toString(remainderWorld));
    }
  }

  protected int fillRows(int count, String type){
    int remaining = count;
    while(currentColumn!=columnCount){
      remaining = ((NewsColumnModel)columns.get(currentColumn)).populateColumn(remaining, type);
    }
    return remaining;
  }

  protected int rowsForPercentage(float percentage){
    int totalRows = columnRows * columnCount;
    return Math.round((float)totalRows * percentage);
  }
}
