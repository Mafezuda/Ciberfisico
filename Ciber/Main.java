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

    public static int solicitaInt(String pergunta) {
        Scanner scanner = new Scanner(System.in);
        int resposta = -1;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print(pergunta);
            if (scanner.hasNextInt()) {
                resposta = scanner.nextInt();
                if (resposta > 0) {
                    entradaValida = true;
                } else {
                    System.out.println("Insira um número positivo.");
                    imprimirLinha(50);
                }
            } else {
                System.out.println("Inválido, digite um número.");
                imprimirLinha(50);
                scanner.next(); // Limpa a entrada inválida
            }
        }

        return resposta;
    }

    public static String solicitaPosicao(String pergunta) {
        Scanner scanner = new Scanner(System.in);
        String resposta = null;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print(pergunta);
            resposta = scanner.next();
            try {
                int numero = Integer.parseInt(resposta);
                if (numero >= 0) {
                    entradaValida = true;
                } else {
                    System.out.println("Insira um número positivo.");
                    imprimirLinha(50);
                }
            } catch (NumberFormatException e) {
                System.out.println("Inválido, insira um número.");
                imprimirLinha(50);
            }
        }

        return resposta;
    }


    public static void mapeamentoDireto(){
        ArrayList<String> posicoes = new ArrayList<>();
        imprimirLinha(50);
        int tamanho = solicitaInt("Quantas linhas/blocos terá a cache: ");
        String posicao = null;
        while (!"2".equals(posicao)) {
            String linha = solicitaPosicao("Qual posição você deseja acessar: ");
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

    public static void mapeamentoAssociativo(){
        ArrayList<String> posicoes = new ArrayList<>();
        imprimirLinha(50);
        int tamanhoConj = solicitaInt("Quantas linhas/blocos terá cada conjunto: ");
        int quantConj = solicitaInt("Quantos conjuntos terá a cache: ");
        String posicao = null;
        while (!"2".equals(posicao)) {
            String linha = solicitaPosicao("Qual posição você deseja acessar: ");
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
        int tamanhoCache = quantConj * tamanhoConj;
        Cache teste2 = new Cache(tamanhoCache);
        int quantidadeAcessos = posicoes.size();

        for(int j = 0; j < quantConj; j++){
            Conjunto conjunto = new Conjunto(tamanhoConj);
            conjunto.preencher();
            teste2.adicionarConjunto(conjunto);
        }

        imprimirLinha(50);
        imprimir("Cache inicial:");
        imprimir("Tamanho da cache:  0" + quantConj + " conjuntos, 0" + tamanhoConj + " blocos por conj.");
        teste2.imprirCacheConjuntos();
        imprimirLinha(50);

        for(int i = 0; i < quantidadeAcessos; i++){
            String verifica = posicoes.get(i); //pega a posição digitada
            int linha = Integer.parseInt(verifica);
            int localCache = divisao(linha, quantConj);//posição na memória cache
            ArrayList<Conjunto> conjuntos = teste2.getConjuntos();

            Conjunto conj = conjuntos.get(localCache);
            ArrayList<Bloco> blocos = conj.getBlocos();

            int  campo = conj.verificarCampo(linha);
            int vazio = conj.verificarCampoVazio(linha);
            int quantBlocos = blocos.size() - 1;
            if(campo == -2){
                teste2.erro();
                if(vazio == -2){
                    imprimir("Linha " + i + " | posição de memória desejada " + verifica);
                    imprimir("Status: Miss");
                    Bloco trocar = new Bloco(linha);    
                    blocos.remove(quantBlocos);
                    blocos.add(0, trocar);
                    conj.setBlocos(blocos);
                    conj.dadoAntigo();
                    teste2.imprirCacheConjuntos();
                    imprimirLinha(50);
                }
                else{
                    imprimir("Linha " + i + " | posição de memória desejada " + verifica);
                    imprimir("Status: Miss");
                    Bloco trocar = new Bloco(linha);
                    blocos.remove(vazio);
                    blocos.add(0, trocar);
                    if(blocos.get(quantBlocos).getValor() == -1){
                        conj.setBlocos(blocos);
                        teste2.imprirCacheConjuntos();
                        imprimirLinha(50);
                    }
                    else{
                        conj.setBlocos(blocos);
                        conj.dadoAntigo();
                        teste2.imprirCacheConjuntos();
                        imprimirLinha(50);
                    }
                }
            }
            else{
                imprimir("Linha " + i + " | posição de memória desejada " + verifica);
                imprimir("Status: Hit");
                teste2.acerto();
                Bloco repetir = new Bloco(linha);
                blocos.remove(campo);
                blocos.add(0, repetir);
                if(blocos.get(quantBlocos).getValor() == -1){
                    conj.setBlocos(blocos);
                    teste2.imprirCacheConjuntos();
                    imprimirLinha(50);
                }
                else{
                    conj.setBlocos(blocos);
                    conj.dadoAntigo();
                    teste2.imprirCacheConjuntos();
                    imprimirLinha(50);
                }
            }
        }
        int hits = teste2.getHits();
        imprimir("Memórias acessadas: " + quantidadeAcessos);
        imprimir("Número de hits: " + hits);
        imprimir("Número de misses: " + teste2.getMisses());
        imprimir("Taxas de aceretos (hits): " + taxaAcertos(hits, quantidadeAcessos) + "%");
        imprimirLinha(95);
        imprimir("Conectividade em Sistemas Ciberfísicos - Prof. Guilherme - Mapeamento Associativo por conjunto");
        imprimir("Feito por: Equipe 02 - Julia Helena e Maria Fernanda ");
        imprimirLinha(95);
    }


    public static void main(String[] args){ 
        
        boolean opcaoValida = false;

        imprimirLinha(50);
        imprimir("                     Bem-vindo");
        imprimirLinha(50);

        while (!opcaoValida) {
           
            int escolha = solicitaInt("Escolha um tipo de mapeamento: \n1 - Mapeamento Direto\n2 - Mapeamento Associativo por Conjunto\n");

            switch (escolha) {
                case 1:
                    mapeamentoDireto();
                    opcaoValida = true;
                    break;
                case 2:
                    mapeamentoAssociativo();
                    opcaoValida = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    imprimirLinha(50);
                    break;
            }
        }
    }

}