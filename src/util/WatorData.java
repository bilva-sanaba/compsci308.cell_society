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
public class WatorData extends CAData {
    
    public static final List<String> EXTRA_FIELDS = Arrays.asList(new String[] {
            "fishBreed",
            "sharkBreed",
            "fishEnergy",
            "sharkEnergy"
        });
    
	public WatorData(Map<String, String> data) {
		super(data);
	}

    @Override
    public Collection<String> getExtraField() {
        return EXTRA_FIELDS;
    }
}
