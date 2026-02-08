CREATE TABLE transaction_file (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  file_name VARCHAR(255),
                                  file_hash VARCHAR(64),
                                  status VARCHAR(50),
                                  uploaded_at TIMESTAMP
);

CREATE TABLE transaction_record (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    file_id BIGINT,
                                    reference_id VARCHAR(100),
                                    amount DECIMAL(15,2),
                                    business_date DATE,
                                    status VARCHAR(50),
                                    CONSTRAINT fk_file FOREIGN KEY (file_id)
                                        REFERENCES transaction_file(id)
);

CREATE TABLE reconciliation_result (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       transaction_id BIGINT,
                                       result_code VARCHAR(50),
                                       remarks VARCHAR(255),
                                       reconciled_at TIMESTAMP,
                                       CONSTRAINT fk_txn FOREIGN KEY (transaction_id)
                                           REFERENCES transaction_record(id)
);

CREATE TABLE audit_log (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           entity_type VARCHAR(50),
                           entity_id BIGINT,
                           action VARCHAR(50),
                           old_value VARCHAR(255),
                           new_value VARCHAR(255),
                           created_at TIMESTAMP
);
