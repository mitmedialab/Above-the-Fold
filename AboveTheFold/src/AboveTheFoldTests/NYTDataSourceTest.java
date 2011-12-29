import aboveTheFold.*;

import junit.framework.TestCase;
import java.util.*;
import java.util.ArrayList;

public class NYTDataSourceTest extends TestCase{
  protected DataSource dataSource;
  protected ArrayList dataStrings;
 
  public void setUp(){
    this.dataSource = new NYTDataSource();
    this.dataStrings = ((NYTDataSource)this.dataSource).loadStrings("fixtures/nyt_data_source.csv");
  }

  public void testLoadStrings(){
    assertEquals(this.dataStrings.size(), 6);
  }

  private void printArrayList(ArrayList al){
    Iterator iterator = al.iterator();
    while(iterator.hasNext()){
      System.out.println(iterator.next().toString());
    }
  }
}
