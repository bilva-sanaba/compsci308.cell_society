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
            "cellNumber",
            "cellProbs"
        });
    
    private Map<String, String> myData;
    private List<CellConfig> myCellConfig;
	
	public CAData(Map<String, String> data) {
	    myData = data;
	    myCellConfig = new ArrayList<CellConfig>();
	    
	    // TODO: Why do these if statements not select the right loading method?
	    // makeRandomCells and makeProbDistrCells work when outside of the if statements, but not when inside
	    // I think data.containsKey isn't working correctly
	    
	    // Debugging:
//	    makeRandomCells(Integer.parseInt(data.get(DATA_FIELDS.get(6))));
//	    makeProbDistrCells(data.get(DATA_FIELDS.get(7)));
	    
	    if (data.containsKey(DATA_FIELDS.get(5))) {
	    	parseCellConfig(data.get(DATA_FIELDS.get(5)));
	    } else if (data.containsKey(DATA_FIELDS.get(6))) {
	    	makeRandomCells(Integer.parseInt(data.get(DATA_FIELDS.get(6))));
	    } else makeProbDistrCells(data.get(DATA_FIELDS.get(7)));
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
				ThreadLocalRandom.current().nextInt(numRows()),
				ThreadLocalRandom.current().nextInt(numCols())
			};
    }
    
    private void makeProbDistrCells(String probString) {
    	Scanner sc = new Scanner(probString);
    	ArrayList<Integer> probs = new ArrayList<Integer>();
    	try {
    		probs.add(sc.nextInt());
    		probs.add(probs.get(0) + sc.nextInt());
    	} catch (NoSuchElementException e) {
    		sc.close();
    		throw new CAException(CAException.INVALID_CELL_PROB);
    	}
    	if (sc.hasNextInt()) {probs.add(probs.get(1) + sc.nextInt());}
    	for (int i = 0; i < numRows(); i++) {
            for (int j = 0; j < numCols(); j++) {
                myCellConfig.add(new CellConfig(i, j, randomCellType(probs)));
            }
        }
    	sc.close();
    }
    
    private int randomCellType(ArrayList<Integer> probs) {
    	int cellType = 0;
    	int rand = ThreadLocalRandom.current().nextInt(100);
    	while (rand >= probs.get(cellType)) cellType += 1;
    	return cellType;
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