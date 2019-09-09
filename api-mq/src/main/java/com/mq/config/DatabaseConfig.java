package com.mq.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan(value = "com.mq")
@EnableTransactionManagement
public class DatabaseConfig {

    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;
    @Value("${mybatis.type-aliases-package}")
    private String typeAliasesPackage;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        properties.setProperty("supportMethodsArguments", "true");
//        properties.setProperty("offsetAsPageNum", "true");
//        properties.setProperty("rowBoundsWithCount", "true");
//        properties.setProperty("reasonable", "true");
//        properties.setProperty("pageSizeZero", "true");
//        properties.setProperty("dialect", "mysql");
//        pageHelper.setProperties(properties);
//        sqlSessionFactoryBean.setPlugins(new Interceptor[]{ pageHelper});
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            Resource[] resources = resolver.getResources(mapperLocations);
            sqlSessionFactoryBean.setMapperLocations(resources);
            /**
             * 无法扫描spring boot 别名的bug，3.4.1之前存在的bug，详情请见
             * https://blog.csdn.net/rainbow702/article/details/63255736
             */
            sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
            sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
            return sqlSessionFactoryBean.getObject();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("获取mapper资源出现异常",e);
        } catch (Exception e){
            throw new RuntimeException("初始化sqlSessionFactory时出现异常",e);
        }
    }

}
