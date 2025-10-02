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
       } catch (IllegalArgumentException e) {
           System.out.println(e.getMessage());
       }

    }

    public static void exhibitTree(Tree tree) {
        javax.swing.SwingUtilities.invokeLater(() -> TreeVisualizer.show(tree));
    }
}
