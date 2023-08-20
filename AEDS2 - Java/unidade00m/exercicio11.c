#include <stdio.h>

int main() {
    int n = 0;
    printf("Entre com n: ");
    scanf("%d", &n);

    FILE *p = fopen("ex1Arq.txt", "w");
    for (int i = 0; i < n; i++) {
        int numero;
        printf("Digite o numero %d: ", i + 1);
        scanf("%d", &numero);
        fprintf(p, "%d\n", numero);
    }
    fclose(p);

    p = fopen("ex1Arq.txt", "r");

    fseek(p, 0, SEEK_END);
    long posicao = ftell(p);

    // Move a posição de leitura de volta um caractere para evitar o EOF
    posicao--;

    // Pule o caractere '\n' após o último número inserido
    fseek(p, posicao, SEEK_SET);
    char c;
    while (posicao >= 0) {
        c = fgetc(p);
        if (c == '\n') {
            posicao--;
            fseek(p, posicao, SEEK_SET);
            continue;
        }
        printf("%c", c);
        posicao--;
        fseek(p, posicao, SEEK_SET);
    }

    printf("\n");

    fclose(p);
    return 0;
}
