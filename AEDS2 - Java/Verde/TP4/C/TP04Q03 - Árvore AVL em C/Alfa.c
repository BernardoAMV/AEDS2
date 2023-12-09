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
    fp = fopen("/tmp/players.csv", "r");
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





void criarLog (double tempo, int *comp){
    FILE *fp = NULL;
    fp = fopen("806347_avl.txt", "w");
    fprintf(fp, "806347\t %.4fs \t %d comparacoes", tempo, *comp);
    fclose(fp);
}
bool parada(char *str){ // aqui eh a funcao que verifica o criterio de parada
    if(str[0] == 'F' && str[1] == 'I' && str[2] == 'M')
        return false;
    else
        return true;
}


typedef struct Node {
    Jogador* chave;
    struct Node* esquerda;
    struct Node* direita;
    int altura;
} Node;


int altura(Node* node,int *comp) {
    (*comp)++;
    if (node == NULL)
        return 0;
    return node->altura;
}


int max(int a, int b) {
    return (a > b) ? a : b;
}


Node* criarNo(Jogador* chave) {
    Node* node = (Node*)malloc(sizeof(Node));
    node->chave = chave;
    node->esquerda = NULL;
    node->direita = NULL;
    node->altura = 1;
    return node;
}


Node* rotacaoDireita(Node* y, int *comp) {
    Node* x = y->esquerda;
    Node* T2 = x->direita;


    x->direita = y;
    y->esquerda = T2;


    y->altura = max(altura(y->esquerda, comp), altura(y->direita, comp)) + 1;
    x->altura = max(altura(x->esquerda, comp), altura(x->direita, comp)) + 1;

    return x;
}


Node* rotacaoEsquerda(Node* x, int *comp) {
    Node* y = x->direita;
    Node* T2 = y->esquerda;


    y->esquerda = x;
    x->direita = T2;


    x->altura = max(altura(x->esquerda, comp), altura(x->direita, comp)) + 1;
    y->altura = max(altura(y->esquerda, comp), altura(y->direita, comp)) + 1;

    return y;
}


int fatorBalanceamento(Node* node,int *comp) {
    (*comp)++;
    if (node == NULL)
        return 0;
    return altura(node->esquerda, comp) - altura(node->direita, comp);
}


Node* inserir(Node* node, Jogador* chave,int *comp) {

    (*comp)++;
    if (node == NULL)
        return criarNo(chave);
    (*comp)++;
    if (strcmp(chave -> nome, node-> chave -> nome) < 0)
        node->esquerda = inserir(node->esquerda, chave, comp);
    else if (strcmp(chave -> nome, node-> chave -> nome) > 0) {
        (*comp)++;
        node->direita = inserir(node->direita, chave, comp);
    }
    else {
        (*comp)+=2;
        return node;
    }

    node->altura = 1 + max(altura(node->esquerda, comp), altura(node->direita, comp));


    int balanceamento = fatorBalanceamento(node, comp);


    if (balanceamento > 1) {

        (*comp)++;
        if (strcmp(chave -> nome, node-> esquerda -> chave -> nome) < 0)
            return rotacaoDireita(node, comp);


        (*comp)++;
        if (strcmp(chave -> nome, node-> esquerda -> chave -> nome) > 0) {
            node->esquerda = rotacaoEsquerda(node->esquerda, comp);
            return rotacaoDireita(node, comp);
        }
    }


    if (balanceamento < -1) {

        (*comp)++;
        if (strcmp(chave -> nome, node-> direita -> chave -> nome) > 0)
            return rotacaoEsquerda(node, comp);


        (*comp)++;
        if (strcmp(chave -> nome, node-> direita-> chave -> nome) < 0) {
            node->direita = rotacaoDireita(node->direita, comp);
            return rotacaoEsquerda(node, comp);
        }
    }


    return node;
}


void emOrdem(Node* root,int *comp) {
    (*comp)++;
    if (root != NULL) {
        emOrdem(root->esquerda, comp);
        printf("%s ", root->chave->nome);
        emOrdem(root->direita, comp);
    }
}
bool pesquisar(Node* node, char chave [],int *comp) {
    (*comp)++;
    bool resp;
    if (node == NULL) {
        resp = false;
        return resp;
    }
    else if (strcmp(node->chave->nome, chave) == 0) {
        (*comp)++;
        resp = true;
    }
    else if (strcmp(chave, node->chave->nome) < 0){
        printf("%s", "esq ");
        (*comp)++;
    resp = pesquisar(node->esquerda, chave, comp);
}else {
        printf("%s", "dir ");

        resp = pesquisar(node->direita, chave, comp);
    }
    return resp;
}






int main(){
    int comp = 0;
    int m = 3922;
    Jogador* jogadores[m];

    leArquivo(jogadores);
    Node* raiz = NULL;


    int posicao;
    char frase [500];
    scanf("%s", frase);

    while(parada(frase)){ // aqui eu tive que ler a entrada como uma string e utilizar do metodo atoi, para transformar a string em um inteiro ->

        posicao = atoi(frase); // -> tive que fazer isso pois era o unico jeito de comparar numeros com o criterio de parada e utilizar como indice do array

        raiz = inserir(raiz,jogadores[posicao], &comp);

        scanf("%s", frase);

    }
    getchar();
    clock_t inicio = clock();
    fgets(frase, 200, stdin);
    size_t comprimento = strcspn(frase, "\n");
    if (comprimento < sizeof(frase))
        frase[comprimento] = '\0';
    while(parada(frase)){ // aqui eu tive que ler a entrada como uma string e utilizar do metodo atoi, para transformar a string em um inteiro ->
        printf("%s raiz ", frase);

        if(pesquisar(raiz,frase, &comp))
            printf("SIM");
        else
            printf("NAO");
        printf("\n");

        fgets(frase, 200, stdin);
        size_t comprimento = strcspn(frase, "\n");
        if (comprimento < sizeof(frase))
            frase[comprimento] = '\0';

    }
    clock_t fim = clock();
    double total = (double)(fim - inicio)/CLOCKS_PER_SEC;
    criarLog(total, &comp);


}
