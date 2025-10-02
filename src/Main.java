import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        exhibitMenu();
    }

    private static void exhibitMenu() {
        Scanner scanner = new Scanner(System.in);

        int selectedOption = -1;

        while (selectedOption != 0) {
            System.out.println("Selecione uma operação:");
            System.out.println("1 - Inserir");
            System.out.println("2 - Codificar");
            System.out.println("3 - Decodificar");
            System.out.println("0 - Sair");

            try {
                selectedOption = scanner.nextInt();

                switch (selectedOption) {
                    case 1:
                        // Inserir

                        break;
                    case 2:
                        // Codificar

                        break;
                    case 3:
                        // Decodificar

                        break;
                    case 0:
                        System.out.println("bye.");

                        break;
                    default:
                        System.out.println("Operação inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input inválido. Tente novamente.");

                scanner.nextLine(); // Limpa o buffer do scanner
                selectedOption = -1;
            }

        }
        scanner.close();

    }

}
