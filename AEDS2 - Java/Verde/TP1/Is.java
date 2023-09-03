
public class Is {

    public static void main(String[] args){
        String s = MyIO.readLine("");
        String condicaoParada = "FIM";
        while(parada(s, condicaoParada)){
            if(isVogal(s))
                MyIO.print("SIM ");
            else
                MyIO.print("NAO ");
            if(isConsoante(s))
                MyIO.print("SIM ");
            else
                MyIO.print("NAO ");
            if(isInt(s))
                MyIO.print("SIM ");
            else
                MyIO.print("NAO ");
            if(isReal(s))
                MyIO.print("SIM");
            else
                MyIO.print("NAO");
            MyIO.print("\n");
            s = MyIO.readLine("");
        }


    }
    static boolean isVogal(String s){
        String vogal = "AaEeIiOoUu";
        boolean resp = true;
        for(int i = 0; i < s.length() && resp; i++){
            resp = false;
            for(int j = 0; j < vogal.length() && !resp; j++){ // compara cada letra com a string vogal
                if(s.charAt(i) == vogal.charAt(j))
                    resp = true;
            }
        }
        return resp;
    }
    static boolean isConsoante(String s){
        boolean resp = true;
        String s1 = "";
        for(int i = 0; i < s.length() && resp; i++){
            resp = false;
            s1 += s.charAt(i);
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'z' && !isVogal(s1) && !resp) //compara cada letra para ver se eh uma letra e se nao eh uma vogal
                resp = true;
            s1 = "";
            }
        return resp;
    }
    static boolean isInt(String s){
        boolean resp = true;
        String numeros = "1234567890";
        for(int i = 0; i < s.length() && resp; i++) {
            resp = false;
            for(int j = 0; j < numeros.length() && !resp; j++){ //compara cada letra com a string numeros, a fim de determinar se ela eh composta apenas de numeros
                if(s.charAt(i) == numeros.charAt(j))
                    resp = true;
            }
        }
        return resp;

    }
    static boolean isReal(String s){
        boolean resp = true;
        String numeros = "1234567890";
        int cont = 0;
        for(int i = 0; i < s.length() && resp; i++) {
            resp = false;
            if(s.charAt(i) == ',' || s.charAt(i) == '.') { // testa a string para saber se ela eh composta apenas de numeros e se ha apenas uma virgula
                cont += 1;
                resp = true;
            }
            for(int j = 0; j < numeros.length() && !resp; j++){
                if(s.charAt(i) == numeros.charAt(j))
                    resp = true;

            }
        }
        if(cont > 1)
            resp = false;
            return resp;


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