package afriqueMed.business;

import afriqueMed.domain.historyLog.HistoryLog;
import afriqueMed.infra.operations.HistoryLogRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class HistoryLogService {

    @Inject
    HistoryLogRepository historyLogRepository;

    @Transactional
    public void createHistoryLog(HistoryLog historyLog) {
        historyLogRepository.save(historyLog);
    }

    public HistoryLog getHistoryLog(Long id) {
        return historyLogRepository.findById(id);
    }

    public List<HistoryLog> getAllHistoryLogs() {
        return historyLogRepository.findAll();
    }

    @Transactional
    public void deleteHistoryLog(Long id) {
        HistoryLog historyLog = historyLogRepository.findById(id);
        if (historyLog != null) {
            historyLogRepository.delete(historyLog);
        }
    }
}
