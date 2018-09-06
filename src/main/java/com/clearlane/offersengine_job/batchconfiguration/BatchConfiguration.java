package com.clearlane.offersengine_job.batchconfiguration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.orm.JpaNativeQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.DefaultRepositoryMetadata;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import com.clearlane.offersengine_job.domain.QuoteOfferFulfillment;
import com.clearlane.offersengine_job.enums.QuoteOfferFulfillmentStatus;
import com.clearlane.offersengine_job.listener.JobCompletionListener;
import com.clearlane.offersengine_job.properties.OffersEngineJobProperties;
import com.clearlane.offersengine_job.repository.QuoteOfferFulfillmentRepository;
import com.clearlane.offersengine_job.repository.QuoteRequestRepository;
import com.clearlane.offersengine_job.requestprocessor.BluelinkFulfillmentRequestProcessor;
import com.clearlane.offersengine_job.service.OffersEngineDataService;
import com.clearlane.offersengine_job.steps.QuoteOfferFullfillmentSubmitionProcessor;
import com.clearlane.offersengine_job.steps.Writer;

@Configuration
@EnableBatchProcessing
@EnableTransactionManagement
@Import(BatchScheduler.class)
public class BatchConfiguration {

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public QuoteOfferFulfillmentRepository fulfillmentRepository;

    @Autowired
    private SimpleJobLauncher jobLauncher;
    
    /*@Autowired
    private EntityManagerFactory entityManagerFactory;*/

    @Autowired
    private OffersEngineJobProperties offersEngineJobProperties;

    @Autowired
    private QuoteRequestRepository quoteRequestRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JpaTransactionManager tm;

    @Scheduled(cron = "*/5 * * * * *")
    public void offersFulfillmentPerform() throws Exception {
        long startTime = System.currentTimeMillis();
        System.out.println("Job Started at in time:" + startTime);

        JobParameters param = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis())).toJobParameters();

        JobExecution execution = jobLauncher.run(offersFulfillmentJob(), param);

        System.out.println("Job finished with status :" + execution.getStatus());
    }

    @Bean
    public RepositoryMetadata repositoryMetadata() {
        return new DefaultRepositoryMetadata(QuoteOfferFulfillmentRepository.class);
    }

    @Bean
    public ItemReader<QuoteOfferFulfillment> reader() {
        JpaPagingItemReader<QuoteOfferFulfillment> fullfillment = new JpaPagingItemReader<>();
        try {
            String sqlQuery = "SELECT * FROM QUOTE_OFFER_FULFILLMENT QOF WHERE QOF.STATUS='"
                    + QuoteOfferFulfillmentStatus.FULFILLMENT_READY + "'" + " OR QOF.STATUS='"
                    + QuoteOfferFulfillmentStatus.FAILED + "'" + " AND QOF.RETRY_COUNT < 3";
            JpaNativeQueryProvider<QuoteOfferFulfillment> queryProvider = new JpaNativeQueryProvider<QuoteOfferFulfillment>();
            queryProvider.setSqlQuery(sqlQuery);
            queryProvider.setEntityClass(QuoteOfferFulfillment.class);
            queryProvider.afterPropertiesSet();
            //fullfillment.setEntityManagerFactory(entityManagerFactory);
            fullfillment.setEntityManagerFactory(tm.getEntityManagerFactory());
            fullfillment.setPageSize(40);
            fullfillment.setQueryProvider(queryProvider);
            fullfillment.afterPropertiesSet();
            fullfillment.setSaveState(true);
        }
        catch (Exception e) {
            System.out.println("BatchConfiguration.reader() ==> error " + e.getMessage());
        }
        return fullfillment;
    }
    
    @Bean
    public ItemProcessor<QuoteOfferFulfillment, QuoteOfferFulfillment> offersFulfillmentProcessor() {
        return new QuoteOfferFullfillmentSubmitionProcessor(offersEngineJobProperties);
    }

    @Bean
    public ItemWriter<QuoteOfferFulfillment> writer() {
        return new Writer(fulfillmentRepository, tm.getEntityManagerFactory(),
                offersEngineJobProperties,
                requestProcessor());
    }

    @Bean
    public BluelinkFulfillmentRequestProcessor requestProcessor() {
        return new BluelinkFulfillmentRequestProcessor(restTemplate, dataService());
    }

    @Bean
    public OffersEngineDataService dataService() {
        return new OffersEngineDataService(quoteRequestRepository);
    }

    @Bean
    public Job offersFulfillmentJob() throws Exception {
        System.out.println("BatchConfiguration2.job() =+> ");
        return jobBuilderFactory.get("offersFulfillmentJob").incrementer(new RunIdIncrementer())
                .listener(new JobCompletionListener()).flow(offersFulfillmentstep(offersFulfillmentProcessor())).end().build();
    
    }

    @Bean
    public Step offersFulfillmentstep(
            ItemProcessor<QuoteOfferFulfillment, QuoteOfferFulfillment> processor)
            throws Exception {
        return this.stepBuilderFactory.get("offersFulfillmentstep")
                .<QuoteOfferFulfillment, QuoteOfferFulfillment> chunk(1).reader(reader())
                .processor(processor).writer(writer()).build();
    }

}
