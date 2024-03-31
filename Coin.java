public class Coin {
    private String type;
    public int num;
    public Coin(int n, String name) {
        setNum(n);
        setType(name);
    }
    private void setType(String name) {
        this.type = name;
    }
    private void setNum(int n) {
        this.num = n;
    }
    public String getType() {
        return this.type;
    }
    //ترتیب سکه ها در تعریف آرایه سکه های هر کارت:
    //سبز-سفید-سیاه-آبی-قرمز-طلایی
}