import aboveTheFold.*;

import junit.framework.TestCase;
import java.util.*;
import java.lang.String;

public class NewsMonthTest extends TestCase{

  public void testToString(){
    NewsMonth month = new NewsMonth(2000, 01, 10, 5, 5);
    assertEquals("NewsMonth: 1/2000 Total: 10 World: 5 US: 5", month.toString());
  }

}
