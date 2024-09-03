package dev.waiyanhtet.batch_processor.service;

import dev.waiyanhtet.batch_processor.controller.request.DescriptionUpdateRequest;
import dev.waiyanhtet.batch_processor.dto.TransactionHistoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionHistoryService {

    TransactionHistoryDto updateDescriptionByTransactionId(DescriptionUpdateRequest request, Long transactionId);
    Page<TransactionHistoryDto> searchTransactionHistory(Long customerId, String description, List<String> accountNumber, Pageable pageable);
}
