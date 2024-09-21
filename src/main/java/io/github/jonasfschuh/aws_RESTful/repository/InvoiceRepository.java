package io.github.jonasfschuh.aws_RESTful.repository;

import io.github.jonasfschuh.aws_RESTful.model.Invoice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);

    List<Invoice> findAllByCustomerName(String customerName);
}
