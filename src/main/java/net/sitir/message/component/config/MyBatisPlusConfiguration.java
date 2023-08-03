package net.sitir.message.component.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * mybatis-plus全局配置
 * @author linweikang
 * @since 2023/8/3
 */
@Configuration
public class MyBatisPlusConfiguration {

    /**
     * 使用oracle自增序列
     * @return
     */
    @Bean
    public IKeyGenerator keyGenerator() {
        return new OracleKeyGenerator();
    }

    /**
     * 分页插件
     * @return
     */
    @Bean
    public MybatisPlusInterceptor paginationInnerInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.ORACLE));
        return interceptor;
    }

}
