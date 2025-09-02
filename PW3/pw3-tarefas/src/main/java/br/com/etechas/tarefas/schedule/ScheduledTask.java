package br.com.etechas.tarefas.schedule;

import br.com.etechas.tarefas.entity.Tarefa;
import br.com.etechas.tarefas.enums.StatusEnum;
import br.com.etechas.tarefas.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class ScheduledTask {

    @Autowired
    private TarefaRepository taskRepository;

    private static final String[] TASK_TITLES = {
        "Revisar código",
        "Atualizar documentação",
        "Fazer backup do sistema",
        "Verificar logs de erro",
        "Executar testes automatizados",
        "Monitorar performance",
        "Limpar cache",
        "Verificar segurança"
    };

    private static final String[] TASK_DESCRIPTIONS = {
        "Revisar o código desenvolvido na última semana",
        "Atualizar a documentação do projeto",
        "Realizar backup automático do sistema",
        "Verificar e analisar logs de erro",
        "Executar bateria de testes automatizados",
        "Monitorar performance e uso de recursos",
        "Limpar cache para otimizar performance",
        "Verificar questões de segurança"
    };

    private static final String[] RESPONSIBLES = {
        "Sistema Automático",
        "Administrador",
        "Desenvolvedor",
        "Analista de Qualidade",
        "DevOps"
    };

    @Scheduled(fixedRate = 60000)
    public void createAutomaticTask() {
        Random random = new Random();
        
        Tarefa task = new Tarefa();
        
        // Seleciona aleatoriamente um título e descrição
        int index = random.nextInt(TASK_TITLES.length);
        task.setTitle(TASK_TITLES[index]);
        task.setDescription(TASK_DESCRIPTIONS[index]);


        // Define data de vencimento para a partir de hoje
        task.setDueDate(LocalDate.now().plusDays(1)
                                       .plusDays(random.nextInt(10)));

        // Define status como PENDING
        task.setStatus(StatusEnum.values()[random.nextInt(3)]);

        // Define um responsável aleatório
        task.setResponsible(RESPONSIBLES[random.nextInt(RESPONSIBLES.length)]);
        
        // Salva a tarefa no banco
        taskRepository.save(task);
        
        System.out.println("Tarefa automática criada: " + task.getTitle() + 
                          " - Responsável: " + task.getResponsible() + 
                          " - Data: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }
} 