

public class Cesar {

    public static void main(String[] args){
        // o codigo deve fazer uma iteração ante de entrar no loop
        String palavra = MyIO.readLine("");
        String condicaoParada = "FIM";
        do {
            criptografia(palavra);
            palavra = MyIO.readLine("");
        }
        while(parada(palavra, condicaoParada));


    }
    static void criptografia(String palavra){
        for (int i = 0; i < palavra.length(); i++) {

            int criptografa = (int) palavra.charAt(i);
                criptografa += 3;
            MyIO.print((char) criptografa);
            }

        MyIO.print('\n');
        }
    static boolean parada(String palavra, String parada){
        for(int i = 0; i < parada.length(); i++){
            if(palavra.charAt(i) != parada.charAt(i))
                return true;
        }
        return false;
    }
}