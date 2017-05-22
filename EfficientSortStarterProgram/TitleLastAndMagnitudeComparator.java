
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
		String q11= qe1.getInfo().substring(qe1.getInfo().lastIndexOf(" ")+1);
		String q22 = qe2.getInfo().substring(qe2.getInfo().lastIndexOf(" ")+1);
        if(q11.compareTo(q22) == 0){
        	return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
        }
        return q11.compareTo(q22);
    }
}
