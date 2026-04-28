package jogo;

public class Mago extends Personagem {

    public Mago(String nome) {
        super(nome, 80, 20, 4, 60);
        inventario.adicionarItem(new Arma("Cajado Arcano", 5));
    }

    @Override
    public void habilidadeEspecial(Inimigo inimigo) {
        if (mana >= 15) {
            mana -= 15;
            inimigo.receberDano(ataque * 3);
        } else {
            System.out.println("Mana insuficiente.");
        }
    }
}

