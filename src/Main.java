import com.projeto.controller.CasaController;
import com.projeto.model.Casa;
import com.projeto.model.CasaObserver;
import com.projeto.model.StatusRede;
import com.projeto.repository.CasaRepository;
import com.projeto.view.GerenciamentoFonteView;
import java.time.YearMonth;

public class Main {
    public static void main(String[] args) {
        CasaRepository casaRepository = new CasaRepository();
        CasaController casaController = new CasaController(casaRepository);

        CasaObserver auditoriaObserver = new CasaObserver() {
            @Override
            public void onStatusChanged(Casa casa, StatusRede antigoStatus, StatusRede novoStatus) {
                System.out.println("\n[OBSERVER NOTIFICAÇÃO]: A casa do(a) " + casa.getNomeResponsavel()
                        + " mudou de " + antigoStatus + " para " + novoStatus + "!");
            }
        };

        // 1. Cadastramos a casa do Lucas
        casaController.cadastrarCasa(10, "Lucas Menezes", "Rua Central, N 50");
        casaRepository.buscarPorId(10).adicionarObservador(auditoriaObserver);

        System.out.println("\n--- SIMULAÇÃO DE VIRADA DE MÊS ---");
        // Forçamos o sistema a processar o faturamento como se estivéssemos no mês passado (Junho)
        System.out.println("1. Rodando rotina automática para Junho:");
        casaController.processarFaturamentoAutomatico(YearMonth.now().minusMonths(1), 50.0);

        // Agora simulamos que o tempo passou e o sistema acordou no mês atual (Julho)
        System.out.println("\n2. O tempo passou! Rodando rotina automática para o mês Atual:");
        casaController.processarFaturamentoAutomatico(YearMonth.now(), 50.0);
        System.out.println("----------------------------------\n");

        GerenciamentoFonteView view = new GerenciamentoFonteView(casaController);
        view.exibirMenu();
    }
}