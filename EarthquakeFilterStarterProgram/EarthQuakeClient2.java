import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        Location loc = new Location(35.42, 139.43);
        
        Filter f = new MagnitudeFilter(4.0, 5.0); 
        Filter z = new DepthFilter(-35000, -12000);
        Filter d = new DistanceFilter(loc, 10000000);
        Filter p = new PhraseFilter("end", "Japan");
        ArrayList<QuakeEntry> m7  = filter(list, f);
        m7 = filter(m7, z);
        //m7 = filter(m7, d);
        //m7 = filter(m7, p);
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
        System.out.println("Found " + m7.size() + " quakes that match that criteria\n");
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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
    
    public void testMatchAllFilter(){
        EarthQuakeParser p = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = p.read(source);
        System.out.println("Read data for " + list.size() + " quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0,2.0));
        maf.addFilter(new DepthFilter(-35000.0, -12000.0));
        maf.addFilter(new PhraseFilter("any", "a"));
        
        ArrayList<QuakeEntry> m  = filter(list, maf); 
		for (QuakeEntry q: m) { 
			System.out.println(q);
		} 
		
		System.out.println("Found " + m.size() + " quakes that match that criteria");
        
    }
    
    public void testMatchAllFilter2(){
        EarthQuakeParser p = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = p.read(source);
        System.out.println("Read data for " + list.size() + " quakes");
        
        Location loc = new Location(55.7308, 9.1153);
        MatchAllFilter maf = new MatchAllFilter();
        //maf.addFilter(new DepthFilter(-180000, -30000));
        maf.addFilter(new MagnitudeFilter(0,5));
        maf.addFilter(new DistanceFilter(loc, 3000000));
        maf.addFilter(new PhraseFilter("any", "e"));
        ArrayList<QuakeEntry> qe = filter(list, maf);
        for(QuakeEntry q: qe){
           System.out.println(q); 
        }
        System.out.println("Filters used are: " + maf.getName() + "\n");
        System.out.println("Found " + qe.size() + " quakes that match that criteria");
    }
}
