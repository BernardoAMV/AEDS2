import java.util.Scanner;


public class exercicio20 {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        int l, c;
        System.out.println("Entre com o numero de linhas: ");
        l = scanner.nextInt();
        System.out.println("Entre com o numero de colunas: ");
        c = scanner.nextInt();
        int [][] matriz = new int[l][c];
        for(int i = 0; i < l; i++){
            for(int j = 0; j < c; j++){
                System.out.println("Entre com matriz " + i + " " + j);
                matriz[i][j] = scanner.nextInt();
            }
        }
        for(int i = 0; i < l; i++){
            System.out.println(" ");
            for(int j = 0; j < c; j++){
                System.out.print(matriz[i][j]);
            }
        }
        System.out.println(" ");

    }
}