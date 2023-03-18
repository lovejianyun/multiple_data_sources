package com.qijy.multiple_data_sources.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author qijy
 * @version 1.0
 * @description: TODO
 * @date 2023/3/18 11:42
 */
@Configuration
@EnableTransactionManagement //开启事务
@MapperScan(basePackages = "com.qijy.multiple_data_sources.mapper2",sqlSessionTemplateRef  = "twoSqlSessionTemplate")
public class MybatisConfigTwo {
    @Bean(name = "twoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource twoDataSource() {
        System.out.println("初始化新的数据源");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "twoSqlSessionFactory")
    public SqlSessionFactory oneSqlSessionFactory(@Qualifier("twoDataSource") DataSource dataSource) throws Exception {
        //SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        //配置自动填充时间
        bean.setDataSource(dataSource);
//        bean.setTypeEnumsPackage("com.cn.slt.exchange.enums");
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "twoMysqlTransactionManager")
    public DataSourceTransactionManager mysqlTransactionManager() {
        return new DataSourceTransactionManager(twoDataSource());
    }

    @Bean(name = "twoSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("twoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
