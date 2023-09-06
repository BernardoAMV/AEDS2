import java.net.*;
import java.io.*;

public class leituraHTML {

    public static void main(String[] args) throws Exception{
        String serie = MyIO.readLine();
        String url = "";
        while(parada(serie, "FIM")){
            url = MyIO.readLine();
            html(url,serie);
            serie = MyIO.readLine();
        }

    }

    static boolean parada(String palavra, String parada){ //metodo para identificar a parada, FIM
        for(int i = 0; i < parada.length(); i++){
            if(palavra.charAt(i) != parada.charAt(i))
                return true;
        }
        return false;
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
        s1 += Character.toLowerCase(s.charAt(i));
        if (s.charAt(i) != ' ' && ((s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') || (s.charAt(i) >= 'a' && s.charAt(i) <= 'z' && !isVogal(s1, 0, 0))))//compara cada letra para ver se eh uma letra e se nao eh uma vogal
            resp = isConsoante(s, i + 1);
        else
            resp = false;

        return resp;
    }


     static String Url(String url)throws Exception {

         URL oracle = new URL(url);
         URLConnection yc = oracle.openConnection();
         BufferedReader br = new BufferedReader(new InputStreamReader(yc.getInputStream()));

         String inter = br.readLine();
         String end = "";
         while (inter != null) {

             end += inter;
             inter = br.readLine();
         }
         return end;
     }
     static void contagemCaracteres(String conteudo, String serie){
        int x1 = 0;    //codigos dados pela questao, cada um define uma vogal ou padrao
        int x2 = 0;
        int x3 = 0;
        int x4 = 0;
        int x5 = 0;
        int x6 = 0;
        int x7 = 0;
        int x8 = 0;
        int x9 = 0;
        int x10 = 0;
        int x11 = 0;
        int x12 = 0;
        int x13 = 0;
        int x14 = 0;
        int x15 = 0;
        int x16 = 0;
        int x17 = 0;
        int x18 = 0;
        int x19 = 0;
        int x20 = 0;
        int x21 = 0;
        int x22 = 0;
        int x23 = 0;
        int x24 = 0;
        int x25 = 0;
        int x26 = 0;
        for(int i = 0; i < conteudo.length(); i++) {
            if (conteudo.charAt(i) == '<') {
                if (conteudo.charAt(i + 1) == 't' && conteudo.charAt(i + 2) == 'a' && conteudo.charAt(i + 3) == 'b' && conteudo.charAt(i + 4) == 'l' && conteudo.charAt(i + 5) == 'e' && conteudo.charAt(i + 6) == '>') {
                    x25++;
                    i += 6;
                }
                if (conteudo.charAt(i + 1) == 'b' && conteudo.charAt(i + 2) == 'r' && conteudo.charAt(i + 3)  == '>') {
                    x24++;
                    i += 3;
                }

            } else {
                switch (conteudo.charAt(i)) {
                    case 'a':
                        x1++;
                        break;
                    case 'e':
                        x2++;
                        break;
                    case 'i':
                        x3++;
                        break;
                    case 'o':
                        x4++;
                        break;
                    case 'u':
                        x5++;
                        break;
                    case 'á':
                        x6++;
                        break;
                    case 'é':
                        x7++;
                        break;
                    case 'í':
                        x8++;
                        break;
                    case 'ó':
                        x9++;
                        break;
                    case 'ú':
                        x10++;
                        break;
                    case 'à':
                        x11++;
                        break;
                    case 'è':
                        x12++;
                        break;
                    case 'ì':
                        x13++;
                        break;
                    case 'ò':
                        x14++;
                        break;
                    case 'ù':
                        x15++;
                        break;
                    case 'ã':
                        x16++;
                        break;
                    case 'õ':
                        x17++;
                        break;
                    case 'â':
                        x18++;
                        break;
                    case 'ê':
                        x19++;
                        break;
                    case 'î':
                        x20++;
                        break;
                    case 'ô':
                        x21++;
                        break;
                    case 'û':
                        x22++;
                        break;
                    default:
                        String teste = "";
                        teste += conteudo.charAt(i);
                        if (isConsoante(teste, 0)) {
                            x23++;
                            //MyIO.println(teste);
                        }
                }

            }
        }
        System.out.printf("a(%d) e(%d) i(%d) o(%d) u(%d) á(%d) é(%d) í(%d) ó(%d) ú(%d) à(%d) è(%d) ì(%d) ò(%d) ù(%d) ã(%d) õ(%d) â(%d) ê(%d) î(%d) ô(%d) û(%d) consoante(%d) <br>(%d) <table>(%d) nomepágina(%d)\n",x1,x2,x3,x4,x5,x6,x7,x8,x9,x10,x11,x12,x13,x14,x15,x16,x17,x18,x19,x20,x21,x22,x23,x24,x25,x26);


    }
    static void html(String url, String serie)throws Exception{
        String conteudo = Url(url);
        contagemCaracteres(conteudo, serie);
    }

    }