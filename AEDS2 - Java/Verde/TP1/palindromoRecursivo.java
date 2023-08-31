public class palindromoRecursivo {

    public static void main(String[] args){
        String p = MyIO.readLine("");
        while(parada(p, "FIM")) {
            if (isPalindromo(p, 0))
                MyIO.println("SIM");
            else {
                MyIO.println("NAO");
            }
             p = MyIO.readLine("");
        }
    }
    static boolean isPalindromo(String p, int i){
        boolean resp = true;
        if(i >= p.length() / 2)
            return resp;
        else if (p.charAt(i) == p.charAt(p.length() - i - 1)) {
           resp = isPalindromo(p, i + 1);
        }
        else {
            resp = false;
        }
        return resp;
    }
    static boolean parada(String palavra, String parada){ //metodo para identificar a parada, FIM
        for(int i = 0; i < parada.length(); i++){
            if(palavra.charAt(i) != parada.charAt(i))
                return true;
        }
        return false;
    }
}