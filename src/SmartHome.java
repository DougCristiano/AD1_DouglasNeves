public class SmartHome {
    Comodo[] comodos;
    int numeroComodos;
    int capacidadeComodosMax;
    static final float taxaEnergia = 1.15f; 

    SmartHome(int capacidadeComodosMax) {
        this.comodos = new Comodo[capacidadeComodosMax];
        this.numeroComodos = 0;
        this.capacidadeComodosMax = capacidadeComodosMax;
    }

    boolean cadastrarComodo(String nome, int capacidadeDispositivosMaxima) {
        for (int i = 0; i < numeroComodos; i++) {
            if (comodos[i].nome.equalsIgnoreCase(nome)) {
                System.out.println("Cômodo '" + nome + "' já existe na residência!");
                return false;
            }
        }
        if (numeroComodos < capacidadeComodosMax) {
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
        System.out.println("\n Resumo do Dia:");
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
        int index = 0;
        for (int i = 0; i < numeroComodos; i++) {
            for (int j = 0; j < comodos[i].numeroTotalDispositivos; j++) {
                todos[index++] = comodos[i].dispositivos[j];
            }
        }
        int totalComConsumo = 0;
        for (int i = 0; i < total; i++) {
            if (todos[i].calcularConsumoHora() > 0) {
                totalComConsumo++;
            }
        }

        Dispositivo[] comConsumo = new Dispositivo[totalComConsumo];
        int indexconsumo = 0;
        for (int i = 0; i < total; i++) {
            if (todos[i].calcularConsumoHora() > 0) {
                comConsumo[indexconsumo++] = todos[i];
            }
        }
        for (int i = 0; i < totalComConsumo - 1; i++) {
            for (int j = 0; j < totalComConsumo - 1 - i; j++) {
                if (comConsumo[j].calcularConsumoHora() < comConsumo[j + 1].calcularConsumoHora()) {
                    Dispositivo temp = comConsumo[j];
                    comConsumo[j] = comConsumo[j + 1];
                    comConsumo[j + 1] = temp;
                }
            }
        }

        System.out.println("\n Maiores Consumidores do Dia:");
        if (totalComConsumo == 0) {
            System.out.println("Nenhum dispositivo foi utilizado.");
        } else {
            for (int i = 0; i < totalComConsumo; i++) {
                System.out.printf("%d) %s (%s) - %.2f kWh%n",
                        i + 1, comConsumo[i].nome, comConsumo[i].comodo.nome, comConsumo[i].calcularConsumoHora());
            }
        }
    }

    void listarCapacidadesComodos() {
        System.out.println("\n Capacidades dos Cômodos:");
        if (numeroComodos == 0) {
            System.out.println("Nenhum cômodo cadastrado.");
        } else {
            for (int i = 0; i < numeroComodos; i++) {
                Comodo comodo = comodos[i];
                int vagas = comodo.capacidadeMax - comodo.numeroTotalDispositivos;
                System.out.println("\nCômodo: " + comodo.nome);
                System.out.println("  Capacidade máxima: " + comodo.capacidadeMax + " dispositivos");
                System.out.println("  Dispositivos atualmente: " + comodo.numeroTotalDispositivos);
                System.out.println("  Vagas disponíveis: " + vagas);
                if (comodo.numeroTotalDispositivos > 0) {
                    System.out.println("  Dispositivos:");
                    for (int j = 0; j < comodo.numeroTotalDispositivos; j++) {
                        System.out.println("    - " + comodo.dispositivos[j].nome + 
                                           " (Consumo: " + comodo.dispositivos[j].consumoHora + " kW)");
                    }
                }
            }
        }
        System.out.println("\nCapacidade máxima de cômodos da residência: " + capacidadeComodosMax);
        System.out.println("Cômodos cadastrados: " + numeroComodos);
        System.out.println("Cômodos disponíveis: " + (capacidadeComodosMax - numeroComodos));
    }
}
