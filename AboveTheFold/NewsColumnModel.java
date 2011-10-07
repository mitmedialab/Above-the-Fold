import java.util.*;

public class NewsColumnModel{
  public ArrayList lines;
  public int currentKey;

  public NewsColumnModel(int columnRows){
    lines = new ArrayList(columnRows);
    month = newsMonth;
  }

  //populate the column, and return the number not filled
  public int populateColumn(int number, String type){
    int populationLimit = currentKey + number;
    for(int i = currentKey; i < populationLimit; i++){
      if(i == lines.size()){
        break;
      }
      lines.set(i, type);
      currentKey++;
      number--;
    }
    return number;
  }
}
