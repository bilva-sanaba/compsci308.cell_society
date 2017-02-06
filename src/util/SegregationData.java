package util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Mike Liu
 * @author Justin Yang
 *
 */
public class SegregationData extends CAData {
    
    public static final String NAME = "segregation";
    public static final double DEFAULT_THRESHOLD = 0.5;
    public static final List<String> EXTRA_FIELDS = Arrays.asList(new String[] {
            "threshold"
        });
	
	public SegregationData(Map<String, String> data) {
		super(data);
	}
	
	public double getThreshold() {
	    try {
	        return Double.parseDouble(getField(EXTRA_FIELDS.get(0)));
	    } catch(NullPointerException e) {
	        return DEFAULT_THRESHOLD;
	    }
	}

    @Override
    public Collection<String> getExtraField() {
        return EXTRA_FIELDS;
    }
}
