import aboveTheFold.*;

import junit.framework.TestCase;
import java.util.*;
import java.lang.String;

public class NewsColumnModelTest extends TestCase{

  public void testNewsColumnModel(){
    NewsColumnModel ncm = new NewsColumnModel(10);
    Iterator columnLineIterator = ncm.columnLines.iterator();
    while(columnLineIterator.hasNext()){
      assertEquals("Neither", (String)columnLineIterator.next());
    }
 
    assertEquals(0, ncm.populateColumn(5, "World"));
    assertEquals(5, ncm.columnRows - ncm.currentKey);
    assertEquals("Neither", ncm.columnLines.get(5));

    assertEquals(2, ncm.populateColumn(7, "U.S."));
    assertEquals(0, ncm.columnRows - ncm.currentKey);
    
    assertEquals("World", ncm.columnLines.get(0));
    assertEquals("World", ncm.columnLines.get(4));
    assertEquals("U.S.", ncm.columnLines.get(5));
    assertEquals("U.S.", ncm.columnLines.get(9));
  }

}
