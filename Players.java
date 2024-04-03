public class Players {
    public int pScore;
    public Coin[] playerCoin = new Coin[6];
    public specialCoin[] playerSCoin = new specialCoin[5];
    public Card[] playerCard = new Card[48];

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