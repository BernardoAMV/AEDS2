import java.util.Scanner;
import java.util.Random;

public class arrayInt{

    public static void main(String [] args){

        int [] array = {1,2,3,4,5};
       System.out.println(hasNumber(array, 651665651));



    }
    static boolean hasNumber(int [] array, int x){
        boolean result = false;
        for(int i = 0; i < array.length && !result; i++){
            if(array[i] == x)
                result = true;
        }
        return result;
    }
}
