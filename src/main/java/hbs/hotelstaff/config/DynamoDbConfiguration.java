package hbs.hotelstaff.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamoDbConfiguration {

	@Value("${amazon.dynamodb.endpoint}")
	private String dynamodbEndpoint;

	@Value("${amazon.aws.region}")
	private String awsRegion;

	@Value("${amazon.aws.accesskey}")
	private String dynamodbAccessKey;

	@Value("${amazon.aws.secretkey}")
	private String dynamodbSecretKey;

	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		return new DynamoDBMapper(buildAmazonDynamoDB());
	}

	private AmazonDynamoDB buildAmazonDynamoDB() {

		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(
						new AwsClientBuilder.EndpointConfiguration(this.dynamodbEndpoint, this.awsRegion))
				.withCredentials(new AWSStaticCredentialsProvider(
						new BasicAWSCredentials(this.dynamodbAccessKey, this.dynamodbSecretKey)))
				.build();
	}

}
