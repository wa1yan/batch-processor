package dev.waiyanhtet.batch_processor.mapper;

import dev.waiyanhtet.batch_processor.controller.response.TransactionHistoryResponse;
import dev.waiyanhtet.batch_processor.dto.TransactionHistoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionHistoryResponseMapper {

    TransactionHistoryResponse asTransactionHistoryResponse(TransactionHistoryDto dto);

    List<TransactionHistoryResponse> asTransactionHistoryResponseList(List<TransactionHistoryDto> dto);
}
