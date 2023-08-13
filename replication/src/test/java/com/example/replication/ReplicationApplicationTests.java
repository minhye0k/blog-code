package com.example.replication;

import com.example.replication.service.ReplTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReplicationApplicationTests {
    @Autowired
    private ReplTestService replTestService;

    @Test
    void 쓰기_전용() {
        replTestService.postReplTest();
    }

    @Test
    void 읽기_전용() {
        replTestService.getReplTest();
    }
}
