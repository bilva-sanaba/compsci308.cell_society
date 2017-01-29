package cellsociety;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public abstract class GameEngine {
    
    public static final int FRAMES_PER_SECOND = 1;
    public static final double MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    
    private Timeline animation;
    private Grid grid;
    
    public GameEngine(Grid grid) {
        animation = getTimeline();
    }
    
    public void play() {
        animation.play();
    }
    
    public void pause() {
        animation.pause();
    }
    
    public abstract void step(double secondDelay);
    
    private Timeline getTimeline() {
        Timeline tl = new Timeline();
        tl.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> step(SECOND_DELAY));
        tl.getKeyFrames().add(frame);
        return tl;
    }
}
