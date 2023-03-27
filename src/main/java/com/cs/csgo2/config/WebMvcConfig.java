package com.cs.csgo2.config;

import com.cs.csgo2.common.JacksonObjectMapper;
import com.cs.csgo2.common.JwtAuthenticationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Bean
    public JwtAuthenticationInterceptor myInterceptor(){
        return new JwtAuthenticationInterceptor();
    }
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        converters.add(0,messageConverter);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建自定义的拦截器
        //添加拦截器
        registry.addInterceptor(myInterceptor())
                //添加需要拦截的路径
                .addPathPatterns("/**")
                .excludePathPatterns("/users/login","/users/register");
    }
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        String imgPath = System.getProperty("user.dir") + "/src/main/resources/images/";
        registry.addResourceHandler("/images/avatar/**").
                addResourceLocations("file:" + imgPath + "avatar/");
    }

}
