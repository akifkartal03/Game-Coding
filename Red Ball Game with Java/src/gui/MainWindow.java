package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/***
 * Window of the game extends from JFrame
 */
public class MainWindow extends JFrame {

    private MainJPanel contentPanel;
    private Thread gameThread;
    private JTextArea logArea;
    private JScrollPane chatScroll;
    private JPanel chatPanel;
    private JPanel mainPanel;

    /**
     * Start the game
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow frame = new MainWindow();
                    frame.setVisible(true);
                    frame.createThread();
                    frame.startThread();
                    JOptionPane.showMessageDialog(null, "Welcome to GTU Red Ball 443 Game"
                            + "\nPlease Start Game from Menu!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame(window).
     */
    public MainWindow() {
        setResizable(false);
        setTitle("GTU Red Ball 443");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(601, 707);
        setLocation(400, 20);

        //menu of the game
        JMenu menu, opt;
        JMenuItem start, pause, exit, keybOpt;
        JMenuBar mb = new JMenuBar();
        menu = new JMenu("Menu");
        start = new JMenuItem("Start");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (contentPanel.getState() == GameState.Paused) {
                    contentPanel.setState(GameState.Playing);
                    logArea.append("\nGame Started. Use your keyboard!");
                } else if (contentPanel.getState() == GameState.Finished) {
                    contentPanel.initGame();
                    logArea.setText("Game Started. Use your keyboard!");
                    contentPanel.setState(GameState.Playing);
                }
            }
        });
        pause = new JMenuItem("Pause");
        pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (contentPanel.getState() == GameState.Playing) {
                    contentPanel.setState(GameState.Paused);
                    logArea.append("\nGame Paused!");
                } else if (contentPanel.getState() == GameState.Finished) {
                    JOptionPane.showMessageDialog(null, "Game Over!\n"
                            + "Start New Game from Menu", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        opt = new JMenu("Options");
        keybOpt = new JMenuItem("Keyboard Options");
        keybOpt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "D: Move Right\n"
                        + "Space: Jump", "Keyboard Options", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(start);
        menu.add(pause);
        menu.add(exit);
        opt.add(keybOpt);
        mb.add(menu);
        mb.add(opt);
        setJMenuBar(mb);
        initGame();

    }

    public void initGame() {
        logArea = new JTextArea("Game Paused!", 8, 40);
        logArea.setEditable(false);
        logArea.setFocusable(false);
        chatScroll = new JScrollPane(logArea);
        chatPanel = new JPanel(new BorderLayout());
        chatPanel.add(new JLabel("Log:", SwingConstants.LEFT), BorderLayout.PAGE_START);
        chatPanel.add(chatScroll);
        contentPanel = new MainJPanel(logArea);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(contentPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(chatPanel);

        setContentPane(mainPanel);
        addKeyListener(contentPanel);
    }

    /***
     * Create a thread for game screen.
     */
    public void createThread() {
        try {
            //contentPanel object is runnable and
            //extends from JPanel class.
            gameThread = new Thread(contentPanel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * Start the thread
     */
    public void startThread() {
        gameThread.start();
    }

}
