
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class LargestQuakes {
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        ArrayList<QuakeEntry> largest = getLargest(list, 50);
        for(QuakeEntry k : largest){
           System.out.println(k); 
        }
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        double max = 0;
        int index = 0;
       for(int i = 0; i < data.size(); i ++){
           double curr = data.get(i).getMagnitude();
           if(max < curr){
               max = curr;
               index = i;
            }
        }
       return index; 
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> request = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        int i = 0;
        if(howMany > copy.size()){ 
			howMany = copy.size();
        }
        while(i < howMany){
            int index = indexOfLargest(copy);
            request.add(copy.get(index));
            copy.remove(index);
            i++;
        }
        return request;
    }
}
