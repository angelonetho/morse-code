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

    private static String encodeLetter(Node node, char target, String path){
        if (node == null) return null;
        if (node.letter == target) return path;

        String left = encodeLetter(node.left, target, path + ".");
        if (left != null) return left;

        return encodeLetter(node.right, target, path + "-");
    }

    public static void encode(Tree tree, Scanner scanner){
        System.out.println("Escreva seu texto para codifica-lo: ");
        String text = scanner.nextLine().toUpperCase();

        StringBuilder morse = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c == ' '){
                morse.append("  "); // separacao
            } else {
                String code = encodeLetter(tree.getRoot(), c, "");
                if (code != null) {
                    morse.append(code).append(" ");
                } else {
                    morse.append("?"); // nao encontrado
                }
            }
        }
        System.out.println("Resultado Decodificacao: " + morse.toString().trim());
    }
}
