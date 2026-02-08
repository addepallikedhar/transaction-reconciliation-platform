package com.project.recon.util;

public final class CsvParserUtil {

    private CsvParserUtil() {}

    public static List<TransactionDTO> parse(List<String> lines) {
        if (lines == null || lines.isEmpty()) {
            throw new IllegalArgumentException("CSV is empty");
        }

        return lines.stream()
                .skip(1)
                .map(line -> {
                    String[] data = line.split(",");
                    return new TransactionDTO(data[0], Double.parseDouble(data[1]));
                })
                .collect(Collectors.toList());
    }
}
