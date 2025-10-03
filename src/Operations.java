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
                result.append(" ");
            }

            System.out.println("Mensagem descriptografada: " + result.toString().trim());
        } catch (IllegalArgumentException e) {
            System.out.println("A operação não pode ser concluída: " + e.getMessage());
        }

    }

    // cheatcode
//    private void insertAllLetters() {
//        // Letras A até Z em Morse
//        insert(".-", 'A');
//        insert("-...", 'B');
//        insert("-.-.", 'C');
//        insert("-..", 'D');
//        insert(".", 'E');
//        insert("..-.", 'F');
//        insert("--.", 'G');
//        insert("....", 'H');
//        insert("..", 'I');
//        insert(".---", 'J');
//        insert("-.-", 'K');
//        insert(".-..", 'L');
//        insert("--", 'M');
//        insert("-.", 'N');
//        insert("---", 'O');
//        insert(".--.", 'P');
//        insert("--.-", 'Q');
//        insert(".-.", 'R');
//        insert("...", 'S');
//        insert("-", 'T');
//        insert("..-", 'U');
//        insert("...-", 'V');
}
