package com.example.replication.service;

import com.example.replication.entity.ReplTest;
import com.example.replication.repository.ReplTestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
public class ReplTestService {
    private final static String TEST = "TEST";
    private final ReplTestRepository replTestRepository;

    @Transactional
    public void postReplTest() {
        log.info(">>>>> i am post repl test method");
        replTestRepository.save(ReplTest.of(TEST));
    }

    @Transactional(readOnly = true)
    public void getReplTest(){
        log.info(">>>>> i am get repl test method");
        replTestRepository.findAll();
    }


}
