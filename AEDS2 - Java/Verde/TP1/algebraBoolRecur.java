public class algebraBoolRecur{

    public static String reduzString(String expressao){

        String retorno="";

        for(int i = 0;i<expressao.length();i++){

            if(expressao.charAt(i)=='0' || expressao.charAt(i)=='1' || expressao.charAt(i)=='2' || expressao.charAt(i)=='3'){
                retorno+=expressao.charAt(i);

            }else if(expressao.charAt(i)>='A'&&expressao.charAt(i)<='Z')
                retorno+=expressao.charAt(i);
            else if(expressao.charAt(i)=='a'){
                retorno+='a';
                i += 2;
            }else if(expressao.charAt(i)=='o'){

                retorno+='o';
                i++;
            }else if(expressao.charAt(i)=='n'){
                retorno+='!';
                i+=2;
            }else if(expressao.charAt(i)=='(' || expressao.charAt(i)==')')
                retorno+=expressao.charAt(i);

        }
        return retorno;
    }
    public static String removeEspacosBrancos(String expressao){

        String retorno = "";

        for(int i = 0;i<expressao.length();i++){

            if(expressao.charAt(i)!=' ')
                retorno+=expressao.charAt(i);

        }
        return retorno;
    }



    public static String trocaValores(String expressao){

        int n = (int)expressao.charAt(0) - 48;
        char[] retorno = new char[expressao.length()-n-1];
        int k = 0;

        for(int i = 0;i < n;i++){
            k = 0;

            for(int j = n + 1;j<expressao.length();j++,k++){


                if(expressao.charAt(j)>='A' && expressao.charAt(j)<='Z'){

                    if(expressao.charAt(j)==(char)'A'+i){
                        retorno[k] = expressao.charAt(i+1);

                    }
                }else{
                    retorno[k] = expressao.charAt(j);
                }
            }
        }
        String fim = new String(retorno);

        return fim;

    }


    /**
     *@param expressao em String, array de char do tamanho da string, contador
     *
     **/
    public static void copiaExpressao(String expressao,char[] array,int i){

        if(i<array.length){
            if(i<expressao.length())
                array[i] = expressao.charAt(i);
            else
                array[i] = ' ';
            copiaExpressao(expressao,array,++i);

        }

    }
    /**
     *@param Array de char, qual posicao comeca a limpeza e qual termina, contador
     */
    public static void limpaArray(char[] array, int fecha,int i){


        if(i<=fecha){
            array[i] = ' ';
            limpaArray(array,fecha,++i);
        }
    }
    /**
     *@param array a ser investigado, contador a partir de onde a ')' apareceu
     * */
    public static int AchaParenteseFechado(char [] array,int i){

        int resp = 0;
        if(array[i]!=')'){

            resp = AchaParenteseFechado(array,++i);

        }else{
            resp = i;

        }
        return resp;

    }

    public static boolean analisaConteudoAND(char[] array,int n,int i){

        boolean resp = true;
        if(i<=n){

            if(array[i] == '0')
                resp = false;
            else
                resp = analisaConteudoAND(array,n,++i);

        }
        return resp;
    }

    public static boolean analisaConteudoOR(char[]array,int n,int i){

        boolean resp = false;
        if(i<=n){

            if(array[i]=='1')
                resp = true;
            else
                resp = analisaConteudoOR(array,n,++i);

        }
        return resp;
    }

    /**
     *@param recebe expressao reduzida e com valores para as variaveis
     *@return resultado variado em '0' ou '1'
     *
     * */
    public static String descobreResultado(String expressao,int i){

        int abre = 0;//guarda posicao de onde a ultima "(" abriu
        int fecha = 0;//guarda posicao da primeira ")" a fechar




        String resultado = expressao;


        int n =expressao.length();
        char[] array = new char[resultado.length()];

        if(i<n-1){
            resultado = descobreResultado(expressao,++i);

        }

        array = new char[resultado.length()];
        copiaExpressao(resultado,array,0);

        if(array[i]=='('){


            abre = i;

            fecha = AchaParenteseFechado(array,abre);

            switch(array[abre - 1]){



                case 'a':

                    boolean resp = analisaConteudoAND(array,fecha,abre);


                    if(!resp){//testa se o resultado vai ser positivo
                        array[abre] = '0';
                        array[abre-1] = ' ';

                        limpaArray(array,fecha,abre+1);//limpa a area avaliada

                        String tmp = new String(array);
                        tmp = removeEspacosBrancos(tmp);//retira espacos vazios

                        n = tmp.length();//atualiza tamanho modificado do array

                        copiaExpressao(tmp,array,0);

                    }else{

                        array[abre] = '1';
                        array[abre-1] = ' ';

                        limpaArray(array,fecha,abre+1);
                        String tmp = new String(array);
                        tmp = removeEspacosBrancos(tmp);
                        n = tmp.length();

                        copiaExpressao(tmp,array,0);
                    }



                    break;

                case 'o':
                    resp = analisaConteudoOR(array,fecha,abre+1);

                    if(resp){
                        array[abre] = '1';

                        array[abre-1] = ' ';
                        limpaArray(array,fecha,abre+1);

                        String tmp = new String(array);
                        tmp = removeEspacosBrancos(tmp);
                        n = tmp.length();

                        copiaExpressao(tmp,array,0);

                    }else{
                        array[abre] = '0';
                        array[abre-1] = ' ';

                        limpaArray(array,fecha,abre+1);
                        String tmp = new String(array);
                        tmp = removeEspacosBrancos(tmp);
                        n = tmp.length();

                        copiaExpressao(tmp,array,0);
                    }



                    break;
                case '!':


                    if(array[abre+1]=='0'){

                        array[abre]= '1';
                        array[abre-1]= ' ';


                        limpaArray(array,fecha,abre+1);


                        String tmp = new String(array);

                        tmp = removeEspacosBrancos(tmp);
                        n = tmp.length();
                        copiaExpressao(tmp,array,0);
                    }
                    else{
                        array[abre] = '0';
                        array[abre-1] = ' ';


                        limpaArray(array,fecha,abre+1);

                        String tmp = new String(array);
                        tmp = removeEspacosBrancos(tmp);
                        n = tmp.length();
                        copiaExpressao(tmp,array,0);

                    }


                    break;
            }
        }
        i++;




        return new String(array);
    }

    public static boolean isFim(String palavra){

        return palavra.length() == 1 && palavra.charAt(0)=='0';
    }

public class AlgebraBooleana{

    public static String reduzString(String expressao){

        String retorno="";

        for(int i = 0;i<expressao.length();i++){

            if(expressao.charAt(i)=='0' || expressao.charAt(i)=='1' || expressao.charAt(i)=='2' || expressao.charAt(i)=='3'){
                retorno+=expressao.charAt(i);

            }else if(expressao.charAt(i)>='A'&&expressao.charAt(i)<='Z')
                retorno+=expressao.charAt(i);
            else if(expressao.charAt(i)=='a'){
                retorno+='a';
                i += 2;
            }else if(expressao.charAt(i)=='o'){

                retorno+='o';
                i++;
            }else if(expressao.charAt(i)=='n'){
                retorno+='!';
                i+=2;
            }else if(expressao.charAt(i)=='(' || expressao.charAt(i)==')')
                retorno+=expressao.charAt(i);

        }
        return retorno;
    }
    public static String removeEspacosBrancos(String expressao){

        String retorno = "";

        for(int i = 0;i<expressao.length();i++){

            if(expressao.charAt(i)!=' ')
                retorno+=expressao.charAt(i);

        }
        return retorno;
    }



    public static String trocaValores(String expressao){

        int n = (int)expressao.charAt(0) - 48;
        char[] retorno = new char[expressao.length()-n-1];
        int k = 0;

        for(int i = 0;i < n;i++){
            k = 0;

            for(int j = n + 1;j<expressao.length();j++,k++){


                if(expressao.charAt(j)>='A' && expressao.charAt(j)<='Z'){

                    if(expressao.charAt(j)==(char)'A'+i){
                        retorno[k] = expressao.charAt(i+1);

                    }
                }else{
                    retorno[k] = expressao.charAt(j);
                }
            }
        }
        String fim = new String(retorno);

        return fim;

    }


     /**
     *@param expressao em String, array de char do tamanho da string, contador
     *
     **/
    public static void copiaExpressao(String expressao,char[] array,int i){

        if(i<array.length){
            if(i<expressao.length())
                array[i] = expressao.charAt(i);
            else
                array[i] = ' ';
            copiaExpressao(expressao,array,++i);

        }

    }
    /**
     *@param Array de char, qual posicao comeca a limpeza e qual termina, contador
     */
    public static void limpaArray(char[] array, int fecha,int i){


        if(i<=fecha){
            array[i] = ' ';
            limpaArray(array,fecha,++i);
        }
    }
    /**
     *@param array a ser investigado, contador a partir de onde a ')' apareceu
     * */
    public static int AchaParenteseFechado(char [] array,int i){

        int resp = 0;
        if(array[i]!=')'){

         resp = AchaParenteseFechado(array,++i);

        }else{
            resp = i;

        }
        return resp;

    }

    public static boolean analisaConteudoAND(char[] array,int n,int i){

        boolean resp = true;
        if(i<=n){

            if(array[i] == '0')
                resp = false;
            else
                resp = analisaConteudoAND(array,n,++i);

        }
        return resp;
    }

    public static boolean analisaConteudoOR(char[]array,int n,int i){

        boolean resp = false;
        if(i<=n){

            if(array[i]=='1')
                resp = true;
            else
                resp = analisaConteudoOR(array,n,++i);

        }
        return resp;
    }

     /**
     *@param recebe expressao reduzida e com valores para as variaveis
     *@return resultado variado em '0' ou '1'
     *
     * */
    public static String descobreResultado(String expressao,int i){

        int abre = 0;//guarda posicao de onde a ultima "(" abriu
        int fecha = 0;//guarda posicao da primeira ")" a fechar




       String resultado = expressao;


        int n =expressao.length();
        char[] array = new char[resultado.length()];

        if(i<n-1){
             resultado = descobreResultado(expressao,++i);

        }

             array = new char[resultado.length()];
             copiaExpressao(resultado,array,0);

            if(array[i]=='('){


                abre = i;

                fecha = AchaParenteseFechado(array,abre);

                switch(array[abre - 1]){



                    case 'a':

                        boolean resp = analisaConteudoAND(array,fecha,abre);


                                if(!resp){//testa se o resultado vai ser positivo
                                    array[abre] = '0';
                                    array[abre-1] = ' ';

                                    limpaArray(array,fecha,abre+1);//limpa a area avaliada

                                    String tmp = new String(array);
                                    tmp = removeEspacosBrancos(tmp);//retira espacos vazios

                                    n = tmp.length();//atualiza tamanho modificado do array

                                    copiaExpressao(tmp,array,0);

                                }else{

                                    array[abre] = '1';
                                    array[abre-1] = ' ';

                                    limpaArray(array,fecha,abre+1);
                                    String tmp = new String(array);
                                    tmp = removeEspacosBrancos(tmp);
                                    n = tmp.length();

                                    copiaExpressao(tmp,array,0);
                               }



                        break;

                    case 'o':
                        resp = analisaConteudoOR(array,fecha,abre+1);

                            if(resp){
                                array[abre] = '1';

                                array[abre-1] = ' ';
                                limpaArray(array,fecha,abre+1);

                                String tmp = new String(array);
                                tmp = removeEspacosBrancos(tmp);
                                n = tmp.length();

                                copiaExpressao(tmp,array,0);

                            }else{
                                array[abre] = '0';
                                array[abre-1] = ' ';

                                limpaArray(array,fecha,abre+1);
                                String tmp = new String(array);
                                tmp = removeEspacosBrancos(tmp);
                                n = tmp.length();

                                copiaExpressao(tmp,array,0);
                            }



                        break;
                    case '!':


                        if(array[abre+1]=='0'){

                            array[abre]= '1';
                            array[abre-1]= ' ';


                            limpaArray(array,fecha,abre+1);


                            String tmp = new String(array);

                            tmp = removeEspacosBrancos(tmp);
                            n = tmp.length();
                            copiaExpressao(tmp,array,0);
                        }
                        else{
                            array[abre] = '0';
                            array[abre-1] = ' ';


                            limpaArray(array,fecha,abre+1);

                            String tmp = new String(array);
                            tmp = removeEspacosBrancos(tmp);
                            n = tmp.length();
                            copiaExpressao(tmp,array,0);

                        }


                        break;
                }
            }
            i++;




        return new String(array);
    }

    public static boolean isFim(String palavra){

        return palavra.length() == 1 && palavra.charAt(0)=='0';
    }


    public static void main(String[] Args){

        String expressao;

        expressao = MyIO.readLine();
        String expressaoReduzida = "";
        String resp;


        while(!isFim(expressao)){

            expressaoReduzida = reduzString(expressao);


            expressaoReduzida = trocaValores(expressaoReduzida);

            resp = descobreResultado(expressaoReduzida,0);

            MyIO.println(resp.charAt(0));

            expressao = MyIO.readLine();
        }



    }



}
    public static void main(String[] Args){

        String expressao;

        expressao = MyIO.readLine();
        String expressaoReduzida = "";
        String resp;


        while(!isFim(expressao)){

            expressaoReduzida = reduzString(expressao);


            expressaoReduzida = trocaValores(expressaoReduzida);

            resp = descobreResultado(expressaoReduzida,0);

            MyIO.println(resp.charAt(0));

            expressao = MyIO.readLine();
        }



    }



}