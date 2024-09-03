package dev.waiyanhtet.batch_processor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Data
@Builder
@Table(name = "TRANSACTION_HISTORY")
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;

    private BigDecimal trxAmount;
    private String description;

    private LocalDate trxDate;

    private String trxTime;

    private Long customerId;
}
