public class exercicio10 {

    public static void main(String[] args){

        String palavra = MyIO.readString("Entre com a palavra: ");
        MyIO.println(vowelQuant(palavra, 0));
    }
    public static int isNotVowel(char c) {
        if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u'){
            return 1;
        }
        else
            return 0;

    }

    public static int isNotConsonant(char C) {
        if (C != 'B' && C != 'C' && C != 'D' && C != 'F' && C != 'G' && C != 'H' && C != 'J' && C != 'K' && C != 'L' && C != 'M' && C != 'N' && C != 'P' && C != 'Q' && C != 'R' && C != 'S' && C != 'T' && C != 'V' && C != 'W' && C != 'X' && C != 'Y' && C != 'Z'){
            return 1;
        }
        else
            return 0;

    }
    public static int vowelQuant(String string, int i){
        if(i == string.length() - 1){
            return isNotVowel(string.charAt(i)) + isNotConsonant(string.charAt(i));
        }
        return isNotVowel(string.charAt(i)) + vowelQuant(string, ++i) + isNotConsonant(string.charAt(i));
    }
}