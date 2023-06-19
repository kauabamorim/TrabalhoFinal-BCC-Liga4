public class App {
    public static void main(String[] args) throws Exception {
        imprimirTabuleiro(tabuleiro());
    }

    public static char[][] tabuleiro() {
        char tabuleiro[][] = new char [6][7];

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                tabuleiro[i][j] = '-';
            }
        }

        return tabuleiro;
    }

    public static void imprimirTabuleiro(char[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                System.out.print(tabuleiro[i][j] + " | ");
            }
            System.out.println();
        }
    }
}
