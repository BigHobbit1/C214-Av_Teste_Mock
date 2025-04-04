import Atendimento.BuscaHorario;
import Atendimento.Horario;
import Atendimento.HorarioService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TesteBuscaHorario {

    static HorarioService service;
    static BuscaHorario buscaHorario;

    @BeforeAll
    public static void setup() {
        // Criando o contexto do meu teste com o Mock (serviço mock)
        service = new MockHorarioService();
        buscaHorario = new BuscaHorario(service);
    }

    @Test
    public void testeBuscaHorarioProfessorSilva() {
        // Fiz a busca
        Horario horarioSilva = buscaHorario.buscaHorario(5);

        // Faz assertion
        assertEquals("Renan", horarioSilva.getNome_professor());
        assertEquals("10:00", horarioSilva.getHorario());
        assertEquals("integral", horarioSilva.getPeriodo());
        assertEquals(5, horarioSilva.getSala());
        assertEquals(1, horarioSilva.getPredio());
    }

    @Test
    public void testeBuscaHorarioProfessorSouza() {
        Horario horarioSouza = buscaHorario.buscaHorario(10);

        assertEquals("Pedro", horarioSouza.getNome_professor());
        assertEquals("14:00", horarioSouza.getHorario());
        assertEquals("noturno", horarioSouza.getPeriodo());
        assertEquals(10, horarioSouza.getSala());
        assertEquals(2, horarioSouza.getPredio());
    }

    @Test
    public void testeBuscaHorarioPadrao() {
        Horario horarioPadrao = buscaHorario.buscaHorario(1);

        assertEquals("Padrão", horarioPadrao.getNome_professor());
        assertEquals("08:00", horarioPadrao.getHorario());
        assertEquals("integral", horarioPadrao.getPeriodo());
        assertEquals(1, horarioPadrao.getSala());
        assertEquals(1, horarioPadrao.getPredio());
    }

    @Test
    public void testeBuscaHorarioValido() {
        boolean horarioValido = buscaHorario.verificaHorarioExistente(5);

        assertTrue(horarioValido);
    }

    @Test
    public void testeBuscaHorarioInvalido() {
        boolean horarioValido = buscaHorario.verificaHorarioExistente(-8);

        assertFalse(horarioValido);
    }
}
