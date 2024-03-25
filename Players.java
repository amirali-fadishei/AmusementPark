import java.util.*;
public class Players {
    public int Pscore;
    public Coin[] PlayerCoin = null;
    public specialCoin[] PlayerSCoin = null;
    List<Card> Playercard = new ArrayList<Card>();
    public void reserve(){

    }
    public void buy(Card card){
        boolean sw=true;
        for(int i=0;i<6;i++){
            if(card.coinList[i].getType()!=null){
                if(card.coinList[i].getType()==this.PlayerCoin[i].getType()&&this.PlayerCoin[i].getNum()>=card.coinList[i].getNum()){

                }else{
                    sw=false;
                    break;
                }
            }
        }
        if(sw==true){
            for (int i=0;i<6;i++){
                this.PlayerCoin[i].changeNum(card.coinList[i].getNum());
                if(card.SCoins[i]!=null){
                    this.PlayerSCoin[i].changeNum(card.SCoins[i].getNum());
                }
            }
            this.Playercard.add(card);
            this.Pscore+=card.Score;
        }
    }
    public void takeCoin(String color){
        if(color.equals("green")){
            this.PlayerCoin[0].num+=1;
        }else if(color.equals("white")){
            this.PlayerCoin[1].num+=1;
        }else if(color.equals("black")){
            this.PlayerCoin[2].num+=1;
        }else if(color.equals("blue")){
            this.PlayerCoin[3].num+=1;
        }else if(color.equals("red")){
            this.PlayerCoin[4].num+=1;
        }else{

        }
    }
}
