import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static float taxaAcertos(int hits, int posicoes){
        return ((float) hits/posicoes) * 100;
    }

    public static int divisao(int linha, int tamanho){
        return linha % tamanho;
    }

    public static void imprimir(String mensagem){
        System.out.println(mensagem);
    }

    public static void imprimirLinha(int quantidade){
        String linha = "";
        for (int i = 0; i < quantidade; i++){
            linha += "-";
        }
        imprimir(linha);
    }

    public static String solicitatString(String pergunta){
        Scanner scanner = new Scanner(System.in);
        System.out.print(pergunta);
        String resposta = scanner.nextLine();
        return resposta;
    }

    public static int solicitaInt(String pergunta){
        Scanner scanner = new Scanner(System.in);
        System.out.print(pergunta);
        int resposta = scanner.nextInt();
        return resposta;
    }

    public static void mapeamentoDireto(){
        ArrayList<String> posicoes = new ArrayList<>();
        imprimirLinha(100);
        int tamanho = solicitaInt("Quantas linhas/blocos terá a cache: ");
        String posicao = null;
        while (!"2".equals(posicao)) {
            String linha = solicitatString("Qual posição você deseja acessar: ");
            posicoes.add(linha);
            posicao = solicitatString("Adicionar mais uma posição - [1] | Sair - [2]\n");
            if ("1".equals(posicao)) {
                continue;
            } else if ("2".equals(posicao)) {
                break;
            } else {
                System.out.println("Opção inválida, tente novamente!");
            }
        }

        Cache teste = new Cache(tamanho);
        imprimirLinha(40);
        imprimir("Cache inicial:");
        imprimir("Tamanho da cache:  " + tamanho);
        teste.inicializarCache();
        teste.imprimirCache();
        ArrayList<String> linhas = teste.getLinhas();
        imprimirLinha(40);
        int quantidadeAcessos = posicoes.size();

        for(int i = 0; i < quantidadeAcessos; i++){
            String verifica = posicoes.get(i); //pega a posição digitada
            int linha = Integer.parseInt(verifica);
            int localCache = divisao(linha, tamanho);

            if(verifica.equals(linhas.get(localCache))){ //verifica se o bloco já está na memória
                teste.acerto();
                imprimir("Linha " + i + " | posição de memória desejada " + verifica);
                imprimir("Status: Hit");
                teste.imprimirCache();
                imprimirLinha(40);
            }
            else{
                teste.erro();
                imprimir("Linha " + i + " | posição de memória desejada " + verifica);
                imprimir("Status: Miss");
                linhas.set(localCache, verifica);
                teste.setLinhas(linhas);
                teste.imprimirCache();
                imprimirLinha(40);
            }
        }
        int hits = teste.getHits();
        imprimir("Memórias acessadas: " + quantidadeAcessos);
        imprimir("Número de hits: " + hits);
        imprimir("Número de misses: " + teste.getMisses());
        imprimir("Taxas de aceretos (hits): " + taxaAcertos(hits, quantidadeAcessos) + "%");
        imprimirLinha(80);
        imprimir("Conectividade em Sistemas Ciberfísicos - Prof. Guilherme - Mapeamento Direto");
        imprimir("Feito por: Equipe 02 - Julia Helena e Maria Fernanda ");
        imprimirLinha(80);
    }

    public static void mapeamentoAssociativoConjunto(){
        ArrayList<String> posicoes = new ArrayList<>();
        imprimirLinha(100);
        int tamanho = solicitaInt("Quantas linhas/blocos terá a cache: ");
        int associativo = solicitaInt("Qual a associatividade por conjunto: ");
        String posicao = null;
        while (!"2".equals(posicao)) {
            String linha = solicitatString("Qual posição você deseja acessar: ");
            posicoes.add(linha);
            posicao = solicitatString("Adicionar mais uma posição - [1] | Sair - [2]\n");
            if ("1".equals(posicao)) {
                continue;
            } else if ("2".equals(posicao)) {
                break;
            } else {
                System.out.println("Opção inválida, tente novamente!");
            }
        }

        Cache cache = new Cache(tamanho, associativo);
        imprimirLinha(40);
        imprimir("Cache inicial:");
        imprimir("Tamanho da cache:  " + tamanho);
        imprimir("Associatividade por conjunto: " + associativo);
        cache.inicializarCache();
        cache.imprimirCache();
        ArrayList<String> linhas = cache.getLinhas();
        imprimirLinha(40);
        int quantidadeAcessos = posicoes.size();
    
        for(int i = 0; i < quantidadeAcessos; i++){
            String verifica = posicoes.get(i); 
            int linha = Integer.parseInt(verifica);
            int localCache = divisao(linha, tamanho);
    
            // Calcula o conjunto correspondente
            int conjunto = divisao(linha, tamanho / associativo);
            Conjunto conjuntoCache = cache.getConjuntos().get(conjunto);
            boolean found = false;
            for (Bloco bloco : conjuntoCache.getBlocos()) {
                if (verifica.equals(bloco.getPosicaoMemoria())) {
                    cache.acerto();
                    conjuntoCache.atualizarLRU(bloco);
                    found = true;
                    break;
                }
            }
    
            // Se não encontrou o bloco, realiza uma substituição utilizando LRU
            if (!found) {
                cache.erro();
                Bloco bloco = new Bloco(verifica);
                conjuntoCache.substituirBloco(bloco);
                conjuntoCache.atualizarLRU(bloco);
            }
    
            imprimir("Linha " + i + " | posição de memória desejada " + verifica);
            if (found) {
                imprimir("Status: Hit");
            } else {
                imprimir("Status: Miss");
            }
            cache.imprimirCache();
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

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        imprimir("Escolha o tipo de mapeamento:");
        imprimir("1 - Mapeamento Direto");
        imprimir("2 - Mapeamento Associativo por Conjunto com LRU");
        int escolha = scanner.nextInt();

        if (escolha == 1) {
            mapeamentoDireto(); 
        } else if (escolha == 2) {
            mapeamentoAssociativoConjunto(); 
        } else {
            System.out.println("Opção inválida!");
        }
    }
}

