import java.util.ArrayList;

public class Conjunto {
    private int tamanhoConjunto;
    private ArrayList<Bloco> blocos;
    private ArrayList<Bloco> lru;

    public Conjunto(int tamanhoConjunto){
        this.tamanhoConjunto = tamanhoConjunto;
        this.blocos = new ArrayList<>();
        this.lru = new ArrayList<>();
    }


    public int getTamanhoConjunto(){return tamanhoConjunto;}

    public ArrayList<Bloco> getBlocos(){return blocos;}

    public ArrayList<Bloco> getLru(){return lru;}

    public void setBlocos(ArrayList<Bloco> linhasAtualizadas){this.blocos = linhasAtualizadas;}

    public void dadoAntigo(){
        Bloco b = blocos.get(blocos.size() - 1);
        b.setLru();
    }

    public int verificarCampo(int posicao){
        int indice = 0;
        for(Bloco b: blocos){
            if(b.getValor() == posicao){
                return indice;
            }
            indice += 1;
        }
        return 10;
    }

    public int verificarCampoVazio(int posicao){
        int indice = 0;
        for(Bloco b: blocos){
            if(b.getValor() == -1){
                return indice;
            }
            indice += 1;
        }
        return 11;
    }

    public void preencher(){
        for(int i = 0; i< tamanhoConjunto; i++){
            Bloco bloco = new Bloco();
            blocos.add(bloco);
        }
    }
   
}
