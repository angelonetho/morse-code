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

    public static void decode(Tree tree, Scanner scanner) {

        try {
            System.out.println("Digite o código morse: (utilize ' ' entre as letras e '/' entre palavras)");
            String encodedMessage = scanner.nextLine().trim();

            if (encodedMessage.isEmpty()) {
                System.out.println("Entrada vazia. Tente novamente.");
                return;
            }

            if (!encodedMessage.matches("^[.\\- /]+$")) {
                System.out.println("A mensagem criptografada pode conter apenas caracteres de . e - e /.");
                return;
            }

            StringBuilder result = new StringBuilder();
            String[] words = encodedMessage.split("/");

            for (String word : words) {
                String[] letters = word.split("\\s+");
                for (String morseCode : letters) {
                    char decoded = tree.search(morseCode);
                    result.append(decoded);
                }
            }

            System.out.println("Mensagem descriptografada: " + result.toString().trim());
        } catch (IllegalArgumentException e) {
            System.out.println("A operação não pode ser concluída: " + e.getMessage());
        }
    }

    private static String encodeLetter(Node node, char targetLetter, String path) {
        if (node == null) throw new IllegalArgumentException("Letra '" + targetLetter + "' não encontrada.");
        if (node.letter == targetLetter) return path;

        String left = encodeLetter(node.left, targetLetter, path + ".");
        if (left != null) return left;

        return encodeLetter(node.right, targetLetter, path + "-");
    }

    public static void encode(Tree tree, Scanner scanner) {
        System.out.println("Escreva seu texto para codifica-lo: ");

        String messageToEncode = scanner.nextLine().trim().toUpperCase();

        if (messageToEncode.isEmpty()) {
            System.out.println("Entrada vazia. Tente novamente.");
            return;
        }

        StringBuilder encodedMessage = new StringBuilder();

        try {

            for (char c : messageToEncode.toCharArray()) {

                if (c == ' ') {
                    encodedMessage.append("  ");

                } else {
                    String code = encodeLetter(tree.getRoot(), c, ""); // Busca recursivamente a letra

                    encodedMessage.append(code).append(" ");
                }
            }

            System.out.println("Resultado Decodificação: " + encodedMessage.toString().trim());

        } catch (IllegalArgumentException e) {
            System.out.println("A operação não pode ser concluída: " + e.getMessage());
        }

    }

    public static void insertAll(Tree tree) {
        tree.insertAllLetters();
        System.out.println("Todas as letras foram inseridas!");
    }
}
