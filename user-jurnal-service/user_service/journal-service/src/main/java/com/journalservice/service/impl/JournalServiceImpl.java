package com.journalservice.service.impl;

import com.journalservice.entity.Journal;
import com.journalservice.repository.JournalRepository;
import com.journalservice.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Override
    public List<Journal> getAllJournals() {
        return journalRepository.findAll();
    }
}
