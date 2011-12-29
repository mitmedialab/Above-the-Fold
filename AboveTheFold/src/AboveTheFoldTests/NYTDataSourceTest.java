import aboveTheFold.*;

import junit.framework.TestCase;
import java.util.*;
import java.util.ArrayList;

public class NYTDataSourceTest extends TestCase{

  public void testLoadStrings(){
    DataSource dataSource = new NYTDataSource();
    ArrayList strings = ((NYTDataSource)dataSource).loadStrings("fixtures/nyt_data_source.csv");
    assertEquals(strings.size(), 6);
  }
  private void printArrayList(ArrayList al){
    Iterator iterator = al.iterator();
    while(iterator.hasNext()){
      System.out.println(iterator.next().toString());
    }
  }
}
