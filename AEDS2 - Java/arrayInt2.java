import java.util.Scanner;
import java.util.Random;

public class arrayInt{

    public static void main(String [] args){

        int [] array = {1,2,3,4,5,6};
       System.out.println(hasNumber(array, 651665651));



    }
    static boolean hasNumber(int [] array, int x){
        boolean result = false;
        for(int i = 0; i < array.length / 2 && !result; i++){
            if(array[array.length / 2] > x) {
                if (array[(array.length - 1) / 2 - i] == x)
                    result = true;
            }
            else {
                if (array[array.length / 2 + i] == x)
                    result = true;
            }
        }
        return result;
    }
}