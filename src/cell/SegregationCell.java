package cell;

import cell.state.SegregationState;

public class SegregationCell extends Cell{
    
    public SegregationCell(SegregationState state) {
        super(state);
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

