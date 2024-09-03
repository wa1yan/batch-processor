package dev.waiyanhtet.batch_processor.config;

import dev.waiyanhtet.batch_processor.dto.TransactionHistoryDto;
import dev.waiyanhtet.batch_processor.utility.MapperUtils;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.math.BigDecimal;

public class TransactionHistoryFieldSetMapper implements FieldSetMapper<TransactionHistoryDto> {

    @Override
    public TransactionHistoryDto mapFieldSet(FieldSet fieldSet) throws BindException {
        return TransactionHistoryDto.builder()
                .accountNumber(fieldSet.readString("ACCOUNT_NUMBER"))
                .trxAmount(new BigDecimal(fieldSet.readString("TRX_AMOUNT")))
                .description(fieldSet.readString("DESCRIPTION"))
                .trxDate(MapperUtils.convertLocalDate(fieldSet.readString("TRX_DATE")))
                .trxTime(fieldSet.readString("TRX_TIME"))
                .customerId(Long.valueOf(fieldSet.readString("CUSTOMER_ID")))
                .build();
    }
}
