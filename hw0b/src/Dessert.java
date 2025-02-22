public class Dessert {
    public int favor;
    public int price;
    public static  int numDesserts;
    public Dessert(int f,int p){
        favor = f;
        price = p;
        numDesserts += 1;
    }
    public void printDessert(){
        System.out.printf("%d %d %d",this.favor,this.price,numDesserts);
    }
    public static void main(String[] args){
        System.out.print("I love dessert!");
    }
}
