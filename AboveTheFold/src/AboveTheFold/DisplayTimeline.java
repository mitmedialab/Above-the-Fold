package AboveTheFold;

import java.util.*;
import java.lang.Math;

public class DisplayTimeline{
  public ArrayList newsMonths;
  public int left;
  public int bottom;
  public int height;
  public int width;
  public float articleUnit;
  public int maximumArticles;
  private float monthUnit;
  private float verticalUnit;
  private float verticalScale;

  public DisplayTimeline(ArrayList newsMonths, int left, int bottom, int width, int height, float verticalScale){
    this.left = left;
    this.bottom = bottom;
    this.height = height; 
    this.width = width;
    this.newsMonths = newsMonths;
    this.monthUnit = (float)width / (float)this.newsMonths.size();
    this.verticalUnit = (float)height / (float)100.00;
    this.verticalScale = verticalScale;
    
    Iterator newsMonthIterator = newsMonths.iterator();
    while(newsMonthIterator.hasNext()){
      NewsMonth newsMonth = (NewsMonth)newsMonthIterator.next();
      if(newsMonth.TotalArticles > maximumArticles){
        maximumArticles = newsMonth.TotalArticles;
      }
    }
    this.articleUnit = (float)height / (float)maximumArticles;
    
  } 

  public int[] getWorldLocation(int newsMonthIndex){
    int[] location = new int[2];
    location[0] = getXLocation(newsMonthIndex);
    location[1] = getYLocation(((NewsMonth)newsMonths.get(newsMonthIndex)).WorldPercentage);
    return location;
  }

  public int[] getUSLocation(int newsMonthIndex){
    int[] location = new int[2];
    location[0] = getXLocation(newsMonthIndex);
    location[1] = getYLocation(((NewsMonth)newsMonths.get(newsMonthIndex)).USPercentage);
    return location;
  }

  public int getXLocation(int newsMonthIndex){
    return (int)Math.round((float)newsMonthIndex * monthUnit) + this.left;
  }

  public int getYLocation(float percentage){
    return this.bottom - (int)Math.round(percentage * (this.verticalUnit * this.verticalScale));
  }
  
  public int totalArticleUnits(int number){
     return (int)Math.round( (float)number * this.articleUnit );
  }
}
