import aboveTheFold.*;

import junit.framework.TestCase;
import java.util.*;
import java.lang.String;

public class DisplayMonthTest extends TestCase{

  protected NewsMonth newsMonth;
  protected DisplayMonth displayMonth;

  public void setUp(){
    // 50% us 40% world 10% other
    newsMonth = new NewsMonth(2000, 01, 10, 5, 4);
    displayMonth = new DisplayMonth(newsMonth);
  }

  public void testRowsForPercentage(){
    assertEquals(69, displayMonth.rowsForPercentage((float)100.0));
    assertEquals(0, displayMonth.rowsForPercentage((float)00.0));
    assertEquals(35, displayMonth.rowsForPercentage((float)50.0)); 
    assertEquals(21, displayMonth.rowsForPercentage((float)30.0));
    assertEquals(19, displayMonth.rowsForPercentage((float)27.7));
  }

  //TODO: Figure out a better way to test this method by itself
  //I think there are some rounding errors which are creeping into
  // my general approach to filling things
  public void testFillRows(){
    // 90% of 69 is already taken up. It should take 6 more items
    assertEquals(4, displayMonth.fillRows(10, "U.S."));
  }

  public void testDisplayMonth(){
    assertEquals("January", displayMonth.monthName);
    assertEquals(3, displayMonth.columns.size());
    assertEquals("U.S.",  ((NewsColumnModel)displayMonth.columns.get(0)).columnLines.get(0));
    assertEquals("World",  ((NewsColumnModel)displayMonth.columns.get(2)).columnLines.get(0));
  }

}
