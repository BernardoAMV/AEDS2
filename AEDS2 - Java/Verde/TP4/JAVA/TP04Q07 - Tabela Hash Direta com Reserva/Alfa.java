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
 class Hash {
    Jogador tabela[];
    int m1, m2, m, reserva;
    final Jogador NULO = null;

    public Hash() {
        this(21, 9);
    }

    public Hash(int m1, int m2) {
        this.m1 = m1;
        this.m2 = m2;
        this.m = m1 + m2;
        this.tabela = new Jogador[this.m];
        for (int i = 0; i < m1; i++) {
            tabela[i] = NULO;
        }
        reserva = 0;
    }

    public int h(Jogador elemento) {
        return elemento.getAltura() % m1;
    }

    public boolean inserir(Jogador elemento) {
        boolean resp = false;
        if (elemento != NULO) {
            int pos = h(elemento);
            Alfa.contador++;
            if (tabela[pos] == NULO) {
                tabela[pos] = elemento;
                resp = true;
            } else if (reserva < m2) {
                tabela[m1 + reserva] = elemento;
                reserva++;
                resp = true;
            }
        }
        return resp;
    }

    public boolean pesquisar(String elemento) {
        boolean resp = false;
        for (int i = 0; i < m && !resp; i++) {
            Alfa.contador++;
            if(tabela[i] != NULO) {
                if (tabela[i].getNome().compareTo(elemento) == 0)
                    resp = true;
            }
        }
        return resp;
    }
}
public class Alfa {

    public static Hash tabela;
    public static Jogador array[];
    public static int contador = 0;
    public static long tempo;

    public static void criarLog() {
        Arq.openWrite("806347_hashReserva.txt");
        Arq.println("806347\t" + tempo + "ms" + "\t" + contador + "comparacoes");
        Arq.close();
    }

    public static void leArquivo() {
        Arq.openRead("players.csv");
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

    public static void main(String[] args) {
        array = new Jogador[3922];
        tabela = new Hash(21, 9);

        leArquivo();
        String parada = MyIO.readString();

        while (parada(parada, "FIM")) {
            int posicao = Integer.parseInt(parada);
            tabela.inserir(array[posicao]);
            parada = MyIO.readString();
        }
        parada = MyIO.readLine();
        long inicio = System.currentTimeMillis();
        while (parada(parada, "FIM")) {
            MyIO.print(parada + " ");
            if (tabela.pesquisar(parada)) {
                MyIO.println("SIM");
            } else {
                MyIO.println("NAO");
            }
            parada = MyIO.readLine();
        }
        long fim = System.currentTimeMillis();

        tempo = fim - inicio;
        criarLog();


    }

}



