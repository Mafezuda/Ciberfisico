import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tamanhoCache;
        int tipoCache;
        int numeroDeAcessos;

        // Menu para solicitar o tamanho da cache
        System.out.print("Digite o tamanho da cache: ");
        tamanhoCache = scanner.nextInt();

        // Menu para selecionar o tipo de cache
        System.out.println("Escolha o tipo de cache:");
        System.out.println("1. Mapeamento Direto");
        System.out.println("2. Mapeamento Associativo");
        tipoCache = scanner.nextInt();

        // Menu para solicitar o número de acessos à memória
        System.out.print("Digite o número de acessos à memória: ");
        numeroDeAcessos = scanner.nextInt();

        Cache cache;
        if (tipoCache == 1) {
            cache = new CacheDireto(tamanhoCache);
        } else {
            cache = new CacheAssociativo(tamanhoCache);
        }

        // Menu para solicitar os endereços de memória
        System.out.println("Digite os endereços de memória:");
        for (int i = 0; i < numeroDeAcessos; i++) {
            int endereco = scanner.nextInt();
            cache.acessarMemoria(endereco);
        }

        cache.imprimirEstatisticas();

        scanner.close();
    }
}
