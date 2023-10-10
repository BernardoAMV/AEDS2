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
public class countingSort {
    public static int cont;
    public static long tempo;
    public static int contMov;

    public static void criarLog(){
        Arq.openWrite("806347_countingsort.txt");
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
    public static void sort(Jogador [] array, int n) {
        //Array para contar o numero de ocorrencias de cada elemento
        int[] count = new int[getMaior(array, n) + 1];
        Jogador[] ordenado = new Jogador[n];
  
        //Inicializar cada posicao do array de contagem 
          for (int i = 0; i < count.length; count[i] = 0, i++, contMov++);
  
        //Agora, o count[i] contem o numero de elemento iguais a i
        for (int i = 0; i < n; count[array[i].getAltura()]++, i++, contMov++);
  
        //Agora, o count[i] contem o numero de elemento menores ou iguais a i
        for(int i = 1; i < count.length; count[i] += count[i-1], i++, contMov++);
  
        //Ordenando
        for(int i = n-1; i >= 0; ordenado[count[array[i].getAltura()]-1] = array[i].clone(), count[array[i].getAltura()]--, i--, contMov++);
  
        //Copiando para o array original
        for(int i = 0; i < n; array[i] = ordenado[i].clone(), i++, contMov++);
        
        insercao(array, n);
     }
     // por conta do counting sort nao ser capaz de ordenar strings, foi utilizado do inserciton sort, devido ao array estar quase ordenado, se tornando o mais eficiente
     public static void insercao(Jogador []novoVetor , int n) {
        Jogador tmp = new Jogador();
        for (int i = 1; i < n; i++) {
            tmp = novoVetor[i].clone();
             int j = i - 1;

         while ((j >= 0)&&(novoVetor[j].getAltura()==tmp.getAltura()&&novoVetor[j].getNome().compareTo(tmp.getNome())>0)){

             novoVetor[j + 1] = novoVetor[j].clone();
            j--;
         }
            novoVetor[j+1] = tmp.clone();
      }
    }
  
  
      /**
       * Retorna o maior elemento do array.
      * @return maior elemento
       */
      public static int getMaior(Jogador [] array, int n) {
          contMov++;
         Jogador maior = array[0].clone();
  
          for (int i = 0; i < n; i++) {
              cont++;
           if(maior.getAltura() < array[i].getAltura()){
               contMov++;
              maior = array[i].clone();
           }
          }
         return maior.getAltura();	
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
         sort(vetor, realTam);
        long inicio = System.currentTimeMillis();
        for(int i = 0; i < realTam; i++){
            vetor[i].imprimir();
        }

        long fim = System.currentTimeMillis();
        tempo = fim - inicio;
        criarLog();
    }

}