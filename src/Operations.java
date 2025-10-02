import java.util.Scanner;

public class Operations {

    public static void insert(Tree tree, Scanner scanner) {

       try {
           System.out.println("Digite a letra a ser inserida:");
           char letter = scanner.next().toUpperCase().charAt(0);
           scanner.nextLine();

           System.out.println("Digite o código morse para a letra:");
           String morse = scanner.nextLine();

           tree.insert(letter, morse);
           System.out.println("Letra '" + letter + "' inserida sem erros durante a execução.");
       } catch (IllegalArgumentException | IllegalStateException e) {
           System.out.println(e.getMessage());
       }

    }

    public static void exhibitTree(Tree tree) {
        javax.swing.SwingUtilities.invokeLater(() -> TreeVisualizer.show(tree));
    }

    public static void search(Tree tree, Scanner scanner) {

        try {
            scanner.nextLine(); // Limpa o buffer do scanner
            System.out.println("Digite o código morse para buscar:");
            String morse = scanner.nextLine();

            char result = tree.search(morse);
            if (result == ' ') {
                System.out.println("Nenhuma letra encontrada para o código: " + morse);
            } else {
                System.out.println("O código '" + morse + "' corresponde à letra: " + result);
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
