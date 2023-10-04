#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

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

    if(!str || strcmp(str, "\0") == 0){
        return "nao informado";

    }
        
    return str;
}

Jogador *clone(Jogador* jogador){
    Jogador *j = (Jogador*) malloc(sizeof(Jogador));
    

}


void imprimir(Jogador *jogador){
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", jogador->id, jogador->nome, jogador->altura, jogador->peso, jogador->anoNascimento, jogador->universidade, jogador->cidadeNascimento, jogador->estadoNascimento);
}
void ler(char* str, Jogador* jogador){
    
    char* token = strsep(&str, ",");
    if(token != NULL){
        jogador->id = atoi(token);
    }
    else
        jogador->id = -1;
    token = strsep(&str, ",");
    strcpy( jogador->nome, validar(token));

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

    token = strsep(&str, "\n");
    strcpy( jogador->estadoNascimento, validar(token));
    
}


void leArquivo(Jogador* jogadores[]){
    FILE *fp = NULL;
    fp = fopen("players.csv", "r");
    char *dados = NULL;

    dados = (char*) malloc(sizeof(char) * 1000);


    fgets(dados, 1000, fp);

    for(int i = 0; i < 3922; i++){
        fgets(dados, 1000, fp);
        jogadores[i] = (Jogador *) malloc(sizeof(Jogador));

        ler(dados, jogadores[i]);
    }

}
bool parada(char *str){
    if(str[0] == 'F' && str[1] == 'I' && str[2] == 'M')
        return false;
    else
    return true;
}


int main(){
    int n = 3922;
    Jogador* jogadores[3922];

    leArquivo(jogadores);


    int posicao;
    char frase [4];
    scanf("%s", frase);
    while(parada(frase)){
        posicao = atoi(frase);
        imprimir(jogadores[posicao]);
        scanf("%s", frase);
    }

}