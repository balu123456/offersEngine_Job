package com.clearlane.offersengine_job.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class OffersEngineJobsController {

    /*@Autowired
    private QuoteOfferFulfillmentService fullfillmentService;*/

    @RequestMapping("/sample")
    String home() {

        //fullfillmentService.loadFullfillment();
        return "This is the first program in OffersEngineJobApplication again!";

    }

    /* @RequestMapping("/invokejob")
    public String handle() throws Exception {
    
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()).toJobParameters();
        jobLauncher.run(processJob, jobParameters);
    
        return "Batch job has been invoked";
    }*/

}
