# Morse Code (PUCPR) — Resolução de Problemas Estruturados em Computação

Projeto acadêmico em Java que implementa uma Árvore Binária de Busca (ABB) baseada no código Morse, onde ponto (.) vai para a esquerda e traço (-) vai para a direita. O programa oferece um menu interativo para inserir letras, codificar e decodificar mensagens e visualizar a árvore resultante em interface gráfica (Swing).

Observação: O enunciado da disciplina referencia JavaFX, porém esta implementação usa Swing para a visualização da árvore (TreeVisualizer.java). Não há dependência de JavaFX para executar este repositório.


## Sumário
- Descrição e requisitos atendidos
- Estrutura do projeto
- Como executar
  - IntelliJ IDEA (recomendado)
  - Terminal (javac/java)
- Como usar (menu e exemplos)
- Visualização da árvore (UI)
- Tratamento de erros e validações
- Observações sobre implementação (recursão, restrições)


## Descrição e requisitos atendidos
De acordo com o TDE 2 — Código Morse:

- Inserção conforme heurística Morse: ponto à esquerda e traço à direita. Atendido com recursão em Tree.insert/insertRecursive.
- Busca/decodificação em profundidade: ponto à esquerda e traço à direita. Atendido com recursão em Tree.search/searchRecursive e Tree.decode.
- Decodificar sequência de caracteres (palavra) via input e imprimir resultado. Implementado em Operations.decode e Tree.decode.
- Codificar sequência (palavra) via input e imprimir resultado. Implementado em Operations.encode e Tree.encode/encodeLetter.
- Interface gráfica para imprimir a árvore resultante. Implementado com Swing em TreeVisualizer.show.
- Todas as operações feitas com recursão. Inserção, busca, codificação e decodificação utilizam métodos recursivos.
- Sem uso de estruturas prontas do Java (ex.: HashMap). Os nós da ABB são definidos manualmente (Node) e a lógica não usa coleções prontas.


## Estrutura do projeto
```
/Users/angeloandrioli/IdeaProjects/morse-code
├─ src/
│  ├─ Main.java            # Menu interativo no console
│  ├─ Operations.java      # Operações de I/O e orquestração (inserir, codificar, decodificar, exibir árvore)
│  ├─ Tree.java            # Implementação da ABB com operações recursivas de inserção/busca/encode/decode
│  ├─ Node.java            # Estrutura do nó da árvore (letra, filho esquerdo/direito)
│  └─ TreeVisualizer.java  # Visualizador Swing da árvore
└─ README.md
```


## Como executar
Pré‑requisitos:
- JDK 8+ instalado (testado com JDKs modernos).
- Sistema operacional: Windows, macOS ou Linux.

### 1) IntelliJ IDEA (recomendado)
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


## Como usar (menu e exemplos)
Ao iniciar, o programa apresenta o menu:
```
1 - Inserir
2 - Codificar
3 - Decodificar
4 - Exibir Árvore Binária de Busca
5 - Inserir todas as letras
0 - Sair
```

- 1 Inserir: solicita uma letra (A–Z) e seu código Morse (ex.: A → `.-`). A inserção segue a regra: `.` para esquerda e `-` para direita. Em caso de conflito ou entrada inválida, uma mensagem de erro é exibida.
- 2 Codificar: solicita um texto (apenas letras e espaços). Ex.: "SOS HELP" → saída em Morse (letras separadas por espaço e palavras separadas por "/").
- 3 Decodificar: solicita um texto em Morse usando `.` e `-`, com espaços entre letras e `/` entre palavras. Ex.: `... --- ... / .... . .-.. .--.` → "SOS HELP".
- 4 Exibir Árvore Binária de Busca: abre uma janela Swing que desenha a ABB atual.
- 5 Inserir todas as letras: insere automaticamente as letras A–Z com seus códigos Morse padrão (em maiúsculas). Útil para começar a codificar/decodificar rapidamente.

Exemplos:
- Codificar
  - Entrada: `HELLO WORLD`
  - Saída: `.... . .-.. .-.. --- / .-- --- .-. .-.. -..`
- Decodificar
  - Entrada: `.... . .-.. .-.. --- / .-- --- .-. .-.. -..`
  - Saída: `HELLO WORLD`

Dicas:
- Para codificar corretamente, insira previamente as letras na árvore (opção 5 facilita). Sem a árvore populada, a codificação/decodificação poderá falhar.


## Visualização da árvore (UI)
- A visualização é feita com Swing via `TreeVisualizer.show(tree)`.
- A janela exibe nós como círculos com as letras inseridas. Conexões esquerdas representam ponto (.), direitas representam traço (-).
- Use barras de rolagem quando a árvore ficar grande.


## Tratamento de erros e validações
- Inserção:
  - Rejeita código nulo ou vazio.
  - Rejeita símbolos inválidos (apenas `.` e `-` são aceitos).
  - Detecta colisão ao tentar inserir uma letra onde já existe outra.
- Codificação:
  - Aceita apenas letras maiúsculas e espaços; outras entradas são rejeitadas.
  - Procura recursivamente o caminho Morse da letra; se a letra não existir na árvore, a operação lança erro (tratado no console).
- Decodificação:
  - Aceita apenas `.`, `-`, espaços e `/`.
  - Espaço separa letras; `/` separa palavras.
  - Busca recursiva do símbolo até a letra correspondente; símbolos inexistentes na árvore geram erro informativo.


## Observações sobre implementação
- Recursão: todas as operações principais (inserção, busca/decodificação e codificação) são implementadas de forma recursiva em `Tree.java`.
- Restrições: não utiliza estruturas de dados prontas do Java (ex.: `HashMap`). A árvore é construída com nós (`Node`) criados manualmente durante as inserções.
- Interface: a visualização utiliza Swing (sem dependências externas). Apesar de o enunciado citar JavaFX, nenhuma configuração adicional é necessária nesta implementação.


## Créditos
- Curso: Resolução de Problemas Estruturados em Computação — PUCPR.
- Trabalho: TDE 2 — Código Morse.
- Base visual: `TreeVisualizer.java` (Swing) incluído no repositório.
