package aboveTheFold;
import java.util.*;


public class TopicMonth{
  public int FPYear;
  public int FPMonth;
  ArrayList topics; // ArrayList of arrays: [String, int]
  
  public TopicMonth(int aFPYear, int aFPMonth, ArrayList topics){
    FPYear = aFPYear;
    FPMonth = aFPMonth;
    this.topics = topics;
  }
}
