package com.project.recon.engine;

import java.util.HashSet;
import java.util.Set;

public class ReconciliationContext {

    private final Set<String> processedRefs = new HashSet<>();

    public boolean isDuplicate(String referenceId) {
        return !processedRefs.add(referenceId);
    }
}
