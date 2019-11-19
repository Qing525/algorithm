package com.wld.config.audit;

import com.wld.modular.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


/***
 * 启用jpa审计配置类
 * @author 王增光
 * @date 2019/9/5
 */
@Configuration
@EnableJpaAuditing
public class AuditorConfig {

    @Bean
    public AuditorAware<Long> auditorProvider() {
        return new SpringSecurityAuditorAware();
    }

}
