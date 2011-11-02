package aboveTheFold;

import processing.core.*;
import java.util.*;
import java.util.Random;
//import processing.serial.*;
import fullscreen.*;

public class AboveTheFold extends PApplet{
  DataSource  data;
  ArrayList displayMonths;
  int currentDisplayMonthIndex;
  int displayWidth;
  int displayHeight;
  int columnWidth;
  int columnHorizMargin;
  int marginLeft;
  int marginTop;
  int nameplateInfoBarTop;
  int contentWidth;
  int nameplateTextLineHeight;
  int nameplateTextMarginTop;
  int nameplateTextMarginRight;
  int newsBoxTop;
  FullScreen fullscreen;

  //IndexedDial dial;
  //Serial serialPort;
 
  PFont titleFont;
  PFont labelFont;
  PFont legendFont;
  PFont nameplateText;
  PFont sansSerifBold;
  DisplayTimeline timeline;
  PImage nytNameplate;


  public void setup(){
    displayWidth = 1280;
    displayHeight = 786;
    columnWidth = 170;
    columnHorizMargin = 5;
    marginLeft = 80;
    marginTop = 240;
    nameplateInfoBarTop = 205;
    nameplateTextLineHeight = 15;
    nameplateTextMarginTop = 110;
    nameplateTextMarginRight = 45;
    newsBoxTop = 80;

    size(displayWidth, displayHeight);
    smooth();
    fullscreen = new FullScreen(this);
    fullscreen.enter();

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
    //timeline = new DisplayTimeline(newsMonths, marginLeft, marginTop + 500, contentWidth, 80, 1.0);


    //initializeSerialPort(newsMonths.size(), contentWidth);

    Iterator newsMonthsItr = newsMonths.iterator();
    while(newsMonthsItr.hasNext()){
      NewsMonth m = (NewsMonth)newsMonthsItr.next();
      DisplayMonth dm = new DisplayMonth(m);
      displayMonths.add(dm);
    }
    currentDisplayMonthIndex = displayMonths.size()/2;
  }

   public static void main(String args[]) {
     PApplet.main(new String[] { "--present", "aboveTheFold.AboveTheFold" });
   }

  public void draw(){
    background(255);

    //dial.poll();
//    currentDisplayMonthIndex = dial.currentIndex - 1;
    if(currentDisplayMonthIndex < 0){
      currentDisplayMonthIndex = 0;
    }

    showCurrentDisplayMonth();
//    drawTimeline();
    drawLabels();
    drawNamePlate();
  }

  void drawNamePlate(){
    textAlign(CENTER);
    // "All the News" box
    // relative width: width/37, relative horizontal placement: width/6.66
    strokeWeight((float)0.5);  
    fill(color(0,0,0), (float)0.0);
    rect(marginLeft, newsBoxTop, 130, 65);

    // "All the News" text
    // Set the font and its size (in units of pixels)
    fill(color(0,0,0));

    textFont(nameplateText, 13);
    text("\"All the News", marginLeft + 60, newsBoxTop + 27);
    text("That's Fit to Analyze\"", marginLeft + 65, newsBoxTop + 47);

    // Center the nameplate, TODO update height to make relative
    // or width/1.6, if you want relative width, and width*0.625 for relative horizontal placement

    int nytNamePlateLeft = (marginLeft + contentWidth)/2 - nytNameplate.width/2 + 40;


    image(nytNameplate, nytNamePlateLeft, marginTop-185, 778, 127);

    // nameplate line 1
    strokeWeight((float)0.5);
    line(marginLeft, nameplateInfoBarTop - 15, marginLeft + contentWidth, nameplateInfoBarTop - 15);

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
    line(marginLeft, nameplateInfoBarTop + 5, marginLeft + contentWidth, nameplateInfoBarTop + 5);
    fill(color(0,0,0));

    textAlign(CENTER);
    textFont(sansSerifBold, 15);
    text("Meta Edition", 1060, nameplateTextMarginTop - 18);
    textFont(nameplateText, 13);


    textAlign(LEFT);
    text("A data comparison of", contentWidth - nameplateTextMarginRight, nameplateTextMarginTop);
    text("front page coverage of", contentWidth-nameplateTextMarginRight, nameplateTextMarginTop + nameplateTextLineHeight);
    text("US and World news,", contentWidth-nameplateTextMarginRight, nameplateTextMarginTop + nameplateTextLineHeight * 2);
    text("1987 to 2007", contentWidth-nameplateTextMarginRight, nameplateTextMarginTop + nameplateTextLineHeight * 3);
  }



  void showCurrentDisplayMonth(){
    DisplayMonth currentDisplayMonth = (DisplayMonth)displayMonths.get(currentDisplayMonthIndex);

    textAlign(CENTER);
    textFont(nameplateText, 13);
    fill(color(0,0,0));
    text("New York, " + currentDisplayMonth.monthName + ", " +
         Integer.toString(currentDisplayMonth.month.FPYear), marginLeft + contentWidth/2, nameplateInfoBarTop);

    Iterator columnIterator = currentDisplayMonth.columns.iterator();
    int columnNumber = 0;
    while(columnIterator.hasNext()){
      drawColumn((NewsColumnModel) columnIterator.next(), columnNumber);
      columnNumber++;
    }
  }


  protected void drawLabels(){
    textAlign(LEFT);

    //U.S. News
    fill(0xF0997C);
    stroke(0xF0997C);
 //   rect( timeline.left, timeline.bottom-105 , 10, 10);

    //World News
    fill(0x85A2C5);
    stroke(0x85A2C5);
//    rect( timeline.left+100, timeline.bottom-105 , 10, 10);

    //Other
    fill(color(30,30,30));
    stroke(color(30,30,30));
//    rect( timeline.left+200, timeline.bottom-105 , 10, 10);

    textFont(labelFont);
    fill(color(0,0,0));
/*    text("U.S.",  timeline.left + 20, timeline.bottom-95);
    text("World",  timeline.left + 120, timeline.bottom-95);
    text("Other",  timeline.left + 220, timeline.bottom-95);*/
  }

  protected void drawColumn(NewsColumnModel column, int columnNumber){
    int x = marginLeft + (columnWidth  + columnHorizMargin) * columnNumber;
    int lineHeight = 17;
    int y = marginTop;

    Iterator columnLineIterator = column.columnLines.iterator();
    int strokeColor = 0xCCCCCC;
    strokeWeight(12);

    while(columnLineIterator.hasNext()){
      String type = (String) columnLineIterator.next();
      if(type == "U.S."){
        strokeColor = color(248, 170, 146);
      }else if(type == "World"){
        strokeColor = color(149, 178, 205);
      }else{
        strokeColor = color(121,121,121);
      }
      stroke(strokeColor);
      strokeCap(SQUARE);
      line(x, y, x + columnWidth, y);
      y += lineHeight;
    }
  }

  public void mousePressed() {
    if (mouseX > displayWidth/2) {
      currentDisplayMonthIndex += 1;
    }else{
      currentDisplayMonthIndex -=1;
    }
  }


/*  void initializeSerialPort(int indices, int scaleWidth){
    for(int i =0 ; i< Serial.list().length; i++){
     System.out.println(Serial.list()[i]);
    }
    serialPort = new Serial(this, Serial.list()[0], 9600);
    dial = new IndexedDial(serialPort, indices, scaleWidth);

  }*/



}
