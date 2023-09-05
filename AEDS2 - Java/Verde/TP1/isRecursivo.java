
public class isRecursivo {

    public static void main(String[] args){
        String s = MyIO.readLine("");
        String condicaoParada = "FIM";
        while(parada(s, condicaoParada)){
            if(isVogal(s, 0, 0))
                MyIO.print("SIM ");
            else
                MyIO.print("NAO ");
            if(isConsoante(s, 0))
                MyIO.print("SIM ");
            else
               MyIO.print("NAO ");
            if(isInt(s, 0, 0))
                MyIO.print("SIM ");
            else
                MyIO.print("NAO ");
            if(isReal(s, 0, 0))
                MyIO.print("SIM");
            else
                MyIO.print("NAO");
            MyIO.print("\n");
            s = MyIO.readLine("");
        }


    }
    static boolean isVogal(String s, int i, int j){
        String vogal = "AaEeIiOoUu";
        boolean resp = false;
        if(i >= s.length()) {
            resp = true;
            return resp;
        }
        if(j >= vogal.length()) {
            return resp;
        }
         if(s.charAt(i) != vogal.charAt(j)){
             resp = isVogal(s, i, j + 1);  // compara cada letra com a string vogal
         }
        else {
            j = 0;
            resp =isVogal(s, i + 1, j);
         }
             return resp;
    }
    static boolean isConsoante(String s, int i){
        boolean resp = true;
        String s1 = "";
        if(i >= s.length())
            return resp;
            s1 += s.charAt(i);
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'z' && !isVogal(s1, 0, 0))//compara cada letra para ver se eh uma letra e se nao eh uma vogal
                resp = isConsoante(s, i + 1);
            else
                resp = false;

        return resp;
    }
    static boolean isInt(String s, int i, int j){
        boolean resp = false;
        String numeros = "1234567890";
        if(i >= s.length()) {
            resp = true;
            return resp;
        }
        if(j >= numeros.length())
            return resp;
        if(s.charAt(i) != numeros.charAt(j))  //compara cada letra com a string numeros, a fim de determinar se ela eh composta apenas de numeros
                    resp = isInt(s, i, j + 1);
        else {
                j = 0;
                resp = isInt(s, i + 1, j);
            }
        return resp;

    }
    static boolean isReal(String s, int i, int cont){

        if (isInt(s, 0, 0))
            return true;

        boolean resp = true;
        if(i < s.length()) {
            String c = "";
             c += s.charAt(i);
             //MyIO.println(isInt(c,0,0));
            if (!isInt(c, 0, 0) && !(s.charAt(i)=='.' || s.charAt(i)==','))
                return false;

            if (s.charAt(i)=='.' || s.charAt(i)==',')
                cont++;

            if(cont > 1)
                return false;

            resp = isReal(s,++i,cont);
        }
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