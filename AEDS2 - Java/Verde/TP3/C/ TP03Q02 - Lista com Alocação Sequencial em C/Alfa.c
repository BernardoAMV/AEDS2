#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

typedef struct Jogador{

    int id;
    char nome[100];
    int altura;
    int peso;
    char universidade[100];
    int anoNascimento;
    char cidadeNascimento[100];
    char estadoNascimento[100];
} Jogador;

char* validar(char str[]){

    if(!str || strcmp(str, "\0") == 0){ //se o campo for vazio, nao informado eh inserido

        return "nao informado";

    }

    return str;
}

void clone(Jogador * jogador, Jogador * copia){

    copia->id = jogador->id;
    strcpy(copia->nome, jogador->nome);
    copia->altura = jogador->altura;
    copia->peso = jogador->peso;
    strcpy(copia->universidade, jogador->universidade);
    copia->anoNascimento = jogador->anoNascimento;
    strcpy(copia->cidadeNascimento, jogador->cidadeNascimento);
    strcpy(copia->estadoNascimento, jogador->estadoNascimento);

}


void imprimir(Jogador * jogador) {
    printf("## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", jogador->nome, jogador->altura, jogador->peso,
           jogador->anoNascimento, jogador->universidade, jogador->cidadeNascimento, jogador->estadoNascimento);
}

void ler(char* str, Jogador* jogador){

    char* token = strsep(&str, ",");  // aqui estou separando a string, a linha inteira do arquivo, usando como criterio de separacao a virgula
    if(token != NULL){
        jogador->id = atoi(token);
    }
    else
        jogador->id = -1;
    token = strsep(&str, ",");
    strcpy( jogador->nome, validar(token)); // aqui estou chamando a funcao validar para os atributos do tipo char, se nao houver nada, o nao informado eh inserido

    token = strsep(&str, ",");
    jogador->altura = atoi(token);

    token = strsep(&str, ",");
    jogador->peso = atoi(token);

    token = strsep(&str, ",");
    strcpy( jogador->universidade, validar(token));

    token = strsep(&str, ",");
    jogador->anoNascimento = atoi(token);

    token = strsep(&str, ",");
    strcpy(jogador->cidadeNascimento, validar(token));

    token = strsep(&str, "\n"); // aqui o criterio de separacao foi diferente, como o final da linha acaba em \n , tive que elimina-lo para o print sair corretamente
    strcpy( jogador->estadoNascimento, validar(token));

}


void leArquivo(Jogador* jogadores[]){
    FILE *fp = NULL;
    fp = fopen("players.csv", "r");
    char *dados;

    dados = (char*) malloc(sizeof(char) * 1000);


    fgets(dados, 1000, fp);

    for(int i = 0; i < 3922; i++){ // aqui eu coloquei todos os jogadores em um array de jogadores, em que cada iteracao eu instanciava um jogador novo em cada indice do array
        fgets(dados, 1000, fp);
        jogadores[i] = (Jogador *) malloc(sizeof(Jogador));

        ler(dados, jogadores[i]);
    }
    free(dados);

}
int encontrarMenor(Jogador *array[], int n, int j, int *menor, int *comp) {
    if (j < n) {


        (*comp)++; // Contagem de comparações
        if (strcmp(array[*menor]->nome, array[j]->nome) > 0) {
            (*menor) = j; // Atualiza o índice do menor elemento
        }

        (*menor) = encontrarMenor(array, n, j + 1, menor, comp);
    }
    return *menor;
}




void criarLog (double tempo, int comp, int mov){
    FILE *fp = NULL;
    fp = fopen("806347_selecaoRecursiva.txt", "w");
    fprintf(fp, "806347\t %.4fs \t %d comparacoes \t %d movimentacoes", tempo, comp, mov);
    fclose(fp);
}
bool parada(char *str){ // aqui eh a funcao que verifica o criterio de parada
    if(str[0] == 'F' && str[1] == 'I' && str[2] == 'M')
        return false;
    else
        return true;
}

//Classe lista sequencial

#define MAXTAM    3922

Jogador* array[MAXTAM];   // Elementos da pilha
int n;               // Quantidade de array.


/**
 * Inicializacoes
 */
void start(){
    n = 0;
}


/**
 * Insere um elemento na primeira posicao da lista e move os demais
 * elementos para o fim da
 * @param x int elemento a ser inserido.
 */
void inserirInicio(Jogador* x) {
    int i;

    //validar insercao
    if(n >= MAXTAM){
        printf("Erro ao inserir!");
        exit(1);
    }

    //levar elementos para o fim do array
    for(i = n; i > 0; i--){
        array[i] = (Jogador*) malloc(sizeof(Jogador));
        clone(array[i - 1], array[i]);
    }
    array[0] = (Jogador*) malloc(sizeof(Jogador));
    clone(x, array[0]);
    n++;
}


/**
 * Insere um elemento na ultima posicao da
 * @param x int elemento a ser inserido.
 */
