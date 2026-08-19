package Interface;

import Posições.Jogador;
import java.util.List;
import java.util.stream.Collectors;

public class FiltroC {

    public static <T extends Jogador> List<T> aplicarFiltro(List<T> listaOriginal, FiltroB filtro) {
        if (filtro == null || listaOriginal == null) {
            return listaOriginal;
        }

        return listaOriginal.stream()
            .filter(j -> {
                // 1. Filtro de Nacionalidade
                if (filtro.getNacionalidade() != null 
                        && !filtro.getNacionalidade().trim().isEmpty() 
                        && !filtro.getNacionalidade().equalsIgnoreCase("Todos")) {

                    boolean mesmaNacionalidade = j.getNacionalidade().equalsIgnoreCase(filtro.getNacionalidade());
                    if ("É".equalsIgnoreCase(filtro.getTipoNacionalidade()) && !mesmaNacionalidade) return false;
                    if ("Não É".equalsIgnoreCase(filtro.getTipoNacionalidade()) && mesmaNacionalidade) return false;
                }

                // 2. Faixa de Idade
                if (filtro.getIdadeMin() != null && j.getIdade() < filtro.getIdadeMin()) return false;
                if (filtro.getIdadeMax() != null && j.getIdade() > filtro.getIdadeMax()) return false; 

                // 3. Time / Clube
                if (filtro.getTime() != null 
                        && !filtro.getTime().trim().isEmpty() 
                        && !filtro.getTime().equalsIgnoreCase("Todos")) {

                    if (!j.getTime().equalsIgnoreCase(filtro.getTime())) return false;
                }

                // 4. Minutos Jogados
                if (filtro.getValorMinutos() != null && filtro.getTipoMinutos() != null) {
                    int minJogados = j.getMinutosJogados(); 
                    int valorRef = filtro.getValorMinutos();

                    switch (filtro.getTipoMinutos()) {
                        case "Maior Que" -> { if (minJogados <= valorRef) return false; }
                        case "Menor Que" -> { if (minJogados >= valorRef) return false; }
                        case "Igual a"   -> { if (minJogados != valorRef) return false; }
                    }
                }

                // 5. Nota Média
                if (filtro.getValorNota() != null && filtro.getTipoNota() != null) {
                    double nota = j.calcularNota();
                    double notaRef = filtro.getValorNota();

                    switch (filtro.getTipoNota()) {
                        case "Maior Que" -> { if (nota <= notaRef) return false; }
                        case "Menor Que" -> { if (nota >= notaRef) return false; }
                        case "Igual a"   -> { if (Math.abs(nota - notaRef) > 0.05) return false; }
                    }
                }

                return true;
            })
            .collect(Collectors.toList());
    }
}