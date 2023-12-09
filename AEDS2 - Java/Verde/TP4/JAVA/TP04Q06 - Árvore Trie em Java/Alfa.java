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
class No {
    public char elemento;
    public final int tamanho = 255;
    public No[] prox;
    public boolean folha;

    public No (){
        this(' ');
    }

    public No (char elemento){
        this.elemento = elemento;
        prox = new No [tamanho];
        for (int i = 0; i < tamanho; i++) prox[i] = null;
        folha = false;
    }

    public static int hash (char x){
        return (int)x;
    }
}
class ArvoreTrie {
    public No raiz;

    public ArvoreTrie(){
        raiz = new No();
    }


    public boolean pesquisar(String s) {
        return pesquisar(s, raiz, 0);
    }

    public boolean pesquisar(String s, No no, int i) {
        boolean resp = false;
        if(no.prox[s.charAt(i)] == null){
            Alfa.contador++;
            resp = false;
        } else if(i == s.length() - 1){
            Alfa.contador+=2;
            resp = (no.prox[s.charAt(i)].folha == true);
        } else if(i < s.length() - 1 ){
            Alfa.contador+=3;
            resp = pesquisar(s, no.prox[s.charAt(i)], i + 1);
        }
        Alfa.contador+=3;
        return resp;
    }

    public void inserir(String s) {
        inserir(s, raiz, 0);
    }

    private void inserir(String s, No no, int i) {
            if (no.prox[s.charAt(i)] == null) {
                Alfa.contador++;
                no.prox[s.charAt(i)] = new No(s.charAt(i));
                Alfa.contador++;
                if (i == s.length() - 1) {
                    no.prox[s.charAt(i)].folha = true;
                } else {
                    inserir(s, no.prox[s.charAt(i)], i + 1);
                }

            } else if (no.prox[s.charAt(i)].folha == false && i < s.length() - 1) {
                Alfa.contador += 2;
                inserir(s, no.prox[s.charAt(i)], i + 1);

            }
            Alfa.contador += 2;

    }

    public void mostrar(){
        mostrar("", raiz);
    }

    public void mostrar(String s, No no) {
        if(no.folha == true){
        } else {
            for(int i = 0; i < no.prox.length; i++){
                if(no.prox[i] != null){
                    mostrar(s + no.elemento, no.prox[i]);
                }
            }
        }
    }
    public void Merge(ArvoreTrie arvore){
        Merge( arvore.raiz, "");
    }
    private void Merge(No no, String z){

        for(int i = 0; i < no.prox.length; i++){
            Alfa.contador++;
            if(no.prox[i] != null){
                No y = no.prox[i];
                String s = z + y.elemento;
                if(y.folha) {
                    inserir(s);
                    //System.out.println("Inserindo " + s);
                }


                Merge(y, s);
            }
        }
    }
}
public class Alfa {

    public static ArvoreTrie arvore;
    public static ArvoreTrie arvore2;
    public static Jogador array[];
    public static int contador = 0;
    public static long tempo;

    public static void criarLog() {
        Arq.openWrite("806347_arvoreTrie.txt");
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
        arvore = new ArvoreTrie();
        arvore2 = new ArvoreTrie();

        leArquivo();
        String parada = MyIO.readString();

        while (parada(parada, "FIM")) {
            int posicao = Integer.parseInt(parada);
            arvore.inserir(array[posicao].getNome());
            MyIO.print(array[posicao].getId() + "\t" + array[posicao].getNome());
            MyIO.print("\n");
            parada = MyIO.readString();
        }
        parada = MyIO.readLine();
        while (parada(parada, "FIM")) {
            int posicao = Integer.parseInt(parada);
            arvore2.inserir(array[posicao].getNome());
            MyIO.print(array[posicao].getId() + "\t" + array[posicao].getNome());
            MyIO.print("\n");
            parada = MyIO.readString();
        }
        parada = MyIO.readLine();

        long inicio = System.currentTimeMillis();
        arvore.Merge(arvore2);

        while (parada(parada, "FIM")) {
            MyIO.print(parada + " ");
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