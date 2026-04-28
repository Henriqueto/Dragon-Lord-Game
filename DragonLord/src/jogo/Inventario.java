package jogo;

import java.util.ArrayList;
import java.util.Scanner;

public class Inventario {
    private ArrayList<Item> itens = new ArrayList<>();
    private Arma armaAtual;

    public void adicionarItem(Item item) {
        itens.add(item);
        if (item instanceof Arma && armaAtual == null) {
            armaAtual = (Arma) item;
        }
    }

    public void escolherArma(Scanner sc) {
        System.out.println("Escolha sua arma:");
        int i = 1;
        ArrayList<Arma> armas = new ArrayList<>();
        for (Item item : itens) {
            if (item instanceof Arma) {
                armas.add((Arma) item);
                System.out.println(i + " - " + item.getNome());
                i++;
            }
        }
        int escolha = sc.nextInt();
        armaAtual = armas.get(escolha - 1);
    }

    public void usarPocao(Scanner sc, Personagem p) {
        System.out.println("Escolha uma pocao:");
        ArrayList<Pocao> pocoes = new ArrayList<>();
        int i = 1;
        for (Item item : itens) {
            if (item instanceof Pocao) {
                pocoes.add((Pocao) item);
                System.out.println(i + " - " + item.getNome());
                i++;
            }
        }
        if (pocoes.isEmpty()) {
            System.out.println("Nenhuma pocao disponivel.");
            return;
        }
        int escolha = sc.nextInt();
        Pocao pz = pocoes.get(escolha - 1);
        pz.usar(p);
        itens.remove(pz);
    }

    public Arma getArmaAtual() {
        return armaAtual;
    }
}

