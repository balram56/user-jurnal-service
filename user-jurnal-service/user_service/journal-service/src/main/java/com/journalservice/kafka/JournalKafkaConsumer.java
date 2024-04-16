package com.journalservice.kafka;

import com.journalservice.entity.Journal;
import com.journalservice.repository.JournalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class JournalKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(JournalKafkaConsumer.class);

    @Autowired
    private JournalRepository journalRepository;

    @KafkaListener(topics = "user-events", groupId = "journal-service")
    public void consume(String message) {
        logger.info(String.format("Consumed message: %s", message));
        // Save the message to the database as a journal
        Journal journal = new Journal();
        journal.setMessage(message);
        journalRepository.save(journal);
    }
}

