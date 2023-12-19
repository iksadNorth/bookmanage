package com.iksadnorth.rmsoft.utils;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SqlGroup({
        @Sql(
                "classpath:save-testcases.sql"
        ),
        @Sql(
                scripts = "classpath:truncate-testcases.sql",
                executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
        )
})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoadTestCase {}

