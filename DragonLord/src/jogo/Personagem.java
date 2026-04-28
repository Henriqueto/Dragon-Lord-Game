package jogo;

public abstract class Personagem {
    protected String nome;
    protected int vida;
    protected int vidaMax;
    protected int ataque;
    protected int defesa;
    protected int mana;
    protected int manaMax;
    protected int nivel = 1;
    protected int xp = 0;
    protected Inventario inventario = new Inventario();

    protected boolean defendendo = false;
    protected boolean esquivando = false;

    public Personagem(String nome, int vida, int ataque, int defesa, int mana) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMax = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.mana = mana;
        this.manaMax = mana;

        inventario.adicionarItem(new Pocao("Pocao de Vida", 20, Pocao.Tipo.VIDA));
    }

    public void atacar(Inimigo inimigo) {
        int dano = Math.max(1, ataque + inventario.getArmaAtual().getBonus() - inimigo.getDefesa());
        inimigo.receberDano(dano);
        System.out.println(nome + " causou " + dano + " de dano.");
    }

    public abstract void habilidadeEspecial(Inimigo inimigo);

    public void defender() {
        defendendo = true;
        System.out.println(nome + " esta defendendo e reduzira o proximo dano.");
    }

    public void esquivar() {
        esquivando = true;
        System.out.println(nome + " tentara esquivar do proximo ataque.");
    }

    public void receberDano(int dano) {

        if (esquivando) {
            if (Math.random() < 0.5) {
                System.out.println(nome + " esquivou do ataque!");
                esquivando = false;
                return;
            }
            esquivando = false;
        }

        if (defendendo) {
            dano /= 2;
            defendendo = false;
        }

        vida -= dano;
        System.out.println(nome + " recebeu " + dano + " de dano.");
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void ganharExperiencia(int valor) {
        xp += valor;
        if (xp >= 100) {
            nivel++;
            xp = 0;
            vidaMax += 10;
            ataque += 3;
            manaMax += 5;
            vida = vidaMax;
            mana = manaMax;
            System.out.println("Voce subiu para o nivel " + nivel + "!");
        }
    }

    public Inventario getInventario() { return inventario; }
    public int getNivel() { return nivel; }
    
    public int getVida() {
    return vida;
}

public int getMana() {
    return mana;
}

public void setMana(int mana) {
    this.mana = Math.max(0, Math.min(mana, manaMax));
}

public int getVidaMax() {
    return vidaMax;
}

public int getManaMax() {
    return manaMax;
}

}


