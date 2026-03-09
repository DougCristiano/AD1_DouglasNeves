public class SmartHome {
    Comodo[] comodos;
    int numeroComodos;
    static final float taxaEnergia = 1.15f; 

    SmartHome(int capacidadeMaxima) {
        this.comodos = new Comodo[capacidadeMaxima];
        this.numeroComodos = 0;
    }

    boolean cadastrarComodo(String nome, int capacidadeMaxima) {
        if (numeroComodos < capacidadeMaxima) {
            comodos[numeroComodos] = new Comodo(nome, capacidadeMaxima);
            numeroComodos++;
            System.out.println("Cômodo " + nome + " cadastrado com sucesso!");
            return true;
        } else {
            System.out.println("Limite de cômodos atingido!");
            return false;
        }
    }
    Comodo buscarComodo(String nome) {
        for (int i = 0; i < numeroComodos; i++) {
            if (comodos[i].nome.equalsIgnoreCase(nome)) {
                return comodos[i];
            }
        }
        System.out.println("Cômodo " + nome + " não encontrado!");
        return null;
    }
    float calcularConsumoTotal() {
        float consumoTotal = 0;
        for (int i = 0; i < numeroComodos; i++) {
            consumoTotal += comodos[i].calcularConsumoTotal();
        }
        return consumoTotal;
    }
    float calcularCustoTotal() {
        return calcularConsumoTotal() * taxaEnergia;
    }
}
