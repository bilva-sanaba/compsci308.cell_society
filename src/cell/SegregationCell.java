package cell;

import cell.state.SegregationState;

public class SegregationCell extends Cell{
    
    private boolean unhappy;
    
    public SegregationCell(SegregationState state) {
        super(state);
    }
    
    public void setUnhappy(boolean isUnhappy) {
        unhappy = isUnhappy;
    }
    
    public boolean isUnhappy() {
        return unhappy;
    }
    
    public void leave() {
    	setNextState(SegregationState.EMPTY);
    }
    
    public void fillRed(){
    	setNextState(SegregationState.RED);
    }
    
    public void fillBlue(){
    	setNextState(SegregationState.BLUE);
    }
}

