package dev.waiyanhtet.batch_processor.repository;

import dev.waiyanhtet.batch_processor.entity.TransactionHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionHistoryRepo extends JpaRepository<TransactionHistoryEntity, Long>, JpaSpecificationExecutor<TransactionHistoryEntity> {

    default Page<TransactionHistoryEntity> findAllBySpecification(Long customerId, String description, List<String> accountNumbers, Pageable pageable) {
        Specification<TransactionHistoryEntity> spec = Specification.where(
                TransactionHistorySpecification.hasCustomerId(customerId))
                .and(TransactionHistorySpecification.hasDescription(description))
                .and(TransactionHistorySpecification.includeAccountNumber(accountNumbers));
        return findAll(spec, pageable);
    }
}
