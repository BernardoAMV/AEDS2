import java.util.Random;

public class aleatorio {

    public static void main(String[] args){
        Random gerador = new Random();
        String parada = "FIM";
        String frase= MyIO.readLine("");
        while(parada(frase, parada)){
            aleatoriza(frase, gerador);
            frase = MyIO.readLine("");

        }

    }
    static void aleatoriza(String p, Random gerador){ // funcao para aleatorizar a string entrada

        gerador.setSeed(4);
        char letraAleatoria1 = ((char)('a' + (Math.abs(gerador.nextInt() % 26))));
        char letraAleatoria2 = ((char)('a' + (Math.abs(gerador.nextInt() % 26))));
        char aleatoria;
        for(int i = 0; i < p.length(); i++){ // a funcao gera a letra aleatoria, procura na string a letra em questao e printa a letra modificada
            if(p.charAt(i) == letraAleatoria1) {
                aleatoria = letraAleatoria2;
                MyIO.print(aleatoria);
            }else // se nao houver semelhanca, a funcao printa a letra da propria string
                MyIO.print(p.charAt(i));

        }
        MyIO.print("\n");
    }
    static boolean parada(String palavra, String parada){ //metodo para identificar a parada, FIM
        for(int i = 0; i < parada.length(); i++){
            if(palavra.charAt(i) != parada.charAt(i))
                return true;
        }
        return false;
    }
}