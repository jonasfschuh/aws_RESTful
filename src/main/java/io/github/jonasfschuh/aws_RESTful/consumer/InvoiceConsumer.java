package io.github.jonasfschuh.aws_RESTful.consumer;

import com.amazonaws.services.s3.AmazonS3;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jonasfschuh.aws_RESTful.repository.InvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceConsumer {

    private static final Logger log = LoggerFactory.getLogger(
            InvoiceConsumer.class);
    private final InvoiceRepository invoiceRepository;
    private final ObjectMapper objectMapper;
    private final AmazonS3 amazonS3;

    @Autowired
    public InvoiceConsumer(ObjectMapper objectMapper, InvoiceRepository invoiceRepository, AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
        this.objectMapper = objectMapper;
        this.invoiceRepository = invoiceRepository;
    }




}
