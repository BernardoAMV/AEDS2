#include <stdio.h>

int main()
{

    int n = 0;
    printf("entre com n: ");
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
    printf("Numeros lidos do arquivo:\n");
    while (fscanf(p, "%d", &n) != EOF)
    {
        printf("%d\n", n);
    }
    fclose(p);
    return 0;
}