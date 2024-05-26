import java.util.ArrayList;

public class Cache {
    private int tamanho;
    private int misses;
    private int hits;
    private ArrayList<String> linhas; 
    private ArrayList<Conjunto> conjuntos = new ArrayList<>();


    public Cache(int tamanho){
        this.tamanho = tamanho;
        this.misses = 0;
        this.hits = 0;
        this.linhas =  new ArrayList<>();
    }

    public int getTamanho(){return tamanho;}

    public int getMisses(){return misses;}

    public int getHits(){return hits;}

    public ArrayList<String> getLinhas(){return linhas;}

    public void setLinhas(ArrayList<String> linhasAtualizadas){this.linhas = linhasAtualizadas;}

    public ArrayList<Conjunto> getConjuntos(){return conjuntos;}

    public void acerto(){hits += 1;}
    
    public void erro(){misses += 1;}

    public void adicionarConjunto(Conjunto conjunto){conjuntos.add(conjunto);}

    public ArrayList<String> inicializarCache(){
        for (int i = 0; i < tamanho; i++) {
            linhas.add("-1");
        }

        return linhas;
    }

    public void imprirCache(){
        System.out.print("Posição cache  |");
        System.out.print("  Posição memória\n");
        for (int i = 0; i < tamanho; i++){
            System.out.print("              " + i + "|");
            System.out.print("               " + linhas.get(i) + "\n");
        }
    }

    public void imprirCacheConjuntos(){
        int contador = 0;
        int numConj = 0;
        System.out.print("Conjunto  |");
        System.out.print("Posição cache  |");
        System.out.print("  Posição memória\n");
       
        for(Conjunto c: conjuntos){
            ArrayList<Bloco> blocos = c.getBlocos();
            for(Bloco b: blocos){
                if(b.getLru() == true){
                    System.out.print("      C 0" + numConj + "|");
                    System.out.print("              " + contador + "|");
                    contador += 1;
                    System.out.print("           " + b.getValor() + "   LRU" + "\n");
                }
                else{
                    System.out.print("      C 0" + numConj + "|");
                    System.out.print("              " + contador + "|");
                    contador += 1;
                    System.out.print("           " + b.getValor() + "\n");
                }
            }
            numConj += 1;
        }
    }
}