import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean jogarNovamente = true;

        while (jogarNovamente) {
            char[][] tabuleiro = tabuleiro();
            char jog;
            char comp = 'A';
            boolean valid = false;

            do {
                System.out.println("Escolha a cor que deseja jogar:");
                System.out.println("V | Vermelho");
                System.out.println("A | Azul");
                jog = sc.next().toUpperCase().charAt(0);

                switch (jog) {
                    case 'V':
                        comp = 'A';
                        valid = true;
                        break;
                    case 'A':
                        comp = 'V';
                        valid = true;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        valid = false;
                        break;
                }
            } while (!valid);

            imprimirTabuleiro(tabuleiro);
            boolean continuarJogo = true;

            while (continuarJogo) {
                continuarJogo = jogarTurno(sc, tabuleiro, jog, comp);

                if (!continuarJogo) {
                    System.out.println("Jogar novamente? (S/N)");
                    char resp = sc.next().toUpperCase().charAt(0);
                    jogarNovamente = (resp == 'S');
                }
            }
        }

        System.out.println("Fim do Jogo.");

        sc.close();
    }

    public static char[][] tabuleiro() {
        char tabuleiro[][] = new char[6][7];

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                tabuleiro[i][j] = '-';
            }
        }

        return tabuleiro;
    }

    public static void imprimirTabuleiro(char[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                System.out.print(tabuleiro[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public static boolean jogarTurno(Scanner sc, char[][] tabuleiro, char jog, char comp) {
        boolean continuarJogo = true;

        if (jog == 'V' && comp == 'A' || jog == 'A' && comp == 'V') {
        System.out.print("Jogador, escolha a coluna (0-6) para inserir a peça: ");
        int coluna = sc.nextInt();

            if (inserirPeca(tabuleiro, coluna, jog)) {
                imprimirTabuleiro(tabuleiro);

                if (verificarVitoria(tabuleiro, jog)) {
                    System.out.println("Jogador venceu!");
                    continuarJogo = false;
                } else if (tabuleiroCheio(tabuleiro)) {
                    System.out.println("O jogo terminou em empate!");
                    continuarJogo = false;
                }
            } else {
                System.out.println("Coluna inválida! Escolha novamente.");
                return continuarJogo;
            }

            int colunaComp = (int) (Math.random() * (7 - 0));

            if (verificarVitoria(tabuleiro, jog) == false) {
                if (inserirPeca(tabuleiro, colunaComp, comp)) {
                    System.out.println("Computador jogou na coluna: " + colunaComp);
                    imprimirTabuleiro(tabuleiro);
    
                    if (verificarVitoria(tabuleiro, comp)) {
                        System.out.println("Jogador perdeu!");
                        continuarJogo = false;
                    } else if (tabuleiroCheio(tabuleiro)) {
                        System.out.println("O jogo terminou em empate!");
                        continuarJogo = false;
                    }
                } else {
                    jogarTurno(sc, tabuleiro, jog, comp);
                    continuarJogo = false;
                }
            }

        }
        return continuarJogo;
    }

    public static boolean inserirPeca(char[][] tabuleiro, int coluna, char peca) {
        if (coluna < 0 || coluna >= tabuleiro[0].length) {
            return false;
        }

        for (int i = tabuleiro.length - 1; i >= 0; i--) {
            if (tabuleiro[i][coluna] == '-') {
                tabuleiro[i][coluna] = peca;
                return true;
            }
        }

        return false;
    }

    public static boolean verificarVitoria(char[][] tabuleiro, char jogador) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j <= tabuleiro[i].length - 4; j++) {
                if (tabuleiro[i][j] == jogador && tabuleiro[i][j + 1] == jogador &&
                        tabuleiro[i][j + 2] == jogador && tabuleiro[i][j + 3] == jogador) {
                    return true;
                }
            }
        }

        for (int j = 0; j < tabuleiro[0].length; j++) {
            for (int i = 0; i <= tabuleiro.length - 4; i++) {
                if (tabuleiro[i][j] == jogador && tabuleiro[i + 1][j] == jogador &&
                        tabuleiro[i + 2][j] == jogador && tabuleiro[i + 3][j] == jogador) {
                    return true;
                }
            }
        }

        for (int i = 0; i <= tabuleiro.length - 4; i++) {
            for (int j = 0; j <= tabuleiro[i].length - 4; j++) {
                if (tabuleiro[i][j] == jogador && tabuleiro[i + 1][j + 1] == jogador &&
                        tabuleiro[i + 2][j + 2] == jogador && tabuleiro[i + 3][j + 3] == jogador) {
                    return true;
                }
            }
        }

        for (int i = 0; i <= tabuleiro.length - 4; i++) {
            for (int j = tabuleiro[i].length - 1; j >= 3; j--) {
                if (tabuleiro[i][j] == jogador && tabuleiro[i + 1][j - 1] == jogador &&
                        tabuleiro[i + 2][j - 2] == jogador && tabuleiro[i + 3][j - 3] == jogador) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean tabuleiroCheio(char[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (tabuleiro[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
