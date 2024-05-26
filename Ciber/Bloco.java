public class Bloco {
    private int valor;
    private boolean lru;

    public Bloco(){
        this.valor = -1;
        this.lru = false;
    }

    public Bloco(int valor){
        this.valor = valor;
        this.lru = false;
    }

    public boolean getLru(){return lru;}

    public void setLru(){this.lru = true;}

    public int getValor(){return valor;}

    public void setValor(int mudar){this.valor = mudar;}
    
    
}
