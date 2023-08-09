import java.util.Scanner;


public class exercicio1{

    public static void main(String [] args){

        Scanner scanner = new Scanner(System.in);
        Aluno [] alunos = new Aluno[5];

        for(int i = 0; i < 5; i++){

            System.out.print("Entre com nome do aluno: ");
            alunos[i] = new Aluno();
            alunos[i].nome = scanner.nextLine();
            System.out.print("entre com sua nota: ");
            alunos[i].nota = scanner.nextDouble();
            scanner.nextLine();
        }
        for(int i = 0; i < 5; i++){
            if(alunos[i].nota >= 60)
                System.out.println("Aluno " + alunos[i].nome +" aprovado!");
        }
    }
}
class Aluno{
     public double nota;
     public String nome;
}