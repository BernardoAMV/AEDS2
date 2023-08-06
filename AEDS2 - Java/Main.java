
import java.util.Scanner;
public class Main{

    public static void main(String [] args){

        String palavra;
        Scanner scanner = new Scanner(System.in);
        palavra = scanner.nextLine();
        int indice;
        indice = scanner.nextInt();
        System.out.println("The character at position "+indice+" is "+ palavra.charAt(indice - 1));
    }
}