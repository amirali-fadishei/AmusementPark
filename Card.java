import javax.swing.*;

public class Card {
    public Coin[] coinList = new Coin[5];
    public specialCoin[] SCoins = new specialCoin[5];
    private int Score;
    public JLabel cardImg;
    private boolean availability = true;

    public Card(int score, Coin[] coins, specialCoin[] sCoins, JLabel img) {
        this.setScore(score);
        this.coinList = coins;
        this.cardImg = img;
        this.SCoins = sCoins;
    }

    public void setScore(int num) {
        this.Score = num;
    }

    public int getScore() {
        return this.Score;
    }

    public void setAvailability(boolean type) {
        this.availability = type;
    }

    public boolean getAvailability() {
        return this.availability;
    }
}