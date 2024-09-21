package io.github.jonasfschuh.aws_RESTful.controller;

import com.amazonaws.services.s3.AmazonS3;
import io.github.jonasfschuh.aws_RESTful.model.UrlResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Value("${aws.s3.bucket.invoice.name}")
    private String bucketName;

    private AmazonS3 amazonS3;

    @Autowired
    public InvoiceController(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @PostMapping
    public ResponseEntity<UrlResponse> createInvoiceUrl() {
        UrlResponse urlResponse = new UrlResponse();
        Instant expiration = Instant.now().plus(Duration.ofMinutes(5));

        return new ResponseEntity<UrlResponse>(urlResponse, HttpStatus.OK);
    }

}
