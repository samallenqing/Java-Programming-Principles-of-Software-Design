
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/earthQuakeDataDec6sample2.atom";
        
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData , int from){
        int index = from;
        for(int i = from + 1; i < quakeData.size(); i ++){
            if(quakeData.get(i).getDepth() > quakeData.get(index).getDepth()){
                index = i;
               }
           }
        return index;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
        for(int i = 0; i < 50; i ++){
            int maxIndex = getLargestDepth(in, i);
            QuakeEntry q = in.get(i);
            in.set(i, in.get(maxIndex));
            in.set(maxIndex, q);
           }
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData , int numSorted){
        for(int i = 1; i < quakeData.size() - numSorted ; i ++){
            if( quakeData.get(i).getMagnitude() < quakeData.get(i-1).getMagnitude()){
                QuakeEntry s = quakeData.get(i);
                QuakeEntry m = quakeData.get(i-1);
                quakeData.set(i, m);
                quakeData.set(i-1, s);
               }
           }
    }
       
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        for(int i = 0; i < in.size() -1 ; i ++){
             onePassBubbleSort(in,i);
       }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        for (int i=1; i<= quakes.size()-1; i++) {
			if (quakes.get(i-1).getMagnitude() > quakes.get(i).getMagnitude()) {
				return false;
			}
		}
		return true;
       }
       
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        for (int i=0; i< in.size()-1; i++) {
            if(checkInSortedOrder(in)) {
                System.out.println(i + " passes were needed to sort the elements."); 
                break;
            }
            onePassBubbleSort(in, i);
       
        }
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        for (int i=0; i< in.size(); i++) {
            if(checkInSortedOrder(in)){
                System.out.println(i + " passes were needed to sort the elements."); 
                break;
            }
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            
        }
    }
}
