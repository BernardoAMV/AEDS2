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
    
    copia -> id = jogador -> id;
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
    fp = fopen("players.csv", "r");
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
bool Binaria(char nome[], int n, Jogador *array[],int *comp)
{

    bool resp = false;
    int dir = n - 1;
    int esq = 0;
    int meio;

    while (esq <= dir)
    {
        meio = (esq + dir) / 2;
 
        if (strcmp(nome, array[meio]->nome) == 0)
        {
        	*comp = *comp+1;

            resp = true;
            esq = n;
        }
        else if (strcmp(nome, array[meio]->nome) > 0)
        {
        	*comp = *comp+2;
            esq = meio + 1;
        }
        else
        {
        	*comp = *comp+2;
            dir = meio - 1;
        }
    }
    return resp;
}

void insercao(Jogador **array, int n)
{
    Jogador* tmp;
    for (int i = 1; i < n; i++)
    {
        tmp = (Jogador*)malloc(sizeof(Jogador));

        clone(array[i], tmp);
 
        int j = i - 1;

        while ((j >= 0) && (strcmp(array[j]->nome, tmp->nome) > 0))
        {
 
            clone(array[j], array[j + 1]);
            j--;
        }
        clone(tmp, array[j + 1]);
        free(tmp);
    }
}

void criarLog (double tempo, int cont){
    FILE *fp = NULL;
    fp = fopen("matricula_binaria.txt", "w");
    fprintf(fp, "806347\t %.4fs \t %d", tempo, cont);
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
    int realTam = 0, cont = 0;

    while(parada(frase)){ // aqui eu tive que ler a entrada como uma string e utilizar do metodo atoi, para transformar a string em um inteiro ->

        posicao = atoi(frase); // -> tive que fazer isso pois era o unico jeito de comparar numeros com o criterio de parada e utilizar como indice do array

        entrada[realTam] = (Jogador *) malloc(sizeof(Jogador));
        entrada[realTam] = jogadores[posicao];

        realTam++;
        scanf("%s", frase);

    }

    insercao(entrada, realTam);

     scanf(" %[^\n]", frase);


    double inicio = clock();
    while(parada(frase)){
        if(Binaria(frase, realTam,entrada, &cont))
            printf("SIM\n");
        else
            printf("NAO\n");
         scanf(" %[^\n]", frase);
    }
    double fim = clock();
    double total = fim - inicio/(double)CLOCKS_PER_SEC;
    criarLog(total,cont);

}

