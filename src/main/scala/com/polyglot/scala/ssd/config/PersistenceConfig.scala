package com.polyglot.scala.ssd.config

import java.util.Properties
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{ Bean, ComponentScan, Configuration, PropertySource }
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.orm.jpa.{ JpaTransactionManager, LocalContainerEntityManagerFactoryBean }
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@ComponentScan(Array("com.polyglot.scala.ssd.persistence"))
@EnableJpaRepositories(basePackages = Array("com.polyglot.scala.ssd.persistence.repositories"))
@PropertySource(Array("classpath:application.properties"))
class PersistenceConfig {

  @Value("${db.driver}") private val dbDriver: String = null
  @Value("${db.url}") private val dbUrl: String = null
  @Value("${db.username}") private val dbUsername: String = null
  @Value("${db.password}") private val dbPassword: String = null

  @Value("${hibernate.dialect}") private val hibernateDialect: String = null
  @Value("${hibernate.hbm2ddl.auto}") private val hibernateHbm2dll: String = null
  @Value("${hibernate.ejb.naming_strategy}") private val hibernateEjbNamingStrategy: String = null
  @Value("${hibernate.show_sql}") private val hibernateShowSql: String = null
  @Value("${hibernate.format_sql}") private val hibernateFormatSql: String = null

  @Bean def entityManagerFactory: LocalContainerEntityManagerFactoryBean = {
    val entityManagerFactoryBean: LocalContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean
    entityManagerFactoryBean.setDataSource(dataSource)
    entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter)
    entityManagerFactoryBean.setPackagesToScan("com.polyglot.scala.ssd.persistence.entities")
    entityManagerFactoryBean.setJpaProperties(additionalJpaProperties)
    entityManagerFactoryBean
  }

  private def additionalJpaProperties: Properties = {
    val jpaProperties: Properties = new Properties
    jpaProperties.put("hibernate.dialect", hibernateDialect)
    jpaProperties.put("hibernate.hbm2ddl.auto", hibernateHbm2dll)
    jpaProperties.put("hibernate.ejb.naming_strategy", hibernateEjbNamingStrategy)
    jpaProperties.put("hibernate.show_sql", hibernateShowSql)
    jpaProperties.put("hibernate.format_sql", hibernateShowSql)
    jpaProperties
  }

  @Bean def dataSource: DataSource = {
    assert(dbDriver != null && dbUrl != null &&
      dbUsername != null && dbPassword != null, "Database properties cannot be null")
    val driverManagerDataSource: DriverManagerDataSource = new DriverManagerDataSource
    driverManagerDataSource.setDriverClassName(dbDriver)
    driverManagerDataSource.setUrl(dbUrl)
    driverManagerDataSource.setUsername(dbUsername)
    driverManagerDataSource.setPassword(dbPassword)
    driverManagerDataSource
  }

  @Bean def transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager = {
    val jpaTransactionManager: JpaTransactionManager = new JpaTransactionManager
    jpaTransactionManager.setEntityManagerFactory(entityManagerFactory)
    jpaTransactionManager
  }

  @Bean def exceptionTranslation: PersistenceExceptionTranslationPostProcessor = {
    new PersistenceExceptionTranslationPostProcessor
  }

}