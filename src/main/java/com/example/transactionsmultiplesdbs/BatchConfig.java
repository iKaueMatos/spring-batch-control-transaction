package com.example.transactionsmultiplesdbs;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Autowired
  @Qualifier("transactionManagerApp")
  private PlatformTransactionManager transactionManager;

  @Bean
  public Job job(Step step) {
    return jobBuilderFactory
        .get("job")
        .start(step)
        .incrementer(new RunIdIncrementer())
        .build();
  }

  @Bean
  public Step step(ItemReader<Person> reader, ItemWriter<Person> writer) {
    return stepBuilderFactory
        .get("step")
        .<Person, Person>chunk(200)
        .reader(reader)
        .writer(writer)
        .transactionManager(transactionManager)
        .build();
  }

  @Bean
  public ItemReader<Person> reader() {
    return new FlatFileItemReaderBuilder<Person>()
        .name("reader")
        .resource(new FileSystemResource("files/pessoas.csv"))
        .comments("--")
        .delimited()
        .names("name", "email", "birthDate", "age", "id")
        .targetType(Person.class)
        .build();
  }

  @Bean
  public ItemWriter<Person> writer(@Qualifier("appDS") DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<Person>()
        .dataSource(dataSource)
        .sql(
            "INSERT INTO person (id, name, email, birthDate, age) VALUES (:id, :name, :email, :birthDate, :age)")
        .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
        .build();
  }
}
