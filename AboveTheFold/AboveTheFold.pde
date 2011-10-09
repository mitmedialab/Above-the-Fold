//ABOVE THE FOLD
//VIEWING THE FRONT PAGE OF THE NYT

import java.util.*;
import java.util.Random;

DataSource  data;
ArrayList displayMonths;
int currentDisplayMonthIndex;
int columnWidth = 140;
int columnHorizMargin = 5; 
int marginLeft = 65;
int marginTop = 50;

void setup() {
  size(1000,700);
  smooth();
  
  data = new NYTDataSource();
  ArrayList newsMonths = data.make("/Users/nathan/Development/civic/above_the_fold/AboveTheFold/data/top_news_on_front_page.csv"); 
  displayMonths = new ArrayList(newsMonths.size());
  Iterator newsMonthsItr = newsMonths.iterator();
  
  while(newsMonthsItr.hasNext()){
    NewsMonth m = (NewsMonth)newsMonthsItr.next();
    //System.out.println(Integer.toString(m.FPYear) + "/" + Integer.toString(m.FPMonth));
    //System.out.println(Integer.toString(m.TotalArticles) + " : " + Integer.toString(m.WorldArticles) + ", " + Integer.toString(m.USArticles));
    //System.out.println(Float.toString(m.WorldPercentage) + " : " + Float.toString(m.USPercentage));
    DisplayMonth dm = new DisplayMonth(m);
    displayMonths.add(dm);
  }
  currentDisplayMonthIndex = displayMonths.size()/2;
}

void draw(){
  background(255);
  showCurrentDisplayMonth();
}

void showCurrentDisplayMonth(){
  DisplayMonth currentDisplayMonth = (DisplayMonth)displayMonths.get(currentDisplayMonthIndex);
  Iterator columnIterator = currentDisplayMonth.columns.iterator();
  int columnNumber = 0;
  while(columnIterator.hasNext()){
    drawColumn((NewsColumnModel) columnIterator.next(), columnNumber); 
    columnNumber++;   
  }
}

void drawColumn(NewsColumnModel column, int columnNumber){
  int x = marginLeft + (columnWidth  + columnHorizMargin) * columnNumber;
  int lineHeight = 17;
  int y = marginTop;

  Iterator columnLineIterator = column.columnLines.iterator();
  color strokeColor = #CCCCCC;
  strokeWeight(12);
  
  while(columnLineIterator.hasNext()){
    String type = (String) columnLineIterator.next();
    if(type == "U.S."){
      strokeColor = #F0997C;
    }else if(type == "World"){
      strokeColor = #85A2C5;
    }else{
      strokeColor = #AAAAAA;
    }
    stroke(strokeColor);
    strokeCap(SQUARE);
    line(x, y, x + columnWidth, y);
    y += lineHeight;
  }
}
