//Swing needs

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Main extends JFrame {
    //game Font
    Font font;

    {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("Vazir.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Font bigFont = font.deriveFont(40f);
    Font normalFont = font.deriveFont(20f);
    //Frames and panels
    private final JPanel mainPanel;
    private final JPanel loginPanel;
    private final JScrollPane guidePanel;
    private final JPanel mainGamePanel;
    private final JPanel storePanel;
    private final JPanel coinPanel;
    private final JPanel reservePanel;
    private final JPanel winnerPanel;
    private final JPanel centerPanel = new JPanel(new BorderLayout());
    private final JPanel topPanel = new JPanel(new FlowLayout());
    private final JPanel bottomPanel = new JPanel(new FlowLayout());
    private final JPanel sidePanel = new JPanel(new GridBagLayout());
    private final JPanel tScorePanel = new JPanel();
    private final JPanel bScorePanel = new JPanel();
    private final JPanel tCoinPanel = new JPanel(new FlowLayout());
    private final JPanel bCoinPanel = new JPanel(new FlowLayout());
    private final JPanel tSCoinPanel = new JPanel(new FlowLayout());
    private final JPanel bSCoinPanel = new JPanel(new FlowLayout());
    private final JPanel tCardPanel = new JPanel(new FlowLayout());
    private final JPanel bCardPanel = new JPanel(new FlowLayout());
    private final JPanel player1res = new JPanel(new FlowLayout());
    private final JPanel player2res = new JPanel(new FlowLayout());
    //frame background
    private final Image bgImage = Toolkit.getDefaultToolkit().createImage("images\\background.png");
    //turn system and players
    private final Players player1 = new Players();
    private final Players player2 = new Players();
    private int turn = 1;
    private int clickNum = 0;
    Random rand = new Random();//shuffle cards list
    //coin slots
    private final Coin[] slotCoins = {new Coin(4, "green"), new Coin(4, "white"), new Coin(4, "black"), new Coin(4, "blue"), new Coin(4, "red"), new Coin(5, "gold")};
    //slots label & button
    JLabel labelBtn1 = new JLabel("تعداد سکه های قرمز : ".concat(String.valueOf(slotCoins[4].getNum())), SwingConstants.CENTER);
    JLabel labelBtn2 = new JLabel("تعداد سکه های آبی : ".concat(String.valueOf(slotCoins[3].getNum())), SwingConstants.CENTER);
    JLabel labelBtn3 = new JLabel("تعداد سکه های سبز : ".concat(String.valueOf(slotCoins[0].getNum())), SwingConstants.CENTER);
    JLabel labelBtn4 = new JLabel("تعداد سکه های سفید : ".concat(String.valueOf(slotCoins[1].getNum())), SwingConstants.CENTER);
    JLabel labelBtn5 = new JLabel("تعداد سکه های سیاه : ".concat(String.valueOf(slotCoins[2].getNum())), SwingConstants.CENTER);
    JButton slotButton11 = new JButton("دو قرمز");
    JButton slotButton12 = new JButton("یک قرمز");
    JButton slotButton21 = new JButton("دو آبی");
    JButton slotButton22 = new JButton("یک آبی");
    JButton slotButton31 = new JButton("دو سبز");
    JButton slotButton32 = new JButton("یک سبز");
    JButton slotButton41 = new JButton("دو سفید");
    JButton slotButton42 = new JButton("یک سفید");
    JButton slotButton51 = new JButton("دو سیاه");
    JButton slotButton52 = new JButton("یک سیاه");

    //show players cards
    public void cardPrinter() {
        for (int k = 0; k < player1.playerCard.length; k++) {
            if (player1.playerCard[k] != null) {
                tCardPanel.add(player1.playerCard[k].getCardImg());
                tCardPanel.revalidate();
                tCardPanel.repaint();
            }
        }
        for (int k = 0; k < player2.playerCard.length; k++) {
            if (player2.playerCard[k] != null) {
                bCardPanel.add(player2.playerCard[k].getCardImg());
                bCardPanel.revalidate();
                bCardPanel.repaint();
            }
        }
    }

    //show players coins
    public void coinPrinter() {
        tCoinPanel.removeAll();
        bCoinPanel.removeAll();
        String[] coinColors = {"green", "white", "black", "blue", "red", "gold"};

        for (int i = 0; i < player1.playerCoin.length; i++) {
            for (int k = 0; k < player1.playerCoin[i].getNum(); k++) {
                JLabel coinLabel = new JLabel(new ImageIcon("images\\coins\\" + coinColors[i] + ".png"));
                int finalI = i;
                coinLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (player1.coinCounter() > 10) {
                            String coinColor = coinColors[finalI];
                            switch (coinColor) {
                                case "green" -> {
                                    slotCoins[0].num++;
                                    labelBtn3.setText("تعداد سکه های سبز : ".concat(String.valueOf(slotCoins[0].getNum())));
                                }
                                case "white" -> {
                                    slotCoins[1].num++;
                                    labelBtn4.setText("تعداد سکه های سفید : ".concat(String.valueOf(slotCoins[1].getNum())));
                                }
                                case "black" -> {
                                    slotCoins[2].num++;
                                    labelBtn5.setText("تعداد سکه های سیاه : ".concat(String.valueOf(slotCoins[2].getNum())));
                                }
                                case "blue" -> {
                                    slotCoins[3].num++;
                                    labelBtn2.setText("تعداد سکه های آبی : ".concat(String.valueOf(slotCoins[3].getNum())));
                                }
                                case "red" -> {
                                    slotCoins[4].num++;
                                    labelBtn1.setText("تعداد سکه های قرمز : ".concat(String.valueOf(slotCoins[4].getNum())));
                                }
                            }
                            player1.playerCoin[finalI].num--;
                            coinPrinter();
                        }
                    }
                });
                tCoinPanel.add(coinLabel);
            }
        }
        for (int i = 0; i < player2.playerCoin.length; i++) {
            for (int k = 0; k < player2.playerCoin[i].getNum(); k++) {
                JLabel coinLabel = new JLabel(new ImageIcon("images\\coins\\" + coinColors[i] + ".png"));
                int finalI = i;
                coinLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (player2.coinCounter() > 10) {
                            String coinColor = coinColors[finalI];
                            switch (coinColor) {
                                case "green" -> {
                                    slotCoins[0].num++;
                                    labelBtn3.setText(String.valueOf(Math.max(0, slotCoins[0].getNum())));
                                }
                                case "white" -> {
                                    slotCoins[1].num++;
                                    labelBtn4.setText(String.valueOf(Math.max(0, slotCoins[1].getNum())));
                                }
                                case "black" -> {
                                    slotCoins[2].num++;
                                    labelBtn5.setText(String.valueOf(Math.max(0, slotCoins[2].getNum())));
                                }
                                case "blue" -> {
                                    slotCoins[3].num++;
                                    labelBtn2.setText(String.valueOf(Math.max(0, slotCoins[3].getNum())));
                                }
                                case "red" -> {
                                    slotCoins[4].num++;
                                    labelBtn1.setText(String.valueOf(Math.max(0, slotCoins[4].getNum())));
                                }
                            }
                            player2.playerCoin[finalI].num--;
                            coinPrinter();
                        }
                    }
                });
                bCoinPanel.add(coinLabel);
            }
        }

        tCoinPanel.revalidate();
        tCoinPanel.repaint();
        topPanel.add(tCoinPanel);
        bCoinPanel.revalidate();
        bCoinPanel.repaint();
        bottomPanel.add(bCoinPanel);
    }

    //show players special coins
    public void sCoinPrinter() {
        tSCoinPanel.removeAll();
        bSCoinPanel.removeAll();
        String[] sCoinColors = {"green", "white", "black", "blue", "red"};

        for (int i = 0; i < player1.playerSCoin.length; i++) {
            for (int k = 0; k < player1.playerSCoin[i].getsNum(); k++) {
                tSCoinPanel.add(new JLabel(new ImageIcon("images\\coins\\" + sCoinColors[i] + ".png")));
            }
        }
        for (int i = 0; i < player2.playerSCoin.length; i++) {
            for (int k = 0; k < player2.playerSCoin[i].getsNum(); k++) {
                bSCoinPanel.add(new JLabel(new ImageIcon("images\\coins\\" + sCoinColors[i] + ".png")));
            }
        }

        tSCoinPanel.revalidate();
        tSCoinPanel.repaint();
        topPanel.add(tSCoinPanel);
        bSCoinPanel.revalidate();
        bSCoinPanel.repaint();
        bottomPanel.add(bSCoinPanel);
    }

    //show players score
    public void scorePrinter(Players player) {
        if (player == player1) {
            JLabel scoreLabel1 = new JLabel(String.valueOf(Math.max(0, player1.pScore)));
            tScorePanel.removeAll();
            tScorePanel.add(scoreLabel1);
            tScorePanel.revalidate();
            tScorePanel.repaint();
            topPanel.add(tScorePanel);
        } else {
            JLabel scoreLabel2 = new JLabel(String.valueOf(Math.max(0, player2.pScore)));
            bScorePanel.removeAll();
            bScorePanel.add(scoreLabel2);
            bScorePanel.revalidate();
            bScorePanel.repaint();
            bottomPanel.add(bScorePanel);
        }
    }

    //one reserve for each turn
    public boolean reserveDone = false;

    //change game turn
    public void changeTurn() {
        turn *= -1;
        for (Coin coin : slotCoins) {
            if (coin.getNum() > 4) {
                coin.setNum(4);
            }
            labelBtn1.setText("تعداد سکه های قرمز : ".concat(String.valueOf(slotCoins[4].getNum())));
            labelBtn2.setText("تعداد سکه های آبی : ".concat(String.valueOf(slotCoins[3].getNum())));
            labelBtn3.setText("تعداد سکه های سبز : ".concat(String.valueOf(slotCoins[0].getNum())));
            labelBtn4.setText("تعداد سکه های سفید : ".concat(String.valueOf(slotCoins[1].getNum())));
            labelBtn5.setText("تعداد سکه های سیاه : ".concat(String.valueOf(slotCoins[2].getNum())));
        }
        slotButton11.setEnabled(true);
        slotButton12.setEnabled(true);
        slotButton21.setEnabled(true);
        slotButton22.setEnabled(true);
        slotButton31.setEnabled(true);
        slotButton32.setEnabled(true);
        slotButton41.setEnabled(true);
        slotButton42.setEnabled(true);
        slotButton51.setEnabled(true);
        slotButton52.setEnabled(true);
        reserveDone = false;
        clickNum = 0;
        if (turn == 1) {
            if ((player1.pScore >= 15 && player2.pScore <= 15) || (player1.pScore <= 15 && player2.pScore >= 15)) {
                switchPanels(winnerPanel);
            }
        }
    }

    //show cards in shop panel
    public void updateAvailableCards(Card[] cards, JPanel panel) {
        panel.removeAll();
        int availableCount = 0;
        for (Card card : cards) {
            if (card.getAvailability()) {
                panel.add(card.getCardImg());
                availableCount++;
                if (availableCount == 4) {
                    break;
                }
            }
        }
        panel.revalidate();
        panel.repaint();
    }

    //show player reserve cards
    public void updateReserveCards(Card[] cards, JPanel panel) {
        panel.removeAll();
        for (Card card : cards) {
            if (card != null) {
                panel.add(card.getCardImg());
                card.getCardImg().addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (panel == player1res) {
                            player1.buyReserve(card, player1, slotCoins);
                            scorePrinter(player1);
                        } else {
                            player2.buyReserve(card, player2, slotCoins);
                            scorePrinter(player2);
                        }
                        coinPrinter();
                        sCoinPrinter();
                        cardPrinter();
                    }
                });
            }
        }
        panel.revalidate();
        panel.repaint();
    }

    //buy or reserve by click on image
    private void addCardMouseListener(Card card, JPanel panelToUpdate, Card[] cards) {
        card.getCardImg().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Players currentPlayer = (turn == 1) ? player1 : player2;
                if (SwingUtilities.isLeftMouseButton(e)) {
                    currentPlayer.buy(card, currentPlayer, slotCoins);
                } else if (SwingUtilities.isRightMouseButton(e) && !reserveDone) {
                    currentPlayer.reserve(card, currentPlayer, slotCoins);
                    reserveDone = true;
                    if (currentPlayer == player1) {
                        updateReserveCards(currentPlayer.reserveCard, player1res);
                    } else {
                        updateReserveCards(currentPlayer.reserveCard, player2res);
                    }
                }
                coinPrinter();
                sCoinPrinter();
                scorePrinter(currentPlayer);
                cardPrinter();
                updateAvailableCards(cards, panelToUpdate);
            }
        });
    }
    private void addPrizeCardMouseListener(Card card, JPanel panelToUpdate, Card[] cards) {
        card.getCardImg().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Players currentPlayer = (turn == 1) ? player1 : player2;
                currentPlayer.buyPrize(card, currentPlayer);
                scorePrinter(currentPlayer);
                cardPrinter();
                updateAvailableCards(cards, panelToUpdate);
            }
        });
    }

    public Main() {
        mainPanel = new JPanel(new BorderLayout());

        loginPanel = createLoginPanel();
        guidePanel = createGuidePanel();
        mainGamePanel = createMainGamePanel();
        storePanel = createStorePanel();
        coinPanel = createCoinPanel();
        reservePanel = createReservePanel();
        winnerPanel = createWinnerPanel();

        mainPanel.add(loginPanel, BorderLayout.CENTER);

        add(mainPanel);
        setTitle("Amusement Park");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private JPanel createLoginPanel() {
        JPanel welcomePanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 40, 0);

        JLabel titleLabel = new JLabel("به شهربازی خوش آمدید!", SwingConstants.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setFont(bigFont);
        titleLabel.setForeground(Color.RED);
        titleLabel.setBackground(Color.BLACK);
        welcomePanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        JButton startButton = new JButton(new ImageIcon("images\\btns\\btn1.png"));
        startButton.setOpaque(false);
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        startButton.addActionListener(e -> switchPanels(mainGamePanel));
        welcomePanel.add(startButton, gbc);

        gbc.gridy = 2;
        JButton guideButton = new JButton(new ImageIcon("images\\btns\\btn2.png"));
        guideButton.setOpaque(false);
        guideButton.setFocusPainted(false);
        guideButton.setBorderPainted(false);
        guideButton.setContentAreaFilled(false);
        guideButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        guideButton.addActionListener(e -> switchGuide(guidePanel));
        welcomePanel.add(guideButton, gbc);

        gbc.gridy = 3;
        JLabel developer = new JLabel("طراحی و توسعه : امیرعلی فدیشه ای");
        developer.setFont(normalFont);
        developer.setForeground(Color.white);
        welcomePanel.add(developer, gbc);

        return welcomePanel;
    }

    private JScrollPane createGuidePanel() {
        JPanel gPagePanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel g1 = new JLabel(new ImageIcon("images\\guide\\g1.png"));
        gPagePanel.add(g1, gbc);

        gbc.gridy = 1;
        JLabel g2 = new JLabel(new ImageIcon("images\\guide\\g2.png"));
        gPagePanel.add(g2, gbc);

        gbc.gridy = 2;
        JLabel g3 = new JLabel(new ImageIcon("images\\guide\\g3.png"));
        gPagePanel.add(g3, gbc);

        gbc.gridy = 3;
        JLabel g4 = new JLabel(new ImageIcon("images\\guide\\g4.png"));
        gPagePanel.add(g4, gbc);

        gbc.gridy = 4;
        JButton returnMain = new JButton(new ImageIcon("images\\btns\\btn3.png"));
        returnMain.addActionListener(e -> switchPanels(loginPanel));
        returnMain.setOpaque(false);
        returnMain.setFocusPainted(false);
        returnMain.setBorderPainted(false);
        returnMain.setContentAreaFilled(false);
        returnMain.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        gPagePanel.add(returnMain, gbc);

        return new JScrollPane(gPagePanel);
    }

    private JPanel createMainGamePanel() {
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics c) {
                super.paintComponent(c);
                c.drawImage(bgImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        topPanel.setPreferredSize(new Dimension(1920, 100));
        topPanel.setOpaque(false);
        topPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        JLabel p1Name = new JLabel("Player 1 :");
        p1Name.setFont(normalFont);
        p1Name.setForeground(Color.white);
        topPanel.add(p1Name);

        player1.playerCoin = new Coin[]{new Coin(0, "green"), new Coin(0, "white"), new Coin(0, "black"), new Coin(0, "blue"), new Coin(0, "red"), new Coin(0, "gold")};
        player1.playerSCoin = new specialCoin[]{new specialCoin(0, "green"), new specialCoin(0, "white"), new specialCoin(0, "black"), new specialCoin(0, "blue"), new specialCoin(0, "red")};

        tCardPanel.setOpaque(false);

        bottomPanel.setPreferredSize(new Dimension(1920, 100));
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        JLabel p2Name = new JLabel("Player 2 :");
        p2Name.setFont(normalFont);
        p2Name.setForeground(Color.white);
        bottomPanel.add(p2Name);

        player2.playerCoin = new Coin[]{new Coin(0, "green"), new Coin(0, "white"), new Coin(0, "black"), new Coin(0, "blue"), new Coin(0, "red"), new Coin(0, "gold")};
        player2.playerSCoin = new specialCoin[]{new specialCoin(0, "green"), new specialCoin(0, "white"), new specialCoin(0, "black"), new specialCoin(0, "blue"), new specialCoin(0, "red")};

        bCardPanel.setOpaque(false);

        sidePanel.setPreferredSize(new Dimension(300, 880));
        sidePanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 40, 40);
        JButton shopButton = new JButton(new ImageIcon("images\\btns\\btn4.png"));
        shopButton.setOpaque(false);
        shopButton.setFocusPainted(false);
        shopButton.setBorderPainted(false);
        shopButton.setContentAreaFilled(false);
        shopButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        shopButton.addActionListener(e -> switchPanels(storePanel));
        sidePanel.add(shopButton, gbc);

        gbc.gridy = 1;
        JButton coinButton = new JButton(new ImageIcon("images\\btns\\btn5.png"));
        coinButton.setOpaque(false);
        coinButton.setFocusPainted(false);
        coinButton.setBorderPainted(false);
        coinButton.setContentAreaFilled(false);
        coinButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        coinButton.addActionListener(e -> switchPanels(coinPanel));
        sidePanel.add(coinButton, gbc);

        gbc.gridy = 2;
        JButton reserveButton = new JButton(new ImageIcon("images\\btns\\btn6.png"));
        reserveButton.setOpaque(false);
        reserveButton.setFocusPainted(false);
        reserveButton.setBorderPainted(false);
        reserveButton.setContentAreaFilled(false);
        reserveButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        reserveButton.addActionListener(e -> switchPanels(reservePanel));
        sidePanel.add(reserveButton, gbc);

        gbc.gridy = 3;
        JButton turnButton = new JButton(new ImageIcon("images\\btns\\btn7.png"));
        turnButton.setOpaque(false);
        turnButton.setFocusPainted(false);
        turnButton.setBorderPainted(false);
        turnButton.setContentAreaFilled(false);
        turnButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        turnButton.addActionListener(e -> changeTurn());
        sidePanel.add(turnButton, gbc);

        centerPanel.setPreferredSize(new Dimension(1620, 880));
        centerPanel.setOpaque(false);
        centerPanel.add(tCardPanel, BorderLayout.NORTH);
        centerPanel.add(bCardPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(sidePanel, BorderLayout.EAST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        return mainPanel;
    }

    private JPanel createStorePanel() {
        JPanel grayPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics b) {
                super.paintComponent(b);
                b.drawImage(bgImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setOpaque(false);
        JPanel prizePanel = new JPanel(new GridLayout(1, 3, 20, 0));
        prizePanel.setOpaque(false);
        JButton backward = new JButton(new ImageIcon("images\\btns\\btn3.png"));
        backward.setOpaque(false);
        backward.setFocusPainted(false);
        backward.setBorderPainted(false);
        backward.setContentAreaFilled(false);
        backward.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        backward.addActionListener(e -> switchPanels(mainGamePanel));
        topPanel.add(prizePanel);
        topPanel.add(backward);
        grayPanel.add(topPanel, gbc);

        gbc.gridy = 1;
        JPanel set1panel = new JPanel(new GridLayout(1, 4, 20, 0));
        set1panel.setOpaque(false);
        grayPanel.add(set1panel, gbc);

        gbc.gridy = 2;
        JPanel set2panel = new JPanel(new GridLayout(1, 4, 20, 0));
        set2panel.setOpaque(false);
        grayPanel.add(set2panel, gbc);

        gbc.gridy = 3;
        JPanel set3panel = new JPanel(new GridLayout(1, 4, 20, 0));
        set3panel.setOpaque(false);
        grayPanel.add(set3panel, gbc);

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
        specialCoin[] card17prize = new specialCoin[]{new specialCoin(2, "green"), null, null, null, null};
        specialCoin[] card18prize = new specialCoin[]{new specialCoin(2, "green"), null, null, null, null};
        specialCoin[] card19prize = new specialCoin[]{new specialCoin(2, "green"), null, null, null, null};
        specialCoin[] card110prize = new specialCoin[]{null, null, null, null, new specialCoin(2, "red")};
        specialCoin[] card111prize = new specialCoin[]{null, null, null, null, new specialCoin(2, "red")};
        specialCoin[] card112prize = new specialCoin[]{null, null, null, null, new specialCoin(1, "red")};
        specialCoin[] card113prize = new specialCoin[]{null, null, null, null, new specialCoin(2, "red")};
        specialCoin[] card114prize = new specialCoin[]{null, null, null, null, new specialCoin(2, "red")};
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
        specialCoin[] card24prize = new specialCoin[]{null, null, null, new specialCoin(2, "blue"), null};
        specialCoin[] card25prize = new specialCoin[]{null, null, null, new specialCoin(2, "blue"), null};
        specialCoin[] card26prize = new specialCoin[]{null, null, null, new specialCoin(1, "blue"), null};
        specialCoin[] card27prize = new specialCoin[]{null, null, null, new specialCoin(2, "blue"), null};
        specialCoin[] card28prize = new specialCoin[]{null, null, null, new specialCoin(1, "blue"), null};
        specialCoin[] card29prize = new specialCoin[]{null, null, null, new specialCoin(1, "blue"), null};
        specialCoin[] card210prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card211prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card212prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card213prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card214prize = new specialCoin[]{new specialCoin(2, "green"), null, null, null, null};
        specialCoin[] card215prize = new specialCoin[]{new specialCoin(2, "green"), null, null, null, null};
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
        specialCoin[] card31prize = new specialCoin[]{null, new specialCoin(1, "white"), null, null, null};
        specialCoin[] card32prize = new specialCoin[]{null, new specialCoin(1, "white"), null, null, null};
        specialCoin[] card33prize = new specialCoin[]{null, new specialCoin(1, "white"), null, null, null};
        specialCoin[] card34prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card35prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card36prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card37prize = new specialCoin[]{new specialCoin(1, "green"), null, null, null, null};
        specialCoin[] card38prize = new specialCoin[]{null, new specialCoin(1, "white"), null, null, null};
        specialCoin[] card39prize = new specialCoin[]{null, new specialCoin(1, "white"), null, null, null};
        specialCoin[] card310prize = new specialCoin[]{null, new specialCoin(1, "white"), null, null, null};
        specialCoin[] card311prize = new specialCoin[]{null, new specialCoin(1, "white"), null, null, null};
        specialCoin[] card312prize = new specialCoin[]{null, new specialCoin(1, "white"), null, null, null};
        specialCoin[] card313prize = new specialCoin[]{null, null, null, new specialCoin(1, "blue"), null};
        specialCoin[] card314prize = new specialCoin[]{null, null, null, new specialCoin(1, "blue"), null};
        specialCoin[] card315prize = new specialCoin[]{null, null, null, new specialCoin(1, "blue"), null};
        Card[] setCard3 = {new Card(3, card31Coins, card31prize, new JLabel(new ImageIcon("images\\card3\\31.png"))), new Card(4, card32Coins, card32prize, new JLabel(new ImageIcon("images\\card3\\32.png"))), new Card(5, card33Coins, card33prize, new JLabel(new ImageIcon("images\\card3\\33.png"))), new Card(3, card34Coins, card34prize, new JLabel(new ImageIcon("images\\card3\\34.png"))), new Card(3, card35Coins, card35prize, new JLabel(new ImageIcon("images\\card3\\35.png"))), new Card(4, card36Coins, card36prize, new JLabel(new ImageIcon("images\\card3\\36.png"))), new Card(5, card37Coins, card37prize, new JLabel(new ImageIcon("images\\card3\\37.png"))), new Card(5, card38Coins, card38prize, new JLabel(new ImageIcon("images\\card3\\38.png"))), new Card(5, card39Coins, card39prize, new JLabel(new ImageIcon("images\\card3\\39.png"))), new Card(4, card310Coins, card310prize, new JLabel(new ImageIcon("images\\card3\\310.png"))), new Card(4, card311Coins, card311prize, new JLabel(new ImageIcon("images\\card3\\311.png"))), new Card(3, card312Coins, card312prize, new JLabel(new ImageIcon("images\\card3\\312.png"))), new Card(3, card313Coins, card313prize, new JLabel(new ImageIcon("images\\card3\\313.png"))), new Card(3, card314Coins, card314prize, new JLabel(new ImageIcon("images\\card3\\314.png"))), new Card(3, card315Coins, card315prize, new JLabel(new ImageIcon("images\\card3\\315.png")))};

        for (int i = 0; i < 15; i++) {
            int randomIndexToSwap = rand.nextInt(setCard1.length);
            Card temp = setCard1[randomIndexToSwap];
            setCard1[randomIndexToSwap] = setCard1[i];
            setCard1[i] = temp;
        }
        for (int i = 0; i < 15; i++) {
            int randomIndexToSwap = rand.nextInt(setCard2.length);
            Card temp = setCard2[randomIndexToSwap];
            setCard2[randomIndexToSwap] = setCard2[i];
            setCard2[i] = temp;
        }
        for (int i = 0; i < 15; i++) {
            int randomIndexToSwap = rand.nextInt(setCard3.length);
            Card temp = setCard3[randomIndexToSwap];
            setCard3[randomIndexToSwap] = setCard3[i];
            setCard3[i] = temp;
        }

        updateAvailableCards(prizeList, prizePanel);
        updateAvailableCards(setCard1, set1panel);
        updateAvailableCards(setCard2, set2panel);
        updateAvailableCards(setCard3, set3panel);

        for (Card card : prizeList) {
            addPrizeCardMouseListener(card, prizePanel, prizeList);
        }
        for (Card card : setCard1) {
            addCardMouseListener(card, set1panel, setCard1);
        }
        for (Card card : setCard2) {
            addCardMouseListener(card, set2panel, setCard2);
        }
        for (Card card : setCard3) {
            addCardMouseListener(card, set3panel, setCard3);
        }

        return grayPanel;
    }

    private JPanel createCoinPanel() {
        JPanel slotPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics b) {
                super.paintComponent(b);
                b.drawImage(bgImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);


        JButton returnButton = new JButton(new ImageIcon("images\\btns\\btn3.png"));
        returnButton.setOpaque(false);
        returnButton.setFocusPainted(false);
        returnButton.setBorderPainted(false);
        returnButton.setContentAreaFilled(false);
        returnButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        returnButton.addActionListener(e -> switchPanels(mainGamePanel));

        JLabel[] labels = {labelBtn1, labelBtn2, labelBtn3, labelBtn4, labelBtn5};
        for (int i = 0; i < 5; i++) {
            labels[i].setForeground(Color.white);
            labels[i].setBackground(Color.blue);
            labels[i].setFont(normalFont);
        }

        JButton[] btns = {slotButton11, slotButton12, slotButton21, slotButton22, slotButton31, slotButton32, slotButton41, slotButton42, slotButton51, slotButton52};
        for (JButton btn : btns) {
            btn.setFont(normalFont);
            btn.setPreferredSize(new Dimension(150, 50));
            btn.addActionListener(e -> {
                for (Coin coin : slotCoins) {
                    if (coin.getNum() > 4) {
                        coin.setNum(4);
                    }
                    labelBtn1.setText("تعداد سکه های قرمز : ".concat(String.valueOf(slotCoins[4].getNum())));
                    labelBtn2.setText("تعداد سکه های آبی : ".concat(String.valueOf(slotCoins[3].getNum())));
                    labelBtn3.setText("تعداد سکه های سبز : ".concat(String.valueOf(slotCoins[0].getNum())));
                    labelBtn4.setText("تعداد سکه های سفید : ".concat(String.valueOf(slotCoins[1].getNum())));
                    labelBtn5.setText("تعداد سکه های سیاه : ".concat(String.valueOf(slotCoins[2].getNum())));
                }
                coinPrinter();
            });
        }

        slotButton11.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (slotCoins[4].getNum() == 4 && clickNum < 1) {
                        player1.takeCoin(2, "red");
                        clickNum++;
                        slotCoins[4].num -= 2;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        changeTurn();
                        clickNum = 0;
                    }
                    break;
                case -1:
                    if (slotCoins[4].getNum() == 4 && clickNum < 1) {
                        player2.takeCoin(2, "red");
                        slotCoins[4].num -= 2;
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        changeTurn();
                        clickNum = 0;
                    }
                    break;
            }
        });
        slotButton21.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (slotCoins[3].getNum() == 4 && clickNum < 1) {
                        player1.takeCoin(2, "blue");
                        slotCoins[3].num -= 2;
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        changeTurn();
                        clickNum = 0;
                    }
                    break;
                case -1:
                    if (slotCoins[3].getNum() == 4 && clickNum < 1) {
                        player2.takeCoin(2, "blue");
                        slotCoins[3].num -= 2;
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        changeTurn();
                        clickNum = 0;
                    }
                    break;
            }
        });
        slotButton31.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (slotCoins[0].getNum() == 4 && clickNum < 1) {
                        player1.takeCoin(2, "green");
                        slotCoins[0].num -= 2;
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        changeTurn();
                        clickNum = 0;
                    }
                    break;
                case -1:
                    if (slotCoins[0].getNum() == 4 && clickNum < 1) {
                        player2.takeCoin(2, "green");
                        slotCoins[0].num -= 2;
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        changeTurn();
                        clickNum = 0;
                    }
                    break;
            }
        });
        slotButton41.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (slotCoins[1].getNum() == 4 && clickNum < 1) {
                        player1.takeCoin(2, "white");
                        clickNum++;
                        slotCoins[1].num -= 2;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        changeTurn();
                        clickNum = 0;
                    }
                    break;
                case -1:
                    if (slotCoins[1].getNum() == 4 && clickNum < 1) {
                        player2.takeCoin(2, "white");
                        clickNum++;
                        slotCoins[1].num -= 2;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                        labelBtn1.setText("تعداد سکه های قرمز : ".concat(String.valueOf(slotCoins[4].getNum())));
                        labelBtn2.setText("تعداد سکه های آبی : ".concat(String.valueOf(slotCoins[3].getNum())));
                        labelBtn3.setText("تعداد سکه های سبز : ".concat(String.valueOf(slotCoins[0].getNum())));
                        labelBtn4.setText("تعداد سکه های سفید : ".concat(String.valueOf(slotCoins[1].getNum())));
                        labelBtn5.setText("تعداد سکه های سیاه : ".concat(String.valueOf(slotCoins[2].getNum())));
                    } else {
                        changeTurn();
                        clickNum = 0;
                    }
                    break;
            }
        });
        slotButton51.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (slotCoins[2].getNum() == 4 && clickNum < 1) {
                        player1.takeCoin(2, "black");
                        clickNum++;
                        slotCoins[2].num -= 2;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        changeTurn();
                        clickNum = 0;
                    }
                    break;
                case -1:
                    if (slotCoins[2].getNum() == 4 && clickNum < 1) {
                        player2.takeCoin(2, "black");
                        clickNum++;
                        slotCoins[2].num -= 2;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                        labelBtn1.setText("تعداد سکه های قرمز : ".concat(String.valueOf(slotCoins[4].getNum())));
                        labelBtn2.setText("تعداد سکه های آبی : ".concat(String.valueOf(slotCoins[3].getNum())));
                        labelBtn3.setText("تعداد سکه های سبز : ".concat(String.valueOf(slotCoins[0].getNum())));
                        labelBtn4.setText("تعداد سکه های سفید : ".concat(String.valueOf(slotCoins[1].getNum())));
                        labelBtn5.setText("تعداد سکه های سیاه : ".concat(String.valueOf(slotCoins[2].getNum())));
                    } else {
                        changeTurn();
                        clickNum = 0;
                    }
                    break;
            }
        });
        slotButton12.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (slotCoins[4].getNum() > 0 && clickNum < 3) {
                        player1.takeCoin(1, "red");
                        slotCoins[4].num--;
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        changeTurn();
                        clickNum = 0;
                    }
                    break;
                case -1:
                    if (slotCoins[4].getNum() > 0 && clickNum < 3) {
                        player2.takeCoin(1, "red");
                        slotCoins[4].num--;
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        changeTurn();
                        clickNum = 0;
                    }
                    break;
            }
        });
        slotButton22.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (slotCoins[3].getNum() > 0 && clickNum < 3) {
                        player1.takeCoin(1, "blue");
                        slotCoins[3].num--;
                        clickNum++;
                        slotButton22.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        changeTurn();
                        clickNum = 0;
                    }
                    break;
                case -1:
                    if (slotCoins[3].getNum() > 0 && clickNum < 3) {
                        player2.takeCoin(1, "blue");
                        slotCoins[3].num--;
                        clickNum++;
                        slotButton22.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        changeTurn();
                        clickNum = 0;
                    }
                    break;
            }
        });
        slotButton32.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (slotCoins[0].getNum() > 0 && clickNum < 3) {
                        player1.takeCoin(1, "green");
                        slotCoins[0].num--;
                        clickNum++;
                        slotButton32.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        changeTurn();
                        clickNum = 0;
                    }
                    break;
                case -1:
                    if (slotCoins[0].getNum() > 0 && clickNum < 3) {
                        player2.takeCoin(1, "green");
                        slotCoins[0].num--;
                        clickNum++;
                        slotButton32.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        changeTurn();
                        clickNum = 0;
                    }
                    break;
            }
        });
        slotButton42.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (slotCoins[1].getNum() > 0 && clickNum < 3) {
                        player1.takeCoin(1, "white");
                        slotCoins[1].num--;
                        clickNum++;
                        slotButton42.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        changeTurn();
                        clickNum = 0;
                    }
                    break;
                case -1:
                    if (slotCoins[1].getNum() > 0 && clickNum < 3) {
                        player2.takeCoin(1, "white");
                        slotCoins[1].num--;
                        clickNum++;
                        slotButton42.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        changeTurn();
                        clickNum = 0;
                    }
                    break;
            }
        });
        slotButton52.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (slotCoins[2].getNum() > 0 && clickNum < 3) {
                        player1.takeCoin(1, "black");
                        slotCoins[2].num--;
                        clickNum++;
                        slotButton52.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);

                    } else {
                        changeTurn();
                        clickNum = 0;
                    }
                    break;
                case -1:
                    if (slotCoins[2].getNum() > 0 && clickNum < 3) {
                        player2.takeCoin(1, "black");
                        slotCoins[2].num--;
                        clickNum++;
                        slotButton52.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        changeTurn();
                        clickNum = 0;
                    }
                    break;
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        slotPanel.add(returnButton, gbc);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        slotPanel.add(labelBtn1, gbc);
        gbc.gridx = 1;
        slotPanel.add(slotButton11, gbc);
        gbc.gridx = 2;
        slotPanel.add(slotButton12, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        slotPanel.add(labelBtn2, gbc);
        gbc.gridx = 1;
        slotPanel.add(slotButton21, gbc);
        gbc.gridx = 2;
        slotPanel.add(slotButton22, gbc);
        gbc.gridy = 3;
        gbc.gridx = 0;
        slotPanel.add(labelBtn3, gbc);
        gbc.gridx = 1;
        slotPanel.add(slotButton31, gbc);
        gbc.gridx = 2;
        slotPanel.add(slotButton32, gbc);
        gbc.gridy = 4;
        gbc.gridx = 0;
        slotPanel.add(labelBtn4, gbc);
        gbc.gridx = 1;
        slotPanel.add(slotButton41, gbc);
        gbc.gridx = 2;
        slotPanel.add(slotButton42, gbc);
        gbc.gridy = 5;
        gbc.gridx = 0;
        slotPanel.add(labelBtn5, gbc);
        gbc.gridx = 1;
        slotPanel.add(slotButton51, gbc);
        gbc.gridx = 2;
        slotPanel.add(slotButton52, gbc);

        return slotPanel;
    }

    private JPanel createReservePanel() {
        JPanel resPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics b) {
                super.paintComponent(b);
                b.drawImage(bgImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        JPanel reserveContainer = new JPanel(new GridBagLayout());
        reserveContainer.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        player1res.setOpaque(false);
        reserveContainer.add(player1res, gbc);

        gbc.gridy = 1;
        JButton returnButton = new JButton(new ImageIcon("images\\btns\\btn3.png"));
        returnButton.setOpaque(false);
        returnButton.setFocusPainted(false);
        returnButton.setBorderPainted(false);
        returnButton.setContentAreaFilled(false);
        returnButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        returnButton.addActionListener(e -> switchPanels(mainGamePanel));
        reserveContainer.add(returnButton, gbc);

        gbc.gridy = 2;
        player2res.setOpaque(false);
        reserveContainer.add(player2res, gbc);

        resPanel.add(reserveContainer, BorderLayout.CENTER);
        return resPanel;
    }

    private JPanel createWinnerPanel() {
        JPanel win = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 40, 0);
        JLabel endGame = new JLabel("برنده مسابقه : ");
        endGame.setFont(normalFont);
        endGame.setForeground(Color.RED);
        win.add(endGame, gbc);
        if (player1.pScore > player2.pScore) {
            gbc.gridy = 1;
            JLabel Winner = new JLabel("بازیکن 1");
            Winner.setFont(bigFont);
            Winner.setForeground(Color.BLACK);
            win.add(Winner, gbc);
        } else if (player1.pScore < player2.pScore) {
            gbc.gridy = 1;
            JLabel Winner = new JLabel("بازیکن 2");
            Winner.setFont(bigFont);
            Winner.setForeground(Color.BLACK);
            win.add(Winner, gbc);
        } else{
            if (player1.cardNum < player2.cardNum) {
                gbc.gridy = 1;
                JLabel Winner = new JLabel("بازیکن 1");
                Winner.setFont(bigFont);
                Winner.setForeground(Color.BLACK);
                win.add(Winner, gbc);
            } else if (player2.cardNum < player1.cardNum) {
                gbc.gridy = 1;
                JLabel Winner = new JLabel("بازیکن 2");
                Winner.setFont(bigFont);
                Winner.setForeground(Color.BLACK);
                win.add(Winner, gbc);
            }
        }
        return win;
    }

    //switch and move between panels
    private void switchPanels(JPanel panel) {
        mainPanel.removeAll();
        mainPanel.add(panel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void switchGuide(JScrollPane pane) {
        mainPanel.removeAll();
        mainPanel.add(pane);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main game = new Main();
            game.setVisible(true);
        });
    }
}