#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>
#include <err.h>

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




/*void criarLog (double tempo, int comp, int mov){
    FILE *fp = NULL;
    fp = fopen("806347_selecaoRecursiva.txt", "w");
    fprintf(fp, "806347\t %.4fs \t %d comparacoes \t %d movimentacoes", tempo, comp, mov);
    fclose(fp);
}*/
bool parada(char *str){ // aqui eh a funcao que verifica o criterio de parada
    if(str[0] == 'F' && str[1] == 'I' && str[2] == 'M')
        return false;
    else
        return true;
}

/**
/"Classe Lista"
**/

/**
 * fila estatica
 * @author Max do Val Machado
 * @version 2 01/2015
 */

#define MAXTAM    5


typedef struct Celula {
    Jogador* elemento;        // Elemento inserido na celula.
    struct Celula* prox; // Aponta a celula prox.
} Celula;

Celula* novaCelula(Jogador* elemento) {
    Celula* nova = (Celula*) malloc(sizeof(Celula));
    nova->elemento = elemento;
    nova->prox = NULL;
    return nova;
}

//FILA PROPRIAMENTE DITA ========================================================
Celula* primeiro;
Celula* ultimo;


/**
 * Cria uma fila sem elementos (somente no cabeca).
 */
void start () {
    Jogador* jogador = (Jogador*) malloc(sizeof(Jogador));
    primeiro = novaCelula(jogador);
    ultimo = primeiro;
}

/**
 * Remove elemento da fila (politica FIFO).
 * @return Elemento removido.
 */
Jogador* remover() {
    if (primeiro == ultimo) {
        errx(1, "Erro ao remover!");
    }
    Celula* tmp = primeiro;
    primeiro = primeiro->prox;
    ultimo -> prox = primeiro;
    Jogador* resp = primeiro->elemento;
    tmp->prox = NULL;
    free(tmp);
    tmp = NULL;
    return resp;
}

int tamanho()
{
    //mostrar();
    int soma = 0;
    Celula *i;
    for (i = primeiro->prox; i != primeiro && i != NULL; i = i->prox)
        soma++;

    return soma;
}


/**
 * Insere elemento na fila (politica FIFO).
 * @param x int Elemento a inserir.
 */
void inserir(Jogador* x) {

    if(tamanho() == MAXTAM){
        remover();
        ultimo->prox = novaCelula(x);
        ultimo = ultimo->prox;
        ultimo -> prox = primeiro;

    }
    ultimo->prox = novaCelula(x);
    ultimo = ultimo->prox;
}





/**
 * Mostra os elementos separados por espacos.
 */
void mostrar() {
    Celula* i;
    int cont = 0;
    for (i = primeiro->prox; i != primeiro; i = i->prox) {
        printf("[%d] ", cont++);
        imprimir(i ->elemento);
    }
}


int Round(float number)
{
    return (number >= 0) ? (int)(number + 0.5) : (int)(number - 0.5);
}
//tira media das alturas de cada jogador na fila
int mediaAltura(){
    Celula *i;
    int soma = 0;
    for (i = primeiro->prox; i != primeiro && i != NULL; i = i->prox)
        soma += i -> elemento -> altura;

    float media = (float)soma/tamanho();

    return Round(media);

}



void operacao(char* data, Jogador* jogadores []) {
    char *divisao[3];

    divisao[0] = strsep(&data, " ");
    divisao[1] = strsep(&data, " ");

    Jogador *jogador;
    int index = 0;

    if (divisao[1] != NULL)
        index = atoi(divisao[1]);

    if (strcmp("I", divisao[0]) == 0) {
        inserir(jogadores[index]);
        printf("%d\n", mediaAltura());

    } else if (strcmp("R", divisao[0]) == 0) {
        jogador = remover();
        printf("(R) %s\n", jogador->nome);
        //free(jogador);


    }
}






int main(){
    int m = 3922;
    Jogador* jogadores[m];

    leArquivo(jogadores);
    start();


    int posicao;
    char frase [500];
    scanf("%s", frase);

    while(parada(frase)){ // aqui eu tive que ler a entrada como uma string e utilizar do metodo atoi, para transformar a string em um inteiro ->

        posicao = atoi(frase); // -> tive que fazer isso pois era o unico jeito de comparar numeros com o criterio de parada e utilizar como indice do array

        inserir(jogadores[posicao]);
        printf("%d\n", mediaAltura());

        scanf("%s", frase);

    }
    int a;
    int* quanti = (int*) malloc(sizeof(int));
    scanf("%d",quanti);

    int* i = (int*) malloc(sizeof(int));

    for(*i = 0; *i < *quanti; *i = *i + 1){
        char data [50];
        scanf(" %[^\n]", data);
        operacao(data, jogadores);
    }
    free(quanti);
    free(i);
    mostrar();


}

