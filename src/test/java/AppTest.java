import Atendimento.BuscaHorario;
import Atendimento.Horario;
import Atendimento.HorarioService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AppTest {

    @Test
    public void testMockHorarioInexistente() {
        HorarioService mockService = mock(HorarioService.class);

        when(mockService.busca(99)).thenReturn("{}");
        when(mockService.horarioExistente(99)).thenReturn(false);

        BuscaHorario buscaHorario = new BuscaHorario(mockService);

        Horario horario = buscaHorario.buscaHorario(99);
        assertNull(horario);
        assertFalse(buscaHorario.verificaHorarioExistente(99));
    }

    @Test
    public void testMockHorarioExistente() {
        HorarioService mockService = mock(HorarioService.class);

        when(mockService.horarioExistente(5)).thenReturn(true);
        when(mockService.horarioExistente(10)).thenReturn(true);

        BuscaHorario buscaHorario = new BuscaHorario(mockService);

        assertTrue(buscaHorario.verificaHorarioExistente(5));
        assertTrue(buscaHorario.verificaHorarioExistente(10));
    }

    @Test
    public void testBuscaHorarioValido() {
        HorarioService mockService = mock(HorarioService.class);
        when(mockService.busca(5)).thenReturn("{\"nome_professor\": \"Renan\", \"horario\": \"10:00\", \"periodo\": \"integral\", \"sala\": 5}");

        BuscaHorario buscaHorario = new BuscaHorario(mockService);
        Horario horario = buscaHorario.buscaHorario(5);

        assertEquals("Renan", horario.getNome_professor());
        assertEquals("10:00", horario.getHorario());
        assertEquals("integral", horario.getPeriodo());
        assertEquals(5, horario.getSala());
    }

    @Test
    public void testSalaExistenteValido() {
        HorarioService mockService = mock(HorarioService.class);
        when(mockService.horarioExistente(5)).thenReturn(true);

        BuscaHorario buscaHorario = new BuscaHorario(mockService);
        assertTrue(buscaHorario.verificaHorarioExistente(5));
    }

    @Test
    public void testSalaNaoExistente() {
        HorarioService mockService = mock(HorarioService.class);
        when(mockService.horarioExistente(99)).thenReturn(false);

        BuscaHorario buscaHorario = new BuscaHorario(mockService);
        assertFalse(buscaHorario.verificaHorarioExistente(99));
    }

    @Test
    public void testSalaForaDoIntervalo() {
        Horario horario = new Horario("Robertinho", "10:00", "integral", 5);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> horario.setSala(26));
        assertEquals("O numero da sala esta incorreto", exception.getMessage());
    }

    @Test
    public void testValidacaoPredio() {
        Horario horario = new Horario("Thiago", "14:00", "noturno", 10);
        assertEquals(2, horario.getPredio());
    }

    @Test
    public void testPredioParaSalaLimite() {
        Horario horario1 = new Horario("Chris", "10:00", "integral", 5);
        Horario horario2 = new Horario("Chris", "14:00", "noturno", 6);

        assertEquals(1, horario1.getPredio());
        assertEquals(2, horario2.getPredio());
    }

    @Test
    public void testPeriodoInvalido() {
        Horario horario = new Horario("Romario", "10:00", "integral", 5);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> horario.setPeriodo("matutino"));
        assertEquals("Turno inválido. Use apenas 'integral' ou 'noturno'", exception.getMessage());
    }

    @Test
    public void testPeriodoValido() {
        Horario horario = new Horario("Romario", "10:00", "noturno", 5);
        assertEquals("noturno", horario.getPeriodo());
    }

    @Test
    public void testSalaForaDoIntervaloUni() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horario("Prof. Silva", "10:00", "integral", 30));
        assertEquals("O numero da sala esta incorreto", exception.getMessage());
    }

    @Test
    public void testSetPredioSalaInvalidaUni() {
        Horario horario = new Horario("Souza", "10:00", "integral", 5);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> horario.setSala(50));
        assertEquals("O numero da sala esta incorreto", exception.getMessage());
    }

    @Test
    public void testErroInesperadoNoServico() {
        HorarioService mockService = mock(HorarioService.class);

        when(mockService.busca(105)).thenThrow(new RuntimeException("Erro inesperado no serviço"));

        BuscaHorario buscaHorario = new BuscaHorario(mockService);

        Exception exception = assertThrows(RuntimeException.class, () -> buscaHorario.buscaHorario(105));
        assertEquals("Erro inesperado no serviço", exception.getMessage());
    }

    @Test
    public void testJsonMalformadoLancaExcecao() {
        HorarioService mockService = mock(HorarioService.class);

        when(mockService.busca(101)).thenReturn("{ nome_professor: Prof. Souza, horario: \"10:00\"");

        BuscaHorario buscaHorario = new BuscaHorario(mockService);

        assertThrows(com.google.gson.JsonSyntaxException.class, () -> buscaHorario.buscaHorario(101));
    }

    @Test
    public void testSetPredioSalaInvalidaUni2() {
        Horario horario = new Horario("Renan", "1:00", "noturno", 10);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> horario.setSala(50));
        assertEquals("O numero da sala esta incorreto", exception.getMessage());
    }

    @Test
    public void testSetPredioSalaInvalidaUni3() {
        Horario horario = new Horario("Varbo", "10:00", "integral", 23);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> horario.setSala(50));
        assertEquals("O numero da sala esta incorreto", exception.getMessage());
    }

}