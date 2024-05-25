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
        teste.imprirCache();
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
                teste.imprirCache();
                imprimirLinha(40);
            }
            else{
                teste.erro();
                imprimir("Linha " + i + " | posição de memória desejada " + verifica);
                imprimir("Status: Miss");
                linhas.set(localCache, verifica);
                teste.setLinhas(linhas);
                teste.imprirCache();
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


    public static void main(String[] args){
        mapeamentoDireto(); 
    }


}