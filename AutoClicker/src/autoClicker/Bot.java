package autoClicker;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;

public class Bot
{
    static Robot robot;
    public static int mouseX;
    public static int mouseY;
    public static int loopCount;
    public static int clickTime;
    public static boolean forceStop;
    
    static {
        Bot.robot = null;
    }
    
    public static void startLoop() {
        while (true) {
            Bot.mouseX = (int)MouseInfo.getPointerInfo().getLocation().getX();
            Bot.mouseY = (int)MouseInfo.getPointerInfo().getLocation().getY();
            GUI.mousePosLabel.setText("Mouse Location: (" + Bot.mouseX + ", " + Bot.mouseY + ")");
        }
    }
    
    public static void l_click(final int x, final int y, final int t) throws AWTException {
        (Bot.robot = new Robot()).delay(t);
        Bot.robot.mouseMove(x, y);
        Bot.robot.delay(50);
        Bot.robot.mousePress(1024);
        Bot.robot.delay(50);
        Bot.robot.mouseRelease(1024);
    }
    
    public static void r_click(final int x, final int y, final int t) throws AWTException {
        (Bot.robot = new Robot()).delay(t);
        Bot.robot.mouseMove(x, y);
        Bot.robot.delay(50);
        Bot.robot.mousePress(4096);
        Bot.robot.delay(50);
        Bot.robot.mouseRelease(4096);
    }
    
    public static void startPlayback() {
        if (!GUI.repeat) {
            for (int i = 0; i != GUI.listModel.getSize(); ++i) {
                final String nextPoint = GUI.listModel.getElementAt(i);
                String button = "";
                if (nextPoint.contains("R")) {
                    button = "Right";
                }
                else if (nextPoint.contains("L")) {
                    button = "Left";
                }
                final int yIndex = nextPoint.indexOf("y");
                final int xIndex = nextPoint.indexOf("x");
                final int commaIndex = nextPoint.indexOf(",");
                final int mIndex = nextPoint.indexOf("m");
                final int yCoord = Integer.parseInt(nextPoint.substring(yIndex + 2, nextPoint.length()));
                final int xCoord = Integer.parseInt(nextPoint.substring(xIndex + 2, commaIndex));
                final int clickTime = Integer.parseInt(nextPoint.substring(1, mIndex - 1));
                if (button.equals("Right")) {
                    try {
                        r_click(xCoord, yCoord, clickTime);
                    }
                    catch (AWTException e1) {
                        e1.printStackTrace();
                    }
                }
                else if (button.equals("Left")) {
                    try {
                        l_click(xCoord, yCoord, clickTime);
                    }
                    catch (AWTException e1) {
                        e1.printStackTrace();
                    }
                }
                else {
                    System.out.println(" Mouse button not found");
                }
            }
            GUI.textBox.setEnabled(true);
        }
        else {
            while (GUI.repeat && !Bot.forceStop) {
                for (int i = 0; i != GUI.listModel.getSize() && !Bot.forceStop; ++i) {
                    final String nextPoint = GUI.listModel.getElementAt(i);
                    String button = "";
                    if (nextPoint.contains("R")) {
                        button = "Right";
                    }
                    else if (nextPoint.contains("L")) {
                        button = "Left";
                    }
                    final int yIndex = nextPoint.indexOf("y");
                    final int xIndex = nextPoint.indexOf("x");
                    final int commaIndex = nextPoint.indexOf(",");
                    final int mIndex = nextPoint.indexOf("m");
                    final int yCoord = Integer.parseInt(nextPoint.substring(yIndex + 2, nextPoint.length()));
                    final int xCoord = Integer.parseInt(nextPoint.substring(xIndex + 2, commaIndex));
                    Bot.clickTime = Integer.parseInt(nextPoint.substring(1, mIndex - 1));
                    if (i == 0 && Bot.loopCount > 0) {
                        Bot.clickTime = Integer.parseInt(GUI.textBox.getText());
                    }
                    if (button.equals("Right")) {
                        try {
                            r_click(xCoord, yCoord, Bot.clickTime);
                        }
                        catch (AWTException e2) {
                            e2.printStackTrace();
                        }
                    }
                    else if (button.equals("Left")) {
                        try {
                            l_click(xCoord, yCoord, Bot.clickTime);
                        }
                        catch (AWTException e2) {
                            e2.printStackTrace();
                        }
                    }
                    else {
                        System.out.println("Mouse button not found");
                    }
                }
                ++Bot.loopCount;
            }
            Bot.forceStop = false;
        }
        GUI.startButton.setEnabled(true);
    }
}
