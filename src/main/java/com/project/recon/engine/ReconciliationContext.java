package com.project.recon.engine;

import java.util.HashSet;
import java.util.Set;

@Data
public class ReconciliationContext {

    private Set<String> processedReferences = new HashSet<>();

    public boolean isDuplicate(String referenceId) {
        return !processedReferences.add(referenceId);
    }
}
