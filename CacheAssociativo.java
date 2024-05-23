import java.util.*;

public class CacheAssociativo implements Cache {
    private int[] cache;
    private int tamanhoCache;
    private int acertos;
    private int falhas;
    private List<Integer> lruList;

    public CacheAssociativo(int tamanhoCache) {
        this.tamanhoCache = tamanhoCache;
        this.cache = new int[tamanhoCache];
        this.acertos = 0;
        this.falhas = 0;
        this.lruList = new ArrayList<>();

        // Inicializa a cache com -1 para indicar que todas as linhas estão vazias
        for (int i = 0; i < tamanhoCache; i++) {
            cache[i] = -1;
        }
    }

    @Override
    public void acessarMemoria(int endereco) {
        if (lruList.contains(endereco)) {
            acertos++;
            System.out.println("Acerto: Endereço " + endereco + " já está na cache");
            lruList.remove((Integer) endereco);
            lruList.add(endereco);
        } else {
            falhas++;
            System.out.println("Falha: Endereço " + endereco + " não está na cache");

            if (lruList.size() >= tamanhoCache) {
                int enderecoARemover = lruList.remove(0);
                for (int i = 0; i < tamanhoCache; i++) {
                    if (cache[i] == enderecoARemover) {
                        cache[i] = endereco;
                        break;
                    }
                }
            } else {
                for (int i = 0; i < tamanhoCache; i++) {
                    if (cache[i] == -1) {
                        cache[i] = endereco;
                        break;
                    }
                }
            }
            lruList.add(endereco);
        }
    }

    @Override
    public void imprimirEstatisticas() {
        System.out.println("Total de acertos: " + acertos);
        System.out.println("Total de falhas: " + falhas);
    }
}