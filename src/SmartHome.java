public class SmartHome {
    String nome;
    Comodo[] comodos;
    int numeroComodos;
    

    SmartHome(String nome) {
        this.nome = nome;
        this.comodos = new Comodo[10]; // Supondo um limite de 10 cômodos
        this.numeroComodos = 0;
    }

    void cadastrarComodo(String nome) {
        if (numeroComodos < 10) {
            comodos[numeroComodos] = new Comodo(nome, 10); // Supondo uma capacidade máxima de 10 dispositivos por cômodo
            numeroComodos++;
            System.out.println("Cômodo " + nome + " cadastrado com sucesso!");
        } else {
            System.out.println("Limite de cômodos atingido!");
        }
    }

}
