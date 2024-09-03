package dev.waiyanhtet.batch_processor.config;

import dev.waiyanhtet.batch_processor.dto.TransactionHistoryDto;
import dev.waiyanhtet.batch_processor.entity.TransactionHistoryEntity;
import dev.waiyanhtet.batch_processor.mapper.TransactionHistoryMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;

@RequiredArgsConstructor
public class TransactionHistoryProcessor implements ItemProcessor<TransactionHistoryDto, TransactionHistoryEntity> {

    private final TransactionHistoryMapper mapper;

    @Override
    public TransactionHistoryEntity process(TransactionHistoryDto dto) throws Exception {
        return mapper.asTransactionHistory(dto);
    }
}
