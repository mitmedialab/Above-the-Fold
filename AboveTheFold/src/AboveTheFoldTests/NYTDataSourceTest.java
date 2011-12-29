import aboveTheFold.*;

import junit.framework.TestCase;
import java.util.*;
import java.util.ArrayList;

public class NYTDataSourceTest extends TestCase{
  protected NYTDataSource dataSource;
  protected ArrayList dataStrings;
 
  public void setUp(){
    this.dataSource = new NYTDataSource();
    this.dataStrings = this.dataSource.loadStrings("fixtures/nyt_data_source.csv");
  }

  public void testLoadStrings(){
    assertEquals(this.dataStrings.size(), 6);
  }

  public void testGetCsvWidth(){
    assertEquals(4, this.dataSource.getCsvWidth(dataStrings));
  }
 
  public void testMake(){
    ArrayList months = dataSource.make("fixtures/nyt_data_source.csv");
    assertEquals(5, months.size());
    testNewsMonth( (NewsMonth)months.get(0), 1987, 1, 178, 78, 64);
    testNewsMonth( (NewsMonth)months.get(2), 1987, 3, 197, 79, 74);
    testNewsMonth( (NewsMonth)months.get(4), 1987, 5, 169, 64, 61);
  }

  private void printArrayList(ArrayList al){
    Iterator iterator = al.iterator();
    while(iterator.hasNext()){
      System.out.println(iterator.next().toString());
    }
  }
  
  public void testNewsMonth(NewsMonth n, int FPyear, int FPMonth, int TotalArticles, int WorldArticles, int USArticles){
    assertEquals(n.FPYear, FPyear);
    assertEquals(n.FPMonth, FPMonth);
    assertEquals(n.TotalArticles, TotalArticles);
    assertEquals(n.WorldArticles, WorldArticles); 
    assertEquals(n.USArticles, USArticles);
  }
}
