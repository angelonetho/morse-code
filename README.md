# Morse Code (PUCPR) — Resolução de Problemas Estruturados em Computação

Projeto acadêmico em Java que implementa uma Árvore Binária de Busca (ABB) baseada no código Morse, onde ponto (.) vai para a esquerda e traço (-) vai para a direita. O programa oferece um menu interativo para inserir letras, codificar e decodificar mensagens e visualizar a árvore resultante em interface gráfica (Swing).

Observação: O enunciado da disciplina referencia JavaFX, porém esta implementação usa Swing para a visualização da árvore (TreeVisualizer.java). Não há dependência de JavaFX para executar este repositório.

## Como executar

1. Abra o projeto (File > Open) apontando para a pasta `morse-code`.
2. Localize `Main.java` em `src/`.
3. Clique em Run no método `main` da classe `Main`.

### 2) Terminal (javac/java)
No diretório raiz do projeto:

Compilar:
```
javac -d out src/*.java
```

Executar:
```
java -cp out Main
```

Exemplos:
- Codificar
  - Entrada: `HELLO WORLD`
  - Saída: `.... . .-.. .-.. --- / .-- --- .-. .-.. -..`
- Decodificar
  - Entrada: `.... . .-.. .-.. --- / .-- --- .-. .-.. -..`
  - Saída: `HELLO WORLD`

Dicas:
- Para codificar corretamente, insira previamente as letras na árvore (opção 5 facilita). Sem a árvore populada, a codificação/decodificação poderá falhar.
