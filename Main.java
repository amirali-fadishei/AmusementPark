import javax.swing.*;
import java.awt.*;

public class Main {
    //game main frame
    private final JFrame frame;
    //game main panels
    private final JPanel loginPanel;
    private final JPanel mainGamePanel;
    private final JPanel storePanel;
    int turn = 1;
    Players player1 = new Players();
    Players player2 = new Players();
    int clickNum = 0;

    public Main() {
        frame = new JFrame("Amusement Park");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginPanel = createLoginPanel();
        mainGamePanel = createMainGamePanel();
        storePanel = createStorePanel();

        frame.add(loginPanel);
        frame.setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setPreferredSize(new Dimension(1280, 720));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 20, 0);

        JLabel titleLabel = new JLabel("به شهربازی خوش آمدید!", SwingConstants.CENTER);
        welcomePanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        JButton startButton = new JButton("شروع بازی");
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.addActionListener(e -> switchPanel(mainGamePanel));
        welcomePanel.add(startButton, gbc);

        gbc.gridy = 2;
        JButton helpButton = new JButton("راهنما");
        helpButton.setPreferredSize(new Dimension(200, 50));
        welcomePanel.add(helpButton, gbc);
        panel.add(welcomePanel);
        return panel;
    }

    public JPanel createMainGamePanel() {
        JPanel panel = new JPanel();

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(1280, 720));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.RED);
        topPanel.setPreferredSize(new Dimension(1280, 100));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.BLUE);
        bottomPanel.setPreferredSize(new Dimension(1280, 100));

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayout(6, 3, 10, 10));
        sidePanel.setBackground(Color.GREEN);
        sidePanel.setPreferredSize(new Dimension(400, 520));

        Coin[] redCoins = new Coin[1];
        Coin[] blueCoins = new Coin[1];
        Coin[] greenCoins = new Coin[1];
        Coin[] whiteCoins = new Coin[1];
        Coin[] blackCoins = new Coin[1];
        redCoins[0] = new Coin(4, "red");
        blueCoins[0] = new Coin(4, "blue");
        greenCoins[0] = new Coin(4, "green");
        whiteCoins[0] = new Coin(4, "white");
        blackCoins[0] = new Coin(4, "black");


        JLabel lableBtn1 = new JLabel(String.valueOf(redCoins[0].num), SwingConstants.CENTER);
        JButton slotButton11 = new JButton("دو سکه قرمز");
        JButton slotButton12 = new JButton("یک سکه قرمز");
        JLabel lableBtn2 = new JLabel(String.valueOf(blueCoins[0].num), SwingConstants.CENTER);
        JButton slotButton21 = new JButton("دو سکه آبی");
        JButton slotButton22 = new JButton("یک سکه آبی");
        JLabel lableBtn3 = new JLabel(String.valueOf(greenCoins[0].num), SwingConstants.CENTER);
        JButton slotButton31 = new JButton("دو سکه سبز");
        JButton slotButton32 = new JButton("یک سکه سبز");
        JLabel lableBtn4 = new JLabel(String.valueOf(whiteCoins[0].num), SwingConstants.CENTER);
        JButton slotButton41 = new JButton("دو سکه سفید");
        JButton slotButton42 = new JButton("یک سکه سفید");
        JLabel lableBtn5 = new JLabel(String.valueOf(blackCoins[0].num), SwingConstants.CENTER);
        JButton slotButton51 = new JButton("دو سکه سیاه");
        JButton slotButton52 = new JButton("یک سکه سیاه");

        slotButton11.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (redCoins[0].num == 4 && clickNum < 1) {
                        player1.takeCoin(2, "red");
                        clickNum++;
                        redCoins[0].num -= 2;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                    }
                    turn *= -1;
                    break;
                case -1:
                    if (redCoins[0].num == 4 && clickNum < 1) {
                        player2.takeCoin(2, "red");
                        redCoins[0].num -= 2;
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                    }
                    turn *= -1;
                    break;
            }
            lableBtn1.setText(String.valueOf(Math.max(0, redCoins[0].num)));
        });
        slotButton21.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (blueCoins[0].num == 4 && clickNum < 1) {
                        player1.takeCoin(2, "blue");
                        blueCoins[0].num -= 2;
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                    }
                    turn *= -1;
                    break;
                case -1:
                    if (blueCoins[0].num == 4 && clickNum < 1) {
                        player2.takeCoin(2, "blue");
                        blueCoins[0].num -= 2;
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                    }
                    turn *= -1;
                    break;
            }
            lableBtn2.setText(String.valueOf(Math.max(0, blueCoins[0].num)));
        });
        slotButton31.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (greenCoins[0].num == 4 && clickNum < 1) {
                        player1.takeCoin(2, "green");
                        greenCoins[0].num -= 2;
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                    }
                    turn *= -1;
                    break;
                case -1:
                    if (greenCoins[0].num == 4 && clickNum < 1) {
                        player2.takeCoin(2, "green");
                        greenCoins[0].num -= 2;
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                    }
                    turn *= -1;
                    break;
            }
            lableBtn3.setText(String.valueOf(Math.max(0, greenCoins[0].num)));
        });
        slotButton41.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (whiteCoins[0].num == 4 && clickNum < 1) {
                        player1.takeCoin(2, "white");
                        whiteCoins[0].num -= 2;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                        clickNum++;
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                    }
                    turn *= -1;
                    break;
                case -1:
                    if (whiteCoins[0].num == 4 && clickNum < 1) {
                        player2.takeCoin(2, "white");
                        whiteCoins[0].num -= 2;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                        clickNum++;
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                        slotButton11.setEnabled(true);
                        slotButton21.setEnabled(true);
                        slotButton31.setEnabled(true);
                        slotButton41.setEnabled(true);
                        slotButton51.setEnabled(true);
                    }
                    turn *= -1;
                    break;
            }
            lableBtn4.setText(String.valueOf(Math.max(0, whiteCoins[0].num)));
        });
        slotButton51.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (blackCoins[0].num == 4 && clickNum < 1) {
                        player1.takeCoin(2, "black");
                        blackCoins[0].num -= 2;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                        clickNum++;
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                    }
                    turn *= -1;
                    break;
                case -1:
                    if (blackCoins[0].num == 4 && clickNum < 1) {
                        player2.takeCoin(2, "black");
                        blackCoins[0].num -= 2;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                        clickNum++;
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                    }
                    turn *= -1;
                    break;
            }
            lableBtn5.setText(String.valueOf(Math.max(0, blackCoins[0].num)));
        });
        slotButton12.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (redCoins[0].num > 0 && clickNum < 3) {
                        player1.takeCoin(1, "red");
                        redCoins[0].num--;
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                        slotButton11.setEnabled(true);
                        slotButton21.setEnabled(true);
                        slotButton31.setEnabled(true);
                        slotButton41.setEnabled(true);
                        slotButton51.setEnabled(true);
                    }
                    turn *= -1;
                    break;
                case -1:
                    if (redCoins[0].num > 0 && clickNum < 3) {
                        player2.takeCoin(1, "red");
                        redCoins[0].num--;
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                        slotButton11.setEnabled(true);
                        slotButton21.setEnabled(true);
                        slotButton31.setEnabled(true);
                        slotButton41.setEnabled(true);
                        slotButton51.setEnabled(true);
                    }
                    turn *= -1;
                    break;
            }
            lableBtn1.setText(String.valueOf(Math.max(0, redCoins[0].num)));
        });
        slotButton22.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (blueCoins[0].num > 0 && clickNum < 3) {
                        player1.takeCoin(1, "blue");
                        blueCoins[0].num--;
                        clickNum++;
                        slotButton22.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                        slotButton11.setEnabled(true);
                        slotButton21.setEnabled(true);
                        slotButton31.setEnabled(true);
                        slotButton41.setEnabled(true);
                        slotButton51.setEnabled(true);
                    }
                    turn *= -1;
                    break;
                case -1:
                    if (blueCoins[0].num > 0 && clickNum < 3) {
                        player2.takeCoin(1, "blue");
                        blueCoins[0].num--;
                        clickNum++;
                        slotButton22.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                        slotButton11.setEnabled(true);
                        slotButton21.setEnabled(true);
                        slotButton31.setEnabled(true);
                        slotButton41.setEnabled(true);
                        slotButton51.setEnabled(true);
                    }
                    turn *= -1;
                    break;
            }
            lableBtn2.setText(String.valueOf(Math.max(0, blueCoins[0].num)));
        });
        slotButton32.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (greenCoins[0].num > 0 && clickNum < 3) {
                        player1.takeCoin(1, "green");
                        greenCoins[0].num--;
                        clickNum++;
                        slotButton32.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                        slotButton11.setEnabled(true);
                        slotButton21.setEnabled(true);
                        slotButton31.setEnabled(true);
                        slotButton41.setEnabled(true);
                        slotButton51.setEnabled(true);
                    }
                    turn *= -1;
                    break;
                case -1:
                    if (greenCoins[0].num > 0 && clickNum < 3) {
                        player2.takeCoin(1, "green");
                        greenCoins[0].num--;
                        clickNum++;
                        slotButton32.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                        slotButton11.setEnabled(true);
                        slotButton21.setEnabled(true);
                        slotButton31.setEnabled(true);
                        slotButton41.setEnabled(true);
                        slotButton51.setEnabled(true);
                    }
                    turn *= -1;
                    break;
            }
            lableBtn3.setText(String.valueOf(Math.max(0, greenCoins[0].num)));
        });
        slotButton42.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (whiteCoins[0].num > 0 && clickNum < 3) {
                        player1.takeCoin(1, "white");
                        whiteCoins[0].num--;
                        clickNum++;
                        slotButton42.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                        slotButton11.setEnabled(true);
                        slotButton21.setEnabled(true);
                        slotButton31.setEnabled(true);
                        slotButton41.setEnabled(true);
                        slotButton51.setEnabled(true);
                    }
                    turn *= -1;
                    break;
                case -1:
                    if (whiteCoins[0].num > 0 && clickNum < 3) {
                        player2.takeCoin(1, "white");
                        whiteCoins[0].num--;
                        clickNum++;
                        slotButton42.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                        slotButton11.setEnabled(true);
                        slotButton21.setEnabled(true);
                        slotButton31.setEnabled(true);
                        slotButton41.setEnabled(true);
                        slotButton51.setEnabled(true);
                    }
                    turn *= -1;
                    break;
            }
            lableBtn4.setText(String.valueOf(Math.max(0, whiteCoins[0].num)));
        });
        slotButton52.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (blackCoins[0].num > 0 && clickNum < 3) {
                        player1.takeCoin(1, "black");
                        blackCoins[0].num--;
                        clickNum++;
                        slotButton52.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                        slotButton11.setEnabled(true);
                        slotButton21.setEnabled(true);
                        slotButton31.setEnabled(true);
                        slotButton41.setEnabled(true);
                        slotButton51.setEnabled(true);
                    }
                    turn *= -1;
                    break;
                case -1:
                    if (blackCoins[0].num > 0 && clickNum < 3) {
                        player2.takeCoin(1, "black");
                        blackCoins[0].num--;
                        clickNum++;
                        slotButton52.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                        slotButton11.setEnabled(true);
                        slotButton21.setEnabled(true);
                        slotButton31.setEnabled(true);
                        slotButton41.setEnabled(true);
                        slotButton51.setEnabled(true);
                    }
                    turn *= -1;
                    break;
            }
            lableBtn5.setText(String.valueOf(Math.max(0, blackCoins[0].num)));
        });

        sidePanel.add(lableBtn1);
        sidePanel.add(slotButton11);
        sidePanel.add(slotButton12);
        sidePanel.add(lableBtn2);
        sidePanel.add(slotButton21);
        sidePanel.add(slotButton22);
        sidePanel.add(lableBtn3);
        sidePanel.add(slotButton31);
        sidePanel.add(slotButton32);
        sidePanel.add(lableBtn4);
        sidePanel.add(slotButton41);
        sidePanel.add(slotButton42);
        sidePanel.add(lableBtn5);
        sidePanel.add(slotButton51);
        sidePanel.add(slotButton52);

        JButton enterButton = new JButton("فروشگاه");
        enterButton.addActionListener(e -> switchPanel(storePanel));
        sidePanel.add(enterButton);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.YELLOW);
        centerPanel.setLayout(new BorderLayout());

        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(bottomPanel, BorderLayout.SOUTH);
        centerPanel.add(sidePanel, BorderLayout.EAST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        panel.add(mainPanel);
        return panel;
    }


    private JPanel createStorePanel() {
        JPanel panel = new JPanel();

        JPanel grayPanel = new JPanel();
        grayPanel.setPreferredSize(new Dimension(1280, 720));

        JPanel prizePanel = new JPanel();
        prizePanel.setPreferredSize(new Dimension(800, 160));
        prizePanel.setLayout(new GridLayout(1, 4));
        JButton backward = new JButton("بازگشت");
        backward.addActionListener(e -> switchPanel(mainGamePanel));

        JPanel set1panel = new JPanel();
        set1panel.setPreferredSize(new Dimension(800, 160));
        set1panel.setLayout(new GridLayout(1, 4));

        JPanel set2panel = new JPanel();
        set2panel.setPreferredSize(new Dimension(800, 160));
        set2panel.setLayout(new GridLayout(1, 4));

        JPanel set3panel = new JPanel();
        set3panel.setPreferredSize(new Dimension(800, 160));
        set3panel.setLayout(new GridLayout(1, 4));

        Coin[] prize1Coins = new Coin[]{new Coin(4, "green"), null, null, null, new Coin(4, "red")};
        Coin[] prize2Coins = new Coin[]{new Coin(5, "green"), null, null, null, new Coin(5, "red")};
        Coin[] prize3Coins = new Coin[]{new Coin(5, "green"), null, null, new Coin(4, "blue"), null};
        Card[] prizeList = {new Card(3, prize1Coins, new JLabel(new ImageIcon("images\\card prize\\prize1.png"))), new Card(4, prize2Coins, new JLabel(new ImageIcon("images\\card prize\\prize2.png"))), new Card(4, prize3Coins, new JLabel(new ImageIcon("images\\card prize\\prize3.png")))};

        for (int t = 0; t < 3; t++) {
            prizePanel.add(prizeList[t].cardImg);
        }
        prizePanel.add(backward);
        grayPanel.add(prizePanel);

        Coin[] card11Coins = new Coin[]{new Coin(2, "green"), null, null, null, new Coin(2, "red")};
        Coin[] card12Coins = new Coin[]{new Coin(2, "green"), null, null, null, new Coin(3, "red")};
        Coin[] card13Coins = new Coin[]{new Coin(3, "green"), null, null, null, new Coin(3, "red")};
        Coin[] card14Coins = new Coin[]{null, null, null, new Coin(3, "blue"), new Coin(3, "red")};
        Coin[] card15Coins = new Coin[]{null, null, null, new Coin(3, "blue"), new Coin(2, "red")};
        Coin[] card16Coins = new Coin[]{null, null, null, new Coin(3, "blue"), new Coin(1, "red")};
        Coin[] card17Coins = new Coin[]{null, null, null, new Coin(3, "blue"), new Coin(3, "red")};
        Coin[] card18Coins = new Coin[]{new Coin(1, "green"), null, null, new Coin(3, "blue"), new Coin(2, "red")};
        Coin[] card19Coins = new Coin[]{new Coin(1, "green"), null, null, new Coin(2, "blue"), new Coin(3, "red")};
        Coin[] card110Coins = new Coin[]{new Coin(2, "green"), null, null, new Coin(2, "blue"), null};
        Coin[] card111Coins = new Coin[]{new Coin(2, "green"), null, null, new Coin(2, "blue"), null};
        Coin[] card112Coins = new Coin[]{new Coin(3, "green"), null, null, new Coin(2, "blue"), null};
        Coin[] card113Coins = new Coin[]{new Coin(3, "green"), null, null, new Coin(3, "blue"), null};
        Coin[] card114Coins = new Coin[]{new Coin(3, "green"), null, null, new Coin(3, "blue"), null};
        Coin[] card115Coins = new Coin[]{new Coin(2, "green"), null, null, new Coin(3, "blue"), null};
        Card[] setCard1 = {new Card(1, card11Coins, new JLabel(new ImageIcon("images\\card1\\11.png"))), new Card(1, card12Coins, new JLabel(new ImageIcon("images\\card1\\12.png"))), new Card(1, card13Coins, new JLabel(new ImageIcon("images\\card1\\13.png"))), new Card(0, card14Coins, new JLabel(new ImageIcon("images\\card1\\14.png"))), new Card(0, card15Coins, new JLabel(new ImageIcon("images\\card1\\15.png"))), new Card(0, card16Coins, new JLabel(new ImageIcon("images\\card1\\16.png"))), new Card(1, card17Coins, new JLabel(new ImageIcon("images\\card1\\17.png"))), new Card(1, card18Coins, new JLabel(new ImageIcon("images\\card1\\18.png"))), new Card(0, card19Coins, new JLabel(new ImageIcon("images\\card1\\19.png"))), new Card(0, card110Coins, new JLabel(new ImageIcon("images\\card1\\110.png"))), new Card(1, card111Coins, new JLabel(new ImageIcon("images\\card1\\111.png"))), new Card(1, card112Coins, new JLabel(new ImageIcon("images\\card1\\112.png"))), new Card(1, card113Coins, new JLabel(new ImageIcon("images\\card1\\113.png"))), new Card(0, card114Coins, new JLabel(new ImageIcon("images\\card1\\114.png"))), new Card(1, card115Coins, new JLabel(new ImageIcon("images\\card1\\115.png")))};
        setCard1[0].SCoins[3] = new specialCoin(1, "blue");
        setCard1[1].SCoins[3] = new specialCoin(1, "blue");
        setCard1[2].SCoins[3] = new specialCoin(1, "blue");
        setCard1[3].SCoins[0] = new specialCoin(1, "green");
        setCard1[4].SCoins[0] = new specialCoin(1, "green");
        setCard1[5].SCoins[0] = new specialCoin(1, "green");
        setCard1[6].SCoins[0] = new specialCoin(1, "green");
        setCard1[7].SCoins[0] = new specialCoin(1, "green");
        setCard1[8].SCoins[0] = new specialCoin(1, "green");
        setCard1[9].SCoins[4] = new specialCoin(1, "red");
        setCard1[10].SCoins[4] = new specialCoin(1, "red");
        setCard1[11].SCoins[4] = new specialCoin(1, "red");
        setCard1[12].SCoins[4] = new specialCoin(1, "red");
        setCard1[13].SCoins[4] = new specialCoin(1, "red");
        setCard1[14].SCoins[4] = new specialCoin(1, "red");

        Coin[] card21Coins = new Coin[]{null, new Coin(2, "white"), new Coin(2, "black"), new Coin(2, "blue"), null};
        Coin[] card22Coins = new Coin[]{null, new Coin(2, "white"), new Coin(2, "black"), new Coin(3, "blue"), null};
        Coin[] card23Coins = new Coin[]{null, new Coin(2, "white"), new Coin(3, "black"), new Coin(3, "blue"), null};
        Coin[] card24Coins = new Coin[]{null, new Coin(4, "white"), new Coin(2, "black"), new Coin(3, "blue"), null};
        Coin[] card25Coins = new Coin[]{null, new Coin(2, "white"), new Coin(2, "black"), new Coin(2, "blue"), new Coin(2, "red")};
        Coin[] card26Coins = new Coin[]{null, null, new Coin(3, "black"), new Coin(3, "blue"), new Coin(2, "red")};
        Coin[] card27Coins = new Coin[]{null, null, new Coin(2, "black"), new Coin(3, "blue"), new Coin(3, "red")};
        Coin[] card28Coins = new Coin[]{null, null, new Coin(2, "black"), new Coin(3, "blue"), new Coin(2, "red")};
        Coin[] card29Coins = new Coin[]{null, null, new Coin(2, "black"), new Coin(2, "blue"), new Coin(2, "red")};
        Coin[] card210Coins = new Coin[]{null, null, new Coin(3, "black"), new Coin(2, "blue"), new Coin(1, "red")};
        Coin[] card211Coins = new Coin[]{null, null, new Coin(3, "black"), new Coin(3, "blue"), new Coin(2, "red")};
        Coin[] card212Coins = new Coin[]{new Coin(1, "green"), null, new Coin(2, "black"), new Coin(3, "blue"), new Coin(2, "red")};
        Coin[] card213Coins = new Coin[]{new Coin(2, "green"), null, new Coin(2, "black"), null, new Coin(2, "red")};
        Coin[] card214Coins = new Coin[]{new Coin(1, "green"), null, new Coin(2, "black"), null, new Coin(3, "red")};
        Coin[] card215Coins = new Coin[]{new Coin(1, "green"), null, new Coin(3, "black"), null, new Coin(3, "red")};
        Card[] setCard2 = {new Card(2, card21Coins, new JLabel(new ImageIcon("images\\card2\\21.png"))), new Card(2, card22Coins, new JLabel(new ImageIcon("images\\card2\\22.png"))), new Card(2, card23Coins, new JLabel(new ImageIcon("images\\card2\\23.png"))), new Card(2, card24Coins, new JLabel(new ImageIcon("images\\card2\\24.png"))), new Card(2, card25Coins, new JLabel(new ImageIcon("images\\card2\\25.png"))), new Card(3, card26Coins, new JLabel(new ImageIcon("images\\card2\\26.png"))), new Card(3, card27Coins, new JLabel(new ImageIcon("images\\card2\\27.png"))), new Card(3, card28Coins, new JLabel(new ImageIcon("images\\card2\\28.png"))), new Card(3, card29Coins, new JLabel(new ImageIcon("images\\card2\\29.png"))), new Card(3, card210Coins, new JLabel(new ImageIcon("images\\card2\\210.png"))), new Card(4, card211Coins, new JLabel(new ImageIcon("images\\card2\\211.png"))), new Card(4, card212Coins, new JLabel(new ImageIcon("images\\card2\\212.png"))), new Card(4, card213Coins, new JLabel(new ImageIcon("images\\card2\\213.png"))), new Card(4, card214Coins, new JLabel(new ImageIcon("images\\card2\\214.png"))), new Card(4, card215Coins, new JLabel(new ImageIcon("images\\card2\\215.png")))};
        setCard2[0].SCoins[3] = new specialCoin(1, "blue");
        setCard2[1].SCoins[3] = new specialCoin(1, "blue");
        setCard2[2].SCoins[3] = new specialCoin(1, "blue");
        setCard2[3].SCoins[3] = new specialCoin(1, "blue");
        setCard2[4].SCoins[3] = new specialCoin(1, "blue");
        setCard2[5].SCoins[3] = new specialCoin(1, "blue");
        setCard2[6].SCoins[3] = new specialCoin(1, "blue");
        setCard2[7].SCoins[3] = new specialCoin(1, "blue");
        setCard2[8].SCoins[3] = new specialCoin(1, "blue");
        setCard2[9].SCoins[0] = new specialCoin(1, "green");
        setCard2[10].SCoins[0] = new specialCoin(1, "green");
        setCard2[11].SCoins[0] = new specialCoin(1, "green");
        setCard2[12].SCoins[0] = new specialCoin(1, "green");
        setCard2[13].SCoins[0] = new specialCoin(1, "green");
        setCard2[14].SCoins[0] = new specialCoin(1, "green");

        Coin[] card31Coins = new Coin[]{new Coin(3, "green"), null, new Coin(3, "black"), null, new Coin(4, "red")};
        Coin[] card32Coins = new Coin[]{new Coin(2, "green"), null, new Coin(3, "black"), null, new Coin(4, "red")};
        Coin[] card33Coins = new Coin[]{new Coin(2, "green"), null, new Coin(3, "black"), null, new Coin(3, "red")};
        Coin[] card34Coins = new Coin[]{new Coin(2, "green"), null, new Coin(2, "black"), null, new Coin(3, "red")};
        Coin[] card35Coins = new Coin[]{null, null, new Coin(2, "black"), new Coin(2, "blue"), new Coin(3, "red")};
        Coin[] card36Coins = new Coin[]{null, null, new Coin(2, "black"), new Coin(3, "blue"), new Coin(3, "red")};
        Coin[] card37Coins = new Coin[]{null, null, new Coin(3, "black"), new Coin(3, "blue"), new Coin(3, "red")};
        Coin[] card38Coins = new Coin[]{new Coin(1, "green"), null, new Coin(3, "black"), new Coin(3, "blue"), new Coin(3, "red")};
        Coin[] card39Coins = new Coin[]{new Coin(2, "green"), null, new Coin(2, "black"), new Coin(3, "blue"), new Coin(3, "red")};
        Coin[] card310Coins = new Coin[]{new Coin(2, "green"), null, new Coin(2, "black"), new Coin(3, "blue"), new Coin(1, "red")};
        Coin[] card311Coins = new Coin[]{new Coin(2, "green"), null, new Coin(2, "black"), new Coin(2, "blue"), new Coin(1, "red")};
        Coin[] card312Coins = new Coin[]{new Coin(3, "green"), null, new Coin(2, "black"), new Coin(1, "blue"), new Coin(1, "red")};
        Coin[] card313Coins = new Coin[]{new Coin(3, "green"), null, new Coin(2, "black"), new Coin(1, "blue"), new Coin(1, "red")};
        Coin[] card314Coins = new Coin[]{new Coin(2, "green"), null, new Coin(2, "black"), new Coin(1, "blue"), new Coin(2, "red")};
        Coin[] card315Coins = new Coin[]{new Coin(2, "green"), null, new Coin(3, "black"), new Coin(1, "blue"), new Coin(2, "red")};
        Card[] setCard3 = {new Card(3, card31Coins, new JLabel(new ImageIcon("images\\card3\\31.png"))), new Card(4, card32Coins, new JLabel(new ImageIcon("images\\card3\\32.png"))), new Card(5, card33Coins, new JLabel(new ImageIcon("images\\card3\\33.png"))), new Card(3, card34Coins, new JLabel(new ImageIcon("images\\card3\\34.png"))), new Card(3, card35Coins, new JLabel(new ImageIcon("images\\card3\\35.png"))), new Card(4, card36Coins, new JLabel(new ImageIcon("images\\card3\\36.png"))), new Card(5, card37Coins, new JLabel(new ImageIcon("images\\card3\\37.png"))), new Card(5, card38Coins, new JLabel(new ImageIcon("images\\card3\\38.png"))), new Card(5, card39Coins, new JLabel(new ImageIcon("images\\card3\\39.png"))), new Card(4, card310Coins, new JLabel(new ImageIcon("images\\card3\\310.png"))), new Card(4, card311Coins, new JLabel(new ImageIcon("images\\card3\\311.png"))), new Card(3, card312Coins, new JLabel(new ImageIcon("images\\card3\\312.png"))), new Card(3, card313Coins, new JLabel(new ImageIcon("images\\card3\\313.png"))), new Card(3, card314Coins, new JLabel(new ImageIcon("images\\card3\\314.png"))), new Card(3, card315Coins, new JLabel(new ImageIcon("images\\card3\\315.png")))};
        setCard3[0].SCoins[1] = new specialCoin(1, "white");
        setCard3[1].SCoins[1] = new specialCoin(1, "white");
        setCard3[2].SCoins[1] = new specialCoin(1, "white");
        setCard3[3].SCoins[0] = new specialCoin(1, "green");
        setCard3[4].SCoins[0] = new specialCoin(1, "green");
        setCard3[5].SCoins[0] = new specialCoin(1, "green");
        setCard3[6].SCoins[0] = new specialCoin(1, "green");
        setCard3[7].SCoins[1] = new specialCoin(1, "white");
        setCard3[8].SCoins[1] = new specialCoin(1, "white");
        setCard3[9].SCoins[1] = new specialCoin(1, "white");
        setCard3[10].SCoins[1] = new specialCoin(1, "white");
        setCard3[11].SCoins[1] = new specialCoin(1, "white");
        setCard3[12].SCoins[3] = new specialCoin(1, "blue");
        setCard3[13].SCoins[3] = new specialCoin(1, "blue");
        setCard3[14].SCoins[3] = new specialCoin(1, "blue");

        for (int t = 0; t < 4; t++) {
            set1panel.add(setCard1[t].cardImg);
            set2panel.add(setCard2[t].cardImg);
            set3panel.add(setCard3[t].cardImg);
        }

        grayPanel.add(set1panel);
        grayPanel.add(set2panel);
        grayPanel.add(set3panel);
        panel.add(grayPanel);
        return panel;
    }

    // Method to switch between panels
    private void switchPanel(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}