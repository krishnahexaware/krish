class C1 { 
   
    int a,b;
   public C1() {
    a=5;
    b=8;
   }

    @Override
    public final String toString() {
        return "A is " +a+ " B is " +b;
    }
   
}

class C2 extends C1 { 
   public C2(int a,int b) {

   }
}
public class Inh5 {
    public static void main(String[] args) {
        C2 obj = new C2(12,5);
        System.out.println(obj);
    }
}