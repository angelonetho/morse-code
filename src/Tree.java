public class Tree {
    private final Node root;

    public Tree() {
        root = new Node(' ');
    }

    public Node getRoot() {
        return root;
    }

    public void insert(char letter, String morse) {
        if (morse == null) {
            throw new IllegalArgumentException("Código morse nulo para a letra: " + letter);
        }

        // Limpa entrada para verificar se não é vazio
        String cleaned = morse.trim();
        cleaned = cleaned.replace(" ", "");

        if (cleaned.isEmpty()) {
            throw new IllegalArgumentException("Código morse vazio para a letra: " + letter);
        }

        // Passamos 0 para começar a partir da raiz da árvore
        insertRecursive(root, letter, cleaned, 0);
    }

    private void insertRecursive(Node current, char letter, String morse, int index) {

        // Verifica se chegamos no nó de destino
        if (index == morse.length()) {

            // Protege contra colisão caso já tenha uma letra no lugar
            if (current.letter != ' ' && current.letter != letter) {
                throw new IllegalStateException("Conflito: nó já contém '" + current.letter + "' para o código '" + morse + "'");
            }

            // Caso tudo de certo, inserimos a letra
            current.letter = letter;
            return;
        }
        /*
         Obtém o carácter atual e verifica se ele é ponto ou traço e envia para uma direção.
         Caso não a posição não exista, preenche temporáriamente com um espaço em branco.
         */
        char symbol = morse.charAt(index);
        if (symbol == '.') {
            if (current.left == null) current.left = new Node(' ');
            insertRecursive(current.left, letter, morse, index + 1);
        } else if (symbol == '-') {
            if (current.right == null) current.right = new Node(' ');
            insertRecursive(current.right, letter, morse, index + 1);
        } else {
            throw new IllegalArgumentException("Símbolo morse inválido: '" + symbol + "' em " + morse);
        }
    }

    public char search(String morse) {
        if (morse == null || morse.trim().isEmpty()) {
            throw new IllegalArgumentException("código morse vazio");
        }
        return searchRecursive(root, morse.trim(), 0);
    }

    private char searchRecursive(Node current, String morse, int index) {
        if (current == null) throw new IllegalArgumentException("Símbolo morse '" + morse + "' não encontrado.");

        if (index == morse.length()) {
            return current.letter;
        }

        char symbol = morse.charAt(index);
        if (symbol == '.') {
            return searchRecursive(current.left, morse, index + 1);
        } else if (symbol == '-') {
            return searchRecursive(current.right, morse, index + 1);
        } else {
            throw new IllegalArgumentException("Símbolo morse inválido: '" + symbol + "' em " + morse);
        }

    }

    public void decode(String[] words, int wordIndex, StringBuilder result) {
        if (wordIndex >= words.length) {
            return;
        }

        String[] letters = words[wordIndex].split("\\s+");
        decodeLettersRecursive(letters, 0, result);

        if (wordIndex < words.length - 1) {
            result.append(" ");
        }

        decode(words, wordIndex + 1, result);
    }

    private void decodeLettersRecursive(String[] letters, int letterIndex, StringBuilder result) {
        if (letterIndex >= letters.length) {
            return;
        }

        if (!letters[letterIndex].isEmpty()) {
            char decoded = this.search(letters[letterIndex]);
            result.append(decoded);
        }

        decodeLettersRecursive(letters, letterIndex + 1, result);
    }
    public void encode(String[] words, int wordIndex, StringBuilder result) {
        if (wordIndex >= words.length) return;

        encodeLettersRecursive(words[wordIndex], 0, result);

        if (wordIndex < words.length - 1) {
            result.append("/ ");
        }

        encode(words, wordIndex + 1, result); // próxima palavra
    }

    // Encode recursivo das letras de uma palavra
    private void encodeLettersRecursive(String word, int letterIndex, StringBuilder result) {
        if (letterIndex >= word.length()) return;

        char c = word.charAt(letterIndex);
        if (c != ' ') {
            try {
                String code = encodeLetter(root, c, "");
                result.append(code).append(" ");
            } catch (IllegalArgumentException e) {
                result.append("? "); // letra não encontrada
            }
        }

        encodeLettersRecursive(word, letterIndex + 1, result); // próxima letra
    }

    // Busca recursiva do código Morse de uma letra
    private String encodeLetter(Node node, char targetLetter, String path) {
        if (node == null) throw new IllegalArgumentException("Letra '" + targetLetter + "' não encontrada.");
        if (node.letter == targetLetter) return path;

        try {
            String left = encodeLetter(node.left, targetLetter, path + ".");
            if (left != null) return left;
        } catch (IllegalArgumentException ignored) {}

        String right = encodeLetter(node.right, targetLetter, path + "-");
        return right;
    }
}