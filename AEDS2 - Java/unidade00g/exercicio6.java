public class exercicio6 {

    public static void main(String[] args){

        int [] array = {9,8,7,6,5,4,3,2,1};
        ordenaArray(array);
        for(int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
    }
   static void ordenaArray(int [] array){
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