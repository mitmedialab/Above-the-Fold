//ABOVE THE FOLD
//VIEWING THE FRONT PAGE OF THE NYT

import java.util.*;

DataSource  data;


void setup() {
  size(480, 120);
  smooth();
  
  data = new NYTDataSource();
  ArrayList months = data.make("/Users/nathan/Development/civic/above_the_fold/AboveTheFold/data/top_news_on_front_page.csv");
  System.out.println(months.size());
}

void draw(){
  
}
