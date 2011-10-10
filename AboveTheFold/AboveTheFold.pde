//ABOVE THE FOLD
//VIEWING THE FRONT PAGE OF THE NYT

import java.util.*;
import java.util.Random;

DataSource  data;
ArrayList displayMonths;
int currentDisplayMonthIndex;
int displayWidth = 1000;
int displayHeight = 700;
int columnWidth = 140;
int columnHorizMargin = 5; 
int marginLeft = 65;
int marginTop = 80;
PFont titleFont;
DisplayTimeline timeline;


void setup() {
  size(displayWidth, displayHeight);
  smooth();
  
  data = new NYTDataSource();
  ArrayList newsMonths = data.make("/Users/nathan/Development/civic/above_the_fold/AboveTheFold/data/top_news_on_front_page.csv"); 
  displayMonths = new ArrayList(newsMonths.size());
  timeline = new DisplayTimeline(newsMonths, 65, 650, 870, 50, 0.5);
  
  titleFont = loadFont("Times-Roman-24.vlw");

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
  drawTimeline();
}

void showCurrentDisplayMonth(){
  DisplayMonth currentDisplayMonth = (DisplayMonth)displayMonths.get(currentDisplayMonthIndex);
  
  textFont(titleFont);
  fill(0,0,0);
  text(Integer.toString(currentDisplayMonth.month.FPMonth) + " / " + 
       Integer.toString(currentDisplayMonth.month.FPYear), displayWidth/2, 50);
  
  Iterator columnIterator = currentDisplayMonth.columns.iterator();  
  int columnNumber = 0;
  while(columnIterator.hasNext()){
    drawColumn((NewsColumnModel) columnIterator.next(), columnNumber); 
    columnNumber++;   
  }
}

void drawTimeline(){
  
  color strokeColor=#AAAAAA;
  stroke(strokeColor);
  strokeWeight(1);
  
  //draw quarterly ticks
  for(int monthIndex = 0; monthIndex < timeline.newsMonths.size(); monthIndex++){
    if(((NewsMonth)timeline.newsMonths.get(monthIndex)).FPMonth % 3 == 0){
      int xLocation = timeline.getXLocation(monthIndex);
      line(xLocation, timeline.bottom+2, xLocation, timeline.bottom-5);
    }
  }
  
  //draw annual ticks
  for(int monthIndex = 0; monthIndex < timeline.newsMonths.size(); monthIndex++){
    if(((NewsMonth)timeline.newsMonths.get(monthIndex)).FPMonth % 12 == 0){
      int xLocation = timeline.getXLocation(monthIndex);
      line(xLocation, timeline.bottom+2, xLocation, timeline.bottom-15);
    }
  }
  
  //draw world graph
  strokeWeight(1);
  strokeColor = #85A2C5;
  stroke(strokeColor);
  int[] previousLocation = new int[2];
  previousLocation[0] = -50;//won't let me use null. Not sure of the convention
  previousLocation[1] = -50;
  for(int monthIndex = 0; monthIndex < timeline.newsMonths.size(); monthIndex++){
    int[] location = timeline.getWorldLocation(monthIndex);
    if(previousLocation[0] == -50 && previousLocation[1] == -50){
      previousLocation[0] = location[0];
      previousLocation[1] = location[1];
    }
    line(previousLocation[0], previousLocation[1], location[0], location[1]);
      previousLocation[0] = location[0];
      previousLocation[1] = location[1];
  }
  
  strokeColor=#AAAAAA;
  stroke(strokeColor);
  strokeWeight(1);

  //draw scale
  line(timeline.left, timeline.bottom, timeline.left+timeline.width, timeline.bottom);  
  line(timeline.left, timeline.bottom, timeline.left, timeline.bottom-50);
  line(timeline.width+timeline.left, timeline.bottom, timeline.width+timeline.left, timeline.bottom-50);

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
      strokeColor = #666666;
    }
    stroke(strokeColor);
    strokeCap(SQUARE);
    line(x, y, x + columnWidth, y);
    y += lineHeight;
  }
}

void mousePressed() {
  if (mouseX > displayWidth/2) {
    currentDisplayMonthIndex += 1;
  }else{
    currentDisplayMonthIndex -=1; 
  }
}

