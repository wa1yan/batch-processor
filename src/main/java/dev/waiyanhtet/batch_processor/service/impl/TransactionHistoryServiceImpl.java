package dev.waiyanhtet.batch_processor.service.impl;

import dev.waiyanhtet.batch_processor.controller.request.DescriptionUpdateRequest;
import dev.waiyanhtet.batch_processor.dto.TransactionHistoryDto;
import dev.waiyanhtet.batch_processor.exception.ResourceNotFoundException;
import dev.waiyanhtet.batch_processor.mapper.TransactionHistoryMapper;
import dev.waiyanhtet.batch_processor.repository.TransactionHistoryRepo;
import dev.waiyanhtet.batch_processor.service.TransactionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    private final TransactionHistoryRepo transactionHistoryRepo;
    private final TransactionHistoryMapper mapper;


    @Override
    public TransactionHistoryDto updateDescriptionByTransactionId(DescriptionUpdateRequest request, Long transactionId) {
        return transactionHistoryRepo.findById(transactionId)
                .map(entity -> {
                    entity.setDescription(request.getDescription());
                    return transactionHistoryRepo.save(entity);
                })
                .map(mapper::asTransactionHistoryDto)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction Id %s not found".formatted(transactionId)));
    }

    @Override
    public Page<TransactionHistoryDto> searchTransactionHistory(Long customerId, String description, List<String> accountNumbers, Pageable pageable) {
        var pageableEntityList = transactionHistoryRepo.findAllBySpecification(
                customerId, description, accountNumbers, pageable);
        return new PageImpl<>(pageableEntityList.getContent()
                    .stream()
                    .map(mapper::asTransactionHistoryDto)
                    .collect(Collectors.toList()),
                pageable,
                pageableEntityList.getTotalElements());
    }
}
