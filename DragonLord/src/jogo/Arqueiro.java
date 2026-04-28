package jogo;

public class Arqueiro extends Personagem {

    public Arqueiro(String nome) {
        super(nome, 100, 17, 6, 40);
        inventario.adicionarItem(new Arma("Arco de Carvalho", 3));
    }

    @Override
    public void habilidadeEspecial(Inimigo inimigo) {
        if (mana >= 12) {
            mana -= 12;
            inimigo.receberDano(ataque * 2);
        } else {
            System.out.println("Mana insuficiente.");
        }
    }
}

