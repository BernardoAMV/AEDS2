#include <stdio.h>

typedef struct Celula{
    int elemento;
    struct Celula *prox;
} Celula;

int main(){
    Celula *newCel = (Celula*) malloc(sizeof(Celula));
    
}