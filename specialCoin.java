public class specialCoin {
    private String type;
    int num;
    private void setType(String name){
        this.type = name;
    }
    public String getType(){
        return this.type;
    }
    private void setNum(int n){
        this.num = n;
    }
    public int getNum(){
        return this.num;
    }
    public specialCoin(int n,String Type){
        this.setType(Type);
        this.setNum(n);
    }
}
