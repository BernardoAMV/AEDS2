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
        System.out.println("[" + id + " ## " + nome + " ## " + altura + " ## " + peso + " ## " +anoNascimento + " ## " + universidade + " ## " + cidadeNascimento + " ## " + estadoNascimento + ']');
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
public class mergesort {
    public static int cont;
    public static long tempo;
    public static int contMov;

    public static void criarLog(){
        Arq.openWrite("806347_mergesort.txt");
        Arq.println("806347\t" + tempo + "ms" + "\t" + cont + "comparacoes" + "\t" + contMov + "movimentacoes");
        Arq.close();
    }
    public static void leArquivo(Jogador[] jogadores){
        Arq.openRead("/tmp/players.csv");
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
    public static  void sort(int n, Jogador [] array) {
        mergesort(0, n-1, array);
    }

    /**
     * Algoritmo de ordenacao Mergesort.
     * @param int esq inicio do array a ser ordenado
     * @param int dir fim do array a ser ordenado
     */
    private static void mergesort(int esq, int dir, Jogador [] array) {
        if (esq < dir){
            int meio = (esq + dir) / 2;
            mergesort(esq, meio, array);
            mergesort(meio + 1, dir, array);
            intercalar(esq, meio, dir, array);
        }
    }

    /**
     * Algoritmo que intercala os elementos entre as posicoes esq e dir
     * @param int esq inicio do array a ser ordenado
     * @param int meio posicao do meio do array a ser ordenado
     * @param int dir fim do array a ser ordenado
     */
    public static void intercalar(int esq, int meio, int dir, Jogador [] array){
        int n1, n2, i, j, k;

        //Definir tamanho dos dois subarrays
        n1 = meio-esq+1;
        n2 = dir - meio;

        Jogador [] a1 = new Jogador[n1+1];
        Jogador [] a2 = new Jogador[n2+1];

        //Inicializar primeiro subarray
        for(i = 0; i < n1; i++){
            a1[i] = new Jogador();
            a1[i] = array[esq+i].clone();
        }

        //Inicializar segundo subarray
        for(j = 0; j < n2; j++){
            a2[j] = new Jogador();
            a2[j] = array[meio+j+1].clone();
        }

        //Sentinela no final dos dois arrays
            a1[i] = new Jogador();
      		a1[i].setNome("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
      		a1[i].setUniversidade("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");

      		a2[j] = new Jogador();
      		a2[j].setNome("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
      		a2[j].setUniversidade("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");

        //Intercalacao propriamente dita1
        for(i = j = 0, k = esq; k <= dir; k++){
           if(comparaJogadores(a1[i], a2[j]))  {
               array[k]  = a1[i++].clone();
                contMov++;
            }
           else{
               array[k]  = a2[j++].clone();
                contMov++;
            }
            }
        }
    public static boolean comparaJogadores(Jogador a,Jogador b){


        cont++;
        return ((a.getUniversidade().compareTo(b.getUniversidade()) < 0)||((a.getUniversidade().compareTo(b.getUniversidade())==0) && (a.getNome().compareTo(b.getNome()) < 0)));

    }
        


    public static void main(String[] args){
        int n = 3922;
        int realTam = 0;
        cont = 0;
        contMov = 0;
        Jogador[] jogadores = new Jogador[n];
        Jogador[] vetor = new Jogador[n];

        leArquivo(jogadores);
        int posicao;
        String parada = MyIO.readString();

        while(parada(parada, "FIM")){
            posicao = Integer.parseInt(parada);
            vetor[realTam] = new Jogador();
            vetor[realTam] = jogadores[posicao].clone();
            realTam++;
            parada = MyIO.readString();
        }
        long inicio = System.currentTimeMillis();
        sort(realTam, vetor);
        long fim = System.currentTimeMillis();
        for(int i = 0; i < realTam; i++){
            vetor[i].imprimir();
        }
        criarLog();
    }

}