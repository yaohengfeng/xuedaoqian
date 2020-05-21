package com.yhf.xuedaoqian.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/5/22 1:47
 */
@Component
public class ApplicationContextFactory implements ApplicationContextAware {
    private static ApplicationContext context = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (context == null) {
            context = applicationContext;
        }
    }
    /**
     * 根据指定的注入类的类型获取该类被Spring管理的实例
     *
     * @param type
     * @return bean实例
     */
    public static <T> T getBean(Class<T> type) {
        if (context == null) {
            return null;
        }
        return context.getBean(type);
    }

    /**
     * 根据指定的注入类的Id，获取该类被Spring管理的实例
     *
     * @param beanId
     * @param requiredType
     * @return bean实例
     */
    public static <T> T getBean(String beanId, Class<T> requiredType) {
        if (context == null) {
            return null;
        }
        return context.getBean(beanId, requiredType);
    }

    /**
     * 根据指定的注入类的Id，获取该类被Spring管理的实例，未添加泛型
     *
     * @param beanId
     * @return 获取该类被Spring管理的实例
     */
    public static Object getBean(String beanId) {
        if (context == null) {
            return null;
        }
        return context.getBean(beanId);
    }
}
