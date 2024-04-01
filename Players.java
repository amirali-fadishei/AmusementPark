public class Players {
    public int pScore;
    public Coin[] playerCoin = new Coin[6];
    public specialCoin[] playerSCoin = new specialCoin[5];
    public Card[] playerCard = new Card[48];

    public void buy(Card card) {
        boolean sw = true;
        for (int i = 0; i < 5; i++) {
            if (card.coinList[i] != null) {
                if (!card.coinList[i].getType().equals(this.playerCoin[i].getType()) || this.playerCoin[i].num < card.coinList[i].num) {
                    sw = false;
                    break;
                }
            }
        }
        if (sw) {
            for (int i = 0; i < 5; i++) {
                if (card.coinList[i] != null) {
                    if (this.playerSCoin[i] != null) {
                        card.coinList[i].num -= this.playerSCoin[i].num;
                    }
                    this.playerCoin[i].num -= card.coinList[i].num;
                }
                if (card.SCoins[i] != null && this.playerSCoin[i] != null) {
                    this.playerSCoin[i].num += card.SCoins[i].num;
                }
            }
            this.pScore += card.getScore();
            for (int j = 0; j < 45; j++) {
                if (this.playerCard[j] == null) {
                    this.playerCard[j] = card;
                    card.setAvailability(false);
                    break;
                }
            }
        }
    }

    public void buyPrize(Card card) {
        boolean sw = true;
        for (int i = 0; i < 5; i++) {
            if (card.SCoins[i] != null) {
                if (!card.SCoins[i].getType().equals(this.playerSCoin[i].getType()) || this.playerSCoin[i].num < card.SCoins[i].num) {
                    sw = false;
                    break;
                }
            }
        }
        if (sw) {
            for (int i = 0; i < 5; i++) {
                if (card.SCoins[i] != null) {
                    this.playerSCoin[i].num -= card.SCoins[i].num;
                }
            }
            this.pScore += card.getScore();
            for (int j = 0; j < 48; j++) {
                if (this.playerCard[j] == null) {
                    this.playerCard[j] = card;
                    card.setAvailability(false);
                    break;
                }
            }
        }
    }

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
}