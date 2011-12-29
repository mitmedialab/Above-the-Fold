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

  private void printArrayList(ArrayList al){
    Iterator iterator = al.iterator();
    while(iterator.hasNext()){
      System.out.println(iterator.next().toString());
    }
  }
}
