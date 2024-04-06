public class Players {
    public int pScore;
    public int reserveCount = 0;
    public Coin[] playerCoin = new Coin[6];
    public int playerCoinNum;
    public specialCoin[] playerSCoin = new specialCoin[5];
    public Card[] playerCard = new Card[48];
    public Card[] reservCard = new Card[3];

    public void takeCoin(int count, String color) {
        switch (color) {
            case "green" -> this.playerCoin[0].num += count;
            case "white" -> this.playerCoin[1].num += count;
            case "black" -> this.playerCoin[2].num += count;
            case "blue" -> this.playerCoin[3].num += count;
            case "red" -> this.playerCoin[4].num += count;
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
            if (card.coinList[i] != null) {
                if ((player.playerCoin[i].getNum() + player.playerSCoin[i].getsNum()) < card.coinList[i].getNum()) {
                    if ((player.playerCoin[5].getNum() + player.playerCoin[i].getNum() + player.playerSCoin[i].getsNum()) < card.coinList[i].getNum()) {
                        sw = false;
                        break;
                    }
                }
            }
        }
        if (sw) {
            for (int i = 0; i < 5; i++) {
                if (card.coinList[i] != null) {
                    coin[i].num += card.coinList[i].num;
                    int remain = card.coinList[i].num - (player.playerCoin[i].num + player.playerSCoin[i].sNum);
                    card.coinList[i].num -= remain;
                    player.playerCoin[5].num -= remain;
                    coin[5].num += remain;
                    card.coinList[i].num -= player.playerSCoin[i].sNum;
                    player.playerCoin[i].num -= card.coinList[i].num;
                }
                if (card.SCoins[i] != null && player.playerSCoin[i] != null) {
                    player.playerSCoin[i].sNum += card.SCoins[i].sNum;
                }
            }
            player.pScore += card.getScore();
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
            if (card.SCoins[i] != null) {
                if (!card.SCoins[i].getType().equals(player.playerSCoin[i].getType()) || player.playerSCoin[i].getsNum() < card.SCoins[i].getsNum()) {
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
                coin[5].num--;
                player.playerCoin[5].num++;
            }
            for (int t = 0; t < player.reservCard.length; t++) {
                if (player.reservCard[t] == null) {
                    player.reservCard[t] = card;
                    card.setAvailability(false);
                    break;
                }
            }
        }
    }
}