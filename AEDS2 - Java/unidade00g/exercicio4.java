import java.util.Scanner;

public class exercicio4{

    public static void main(String [] args){
        int soma = 0, i, n = 10git;
        int [] array = new int[n];
        Scanner scanner = new Scanner(System.in);
        for(i = 0; 2*i+1 < n; i++){
            //System.out.println("entre com o numero "+i);
            array[i] = scanner.nextInt();
            soma += array[i];
        }
        System.out.println("a soma dos numeros é: " + soma);


    }

}