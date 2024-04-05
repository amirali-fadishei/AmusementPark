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
    public int coinCounter(){
        this.playerCoinNum = 0;
        for(int t=0;t<6;t++){
            this.playerCoinNum+=this.playerCoin[t].num;
        }
        return this.playerCoinNum;
    }
}