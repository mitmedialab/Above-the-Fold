//ABOVE THE FOLD
//VIEWING THE FRONT PAGE OF THE NYT

import java.util.*;
import java.util.Random;

DataSource  data;

void setup() {
  size(1024,768);
  smooth();
  
  data = new NYTDataSource();
  ArrayList newsMonths = data.make("/Users/nathan/Development/civic/above_the_fold/AboveTheFold/data/top_news_on_front_page.csv"); 
  ArrayList displayMonths = new ArrayList(newsMonths.size());
  Iterator newsMonthsItr = newsMonths.iterator();
  
  while(newsMonthsItr.hasNext()){
    NewsMonth m = (NewsMonth)newsMonthsItr.next();
    System.out.println(Integer.toString(m.FPYear) + "/" + Integer.toString(m.FPMonth));
    System.out.println(Integer.toString(m.TotalArticles) + " : " + Integer.toString(m.WorldArticles) + ", " + Integer.toString(m.USArticles));
    System.out.println(Float.toString(m.WorldPercentage) + " : " + Float.toString(m.USPercentage));
    DisplayMonth dm = new DisplayMonth(m);
    displayMonths.add(dm);
  }
}

void draw(){
  background(255);
  strokeWeight(4);
  int x = 0;
  int y = 0;
  int w = 165;
  int tabbing = 15;
  color strokeColor = #CCCCCC;
  stroke(strokeColor);
  strokeCap(SQUARE);
  
  for(int j = 5; j< 1024; j+=w+10){
    x = j;
    for(int i =3; i<786; i+=8){
      y = i;
      line(x, y, x+w, y);
    }
  }
}
