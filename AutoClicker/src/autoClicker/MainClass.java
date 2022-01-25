package autoClicker;

import java.awt.AWTException;

public class MainClass
{
    public static void main(String[] args) throws AWTException {
        System.out.println("Program started");
        GUI.createGUI();
        MouseListener.listenForMouse();
        KeyListener.listenForKey();
        GUI.randFont();
        Bot.startLoop();
    }
}
