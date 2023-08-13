package com.example.replication.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import static com.example.replication.constant.AppConstant.PRIMARY;
import static com.example.replication.constant.AppConstant.SECONDARY;
import static org.springframework.transaction.support.TransactionSynchronizationManager.isCurrentTransactionReadOnly;

@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName = isCurrentTransactionReadOnly() ? SECONDARY : PRIMARY;
        log.info(">>>>>> current data source : {}", dataSourceName);
        return dataSourceName;
    }
}
