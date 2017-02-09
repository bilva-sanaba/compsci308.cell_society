package grid;

import java.util.List;

import cellsociety.CAException;

public class NeighborOffset {
    
    private List<Integer> myRowOffset, myColOffset;
    
    public NeighborOffset(List<Integer> rowOffset, List<Integer> colOffset) {
        if(rowOffset.size() != colOffset.size()) {
            throw new CAException(CAException.MISMATCH_OFFSET);
        }
        myRowOffset = rowOffset;
        myColOffset = colOffset;
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
