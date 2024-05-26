import java.util.ArrayList;
import java.util.LinkedList;

public class Cache {
    private int tamanho;
    private int associativo;
    private int misses;
    private int hits;
    private ArrayList<String> linhas; 
    private ArrayList<Conjunto> conjuntos;

    // Construtor para mapeamento direto
    public Cache(int tamanho) {
        this.tamanho = tamanho;
        this.associativo = 1; 
        this.misses = 0;
        this.hits = 0;
        this.linhas = new ArrayList<>();
    }

    // Construtor para mapeamento associativo por conjunto
    public Cache(int tamanho, int associativo) {
        this.tamanho = tamanho;
        this.associativo = associativo;
        this.misses = 0;
        this.hits = 0;
        this.linhas = new ArrayList<>();
        this.conjuntos = new ArrayList<>();
        inicializarConjuntos();
    }

    public int getTamanho() { 
        return tamanho; 
    }

    public int getAssociatividade() { 
        return associativo; 
    }

    public int getMisses() { 
        return misses; 
    }

    public int getHits() { 
        return hits; 
    }

    public ArrayList<String> getLinhas() { 
        return linhas; 
    }

    public ArrayList<Conjunto> getConjuntos() { 
        return conjuntos; 
    }

    public void setLinhas(ArrayList<String> linhasAtualizadas) { 
        this.linhas = linhasAtualizadas; 
    }

    public void acerto() { 
        hits += 1; 
    }

    public void erro() { 
        misses += 1; 
    }

    public ArrayList<String> inicializarCache() {
        for (int i = 0; i < tamanho; i++) {
            linhas.add("-1");
        }
        return linhas;
    }

    private void inicializarConjuntos() {
        int numConjuntos = tamanho / associativo;
        for (int i = 0; i < numConjuntos; i++) {
            conjuntos.add(new Conjunto(associativo));
        }
    }

    public void imprimirCache() {
        System.out.print("Posição cache  |");
        System.out.print("  Posição memória\n");
        for (int i = 0; i < tamanho; i++) {
            System.out.print("              " + i + "|");
            System.out.print("               " + linhas.get(i) + "\n");
        }
    }

    public void imprimirConjuntos() {
        for (int i = 0; i < conjuntos.size(); i++) {
            System.out.print("Conjunto " + i + ": ");
            conjuntos.get(i).imprimirBlocos();
        }
    }
}
