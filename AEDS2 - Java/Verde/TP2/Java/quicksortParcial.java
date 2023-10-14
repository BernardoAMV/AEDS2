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
public class quicksortParcial {
    public static int cont;
    public static long tempo;
    public static int contMov;

    /*public static void criarLog(){
        Arq.openWrite("806347_quickS.txt");
        Arq.println("806347\t" + tempo + "ms" + "\t" + cont + "comparacoes" + "\t" + contMov + "movimentacoes");
        Arq.close();
    }*/
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
   public static void QuicksortParcial(Jogador [] array, int esq, int dir, int k){
        Jogador pivo = new Jogador();
        pivo = array[ (esq+dir) / 2].clone();
        int i = esq, j = dir;
        while(i <= j){
            while ((array[i].getEstadoNascimento().compareTo(pivo.getEstadoNascimento())<0) || (array[i].getEstadoNascimento().compareTo(pivo.getEstadoNascimento())==0 && array[i].getNome().compareTo(pivo.getNome())<0))
                i++;

            while ((array[j].getEstadoNascimento().compareTo(pivo.getEstadoNascimento())>0) || (array[j].getEstadoNascimento().compareTo(pivo.getEstadoNascimento())==0 && array[j].getNome().compareTo(pivo.getNome())>0))
                j--;

            if(i <= j)
            {swap(array, i, j); i++; j--; }
        }
        if(esq < j)
            QuicksortParcial(array,esq, j, k);
        if(i < k && i < dir)
            QuicksortParcial(array,i, dir, k);
    }
    public static void swap(Jogador [] array, int i, int j){
        Jogador temp = new Jogador();
        temp = array[i].clone();
        array[i] = array[j].clone();
        array[j] = temp.clone();
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
        QuicksortParcial(vetor,0 ,realTam - 1, 10);
        long fim = System.currentTimeMillis();
        for(int i = 0; i < 10; i++){
            vetor[i].imprimir();
        }
        //criarLog();
    }

}