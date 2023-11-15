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


    public void imprimir(){

        MyIO.println("["+getId()+" ## " + getNome() + " ## " +getAltura() + " ## " + getPeso() + " ## " + getAnoNascimento() + " ## " + getUniversidade() + " ## " + getCidadeNascimento() + " ## "+ getEstadoNascimento() +"]");
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



class celula{
    public Jogador elemento;
    public celula prox;
    public celula ant;

    public  celula(){
        this.ant = this.prox = null;
    }
    public  celula(Jogador x){
        this.elemento = x.clone();
        this.prox = this.ant = null;
    }


}

class listaDupla{

    private celula primeiro;
    private celula ultimo;

    public listaDupla(){
        primeiro = new celula();
        ultimo = primeiro;
    }

    public void inserirFim(Jogador x){
        ultimo.prox = new celula(x);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;

    }

    public void inserirInicio(Jogador x){

        celula tmp = new celula(x);

        tmp.ant = primeiro;

        tmp.prox = primeiro.prox;

        primeiro.prox = tmp;
        if(primeiro == ultimo){
            ultimo = tmp;
        }else{
            tmp.prox.ant = tmp;
        }
        tmp = null;

    }

    public Jogador removerFim() throws Exception{
        if(primeiro == ultimo)
            throw new Exception("Erro ao remover (vazia!)");
        Jogador resp = ultimo.elemento;
        ultimo = ultimo.ant;
        ultimo.prox.ant = ultimo.prox = null;
        return resp;
    }
    public Jogador removerInicio() throws Exception{
        if(primeiro == ultimo)
            throw new Exception("Erro ao remover (vazia!)");

        celula tmp = primeiro;
        primeiro = primeiro.prox;
        Jogador elemento = primeiro.elemento.clone();

        tmp = tmp.prox = primeiro.ant = null;

        return elemento;

    }
    public void inserir(Jogador x, int pos) throws Exception{
        int tamanho = tamanho();

        if(pos < 0 || pos > tamanho){
            throw new Exception("erro");
        }else if(pos == 0){
            inserirInicio(x);
        }else if(pos == tamanho){
            inserirFim(x);
        }else {

            celula tmp = new celula(x);
            celula i = primeiro;
            for (int j = 0;j < pos; i = i.prox, j++) ;
            tmp.ant = i;
            tmp.prox = i.prox;
            tmp.ant.prox = tmp.prox.ant = tmp;
            tmp = i = null;
        }
    }
    public Jogador remover(int pos) throws Exception{
        int tamanho = tamanho();
        Jogador elemento = new Jogador();
        if(pos < 0 || pos >= tamanho || primeiro == ultimo) {
            throw new Exception("erro");
        }else if(pos == 0){
            removerInicio();
        }else if(pos == tamanho - 1){
            removerFim();
        }else {

            celula i = primeiro.prox;
            for (int j = 0;j < pos; i = i.prox, j++) ;

            i.ant.prox = i.prox;
            i.prox.ant = i.ant;
            elemento = i.elemento;
            i.prox = i.ant = null;
            i = null;
        }
        return elemento;

    }


    public void mostrar(){
        for(celula i = primeiro.prox; i != null; i = i.prox){
            i.elemento.imprimir();
        }
    }

    public int tamanho(){
        int resp = 0;
        for(celula i = primeiro; i != ultimo; i = i.prox, resp++);
        return resp;
    }


    public void swap(celula i,celula j){

        Jogador tmp;
        tmp = i.elemento.clone();
        i.elemento = j.elemento.clone();
        j.elemento = tmp.clone();

    }
    public void sort() {
        Alfa.comp = 0;
        quicksort(primeiro.prox, ultimo);
    }

    /**
     * Algoritmo de ordenacao Quicksort.
     * @param int esq inicio do array a ser ordenado
     * @param int dir fim do array a ser ordenado
     */
    private void quicksort(celula esq, celula dir) {
        celula i = esq, j = dir;
        celula tmp;
        int cont = 0;
        int end = 1;
        for( tmp = esq ; tmp.prox != dir ; tmp = tmp.prox,end++);

        for(tmp = esq ; cont < (end/2) ; cont++)
            tmp = tmp.prox;

        Jogador pivo = tmp.elemento.clone();


        while (calculaDistancia(i) <= calculaDistancia(j)) {

            Alfa.comp++;
            while ((i.elemento.getEstadoNascimento().compareTo(pivo.getEstadoNascimento())<0) || (i.elemento.getEstadoNascimento().compareTo(pivo.getEstadoNascimento())==0 && i.elemento.getNome().compareTo(pivo.getNome())<0)){
                i = i.prox;
                Alfa.comp++;
            }

            Alfa.comp++;
            while ((j.elemento.getEstadoNascimento().compareTo(pivo.getEstadoNascimento())>0) || (j.elemento.getEstadoNascimento().compareTo(pivo.getEstadoNascimento())==0 && j.elemento.getNome().compareTo(pivo.getNome())>0)){
                j = j.ant;
                Alfa.comp++;
            }
            if (calculaDistancia(i) <= calculaDistancia(j)) {
                swap(i, j);
                Alfa.mov += 3;
                i = i.prox;
                j = j.ant;
            }
        }
        if (calculaDistancia(esq) < calculaDistancia(j))  quicksort(esq, j);
        if (calculaDistancia(i) < calculaDistancia(dir))  quicksort(i, dir);
    }


    public int calculaDistancia(celula fim){
        int resp = 0;
        for(celula tmp = primeiro;tmp!=fim;tmp = tmp.prox)
            resp++;
        return resp;

    }




}
public class Alfa {
   static public int comp, mov = 0;
   static public long tempo;
    private static listaDupla lista;
    private static Jogador [] jogadores;

    public static void operacao(String data) throws Exception {
        String[] entrada = data.split(" ",3);
        int posicao = 0;
        if (entrada.length > 1)
            posicao = Integer.parseInt(entrada[1]);
        Jogador retorno;
        switch (entrada[0]) {
            case "II":
                lista.inserirInicio(jogadores[posicao]);
                break;
            case "IF":
                lista.inserirFim(jogadores[posicao]);
                break;
            case "I*":
                lista.inserir(jogadores[Integer.parseInt(entrada[2])], posicao);
                break;
            case "RI":
                retorno = lista.removerInicio();
                MyIO.println("(R) " + retorno.getNome());
                break;
            case "RF":
                retorno = lista.removerFim();
                MyIO.println("(R) " + retorno.getNome());
                break;
            case "R*":
                retorno = lista.remover(Integer.parseInt(entrada[1]));
                MyIO.println("(R) " + retorno.getNome());
                break;
        }
    }
    public static void criarLog(){
       Arq.openWrite("806347_quicksort.txt");
       Arq.println("806347\t" + tempo + "ms" + "\t" + comp + "comparacoes" + "\t" + mov + "movimentacoes");
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




    public static void main(String[] args) throws Exception {
        int n = 3922;
        int realTam = 0;

        jogadores = new Jogador[n];

        leArquivo(jogadores);
        int posicao;
        String parada = MyIO.readString();
        lista = new listaDupla();

        while (parada(parada, "FIM")) {
            posicao = Integer.parseInt(parada);
            lista.inserirFim(jogadores[posicao]);
            parada = MyIO.readString();
        }
        long inicio = System.currentTimeMillis();
        lista.sort();
        long fim = System.currentTimeMillis();

        tempo = fim - inicio;
        criarLog();

        lista.mostrar();
    }
}