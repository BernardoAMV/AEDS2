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
class NoAN {
    public boolean cor;
    public Jogador elemento;
    public NoAN esq, dir;

    public NoAN() {
        this(null);
    }

    public NoAN(Jogador elemento) {
        this(elemento, false, null, null);
    }

    public NoAN(Jogador elemento, boolean cor) {
        this(elemento, cor, null, null);
    }

    public NoAN(Jogador elemento, boolean cor, NoAN esq, NoAN dir) {
        this.cor = cor;
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}
/**
 * Arvore binaria de pesquisa
 *
 * @author Max do Val Machado
 */
 class Alvinegra {
    private NoAN raiz; // Raiz da arvore.

    /**
     * Construtor da classe.
     */
    public Alvinegra() {
        raiz = null;
    }

    /**
     * Metodo publico iterativo para pesquisar elemento.
     *
     * @param elemento Elemento que sera procurado.
     * @return <code>true</code> se o elemento existir,
     *         <code>false</code> em caso contrario.
     */
    public boolean pesquisar(String elemento) {
        return pesquisar(elemento, raiz);
    }

    /**
     * Metodo privado recursivo para pesquisar elemento.
     *
     * @param elemento Elemento que sera procurado.
     * @param i        NoAN em analise.
     * @return <code>true</code> se o elemento existir,
     *         <code>false</code> em caso contrario.
     */
    private boolean pesquisar(String elemento, NoAN i) {
        boolean resp;
        if (i == null) {
            resp = false;
        } else if (elemento.compareTo(i.elemento.getNome()) == 0) {
            Alfa.contador++;
            resp = true;
        } else if (elemento.compareTo(i.elemento.getNome()) < 0) {
            Alfa.contador++;
            MyIO.print("esq ");
            resp = pesquisar(elemento, i.esq);
        } else {
            Alfa.contador+=2;
            MyIO.print("dir ");
            resp = pesquisar(elemento, i.dir);
        }
        return resp;
    }



    /**
     * Metodo publico iterativo para inserir elemento.
     *
     * @param elemento Elemento a ser inserido.
     * @throws Exception Se o elemento existir.
     */
    public void inserir(Jogador elemento) {
        // Se a arvore estiver vazia
        if (raiz == null) {
            raiz = new NoAN(elemento);

            // Senao, se a arvore tiver um elemento
        } else if (raiz.esq == null && raiz.dir == null) {
            Alfa.contador++;
            if (elemento.getNome().compareTo(raiz.elemento.getNome()) < 0) {
                raiz.esq = new NoAN(elemento);

            } else {
                raiz.dir = new NoAN(elemento);

            }

            // Senao, se a arvore tiver dois elementos (raiz e dir)
        } else if (raiz.esq == null) {
            Alfa.contador++;
            if (elemento.getNome().compareTo(raiz.elemento.getNome()) < 0) {
                raiz.esq = new NoAN(elemento);

            } else if (elemento.getNome().compareTo(raiz.dir.elemento.getNome()) < 0) {
                Alfa.contador++;
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = elemento;

            } else {
                Alfa.contador++;
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = elemento;

            }
            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, se a arvore tiver dois elementos (raiz e esq)
        } else if (raiz.dir == null) {
            Alfa.contador++;
            if (elemento.getNome().compareTo(raiz.elemento.getNome()) > 0) {
                raiz.dir = new NoAN(elemento);

            } else if (elemento.getNome().compareTo(raiz.esq.elemento.getNome()) > 0) {
                Alfa.contador++;
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = elemento;

            } else {
                Alfa.contador++;
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = elemento;
            }
            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, a arvore tem tres ou mais elementos
        } else {
            inserir(elemento, null, null, null, raiz);
        }
        raiz.cor = false;
    }

    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
        // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if (pai.cor == true) {
            // 4 tipos de reequilibrios e acoplamento
            Alfa.contador++;
            if (pai.elemento.getNome().compareTo(avo.elemento.getNome()) > 0) { // rotacao a esquerda ou direita-esquerda
                Alfa.contador++;
                if (i.elemento.getNome().compareTo(pai.elemento.getNome()) > 0) {
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }
            } else { // rotacao a direita ou esquerda-direita
                Alfa.contador++;
                if (i.elemento.getNome().compareTo(pai.elemento.getNome()) < 0) {
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }
            if (bisavo == null) {
                raiz = avo;
            } else if (avo.elemento.getNome().compareTo(bisavo.elemento.getNome()) < 0) {
                Alfa.contador++;
                bisavo.esq = avo;
            } else {
                Alfa.contador++;
                bisavo.dir = avo;
            }
            // reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        } // if(pai.cor == true)
    }

    /**
     * Metodo privado recursivo para inserir elemento.
     *
     * @param elemento Elemento a ser inserido.
     * @param avo      NoAN em analise.
     * @param pai      NoAN em analise.
     * @param i        NoAN em analise.
     * @throws Exception Se o elemento existir.
     */
    private void inserir(Jogador elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
        if (i == null) {
            Alfa.contador++;
            if (elemento.getNome().compareTo(pai.elemento.getNome()) < 0) {
                i = pai.esq = new NoAN(elemento, true);
            } else {
                i = pai.dir = new NoAN(elemento, true);
            }
            if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
            }
        } else {
            // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if (i == raiz) {
                    i.cor = false;
                } else if (pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                }
            }
            Alfa.contador++;
            if (elemento.getNome().compareTo(i.elemento.getNome()) < 0) {
                inserir(elemento, avo, pai, i, i.esq);
            }
            else if (elemento.getNome().compareTo(i.elemento.getNome()) > 0) {
                Alfa.contador++;
                inserir(elemento, avo, pai, i, i.dir);
            }
            else
                Alfa.contador++;
        }
    }

    private NoAN rotacaoDir(NoAN no) {
        NoAN noEsq = no.esq;
        NoAN noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private NoAN rotacaoEsq(NoAN no) {
        NoAN noDir = no.dir;
        NoAN noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private NoAN rotacaoDirEsq(NoAN no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private NoAN rotacaoEsqDir(NoAN no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }
}
public class Alfa {

    public static Alvinegra arvore;
    public static Jogador array[];
    public static int contador = 0;
    public static long tempo;

    public static void criarLog() {
        Arq.openWrite("806347_alvinegra.txt");
        Arq.println("806347\t" + tempo + "ms" + "\t" + contador + "comparacoes");
        Arq.close();
    }

    public static void leArquivo() {
        Arq.openRead("/tmp/players.csv");
        Arq.readLine(); //remove a primeira linha
        for (int i = 0; i < 3922; i++) {
            String data = Arq.readLine();
            array[i] = new Jogador();
            array[i].ler(data);
        }
    }

    //metodo para identificar a parada, FIM
    static boolean parada(String palavra, String parada) {
        for (int i = 0; i < parada.length(); i++) {
            if (palavra.charAt(i) != parada.charAt(i))
                return true;
        }
        return false;
    }

    public static void main(String [] args){
        array = new Jogador[3922];
        arvore = new Alvinegra();

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