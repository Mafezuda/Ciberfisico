import java.util.ArrayList;


public class Cache {
    private int tamanho;
    private int misses;
    private int hits;
    private ArrayList<String> linhas; 


    public Cache(int tamanho){
        this.tamanho = tamanho;
        this.misses = 0;
        this.hits = 0;
        this.
        linhas =  new ArrayList<>();
    }

    public int getTamanho(){return tamanho;}

    public int getMisses(){return misses;}

    public int getHits(){return hits;}

    public ArrayList<String> getLinhas(){return linhas;}

    public void setLinhas(ArrayList<String> linhasAtualizadas){this.linhas = linhasAtualizadas;}

    public void acerto(){hits += 1;}
    
    public void erro(){misses += 1;}


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






}