public class SmartHome {
    Comodo[] comodos;
    int numeroComodos;
    int capacidadeMaxima;
    static final float taxaEnergia = 1.15f; 

    SmartHome(int capacidadeMaxima) {
        this.comodos = new Comodo[capacidadeMaxima];
        this.numeroComodos = 0;
        this.capacidadeMaxima = capacidadeMaxima;
    }

    boolean cadastrarComodo(String nome, int capacidadeDispositivosMaxima) {
        for (int i = 0; i < numeroComodos; i++) {
            if (comodos[i].nome.equalsIgnoreCase(nome)) {
                System.out.println("Cômodo '" + nome + "' já existe na residência!");
                return false;
            }
        }
        if (numeroComodos < capacidadeMaxima) {
            comodos[numeroComodos] = new Comodo(nome, capacidadeDispositivosMaxima);
            numeroComodos++;
            System.out.println("Cômodo '" + nome + "' cadastrado com sucesso!");
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

    void exibirResumoDia() {
        System.out.println("\n=== Resumo do Dia ===");
        for (int i = 0; i < numeroComodos; i++) {
            Comodo comodo = comodos[i];
            System.out.println("\nCômodo: " + comodo.nome);
            for (int j = 0; j < comodo.numeroTotalDispositivos; j++) {
                Dispositivo d = comodo.dispositivos[j];
                System.out.println("  Dispositivo: " + d.nome);
                System.out.println("  Estado: " + (d.ligado ? "Ligado" : "Desligado"));
                System.out.println("  Horas de uso: " + d.tempoHoraUso);
                System.out.printf("  Consumo diário: %.2f kWh%n", d.calcularConsumoHora());
            }
            System.out.printf("  Consumo do cômodo: %.2f kWh%n", comodo.calcularConsumoTotal());
        }
        System.out.printf("%nConsumo total da residência: %.2f kWh%n", calcularConsumoTotal());
        System.out.printf("Custo total do dia: R$ %.2f%n", calcularCustoTotal());
    }

    void listarMaioresConsumidores() {
        int total = 0;
        for (int i = 0; i < numeroComodos; i++) {
            total += comodos[i].numeroTotalDispositivos;
        }

        Dispositivo[] todos = new Dispositivo[total];
        int idx = 0;
        for (int i = 0; i < numeroComodos; i++) {
            for (int j = 0; j < comodos[i].numeroTotalDispositivos; j++) {
                todos[idx++] = comodos[i].dispositivos[j];
            }
        }

        // Bubble sort em ordem decrescente de consumo diário
        for (int i = 0; i < total - 1; i++) {
            for (int j = 0; j < total - 1 - i; j++) {
                if (todos[j].calcularConsumoHora() < todos[j + 1].calcularConsumoHora()) {
                    Dispositivo temp = todos[j];
                    todos[j] = todos[j + 1];
                    todos[j + 1] = temp;
                }
            }
        }

        System.out.println("\n Maiores Consumidores do Dia:");
        for (int i = 0; i < total; i++) {
            System.out.printf("%d) %s (%s) - %.2f kWh%n",
                    i + 1, todos[i].nome, todos[i].comodo.nome, todos[i].calcularConsumoHora());
        }
    }
}
