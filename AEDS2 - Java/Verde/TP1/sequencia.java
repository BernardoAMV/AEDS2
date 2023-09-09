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
        String normal = "";
        String espelho = "";
        for(int i = 0; i < formula; i++){
            if(x <= y) {
                normal += x;
                x++;

            }
        }
        for(int i = 0; i < normal.length(); i++){
            espelho += normal.charAt(normal.length() - i - 1);
        }
        MyIO.print(normal);
        MyIO.print(espelho);

        MyIO.print("\n");
    }
}