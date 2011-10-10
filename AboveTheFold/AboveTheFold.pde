//ABOVE THE FOLD
//VIEWING THE FRONT PAGE OF THE NYT

import java.util.*;
import java.util.Random;

DataSource  data;
ArrayList displayMonths;
int currentDisplayMonthIndex;
int displayWidth = 1200;
int displayHeight = 786;
int columnWidth = 185;
int columnHorizMargin = 5; 
int marginLeft = 30;
int marginTop = 210;
int nameplateInfoBarTop = 175;
int contentWidth;
PFont titleFont;
PFont labelFont;
PFont legendFont;
PFont nameplateText;
PFont sansSerifBold;
DisplayTimeline timeline;
PImage nytNameplate;

void setup() {
  size(displayWidth, displayHeight);
  smooth();

  titleFont = loadFont("Times-Roman-24.vlw");
  labelFont = loadFont("Times-Roman-14.vlw");
  legendFont = loadFont("Times-Roman-16.vlw");
  nameplateText = loadFont("Times-Roman-13.vlw");
  sansSerifBold = loadFont("Arial-BoldMT-15.vlw");
  nytNameplate = loadImage("NYT_nameplate.png");  // Load the image into the program  
  
  data = new NYTDataSource();
  ArrayList newsMonths = data.make("/Users/nathan/Development/civic/above_the_fold/AboveTheFold/data/top_news_on_front_page.csv"); 
  displayMonths = new ArrayList(newsMonths.size());

  contentWidth = (columnWidth+columnHorizMargin) * 6 - 5;
  timeline = new DisplayTimeline(newsMonths, marginLeft, 710, contentWidth, 80, 1.0);
  
  Iterator newsMonthsItr = newsMonths.iterator();  
  while(newsMonthsItr.hasNext()){
    NewsMonth m = (NewsMonth)newsMonthsItr.next();
    DisplayMonth dm = new DisplayMonth(m);
    displayMonths.add(dm);
  }
  currentDisplayMonthIndex = displayMonths.size()/2;
  
}

void draw(){
  background(255);
  showCurrentDisplayMonth();
  drawTimeline();
  drawLabels();
  drawNamePlate();
}

