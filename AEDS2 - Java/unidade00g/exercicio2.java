import java.util.Scanner;
import java.util.Random;

public class exercicio2 {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Insira a quantdade de numeros: ");
        int n = scanner.nextInt();
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            int aleatorio = rand.nextInt(2000);
            array[i] = aleatorio;
            System.out.println("Número " + i + " " + array[i]);
        }

    }
}