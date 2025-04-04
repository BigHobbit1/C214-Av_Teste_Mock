package Atendimento;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BuscaHorario {

    HorarioService horarioService;

    public BuscaHorario(HorarioService service) {
        this.horarioService = service;
    }

    public Horario buscaHorario(int sala) {
        String horarioJson = horarioService.busca(sala);
        JsonObject jsonObject = JsonParser.parseString(horarioJson).getAsJsonObject();

        return new Horario(
                jsonObject.get("nome_professor").getAsString(),
                jsonObject.get("horario").getAsString(),
                jsonObject.get("periodo").getAsString(),
                jsonObject.get("sala").getAsInt()
        );
    }

    public boolean verificaHorarioExistente(int sala) {
        return horarioService.horarioExistente(sala);
    }
}