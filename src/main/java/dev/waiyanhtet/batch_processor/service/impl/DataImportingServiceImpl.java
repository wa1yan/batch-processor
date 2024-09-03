package dev.waiyanhtet.batch_processor.service.impl;

import dev.waiyanhtet.batch_processor.service.DataImportingService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataImportingServiceImpl implements DataImportingService {


    private final Job job;
    private final JobLauncher jobLauncher;

    @Override
    public String importData(String fileName) {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();

        try {
            jobLauncher.run(job, jobParameters);

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
        
        return "Success";
    }
}
