package util;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import cellsociety.CAException;
import cellsociety.cell.CellConfig;
import cellsociety.model.GOLModel;
import cellsociety.model.SegregationModel;
import cellsociety.model.WatorModel;

/**
 * Data class of the information needed to initialize a simulation model
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
            "cellNum",
            "cellProb"
        });
    
    private Map<String, String> myData;
    private int numRow, numCol;
    private List<CellConfig> myCellConfig;
	
	public CAData(Map<String, String> data) {
	    myData = data;
	    myCellConfig = new ArrayList<CellConfig>();
	    numRow = parsePositiveInteger(DATA_FIELDS.get(3));
	    numCol = parsePositiveInteger(DATA_FIELDS.get(4));
	    if (data.containsKey(DATA_FIELDS.get(5)) && data.get(DATA_FIELDS.get(5)).length() != 0) {
	    	parseCellConfig(data.get(DATA_FIELDS.get(5)));
	    }
	    else if (data.containsKey(DATA_FIELDS.get(6)) && data.get(DATA_FIELDS.get(6)).length() != 0) {
	    	parseCellNum(data.get(DATA_FIELDS.get(6)));
	    }
	    else if (data.containsKey(DATA_FIELDS.get(7)) && data.get(DATA_FIELDS.get(7)).length() != 0){
	        parseCellProb(data.get(DATA_FIELDS.get(7)));
	    }
	}
	
	/**
	 * Add new key value entry in myData Map
	 * @param field
	 * @param data
	 */
	public void addExtraField(String field, String data) {
	    myData.put(field, data);
	}
	
	/**
	 * Returns the string in Map associated with field key
	 * @param field
	 * @return
	 */
	protected String getField(String field) {
	    return myData.get(field);
	}
	
	/**
	 * Returns the name of the simulation
	 * @return
	 */
	public String getName() {
		return myData.get(DATA_FIELDS.get(0));
	}
	
	/**
	 * Returns the title of the simulation
	 * @return
	 */
	public String getTitle() {
		return myData.get(DATA_FIELDS.get(1));
	}
	
	/**
	 * Returns the author of the xml file
	 * @return
	 */
	public String getAuthor() {
	    return myData.get(DATA_FIELDS.get(2));
	}
	
	/**
	 * Returns the number of rows the simulation will have
	 * @return
	 */
	public int numRows() {
		return numRow;
	}
	
	/**
	 * Returns the number of columns the simulation will have
	 * @return
	 */
	public int numCols() {
	    return numCol;
	}
	
	/**
	 * Returns a list of the initial cells that were configured
	 * @return
	 */
	public Collection<CellConfig> getCell() {
	    return myCellConfig;
	}
	
	/**
	 * Returns a list of strings that identify parameters specific to the particular model
	 * @return
	 */
	public abstract Collection<String> getExtraField();
    
    /**
     * Parses the string from the cell tag, makes specific new cells, and adds them to a list
     * @param cellConfig
     */
    private void parseCellConfig(String cellConfig) {
        Scanner sc = new Scanner(cellConfig);
        while(sc.hasNext()) {
            int row, col, state;
            try {
                row = sc.nextInt();
                col = sc.nextInt();
                state = sc.nextInt();
                validateLocation(row, col);
            } catch(NoSuchElementException e) {
                sc.close();
                throw new CAException(CAException.INVALID_CELL_CONFIG);
            }
            myCellConfig.add(new CellConfig(row, col, state));
        }
        sc.close();
    }
    
    /**
     * Check if cell is being placed in a valid location in the grid
     * @param row
     * @param col
     */
    private void validateLocation(int row, int col) {
        if(row < 0 || row >= numRows() || col < 0 || row > numCols()) {
            throw new CAException(CAException.INVALID_LOCATION);
        }
    }

    /**
     * Make a user-defined number of each type of cell in random locations
     * @param cellNum
     */
    private void parseCellNum(String cellNum) {
        Scanner sc = new Scanner(cellNum);
        Deque<Integer> randomSeq = getRandomSeq(numRows() * numCols());
        while(sc.hasNext()) {
            try {
                addRandomCells(sc.nextInt(), sc.nextInt(), randomSeq);
            } catch(NoSuchElementException e) {
                sc.close();
                throw new CAException(CAException.INVALID_CELL_CONFIG);
            }
        }
        sc.close();
    }
    
    /**
     * Make cells in random locations and according to a distribution on the type of cell
     * @param cellProb
     */
    private void parseCellProb(String cellProb) {
    	Scanner sc = new Scanner(cellProb);
    	ArrayList<Integer> probs = new ArrayList<Integer>();
    	int total = 0;
    	while(sc.hasNext()) {
    	    int prob;
    	    try {
    	        prob = sc.nextInt();
    	    } catch (NoSuchElementException e) {
    	        sc.close();
                throw new CAException(CAException.INVALID_CELL_CONFIG);
    	    }
    	    total += prob;
    	    if(total > 100) {
    	        sc.close();
    	        throw new CAException(CAException.INVALID_CELL_PROB);
    	    }
            probs.add(total);
    	}
    	for (int i = 0; i < numRows(); i++) {
            for (int j = 0; j < numCols(); j++) {
                myCellConfig.add(new CellConfig(i, j, randomCellType(probs)));
            }
        }
    	sc.close();
    }
    
    /**
     * Returns a random cell type
     * @param probs
     * @return
     */
    private int randomCellType(List<Integer> probs) {
    	int rand = ThreadLocalRandom.current().nextInt(100);
    	for(int i = 0; i < probs.size(); i++) {
    	    if(rand < probs.get(i)) {
    	        return i;
    	    }
    	}
    	return 0;
    }
    
    /**
     * Returns the positive integer contained in a string
     * @param field
     * @return
     */
    private int parsePositiveInteger(String field) {
        return Math.abs(Integer.parseInt(myData.get(field)));
    }
    
    /**
     * Returns a sequence of random integers
     * @param size
     * @return
     */
    private Deque<Integer> getRandomSeq(int size) {
        List<Integer> random = new ArrayList<Integer>();
        for(int i = 0; i < size; i++) {
            random.add(i);
        }
        Collections.shuffle(random);
        return new ArrayDeque<Integer>(random);
    }

    /**
     * Adds random types of cells at random locations specified in a queue
     * @param state
     * @param num
     * @param locations
     */
    private void addRandomCells(int state, int num, Deque<Integer> locations) {
        for(int i = 0; i < num; i++) {
            int location;
            try {
                location = locations.pop();
            } catch(NoSuchElementException e) {
                throw new CAException(CAException.INVALID_CELL_NUM);
            }
            myCellConfig.add(new CellConfig(location / numRows(), location % numRows(), state));
        }
    }
	
	/**
	 * Create a data subclass corresponding to the appropriate simulation
	 * @param data
	 * @return
	 */
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