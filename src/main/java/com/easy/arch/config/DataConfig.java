package com.easy.arch.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.easy.arch.config.Info.JdbcInfo;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
//@MapperScan("com.easy.archiecture.dao.mapper")
//@EnableTransactionManagement
public class DataConfig {

    @Resource
    private JdbcInfo jdbcInfo;

    @Bean
    public DataSource getDataSource(){

//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(jdbcInfo.getUrl());
//        dataSource.setUsername(jdbcInfo.getUsername());
//        dataSource.setPassword(jdbcInfo.getPassword());
//        dataSource.setDriverClassName(jdbcInfo.getDriver());
//        dataSource.setFilters(jdbcInfo.getFilters());
//        System.out.println(jdbcInfo.getUsername());
//        System.out.println(jdbcInfo.getDriver());
//        dataSource.setConnectionProperties(jdbcInfo.getConnectionProperties());



        HikariDataSource dataSource = new HikariDataSource();
        System.out.println(jdbcInfo.getUrl());
        dataSource.setJdbcUrl(jdbcInfo.getUrl());
        dataSource.setUsername(jdbcInfo.getUsername());
        dataSource.setPassword(jdbcInfo.getPassword());
        dataSource.setDriverClassName(jdbcInfo.getDriver());



        return dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

//    @Bean(name = "transactionManager")
//    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean(name = "txTemplate")
//    public TransactionTemplate transactionTemplate(DataSourceTransactionManager transactionManager) {
//        return new TransactionTemplate(transactionManager);
//    }
}
