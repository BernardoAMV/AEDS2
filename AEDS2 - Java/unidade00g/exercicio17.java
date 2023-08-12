import java.util.Scanner;


public class exercicio16{

    public static void main(String [] args){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Entre o n ");
        int n = scanner.nextInt();
        int [] array = new int[n];
        int [] array2 = new int[n];

        for(int i = 0; i < n; i++){

            System.out.print("Entre com o numero " + (i + 1) + " :");
            array[i] = scanner.nextInt();
        }
        for(int i = 0; i < n; i++){

            System.out.print("Entre com o numero " + (i + 1) + " :");
            array2[i] = scanner.nextInt();
        }
        ordenaArray1(array);
        ordenaArray1(array2);
        for(int i = 0; i < n; i++){

            System.out.println(array[i]);
        }
        for(int i = 0; i < n; i++){

            System.out.println(array2[i]);
        }

    }
    static void ordenaArray1(int [] array){
        int aux = 0;
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array.length; j++) {
                if (array[j] > array[i]) {
                    aux = array[j];
                    array[j] = array[i];
                    array[i] = aux;
                }
            }
        }
    }
}
