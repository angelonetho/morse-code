import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Tree tree = new Tree();

        exhibitMenu(tree);
    }

    private static void exhibitMenu(Tree tree) {
        Scanner scanner = new Scanner(System.in);

        int selectedOption = -1;

        while (selectedOption != 0) {
            System.out.println("Selecione uma operação:");
            System.out.println("1 - Inserir");
            System.out.println("2 - Codificar");
            System.out.println("3 - Decodificar");
            System.out.println("4 - Exibir Árvore Binária de Busca");
            System.out.println("5 - Inserir Todas as Letras");
            System.out.println("0 - Sair");

            try {
                selectedOption = scanner.nextInt();

                switch (selectedOption) {
                    case 1:
                        // Inserir
                        Operations.insert(tree, scanner);

                        break;
                    case 2:
                        // Codificar
                        Operations.encode(tree, scanner);

                        break;
                    case 3:
                        // Decodificar

                        break;
                    case 4:
                        //  Exibir a Árvore Binária
                        Operations.exhibitTree(tree);

                        break;
                    case 0:
                        System.out.println("bye.");

                        break;
                    default:
                        System.out.println("Operação inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Tente novamente.");

                scanner.nextLine(); // Limpa o buffer do scanner
                selectedOption = -1;
            }

        }
        scanner.close();

    }

}
