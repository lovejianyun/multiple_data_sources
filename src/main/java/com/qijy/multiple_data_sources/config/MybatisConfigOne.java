package com.qijy.multiple_data_sources.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
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
 * @date 2023/3/18 11:41
 */
@Configuration
@EnableTransactionManagement //开启事务
@MapperScan(basePackages = "com.qijy.multiple_data_sources.mapper1",sqlSessionTemplateRef  = "oneSqlSessionTemplate")
public class MybatisConfigOne {

    @Bean(name = "oneDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource oneDataSource() {
        System.out.println("初始化新的数据源");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oneSqlSessionFactory")
    public SqlSessionFactory oneSqlSessionFactory(@Qualifier("oneDataSource") DataSource dataSource) throws Exception {
        //SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        //配置自动填充时间
        bean.setDataSource(dataSource);
//        bean.setTypeEnumsPackage("com.cn.slt.exchange.enums");
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));

        bean.setPlugins(mybatisPlusInterceptor());
        return bean.getObject();
    }

    @Bean(name = "oneMysqlTransactionManager")
    public DataSourceTransactionManager mysqlTransactionManager() {
        return new DataSourceTransactionManager(oneDataSource());
    }

    @Bean(name = "oneSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("oneSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
