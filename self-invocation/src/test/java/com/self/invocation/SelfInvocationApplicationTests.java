package com.self.invocation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SelfInvocationApplicationTests {
    @Autowired
    private SelfInvocationService selfInvocationService;

    @Test
    void transaction_self_invocation_test() {
        selfInvocationService.callApplyTransaction();
        selfInvocationService.applyTransaction();
    }

    @Test
    void cache_self_invocation_test() {
        selfInvocationService.applyCache();
        selfInvocationService.applyCache();
        selfInvocationService.callApplyCache();
    }
}
