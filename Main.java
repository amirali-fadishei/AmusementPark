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


        Card prize1 = new Card(3);
        Card prize2 = new Card(4);
        Card prize3 = new Card(4);
        Card card11 = new Card(1);
        Card card12 = new Card(1);
        Card card13 = new Card(1);
        Card card14 = new Card(0);
        Card card15 = new Card(0);
        Card card16 = new Card(0);
        Card card17 = new Card(1);
        Card card18 = new Card(1);
        Card card19 = new Card(0);
        Card card110 = new Card(0);
        Card card111 = new Card(1);
        Card card112 = new Card(1);
        Card card113 = new Card(1);
        Card card114 = new Card(0);
        Card card115 = new Card(1);
        Card card21 = new Card(2);
        Card card22 = new Card(2);
        Card card23 = new Card(2);
        Card card24 = new Card(2);
        Card card25 = new Card(2);
        Card card26 = new Card(3);
        Card card27 = new Card(3);
        Card card28 = new Card(3);
        Card card29 = new Card(3);
        Card card210 = new Card(3);
        Card card211 = new Card(4);
        Card card212 = new Card(4);
        Card card213 = new Card(4);
        Card card214 = new Card(4);
        Card card215 = new Card(4);
        Card card31 = new Card(3);
        Card card32 = new Card(4);
        Card card33 = new Card(5);
        Card card34 = new Card(3);
        Card card35 = new Card(3);
        Card card36 = new Card(4);
        Card card37 = new Card(5);
        Card card38 = new Card(5);
        Card card39 = new Card(5);
        Card card310 = new Card(4);
        Card card311 = new Card(4);
        Card card312 = new Card(3);
        Card card313 = new Card(3);
        Card card314 = new Card(3);
        Card card315 = new Card(3);

        prize1.coinList[0] = new Coin(4,"green");
        prize1.coinList[4] = new Coin(4,"red");
        prize2.coinList[0] = new Coin(5,"green");
        prize2.coinList[4] = new Coin(5,"red");
        prize3.coinList[0] = new Coin(5,"green");
        prize3.coinList[3] = new Coin(4,"blue");
        card11.SCoins[3] = new specialCoin(1,"blue");
        card11.coinList[0] = new Coin(2,"green");
        card11.coinList[4] = new Coin(2,"red");
        card12.SCoins[3] = new specialCoin(1,"blue");
        card12.coinList[0] = new Coin(2,"green");
        card12.coinList[4] = new Coin(3,"red");
        card13.SCoins[3] = new specialCoin(1,"blue");
        card13.coinList[0] = new Coin(3,"green");
        card13.coinList[4] = new Coin(3,"red");
        card14.SCoins[0] = new specialCoin(1,"green");
        card14.coinList[3] = new Coin(3,"blue");
        card14.coinList[4] = new Coin(3,"red");
        card15.SCoins[0] = new specialCoin(1,"green");
        card15.coinList[3] = new Coin(3,"blue");
        card15.coinList[4] = new Coin(2,"red");
        card16.SCoins[0] = new specialCoin(1,"green");
        card16.coinList[3] = new Coin(3,"blue");
        card16.coinList[4] = new Coin(1,"red");
        card17.SCoins[0] = new specialCoin(1,"green");
        card17.coinList[3] = new Coin(3,"blue");
        card17.coinList[4] = new Coin(3,"red");
        card18.SCoins[0] = new specialCoin(1,"green");
        card18.coinList[0] = new Coin(1,"green");
        card18.coinList[3] = new Coin(3,"blue");
        card18.coinList[4] = new Coin(2,"red");
        card19.SCoins[0] = new specialCoin(1,"green");
        card19.coinList[0] = new Coin(1,"green");
        card19.coinList[3] = new Coin(2,"blue");
        card19.coinList[4] = new Coin(3,"red");
        card110.SCoins[4] = new specialCoin(1,"red");
        card110.coinList[0] = new Coin(2,"green");
        card110.coinList[3] = new Coin(2,"blue");
        card111.SCoins[4] = new specialCoin(1,"red");
        card111.coinList[0] = new Coin(2,"green");
        card111.coinList[3] = new Coin(2,"blue");
        card112.SCoins[4] = new specialCoin(1,"red");
        card112.coinList[0] = new Coin(3,"green");
        card112.coinList[3] = new Coin(2,"blue");
        card113.SCoins[4] = new specialCoin(1,"red");
        card113.coinList[0] = new Coin(3,"green");
        card113.coinList[3] = new Coin(3,"blue");
        card114.SCoins[4] = new specialCoin(1,"red");
        card114.coinList[0] = new Coin(3,"green");
        card114.coinList[3] = new Coin(3,"blue");
        card115.SCoins[4] = new specialCoin(1,"red");
        card115.coinList[0] = new Coin(2,"green");
        card115.coinList[3] = new Coin(3,"blue");
        card21.SCoins[3] = new specialCoin(1,"blue");
        card21.coinList[3] = new Coin(2,"blue");
        card21.coinList[2] = new Coin(2,"black");
        card21.coinList[1] = new Coin(2,"white");
        card22.SCoins[3] = new specialCoin(1,"blue");
        card22.coinList[3] = new Coin(3,"blue");
        card22.coinList[2] = new Coin(2,"black");
        card22.coinList[1] = new Coin(2,"white");
        card23.SCoins[3] = new specialCoin(1,"blue");
        card23.coinList[3] = new Coin(3,"blue");
        card23.coinList[2] = new Coin(3,"black");
        card23.coinList[1] = new Coin(2,"white");
        card24.SCoins[3] = new specialCoin(1,"blue");
        card24.coinList[3] = new Coin(2,"blue");
        card24.coinList[2] = new Coin(2,"black");
        card24.coinList[1] = new Coin(4,"white");
        card25.SCoins[3] = new specialCoin(1,"blue");
        card25.coinList[3] = new Coin(2,"blue");
        card25.coinList[2] = new Coin(2,"black");
        card25.coinList[1] = new Coin(2,"white");
        card25.coinList[4] = new Coin(2,"red");
        card26.SCoins[3] = new specialCoin(1,"blue");
        card26.coinList[3] = new Coin(3,"blue");
        card26.coinList[2] = new Coin(3,"black");
        card26.coinList[4] = new Coin(2,"red");
        card27.SCoins[3] = new specialCoin(1,"blue");
        card27.coinList[3] = new Coin(3,"blue");
        card27.coinList[2] = new Coin(2,"black");
        card27.coinList[4] = new Coin(3,"red");
        card28.SCoins[3] = new specialCoin(1,"blue");
        card28.coinList[3] = new Coin(3,"blue");
        card28.coinList[2] = new Coin(2,"black");
        card28.coinList[4] = new Coin(2,"red");
        card29.SCoins[3] = new specialCoin(1,"blue");
        card29.coinList[3] = new Coin(2,"blue");
        card29.coinList[2] = new Coin(2,"black");
        card29.coinList[4] = new Coin(2,"red");
        card210.SCoins[0] = new specialCoin(1,"green");
        card210.coinList[3] = new Coin(4,"blue");
        card210.coinList[2] = new Coin(3,"black");
        card210.coinList[4] = new Coin(1,"red");
        card211.SCoins[0] = new specialCoin(1,"green");
        card211.coinList[3] = new Coin(3,"blue");
        card211.coinList[2] = new Coin(3,"black");
        card211.coinList[4] = new Coin(2,"red");
        card212.SCoins[0] = new specialCoin(1,"green");
        card212.coinList[3] = new Coin(3,"blue");
        card212.coinList[2] = new Coin(2,"black");
        card212.coinList[4] = new Coin(2,"red");
        card212.coinList[0] = new Coin(1,"green");
        card213.SCoins[0] = new specialCoin(1,"green");
        card213.coinList[2] = new Coin(2,"black");
        card213.coinList[4] = new Coin(2,"red");
        card213.coinList[0] = new Coin(2,"green");
        card214.SCoins[0] = new specialCoin(1,"green");
        card214.coinList[2] = new Coin(2,"black");
        card214.coinList[4] = new Coin(3,"red");
        card214.coinList[0] = new Coin(1,"green");
        card215.SCoins[0] = new specialCoin(1,"green");
        card215.coinList[2] = new Coin(3,"black");
        card215.coinList[4] = new Coin(3,"red");
        card215.coinList[0] = new Coin(1,"green");
        card31.SCoins[1] = new specialCoin(1,"white");
        card31.coinList[0] = new Coin(3,"green");
        card31.coinList[4] = new Coin(4,"red");
        card31.coinList[2] = new Coin(3,"black");
        card32.SCoins[1] = new specialCoin(1,"white");
        card32.coinList[0] = new Coin(2,"green");
        card32.coinList[4] = new Coin(4,"red");
        card32.coinList[2] = new Coin(3,"black");
        card33.SCoins[1] = new specialCoin(1,"white");
        card33.coinList[0] = new Coin(2,"green");
        card33.coinList[4] = new Coin(3,"red");
        card33.coinList[2] = new Coin(3,"black");
        card34.SCoins[0] = new specialCoin(1,"green");
        card34.coinList[0] = new Coin(2,"green");
        card34.coinList[4] = new Coin(3,"red");
        card34.coinList[2] = new Coin(2,"black");
        card35.SCoins[0] = new specialCoin(1,"green");
        card35.coinList[3] = new Coin(2,"blue");
        card35.coinList[4] = new Coin(3,"red");
        card35.coinList[2] = new Coin(2,"black");
        card36.SCoins[0] = new specialCoin(1,"green");
        card36.coinList[3] = new Coin(3,"blue");
        card36.coinList[4] = new Coin(3,"red");
        card36.coinList[2] = new Coin(2,"black");
        card37.SCoins[0] = new specialCoin(1,"green");
        card37.coinList[3] = new Coin(3,"blue");
        card37.coinList[4] = new Coin(3,"red");
        card37.coinList[2] = new Coin(3,"black");
        card38.SCoins[1] = new specialCoin(1,"white");
        card38.coinList[3] = new Coin(3,"blue");
        card38.coinList[4] = new Coin(3,"red");
        card38.coinList[2] = new Coin(3,"black");
        card38.coinList[0] = new Coin(1,"green");
        card39.SCoins[1] = new specialCoin(1,"white");
        card39.coinList[3] = new Coin(3,"blue");
        card39.coinList[4] = new Coin(3,"red");
        card39.coinList[2] = new Coin(2,"black");
        card39.coinList[0] = new Coin(2,"green");
        card310.SCoins[1] = new specialCoin(1,"white");
        card310.coinList[3] = new Coin(3,"blue");
        card310.coinList[4] = new Coin(1,"red");
        card310.coinList[2] = new Coin(2,"black");
        card310.coinList[0] = new Coin(2,"green");
        card311.SCoins[1] = new specialCoin(1,"white");
        card311.coinList[3] = new Coin(2,"blue");
        card311.coinList[4] = new Coin(1,"red");
        card311.coinList[2] = new Coin(2,"black");
        card311.coinList[0] = new Coin(2,"green");
        card312.SCoins[1] = new specialCoin(1,"white");
        card312.coinList[3] = new Coin(1,"blue");
        card312.coinList[4] = new Coin(1,"red");
        card312.coinList[2] = new Coin(2,"black");
        card312.coinList[0] = new Coin(3,"green");
        card313.SCoins[3] = new specialCoin(1,"blue");
        card313.coinList[3] = new Coin(1,"blue");
        card313.coinList[4] = new Coin(1,"red");
        card313.coinList[2] = new Coin(2,"black");
        card313.coinList[0] = new Coin(3,"green");
        card314.SCoins[3] = new specialCoin(1,"blue");
        card314.coinList[3] = new Coin(1,"blue");
        card314.coinList[4] = new Coin(2,"red");
        card314.coinList[2] = new Coin(2,"black");
        card314.coinList[0] = new Coin(2,"green");
        card315.SCoins[3] = new specialCoin(1,"blue");
        card315.coinList[3] = new Coin(1,"blue");
        card315.coinList[4] = new Coin(2,"red");
        card315.coinList[2] = new Coin(3,"black");
        card315.coinList[0] = new Coin(2,"green");

        add(grayPanel);
        revalidate();
        repaint();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
