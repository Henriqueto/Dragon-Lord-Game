package jogo;

public class Arma extends Item {
    private int bonus;

    public Arma(String nome, int bonus) {
        super(nome);
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }
}

