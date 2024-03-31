public class Players {
    public int pScore;
    public Coin[] PlayerCoin = new Coin[6];
    public specialCoin[] PlayerSCoin = new specialCoin[5];
   public Card[] playerCard = new Card[45];
   public void buy(Card card) {
        boolean sw = true;
        for (int i = 0; i < 6; i++) {
            if (card.coinList[i].getType() != null) {
                if (!card.coinList[i].getType().equals(this.PlayerCoin[i].getType()) || this.PlayerCoin[i].num < card.coinList[i].num) {
                    sw = false;
                    break;
                }
            }
        }
        if (sw) {
            for (int i = 0; i < 6; i++) {
                if (card.coinList[i] != null) {
                    if(this.PlayerSCoin[i]!=null){
                        card.coinList[i].num -= this.PlayerSCoin[i].num;
                    }
                    this.PlayerCoin[i].num -= card.coinList[i].num;
                }
                if (card.SCoins[i] != null) {
                    this.PlayerSCoin[i].num += card.SCoins[i].num;
                }
            }
            this.pScore += card.getScore();
            for (int j=0;j<45;j++){
                if(this.playerCard[j]==null){
                    this.playerCard[j]=card;
                    break;
                }
            }
        }
    }
    public void takeCoin(int count, String color) {
        switch (color) {
            case "green" -> this.PlayerCoin[0].num += count;
            case "white" -> this.PlayerCoin[1].num += count;
            case "black" -> this.PlayerCoin[2].num += count;
            case "blue" -> this.PlayerCoin[3].num += count;
            case "red" -> this.PlayerCoin[4].num += count;
            default -> {
            }
        }
    }
}