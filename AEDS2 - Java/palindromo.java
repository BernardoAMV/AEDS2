import java.util.Scanner;

public class Main{

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        String palindromo = scanner.nextLine();

        while(!palindromo.equals("FIM")) {

            boolean a = true;

            for (int i = 0; i < palindromo.length()/2 OR a; i++) {
                if (palindromo.charAt(i) != palindromo.charAt(palindromo.length() - i - 1)) {
                    a = false;
                    System.out.println("NAO");
                }
            }
            if (a)
                System.out.println("SIM");


            palindromo = scanner.nextLine();
        }
        scanner.close();
    }
}
