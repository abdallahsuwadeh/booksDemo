package com.sumeria.booksDemo.booksDemo.backgrounJobs;

import com.sumeria.booksDemo.booksDemo.models.Book;
import com.sumeria.booksDemo.booksDemo.repositories.BooksRepository;
import com.sumeria.booksDemo.booksDemo.services.BooksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component // explain why component
public class DataFillerJob {
    private final BooksRepository repository;

    Logger logger = LoggerFactory.getLogger(DataFillerJob.class);


    public DataFillerJob(BooksRepository repository){
        this.repository = repository;
    }

    // Run every minute
    @Scheduled(cron = "0 * * * * *")
    public void cronJobSch() {
        logger.info("Starting running DataFillerJob" + LocalDateTime.now());
        repository.createOrUpdateBook(new Book("My_Lovely_Book", "Talal Mahmoud", 88));
        repository.createOrUpdateBook(new Book("book_2", "Abed Mohammad", 23));
        repository.createOrUpdateBook(new Book("Look Away", "Mahmoud Blabla", 324));
        repository.createOrUpdateBook(new Book("My_Lovely_Book2", "Talal Mahmoud", 828));
        repository.createOrUpdateBook(new Book("My_Lovely_Book3", "Talal Mahmoud", 11));
    }

    @Scheduled(cron = "*/1 * * * * *")
    public void logEverySecond() {
        logger.info("Logging every second");
    }
}