void drawNamePlate(){
  textAlign(CENTER);

  // "All the News" box
  // relative width: width/37, relative horizontal placement: width/6.66
  strokeWeight(0.5);
  fill(#000000, 0.0);
  rect(33, 20, 130, 55);

  // "All the News" text
  // Set the font and its size (in units of pixels)
  fill(#000000);
  textFont(nameplateText, 13);
  text("\"All the News", 90, 40);
  text("That's Fit to Analyze\"", 100, 60);

  // Center the nameplate, TODO update height to make relative
  // or width/1.6, if you want relative width, and width*0.625 for relative horizontal placement
  image(nytNameplate, width/5, marginLeft, 778, 127); 

  // nameplate line 1
  strokeWeight(0.5);
  line(marginLeft, 160, marginLeft + contentWidth, nameplateInfoBarTop - 15);

  // Volume Number in nameplate
  textFont(nameplateText, 13);
  textAlign(LEFT);
  text("VOL. CLXI..No. 55,555", marginLeft, nameplateInfoBarTop);

  // Price in nameplate
  textFont(nameplateText, 13);
  textAlign(RIGHT);
  text("$2.00", marginLeft + contentWidth, nameplateInfoBarTop);
  textAlign(CENTER);

  // nameplate line 2
  strokeWeight(1);
  line(marginLeft, 180, marginLeft + contentWidth, nameplateInfoBarTop + 5);
  fill(#000000);
  
  textFont(sansSerifBold, 15);
  text("Meta Edition", 1100, 40);
  textFont(nameplateText, 13);
  text("A comparison of front", 1100, 60);
  text("page coverage of US", 1100, 75);
  text("and World news between", 1100, 90);
  text("1987 and 2007", 1100, 105);
}

void showCurrentDisplayMonth(){
  DisplayMonth currentDisplayMonth = (DisplayMonth)displayMonths.get(currentDisplayMonthIndex);
  
  textFont(titleFont);
  fill(#000000);
  text(currentDisplayMonth.monthName + " / " + 
       Integer.toString(currentDisplayMonth.month.FPYear), displayWidth/2, 40);
  
  Iterator columnIterator = currentDisplayMonth.columns.iterator();  
  int columnNumber = 0;
  while(columnIterator.hasNext()){
    drawColumn((NewsColumnModel) columnIterator.next(), columnNumber); 
    columnNumber++;   
  }
}

void drawLabels(){
  textAlign(LEFT);

  //U.S. News
  fill(#F0997C);
  stroke(#F0997C);
  rect( timeline.left, timeline.bottom-105 , 10, 10);

  //World News
  fill(#85A2C5);
  stroke(#85A2C5);
  rect( timeline.left+100, timeline.bottom-105 , 10, 10);

  //Other
  fill(#333333);
  stroke(#333333);
  rect( timeline.left+200, timeline.bottom-105 , 10, 10);

  textFont(labelFont);
  fill(#000000);
  text("U.S.",  timeline.left + 20, timeline.bottom-95);
  text("World",  timeline.left + 120, timeline.bottom-95);
  text("Other",  timeline.left + 220, timeline.bottom-95);
}



void drawTimeline(){
  textAlign(LEFT);

  stroke(#AAAAAA);
  strokeWeight(1);
  int location[] = new int[2];
  
  //draw quarterly ticks
  /*
  for(int monthIndex = 0; monthIndex < timeline.newsMonths.size(); monthIndex++){
    if(((NewsMonth)timeline.newsMonths.get(monthIndex)).FPMonth % 3 == 0){
      int xLocation = timeline.getXLocation(monthIndex);
      line(xLocation, timeline.bottom+2, xLocation, timeline.bottom-5);
    }
  }*/
  
  //draw annual ticks
  textFont(labelFont);
  fill(#000000);
  for(int monthIndex = 0; monthIndex < timeline.newsMonths.size(); monthIndex++){
    if(((NewsMonth)timeline.newsMonths.get(monthIndex)).FPMonth-1 % 12 == 0){
      int xLocation = timeline.getXLocation(monthIndex);
      line(xLocation, timeline.bottom+15, xLocation, timeline.bottom);
      text(Integer.toString((int)((NewsMonth)timeline.newsMonths.get(monthIndex)).FPYear), xLocation +2, timeline.bottom+14);
    }
  }
  
  //draw world graph
  strokeWeight(2);
  strokeJoin(ROUND);
  fill(#000000, 0.0);
  stroke(#85A2C5);
  int[] previousLocation = new int[2];

  beginShape();
  for(int monthIndex = 0; monthIndex < timeline.newsMonths.size(); monthIndex++){
    location = timeline.getWorldLocation(monthIndex);
    vertex(location[0], location[1]);
  }
  endShape();
  
  //draw the U.S. graph
  strokeWeight(2);
  fill(#000000, 0.0);
  stroke(#F0997C);
  beginShape();
  for(int monthIndex = 0; monthIndex < timeline.newsMonths.size(); monthIndex++){
    location = timeline.getUSLocation(monthIndex);
    vertex(location[0], location[1]);
  }
  endShape();
  

  stroke(#AAAAAA);
  strokeWeight(1);

  //draw scale
  line(timeline.left, timeline.bottom, timeline.left+timeline.width, timeline.bottom);  
  line(timeline.left, timeline.bottom, timeline.left, timeline.bottom-timeline.height);
  line(timeline.width+timeline.left, timeline.bottom, timeline.width+timeline.left, timeline.bottom-timeline.height);

  //draw the current Cursor
  stroke(#333333);
  strokeWeight(1.5);
  fill(#000000, 0.0);
  int cursorLocation = timeline.getXLocation(currentDisplayMonthIndex);
  rect( cursorLocation-2, timeline.bottom-80 , 4, 75);


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

