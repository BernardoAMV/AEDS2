import java.util.Random;

public class aleatorio {

    public static void main(String[] args){
        String parada = "FIM";
        String frase= MyIO.readLine("");
        while(parada(frase, parada)){
            aleatoriza(frase);
            frase = MyIO.readLine("");

        }

    }
    static void aleatoriza(String p){
        Random gerador = new Random();
        gerador.setSeed(4);
        char aleatoria;
        for(int i = 0; i < p.length(); i++){
            char letraAleatoria1 = ((char)('a' + (Math.abs(gerador.nextInt()) % 26)));
            char letraAleatoria2 = ((char)('a' + (Math.abs(gerador.nextInt()) % 26)));
            if(p.charAt(i) == letraAleatoria1) {
                aleatoria = letraAleatoria2;
                MyIO.print(aleatoria);
            }else {
                MyIO.print(p.charAt(i));
            }
        }
        MyIO.print("\n");
    }
    static boolean parada(String palavra, String parada){
        for(int i = 0; i < parada.length(); i++){
            if(palavra.charAt(i) != parada.charAt(i))
                return true;
        }
        return false;
    }
}