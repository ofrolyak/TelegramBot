package com.tatko.api;

import com.tatko.api.repositories.AdsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
//@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class,
//        JpaRepositoriesAutoConfiguration.class,
//        DataSourceAutoConfiguration.class,
//})
//@Sql(scripts = "classpath:/data/clean_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class SpringBootTestBaseMockTests extends BaseMockTests {

    @MockitoBean
    private JwtDecoder jwtDecoder;
    @Autowired
    AdsRepository adsRepository;

    @BeforeEach
    protected void setUp() {

        adsRepository.deleteAll();

    }

}