void inserirFim(Jogador* x) {

    //validar insercao
    if(n >= MAXTAM){
        printf("Erro ao inserir!");
        exit(1);
    }
    array[n] = (Jogador*) malloc(sizeof(Jogador));
    clone(x, array[n]);

    n++;
}


/**
 * Insere um elemento em uma posicao especifica e move os demais
 * elementos para o fim da
 * @param x int elemento a ser inserido.
 * @param pos Posicao de insercao.
 */
void inserir(Jogador* x, int pos) {
    int i;

    //validar insercao
    if(n >= MAXTAM || pos < 0 || pos > n){
        printf("Erro ao inserir!");
        exit(1);
    }

    //levar elementos para o fim do array
    array[n] = (Jogador*) malloc(sizeof(Jogador));
    for(i = n; i > pos; i--){
        clone(array[i - 1], array[i]);
    }
    array[pos] = (Jogador*) malloc(sizeof(Jogador));
    clone(x, array[pos]);


    n++;
}


/**
 * Remove um elemento da primeira posicao da lista e movimenta
 * os demais elementos para o inicio da mesma.
 * @return resp int elemento a ser removido.
 */
Jogador* removerInicio() {
    int i;
    Jogador* resp;

    //validar remocao
    if (n == 0) {
        printf("Erro ao remover!");
        exit(1);
    }
    clone(array[0], resp);

    n--;

    for(i = 0; i < n; i++){
        clone(array[i + 1], array[i]);
    }

    return resp;
}


/**
 * Remove um elemento da ultima posicao da
 * @return resp int elemento a ser removido.
 */
Jogador* removerFim() {

    //validar remocao
    if (n == 0) {
        printf("Erro ao remover!");
        exit(1);
    }

    return array[--n];
}


/**
 * Remove um elemento de uma posicao especifica da lista e
 * movimenta os demais elementos para o inicio da mesma.
 * @param pos Posicao de remocao.
 * @return resp int elemento a ser removido.
 */
Jogador* remover(int pos) {
    int i;
    Jogador* resp = (Jogador*) malloc(sizeof(Jogador));

    //validar remocao
    if (n == 0 || pos < 0 || pos >= n) {
        printf("Erro ao remover!");
        exit(1);
    }
    clone(array[pos], resp);

    n--;

    for(i = pos; i < n; i++){
        clone(array[i + 1], array[i]);
    }

    return resp;
}


/**
 * Mostra os array separados por espacos.
 */
void mostrar (){
    int i;

    for(i = 0; i < n; i++){
         imprimir(array[i]);
    }
}


/**
 * Procura um array e retorna se ele existe.
 * @param x int elemento a ser pesquisado.
 * @return <code>true</code> se o array existir,
 * <code>false</code> em caso contrario.
 */
bool pesquisar(Jogador* x) {
    bool retorno = false;
    int i;

    for (i = 0; i < n && retorno == false; i++) {
        retorno = strcmp(array[i] -> nome, x ->nome);
    }
    return retorno;
}

void operacao(char* data, Jogador* jogadores []){
    char *divisao[3];

    divisao[0] = strsep(&data, " ");
    divisao[1] = strsep(&data, " ");
    divisao[2] = strsep(&data, " ");

    Jogador* jogador;
    int index = 0;

    if(divisao[1] != NULL)
        index = atoi(divisao[1]);

    if (strcmp("II", divisao[0]) == 0) {
        inserirInicio(jogadores[index]);

    }else if (strcmp("IF", divisao[0]) == 0) {
        inserirFim(jogadores[index]);

    } else if (strcmp("RI", divisao[0]) == 0) {
        jogador = removerInicio();
        printf("(R) %s\n", jogador->nome);
        //free(jogador);

    } else if (strcmp("RF", divisao[0]) == 0) {
        jogador = removerFim();
        printf("(R) %s\n", jogador->nome);
        //free(jogador);

    } else if (strcmp("R*", divisao[0]) == 0) {
        jogador = remover(index);
        printf("(R) %s\n", jogador->nome);
       free(jogador);

    } else if (strcmp("I*", divisao[0]) == 0) {

        inserir(jogadores[atoi(divisao[2])], index);
    }

    }







int main(){
    int m = 3922;
    Jogador* jogadores[m];

    leArquivo(jogadores);


    int posicao;
    char frase [500];
    scanf("%s", frase);
    Jogador* entrada[n];

    while(parada(frase)){ // aqui eu tive que ler a entrada como uma string e utilizar do metodo atoi, para transformar a string em um inteiro ->

        posicao = atoi(frase); // -> tive que fazer isso pois era o unico jeito de comparar numeros com o criterio de parada e utilizar como indice do array

        inserirFim(jogadores[posicao]);

        scanf("%s", frase);

    }
    int a;
    int* quant = (int*) malloc(sizeof(int));
    scanf("%d",quant);

    int* i = (int*) malloc(sizeof(int));

    for(*i = 0; *i < *quant; *i = *i + 1){
        char data [50];
        scanf(" %[^\n]", data);
        operacao(data, jogadores);
    }
free(quant);
    free(i);
    for(int i = 0; i < n; i++){
        printf("[%d] ", i);
        imprimir(array[i]);
    }


}

