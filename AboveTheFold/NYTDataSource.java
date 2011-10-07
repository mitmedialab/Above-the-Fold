import java.util.*;
import java.lang.String;
import java.io.*;

public class NYTDataSource implements DataSource {


  public NYTDataSource() {
    // seeded, so we can reproduce results
  }

  public ArrayList make(String filename) {
    ArrayList lines = loadStrings(filename);
    String [][] csv;
    ArrayList months = new ArrayList();
    int csvWidth=0;
    
    //calculate max width of csv file
    for (int i=0; i < lines.size(); i++) {
      String [] chars=lines.get(i).toString().split(",");
      if (chars.length>csvWidth){
        csvWidth=chars.length;
      }
    }
    
    String [] fieldNames = new String[csvWidth];
    
    int rowCount = 0;
    Iterator itr = lines.iterator();
    itr.next(); // skip the first line
    while(itr.hasNext()){
      String line = itr.next().toString();
      String [] rowValues = new String [line.length()];
      rowValues = line.split(",");
      
      if(rowValues.length == 4){
        String [] dateValues = new String[2];
        dateValues = rowValues[0].split("_");
        months.add(new NewsMonth(Integer.parseInt(dateValues[0]), 
                                 Integer.parseInt(dateValues[1]), 
                                 Integer.parseInt(rowValues[1]), 
                                 Integer.parseInt(rowValues[2]), 
                                 Integer.parseInt(rowValues[3])));    
      }
     
      rowCount ++;
    }
    
    return months;
  }

  
  
  public ArrayList loadStrings(String filename){
    ArrayList strings = new ArrayList();
    try{
      FileInputStream fstream = new FileInputStream(filename);
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String strLine;
      while ((strLine = br.readLine()) != null)   {
        strings.add(strLine);
      }
      in.close();
    }catch (Exception e){//Catch exception if any
      System.err.println("Error: " + e.getMessage());
    }
    
    return strings;    
  }

}
