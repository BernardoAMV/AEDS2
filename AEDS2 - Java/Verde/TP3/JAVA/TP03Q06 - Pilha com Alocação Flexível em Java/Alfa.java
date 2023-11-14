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



class celula{
    public Jogador elemento;
    public celula prox;

    public  celula(){}
    public  celula(Jogador x){
        this.elemento = x;
        this.prox = null;
    }


}

class pilhaflexivel{

    public celula topo;


    public pilhaflexivel(){
        topo = new celula();
    }

    public void inserirInicio(Jogador x){

        celula tmp = new celula(x);

        tmp.prox = topo.prox;

        topo.prox = tmp;

        tmp = null;

    }


    public Jogador removerInicio() {


        celula tmp = topo;
        topo = topo.prox;
        Jogador elemento = topo.elemento;

        tmp = tmp.prox = null;

        return elemento;

    }



    public void mostrar(){

        int cont = 0;
        for(celula i = topo.prox; i != null; i = i.prox, cont++){
            System.out.print("[" + cont + "] ");
            i.elemento.imprimir();
        }
    }

    public int tamanho(){
        int resp = 0;
        for(celula i = topo.prox; i != null; i = i.prox, resp++);
        return resp;
    }

    public celula get(int pos){
        celula resp = new celula();
        celula i = topo.prox;
        for(int j = 0; j < pos; i = i.prox, j++);
        resp = i;
        i = null;

        return resp;

    }
    public void swap(int pos1, int pos2){
        celula i =topo.prox;
        celula j = topo.prox;
        celula tmp = new celula();
        for(int k = 0; k < pos1; i=i.prox, k++);
        for(int l = 0; l < pos2; j=j.prox, l++);
        tmp.elemento = i.elemento;
        i.elemento = j.elemento;
        j.elemento = tmp.elemento;

    }



}
public class Alfa {
    private static pilhaflexivel pilha;
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
        pilha = new pilhaflexivel();

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
        //Aqui estou criando uma pilha inversa a pilha original para printar
        pilhaflexivel pilhaInversa = new pilhaflexivel();
        celula tmp;
                for(tmp = pilha.topo.prox; tmp != null; tmp = tmp.prox){
                    pilhaInversa.inserirInicio(tmp.elemento);
                }
                //tmp = tmp.prox = null;

        pilhaInversa.mostrar();
    }
}