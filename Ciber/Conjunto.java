import java.util.LinkedList;

public class Conjunto {
    private int associativo;
    private LinkedList<Bloco> blocos;

    public Conjunto(int associativo) {
        this.associativo = associativo;
        this.blocos = new LinkedList<>();

        for (int i = 0; i < associativo; i++) {
            blocos.add(new Bloco("-1"));
        }
    }

    public LinkedList<Bloco> getBlocos() { 
        return blocos; 
    }

    public void atualizarLRU(Bloco bloco) {
        blocos.remove(bloco);
        blocos.addFirst(bloco);
    }

    public Bloco getBlocoMenosUsado() {
        return blocos.getLast();
    }

    public void substituirBloco(Bloco bloco) {
        blocos.removeLast();
        blocos.addFirst(bloco);
    }

    public void imprimirBlocos() {
        for (Bloco bloco : blocos) {
            System.out.print(" " + bloco.getPosicaoMemoria() + " ");
        }
        System.out.println();
    }
}
