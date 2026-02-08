package com.project.recon.service;

@Service
@RequiredArgsConstructor
public class ReconciliationService {

    private final TransactionRepository repository;

    public void reconcile(List<TransactionDTO> dtos) {
        for (TransactionDTO dto : dtos) {
            TransactionEntity entity = new TransactionEntity();
            entity.setReferenceId(dto.getReferenceId());
            entity.setAmount(dto.getAmount());
            entity.setStatus(dto.getAmount() > 0 ? "MATCHED" : "UNMATCHED");
            repository.save(entity);
        }
    }
}
