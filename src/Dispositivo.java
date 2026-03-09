public class Dispositivo {
    String nome;
    Comodo comodo;
    float consumoHora;
    float tempoHoraUso;
    boolean ligado;


    Dispositivo(String nome, float consumoHora) {
        this.nome = nome;
        this.consumoHora = consumoHora;
        this.tempoHoraUso = 0;
        this.ligado = false;
    }
    void ligar() {
        if (!ligado) {
            ligado = true;
            System.out.println("Dispositivo " + nome + " ligado.");
        } else {
            System.out.println("Dispositivo " + nome + " já está ligado.");
        }
    }
    void desligar() {
        if (ligado) {
            ligado = false;
            System.out.println("Dispositivo " + nome + " desligado.");
        } else {
            System.out.println("Dispositivo " + nome + " já está desligado.");
        }
    }
    boolean registrarTempoUso(float horas) {
        if (ligado) {
            tempoHoraUso += horas;
            System.out.println("Tempo de uso registrado: " + horas + " horas.");
            return true;
        } else {
            System.out.println("Dispositivo " + nome + " está desligado. Não é possível registrar tempo de uso.");
            return false;
        }
    }
    float calcularConsumoHora() {
        return consumoHora * tempoHoraUso;
    }
}
