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


void imprimir(Jogador * jogador){
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", jogador->id, jogador->nome, jogador->altura, jogador->peso, jogador->anoNascimento, jogador->universidade, jogador->cidadeNascimento, jogador->estadoNascimento);
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
    char *dados = NULL;

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
void selecao(Jogador *array[], int n, int i, int *comp, int *mov) {
    if (i < (n - 1)) {
        
    

    int menor = i; // Inicializa o índice do menor elemento
    encontrarMenor(array, n, i + 1, &menor, comp);

        Jogador *tmp = (Jogador*)malloc(sizeof(Jogador));

        clone(array[menor], tmp);
        clone(array[i], array[menor]);
        clone(tmp, array[i]);

        (*mov) += 3; // Contagem de movimentações

        free(tmp);

        selecao(array, n, i + 1, comp, mov);
    }
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

int main(){
    int n = 3922;
    Jogador* jogadores[n];

    leArquivo(jogadores);


    int posicao;
    char frase [500];
    scanf("%s", frase);
    Jogador* entrada[n];
    int realTam = 0, comp = 0, mov = 0;

    while(parada(frase)){ // aqui eu tive que ler a entrada como uma string e utilizar do metodo atoi, para transformar a string em um inteiro ->

        posicao = atoi(frase); // -> tive que fazer isso pois era o unico jeito de comparar numeros com o criterio de parada e utilizar como indice do array

        entrada[realTam] = (Jogador *) malloc(sizeof(Jogador));
        entrada[realTam] = jogadores[posicao];

        realTam++;
        scanf("%s", frase);

    }
    clock_t inicio = clock();
    selecao(entrada, realTam, 0, &comp, &mov);
    clock_t fim = clock();

    for(int i = 0; i < realTam; i++){
        imprimir(entrada[i]);
    }
    
    double total = (double)(fim - inicio)/CLOCKS_PER_SEC;
    criarLog(total,comp, mov);

}

