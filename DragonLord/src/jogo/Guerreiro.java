package jogo;

public class Guerreiro extends Personagem {

    public Guerreiro(String nome) {
        super(nome, 120, 18, 8, 30);
        inventario.adicionarItem(new Arma("Espada de Ferro", 4));
    }

    @Override
    public void habilidadeEspecial(Inimigo inimigo) {
        if (mana >= 10) {
            mana -= 10;
            inimigo.receberDano(ataque * 2);
        } else {
            System.out.println("Mana insuficiente.");
        }
    }
}

