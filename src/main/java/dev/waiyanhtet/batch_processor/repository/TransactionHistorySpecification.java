package dev.waiyanhtet.batch_processor.repository;

import dev.waiyanhtet.batch_processor.entity.TransactionHistoryEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.List;

public class TransactionHistorySpecification {

    private static final String likeString = "%";

    public static Specification<TransactionHistoryEntity> hasCustomerId(Long customerId) {
        return ((root, query, criteriaBuilder) -> {
            if(customerId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("customerId"), customerId);
        });
    }

    public static Specification<TransactionHistoryEntity> hasDescription(String description) {
        return ((root, query, criteriaBuilder) -> {
            if(!StringUtils.hasText(description)) {
                return null;
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("description")),
                    likeString.concat(description.toLowerCase()).concat(likeString));
        });
    }

    public static Specification<TransactionHistoryEntity> includeAccountNumber(List<String> accountNumbers) {
        return ((root, query, criteriaBuilder) -> {
            if(accountNumbers == null || accountNumbers.isEmpty()) {
                return null;
            }
            return root.get("accountNumber").in(accountNumbers);
        });
    }
}
