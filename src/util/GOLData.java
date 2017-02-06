package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * 
 * @author Mike Liu
 * @author Justin Yang
 *
 */
public class GOLData extends CAData {
    
	public GOLData(Map<String, String> data) {
		super(data);
	}

    @Override
    public Collection<String> getExtraField() {
        return new ArrayList<String>();
    }
}
