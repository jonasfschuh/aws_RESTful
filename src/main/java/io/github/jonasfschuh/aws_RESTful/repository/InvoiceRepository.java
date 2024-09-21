package io.github.jonasfschuh.aws_RESTful.repository;

import io.github.jonasfschuh.aws_RESTful.model.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {


}
