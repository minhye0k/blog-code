package com.example.replication.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import static com.example.replication.constant.AppConstant.PRIMARY;
import static com.example.replication.constant.AppConstant.SECONDARY;
import static org.springframework.transaction.support.TransactionSynchronizationManager.isCurrentTransactionReadOnly;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return isCurrentTransactionReadOnly() ? SECONDARY : PRIMARY;
    }
}
