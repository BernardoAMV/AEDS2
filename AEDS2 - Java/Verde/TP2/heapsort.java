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
public class heapsort {
    public static int cont;
    public static long tempo;
    public static int contMov;

    public static void criarLog(){
        Arq.openWrite("806347_heapsort.txt");
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
    public static Jogador [] sort(Jogador [] array, int n) {
        //Alterar o vetor ignorando a posicao zero
        Jogador [] tmp = new Jogador[n+1];
        for(int i = 0; i < n; i++){
            tmp[i+1] = new Jogador();
            tmp[i+1] = array[i].clone();
            contMov++;
        }
        array = tmp;

        //Contrucao do heap
        for(int tamHeap = 2; tamHeap <= n; tamHeap++){
            construir(tamHeap, array);
        }

        //Ordenacao propriamente dita
        int tamHeap = n;
        while(tamHeap > 1){
            Jogador tmpp = new Jogador();
            tmpp = array[1].clone();
            array[1] = array[tamHeap].clone();
            array[tamHeap] = tmpp;
            contMov += 3;
            tamHeap--;
            reconstruir(tamHeap, array);
        }

        //Alterar o vetor para voltar a posicao zero
        tmp = array;
        array = new Jogador [n];
        for(int i = 0; i < n; i++){
            array[i] = tmp[i+1].clone();
            contMov++;
        }
        return array;
    }
    public static void construir(int tamHeap, Jogador [] array){
        cont++;
        for(int i = tamHeap; i > 1 && (array[i].getAltura() > array[i/2].getAltura()|| (array[i].getAltura() == array[i/2].getAltura()) && (array[i].getNome().compareTo(array[i/2].getNome()) > 0)); i /= 2){
            cont++;
            Jogador tmp = array[i].clone();
            array[i] = array[i/2].clone();
            array[i/2] = tmp;
            contMov += 3;
        }
    }
    public static void reconstruir(int tamHeap, Jogador [] array){
        int i = 1;
        while(i <= (tamHeap/2)){
            int filho = getMaiorFilho(i, tamHeap, array);
            cont++;
            if(array[i].getAltura() < array[filho].getAltura() || (array[i].getAltura() == array[filho].getAltura() && array[i].getNome().compareTo(array[filho].getNome()) < 0) ){
                Jogador tmp = new Jogador();
                tmp = array[i].clone();
                array[i] = array[filho].clone();
                array[filho] = tmp;
                contMov += 3;
                i = filho;
            }else{
                i = tamHeap;
            }
        }
    }

    public static int getMaiorFilho(int i, int tamHeap, Jogador [] array){
        int filho;
        cont++;
        if (2*i == tamHeap || array[2*i].getAltura() > array[2*i+1].getAltura()|| array[2*i].getAltura() == array[2*i+1].getAltura() && array[2*i].getNome().compareTo(array[2*i+1].getNome()) > 0){
            filho = 2*i;
        } else {
            filho = 2*i + 1;
        }
        return filho;
    }
    public static void main(String[] args){
        int n = 3922;
        int realTam = 0;
        cont = 0;
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
        vetor = sort(vetor, realTam);
        long inicio = System.currentTimeMillis();
        for(int i = 0; i < realTam; i++){
            vetor[i].imprimir();
        }

        long fim = System.currentTimeMillis();
        tempo = fim - inicio;
        criarLog();
    }

}