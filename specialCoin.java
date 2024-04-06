public class specialCoin {
    private String type;
    public int sNum;

    public specialCoin(int n, String Type) {
        this.setType(Type);
        this.setsNum(n);
    }

    private void setType(String name) {
        this.type = name;
    }

    public String getType() {
        return this.type;
    }

    private void setsNum(int n) {
        this.sNum = n;
    }

    public int getsNum() {
        return this.sNum;
    }
}