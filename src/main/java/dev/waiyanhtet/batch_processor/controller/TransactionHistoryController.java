package dev.waiyanhtet.batch_processor.controller;

import dev.waiyanhtet.batch_processor.controller.request.DescriptionUpdateRequest;
import dev.waiyanhtet.batch_processor.controller.response.TransactionHistoryResponse;
import dev.waiyanhtet.batch_processor.dto.TransactionHistoryDto;
import dev.waiyanhtet.batch_processor.mapper.TransactionHistoryResponseMapper;
import dev.waiyanhtet.batch_processor.service.TransactionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionHistoryController {

    private final TransactionHistoryResponseMapper responseMapper;
    private final TransactionHistoryService transactionHistoryService;

    @GetMapping(value = "")
    public ResponseEntity<Page<TransactionHistoryResponse>> searchTransactionByCustomerId(
            @RequestParam(value = "customerId", required = false, defaultValue = "") Long customerId,
            @RequestParam(value = "description", required = false, defaultValue = "") String description,
            @RequestParam(value = "accountNumbers", required = false) List<String> accountNumbers,
            Pageable pageable) {

        var pageableDto = transactionHistoryService.searchTransactionHistory(customerId, description, accountNumbers, pageable);
        var pageableResponse = new PageImpl<>(pageableDto.getContent()
                .stream()
                .map(responseMapper::asTransactionHistoryResponse)
                .collect(Collectors.toList()),
                pageable,
                pageableDto.getTotalElements());

        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }

    @PatchMapping(value = "/{transactionId}")
    public ResponseEntity<TransactionHistoryResponse> updateTransactionByDescription(
            @PathVariable(value = "transactionId") Long transactionId,
            @RequestBody DescriptionUpdateRequest request) {

        var dto = transactionHistoryService.updateDescriptionByTransactionId(request, transactionId);
        var response = responseMapper.asTransactionHistoryResponse(dto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
