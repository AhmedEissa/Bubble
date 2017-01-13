import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by IntelliJ IDEA.
 * User: sthirumuru
 * Date: 5/26/14
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class BubbleManager extends Timer {
    private BubbleFrameController bubbleFrameController = null;
    private BubbleFrame bubbleFrame = null;

    public BubbleManager(BubbleFrameController bubbleFrameController) {
        this.bubbleFrameController = bubbleFrameController;
        this.bubbleFrame = bubbleFrameController.getBubbleFrame();
    }

    public void scheduleBubbleTasks() {
        schedule(getMoveBubblesTask(), Calendar.getInstance().getTime(), 30);
        schedule(getAddBubblesTask(), Calendar.getInstance().getTime(), 300);
    }

    private TimerTask getMoveBubblesTask() {
        return new UIUpdateTimerTask() {
            @Override
            public void uiUpdate() {
                if (!bubbleFrameController.isFreeze()) {
                    if (bubbleFrame.anyBubbleReachedOtherEnd()) {
                        bubbleFrame.removeBubble();

                    } else {
                        bubbleFrame.removeBubbles();
                        bubbleFrame.moveBubbles();
                    }
                }
            }
        };
    }

    private TimerTask getAddBubblesTask() {
        return new UIUpdateTimerTask() {
            @Override
            public void uiUpdate() {
                if (!bubbleFrameController.isFreeze()) {
                    if (bubbleFrame.getRandomInt(100) > 50) {
                        bubbleFrame.addBubble();
                    }
                }

            }
        };
    }

}
