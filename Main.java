import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    //game main frame
    private final JFrame frame;
    //game main panels
    private final JPanel loginPanel;
    private final JPanel mainGamePanel;
    private final JPanel storePanel;
    private final JPanel centerPanel = new JPanel(new BorderLayout());
    private final JPanel topPanel = new JPanel(new FlowLayout());
    private final JPanel bottomPanel = new JPanel(new FlowLayout());
    JPanel sidePanel = new JPanel(new GridLayout(6, 3));
    private final JPanel tScorePanel = new JPanel();
    private final JPanel bScorePanel = new JPanel();
    private final JPanel tCoinPanel = new JPanel(new FlowLayout());
    private final JPanel bCoinPanel = new JPanel(new FlowLayout());
    private final JPanel tCardPanel = new JPanel(new FlowLayout());
    private final JPanel bCardPanel = new JPanel(new FlowLayout());
    //turn and players
    private int turn = 1;
    private final Players player1 = new Players();
    private final Players player2 = new Players();
    private int clickNum = 0;

    public void cardPrinter(JPanel panel1) {
        if (turn == 1) {
            for (int k = 0; k < player1.playerCard.length; k++) {
                if (player1.playerCard[k] != null) {
                    tCardPanel.add(player1.playerCard[k].cardImg);
                    tCardPanel.revalidate();
                    tCardPanel.repaint();
                    panel1.add(tCardPanel, BorderLayout.NORTH);
                }
            }
        } else {
            for (int k = 0; k < player2.playerCard.length; k++) {
                if (player2.playerCard[k] != null) {
                    bCardPanel.add(player2.playerCard[k].cardImg);
                    bCardPanel.revalidate();
                    bCardPanel.repaint();
                    panel1.add(bCardPanel, BorderLayout.SOUTH);
                }
            }
        }
    }
    public void coinPrinter(JPanel panel1, JPanel panel2, Players currentPlayer) {
        panel1.removeAll();
        String[] coinColors = {"green", "white", "black", "blue", "red"};

        for (int i = 0; i < currentPlayer.playerCoin.length; i++) {
            for (int k = 0; k < currentPlayer.playerCoin[i].num; k++) {
                panel1.add(new JLabel(new ImageIcon("images\\coins\\" + coinColors[i] + ".png")));
            }
        }

        panel1.revalidate();
        panel1.repaint();
        panel2.add(panel1);
    }
    public void scorePrinter(JPanel panel1, JPanel panel2) {
        Players currentPlayer = (turn == 1) ? player1 : player2;
        JLabel scoreLabel = new JLabel(String.valueOf(Math.max(0, currentPlayer.pScore)));
        panel1.removeAll();
        panel1.add(scoreLabel);
        panel1.revalidate();
        panel1.repaint();
        panel2.add(panel1);
    }

    public Main() {
        frame = new JFrame("Amusement Park");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close type
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        loginPanel = createLoginPanel();
        mainGamePanel = createMainGamePanel();
        storePanel = createStorePanel();

        frame.add(loginPanel);
        frame.setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel welcomePanel = new JPanel(new GridBagLayout());//layout system
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
        return welcomePanel;
    }

    public JPanel createMainGamePanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        topPanel.setBackground(Color.MAGENTA);
        topPanel.setPreferredSize(new Dimension(1920, 100));
        player1.playerCoin = new Coin[]{new Coin(0, "green"), new Coin(0, "white"), new Coin(0, "black"), new Coin(0, "blue"), new Coin(0, "red"), new Coin(0, "gold")};
        player1.playerSCoin = new specialCoin[]{new specialCoin(0, "green"), new specialCoin(0, "white"), new specialCoin(0, "black"), new specialCoin(0, "blue"), new specialCoin(0, "red")};

        bottomPanel.setBackground(Color.cyan);
        bottomPanel.setPreferredSize(new Dimension(1920, 100));
        player2.playerCoin = new Coin[]{new Coin(0, "green"), new Coin(0, "white"), new Coin(0, "black"), new Coin(0, "blue"), new Coin(0, "red"), new Coin(0, "gold")};
        player2.playerSCoin = new specialCoin[]{new specialCoin(0, "green"), new specialCoin(0, "white"), new specialCoin(0, "black"), new specialCoin(0, "blue"), new specialCoin(0, "red")};

        sidePanel.setBackground(Color.GREEN);
        sidePanel.setPreferredSize(new Dimension(320, 520));

        centerPanel.setBackground(Color.YELLOW);
        centerPanel.setPreferredSize(new Dimension(1600, 520));
        tCardPanel.setBackground(Color.YELLOW);
        bCardPanel.setBackground(Color.YELLOW);

        //slot machines
        Coin[] redCoins = {new Coin(4, "red")};
        Coin[] blueCoins = {new Coin(4, "blue")};
        Coin[] greenCoins = {new Coin(4, "green")};
        Coin[] whiteCoins = {new Coin(4, "white")};
        Coin[] blackCoins = {new Coin(4, "black")};

        JLabel labelBtn1 = new JLabel(String.valueOf(redCoins[0].num), SwingConstants.CENTER);
        JButton slotButton11 = new JButton("دو سکه قرمز");
        JButton slotButton12 = new JButton("یک سکه قرمز");
        JLabel labelBtn2 = new JLabel(String.valueOf(blueCoins[0].num), SwingConstants.CENTER);
        JButton slotButton21 = new JButton("دو سکه آبی");
        JButton slotButton22 = new JButton("یک سکه آبی");
        JLabel labelBtn3 = new JLabel(String.valueOf(greenCoins[0].num), SwingConstants.CENTER);
        JButton slotButton31 = new JButton("دو سکه سبز");
        JButton slotButton32 = new JButton("یک سکه سبز");
        JLabel labelBtn4 = new JLabel(String.valueOf(whiteCoins[0].num), SwingConstants.CENTER);
        JButton slotButton41 = new JButton("دو سکه سفید");
        JButton slotButton42 = new JButton("یک سکه سفید");
        JLabel labelBtn5 = new JLabel(String.valueOf(blackCoins[0].num), SwingConstants.CENTER);
        JButton slotButton51 = new JButton("دو سکه سیاه");
        JButton slotButton52 = new JButton("یک سکه سیاه");
        JButton enterButton = new JButton("فروشگاه");
        enterButton.addActionListener(e -> switchPanel(storePanel));

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
                        coinPrinter(tCoinPanel, topPanel,player1);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                        turn *= -1;
                    }
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
                        coinPrinter(bCoinPanel, bottomPanel,player2);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                        turn *= -1;
                    }
                    break;
            }
            labelBtn1.setText(String.valueOf(Math.max(0, redCoins[0].num)));
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
                        coinPrinter(tCoinPanel, topPanel,player1);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                        turn *= -1;
                    }
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
                        coinPrinter(bCoinPanel, bottomPanel,player2);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                        turn *= -1;
                    }
                    break;
            }
            labelBtn2.setText(String.valueOf(Math.max(0, blueCoins[0].num)));
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
                        coinPrinter(tCoinPanel, topPanel,player1);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                        turn *= -1;
                    }
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
                        coinPrinter(bCoinPanel, bottomPanel,player2);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                        turn *= -1;
                    }
                    break;
            }
            labelBtn3.setText(String.valueOf(Math.max(0, greenCoins[0].num)));
        });
        slotButton41.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (whiteCoins[0].num == 4 && clickNum < 1) {
                        player1.takeCoin(2, "white");
                        clickNum++;
                        whiteCoins[0].num -= 2;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                        coinPrinter(tCoinPanel, topPanel,player1);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                        turn *= -1;
                    }
                    break;
                case -1:
                    if (whiteCoins[0].num == 4 && clickNum < 1) {
                        player2.takeCoin(2, "white");
                        clickNum++;
                        whiteCoins[0].num -= 2;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                        coinPrinter(bCoinPanel, bottomPanel,player2);
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
                        turn *= -1;
                    }
                    break;
            }
            labelBtn4.setText(String.valueOf(Math.max(0, whiteCoins[0].num)));
        });
        slotButton51.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (blackCoins[0].num == 4 && clickNum < 1) {
                        player1.takeCoin(2, "black");
                        clickNum++;
                        blackCoins[0].num -= 2;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                        coinPrinter(tCoinPanel, topPanel,player1);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                        turn *= -1;
                    }
                    break;
                case -1:
                    if (blackCoins[0].num == 4 && clickNum < 1) {
                        player2.takeCoin(2, "black");
                        clickNum++;
                        blackCoins[0].num -= 2;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                        coinPrinter(bCoinPanel, bottomPanel,player2);
                    } else {
                        clickNum = 0;
                        slotButton22.setEnabled(true);
                        slotButton12.setEnabled(true);
                        slotButton32.setEnabled(true);
                        slotButton42.setEnabled(true);
                        slotButton52.setEnabled(true);
                        turn *= -1;
                    }
                    break;
            }
            labelBtn5.setText(String.valueOf(Math.max(0, blackCoins[0].num)));
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
                        coinPrinter(tCoinPanel, topPanel,player1);
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
                        turn *= -1;
                    }
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
                        coinPrinter(bCoinPanel, bottomPanel,player2);
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
                        turn *= -1;
                    }
                    break;
            }
            labelBtn1.setText(String.valueOf(Math.max(0, redCoins[0].num)));
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
                        coinPrinter(tCoinPanel, topPanel,player1);
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
                        turn *= -1;
                    }
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
                        coinPrinter(bCoinPanel, bottomPanel,player2);
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
                        turn *= -1;
                    }
                    break;
            }
            labelBtn2.setText(String.valueOf(Math.max(0, blueCoins[0].num)));
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
                        coinPrinter(tCoinPanel, topPanel,player1);
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
                        turn *= -1;
                    }
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
                        coinPrinter(bCoinPanel, bottomPanel,player2);
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
                        turn *= -1;
                    }
                    break;
            }
            labelBtn3.setText(String.valueOf(Math.max(0, greenCoins[0].num)));
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
                        coinPrinter(tCoinPanel, topPanel,player1);
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
                        turn *= -1;
                    }
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
                        coinPrinter(bCoinPanel, bottomPanel,player2);
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
                        turn *= -1;
                    }
                    break;
            }
            labelBtn4.setText(String.valueOf(Math.max(0, whiteCoins[0].num)));
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
                        coinPrinter(tCoinPanel, topPanel,player1);
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
                        turn *= -1;
                    }
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
                        coinPrinter(bCoinPanel, bottomPanel,player2);
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
                        turn *= -1;
                    }
                    break;
            }
            labelBtn5.setText(String.valueOf(Math.max(0, blackCoins[0].num)));
        });

        JLabel[] labels = {labelBtn1, labelBtn2, labelBtn3, labelBtn4, labelBtn5};
        JButton[][] buttons = {{slotButton11, slotButton12}, {slotButton21, slotButton22}, {slotButton31, slotButton32}, {slotButton41, slotButton42}, {slotButton51, slotButton52}};
        for (int i = 0; i < labels.length; i++) {
            sidePanel.add(labels[i]);
            for (int j = 0; j < buttons[i].length; j++) {
                sidePanel.add(buttons[i][j]);
            }
        }
        sidePanel.add(enterButton);


        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(sidePanel, BorderLayout.EAST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        return mainPanel;
    }

    private JPanel createStorePanel() {
        JPanel grayPanel = new JPanel();

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

        specialCoin[] prize1Coins = new specialCoin[]{new specialCoin(4, "green"), null, null, null, new specialCoin(4, "red")};
        specialCoin[] prize2Coins = new specialCoin[]{new specialCoin(5, "green"), null, null, null, new specialCoin(5, "red")};
        specialCoin[] prize3Coins = new specialCoin[]{new specialCoin(5, "green"), null, null, new specialCoin(4, "blue"), null};
        Card[] prizeList = {new Card(3, null, prize1Coins, new JLabel(new ImageIcon("images\\card prize\\prize1.png"))), new Card(4, null, prize2Coins, new JLabel(new ImageIcon("images\\card prize\\prize2.png"))), new Card(4, null, prize3Coins, new JLabel(new ImageIcon("images\\card prize\\prize3.png")))};

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
        specialCoin[] card11prize = new specialCoin[]{null, null, null, new specialCoin(1, "blue"), null};
        specialCoin[] card12prize = new specialCoin[]{null, null, null, new specialCoin(1, "blue"), null};
        specialCoin[] card13prize = new specialCoin[]{null, null, null, new specialCoin(1, "blue"), null};
        specialCoin[] card14prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card15prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card16prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card17prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card18prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card19prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card110prize = new specialCoin[]{null, null, null, null, new specialCoin(1, "red")};
        specialCoin[] card111prize = new specialCoin[]{null, null, null, null, new specialCoin(1, "red")};
        specialCoin[] card112prize = new specialCoin[]{null, null, null, null, new specialCoin(1, "red")};
        specialCoin[] card113prize = new specialCoin[]{null, null, null, null, new specialCoin(1, "red")};
        specialCoin[] card114prize = new specialCoin[]{null, null, null, null, new specialCoin(1, "red")};
        specialCoin[] card115prize = new specialCoin[]{null, null, null, null, new specialCoin(1, "red")};
        Card[] setCard1 = {new Card(1, card11Coins, card11prize, new JLabel(new ImageIcon("images\\card1\\11.png"))), new Card(1, card12Coins, card12prize, new JLabel(new ImageIcon("images\\card1\\12.png"))), new Card(1, card13Coins, card13prize, new JLabel(new ImageIcon("images\\card1\\13.png"))), new Card(0, card14Coins, card14prize, new JLabel(new ImageIcon("images\\card1\\14.png"))), new Card(0, card15Coins, card15prize, new JLabel(new ImageIcon("images\\card1\\15.png"))), new Card(0, card16Coins, card16prize, new JLabel(new ImageIcon("images\\card1\\16.png"))), new Card(1, card17Coins, card17prize, new JLabel(new ImageIcon("images\\card1\\17.png"))), new Card(1, card18Coins, card18prize, new JLabel(new ImageIcon("images\\card1\\18.png"))), new Card(0, card19Coins, card19prize, new JLabel(new ImageIcon("images\\card1\\19.png"))), new Card(0, card110Coins, card110prize, new JLabel(new ImageIcon("images\\card1\\110.png"))), new Card(1, card111Coins, card111prize, new JLabel(new ImageIcon("images\\card1\\111.png"))), new Card(1, card112Coins, card112prize, new JLabel(new ImageIcon("images\\card1\\112.png"))), new Card(1, card113Coins, card113prize, new JLabel(new ImageIcon("images\\card1\\113.png"))), new Card(0, card114Coins, card114prize, new JLabel(new ImageIcon("images\\card1\\114.png"))), new Card(1, card115Coins, card115prize, new JLabel(new ImageIcon("images\\card1\\115.png")))};

        Coin[] card21Coins = new Coin[]{null, new Coin(2, "white"), new Coin(2, "black"), new Coin(2, "blue"), null, null};
        Coin[] card22Coins = new Coin[]{null, new Coin(2, "white"), new Coin(2, "black"), new Coin(3, "blue"), null, null};
        Coin[] card23Coins = new Coin[]{null, new Coin(2, "white"), new Coin(3, "black"), new Coin(3, "blue"), null, null};
        Coin[] card24Coins = new Coin[]{null, new Coin(4, "white"), new Coin(2, "black"), new Coin(3, "blue"), null, null};
        Coin[] card25Coins = new Coin[]{null, new Coin(2, "white"), new Coin(2, "black"), new Coin(2, "blue"), new Coin(2, "red"), null};
        Coin[] card26Coins = new Coin[]{null, null, new Coin(3, "black"), new Coin(3, "blue"), new Coin(2, "red"), null};
        Coin[] card27Coins = new Coin[]{null, null, new Coin(2, "black"), new Coin(3, "blue"), new Coin(3, "red"), null};
        Coin[] card28Coins = new Coin[]{null, null, new Coin(2, "black"), new Coin(3, "blue"), new Coin(2, "red"), null};
        Coin[] card29Coins = new Coin[]{null, null, new Coin(2, "black"), new Coin(2, "blue"), new Coin(2, "red"), null};
        Coin[] card210Coins = new Coin[]{null, null, new Coin(3, "black"), new Coin(2, "blue"), new Coin(1, "red"), null};
        Coin[] card211Coins = new Coin[]{null, null, new Coin(3, "black"), new Coin(3, "blue"), new Coin(2, "red"), null};
        Coin[] card212Coins = new Coin[]{new Coin(1, "green"), null, new Coin(2, "black"), new Coin(3, "blue"), new Coin(2, "red"), null};
        Coin[] card213Coins = new Coin[]{new Coin(2, "green"), null, new Coin(2, "black"), null, new Coin(2, "red"), null};
        Coin[] card214Coins = new Coin[]{new Coin(1, "green"), null, new Coin(2, "black"), null, new Coin(3, "red"), null};
        Coin[] card215Coins = new Coin[]{new Coin(1, "green"), null, new Coin(3, "black"), null, new Coin(3, "red"), null};
        specialCoin[] card21prize = new specialCoin[]{null, null, null, new specialCoin(1, "blue"), null};
        specialCoin[] card22prize = new specialCoin[]{null, null, null, new specialCoin(1, "blue"), null};
        specialCoin[] card23prize = new specialCoin[]{null, null, null, new specialCoin(1, "blue"), null};
        specialCoin[] card24prize = new specialCoin[]{null, null, null, new specialCoin(1, "blue"), null};
        specialCoin[] card25prize = new specialCoin[]{null, null, null, new specialCoin(1, "blue"), null};
        specialCoin[] card26prize = new specialCoin[]{null, null, null, new specialCoin(1, "blue"), null};
        specialCoin[] card27prize = new specialCoin[]{null, null, null, new specialCoin(1, "blue"), null};
        specialCoin[] card28prize = new specialCoin[]{null, null, null, new specialCoin(1, "blue"), null};
        specialCoin[] card29prize = new specialCoin[]{null, null, null, new specialCoin(1, "blue"), null};
        specialCoin[] card210prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card211prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card212prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card213prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card214prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card215prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        Card[] setCard2 = {new Card(2, card21Coins, card21prize, new JLabel(new ImageIcon("images\\card2\\21.png"))), new Card(2, card22Coins, card22prize, new JLabel(new ImageIcon("images\\card2\\22.png"))), new Card(2, card23Coins, card23prize, new JLabel(new ImageIcon("images\\card2\\23.png"))), new Card(2, card24Coins, card24prize, new JLabel(new ImageIcon("images\\card2\\24.png"))), new Card(2, card25Coins, card25prize, new JLabel(new ImageIcon("images\\card2\\25.png"))), new Card(3, card26Coins, card26prize, new JLabel(new ImageIcon("images\\card2\\26.png"))), new Card(3, card27Coins, card27prize, new JLabel(new ImageIcon("images\\card2\\27.png"))), new Card(3, card28Coins, card28prize, new JLabel(new ImageIcon("images\\card2\\28.png"))), new Card(3, card29Coins, card29prize, new JLabel(new ImageIcon("images\\card2\\29.png"))), new Card(3, card210Coins, card210prize, new JLabel(new ImageIcon("images\\card2\\210.png"))), new Card(4, card211Coins, card211prize, new JLabel(new ImageIcon("images\\card2\\211.png"))), new Card(4, card212Coins, card212prize, new JLabel(new ImageIcon("images\\card2\\212.png"))), new Card(4, card213Coins, card213prize, new JLabel(new ImageIcon("images\\card2\\213.png"))), new Card(4, card214Coins, card214prize, new JLabel(new ImageIcon("images\\card2\\214.png"))), new Card(4, card215Coins, card215prize, new JLabel(new ImageIcon("images\\card2\\215.png")))};

        Coin[] card31Coins = new Coin[]{new Coin(3, "green"), null, new Coin(3, "black"), null, new Coin(4, "red"), null};
        Coin[] card32Coins = new Coin[]{new Coin(2, "green"), null, new Coin(3, "black"), null, new Coin(4, "red"), null};
        Coin[] card33Coins = new Coin[]{new Coin(2, "green"), null, new Coin(3, "black"), null, new Coin(3, "red"), null};
        Coin[] card34Coins = new Coin[]{new Coin(2, "green"), null, new Coin(2, "black"), null, new Coin(3, "red"), null};
        Coin[] card35Coins = new Coin[]{null, null, new Coin(2, "black"), new Coin(2, "blue"), new Coin(3, "red"), null};
        Coin[] card36Coins = new Coin[]{null, null, new Coin(2, "black"), new Coin(3, "blue"), new Coin(3, "red"), null};
        Coin[] card37Coins = new Coin[]{null, null, new Coin(3, "black"), new Coin(3, "blue"), new Coin(3, "red"), null};
        Coin[] card38Coins = new Coin[]{new Coin(1, "green"), null, new Coin(3, "black"), new Coin(3, "blue"), new Coin(3, "red"), null};
        Coin[] card39Coins = new Coin[]{new Coin(2, "green"), null, new Coin(2, "black"), new Coin(3, "blue"), new Coin(3, "red"), null};
        Coin[] card310Coins = new Coin[]{new Coin(2, "green"), null, new Coin(2, "black"), new Coin(3, "blue"), new Coin(1, "red"), null};
        Coin[] card311Coins = new Coin[]{new Coin(2, "green"), null, new Coin(2, "black"), new Coin(2, "blue"), new Coin(1, "red"), null};
        Coin[] card312Coins = new Coin[]{new Coin(3, "green"), null, new Coin(2, "black"), new Coin(1, "blue"), new Coin(1, "red"), null};
        Coin[] card313Coins = new Coin[]{new Coin(3, "green"), null, new Coin(2, "black"), new Coin(1, "blue"), new Coin(1, "red"), null};
        Coin[] card314Coins = new Coin[]{new Coin(2, "green"), null, new Coin(2, "black"), new Coin(1, "blue"), new Coin(2, "red"), null};
        Coin[] card315Coins = new Coin[]{new Coin(2, "green"), null, new Coin(3, "black"), new Coin(1, "blue"), new Coin(2, "red"), null};
        specialCoin[] card31prize = new specialCoin[]{new specialCoin(1, "white"), null, null, null, null};
        specialCoin[] card32prize = new specialCoin[]{new specialCoin(1, "white"), null, null, null, null};
        specialCoin[] card33prize = new specialCoin[]{new specialCoin(1, "white"), null, null, null, null};
        specialCoin[] card34prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card35prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card36prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card37prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card38prize = new specialCoin[]{new specialCoin(1, "white"), null, null, null, null};
        specialCoin[] card39prize = new specialCoin[]{new specialCoin(1, "white"), null, null, null, null};
        specialCoin[] card310prize = new specialCoin[]{new specialCoin(1, "white"), null, null, null, null};
        specialCoin[] card311prize = new specialCoin[]{new specialCoin(1, "white"), null, null, null, null};
        specialCoin[] card312prize = new specialCoin[]{new specialCoin(1, "white"), null, null, null, null};
        specialCoin[] card313prize = new specialCoin[]{null, null, null, new specialCoin(1, "blue"), null};
        specialCoin[] card314prize = new specialCoin[]{null, null, null, new specialCoin(1, "blue"), null};
        specialCoin[] card315prize = new specialCoin[]{null, null, null, new specialCoin(1, "blue"), null};
        Card[] setCard3 = {new Card(3, card31Coins, card31prize, new JLabel(new ImageIcon("images\\card3\\31.png"))), new Card(4, card32Coins, card32prize, new JLabel(new ImageIcon("images\\card3\\32.png"))), new Card(5, card33Coins, card33prize, new JLabel(new ImageIcon("images\\card3\\33.png"))), new Card(3, card34Coins, card34prize, new JLabel(new ImageIcon("images\\card3\\34.png"))), new Card(3, card35Coins, card35prize, new JLabel(new ImageIcon("images\\card3\\35.png"))), new Card(4, card36Coins, card36prize, new JLabel(new ImageIcon("images\\card3\\36.png"))), new Card(5, card37Coins, card37prize, new JLabel(new ImageIcon("images\\card3\\37.png"))), new Card(5, card38Coins, card38prize, new JLabel(new ImageIcon("images\\card3\\38.png"))), new Card(5, card39Coins, card39prize, new JLabel(new ImageIcon("images\\card3\\39.png"))), new Card(4, card310Coins, card310prize, new JLabel(new ImageIcon("images\\card3\\310.png"))), new Card(4, card311Coins, card311prize, new JLabel(new ImageIcon("images\\card3\\311.png"))), new Card(3, card312Coins, card312prize, new JLabel(new ImageIcon("images\\card3\\312.png"))), new Card(3, card313Coins, card313prize, new JLabel(new ImageIcon("images\\card3\\313.png"))), new Card(3, card314Coins, card314prize, new JLabel(new ImageIcon("images\\card3\\314.png"))), new Card(3, card315Coins, card315prize, new JLabel(new ImageIcon("images\\card3\\315.png")))};

        for (Card card : prizeList) {
            if (card.getAvailability()) {
                prizePanel.add(card.cardImg);
            }
        }
        int availableCount = 0;
        for (Card card : setCard1) {
            availableCount++;
            if (card.getAvailability()) {
                set1panel.add(card.cardImg);
                if (availableCount == 4) {
                    break;
                }
            } else {
                availableCount--;
            }
        }
        availableCount = 0;
        for (Card card : setCard2) {
            availableCount++;
            if (card.getAvailability()) {
                set2panel.add(card.cardImg);
                if (availableCount == 4) {
                    break;
                }
            } else {
                availableCount--;
            }
        }
        availableCount = 0;
        for (Card card : setCard3) {
            availableCount++;
            if (card.getAvailability()) {
                set3panel.add(card.cardImg);
                if (availableCount == 4) {
                    break;
                }
            } else {
                availableCount--;
            }
        }

        for (Card card : prizeList) {
            card.cardImg.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (turn == 1) {
                        player1.buyPrize(card);
                        scorePrinter(tScorePanel, topPanel);

                    } else {
                        player2.buyPrize(card);
                        scorePrinter(bScorePanel, bottomPanel);
                    }
                    cardPrinter(centerPanel);
                }
            });
        }
        for (Card card : setCard1) {
            card.cardImg.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (turn == 1) {
                        player1.buy(card);
                        int availableCount = 0;
                        for (Card card : setCard1) {
                            availableCount++;
                            if (card.getAvailability()) {
                                set1panel.add(card.cardImg);
                                if (availableCount == 4) {
                                    break;
                                }
                            } else {
                                availableCount--;
                            }
                        }
                        scorePrinter(tScorePanel, topPanel);

                    } else {
                        player2.buy(card);
                        int availableCount = 0;
                        for (Card card : setCard1) {
                            availableCount++;
                            if (card.getAvailability()) {
                                set1panel.add(card.cardImg);
                                if (availableCount == 4) {
                                    break;
                                }
                            } else {
                                availableCount--;
                            }
                        }
                        scorePrinter(bScorePanel, bottomPanel);
                    }
                    cardPrinter(centerPanel);
                }
            });
        }
        for (Card card : setCard2) {
            card.cardImg.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (turn == 1) {
                        player1.buy(card);
                        int availableCount = 0;
                        for (Card card : setCard2) {
                            availableCount++;
                            if (card.getAvailability()) {
                                set2panel.add(card.cardImg);
                                if (availableCount == 4) {
                                    break;
                                }
                            } else {
                                availableCount--;
                            }
                        }
                        scorePrinter(tScorePanel, topPanel);
                    } else {
                        player2.buy(card);
                        int availableCount = 0;
                        for (Card card : setCard2) {
                            availableCount++;
                            if (card.getAvailability()) {
                                set2panel.add(card.cardImg);
                                if (availableCount == 4) {
                                    break;
                                }
                            } else {
                                availableCount--;
                            }
                        }
                        scorePrinter(bScorePanel, bottomPanel);
                    }
                    cardPrinter(centerPanel);
                }
            });
        }
        for (Card card : setCard3) {
            card.cardImg.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (turn == 1) {
                        player1.buy(card);
                        int availableCount = 0;
                        for (Card card : setCard3) {
                            availableCount++;
                            if (card.getAvailability()) {
                                set3panel.add(card.cardImg);
                                if (availableCount == 4) {
                                    break;
                                }
                            } else {
                                availableCount--;
                            }
                        }
                        scorePrinter(tScorePanel, topPanel);
                    } else {
                        player2.buy(card);
                        int availableCount = 0;
                        for (Card card : setCard3) {
                            availableCount++;
                            if (card.getAvailability()) {
                                set3panel.add(card.cardImg);
                                if (availableCount == 4) {
                                    break;
                                }
                            } else {
                                availableCount--;
                            }
                        }
                        scorePrinter(bScorePanel, bottomPanel);
                    }
                    cardPrinter(centerPanel);
                }
            });
        }


        prizePanel.add(backward);
        grayPanel.add(prizePanel);
        grayPanel.add(set1panel);
        grayPanel.add(set2panel);
        grayPanel.add(set3panel);
        return grayPanel;
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