import Atendimento.HorarioService;

public class MockHorarioService implements HorarioService {

    @Override
    public String busca(int sala) {
        switch (sala) {
            case 5:
                return "{ \"nome_professor\": \"Renan\", \"horario\": \"10:00\", \"periodo\": \"integral\", \"sala\": 5, \"predio\": 1 }";
            case 10:
                return "{ \"nome_professor\": \"Pedro\", \"horario\": \"14:00\", \"periodo\": \"noturno\", \"sala\": 10, \"predio\": 2 }";
            case 1:
                return "{ \"nome_professor\": \"Padrão\", \"horario\": \"08:00\", \"periodo\": \"integral\", \"sala\": 1, \"predio\": 1 }";
            default:
                return "{}";
        }
    }

    @Override
    public boolean horarioExistente(int sala) {
        return sala > 0 && sala <= 10;
    }
}