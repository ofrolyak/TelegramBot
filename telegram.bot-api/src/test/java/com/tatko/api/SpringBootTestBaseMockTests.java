package com.tatko.api;

import com.tatko.api.dao.AdsDaoService;
import com.tatko.api.repositories.AdsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
//@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class,
//        JpaRepositoriesAutoConfiguration.class,
//        DataSourceAutoConfiguration.class,
//})
//@Sql(scripts = "classpath:/data/clean_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class SpringBootTestBaseMockTests extends BaseMockTests {

    @Autowired
    AdsRepository adsRepository;

    @BeforeEach
    protected void setUp() {

        adsRepository.deleteAll();

    }

}
