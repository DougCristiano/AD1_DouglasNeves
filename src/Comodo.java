public class Comodo {
    String nome;
    Dispositivo[] dispositivos;
    int numeroTotalDispositivos;
    int capacidadeMax;

    Comodo(String nome, int capacidadeMax) {
        this.nome = nome;
        this.dispositivos = new Dispositivo[capacidadeMax];
        this.numeroTotalDispositivos = 0;
        this.capacidadeMax = capacidadeMax;
    }
    boolean adicionarDispositivo(Dispositivo dispositivo) {
        if (numeroTotalDispositivos < dispositivos.length) {
            dispositivos[numeroTotalDispositivos] = dispositivo;
            numeroTotalDispositivos++;
            System.out.println("Dispositivo " + dispositivo.nome + " adicionado ao cômodo " + nome + ".");
            return true;
        } else {
            System.out.println("Capacidade máxima de dispositivos atingida para o cômodo " + nome + ".");
            return false;
        }
    }
    float calcularConsumoTotal() {
        float consumoTotal = 0;
        for (int i = 0; i < numeroTotalDispositivos; i++) {
            consumoTotal += dispositivos[i].calcularConsumoHora();
        }
        return consumoTotal;
    }
    Dispositivo buscarDispositivo(String nomeDispositivo) {
        for (int i = 0; i < numeroTotalDispositivos; i++) {
            if (dispositivos[i].nome.equals(nomeDispositivo)) {
                return dispositivos[i];
            }
        }
        System.out.println("Dispositivo " + nomeDispositivo + " não encontrado no cômodo " + nome + ".");
        return null;
    }
}
