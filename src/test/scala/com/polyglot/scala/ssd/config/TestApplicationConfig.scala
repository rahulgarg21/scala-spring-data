package com.polyglot.scala.ssd.config

import org.springframework.context.annotation.{ Bean, Import, PropertySource }
import org.springframework.jdbc.datasource.embedded.{ EmbeddedDatabaseBuilder, EmbeddedDatabaseType }
import javax.sql.DataSource

@Import(Array(classOf[ApplicationConfig]))
@PropertySource(Array("classpath:application.properties"))
class TestApplicationConfig {

  //overriding dataSoruce bean for testing
  @Bean def dataSource: DataSource = {
    new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).addScript("classpath:import.sql").build()
  }

}