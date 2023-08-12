import java.util.Scanner;


public class exercicio28 {


    public static void main(String[] args) {
        int soma = 0, letras = 0, nLetras = 0, vogais = 0, consoantes = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entre com a string: ");
        String frase = scanner.nextLine();
        boolean resp = true;
        for (int i = 0; i < frase.length() && resp; i++) {
            soma++;
            if (Character.isLetter(frase.charAt(i)))
                letras++;
            if (!Character.isLetter(frase.charAt(i)))
                nLetras++;
            char c = Character.toLowerCase(frase.charAt(i));
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                vogais++;
            if (Character.isLetter(c) && !isVowel(c)) {
                consoantes++;

            }
            }
        System.out.println("caracteres: " + soma + " letras: " + letras + " não letras: " + nLetras + " vogais: " + vogais + " consoantes: " + consoantes);


    }

    public static boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';

    }
}