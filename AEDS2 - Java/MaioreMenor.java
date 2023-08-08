public class MaioreMenor{

    public static void main(String [] args){
        int [] array = {1,2,3,4,5,6,7,8,9};
        int [] array2 = maiorEMenor(array);
        System.out.println("o maior numero eh: " +array2[0]);
        System.out.println("o menor numero eh: " +array2[1]);

    }
    static int [] maiorEMenor(int [] array){
        int menor = 1000000;
        int maior = -10000000;
        for(int i = 0; i < array.length; i++){
            if(array[i] > maior)
                maior = array[i];
            if(array[i] < menor)
                menor = array[i];
        }
        int [] maiorMenor = {maior, menor};
        return maiorMenor;
    }
}