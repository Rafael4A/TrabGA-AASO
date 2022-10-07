public class Item {
    int qualidade;
    String nome;
    int tempoEntrada;
    int tempoSaida;

    public Item(String nome, int qualidade, int tempoEntrada) {
        this.qualidade = qualidade;
        this.nome = nome;
        this.tempoEntrada = tempoEntrada;
    }

    public void setTempoSaida(int tempoSaida) {
        this.tempoSaida = tempoSaida;
    }

    @Override
    public String toString() {
        return "Item { " +
                "qualidade:" + qualidade +
                " nome:'" + nome + "'," +
                " tempoEntrada:" + tempoEntrada + "'," +
                " tempoSaida:" + tempoSaida +
                " }";
    }
}
