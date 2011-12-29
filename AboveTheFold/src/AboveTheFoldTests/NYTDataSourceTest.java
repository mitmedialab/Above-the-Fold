import aboveTheFold.*;

import junit.framework.TestCase;
import java.util.*;
import java.util.ArrayList;

public class NYTDataSourceTest extends TestCase{

  public void testLoadStrings(){
//    assertEquals( "Equality Test", 0, 1 );
    DataSource dataSource = new NYTDataSource();
    ArrayList strings = ((NYTDataSource)dataSource).loadStrings("fixtures/nyt_data_source.csv");
    assertEquals(strings.size(), 5);

  }
}
