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
		return numRow;
	}
	
	public int numCols() {
	    return numCol;
	}
	
	public Collection<CellConfig> getCell() {
	    return myCellConfig;
	}
	
	public abstract Collection<String> getExtraField();
    
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
    
    private void validateLocation(int row, int col) {
        if(row < 0 || row >= numRows() || col < 0 || row > numCols()) {
            throw new CAException(CAException.INVALID_LOCATION);
        }
    }

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
    
    private int randomCellType(List<Integer> probs) {
    	int rand = ThreadLocalRandom.current().nextInt(100);
    	for(int i = 0; i < probs.size(); i++) {
    	    if(rand < probs.get(i)) {
    	        return i;
    	    }
    	}
    	return 0;
    }
    
    private int parsePositiveInteger(String field) {
        return Math.abs(Integer.parseInt(myData.get(field)));
    }
    
    private Deque<Integer> getRandomSeq(int size) {
        List<Integer> random = new ArrayList<Integer>();
        for(int i = 0; i < size; i++) {
            random.add(i);
        }
        Collections.shuffle(random);
        return new ArrayDeque<Integer>(random);
    }

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