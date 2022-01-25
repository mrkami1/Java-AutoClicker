package autoClicker;

import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class GUI
{
    static JFrame frame;
    static JPanel panel;
    public static JLabel mousePosLabel;
    public static JLabel stopRecordingLabel;
    public static JLabel stopPlayingLabel;
    public static JLabel mottoLabel;
    public static JLabel inputLabel;
    public static JButton recordButton;
    public static JButton removeButton;
    public static JButton removeAllButton;
    public static JButton startButton;
    public static JButton infiniteButton;
    public static JCheckBox checkRepeat;
    public static JTextField textBox;
    public static JList<String> clickList;
    public static DefaultListModel<String> listModel;
    public static boolean repeat;
    
    static {
        GUI.frame = new JFrame();
        GUI.panel = new JPanel();
        GUI.mousePosLabel = new JLabel("Mouse location: (0, 0)");
        GUI.stopRecordingLabel = new JLabel("Press 'Q' to stop recording");
        GUI.stopPlayingLabel = new JLabel("Press 'END' to stop playback");
        GUI.mottoLabel = new JLabel("Creator: Kami");
        GUI.inputLabel = new JLabel("Reset time (ms)");
        GUI.recordButton = new JButton("Record");
        GUI.removeButton = new JButton("Remove");
        GUI.removeAllButton = new JButton("Remove All");
        GUI.startButton = new JButton("START");
        GUI.infiniteButton = new JButton("Infinite Click");
        GUI.checkRepeat = new JCheckBox("Repeat");
        GUI.textBox = new JTextField("0");
        GUI.clickList = new JList<String>();
        GUI.listModel = new DefaultListModel<String>();
        GUI.repeat = false;
    }
    
    public static void createGUI() {
        System.out.println("creating gui");
        GUI.recordButton.setBounds(25, 50, 200, 50);
        GUI.recordButton.setBackground(Color.GRAY);
        GUI.recordButton.setForeground(Color.white);
        GUI.recordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                GUI.recordButton.setText("Recording");
                GUI.recordButton.setEnabled(false);
                GUI.removeButton.setEnabled(false);
                GUI.removeAllButton.setEnabled(false);
                GUI.startButton.setEnabled(false);
            }
        });
        GUI.removeButton.setBounds(250, 15, 100, 25);
        GUI.removeButton.setBackground(Color.GRAY);
        GUI.removeButton.setForeground(Color.white);
        GUI.removeButton.setEnabled(false);
        GUI.removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final int[] indices = GUI.clickList.getSelectedIndices();
                for (int i = indices.length - 1; i >= 0; --i) {
                    GUI.listModel.removeElementAt(indices[i]);
                    GUI.clickList.remove(indices[i]);
                }
                if (GUI.listModel.isEmpty()) {
                    GUI.removeButton.setEnabled(false);
                    GUI.startButton.setEnabled(false);
                }
            }
        });
        GUI.removeAllButton.setBounds(450, 15, 100, 25);
        GUI.removeAllButton.setBackground(Color.GRAY);
        GUI.removeAllButton.setForeground(Color.white);
        GUI.removeAllButton.setEnabled(false);
        GUI.removeAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                GUI.listModel.removeAllElements();
                GUI.clickList.removeAll();
                GUI.removeAllButton.setEnabled(false);
                GUI.removeButton.setEnabled(false);
                GUI.startButton.setEnabled(false);
            }
        });
        GUI.startButton.setBounds(25, 430, 200, 50);
        GUI.startButton.setBackground(Color.GRAY);
        GUI.startButton.setForeground(Color.white);
        GUI.startButton.setEnabled(false);
        GUI.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                GUI.startButton.setEnabled(false);
                GUI.textBox.setEnabled(false);
                Bot.startPlayback();
            }
        });
        GUI.infiniteButton.setBounds(25, 350, 200, 50);
        GUI.infiniteButton.setBackground(Color.GRAY);
        GUI.infiniteButton.setForeground(Color.white);
        GUI.infiniteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                GUI.infiniteButton.setEnabled(false);
                do {
                    try {
                        Bot.l_click(Bot.mouseX, Bot.mouseY, 1);
                    }
                    catch (AWTException e2) {
                        e2.printStackTrace();
                    }
                } while (!Bot.forceStop);
                GUI.infiniteButton.setEnabled(true);
            }
        });
        GUI.mousePosLabel.setBounds(5, 540, 200, 25);
        GUI.mousePosLabel.setForeground(Color.white);
        GUI.stopRecordingLabel.setBounds(5, 500, 200, 25);
        GUI.stopRecordingLabel.setForeground(Color.white);
        GUI.stopPlayingLabel.setBounds(5, 520, 200, 25);
        GUI.stopPlayingLabel.setForeground(Color.white);
        GUI.mottoLabel.setBounds(25, 15, 200, 25);
        GUI.mottoLabel.setForeground(Color.white);
        GUI.mottoLabel.setFont(new Font("Courier New", 2, 14));
        GUI.mottoLabel.setText("By: Mr. Kami");
        GUI.inputLabel.setForeground(Color.white);
        GUI.inputLabel.setBounds(25, 140, 100, 25);
        GUI.checkRepeat.setBounds(20, 110, 100, 25);
        GUI.checkRepeat.setForeground(Color.WHITE);
        GUI.checkRepeat.setOpaque(false);
        GUI.checkRepeat.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent e) {
                if (e.getStateChange() == 1) {
                    GUI.repeat = true;
                }
                else {
                    GUI.repeat = false;
                }
            }
        });
        GUI.textBox.setBounds(25, 160, 100, 25);
        GUI.textBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(final FocusEvent arg0) {
            }
            
            @Override
            public void focusLost(final FocusEvent arg0) {
                if (GUI.textBox.getText().equals("")) {
                    GUI.textBox.setText("0");
                }
            }
        });
        GUI.clickList.setSelectionMode(2);
        GUI.clickList.setLayoutOrientation(1);
        GUI.clickList = new JList<String>(GUI.listModel);
        final JScrollPane scroll = new JScrollPane(GUI.clickList);
        scroll.setBounds(250, 50, 300, 500);
        GUI.panel.setLayout(null);
        GUI.panel.add(GUI.mottoLabel);
        GUI.panel.add(GUI.mousePosLabel);
        GUI.panel.add(GUI.stopRecordingLabel);
        GUI.panel.add(GUI.stopPlayingLabel);
        GUI.panel.add(GUI.inputLabel);
        GUI.panel.add(GUI.recordButton);
        GUI.panel.add(GUI.removeButton);
        GUI.panel.add(GUI.removeAllButton);
        GUI.panel.add(GUI.startButton);
        GUI.panel.add(GUI.infiniteButton);
        GUI.panel.add(GUI.checkRepeat);
        GUI.panel.add(GUI.textBox);
        GUI.panel.add(scroll);
        GUI.panel.setVisible(true);
        GUI.panel.setBackground(Color.DARK_GRAY);
        GUI.frame.add(GUI.panel);
        GUI.frame.setVisible(true);
        GUI.frame.setSize(580, 600);
        GUI.frame.setTitle("Kami AutoClicker");
        GUI.frame.setDefaultCloseOperation(3);
        GUI.frame.setAlwaysOnTop(true);
        GUI.frame.setResizable(false);           
    }      
}
