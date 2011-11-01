package aboveTheFold;

import processing.core.*;
//import processing.serial.*;
import java.util.*;
import java.lang.Math;

class IndexedDial{
  public String dialValString;
  public float dialVal;
  public int indices;
  public int scaleWidth;
  public float indexScalingFactor;
 // public Serial serialPort;
  public int currentIndex;
  public int indexWidth;

  public IndexedDial(/*Serial serialPort,*/ int indices, int scaleWidth){
    this.dialValString = "0";
    this.dialVal = (float)0.0;
  //  this.serialPort = serialPort;
    this.indices = indices;
    this.scaleWidth = scaleWidth;
    this.indexWidth = (int)Math.round((float)scaleWidth / (float)indices);
    this.indexScalingFactor = (float)1024 / (float)indices; 
  }

  public void poll(){
   currentIndex = 10;
/*    while (serialPort.available() > 0) {
      String inBuffer = serialPort.readStringUntil(10);   
      if (inBuffer != null) {
        dialValString = inBuffer.trim();
      }
    }
    dialVal = (float)( 1024  - Integer.parseInt(dialValString) )/ indexScalingFactor;
    currentIndex = (int)Math.round(dialVal);  */
  }
}
