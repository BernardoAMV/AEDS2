import java.util.Scanner;

public class sequencia {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

            int x,y;

            while(sc.hasNext()) {
                String n = sc.next();
                x = Integer.parseInt(n);
                n = sc.next();
                y = Integer.parseInt(n);

                espelho(x, y);
            }
        sc.close();
    }

    static void espelho(int x, int y){
        int formula = ((y - x ) * 2) + 1;
        for(int i = 0; i < formula; i++){
            if(x <= y) {
                MyIO.print(x);
                x++;

            }
            if(x > y) {
                MyIO.print(y);
                y--;
            }
        }
    }
}