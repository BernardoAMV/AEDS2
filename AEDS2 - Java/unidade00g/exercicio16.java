import java.util.Scanner;


public class exercicio16{

    public static void main(String [] args){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Entre o n ");
        int n = scanner.nextInt();
        int [] array = new int[n];
        int [] array2 = new int[n];
        int [] array3 = new int[2 *n];
        int soma = 0;
        int soma2 = 0;
        int soma3 = 0;

        for(int i = 0; i < n; i++){

            System.out.print("Entre com o numero " + (i + 1) + " :");
            array[i] = scanner.nextInt();
        }
        for(int i = 0; i < n; i++){

            System.out.print("Entre com o numero " + (i + 1) + " :");
            array2[i] = scanner.nextInt();
        }
        for(int i = 0; i < n; i++){
                    array3[soma] = array[i];
                    soma++;
                    array3[soma] = array2[i];
                    soma++;

        }

        for(int i = 0; i < n*2; i++){

            System.out.println(array3[i]);
        }


    }

}
