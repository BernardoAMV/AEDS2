

public class Cesar {

    public static void main(String[] args){
        // o codigo deve fazer uma iteração antes de entrar no loop, por isso o do - while
        String palavra = MyIO.readLine("");
        String condicaoParada = "FIM";
        do {
            criptografia(palavra);
            palavra = MyIO.readLine("");
        }
        while(parada(palavra, condicaoParada));


    }
    //méodo para criptografar a entrada, transformando cada letra da entrada em int, adicionando 3 e printando como char
    static void criptografia(String palavra){
        for (int i = 0; i < palavra.length(); i++) {

            int criptografa = (int) palavra.charAt(i);
                criptografa += 3;
            MyIO.print((char) criptografa);
            }

        MyIO.print('\n');
        }
        //metodo para identificar a parada, FIM
    static boolean parada(String palavra, String parada){
        for(int i = 0; i < parada.length(); i++){
            if(palavra.charAt(i) != parada.charAt(i))
                return true;
        }
        return false;
    }
}