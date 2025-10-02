import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Visualizador Swing para a sua classe Tree.
 * Uso: TreeVisualizerSwing.show(tree);  // abre a janela
 */
public final class TreeVisualizer {

    private TreeVisualizer() { /* util class */ }

    /** Abre a janela com título padrão. */
    public static void show(Tree tree) {
        show(tree, "Visualizador de Árvore Binária");
    }

    /** Abre a janela com título customizado. */
    public static void show(Tree tree, String title) {
        if (tree == null) throw new IllegalArgumentException("tree == null");

        // Cria painel de desenho baseado na sua árvore
        TreePanel panel = new TreePanel(tree);

        // Coloca em scroll pane para árvores grandes
        JScrollPane scroll = new JScrollPane(panel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setContentPane(scroll);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /** Painel que desenha a árvore. */
    private static class TreePanel extends JPanel {
        private final Tree tree;

        // Estilo/desenho
        private static final int NODE_DIAM = 34;     // diâmetro do círculo
        private static final int LEVEL_VSPACE = 120; // distância vertical entre níveis
        private static final int MIN_HSPACE = 40;    // base de espaçamento horizontal
        private static final int PADDING = 40;       // margem interna

        TreePanel(Tree tree) {
            this.tree = tree;
            // Define um tamanho preferido com base na altura
            int h = getHeight(tree.getRoot());
            int prefH = Math.max(200, PADDING * 2 + 60 + (h <= 1 ? 0 : (h - 1) * LEVEL_VSPACE));
            int prefW = Math.max(300, PADDING * 2 + (int) Math.pow(2, Math.max(1, h)) * MIN_HSPACE);
            setPreferredSize(new Dimension(prefW, prefH));
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Node root = tree.getRoot();
            if (root == null) return;

            Graphics2D g2 = (Graphics2D) g.create();
            // Anti-aliasing para ficar mais lisinho
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(2f));
            g2.setColor(Color.BLACK);

            //int h = getHeight(root);
            // Ponto inicial (topo central)
            double startX = getWidth() / 2.0;
            double startY = PADDING + NODE_DIAM;

            // Offset horizontal inicial (metade da largura útil / 2)
            double xOffset = Math.max(MIN_HSPACE, (getWidth() - 2.0 * PADDING) / 4.0);
            drawNode(g2, root, startX, startY, xOffset, 1);

            g2.dispose();
        }

        /** Desenha recursivamente: círculo, letra e arestas. */
        private void drawNode(Graphics2D g2, Node node, double x, double y, double xOffset, int level) {
            if (node == null) return;

            // Filhos: primeiro desenha as linhas para manter por baixo dos nós
            double childY = y + LEVEL_VSPACE;

            if (node.left != null) {
                double leftX = x - xOffset;
                g2.drawLine((int) x, (int) (y + NODE_DIAM / 2.0), (int) leftX, (int) (childY - NODE_DIAM / 2.0));
                drawNode(g2, node.left, leftX, childY, Math.max(MIN_HSPACE / 2.0, xOffset / 2.0), level + 1);
            }

            if (node.right != null) {
                double rightX = x + xOffset;
                g2.drawLine((int) x, (int) (y + NODE_DIAM / 2.0), (int) rightX, (int) (childY - NODE_DIAM / 2.0));
                drawNode(g2, node.right, rightX, childY, Math.max(MIN_HSPACE / 2.0, xOffset / 2.0), level + 1);
            }

            // Desenha o nó (círculo)
            double r = NODE_DIAM;
            Shape circle = new Ellipse2D.Double(x - r / 2.0, y - r / 2.0, r, r);
            g2.setColor(Color.WHITE);
            g2.fill(circle);
            g2.setColor(Color.BLACK);
            g2.draw(circle);

            // Desenha a letra (ou espaço em branco como '·' para visual)
            String text = node.letter == ' ' ? " " : String.valueOf(node.letter);
            // Centraliza o texto aproximadamente
            FontMetrics fm = g2.getFontMetrics();
            int tx = (int) (x - fm.stringWidth(text) / 2.0);
            int ty = (int) (y + fm.getAscent() / 2.5);
            g2.drawString(text, tx, ty);
        }

        /** Altura da árvore (número de níveis). */
        private int getHeight(Node n) {
            if (n == null) return 0;
            int lh = getHeight(n.left);
            int rh = getHeight(n.right);
            return 1 + Math.max(lh, rh);
        }
    }
}
