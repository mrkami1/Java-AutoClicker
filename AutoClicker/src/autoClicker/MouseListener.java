package autoClicker;

import java.util.concurrent.TimeUnit;
import java.util.logging.*;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;


public class MouseListener implements NativeMouseInputListener {
    public static long startTime;
    public static long prevTime;
    public static long time;
    
    public static void listenForMouse() {
        try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

        MouseListener mouse = new MouseListener();

		GlobalScreen.addNativeMouseListener(mouse);
		GlobalScreen.addNativeMouseMotionListener(mouse);

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        // Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);

    }
    
    public void nativeMouseClicked(NativeMouseEvent e) {
    }
    
    public void nativeMousePressed(NativeMouseEvent e) {
        if (!GUI.recordButton.isEnabled()) {
            MouseListener.startTime = MouseListener.prevTime;
            MouseListener.prevTime = System.nanoTime();
            MouseListener.time = TimeUnit.NANOSECONDS.toMillis(MouseListener.prevTime - MouseListener.startTime);
            System.out.println(MouseListener.time);
            if (GUI.listModel.isEmpty()) {
                MouseListener.time = 0L;
            }
            if (e.getButton() == 1) {
                GUI.listModel.addElement("[" + MouseListener.time + " ms] Left Click at: x=" + e.getX() + ", y=" + e.getY());
            }
            else if (e.getButton() == 2) {
                GUI.listModel.addElement("[" + MouseListener.time + " ms] Right Click at: x=" + e.getX() + ", y=" + e.getY());
            }
            GUI.startButton.setEnabled(false);
        }
    }
    
    public void nativeMouseReleased(NativeMouseEvent e) {
    }
    
    public void nativeMouseDragged(NativeMouseEvent e) {
    }
    
    public void nativeMouseMoved(NativeMouseEvent e) {
    }
}
