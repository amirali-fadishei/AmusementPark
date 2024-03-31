import javax.swing.*;

public class Card {
    public Coin[] coinList = new Coin[6];
    public specialCoin[] SCoins = new specialCoin[5];
    private int Score;
    public JLabel cardImg;

    public Card(int score, Coin[] coins, JLabel img) {
        this.setScore(score);
        this.coinList = coins;
        this.cardImg = img;
    }

    public void setScore(int num) {
        this.Score = num;
    }

    public int getScore() {
        return this.Score;
    }
}