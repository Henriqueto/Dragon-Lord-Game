package jogo;

public class Pocao extends Item {
    public enum Tipo { VIDA, MANA }

    private int valor;
    private Tipo tipo;

    public Pocao(String nome, int valor, Tipo tipo) {
        super(nome);
        this.valor = valor;
        this.tipo = tipo;
    }

    public void usar(Personagem p) {
        if (tipo == Tipo.VIDA) {
            p.vida = Math.min(p.vida + valor, p.vidaMax);
        } else {
            p.mana = Math.min(p.mana + valor, p.manaMax);
        }
    }

    public Tipo getTipo() { return tipo; }
}

