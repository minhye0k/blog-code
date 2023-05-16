package com.self.invocation;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SelfInvocationService {
    public SelfInvocationService(SelfInvocationRepository selfInvocationRepository) {
        this.selfInvocationRepository = selfInvocationRepository;
    }

    private final SelfInvocationRepository selfInvocationRepository;

    @Autowired
    private  SelfInvocationService self;

    public void callApplyTransaction() {

//        applyTransaction();

//        ((SelfInvocationService) AopContext.currentProxy()).applyTransaction();
        self.applyTransaction();
    }

    @Transactional
    public void applyTransaction() {
        SelfInvocation selfInvocation = new SelfInvocation();
        selfInvocationRepository.save(selfInvocation);
    }

    public void callApplyCache() {
        applyCache();
    }

    @Cacheable(cacheNames = "example")
    public String applyCache() {
        String caching = "caching";
        System.out.println("--- processing ---");
        return caching;
    }
}
