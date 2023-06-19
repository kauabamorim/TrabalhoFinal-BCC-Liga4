import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        String opc = "";

        System.out.println("Com qual você deseja jogar?");
        System.out.println("V -> Vermelho ");
        System.out.println("A -> Azul");
        opc = scanner.nextLine();

        char[][] tabuleiro = tabuleiro();

        switch (opc.toUpperCase()) {
            case "V":
                imprimirTabuleiro(tabuleiro);
                jogarTurno(tabuleiro, 'V');
                break;
            case "A":
                imprimirTabuleiro(tabuleiro);
                jogarTurno(tabuleiro, 'A');
                break;

            default:
                break;
        }

        scanner.close();
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

    public static void jogarTurno(char[][] tabuleiro, char jogador) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        if (jogador == 'V') {
            System.out.print("Jogador, escolha a coluna (0-6) para inserir a peça: ");
            int coluna = scanner.nextInt();

            if (inserirPeca(tabuleiro, coluna, jogador)) {
                imprimirTabuleiro(tabuleiro);

                if (verificarVitoria(tabuleiro, jogador)) {
                    System.out.println("Jogador venceu!");
                    return;
                } else if (tabuleiroCheio(tabuleiro)) {
                    System.out.println("O jogo terminou em empate!");
                    return;
                }
            } else {
                System.out.println("Coluna inválida! Escolha novamente.");
                jogarTurno(tabuleiro, jogador);
                return;
            }
        } else if (jogador == 'A') {
            int coluna = random.nextInt(tabuleiro[0].length);

            if (inserirPeca(tabuleiro, coluna, jogador)) {
                System.out.println("Computador jogou na coluna: " + coluna);
                imprimirTabuleiro(tabuleiro);

                if (verificarVitoria(tabuleiro, jogador)) {
                    System.out.println("Computador venceu!");
                    return;
                } else if (tabuleiroCheio(tabuleiro)) {
                    System.out.println("O jogo terminou em empate!");
                    return;
                }
            } else {
                jogarTurno(tabuleiro, jogador);
                return;
            }
        }

        jogarTurno(tabuleiro, jogador == 'V' ? 'A' : 'V');
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
                if (tabuleiro[i][j] == jogador && tabuleiro[i][j+1] == jogador &&
                    tabuleiro[i][j+2] == jogador && tabuleiro[i][j+3] == jogador) {
                    return true;
                }
            }
        }
    
        for (int j = 0; j < tabuleiro[0].length; j++) {
            for (int i = 0; i <= tabuleiro.length - 4; i++) {
                if (tabuleiro[i][j] == jogador && tabuleiro[i+1][j] == jogador &&
                    tabuleiro[i+2][j] == jogador && tabuleiro[i+3][j] == jogador) {
                    return true;
                }
            }
        }
    
        for (int i = 0; i <= tabuleiro.length - 4; i++) {
            for (int j = 0; j <= tabuleiro[i].length - 4; j++) {
                if (tabuleiro[i][j] == jogador && tabuleiro[i+1][j+1] == jogador &&
                    tabuleiro[i+2][j+2] == jogador && tabuleiro[i+3][j+3] == jogador) {
                    return true;
                }
            }
        }
    
        for (int i = 0; i <= tabuleiro.length - 4; i++) {
            for (int j = tabuleiro[i].length - 1; j >= 3; j--) {
                if (tabuleiro[i][j] == jogador && tabuleiro[i+1][j-1] == jogador &&
                    tabuleiro[i+2][j-2] == jogador && tabuleiro[i+3][j-3] == jogador) {
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
