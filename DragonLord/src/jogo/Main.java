package jogo;

import java.util.Scanner;
import jogo.Jogo;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Jogo jogo = new Jogo(sc);
        jogo.iniciar();
        sc.close();
    }
}
