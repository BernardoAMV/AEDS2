class Jogador {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    public Jogador() {
    }

    public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento) {
        setId(id);
        setNome(nome);
        setAltura(altura);
        setPeso(peso);
        setUniversidade(universidade);
        setAnoNascimento(anoNascimento);
        setCidadeNascimento(cidadeNascimento);
        setEstadoNascimento(estadoNascimento);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getAltura() {
        return altura;
    }

    public int getPeso() {
        return peso;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        cidadeNascimento = validar(cidadeNascimento);
        this.cidadeNascimento = cidadeNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        estadoNascimento = validar(estadoNascimento);
        this.estadoNascimento = estadoNascimento;
    }

    public void setNome(String nome) {
        nome = validar(nome);
        this.nome = nome;
    }

    public void setUniversidade(String universidade) {
        universidade = validar(universidade);
        this.universidade = universidade;
    }

    public String validar(String str) {
        if (str.isEmpty())
            str = "nao informado";
        return str;
    }

    public Jogador clone() {
        return new Jogador(getId(), getNome(), getAltura(), getPeso(), getUniversidade(), getAnoNascimento(), getCidadeNascimento(), getEstadoNascimento());
    }


    public void imprimir() {
        System.out.println("## " + getNome() + " ## " + getAltura() + " ## " + getPeso() + " ## " + getAnoNascimento() + " ## " + getUniversidade() + " ## " + getCidadeNascimento() + " ## " + getEstadoNascimento() + " ##");
    }

    public void ler(String jogador) {

        String[] dados = jogador.split(",", 8);
        setId(dados[0] != "" ? Integer.parseInt(dados[0]) : -1);
        setNome(dados[1]);
        setAltura(Integer.parseInt(dados[2]));
        setPeso(Integer.parseInt(dados[3]));
        setUniversidade(dados[4]);
        setAnoNascimento(Integer.parseInt(dados[5]));
        setCidadeNascimento(dados[6]);
        setEstadoNascimento(dados[7]);
    }
}

    /**
     * No da arvore binaria
     * @author Max do Val Machado
     */

    class No {
        public Jogador elemento; // Conteudo do no.
        public No esq, dir;  // Filhos da esq e dir.

        /**
         * Construtor da classe.
         * @param elemento Conteudo do no.
         */
        public No(Jogador elemento) {
            this(elemento, null, null);
        }

        /**
         * Construtor da classe.
         * @param elemento Conteudo do no.
         * @param esq No da esquerda.
         * @param dir No da direita.
         */
        public No(Jogador elemento, No esq, No dir) {
            this.elemento = elemento;
            this.esq = esq;
            this.dir = dir;
        }
    }


    /**
     * Arvore binaria de pesquisa
     * @author Max do Val Machado
     */
    class ArvoreBinaria {
        private No raiz; // Raiz da arvore.

        /**
         * Construtor da classe.
         */
        public ArvoreBinaria() {
            raiz = null;
        }

        /**
         * Metodo publico iterativo para pesquisar elemento.
         * @param x Elemento que sera procurado.
         * @return <code>true</code> se o elemento existir,
         * <code>false</code> em caso contrario.
         */
        public boolean pesquisar(String x) {
            return pesquisar(x, raiz);
        }

        /**
         * Metodo privado recursivo para pesquisar elemento.
         * @param x Elemento que sera procurado.
         * @param i No em analise.
         * @return <code>true</code> se o elemento existir,
         * <code>false</code> em caso contrario.
         */
        private boolean pesquisar(String x, No i) {
            boolean resp =  false;
            if (i == null) {
                Alfa.contador++;
                resp = false;

            } else if (x.compareTo(i.elemento.getNome()) == 0) {
                Alfa.contador+=2;
                resp = true;

            } else if (x.compareTo(i.elemento.getNome()) < 0) {
                Alfa.contador+=3;
                MyIO.print("esq ");
                resp = pesquisar(x, i.esq);

            } else if (x.compareTo(i.elemento.getNome()) > 0) {
                Alfa.contador+=4;
                MyIO.print("dir ");
                resp = pesquisar(x, i.dir);
            }
            return resp;
        }

        /**
         * Metodo publico iterativo para exibir elementos.
         */
        public void caminharPre() {
            System.out.print("[ ");
            caminharPre(raiz);
            System.out.println("]");
        }

        /**
         * Metodo privado recursivo para exibir elementos.
         * @param i No em analise.
         */
        private void caminharPre(No i) {
            if (i != null) {
                System.out.print(i.elemento + " "); // Conteudo do no.
                caminharPre(i.esq); // Elementos da esquerda.
                caminharPre(i.dir); // Elementos da direita.
            }
        }


        /**
         * Metodo publico iterativo para inserir elemento.
         * @param x Elemento a ser inserido.
         * @throws Exception Se o elemento existir.
         */
        public void inserir(Jogador x) {
            raiz = inserir(x, raiz);
        }

        /**
         * Metodo privado recursivo para inserir elemento.
         * @param x Elemento a ser inserido.
         * @param i No em analise.
         * @return No em analise, alterado ou nao.
         * @throws Exception Se o elemento existir.
         */
        private No inserir(Jogador x, No i) {
            if (i == null) {
                Alfa.contador+=1;
                i = new No(x);

            } else if (x.getNome().compareTo(i.elemento.getNome()) < 0) {
                Alfa.contador+=2;
                i.esq = inserir(x, i.esq);

            } else if (x.getNome().compareTo(i.elemento.getNome()) > 0) {
                Alfa.contador+=3;
                i.dir = inserir(x, i.dir);

            }

            return i;
        }


    }

    public class Alfa{

        public static ArvoreBinaria arvore;
        public static Jogador array [];
        public static int contador = 0;
        public static long tempo;

        public static void criarLog(){
        Arq.openWrite("806347_arvoreBinaria.txt");
        Arq.println("806347\t" + tempo + "ms" + "\t" + contador + "comparacoes");
        Arq.close();
    }
        public static void leArquivo(){
            Arq.openRead("/tmp/players.csv");
            Arq.readLine(); //remove a primeira linha
            for(int i = 0; i < 3922; i++){
                String data = Arq.readLine();
                array[i] = new Jogador();
                array[i].ler(data);
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

        public static void main(String [] args){
            array = new Jogador[3922];
            arvore = new ArvoreBinaria();

            leArquivo();
            String parada = MyIO.readString();

            while (parada(parada, "FIM")) {
                int posicao = Integer.parseInt(parada);
                arvore.inserir(array[posicao]);
                parada = MyIO.readString();
            }
            parada = MyIO.readLine();
            long inicio = System.currentTimeMillis();
            while (parada(parada, "FIM")) {
                MyIO.print(parada + " ");
                MyIO.print("raiz ");
                if(arvore.pesquisar(parada)){
                    MyIO.println("SIM");
                }else{
                    MyIO.println("NAO");
                }
                parada = MyIO.readLine();
            }
            long fim = System.currentTimeMillis();

            tempo = fim - inicio;
            criarLog();


        }





    }
