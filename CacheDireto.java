public class CacheDireto implements Cache {
    private int[] cache;
    private int tamanhoCache;
    private int acertos;
    private int falhas;

    public CacheDireto(int tamanhoCache) {
        this.tamanhoCache = tamanhoCache;
        this.cache = new int[tamanhoCache];
        this.acertos = 0;
        this.falhas = 0;

        // Inicializa a cache com -1 para indicar que todas as linhas estão vazias
        for (int i = 0; i < tamanhoCache; i++) {
            cache[i] = -1;
        }
    }

    @Override
    public void acessarMemoria(int endereco) {
        int indiceCache = endereco % tamanhoCache;

        if (cache[indiceCache] == endereco) {
            acertos++;
            System.out.println("Acerto: Endereço " + endereco + " encontrado na linha " + indiceCache);
        } else {
            falhas++;
            System.out.println("Falha: Endereço " + endereco + " não encontrado na linha " + indiceCache);
            cache[indiceCache] = endereco;
        }
    }

    @Override
    public void imprimirEstatisticas() {
        System.out.println("Total de acertos: " + acertos);
        System.out.println("Total de falhas: " + falhas);
    }
}