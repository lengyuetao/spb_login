package com.example.spblogin.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

public class SwitchDataSource extends AbstractRoutingDataSource {

    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {

        return null;
    }
}
