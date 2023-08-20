#include <stdio.h>

int main()
{
    int n = 0;
    printf("Entre com n: ");
    scanf("%d", &n);
    FILE *p = fopen("ex1Arq.txt", "w");
    for (int i = 0; i < n; i++)
    {
        int numero;
        printf("Digite o numero %d: ", i + 1);
        scanf("%d", &numero);
        fprintf(p, "%d\n", numero);
    }
    fclose(p);

    p = fopen("ex1Arq.txt", "r");
    int soma = 0;
    int soma2 = 0;
    for (int i = 0; i < n/2; i++)
    {
        int erro = 0;
        int size = fscanf(p, "%d\n",&soma);
        erro = fseek(p, i * sizeof(char), SEEK_SET);
        printf("%d foi lido, erro foi %d\n", size, erro);

        erro = fseek(p, n - i * sizeof(char), SEEK_SET);
        size = fscanf(p, "%d\n",&soma2);
        printf("%d foi lido, erro foi %d\n", size, erro);
        
        printf("a soma do numero %d mais %d eh %d \n",soma, soma2, soma + soma2);
        soma = 0;
        soma2 = 0;
    }
    fclose(p);
}