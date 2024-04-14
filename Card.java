import javax.swing.*;

public class Card {
    private Coin[] coinList = new Coin[5];
    private specialCoin[] cardSCoins = new specialCoin[5];
    private int Score;
    private JLabel cardImg;
    private boolean availability = true;

    public Card(int score, Coin[] coins, specialCoin[] sCoinList, JLabel img) {
        this.setScore(score);
        this.setCoinList(coins);
        this.setCardImg(img);
        this.setSCoins(sCoinList);
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

    public void setCardImg(JLabel image) {
        this.cardImg = image;
    }

    public JLabel getCardImg() {
        return this.cardImg;
    }

    public void setCoinList(Coin[] coinList) {
        this.coinList = coinList;
    }

    public Coin[] getCoinList() {
        return this.coinList;
    }

    public void setSCoins(specialCoin[] sCoinList) {
        this.cardSCoins = sCoinList;
    }

    public specialCoin[] getCardSCoins() {
        return this.cardSCoins;
    }
}