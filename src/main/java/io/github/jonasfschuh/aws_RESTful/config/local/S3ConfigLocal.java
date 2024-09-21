package io.github.jonasfschuh.aws_RESTful.config.local;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class S3ConfigLocal {
    private static final String BUCKET_NAME = "pcs-invoice";

    private AmazonS3 amazonS3;

    public S3ConfigLocal() {
        amazonS3 = getAmazonS3();

        createBucket();

        AmazonSNS snsClient = getAmazonSns();

        String s3InvoiceEventsTopicArn = createTopic(snsClient, "s3-invoice-events");

        AmazonSQS sqsClient = getAmazonSqs();

        createQueue(snsClient, s3InvoiceEventsTopicArn, sqsClient;

        configureBucket(s3InvoiceEventsTopicArn);
    }

    private String createTopic(AmazonSNS snsClient, String s) {
        return snsClient.createTopic(s).getTopicArn();
    }

    private AmazonS3 getAmazonS3() {
        AWSCredentials credentials = new BasicAWSCredentials("test", "test");
        return AmazonS3ClientBuilder.builder()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("http://localhost:4572",
                                Regions.US_EAST_1.getName()))
                .withCredentials(credentials)
                .enablePathStyleAccess()
                .build();
    }

    private void createBucket() {
        amazonS3.createBucket(BUCKET_NAME);
    }

    @Bean
    public AmazonS3 amazonS3Client() {
        return amazonS3;
    }

    private AmazonSNS getAmazonSns() {
        return AmazonSNSClient.builder()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("http://localhost:4566",
                                Regions.US_EAST_1.getName()))
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
    }

    private AmazonSQS getAmazonSqs() {
        return AmazonSQSClient.builder()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("http://localhost:4566",
                                Regions.US_EAST_1.getName()))
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
    }





}
