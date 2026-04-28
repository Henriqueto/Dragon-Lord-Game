package jogo;

public class Inimigo {
    protected String nome;
    protected int vida;
    protected int ataque;
    protected int defesa = 3;

    public Inimigo(String nome, int vida, int ataque) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
    }

    public void atacar(Personagem p) {
        int dano = Math.max(1, ataque - p.defesa);
        p.receberDano(dano);
    }

    public void receberDano(int dano) {
        vida -= dano;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public String getNome() { return nome; }
    public int getDefesa() { return defesa; }
    
    public int getVida() {
    return vida;
}
}

