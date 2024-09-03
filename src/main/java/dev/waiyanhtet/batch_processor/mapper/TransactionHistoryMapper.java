package dev.waiyanhtet.batch_processor.mapper;

import dev.waiyanhtet.batch_processor.dto.TransactionHistoryDto;
import dev.waiyanhtet.batch_processor.entity.TransactionHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionHistoryMapper {

    TransactionHistoryEntity asTransactionHistory(TransactionHistoryDto dto);
    List<TransactionHistoryEntity> asTransactionHistoryList(List<TransactionHistoryDto> dto);

    TransactionHistoryDto asTransactionHistoryDto(TransactionHistoryEntity dto);
    List<TransactionHistoryDto> asTransactionHistoryDtoList(List<TransactionHistoryEntity> dto);

}
