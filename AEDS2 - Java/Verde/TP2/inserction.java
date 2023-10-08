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
public class inserction {
    public static int cont;
    public static long tempo;

    public static void criarLog(){
        Arq.openWrite("1449516_selecao.txt");
        Arq.println("1449516\t" + tempo + "ms" + "\t" + cont);
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
    static void insercao(Jogador [] array, int n){
        int i, j;
        Jogador temp2 = new Jogador();

        for(i = 1; i < n; i++){

            int temp = array[i].getAnoNascimento();
            temp2 = array[i].clone();
            boolean teste = true;
            j = i - 1;

            while((j >= 0) && (array[j].getAnoNascimento() >= temp) && teste){
               if(array[j].getAnoNascimento() == temp){

                    if (array[j].getNome().compareTo(temp2.getNome()) > 0) {
                        array[j + 1] = array[j].clone();
                        cont++;
                        j--;

                    }else{
                        teste = false;
                    }

                }else{

                    array[j + 1] = array[j].clone();
                    cont++;
                    j--;
                }
            }
            array[j+1] = temp2;
        }

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
        insercao(vetor, realTam);
        long inicio = System.currentTimeMillis();
        for(int i = 0; i < realTam; i++){
            vetor[i].imprimir();
        }

        long fim = System.currentTimeMillis();
        tempo = fim - inicio;
        criarLog();
    }

}