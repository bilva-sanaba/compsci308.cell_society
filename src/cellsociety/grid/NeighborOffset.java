package cellsociety.grid;

import java.util.ArrayList;
import java.util.List;

import cellsociety.CAException;

/**
 * Data class for neighbor offsets
 * @author Mike Liu
 *
 */
public class NeighborOffset {
    
    private List<Integer> myRowOffset, myColOffset;
    
    public NeighborOffset(List<Integer> rowOffset, List<Integer> colOffset) {
        if(rowOffset.size() != colOffset.size()) {
            throw new CAException(CAException.MISMATCH_OFFSET);
        }
        myRowOffset = new ArrayList<Integer>(rowOffset);
        myColOffset = new ArrayList<Integer>(colOffset);
    }
    
    public NeighborOffset(NeighborOffset other, List<Integer> rowOffset, List<Integer> colOffset) {
        this(rowOffset, colOffset);
        myRowOffset.addAll(other.myRowOffset);
        myColOffset.addAll(other.myColOffset);
    }
    
    public int getRowOffset(int i) {
        return myRowOffset.get(i);
    }
    
    public int getColOffset(int i) {
        return myColOffset.get(i);
    }
    
    public int length() {
        return myRowOffset.size();
    }
}
