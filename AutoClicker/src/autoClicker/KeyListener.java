
package autoClicker;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyListener implements NativeKeyListener
{
    public static void listenForKey() {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(new KeyListener());
    }
    
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (NativeKeyEvent.getKeyText(e.getKeyCode()) == "Q") {
            GUI.recordButton.setEnabled(true);
            GUI.recordButton.setText("Record");
            MouseListener.time = 0L;
            if (!GUI.listModel.isEmpty()) {
                GUI.startButton.setEnabled(true);
                GUI.removeButton.setEnabled(true);
                GUI.removeAllButton.setEnabled(true);
            }
        }
        else if (NativeKeyEvent.getKeyText(e.getKeyCode()) == "End") {
            Bot.forceStop = true;
            GUI.textBox.setEnabled(true);
        }
        if (NativeKeyEvent.getKeyText(e.getKeyCode()) == "Enter" && GUI.textBox.hasFocus()) {
            final String text = GUI.textBox.getText();
            text.toLowerCase();
            text.trim();
            int value = 0;
            final String[] numbers = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
            for (int i = 0; i != text.length(); ++i) {
                final String index = text.substring(i, i + 1);
                //if (!ArrayUtils.contains((Object[])numbers, (Object)index)) {
                    GUI.textBox.setText("0");
                //}
                value = Integer.parseInt(text);
            }
            if (text.equals("") || value > 60000) {
                GUI.textBox.setText("0");
            }
        }
    }
    
    public void nativeKeyReleased(final NativeKeyEvent e) {
    }
    
    public void nativeKeyTyped(final NativeKeyEvent e) {
    }
}
