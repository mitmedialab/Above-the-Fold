package aboveTheFold;

public class NewsMonth{
  public int FPYear;
  public int FPMonth;
  public int TotalArticles;
  public int WorldArticles;
  public int USArticles;
  public float WorldPercentage;
  public float USPercentage;
  
  public NewsMonth(int aFPYear, int aFPMonth, int aTotalArticles, int aUSArticles, int aWorldArticles){
    FPYear = aFPYear;
    FPMonth = aFPMonth;
    TotalArticles = aTotalArticles;
    WorldArticles = aWorldArticles;
    USArticles = aUSArticles;
    
    WorldPercentage = (float)WorldArticles / ((float)TotalArticles / (float)100.00);
    USPercentage = (float)USArticles / ((float)TotalArticles / (float)100.000) ;
  }
}
