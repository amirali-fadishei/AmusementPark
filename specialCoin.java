public class specialCoin {
    private String type;
    private int sNum;

    public specialCoin(int n, String Type) {
        this.setType(Type);
        this.setsNum(n);
    }

    public void setType(String name) {
        this.type = name;
    }

    public String getType() {
        return this.type;
    }

    public void setsNum(int n) {
        this.sNum = n;
    }

    public int getsNum() {
        return this.sNum;
    }
}