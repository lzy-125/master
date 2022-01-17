package com.easy.arch.config.Info;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
//当前这个类的属性需要与某个配置文件绑定
@PropertySource("classpath:mysql.properties")
public class JdbcInfo {
    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;


    public String getUrl() {
        return url;
    }

    public String getDriver() {
        return driver;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

//    @Value("${FILTERS}")
//    private String filters;


//    @Value("${CONNECTION_PROPERTIES}")
//    private String connectionProperties;


//    @Value("${mybatis.type.alias.package}")
//    private String typeAliasesPackage;
//
//    public String getTypeAliasesPackage() {
//        return typeAliasesPackage;
//    }
//
//    public void setTypeAliasesPackage(String typeAliasesPackage) {
//        this.typeAliasesPackage = typeAliasesPackage;
//    }


//    @Value("${mariaDB.validationQuery}")
//    private String validationQuery;
//
//    public String getValidationQuery() {
//        return validationQuery;
//    }


//    public String getFilters() {
//        return filters;
//    }

//    public String getConnectionProperties() {
//        return connectionProperties;
//    }



}
