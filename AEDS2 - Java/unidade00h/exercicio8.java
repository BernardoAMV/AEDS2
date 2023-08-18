public class exercicio8 {

    public static void main(String[] args){

        String palavra = "arara";
        MyIO.println(palindromo(palavra));
    }
    static boolean palindromo(String string){
        boolean resp;
        int i = 0;
        if(i == string.length() / 2){
            resp = true;
        }else if (string.charAt(i) != string.charAt(string.length() - 1 - i)){
            resp = false;
        }
        else {
            i++;
            resp = palindromo(string);
        }
        return resp;
    }
}