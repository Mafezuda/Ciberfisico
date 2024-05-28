import java.util.ArrayList;

public class Conjunto {
    private int tamanhoConjunto;
    private ArrayList<Bloco> blocos;

    public Conjunto(int tamanhoConjunto){
        this.tamanhoConjunto = tamanhoConjunto;
        this.blocos = new ArrayList<>();
    }


    public int getTamanhoConjunto(){return tamanhoConjunto;}

    public ArrayList<Bloco> getBlocos(){return blocos;}

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
        return -2;//Caso não encontre a posição pedida na cache 
    }

    public int verificarCampoVazio(int posicao){
        int indice = 0;
        for(Bloco b: blocos){
            if(b.getValor() == -1){
                return indice;
            }
            indice += 1;
        }
        return -2;//caso não encontre campo vazio na cache
    }

    public void preencher(){
        for(int i = 0; i< tamanhoConjunto; i++){
            Bloco bloco = new Bloco();
            blocos.add(bloco);
        }
    }
   
}
