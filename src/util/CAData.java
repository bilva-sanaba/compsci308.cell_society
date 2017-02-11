package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import cell.CellConfig;
import cellsociety.CAException;
import model.GOLModel;
import model.SegregationModel;
import model.WatorModel;

/**
 * 
 * @author Justin Yang
 * @author Mike Liu
 *
 */
public abstract class CAData {
    
    public static final String DATA_TYPE = "CA";
    public static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
            "sim",
            "title",
            "author",
            "row",
            "col",
            "cell",
            "cellPercent",
            "cellNumber"
        });
    
    private Map<String, String> myData;
    private List<CellConfig> myCellConfig;
	
	public CAData(Map<String, String> data) {
	    myData = data;
	    myCellConfig = new ArrayList<CellConfig>();
	    if (data.containsKey(DATA_FIELDS.get(5))) {
	    	parseCellConfig(data.get(DATA_FIELDS.get(5)));
	    } else if (data.containsKey(DATA_FIELDS.get(7))) {
	    	makeRandomCells(Integer.parseInt(data.get(DATA_FIELDS.get(7))));
	    } else makeProbDistrCells(Integer.parseInt(data.get(DATA_FIELDS.get(6))));
	}
	
	public void addExtraField(String field, String data) {
	    myData.put(field, data);
	}
	
	protected String getField(String field) {
	    return myData.get(field);
	}
	
	public String getName() {
		return myData.get(DATA_FIELDS.get(0));
	}
	
	public String getTitle() {
		return myData.get(DATA_FIELDS.get(1));
	}
	
	public String getAuthor() {
	    return myData.get(DATA_FIELDS.get(2));
	}
	
	public int numRows() {
		return Integer.parseInt(myData.get(DATA_FIELDS.get(3)));
	}
	
	public int numCols() {
	    return Integer.parseInt(myData.get(DATA_FIELDS.get(4)));
	}
	
	public Collection<CellConfig> getCell() {
	    return myCellConfig;
	}
	
	public abstract Collection<String> getExtraField();
    
    private void parseCellConfig(String cellConfig) {
        Scanner sc = new Scanner(cellConfig);
        while(sc.hasNext()) {
            try {
                myCellConfig.add(new CellConfig(sc.nextInt(), sc.nextInt(), sc.nextInt()));
            } catch(NoSuchElementException e) {
                sc.close();
                throw new CAException(CAException.INVALID_CELL_CONFIG);
            }
        }
        sc.close();
    }
    
    private void makeRandomCells(int numCells) {
    	ArrayList<int[]> coordinateList = new ArrayList<int[]>();
    	int[] coordinate = randomCoordinate();
    	while (numCells > 0) {
    		while (coordinateList.contains(coordinate)) {
    			coordinate = randomCoordinate();
    		}
    		coordinateList.add(coordinate);
    		coordinate = randomCoordinate();
    		numCells -= 1;
    	}
    	if (getName().equals(GOLModel.NAME)) {
    		for (int[] c : coordinateList) {
    			myCellConfig.add(new CellConfig(c[0], c[1], 1));
    		}
    	} else for (int[] c : coordinateList) {
    		myCellConfig.add(new CellConfig(c[0], c[1], ThreadLocalRandom.current().nextInt(1, 3)));
    	}
    }
    
    private int[] randomCoordinate() {
    	return new int[]{
				ThreadLocalRandom.current().nextInt(0, numRows()),
				ThreadLocalRandom.current().nextInt(0, numCols())
			};
    }
    
    private void makeProbDistrCells(int prob) {
    	// TODO: Decide on how to implement
    }
	
	public static CAData getModelData(Map<String, String> data) {
	    String name = data.get(DATA_FIELDS.get(0)).toLowerCase();
	    if(name.equals(SegregationModel.NAME)) {
	        return new SegregationData(data);
	    }
        else if(name.equals(WatorModel.NAME)) {
            return new WatorData(data);
        }
        else if(name.equals(FireData.NAME)) {
            return new FireData(data);
        }
        else if(name.equals(GOLModel.NAME)) {
            return new GOLData(data);
        }
        throw new CAException(CAException.INVALID_MODEL, name);
	}
}