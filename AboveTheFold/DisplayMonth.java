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
    columnRows = 35;
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
      //System.out.println("REMAINDER ALERT.");
      //System.out.println("  " + Integer.toString(remainderUS));
      //System.out.println("  " + Integer.toString(remainderWorld));
    }
  }

  protected int fillRows(int count, String type){
    int remaining = count;
    while(currentColumn < columnCount){
      //System.out.println(Integer.toString(remaining) + ": " + type);
      remaining = ((NewsColumnModel)columns.get(currentColumn)).populateColumn(remaining, type);
      if(remaining > 0){
        currentColumn++; 
      }else{
        break;
      }
    }
    return remaining;
  }

  protected int rowsForPercentage(float percentage){
    int totalRows = columnRows * columnCount;
    return (int)Math.round(((float)totalRows/100.00) * percentage);
  }
}
