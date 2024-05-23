import java.util.ArrayList;
import java.util.List;

public class CacheAssociativo {

    private List<Integer> cache;
    private int tamanhoCache;
    private int acertos;
    private int falhas;

    public CacheAssociativo(int tamanhoCache) {
        this.tamanhoCache = tamanhoCache;
        this.cache = new ArrayList<>(tamanhoCache);
        this.acertos = 0;
        this.falhas = 0;
    }

    public void acessarMemoria(int endereco) {
        if (cache.contains(endereco)) {
            acertos++;
            // Se o bloco já está na cache, move ele para o final da lista
            cache.remove((Integer) endereco);
            cache.add(endereco);
        } else {
            falhas++;
            // Se a cache estiver cheia, remove o primeiro bloco (LRU) antes de adicionar o novo bloco
            if (cache.size() >= tamanhoCache) {
                cache.remove(0);
            }
            cache.add(endereco);
        }
    }

    public void estatisticas() {
        System.out.println("Total de acertos: " + acertos);
        System.out.println("Total de falhas: " + falhas);
    }
}
