import java.util.Scanner;

public class comparaString{

   public static void main(String [] args){
    Scanner scanner = new Scanner(System.in);
    String palavra1 = scanner.nextLine();
    String palavra2 = scanner.nextLine();
    boolean a = true;
    if(palavra1.length() != palavra2.length())
       a = false;
    for(int i = 0; i < palavra1.length() && a; i++){
        if(palavra1.charAt(i) != palavra2.charAt(i))
           a = false;
    }
    if(!a)
     System.out.println("As strings são diferentes");
    else
     System.out.println("As strings são iguais");
   }
}
