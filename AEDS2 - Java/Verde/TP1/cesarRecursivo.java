

public class cesarRecursivo {

    public static void main(String[] args){
        // o codigo deve fazer uma iteração antes de entrar no loop, por isso o do - while
        String palavra = MyIO.readLine("");
        String condicaoParada = "FIM";
        do {
            criptografia(palavra, 0);
            MyIO.print('\n');
            palavra = MyIO.readLine("");
        }
        while(parada(palavra, condicaoParada));


    }
    //méodo para criptografar a entrada, transformando cada letra da entrada em int, adicionando 3 e printando como char
    static void criptografia(String palavra, int i){
    if(i < palavra.length()) {
        int criptografa = (int) palavra.charAt(i);
        criptografa += 3;
        MyIO.print((char) criptografa);
        criptografia(palavra, i + 1);
    }

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