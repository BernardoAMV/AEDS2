import java.util.*;
import java.lang.*;



class Jogador {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;
    public Jogador(){}
    public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento){
        setId(id);
        setNome(nome);
        setAltura(altura);
        setPeso(peso);
        setUniversidade(universidade);
        setAnoNascimento(anoNascimento);
        setCidadeNascimento(cidadeNascimento);
        setEstadoNascimento(estadoNascimento);
    }
    public int getId(){
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
    public void setId(int id){
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
    public String validar(String str){
        if(str.isEmpty())
            str = "nao informado";
        return str;
    }
    public Jogador clone(){
        return new Jogador(getId(),getNome(),getAltura(),getPeso(),getUniversidade(),getAnoNascimento(),getCidadeNascimento(),getEstadoNascimento());
    }


    public void imprimir() {
        System.out.println("## " + getNome() + " ## " +getAltura() + " ## " + getPeso() + " ## " + getAnoNascimento() + " ## " + getUniversidade() + " ## " + getCidadeNascimento() + " ## "+ getEstadoNascimento() +" ##");
    }
    public void ler(String jogador){

        String[] dados = jogador.split(",", 8);
        setId(dados[0]!="" ?Integer.parseInt(dados[0]): -1);
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
 * Lista estatica
 * @author Max do Val Machado
 * @version 2 01/2015
 */
class pilhaSequencial {
    private Jogador[] array;
    private int n;


    /**
     * Construtor da classe.
     */
    public pilhaSequencial() {
        this(3922);
    }


    /**
     * Construtor da classe.
     *
     * @param tamanho Tamanho da lista.
     */
    public pilhaSequencial(int tamanho) {
        array = new Jogador[tamanho];
        n = 0;
    }


    /**
     * Insere um elemento na primeira posicao da lista e move os demais
     * elementos para o fim da lista.
     *
     * @param x int elemento a ser inserido.
     * @throws Exception Se a lista estiver cheia.
     */
    public void inserirInicio(Jogador x) throws Exception {

        //validar insercao
        if (n >= array.length) {
            throw new Exception("Erro ao inserir! Lista cheia!");
        }

        //levar elementos para o fim do array
        for (int i = n; i > 0; i--) {
            array[i] = array[i - 1].clone();
        }

        array[0] = x.clone();
        n++;
    }



    /**
     * Remove um elemento da primeira posicao da lista e movimenta
     * os demais elementos para o inicio da mesma.
     *
     * @return resp int elemento a ser removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public Jogador removerInicio() throws Exception {

        //validar remocao
        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }

        Jogador resp = array[0].clone();
        n--;

        for (int i = 0; i < n; i++) {
            array[i] = array[i + 1].clone();
        }

        return resp;
    }


    /**
     * Mostra os elementos da lista separados por espacos.
     */
    public void mostrar() {
        for (int i = n - 1; i > -1; i--) {
            System.out.print("[" + (n - 1 - i) + "] ");
            array[i].imprimir();
        }
    }


    /**
     * Procura um elemento e retorna se ele existe.
     *
     * @param x int elemento a ser pesquisado.
     * @return <code>true</code> se o array existir,
     * <code>false</code> em caso contrario.
     */
    public boolean pesquisar(int x) {
        boolean retorno = false;
        for (int i = 0; i < n && retorno == false; i++) {
            retorno = (array[i].getId() == x);
            ;
        }
        return retorno;
    }

}
public class Alfa {
    private static pilhaSequencial pilha;
    private static Jogador [] jogadores;

    public static void operacao(String data) throws Exception {
        String[] entrada = data.split(" ",3);
        int posicao = 0;
        if (entrada.length > 1)
            posicao = Integer.parseInt(entrada[1]);
        Jogador retorno;
        switch (entrada[0]) {
            case "I":
                pilha.inserirInicio(jogadores[posicao]);
                break;
            case "R":
                retorno = pilha.removerInicio();
                MyIO.println("(R) " + retorno.getNome());
                break;
        }
    }
    /*public static void criarLog(){
       Arq.openWrite("806347_selecao.txt");
       Arq.println("806347\t" + tempo + "ms" + "\t" + cont + "comparacoes" + "\t" + contMov + "movimentacoes");
       Arq.close();
   }*/
    public static void leArquivo(Jogador[] jogadores){
        Arq.openRead("players.csv");
        Arq.readLine(); //remove a primeira linha
        for(int i = 0; i < 3922; i++){
            String data = Arq.readLine();
            jogadores[i] = new Jogador();
            jogadores[i].ler(data);
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



    public static void main(String[] args) throws Exception {
        int n = 3922;
        int realTam = 0;

        jogadores = new Jogador[n];

        leArquivo(jogadores);
        int posicao;
        String parada = MyIO.readString();
        pilha = new pilhaSequencial(n);

        while (parada(parada, "FIM")) {
            posicao = Integer.parseInt(parada);
            pilha.inserirInicio(jogadores[posicao]);
            parada = MyIO.readString();
        }

        int quant = MyIO.readInt();
        for (int i = 0; i < quant; i++) {
            parada = MyIO.readLine();
            operacao(parada);

        }
        pilha.mostrar();
    }
}