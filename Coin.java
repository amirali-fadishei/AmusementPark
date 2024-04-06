public class Coin {
    private String type;
    public int num;

    public Coin(int n, String name) {
        setNum(n);
        setType(name);
    }

    public void setType(String name) {
        this.type = name;
    }

    public String getType() {
        return this.type;
    }

    public void setNum(int n) {
        this.num = n;
    }

    public int getNum() {
        return this.num;
    }
}