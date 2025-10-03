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

            for (String word : words){
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
    public static void insertAll(Tree tree) {
        tree.insertAllLetters(); // chama o metodo da classe Tree
    }
}
