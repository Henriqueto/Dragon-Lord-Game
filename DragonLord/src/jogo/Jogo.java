package jogo;

import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private Scanner sc;
    private Random random = new Random();
    private Personagem jogador;

    public Jogo(Scanner sc) {
        this.sc = sc;
    }

    public void iniciar() {
        System.out.println("=== DragonLord ===");
        System.out.print("Digite seu nome: ");
        String nome = sc.nextLine();

        System.out.println("Escolha sua classe:");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Mago");
        System.out.println("3 - Arqueiro");
        int escolha = sc.nextInt();

        switch (escolha) {
            case 1 -> jogador = new Guerreiro(nome);
            case 2 -> jogador = new Mago(nome);
            case 3 -> jogador = new Arqueiro(nome);
            default -> jogador = new Guerreiro(nome);
        }

        System.out.println("\nVoce foi escolhido " + nome + ". Sua jornada comeca agora...\n");

        for (int i = 1; i <= 3; i++) {
            Inimigo inimigo = gerarInimigo(i);
            batalha(inimigo);

            if (!jogador.estaVivo()) {
                System.out.println("Voce foi derrotado...");
                return;
            }

            subirNivelAutomatico();
            droparItem();
        }

        System.out.println("\nO Dragao surge diante de voce!");
        Inimigo dragao = new Dragao();
        batalha(dragao);

        if (jogador.estaVivo()) {
            subirNivelAutomatico();
            droparItem();
            System.out.println("Voce derrotou o Dragao! O reino esta salvo!");
        } else {
            System.out.println("O Dragao venceu... Fim de jogo.");
        }
    }

    private Inimigo gerarInimigo(int nivel) {
        return switch (nivel) {
            case 1 -> new Inimigo("Goblin Sombrio", 70, 12);
            case 2 -> new Inimigo("Orc Brutal", 100, 18);
            case 3 -> new Inimigo("Cavaleiro Corrompido", 130, 22);
            default -> new Inimigo("Criatura Misteriosa", 80, 15);
        };
    }

   private void batalha(Inimigo inimigo) {
    System.out.println("\nEnfrentando: " + inimigo.getNome());
    jogador.getInventario().escolherArma(sc);

    int cooldownEspecial = 0;

    while (jogador.estaVivo() && inimigo.estaVivo()) {

        System.out.println("\n---------------------------------");
        System.out.println("Sua vida: " + jogador.getVida());
        System.out.println("Sua mana: " + jogador.getMana());
        System.out.println("Vida do inimigo: " + inimigo.getVida());
        System.out.println("---------------------------------");

        System.out.println("\n1 - Atacar");
        System.out.println("2 - Habilidade especial");
        System.out.println("3 - Defender/Esquivar");
        System.out.println("4 - Usar pocao");

        int opcao = sc.nextInt();
        boolean usouEspecial = false;

        if (opcao == 1) {
            jogador.atacar(inimigo);
        } 

        else if (opcao == 2) {

            if (cooldownEspecial > 0) {
                System.out.println("Habilidade em recarga por " + cooldownEspecial + " turno(s).");
            } 
            else if (jogador.getMana() < 20) {
                System.out.println("Mana insuficiente.");
            } 
            else {

                jogador.setMana(jogador.getMana() - 20);
                jogador.habilidadeEspecial(inimigo);

                if (jogador instanceof Guerreiro) {
                    System.out.println("O Guerreiro agora arde em chamas!");
                } 
                else if (jogador instanceof Mago) {
                    System.out.println("O Mago canaliza energia arcana!");
                } 
                else if (jogador instanceof Arqueiro) {
                    System.out.println("O Arqueiro concentra sua mira mortal!");
                }

                if (inimigo.estaVivo()) {
                    System.out.println("Voce pode atacar novamente!");
                    jogador.atacar(inimigo);
                }

                cooldownEspecial = 2;
                usouEspecial = true;
            }
        } 

        else if (opcao == 3) {

            if (jogador instanceof Guerreiro) {
                jogador.defender();
            } 
            else if (jogador instanceof Arqueiro) {
                jogador.esquivar();
            } 
            else {
                jogador.defender();
            }
        } 

        else if (opcao == 4) {
            jogador.getInventario().usarPocao(sc, jogador);
        }

        if (inimigo.estaVivo() && !usouEspecial) {
            inimigo.atacar(jogador);
        }

        if (cooldownEspecial > 0) {
            cooldownEspecial--;
        }
    }

    if (!inimigo.estaVivo()) {
        System.out.println("\nVoce derrotou " + inimigo.getNome() + "!");
        jogador.ganharExperiencia(100);
    }
}

    private void droparItem() {

        Pocao vida = new Pocao("Pocao de Vida", 30, Pocao.Tipo.VIDA);
        jogador.getInventario().adicionarItem(vida);
        System.out.println("Drop: Pocao de Vida");

        Pocao mana = new Pocao("Pocao de Mana", 20, Pocao.Tipo.MANA);
        jogador.getInventario().adicionarItem(mana);
        System.out.println("Drop: Pocao de Mana");

        int chance = random.nextInt(100);
        if (chance < 50) {
            Arma arma = new Arma("Arma Lendaria", 5 + jogador.getNivel() * 2);
            jogador.getInventario().adicionarItem(arma);
            System.out.println("Drop raro: Nova arma poderosa!");
        }
    }

    private void subirNivelAutomatico() {
        jogador.nivel++;
        jogador.vidaMax += 10;
        jogador.ataque += 3;
        jogador.manaMax += 5;
        jogador.vida = jogador.vidaMax;
        jogador.mana = jogador.manaMax;

        System.out.println("Voce subiu para o nivel " + jogador.getNivel() + "!");
    }
}


