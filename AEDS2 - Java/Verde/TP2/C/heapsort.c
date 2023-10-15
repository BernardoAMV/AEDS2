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
    fp = fopen("/tmp/playersAtualizado.csv", "r");
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
void swapJogador(Jogador* jogador1, Jogador* jogador2)
{

    Jogador* tmp = (Jogador*)malloc(sizeof(Jogador));
    clone(jogador1, tmp);
    clone(jogador2, jogador1);
    clone(tmp, jogador2);

    free(tmp);
}
void construir(Jogador **array, int tamHeap,int *comp, int *mov)
{   (*comp) += 3;
    for (int i = tamHeap; i > 1 && ((array[i]->altura < array[i / 2]->altura) || (array[i]->altura == array[i / 2]->altura && strcmp(array[i]->nome, array[i / 2]->nome) < 0)); i /= 2,*comp=*comp+1)
    {
    	(*comp) += 3;
        swapJogador(array[i], array[i / 2]);
        (*mov) += 3;
    }
}
int getMenorFilho(Jogador * *array, int i, int tamHeap,int *comp, int *mov)
{
    int filho;
    *comp=*comp+1;
    if (2 * i + 1 == tamHeap || ((array[2 * i]->altura > array[2 * i + 1]->altura) || (array[2 * i]->altura == array[2 * i + 1]->altura && strcmp(array[2 * i]->nome, array[2 * i + 1]->nome) > 0)))
    {
        filho = 2 * i + 1;
    }
    else
    {
        filho = 2 * i;
    }
    return filho;
}
//=============================================================================ok
void reconstruir(Jogador * *array, int tamHeap,int *comp, int *mov)
{
    int i = 1;
    while (i <= (tamHeap / 2))
    {
    	*comp=*comp+3;
        int filho = getMenorFilho(array, i, tamHeap,comp, mov);
        if ((array[i]->altura > array[filho]->altura) || (array[i]->altura == array[filho]->altura && strcmp(array[i]->nome,array[filho]->nome)>0))
        {
            swapJogador(array[i], array[filho]);
            (*mov)+=3;
            i = filho;
        }
        else
        {
            i = tamHeap;
        }
    }
}
//=============================================================================ok
void heapsortParcial(Jogador **array, int n, int k, int *comp, int *mov)
{
    //Alterar o vetor ignorando a posicao zero
    Jogador * arrayTmp[n + 1];
    for (int i = 0; i < n; i++)
    {
        arrayTmp[i + 1] = (Jogador *)malloc(sizeof(Jogador));
        clone(array[i], arrayTmp[i + 1]);
    }

    //Contrucao do heap
    for (int tamHeap = 2; tamHeap <= n; tamHeap++)
    {
        construir(arrayTmp, tamHeap,comp, mov);
    }
    int dir;
    int aux = 0;
    //Ordenacao propriamente dita
    int tamHeap = n;
    while (aux < k)
    {
        clone(arrayTmp[1], array[aux]);
        swapJogador(arrayTmp[1], arrayTmp[tamHeap--]);
        (*mov)+=3;
        reconstruir(arrayTmp, tamHeap,comp, mov);
        aux++;
    }
}

  

/*void criarLog (double tempo, int comp, int mov){
    FILE *fp = NULL;
    fp = fopen("806347_radixsort.txt", "w");
    fprintf(fp, "806347\t %.4fs \t %d comparacoes \t %d movimentacoes", tempo, comp, mov);
    fclose(fp);
}*/
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
    heapsortParcial(entrada, realTam,10, &comp, &mov);
    clock_t fim = clock();

    for(int i = 0; i < 10; i++){
        imprimir(entrada[i]);
    }
    
    double total = (double)(fim - inicio)/CLOCKS_PER_SEC;
    //criarLog(total,comp, mov);

}

