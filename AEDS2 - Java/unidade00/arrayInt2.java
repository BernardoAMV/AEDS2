import java.util.Scanner;
import java.util.Random;

public class arrayInt2{

    public static void main(String [] args){
        int number;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entre com o numero procurado: ");
        number = scanner.nextInt();
        int [] array = {1,2,3,4,5,6,7};
       System.out.println(hasNumber(array, 7));



    }
    static boolean hasNumber(int [] array, int x){
        boolean result = false;
        for(int i = 0; i <= array.length / 2 && !result; i++){
            if(array[array.length / 2] > x) {
                if (array[(array.length) / 2 - i] == x) {
                    result = true;
                }
                System.out.println(array[(array.length) / 2 - i]);
            }
            else {
                if (array[array.length / 2 + i] == x)
                    result = true;
            }
        }
        return result;
    }
}
