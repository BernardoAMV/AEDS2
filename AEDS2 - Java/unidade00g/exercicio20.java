import java.util.Scanner;


public class exercicio19 {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        int l, c;
        System.out.println("Entre com o numero de linhas: ");
        l = scanner.nextInt();
        System.out.println("Entre com o numero de colunas: ");
        c = scanner.nextInt();
        int [][] matriz = new int[l][c];
        int [][] matriz2 = new int[l][c];
        int [][] matrizResult = new int[l][c];
        for(int i = 0; i < l; i++){
            for(int j = 0; j < c; j++){
                System.out.println("Entre com matriz " + i + " " + j);
                matriz[i][j] = scanner.nextInt();
            }
        }
        for(int i = 0; i < l; i++){
            for(int j = 0; j < c; j++){
                System.out.println("Entre com matriz " + i + " " + j);
                matriz2[i][j] = scanner.nextInt();
            }
        }
       matrizResult = somaMatriz(matriz, matriz2, l, c);

        for(int i = 0; i < l; i++){
            System.out.println(" ");
            for(int j = 0; j < c; j++){
                System.out.print(matrizResult[i][j]);
            }
        }
        System.out.println(" ");

    }
   static int [][] somaMatriz(int [][] matriz1, int [][] matriz2, int l, int c){
        int [][] matrizResult = new int[l][c];
        for(int i = 0; i < l; i++){
            for(int j = 0; j < c; j++){
               matrizResult[i][j] =  matriz1[i][j] + matriz2[i][j];
            }
        }
        return matrizResult;
    }
}