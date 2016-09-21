package com.jzeen.travel.admin.setting;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@EnableTransactionManagement
public class SpringbootTrans implements TransactionManagementConfigurer{
	   @Resource(name="txManager")
	    private PlatformTransactionManager txManager;
	 
/*	// 创建事务管理器1
	    @Bean(name = "txManager1")
	    public PlatformTransactionManager txManager(DataSource dataSource) {
	        return new DataSourceTransactionManager(dataSource);
	    }*/
	 // 创建事务管理器2
	 @Bean(name = "txManager")
	    public PlatformTransactionManager txManager2(EntityManagerFactory factory) {
	        return new JpaTransactionManager(factory);
	    }
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		// TODO Auto-generated method stub
		return txManager;
	}

}
