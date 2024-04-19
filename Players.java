import javax.swing.*;

public class Players {
    private int pScore;
    private int reserveCount;
    private int cardNum;
    private int playerCoinNum;
    private Coin[] playerCoin;
    private specialCoin[] playerSCoin;
    public Card[] playerCard = new Card[48];
    public Card[] reserveCard = new Card[3];

    // Constructor
    public Players(Coin[] playerCoin, specialCoin[] playerSCoin) {
        this.setPScore(0);
        this.setReserveCount(0);
        this.setCardNum(0);
        this.setPlayerCoinNum(0);
        this.setPlayerCoin(playerCoin);
        this.setPlayerSCoin(playerSCoin);
    }

    // Setter methods
    public void setPScore(int pScore) {
        this.pScore = pScore;
    }

    public void setReserveCount(int reserveCount) {
        this.reserveCount = reserveCount;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    public void setPlayerCoinNum(int playerCoinNum) {
        this.playerCoinNum = playerCoinNum;
    }

    public void setPlayerCoin(Coin[] playerCoin) {
        this.playerCoin = playerCoin;
    }

    public void setPlayerSCoin(specialCoin[] playerSCoin) {
        this.playerSCoin = playerSCoin;
    }


    // Getter methods
    public int getPScore() {
        return pScore;
    }

    public int getCardNum() {
        return cardNum;
    }

    public Coin[] getPlayerCoin() {
        return playerCoin;
    }

    public specialCoin[] getPlayerSCoin() {
        return playerSCoin;
    }


    public void takeCoin(int count, String color) {
        switch (color) {
            case "green" -> this.playerCoin[0].setNum(this.playerCoin[0].getNum() + count);
            case "white" -> this.playerCoin[1].setNum(this.playerCoin[1].getNum() + count);
            case "black" -> this.playerCoin[2].setNum(this.playerCoin[2].getNum() + count);
            case "blue" -> this.playerCoin[3].setNum(this.playerCoin[3].getNum() + count);
            case "red" -> this.playerCoin[4].setNum(this.playerCoin[4].getNum() + count);
            default -> {
            }
        }
    }

    public int coinCounter() {
        this.playerCoinNum = 0;
        for (int t = 0; t < 6; t++) {
            this.playerCoinNum += this.playerCoin[t].getNum();
        }
        return this.playerCoinNum;
    }

    public void buy(Card card, Players player, Coin[] coin) {
        boolean sw = true;
        for (int i = 0; i < 5; i++) {
            if (card.getCoinList()[i] != null) {
                if ((player.playerCoin[i].getNum() + player.playerSCoin[i].getsNum()) < card.getCoinList()[i].getNum()) {
                    if ((player.playerCoin[5].getNum() + player.playerCoin[i].getNum() + player.playerSCoin[i].getsNum()) < card.getCoinList()[i].getNum()) {
                        sw = false;
                        break;
                    }
                }
            }
        }
        if (sw) {
            for (int i = 0; i < 5; i++) {
                if (card.getCoinList()[i] != null) {
                    if ((player.playerCoin[i].getNum() + player.playerSCoin[i].getsNum()) >= card.getCoinList()[i].getNum()) {
                        card.getCoinList()[i].setNum(card.getCoinList()[i].getNum() - player.playerSCoin[i].getsNum());
                        player.playerCoin[i].setNum(player.playerCoin[i].getNum() - card.getCoinList()[i].getNum());
                        coin[i].setNum(coin[i].getNum() + card.getCoinList()[i].getNum());
                    } else {
                        card.getCoinList()[i].setNum(card.getCoinList()[i].getNum() - player.playerSCoin[i].getsNum());
                        coin[i].setNum(coin[i].getNum() + card.getCoinList()[i].getNum());
                        card.getCoinList()[i].setNum(card.getCoinList()[i].getNum() - player.playerCoin[i].getNum());
                        player.playerCoin[i].setNum(0);
                        player.playerCoin[5].setNum(player.playerCoin[5].getNum() - card.getCoinList()[i].getNum());
                        coin[5].setNum(coin[5].getNum() + card.getCoinList()[i].getNum());
                    }
                }
                if (card.getCardSCoins()[i] != null) {
                    player.playerSCoin[i].setsNum(player.playerSCoin[i].getsNum() + card.getCardSCoins()[i].getsNum());
                }
            }
            player.pScore += card.getScore();
            player.cardNum += 1;
            for (int j = 0; j < 45; j++) {
                if (player.playerCard[j] == null) {
                    player.playerCard[j] = card;
                    card.setAvailability(false);
                    break;
                }
            }
        }
    }

    public void buyPrize(Card card, Players player) {
        boolean sw = true;
        for (int i = 0; i < 5; i++) {
            if (card.getCardSCoins()[i] != null) {
                if (!card.getCardSCoins()[i].getType().equals(player.playerSCoin[i].getType()) || player.playerSCoin[i].getsNum() < card.getCardSCoins()[i].getsNum()) {
                    sw = false;
                    break;
                }
            }
        }
        if (sw) {
            player.pScore += card.getScore();
            for (int j = 0; j < 48; j++) {
                if (player.playerCard[j] == null) {
                    player.playerCard[j] = card;
                    card.setAvailability(false);
                    break;
                }
            }
        }
    }

    public void reserve(Card card, Players player, Coin[] coin) {
        if (player.reserveCount < 3 && card.getAvailability()) {
            player.reserveCount++;
            if (coin[5].getNum() != 0) {
                coin[5].setNum(coin[5].getNum() - 1);
                player.playerCoin[5].setNum(player.playerCoin[5].getNum() + 1);
            }
            for (int t = 0; t < player.reserveCard.length; t++) {
                if (player.reserveCard[t] == null) {
                    player.reserveCard[t] = card;
                    card.setAvailability(false);
                    break;
                }
            }
        }
    }

    public void buyReserve(Card card, Players player, Coin[] coin) {
        player.buy(card, player, coin);
    }
}