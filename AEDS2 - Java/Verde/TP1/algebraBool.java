public class algebraBool{

    public static String reduzString(String expressao){

        String retorno="";

        for(int i = 0;i<expressao.length();i++ ){

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
     *@param recebe expressao reduzida e com valores para as variaveis
     *@return resultado variado em '0' ou '1'
     *
     * */
    public static char descobreResultado(String expressao){

        int abre = 0;//guarda posicao de onde a ultima "(" abriu
        int fecha = 0;//guarda posicao da primeira ")" a fechar



        char[] array = new char[expressao.length()];

        for(int i = 0;i<expressao.length();i++){


            array[i] = expressao.charAt(i);
        }

        int n = array.length;

        for(int i = 0;i<n;i++){

            if(array[i]=='('){

                abre = i;

            }else if(array[i]==')'){
                fecha = i;

                switch(array[abre - 1]){


                    case 'a':
                        boolean resp = false;

                        for(int j = abre;j < fecha  ;j++){//analisa conteudo

                            if(array[j]=='0')
                                resp = true;
                        }


                        if(resp){//testa se o resultado vai ser positivo
                            array[abre] = '0';
                            array[abre-1] = ' ';

                            for(int k = abre+1;k<=fecha;k++){//limpa a area avaliada
                                array[k] = ' ';

                            }
                            String tmp = new String(array);
                            tmp = removeEspacosBrancos(tmp);//retira espacos vazios

                            n = tmp.length();//atualiza tamanho modificado do array

                            for(int k = 0;k < array.length;k++){//devolve nova expressao para o array
                                if(k<tmp.length())
                                    array[k] = tmp.charAt(k);

                                else{
                                    array[k] = ' ';
                                }
                            }
                        }else{

                            array[abre] = '1';
                            array[abre-1] = ' ';

                            for(int k = abre+1;k<=fecha;k++){

                                array[k] =' ';
                            }
                            String tmp = new String(array);
                            tmp = removeEspacosBrancos(tmp);
                            n = tmp.length();

                            for(int k = 0;k<array.length;k++){
                                if(k<tmp.length())
                                    array[k] = tmp.charAt(k);
                                else
                                    array[k] = ' ';

                            }
                        }

                        i = 0;

                        break;

                    case 'o':
                        resp = false;

                        for(int j = abre;j < fecha; j++){

                            if(array[j]=='1')
                                resp = true;
                        }

                        if(resp){
                            array[abre] = '1';

                            array[abre-1] = ' ';
                            for(int k = abre + 1;k <= fecha;k++){

                                array[k] = ' ';
                            }

                            String tmp = new String(array);
                            tmp = removeEspacosBrancos(tmp);
                            n = tmp.length();

                            for(int k = 0;k < array.length;k++){

                                if(k < tmp.length())
                                    array[k] = tmp.charAt(k);

                                else{

                                    array[k] = ' ';


                                }

                            }

                        }else{
                            array[abre] = '0';
                            array[abre-1] = ' ';

                            for(int k = abre+1;k<=fecha;k++){

                                array[k] = ' ';

                            }
                            String tmp = new String(array);
                            tmp = removeEspacosBrancos(tmp);
                            n = tmp.length();

                            for(int k = 0;k<array.length;k++){

                                if(k<tmp.length())
                                    array[k] = tmp.charAt(k);
                                else
                                    array[k] = ' ';

                            }



                        }


                        i = 0;

                        break;
                    case '!':


                        if(array[abre+1]=='0'){

                            array[abre]= '1';
                            array[abre-1]= ' ';

                            for(int k = abre+1;k<=fecha;k++){

                                array[k] = ' ';
                            }

                            String tmp = new String(array);
                            tmp = removeEspacosBrancos(tmp);
                            n = tmp.length();
                            for(int k = 0;k<array.length;k++){

                                if(k<tmp.length())
                                    array[k] = tmp.charAt(k);
                                else
                                    array[k] = ' ';
                            }
                        }
                        else{
                            array[abre] = '0';
                            array[abre-1] = ' ';

                            for(int k = abre+1;k<=fecha;k++){

                                array[k] = ' ';
                            }
                            String tmp = new String(array);
                            tmp = removeEspacosBrancos(tmp);
                            n = tmp.length();
                            for(int k = 0;k<array.length;k++){

                                if(k<tmp.length())
                                    array[k] = tmp.charAt(k);
                                else
                                    array[k] = ' ';
                            }
                        }
                        i = 0;
                        break;
                }
            }


        }
        return array[0];
    }

    public static boolean isFim(String palavra){

        return palavra.length() == 1 && palavra.charAt(0)=='0';
    }


    public static void main(String[] Args){

        String expressao;

        expressao = MyIO.readLine();
        String expressaoReduzida = "";
        char resp;


        while(!isFim(expressao)){

            expressaoReduzida = reduzString(expressao);

            expressaoReduzida = trocaValores(expressaoReduzida);

            resp = descobreResultado(expressaoReduzida);

            MyIO.println(resp);

            expressao = MyIO.readLine();
        }



    }



}