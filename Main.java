import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    Font customFont = loadFont("Vazirmatn-Regular.ttf");

    public Main() {
        setTitle("Amusement Park");
        setSize(1280, 720); // Set frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setPreferredSize(new Dimension(1280, 720)); // Set panel size
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 20, 20, 20);

        JLabel titleLabel = new JLabel("به شهربازی خوش آمدید!");
        titleLabel.setFont(customFont.deriveFont(Font.BOLD, 16)); // Set font
        welcomePanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        JButton startButton = new JButton("شروع بازی");
        startButton.setPreferredSize(new Dimension(200, 50)); // Set button size
        startButton.setFont(customFont.deriveFont(Font.PLAIN, 14)); // Set font
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMainPage();
            }
        });
        welcomePanel.add(startButton, gbc);

        gbc.gridy = 2;
        JButton helpButton = new JButton("راهنما");
        helpButton.setPreferredSize(new Dimension(200, 50)); // Set button size
        helpButton.setFont(customFont.deriveFont(Font.PLAIN, 14)); // Set font
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openGrayPage();
            }
        });
        welcomePanel.add(helpButton, gbc);

        add(welcomePanel);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private Font loadFont(String path) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return new JLabel().getFont(); // Return default font if loading fails
        }
    }
    boolean clickable;
    private void openMainPage() {
        getContentPane().removeAll();
        getContentPane().revalidate();
        getContentPane().repaint();

        Players player1 = new Players();
        Players player2 = new Players();
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.RED);
        topPanel.setPreferredSize(new Dimension(1280, 100)); // ارتفاع بخش بالایی

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.BLUE);
        bottomPanel.setPreferredSize(new Dimension(1280, 100)); // ارتفاع بخش پایینی

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayout(10,1,0,10));
        sidePanel.setBackground(Color.GREEN);
        sidePanel.setPreferredSize(new Dimension(200, 520)); // عرض بخش کناری
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
        storePanel.setPreferredSize(new Dimension(400, 100));
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

        slotButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(clickable){
                    player1.takeCoin("green");
                }
                clickable = false;
            }
        });
        slotButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(clickable){
                    player1.takeCoin("white");
                }
                clickable = false;
            }
        });
        slotButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(clickable){
                    player1.takeCoin("black");
                }
                clickable = false;
            }
        });
        slotButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(clickable){
                    player1.takeCoin("blue");
                }
                clickable = false;
            }
        });
        slotButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(clickable){
                    player1.takeCoin("red");
                }
                clickable = false;
            }
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
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openGrayPage();
            }
        });
        storePanel.add(enterButton);
        sidePanel.add(Box.createVerticalStrut(10));
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
        grayPanel.setPreferredSize(new Dimension(1280, 720)); // Set panel size

        add(grayPanel);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
