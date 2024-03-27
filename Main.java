import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    Font customFont = loadFont();
    private Font loadFont() {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File("Vazirmatn-Regular.ttf"));
        } catch (FontFormatException | IOException e) {
            return new JLabel().getFont();
        }
    }
    public Main() {
        setTitle("Amusement Park");
        setSize(1280, 720); // Set frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setPreferredSize(new Dimension(1280, 720));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 20,0);

        JLabel titleLabel = new JLabel("به شهربازی خوش آمدید!",SwingConstants.CENTER);
        titleLabel.setFont(customFont.deriveFont(Font.BOLD, 16));
        welcomePanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        JButton startButton = new JButton("شروع بازی");
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.setFont(customFont.deriveFont(Font.PLAIN, 14));
        startButton.addActionListener(e -> openMainPage());
        welcomePanel.add(startButton, gbc);

        gbc.gridy = 2;
        JButton helpButton = new JButton("راهنما");
        helpButton.setPreferredSize(new Dimension(200, 50));
        helpButton.setFont(customFont.deriveFont(Font.PLAIN, 14));
        helpButton.addActionListener(e -> openGrayPage());
        welcomePanel.add(helpButton, gbc);

        add(welcomePanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    Players player1 = new Players();
    Players player2 = new Players();
    boolean clickable;
    int turn = 1;
    private void openMainPage() {
        getContentPane().removeAll();
        getContentPane().revalidate();
        getContentPane().repaint();

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.RED);
        topPanel.setPreferredSize(new Dimension(1280, 100));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.BLUE);
        bottomPanel.setPreferredSize(new Dimension(1280, 100));

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayout(10,1,0,10));
        sidePanel.setBackground(Color.GREEN);
        sidePanel.setPreferredSize(new Dimension(200, 520));

        JPanel buttonPanel1 = new JPanel();
        JPanel buttonPanel2 = new JPanel();
        JPanel buttonPanel3 = new JPanel();
        JPanel buttonPanel4 = new JPanel();
        JPanel buttonPanel5 = new JPanel();
        JPanel storePanel = new JPanel();

        buttonPanel1.setBackground(Color.GREEN);
        buttonPanel2.setBackground(Color.GREEN);
        buttonPanel3.setBackground(Color.GREEN);
        buttonPanel4.setBackground(Color.GREEN);
        buttonPanel5.setBackground(Color.GREEN);
        storePanel.setBackground(Color.GREEN);

        storePanel.setPreferredSize(new Dimension(400, 50));
        buttonPanel1.setPreferredSize(new Dimension(50, 50));
        buttonPanel2.setPreferredSize(new Dimension(50, 50));
        buttonPanel3.setPreferredSize(new Dimension(50, 50));
        buttonPanel4.setPreferredSize(new Dimension(50, 50));
        buttonPanel5.setPreferredSize(new Dimension(50, 50));

        JButton slotButton1 = new JButton("red slot");
        JButton slotButton2 = new JButton("blue slot");
        JButton slotButton3 = new JButton("green slot");
        JButton slotButton4 = new JButton("white slot");
        JButton slotButton5 = new JButton("black slot");

        slotButton1.addActionListener(e -> {
            if(clickable){
                if(turn==1){
                    player1.takeCoin("green");
                }else{
                    player2.takeCoin("green");
                }
            }
            turn*=-1;
            clickable = false;
        });
        slotButton2.addActionListener(e -> {
            if(clickable){
                if(turn==1){
                    player1.takeCoin("white");
                }else{
                    player2.takeCoin("white");
                }
            }
            turn*=-1;
            clickable = false;
        });
        slotButton3.addActionListener(e -> {
            if(clickable){
                if(turn==1){
                    player1.takeCoin("black");
                }else{
                    player2.takeCoin("black");
                }
            }
            turn*=-1;
            clickable = false;
        });
        slotButton4.addActionListener(e -> {
            if(clickable){
                if(turn==1){
                    player1.takeCoin("blue");
                }else{
                    player2.takeCoin("blue");
                }
            }
            turn*=-1;
            clickable = false;
        });
        slotButton5.addActionListener(e -> {
            if(clickable){
                if(turn==1){
                    player1.takeCoin("red");
                }else{
                    player2.takeCoin("red");
                }
            }
            turn*=-1;
            clickable = false;
        });
        buttonPanel1.add(slotButton1);
        buttonPanel2.add(slotButton2);
        buttonPanel3.add(slotButton3);
        buttonPanel4.add(slotButton4);
        buttonPanel5.add(slotButton5);
        sidePanel.add(buttonPanel1);
        sidePanel.add(buttonPanel2);
        sidePanel.add(buttonPanel3);
        sidePanel.add(buttonPanel4);
        sidePanel.add(buttonPanel5);

        JButton enterButton = new JButton("Store");
        enterButton.setFont(customFont.deriveFont(Font.BOLD, 16));
        enterButton.addActionListener(e -> openGrayPage());
        storePanel.add(enterButton);
        sidePanel.add(storePanel);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.YELLOW);
        centerPanel.setLayout(new BorderLayout());

        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(bottomPanel, BorderLayout.SOUTH);
        centerPanel.add(sidePanel, BorderLayout.EAST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);
        revalidate();
        repaint();
    }
    private void openGrayPage() {
        getContentPane().removeAll();
        getContentPane().revalidate();
        getContentPane().repaint();

        JPanel grayPanel = new JPanel();
        grayPanel.setBackground(Color.GRAY);
        grayPanel.setPreferredSize(new Dimension(1280, 720));


        Coin[] prize1Coins = new Coin[]{new Coin(4,"green"),null,null,null,new Coin(4,"red")};
        Coin[] prize2Coins = new Coin[]{new Coin(5,"green"),null,null,null,new Coin(5,"red")};
        Coin[] prize3Coins = new Coin[]{new Coin(5,"green"),null,null,new Coin(4,"blue"),null};
        Card[] prizeList = {new Card(3,prize1Coins,new JLabel(new ImageIcon("images\\card prize\\prize1.png"))),
                new Card(4,prize2Coins,new JLabel(new ImageIcon("images\\card prize\\prize2.png"))),
                new Card(4,prize3Coins,new JLabel(new ImageIcon("images\\card prize\\prize3.png")))};

        for (int t=0;t<3;t++){
            grayPanel.add(prizeList[t].cardImg);
        }

        Coin[] card11Coins = new Coin[]{new Coin(2,"green"),null,null,null,new Coin(2,"red")};
        Coin[] card12Coins = new Coin[]{new Coin(2,"green"),null,null,null,new Coin(3,"red")};
        Coin[] card13Coins = new Coin[]{new Coin(3,"green"),null,null,null,new Coin(3,"red")};
        Coin[] card14Coins = new Coin[]{null,null,null,new Coin(3,"blue"),new Coin(3,"red")};
        Coin[] card15Coins = new Coin[]{null,null,null,new Coin(3,"blue"),new Coin(2,"red")};
        Coin[] card16Coins = new Coin[]{null,null,null,new Coin(3,"blue"),new Coin(1,"red")};
        Coin[] card17Coins = new Coin[]{null,null,null,new Coin(3,"blue"),new Coin(3,"red")};
        Coin[] card18Coins = new Coin[]{new Coin(1,"green"),null,null,new Coin(3,"blue"),new Coin(2,"red")};
        Coin[] card19Coins = new Coin[]{new Coin(1,"green"),null,null,new Coin(2,"blue"),new Coin(3,"red")};
        Coin[] card110Coins = new Coin[]{new Coin(2,"green"),null,null,new Coin(2,"blue"),null};
        Coin[] card111Coins = new Coin[]{new Coin(2,"green"),null,null,new Coin(2,"blue"),null};
        Coin[] card112Coins = new Coin[]{new Coin(3,"green"),null,null,new Coin(2,"blue"),null};
        Coin[] card113Coins = new Coin[]{new Coin(3,"green"),null,null,new Coin(3,"blue"),null};
        Coin[] card114Coins = new Coin[]{new Coin(3,"green"),null,null,new Coin(3,"blue"),null};
        Coin[] card115Coins = new Coin[]{new Coin(2,"green"),null,null,new Coin(3,"blue"),null};
        Card[] setCard1 = {new Card(1,card11Coins,new JLabel(new ImageIcon("images\\card1\\11.png"))),
                new Card(1,card12Coins,new JLabel(new ImageIcon("images\\card1\\12.png"))),
                new Card(1,card13Coins,new JLabel(new ImageIcon("images\\card1\\13.png"))),
                new Card(0,card14Coins,new JLabel(new ImageIcon("images\\card1\\14.png"))),
                new Card(0,card15Coins,new JLabel(new ImageIcon("images\\card1\\15.png"))),
                new Card(0,card16Coins,new JLabel(new ImageIcon("images\\card1\\16.png"))),
                new Card(1,card17Coins,new JLabel(new ImageIcon("images\\card1\\17.png"))),
                new Card(1,card18Coins,new JLabel(new ImageIcon("images\\card1\\18.png"))),
                new Card(0,card19Coins,new JLabel(new ImageIcon("images\\card1\\19.png"))),
                new Card(0,card110Coins,new JLabel(new ImageIcon("images\\card1\\110.png"))),
                new Card(1,card111Coins,new JLabel(new ImageIcon("images\\card1\\111.png"))),
                new Card(1,card112Coins,new JLabel(new ImageIcon("images\\card1\\112.png"))),
                new Card(1,card113Coins,new JLabel(new ImageIcon("images\\card1\\113.png"))),
                new Card(0,card114Coins,new JLabel(new ImageIcon("images\\card1\\114.png"))),
                new Card(1,card115Coins,new JLabel(new ImageIcon("images\\card1\\115.png")))};
        setCard1[0].SCoins[3] = new specialCoin(1,"blue");
        setCard1[1].SCoins[3] = new specialCoin(1,"blue");
        setCard1[2].SCoins[3] = new specialCoin(1,"blue");
        setCard1[3].SCoins[0] = new specialCoin(1,"green");
        setCard1[4].SCoins[0] = new specialCoin(1,"green");
        setCard1[5].SCoins[0] = new specialCoin(1,"green");
        setCard1[6].SCoins[0] = new specialCoin(1,"green");
        setCard1[7].SCoins[0] = new specialCoin(1,"green");
        setCard1[8].SCoins[0] = new specialCoin(1,"green");
        setCard1[9].SCoins[4] = new specialCoin(1,"red");
        setCard1[10].SCoins[4] = new specialCoin(1,"red");
        setCard1[11].SCoins[4] = new specialCoin(1,"red");
        setCard1[12].SCoins[4] = new specialCoin(1,"red");
        setCard1[13].SCoins[4] = new specialCoin(1,"red");
        setCard1[14].SCoins[4] = new specialCoin(1,"red");

        Coin[] card21Coins = new Coin[]{null,new Coin(2,"white"),new Coin(2,"black"),new Coin(2,"blue"),null};
        Coin[] card22Coins = new Coin[]{null,new Coin(2,"white"),new Coin(2,"black"),new Coin(3,"blue"),null};
        Coin[] card23Coins = new Coin[]{null,new Coin(2,"white"),new Coin(3,"black"),new Coin(3,"blue"),null};
        Coin[] card24Coins = new Coin[]{null,new Coin(4,"white"),new Coin(2,"black"),new Coin(3,"blue"),null};
        Coin[] card25Coins = new Coin[]{null,new Coin(2,"white"),new Coin(2,"black"),new Coin(2,"blue"),new Coin(2,"red")};
        Coin[] card26Coins = new Coin[]{null,null,new Coin(3,"black"),new Coin(3,"blue"),new Coin(2,"red")};
        Coin[] card27Coins = new Coin[]{null,null,new Coin(2,"black"),new Coin(3,"blue"),new Coin(3,"red")};
        Coin[] card28Coins = new Coin[]{null,null,new Coin(2,"black"),new Coin(3,"blue"),new Coin(2,"red")};
        Coin[] card29Coins = new Coin[]{null,null,new Coin(2,"black"),new Coin(2,"blue"),new Coin(2,"red")};
        Coin[] card210Coins = new Coin[]{null,null,new Coin(3,"black"),new Coin(2,"blue"),new Coin(1,"red")};
        Coin[] card211Coins = new Coin[]{null, null, new Coin(3,"black"), new Coin(3,"blue"), new Coin(2,"red")};
        Coin[] card212Coins = new Coin[]{new Coin(1,"green"), null, new Coin(2,"black"), new Coin(3,"blue"), new Coin(2,"red")};
        Coin[] card213Coins = new Coin[]{new Coin(2,"green"), null, new Coin(2,"black"), null, new Coin(2,"red")};
        Coin[] card214Coins = new Coin[]{new Coin(1,"green"), null, new Coin(2,"black"), null, new Coin(3,"red")};
        Coin[] card215Coins = new Coin[]{new Coin(1,"green"), null, new Coin(3,"black"), null, new Coin(3,"red")};
        Card[] setCard2 = {new Card(2,card21Coins,new JLabel(new ImageIcon("images\\card prize\\21.png"))),
                new Card(2,card22Coins,new JLabel(new ImageIcon("images\\card2\\22.png"))),
                new Card(2,card23Coins,new JLabel(new ImageIcon("images\\card2\\23.png"))),
                new Card(2,card24Coins,new JLabel(new ImageIcon("images\\card2\\24.png"))),
                new Card(2,card25Coins,new JLabel(new ImageIcon("images\\card2\\25.png"))),
                new Card(3,card26Coins,new JLabel(new ImageIcon("images\\card2\\26.png"))),
                new Card(3,card27Coins,new JLabel(new ImageIcon("images\\card2\\27.png"))),
                new Card(3,card28Coins,new JLabel(new ImageIcon("images\\card2\\28.png"))),
                new Card(3,card29Coins,new JLabel(new ImageIcon("images\\card2\\29.png"))),
                new Card(3,card210Coins,new JLabel(new ImageIcon("images\\card2\\210.png"))),
                new Card(4,card211Coins,new JLabel(new ImageIcon("images\\card2\\211.png"))),
                new Card(4,card212Coins,new JLabel(new ImageIcon("images\\card2\\212.png"))),
                new Card(4,card213Coins,new JLabel(new ImageIcon("images\\card2\\213.png"))),
                new Card(4,card214Coins,new JLabel(new ImageIcon("images\\card2\\214.png"))),
                new Card(4,card215Coins,new JLabel(new ImageIcon("images\\card2\\215.png")))};
        setCard2[0].SCoins[3] = new specialCoin(1,"blue");
        setCard2[1].SCoins[3] = new specialCoin(1,"blue");
        setCard2[2].SCoins[3] = new specialCoin(1,"blue");
        setCard2[3].SCoins[3] = new specialCoin(1,"blue");
        setCard2[4].SCoins[3] = new specialCoin(1,"blue");
        setCard2[5].SCoins[3] = new specialCoin(1,"blue");
        setCard2[6].SCoins[3] = new specialCoin(1,"blue");
        setCard2[7].SCoins[3] = new specialCoin(1,"blue");
        setCard2[8].SCoins[3] = new specialCoin(1,"blue");
        setCard2[9].SCoins[0] = new specialCoin(1,"green");
        setCard2[10].SCoins[0] = new specialCoin(1,"green");
        setCard2[11].SCoins[0] = new specialCoin(1,"green");
        setCard2[12].SCoins[0] = new specialCoin(1,"green");
        setCard2[13].SCoins[0] = new specialCoin(1,"green");
        setCard2[14].SCoins[0] = new specialCoin(1,"green");

        Coin[] card31Coins = new Coin[]{new Coin(3,"green"), null, new Coin(3,"black"), null, new Coin(4,"red")};
        Coin[] card32Coins = new Coin[]{new Coin(2,"green"), null, new Coin(3,"black"), null, new Coin(4,"red")};
        Coin[] card33Coins = new Coin[]{new Coin(2,"green"), null, new Coin(3,"black"), null, new Coin(3,"red")};
        Coin[] card34Coins = new Coin[]{new Coin(2,"green"), null, new Coin(2,"black"), null, new Coin(3,"red")};
        Coin[] card35Coins = new Coin[]{null, null, new Coin(2,"black"), new Coin(2,"blue"), new Coin(3,"red")};
        Coin[] card36Coins = new Coin[]{null, null, new Coin(2,"black"), new Coin(3,"blue"), new Coin(3,"red")};
        Coin[] card37Coins = new Coin[]{null, null, new Coin(3,"black"), new Coin(3,"blue"), new Coin(3,"red")};
        Coin[] card38Coins = new Coin[]{new Coin(1,"green"), null, new Coin(3,"black"), new Coin(3,"blue"), new Coin(3,"red")};
        Coin[] card39Coins = new Coin[]{new Coin(2,"green"), null, new Coin(2,"black"), new Coin(3,"blue"), new Coin(3,"red")};
        Coin[] card310Coins = new Coin[]{new Coin(2,"green"), null, new Coin(2,"black"), new Coin(3,"blue"), new Coin(1,"red")};
        Coin[] card311Coins = new Coin[]{new Coin(2,"green"), null, new Coin(2,"black"), new Coin(2,"blue"), new Coin(1,"red")};
        Coin[] card312Coins = new Coin[]{new Coin(3,"green"), null, new Coin(2,"black"), new Coin(1,"blue"), new Coin(1,"red")};
        Coin[] card313Coins = new Coin[]{new Coin(3,"green"), null, new Coin(2,"black"), new Coin(1,"blue"), new Coin(1,"red")};
        Coin[] card314Coins = new Coin[]{new Coin(2,"green"), null, new Coin(2,"black"), new Coin(1,"blue"), new Coin(2,"red")};
        Coin[] card315Coins = new Coin[]{new Coin(2,"green"), null, new Coin(3,"black"), new Coin(1,"blue"), new Coin(2,"red")};
        Card[] setCard3 = {new Card(3,card31Coins,new JLabel(new ImageIcon("images\\card prize\\31.png"))),
                new Card(4,card32Coins,new JLabel(new ImageIcon("images\\card3\\32.png"))),
                new Card(5,card33Coins,new JLabel(new ImageIcon("images\\card3\\33.png"))),
                new Card(3,card34Coins,new JLabel(new ImageIcon("images\\card3\\34.png"))),
                new Card(3,card35Coins,new JLabel(new ImageIcon("images\\card3\\35.png"))),
                new Card(4,card36Coins,new JLabel(new ImageIcon("images\\card3\\36.png"))),
                new Card(5,card37Coins,new JLabel(new ImageIcon("images\\card3\\37.png"))),
                new Card(5,card38Coins,new JLabel(new ImageIcon("images\\card3\\38.png"))),
                new Card(5,card39Coins,new JLabel(new ImageIcon("images\\card3\\39.png"))),
                new Card(4,card310Coins,new JLabel(new ImageIcon("images\\card3\\310.png"))),
                new Card(4,card311Coins,new JLabel(new ImageIcon("images\\card3\\311.png"))),
                new Card(3,card312Coins,new JLabel(new ImageIcon("images\\card3\\312.png"))),
                new Card(3,card313Coins,new JLabel(new ImageIcon("images\\card3\\313.png"))),
                new Card(3,card314Coins,new JLabel(new ImageIcon("images\\card3\\314.png"))),
                new Card(3,card315Coins,new JLabel(new ImageIcon("images\\card3\\315.png")))};
        setCard3[0].SCoins[1] = new specialCoin(1,"white");
        setCard3[1].SCoins[1] = new specialCoin(1,"white");
        setCard3[2].SCoins[1] = new specialCoin(1,"white");
        setCard3[3].SCoins[0] = new specialCoin(1,"green");
        setCard3[4].SCoins[0] = new specialCoin(1,"green");
        setCard3[5].SCoins[0] = new specialCoin(1,"green");
        setCard3[6].SCoins[0] = new specialCoin(1,"green");
        setCard3[7].SCoins[1] = new specialCoin(1,"white");
        setCard3[8].SCoins[1] = new specialCoin(1,"white");
        setCard3[9].SCoins[1] = new specialCoin(1,"white");
        setCard3[10].SCoins[1] = new specialCoin(1,"white");
        setCard3[11].SCoins[1] = new specialCoin(1,"white");
        setCard3[12].SCoins[3] = new specialCoin(1,"blue");
        setCard3[13].SCoins[3] = new specialCoin(1,"blue");
        setCard3[14].SCoins[3] = new specialCoin(1,"blue");

        for(int t=0;t<4;t++){
            grayPanel.add(setCard1[t].cardImg);
            grayPanel.add(setCard2[t].cardImg);
            grayPanel.add(setCard3[t].cardImg);
        }

        add(grayPanel);
        revalidate();
        repaint();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
