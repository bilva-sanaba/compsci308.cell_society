package util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Justin Yang
 * @author Mike Liu
 *
 */
public class FireData extends CAData {
    
    public static final String NAME = "fire";
    public static final double DEFAULT_PROB = 0.5;
    public static final List<String> EXTRA_FIELDS = Arrays.asList(new String[] {
            "prob"
        });
	
	public FireData(Map<String, String> data) {
		super(data);
	}
	
	/**
	 * Returns the probability parameter used in fire simulation
	 * @return
	 */
	public double getProbCatch() {
	    try {
            return Double.parseDouble(getField(EXTRA_FIELDS.get(0)));
        } catch(NullPointerException e) {
            return DEFAULT_PROB;
        }
	}

    @Override
    public Collection<String> getExtraField() {
        return EXTRA_FIELDS;
    }
}
