import java.util.*;
public class Players {
    public int pScore;
    public Coin[] PlayerCoin = null;
    public specialCoin[] PlayerSCoin = null;
    List<Card> playerCard = new ArrayList<>();
    public void reserve(){

    }
    public void buy(Card card){
        boolean sw=true;
        for(int i=0;i<6;i++){
            if(card.coinList[i].getType()!=null){
                if (!card.coinList[i].getType().equals(this.PlayerCoin[i].getType()) || this.PlayerCoin[i].num < card.coinList[i].num) {
                    sw=false;
                    break;
                }
            }
        }
        if(sw){
            for (int i=0;i<6;i++){
                if(card.coinList[i]!=null){
                    card.coinList[i].num-=this.PlayerSCoin[i].num;
                    this.PlayerCoin[i].num-=card.coinList[i].num;
                }
                if(card.SCoins[i]!=null){
                    this.PlayerSCoin[i].num+=card.SCoins[i].num;
                }
            }
            this.playerCard.add(card);
            this.pScore+=card.getScore();
        }
    }
    public void takeCoin(String color){
        switch (color) {
            case "green" -> this.PlayerCoin[0].num += 1;
            case "white" -> this.PlayerCoin[1].num += 1;
            case "black" -> this.PlayerCoin[2].num += 1;
            case "blue" -> this.PlayerCoin[3].num += 1;
            case "red" -> this.PlayerCoin[4].num += 1;
            default -> {
            }
        }
    }
}
