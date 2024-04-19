//Swing needs

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Random;

public class Main extends JFrame {
    //game Font
    InputStream is = Main.class.getResourceAsStream("Vazir.ttf");
    Font font;

    {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(is));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    Font bigFont = font.deriveFont(40f);
    Font normalFont = font.deriveFont(20f);
    //Frames and panels
    private final JPanel mainPanel = new JPanel(new BorderLayout());
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
    private static final Image bgImage;

    static {
        try {
            bgImage = ImageIO.read(ClassLoader.getSystemResource("images/background.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //turn system and players
    Coin[] player1Coin = new Coin[]{new Coin(0, "green"), new Coin(0, "white"), new Coin(0, "black"), new Coin(0, "blue"), new Coin(0, "red"), new Coin(0, "gold")};
    specialCoin[] player1SCoin = new specialCoin[]{new specialCoin(0, "green"), new specialCoin(0, "white"), new specialCoin(0, "black"), new specialCoin(0, "blue"), new specialCoin(0, "red")};
    Coin[] player2Coin = new Coin[]{new Coin(0, "green"), new Coin(0, "white"), new Coin(0, "black"), new Coin(0, "blue"), new Coin(0, "red"), new Coin(0, "gold")};
    specialCoin[] player2SCoin = new specialCoin[]{new specialCoin(0, "green"), new specialCoin(0, "white"), new specialCoin(0, "black"), new specialCoin(0, "blue"), new specialCoin(0, "red")};
    private final Players player1 = new Players(player1Coin, player1SCoin);
    private final Players player2 = new Players(player2Coin, player2SCoin);
    private static int turn = 1;
    private int clickNum = 0;
    //shuffle cards list
    Random rand = new Random();
    //coin slots
    private static final Coin[] slotCoins = {new Coin(4, "green"), new Coin(4, "white"), new Coin(4, "black"), new Coin(4, "blue"), new Coin(4, "red"), new Coin(5, "gold")};
    //slots label & button
    private final JLabel labelBtn1 = new JLabel(" " + "تعداد سکه های قرمز : ".concat(slotCoins[4].getNum() + " "), SwingConstants.CENTER);
    private final JLabel labelBtn2 = new JLabel(" " + "تعداد سکه های آبی : ".concat(slotCoins[3].getNum() + " "), SwingConstants.CENTER);
    private final JLabel labelBtn3 = new JLabel(" " + "تعداد سکه های سبز : ".concat(slotCoins[0].getNum() + " "), SwingConstants.CENTER);
    private final JLabel labelBtn4 = new JLabel(" " + "تعداد سکه های سفید : ".concat(slotCoins[1].getNum() + " "), SwingConstants.CENTER);
    private final JLabel labelBtn5 = new JLabel(" " + "تعداد سکه های سیاه : ".concat(slotCoins[2].getNum() + " "), SwingConstants.CENTER);
    private static final JButton slotButton11 = new JButton("دو قرمز");
    private static final JButton slotButton12 = new JButton("یک قرمز");
    private static final JButton slotButton21 = new JButton("دو آبی");
    private static final JButton slotButton22 = new JButton("یک آبی");
    private static final JButton slotButton31 = new JButton("دو سبز");
    private static final JButton slotButton32 = new JButton("یک سبز");
    private static final JButton slotButton41 = new JButton("دو سفید");
    private static final JButton slotButton42 = new JButton("یک سفید");
    private static final JButton slotButton51 = new JButton("دو سیاه");
    private static final JButton slotButton52 = new JButton("یک سیاه");

    private void labelUpdate(){
        labelBtn1.setText(" " + "تعداد سکه های قرمز : ".concat(slotCoins[4].getNum() + " "));
        labelBtn2.setText(" " + "تعداد سکه های آبی : ".concat(slotCoins[3].getNum() + " "));
        labelBtn3.setText(" " + "تعداد سکه های سبز : ".concat(slotCoins[0].getNum() + " "));
        labelBtn4.setText(" " + "تعداد سکه های سفید : ".concat(slotCoins[1].getNum() + " "));
        labelBtn5.setText(" " + "تعداد سکه های سیاه : ".concat(slotCoins[2].getNum() + " "));
    }

    //show players cards
    private void cardPrinter() {
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
    private void coinPrinter() {
        tCoinPanel.removeAll();
        bCoinPanel.removeAll();
        String[] coinColors = {"green", "white", "black", "blue", "red", "gold"};

        for (int i = 0; i < player1.getPlayerCoin().length; i++) {
            for (int k = 0; k < player1.getPlayerCoin()[i].getNum(); k++) {
                JLabel coinLabel;
                try {
                    coinLabel = new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/coins/" + coinColors[i] + ".png")))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                int finalI = i;
                coinLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (player1.coinCounter() > 10) {
                            switch (coinColors[finalI]) {
                                case "green" -> slotCoins[0].setNum(slotCoins[0].getNum() + 1);
                                case "white" -> slotCoins[1].setNum(slotCoins[1].getNum() + 1);
                                case "black" -> slotCoins[2].setNum(slotCoins[2].getNum() + 1);
                                case "blue" -> slotCoins[3].setNum(slotCoins[3].getNum() + 1);
                                case "red" -> slotCoins[4].setNum(slotCoins[4].getNum() + 1);
                            }
                            player1.getPlayerCoin()[finalI].setNum(player1.getPlayerCoin()[finalI].getNum() - 1);
                            coinPrinter();
                            labelUpdate();
                        }
                    }
                });
                tCoinPanel.add(coinLabel);
            }
        }
        for (int i = 0; i < player2.getPlayerCoin().length; i++) {
            for (int k = 0; k < player2.getPlayerCoin()[i].getNum(); k++) {
                JLabel coinLabel;
                try {
                    coinLabel = new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/coins/" + coinColors[i] + ".png")))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                int finalI = i;
                coinLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (player2.coinCounter() > 10) {
                            switch (coinColors[finalI]) {
                                case "green" -> slotCoins[0].setNum(slotCoins[0].getNum() + 1);
                                case "white" -> slotCoins[1].setNum(slotCoins[1].getNum() + 1);
                                case "black" -> slotCoins[2].setNum(slotCoins[2].getNum() + 1);
                                case "blue" -> slotCoins[3].setNum(slotCoins[3].getNum() + 1);
                                case "red" -> slotCoins[4].setNum(slotCoins[4].getNum() + 1);
                            }
                            player2.getPlayerCoin()[finalI].setNum(player2.getPlayerCoin()[finalI].getNum() - 1);
                            coinPrinter();
                            labelUpdate();
                        }
                    }
                });
                bCoinPanel.add(coinLabel);
            }
        }

        tCoinPanel.revalidate();
        tCoinPanel.repaint();
        bCoinPanel.revalidate();
        bCoinPanel.repaint();
    }

    //show players special coins
    private void sCoinPrinter() {
        tSCoinPanel.removeAll();
        bSCoinPanel.removeAll();
        String[] sCoinColors = {"green", "white", "black", "blue", "red"};
        int i;
        for (i = 0; i < player1.getPlayerSCoin().length; i++) {
            for (int k = 0; k < player1.getPlayerSCoin()[i].getsNum(); k++) {
                try {
                    tSCoinPanel.add(new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/coins/" + sCoinColors[i] + ".png"))))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        for (i = 0; i < player2.getPlayerSCoin().length; i++) {
            for (int k = 0; k < player2.getPlayerSCoin()[i].getsNum(); k++) {
                try {
                    bSCoinPanel.add(new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/coins/" + sCoinColors[i] + ".png"))))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        tSCoinPanel.revalidate();
        tSCoinPanel.repaint();
        bSCoinPanel.revalidate();
        bSCoinPanel.repaint();
    }

    //show players score
    private void scorePrinter() {
        tScorePanel.removeAll();
        tScorePanel.add(new JLabel(String.valueOf(player1.getPScore())));
        tScorePanel.revalidate();
        tScorePanel.repaint();

        bScorePanel.removeAll();
        bScorePanel.add(new JLabel(String.valueOf(player2.getPScore())));
        bScorePanel.revalidate();
        bScorePanel.repaint();
    }

    //one reserve for each turn
    private boolean reserveDone = false;

    //change game turn
    private void changeTurn() {
        turn *= -1;
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
        //show the winner player
        if (turn == 1) {
            if (player1.getPScore() >= 15 || player2.getPScore() >= 15) {
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
                        } else {
                            player2.buyReserve(card, player2, slotCoins);
                        }
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
                scorePrinter();
                cardPrinter();
                for (Coin coin : slotCoins) {
                    if (coin.getNum() > 4) {
                        coin.setNum(4);
                    }
                    labelUpdate();
                }
                updateAvailableCards(cards, panelToUpdate);
            }
        });
    }

    private void addPrizeCardMouseListener(Card card, JPanel panelToUpdate, Card[] cards) {
        card.getCardImg().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Players currentPlayer = (turn == 1) ? player1 : player2;
                currentPlayer.buyPrize(card, currentPlayer);
                scorePrinter();
                cardPrinter();
                for (Coin coin : slotCoins) {
                    if (coin.getNum() > 4) {
                        coin.setNum(4);
                    }
                    labelUpdate();
                }
                updateAvailableCards(cards, panelToUpdate);
            }
        });
    }

    public Main() {
        setTitle("Amusement Park!");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        try {
            setIconImage(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("images/logo.png")))).getImage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        loginPanel = createLoginPanel();
        guidePanel = createGuidePanel();
        mainGamePanel = createMainGamePanel();
        storePanel = createStorePanel();
        coinPanel = createCoinPanel();
        reservePanel = createReservePanel();
        winnerPanel = createWinnerPanel();

        mainPanel.add(loginPanel, BorderLayout.CENTER);

        add(mainPanel);
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
        JButton startButton;
        try {
            startButton = new JButton(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("images/btns/btn1.png")))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        startButton.setOpaque(false);
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.addActionListener(e -> switchPanels(mainGamePanel));
        welcomePanel.add(startButton, gbc);

        gbc.gridy = 2;
        JButton guideButton;
        try {
            guideButton = new JButton(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/btns/btn2.png")))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        guideButton.setOpaque(false);
        guideButton.setFocusPainted(false);
        guideButton.setBorderPainted(false);
        guideButton.setContentAreaFilled(false);
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
        gbc.gridy = 1;

        try {
            gPagePanel.add(new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/guide/g1.png"))))), gbc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        gbc.gridy = 2;
        try {
            gPagePanel.add(new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/guide/g2.png"))))), gbc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        gbc.gridy = 3;
        try {
            gPagePanel.add(new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/guide/g3.png"))))), gbc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        gbc.gridy = 4;
        try {
            gPagePanel.add(new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/guide/g4.png"))))), gbc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        gbc.gridy = 0;
        gbc.insets = new Insets(60, 0, 0, 0);
        JButton returnMain;
        try {
            returnMain = new JButton(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/btns/btn3.png")))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        returnMain.setOpaque(false);
        returnMain.setFocusPainted(false);
        returnMain.setBorderPainted(false);
        returnMain.setContentAreaFilled(false);
        returnMain.addActionListener(e -> switchPanels(loginPanel));
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

        topPanel.setPreferredSize(new Dimension(1920, 80));
        topPanel.setOpaque(false);
        topPanel.setBorder(new EmptyBorder(30, 0, 0, 0));
        JLabel p1Name = new JLabel("Player 1 :");
        p1Name.setFont(normalFont);
        p1Name.setForeground(Color.white);
        topPanel.add(p1Name);
        topPanel.add(tCoinPanel);
        topPanel.add(tSCoinPanel);
        topPanel.add(tScorePanel);
        tCardPanel.setOpaque(false);

        bottomPanel.setPreferredSize(new Dimension(1920, 80));
        bottomPanel.setOpaque(false);
        JLabel p2Name = new JLabel("Player 2 :");
        p2Name.setFont(normalFont);
        p2Name.setForeground(Color.white);
        bottomPanel.add(p2Name);
        bottomPanel.add(bCoinPanel);
        bottomPanel.add(bSCoinPanel);
        bottomPanel.add(bScorePanel);
        bCardPanel.setOpaque(false);


        sidePanel.setPreferredSize(new Dimension(300, 880));
        sidePanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(30, 0, 30, 30);
        JButton shopButton;
        try {
            shopButton = new JButton(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/btns/btn4.png")))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        shopButton.setOpaque(false);
        shopButton.setFocusPainted(false);
        shopButton.setBorderPainted(false);
        shopButton.setContentAreaFilled(false);
        shopButton.addActionListener(e -> switchPanels(storePanel));
        sidePanel.add(shopButton, gbc);

        gbc.gridy = 1;
        JButton coinButton;
        try {
            coinButton = new JButton(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/btns/btn5.png")))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        coinButton.setOpaque(false);
        coinButton.setFocusPainted(false);
        coinButton.setBorderPainted(false);
        coinButton.setContentAreaFilled(false);
        coinButton.addActionListener(e -> switchPanels(coinPanel));
        sidePanel.add(coinButton, gbc);

        gbc.gridy = 2;
        JButton reserveButton;
        try {
            reserveButton = new JButton(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/btns/btn6.png")))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        reserveButton.setOpaque(false);
        reserveButton.setFocusPainted(false);
        reserveButton.setBorderPainted(false);
        reserveButton.setContentAreaFilled(false);
        reserveButton.addActionListener(e -> switchPanels(reservePanel));
        sidePanel.add(reserveButton, gbc);

        gbc.gridy = 3;
        JButton turnButton;
        try {
            turnButton = new JButton(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/btns/btn7.png")))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        turnButton.setOpaque(false);
        turnButton.setFocusPainted(false);
        turnButton.setBorderPainted(false);
        turnButton.setContentAreaFilled(false);
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
        JPanel grayPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics b) {
                super.paintComponent(b);
                b.drawImage(bgImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        JButton backward;
        try {
            backward = new JButton(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/btns/btn3.png")))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        backward.setOpaque(false);
        backward.setFocusPainted(false);
        backward.setBorderPainted(false);
        backward.setContentAreaFilled(false);
        backward.addActionListener(e -> switchPanels(mainGamePanel));
        topPanel.add(backward, gbc);

        gbc.gridy = 1;
        gbc.gridheight = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 10);
        JPanel prizePanel = new JPanel(new GridLayout(3, 1, 0, 10));
        prizePanel.setOpaque(false);
        topPanel.add(prizePanel, gbc);

        gbc.gridx = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        JPanel set1panel = new JPanel(new GridLayout(1, 4, 10, 0));
        set1panel.setOpaque(false);
        topPanel.add(set1panel, gbc);

        gbc.gridy = 2;
        JPanel set2panel = new JPanel(new GridLayout(1, 4, 10, 0));
        set2panel.setOpaque(false);
        topPanel.add(set2panel, gbc);

        gbc.gridy = 3;
        JPanel set3panel = new JPanel(new GridLayout(1, 4, 10, 0));
        set3panel.setOpaque(false);
        topPanel.add(set3panel, gbc);

        specialCoin[] prize1Coins = new specialCoin[]{new specialCoin(4, "green"), null, null, null, new specialCoin(4, "red")};
        specialCoin[] prize2Coins = new specialCoin[]{new specialCoin(5, "green"), null, null, null, new specialCoin(5, "red")};
        specialCoin[] prize3Coins = new specialCoin[]{new specialCoin(5, "green"), null, null, new specialCoin(4, "blue"), null};
        Card[] prizeList;
        try {
            prizeList = new Card[]{new Card(3, null, prize1Coins, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card prize/prize1.png")))))), new Card(4, null, prize2Coins, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card prize/prize2.png")))))), new Card(4, null, prize3Coins, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card prize/prize3.png"))))))};
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


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
        Card[] setCard1;
        try {
            setCard1 = new Card[]{new Card(1, card11Coins, card11prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card1/11.png")))))), new Card(1, card12Coins, card12prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card1/12.png")))))), new Card(1, card13Coins, card13prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card1/13.png")))))), new Card(0, card14Coins, card14prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card1/14.png")))))), new Card(0, card15Coins, card15prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card1/15.png")))))), new Card(0, card16Coins, card16prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card1/16.png")))))), new Card(1, card17Coins, card17prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card1/17.png")))))), new Card(1, card18Coins, card18prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card1/18.png")))))), new Card(0, card19Coins, card19prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card1/19.png")))))), new Card(0, card110Coins, card110prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card1/110.png")))))), new Card(1, card111Coins, card111prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card1/111.png")))))), new Card(1, card112Coins, card112prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card1/112.png")))))), new Card(1, card113Coins, card113prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card1/113.png")))))), new Card(0, card114Coins, card114prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card1/114.png")))))), new Card(1, card115Coins, card115prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card1/115.png"))))))};
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Coin[] card21Coins = new Coin[]{null, new Coin(2, "white"), new Coin(2, "black"), new Coin(2, "blue"), null, null};
        Coin[] card22Coins = new Coin[]{null, new Coin(2, "white"), new Coin(2, "black"), new Coin(3, "blue"), null, null};
        Coin[] card23Coins = new Coin[]{null, new Coin(2, "white"), new Coin(3, "black"), new Coin(3, "blue"), null, null};
        Coin[] card24Coins = new Coin[]{null, new Coin(4, "white"), new Coin(2, "black"), new Coin(2, "blue"), null, null};
        Coin[] card25Coins = new Coin[]{null, new Coin(2, "white"), new Coin(2, "black"), new Coin(2, "blue"), new Coin(2, "red"), null};
        Coin[] card26Coins = new Coin[]{null, null, new Coin(3, "black"), new Coin(3, "blue"), new Coin(2, "red"), null};
        Coin[] card27Coins = new Coin[]{null, null, new Coin(2, "black"), new Coin(3, "blue"), new Coin(3, "red"), null};
        Coin[] card28Coins = new Coin[]{null, null, new Coin(2, "black"), new Coin(3, "blue"), new Coin(2, "red"), null};
        Coin[] card29Coins = new Coin[]{null, null, new Coin(2, "black"), new Coin(2, "blue"), new Coin(2, "red"), null};
        Coin[] card210Coins = new Coin[]{null, null, new Coin(3, "black"), new Coin(4, "blue"), new Coin(1, "red"), null};
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
        Card[] setCard2;
        try {
            setCard2 = new Card[]{new Card(2, card21Coins, card21prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card2/21.png")))))), new Card(2, card22Coins, card22prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card2/22.png")))))), new Card(2, card23Coins, card23prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card2/23.png")))))), new Card(2, card24Coins, card24prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card2/24.png")))))), new Card(2, card25Coins, card25prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card2/25.png")))))), new Card(3, card26Coins, card26prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card2/26.png")))))), new Card(3, card27Coins, card27prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card2/27.png")))))), new Card(3, card28Coins, card28prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card2/28.png")))))), new Card(3, card29Coins, card29prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card2/29.png")))))), new Card(3, card210Coins, card210prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card2/210.png")))))), new Card(4, card211Coins, card211prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card2/211.png")))))), new Card(4, card212Coins, card212prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card2/212.png")))))), new Card(4, card213Coins, card213prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card2/213.png")))))), new Card(4, card214Coins, card214prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card2/214.png")))))), new Card(4, card215Coins, card215prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card2/215.png"))))))};
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
        Card[] setCard3;
        try {
            setCard3 = new Card[]{new Card(3, card31Coins, card31prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card3/31.png")))))), new Card(4, card32Coins, card32prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card3/32.png")))))), new Card(5, card33Coins, card33prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card3/33.png")))))), new Card(3, card34Coins, card34prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(Objects.requireNonNull(getClass().getResourceAsStream("/images/card3/34.png"))))))), new Card(3, card35Coins, card35prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card3/35.png")))))), new Card(4, card36Coins, card36prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card3/36.png")))))), new Card(5, card37Coins, card37prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card3/37.png")))))), new Card(5, card38Coins, card38prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card3/38.png")))))), new Card(5, card39Coins, card39prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card3/39.png")))))), new Card(4, card310Coins, card310prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card3/310.png")))))), new Card(4, card311Coins, card311prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card3/311.png")))))), new Card(3, card312Coins, card312prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card3/312.png")))))), new Card(3, card313Coins, card313prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card3/313.png")))))), new Card(3, card314Coins, card314prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card3/314.png")))))), new Card(3, card315Coins, card315prize, new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/card3/315.png"))))))};
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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

        grayPanel.add(topPanel, BorderLayout.CENTER);
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


        JButton returnButton;
        try {
            returnButton = new JButton(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/btns/btn3.png")))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        returnButton.setOpaque(false);
        returnButton.setFocusPainted(false);
        returnButton.setBorderPainted(false);
        returnButton.setContentAreaFilled(false);
        returnButton.addActionListener(e -> switchPanels(mainGamePanel));

        JLabel[] labels = {labelBtn1, labelBtn2, labelBtn3, labelBtn4, labelBtn5};
        for (int i = 0; i < 5; i++) {
            labels[i].setForeground(Color.white);
            labels[i].setOpaque(true);
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
                    labelUpdate();
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
                        slotCoins[4].setNum(slotCoins[4].getNum() - 2);
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(getComponent(0), "امکان دریافت سکه وجود ندارد");
                    }
                    break;
                case -1:
                    if (slotCoins[4].getNum() == 4 && clickNum < 1) {
                        player2.takeCoin(2, "red");
                        slotCoins[4].setNum(slotCoins[4].getNum() - 2);
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(getComponent(0), "امکان دریافت سکه وجود ندارد");
                    }
                    break;
            }
        });
        slotButton21.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (slotCoins[3].getNum() == 4 && clickNum < 1) {
                        player1.takeCoin(2, "blue");
                        slotCoins[3].setNum(slotCoins[3].getNum() - 2);
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(getComponent(0), "امکان دریافت سکه وجود ندارد");
                    }
                    break;
                case -1:
                    if (slotCoins[3].getNum() == 4 && clickNum < 1) {
                        player2.takeCoin(2, "blue");
                        slotCoins[3].setNum(slotCoins[3].getNum() - 2);
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(getComponent(0), "امکان دریافت سکه وجود ندارد");
                    }
                    break;
            }
        });
        slotButton31.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (slotCoins[0].getNum() == 4 && clickNum < 1) {
                        player1.takeCoin(2, "green");
                        slotCoins[0].setNum(slotCoins[0].getNum() - 2);
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(getComponent(0), "امکان دریافت سکه وجود ندارد");
                    }
                    break;
                case -1:
                    if (slotCoins[0].getNum() == 4 && clickNum < 1) {
                        player2.takeCoin(2, "green");
                        slotCoins[0].setNum(slotCoins[0].getNum() - 2);
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(getComponent(0), "امکان دریافت سکه وجود ندارد");
                    }
                    break;
            }
        });
        slotButton41.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (slotCoins[1].getNum() == 4 && clickNum < 1) {
                        player1.takeCoin(2, "white");
                        slotCoins[1].setNum(slotCoins[1].getNum() - 2);
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(getComponent(0), "امکان دریافت سکه وجود ندارد");
                    }
                    break;
                case -1:
                    if (slotCoins[1].getNum() == 4 && clickNum < 1) {
                        player2.takeCoin(2, "white");
                        slotCoins[1].setNum(slotCoins[1].getNum() - 2);
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(getComponent(0), "امکان دریافت سکه وجود ندارد");
                    }
                    break;
            }
        });
        slotButton51.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (slotCoins[2].getNum() == 4 && clickNum < 1) {
                        player1.takeCoin(2, "black");
                        slotCoins[2].setNum(slotCoins[2].getNum() - 2);
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(getComponent(0), "امکان دریافت سکه وجود ندارد");
                    }
                    break;
                case -1:
                    if (slotCoins[2].getNum() == 4 && clickNum < 1) {
                        player2.takeCoin(2, "black");
                        slotCoins[2].setNum(slotCoins[2].getNum() - 2);
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton22.setEnabled(false);
                        slotButton32.setEnabled(false);
                        slotButton42.setEnabled(false);
                        slotButton52.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(getComponent(0), "امکان دریافت سکه وجود ندارد");
                    }
                    break;
            }
        });
        slotButton12.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (slotCoins[4].getNum() > 0 && clickNum < 3) {
                        player1.takeCoin(1, "red");
                        slotCoins[4].setNum(slotCoins[4].getNum() - 1);
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(getComponent(0), "امکان دریافت سکه وجود ندارد");
                    }
                    break;
                case -1:
                    if (slotCoins[4].getNum() > 0 && clickNum < 3) {
                        player2.takeCoin(1, "red");
                        slotCoins[4].setNum(slotCoins[4].getNum() - 1);
                        clickNum++;
                        slotButton12.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(getComponent(0), "امکان دریافت سکه وجود ندارد");
                    }
                    break;
            }
        });
        slotButton22.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (slotCoins[3].getNum() > 0 && clickNum < 3) {
                        player1.takeCoin(1, "blue");
                        slotCoins[3].setNum(slotCoins[3].getNum() - 1);
                        clickNum++;
                        slotButton22.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(getComponent(0), "امکان دریافت سکه وجود ندارد");
                    }
                    break;
                case -1:
                    if (slotCoins[3].getNum() > 0 && clickNum < 3) {
                        player2.takeCoin(1, "blue");
                        slotCoins[3].setNum(slotCoins[3].getNum() - 1);
                        clickNum++;
                        slotButton22.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(getComponent(0), "امکان دریافت سکه وجود ندارد");
                    }
                    break;
            }
        });
        slotButton32.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (slotCoins[0].getNum() > 0 && clickNum < 3) {
                        player1.takeCoin(1, "green");
                        slotCoins[0].setNum(slotCoins[0].getNum() - 1);
                        clickNum++;
                        slotButton32.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(getComponent(0), "امکان دریافت سکه وجود ندارد");
                    }
                    break;
                case -1:
                    if (slotCoins[0].getNum() > 0 && clickNum < 3) {
                        player2.takeCoin(1, "green");
                        slotCoins[0].setNum(slotCoins[0].getNum() - 1);
                        clickNum++;
                        slotButton32.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(getComponent(0), "امکان دریافت سکه وجود ندارد");
                    }
                    break;
            }
        });
        slotButton42.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (slotCoins[1].getNum() > 0 && clickNum < 3) {
                        player1.takeCoin(1, "white");
                        slotCoins[1].setNum(slotCoins[1].getNum() - 1);
                        clickNum++;
                        slotButton42.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(getComponent(0), "امکان دریافت سکه وجود ندارد");
                    }
                    break;
                case -1:
                    if (slotCoins[1].getNum() > 0 && clickNum < 3) {
                        player2.takeCoin(1, "white");
                        slotCoins[1].setNum(slotCoins[1].getNum() - 1);
                        clickNum++;
                        slotButton42.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(getComponent(0), "امکان دریافت سکه وجود ندارد");
                    }
                    break;
            }
        });
        slotButton52.addActionListener(e -> {
            switch (turn) {
                case 1:
                    if (slotCoins[2].getNum() > 0 && clickNum < 3) {
                        player1.takeCoin(1, "black");
                        slotCoins[2].setNum(slotCoins[2].getNum() - 1);
                        clickNum++;
                        slotButton52.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(getComponent(0), "امکان دریافت سکه وجود ندارد");
                    }
                    break;
                case -1:
                    if (slotCoins[2].getNum() > 0 && clickNum < 3) {
                        player2.takeCoin(1, "black");
                        slotCoins[2].setNum(slotCoins[2].getNum() - 1);
                        clickNum++;
                        slotButton52.setEnabled(false);
                        slotButton11.setEnabled(false);
                        slotButton21.setEnabled(false);
                        slotButton31.setEnabled(false);
                        slotButton41.setEnabled(false);
                        slotButton51.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(getComponent(0), "امکان دریافت سکه وجود ندارد");
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
        JPanel resPanel = new JPanel(new GridBagLayout()) {
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
        player1res.setOpaque(false);
        resPanel.add(player1res, gbc);

        gbc.gridy = 1;
        JButton returnButton;
        try {
            returnButton = new JButton(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/btns/btn3.png")))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        returnButton.setOpaque(false);
        returnButton.setFocusPainted(false);
        returnButton.setBorderPainted(false);
        returnButton.setContentAreaFilled(false);
        returnButton.addActionListener(e -> switchPanels(mainGamePanel));
        resPanel.add(returnButton, gbc);

        gbc.gridy = 2;
        player2res.setOpaque(false);
        resPanel.add(player2res, gbc);

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

        gbc.gridy = 1;
        JLabel Winner = new JLabel();
        win.add(Winner, gbc);
        Winner.setFont(bigFont);
        Winner.setForeground(Color.BLACK);

        gbc.gridy = 2;
        JButton showBtn = new JButton("نمایش");
        showBtn.setPreferredSize(new Dimension(150, 50));
        showBtn.setFont(normalFont);
        win.add(showBtn, gbc);
        showBtn.addActionListener((ActionEvent e) -> {
            if (player1.getPScore() > player2.getPScore()) {
                Winner.setText("بازیکن 1");
            } else if (player2.getPScore() > player1.getPScore()) {
                Winner.setText("بازیکن 2");
            } else {
                if (player1.getCardNum() < player2.getCardNum()) {
                    Winner.setText("بازیکن 1");
                } else if (player2.getCardNum() < player1.getCardNum()) {
                    Winner.setText("بازیکن 2");
                }
            }
            win.revalidate();
            win.repaint();
        });
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