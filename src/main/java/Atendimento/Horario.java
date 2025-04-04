package Atendimento;
import java.sql.Time;

public class Horario {
    private String nome_professor;
    private String horario;
    private String periodo;
    private int sala;
    private int predio;

    public Horario(String nome_professor, String horario, String periodo, int sala) {
        this.nome_professor = nome_professor;
        this.horario = horario;
        setPeriodo(periodo);
        setSala(sala);
        setPredio(sala);
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        if(sala < 1 || sala > 25){
            throw new IllegalArgumentException("O numero da sala esta incorreto");
        }
        this.sala = sala;
    }

    public void setPeriodo(String periodo) {
        if (!periodo.equalsIgnoreCase("integral") && !periodo.equalsIgnoreCase("noturno")) {
            throw new IllegalArgumentException("Turno inválido. Use apenas 'integral' ou 'noturno'");
        }
        this.periodo = periodo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public String getNome_professor() {
        return nome_professor;
    }

    public void setNome_professor(String nome_professor) {
        this.nome_professor = nome_professor;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getPredio() {
        return predio;
    }

    public void setPredio(int sala) {
        if(sala >= 1 && sala <= 5){
            this.predio = 1;
        } else if (sala>=6 && sala<=10) {
            this.predio = 2;
        } else if (sala>=11 && sala<=15) {
            this.predio = 3;
        } else if (sala>=16 && sala<=20) {
            this.predio = 4;
        } else if (sala >= 21 && sala <= 25) {
            this.predio = 6;
        } else {
            throw new IllegalArgumentException("Sala fora do intervalo permitido para prédios.");
        }
    }
}
