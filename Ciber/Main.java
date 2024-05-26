import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static float taxaAcertos(int hits, int posicoes) {
        return ((float) hits / posicoes) * 100;
    }

    public static int divisao(int linha, int tamanho) {
        return linha % tamanho;
    }

    public static void imprimir(String mensagem) {
        System.out.println(mensagem);
    }

    public static void imprimirLinha(int quantidade) {
        String linha = "";
        for (int i = 0; i < quantidade; i++) {
            linha += "-";
        }
        imprimir(linha);
    }

    public static String solicitaString(String pergunta) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(pergunta);
        return scanner.nextLine();
    }

    public static int solicitaInt(String pergunta) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(pergunta);
        return scanner.nextInt();
    }

    public static void mapeamentoDireto(Cache cache) {
        ArrayList<String> posicoes = new ArrayList<>();
        imprimirLinha(100);
        int tamanho = cache.getTamanho();
        String posicao;

        while (true) {
            String linha = solicitaString("Qual posição você deseja acessar: ");
            posicoes.add(linha);
            posicao = solicitaString("Adicionar mais uma posição - [1] | Sair - [2]\n");
            if ("2".equals(posicao)) {
                break;
            } else if (!"1".equals(posicao)) {
                System.out.println("Opção inválida, tente novamente!");
            }
        }

        imprimirLinha(40);
        imprimir("Cache inicial:");
        imprimir("Tamanho da cache: " + tamanho);
        cache.inicializarCache();
        cache.imprirCache();
        ArrayList<String> linhas = cache.getLinhas();
        imprimirLinha(40);
        int quantidadeAcessos = posicoes.size();

        for (int i = 0; i < quantidadeAcessos; i++) {
            String verifica = posicoes.get(i);
            int linha = Integer.parseInt(verifica);
            int localCache = divisao(linha, tamanho);

            if (verifica.equals(linhas.get(localCache))) {
                cache.acerto();
                imprimir("Linha " + i + " | posição de memória desejada " + verifica);
                imprimir("Status: Hit");
            } else {
                cache.erro();
                imprimir("Linha " + i + " | posição de memória desejada " + verifica);
                imprimir("Status: Miss");
                linhas.set(localCache, verifica);
                cache.setLinhas(linhas);
            }
            cache.imprirCache();
            imprimirLinha(40);
        }
        int hits = cache.getHits();
        imprimir("Memórias acessadas: " + quantidadeAcessos);
        imprimir("Número de hits: " + hits);
        imprimir("Número de misses: " + cache.getMisses());
        imprimir("Taxas de acertos (hits): " + taxaAcertos(hits, quantidadeAcessos) + "%");
        imprimirLinha(80);
        imprimir("Conectividade em Sistemas Ciberfísicos - Prof. Guilherme - Mapeamento Direto");
        imprimir("Feito por: Equipe 02 - Julia Helena e Maria Fernanda ");
        imprimirLinha(80);
    }

    public static void mapeamentoAssociativoConjunto(Cache cache) {
        ArrayList<String> posicoes = new ArrayList<>();
        imprimirLinha(100);
        int tamanho = cache.getTamanho();
        int associativo = cache.getAssociatividade();
        String posicao;

        while (true) {
            String linha = solicitaString("Qual posição você deseja acessar: ");
            posicoes.add(linha);
            posicao = solicitaString("Adicionar mais uma posição - [1] | Sair - [2]\n");
            if ("2".equals(posicao)) {
                break;
            } else if (!"1".equals(posicao)) {
                System.out.println("Opção inválida, tente novamente!");
            }
        }

        imprimirLinha(40);
        imprimir("Cache inicial:");
        imprimir("Tamanho da cache: " + tamanho + " | Associatividade: " + associativo);
        cache.inicializarConjuntos();
        cache.imprimirConjuntos();
        imprimirLinha(40);
        int quantidadeAcessos = posicoes.size();

        for (int i = 0; i < quantidadeAcessos; i++) {
            String verifica = posicoes.get(i);
            int linha = Integer.parseInt(verifica);
            int localConjunto = divisao(linha, tamanho / associativo);

            Conjunto conjunto = cache.getConjuntos().get(localConjunto);
            boolean hit = false;
            for (Bloco bloco : conjunto.getBlocos()) {
                if (bloco.getPosicaoMemoria().equals(verifica)) {
                    hit = true;
                    conjunto.atualizarLRU(bloco);
                    break;
                }
            }

            if (hit) {
                cache.acerto();
                imprimir("Linha " + i + " | posição de memória desejada " + verifica);
                imprimir("Status: Hit");
            } else {
                cache.erro();
                imprimir("Linha " + i + " | posição de memória desejada " + verifica);
                imprimir("Status: Miss");
                Bloco blocoMenosUsado = conjunto.getBlocoMenosUsado();
                blocoMenosUsado.setPosicaoMemoria(verifica);
                conjunto.atualizarLRU(blocoMenosUsado);
            }
            cache.imprimirConjuntos();
            imprimirLinha(40);
        }
        int hits = cache.getHits();
        imprimir("Memórias acessadas: " + quantidadeAcessos);
        imprimir("Número de hits: " + hits);
        imprimir("Número de misses: " + cache.getMisses());
        imprimir("Taxas de acertos (hits): " + taxaAcertos(hits, quantidadeAcessos) + "%");
        imprimirLinha(80);
        imprimir("Conectividade em Sistemas Ciberfísicos - Prof. Guilherme - Mapeamento Associativo por Conjunto");
        imprimir("Feito por: Equipe 02 - Julia Helena e Maria Fernanda ");
        imprimirLinha(80);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        imprimir("Escolha o tipo de mapeamento:");
        imprimir("1 - Mapeamento Direto");
        imprimir("2 - Mapeamento Associativo por Conjunto com LRU");
        int escolha = scanner.nextInt();

        if (escolha == 1) {
            int tamanho = solicitaInt("Quantas linhas/blocos terá a cache: ");
            Cache cache = new Cache(tamanho);
            mapeamentoDireto(cache);
        } else if (escolha == 2) {
            int tamanho = solicitaInt("Quantas linhas/blocos terá a cache: ");
            int associativo = solicitaInt("Qual a associatividade da cache: ");
            Cache cache = new Cache(tamanho, associativo);
            mapeamentoAssociativoConjunto(cache);
        } else {
            System.out.println("Opção inválida!");
        }
    }
}
