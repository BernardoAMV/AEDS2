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
class No2 {
    public ArvoreBinaria elemento; // Conteudo do no.
    public int altura;
    public No2 esq, dir;  // Filhos da esq e dir.

    /**
     * Construtor da classe.
     * @param elemento Conteudo do no.
     */
    public No2(ArvoreBinaria elemento, int altura) {
        this(elemento, altura, null, null);
    }

    /**
     * Construtor da classe.
     * @param elemento Conteudo do no.
     * @param esq No da esquerda.
     * @param dir No da direita.
     */
    public No2(ArvoreBinaria elemento, int altura, No2 esq, No2 dir) {
        this.elemento = elemento;
        this.altura = altura;
        this.esq = esq;
        this.dir = dir;
    }
}
/**
 * Arvore binaria de pesquisa
 * @author Max do Val Machado
 */
class ArvoreDeArvore {
    private No2 raiz; // Raiz da arvore.

    /**
     * Construtor da classe.
     */
    public ArvoreDeArvore() {
        raiz = null;
    }

    /**
     * Metodo publico iterativo para pesquisar elemento.
     * @param x Elemento que sera procurado.
     * @return <code>true</code> se o elemento existir,
     * <code>false</code> em caso contrario.
     */
    public boolean pesquisar(String y) {
        return pesquisar(y, raiz);
    }

    /**
     * Metodo privado recursivo para pesquisar elemento.
     * @param x Elemento que sera procurado.
     * @param i No em analise.
     * @return <code>true</code> se o elemento existir,
     * <code>false</code> em caso contrario.
     */
    private boolean pesquisar(String y, No2 i) {
        boolean resp =  false;
        if (i != null) {
            Alfa.contador++;
            resp = i.elemento.pesquisar(y);
            if(!resp) {
                MyIO.print("esq ");
                resp = pesquisar(y, i.esq);
            }
            if(!resp) {
                MyIO.print("dir ");
                resp = pesquisar(y, i.dir);
            }
        }
        return resp;
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
    private No2 inserir(Jogador x, No2 i) {
         if(i.altura == x.getAltura() % 15){
            Alfa.contador+=1;
            i.elemento.inserir(x);
        }
        else if (x.getAltura() % 15 < i.altura) {
            Alfa.contador+=2;
            i.esq = inserir(x, i.esq);

        } else if (x.getAltura() % 15 > i.altura) {
            Alfa.contador+=3;
            i.dir = inserir(x, i.dir);

        }

        return i;
    }
    public void inserirNada(int x) {
        raiz = inserirNada(x, raiz);
    }

    /**
     * Metodo privado recursivo para inserir elemento.
     * @param x Elemento a ser inserido.
     * @param i No em analise.
     * @return No em analise, alterado ou nao.
     * @throws Exception Se o elemento existir.
     */
    private No2 inserirNada(int x, No2 i) {
        if (i == null) {
            Alfa.contador+=1;
            ArvoreBinaria arvore = new ArvoreBinaria();
            i = new No2(arvore ,x);
        }
        else if (x < i.altura) {
            Alfa.contador+=2;
            i.esq = inserirNada(x, i.esq);

        } else if (x > i.altura) {
            Alfa.contador+=3;
            i.dir = inserirNada(x, i.dir);

        }

        return i;
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
        boolean resp = false;
        if (i != null) {
            Alfa.contador++;
            if (x.compareTo(i.elemento.getNome()) == 0) {
                resp = true;
            } else {
                if (!resp) {
                    MyIO.print("ESQ ");
                    resp = pesquisar(x, i.esq);
                }

                if (!resp) {
                    MyIO.print("DIR ");
                    resp = pesquisar(x, i.dir);
                }
            }
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

    public static ArvoreDeArvore arvore;
    public static Jogador array [];
    public static int contador = 0;
    public static long tempo;

    public static void criarLog(){
        Arq.openWrite("matrícula_arvoreArvore.txt");
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
        arvore = new ArvoreDeArvore();

        leArquivo();
        String parada = MyIO.readString();
        arvore.inserirNada(7);
        arvore.inserirNada(3);
        arvore.inserirNada(11);
        arvore.inserirNada(1);
        arvore.inserirNada(5);
        arvore.inserirNada(9);
        arvore.inserirNada(13);
        arvore.inserirNada(0);
        arvore.inserirNada(2);
        arvore.inserirNada(4);
        arvore.inserirNada(6);
        arvore.inserirNada(8);
        arvore.inserirNada(10);
        arvore.inserirNada(12);
        arvore.inserirNada(14);

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
