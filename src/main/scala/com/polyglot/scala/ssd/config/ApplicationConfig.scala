package com.polyglot.scala.ssd.config

import org.springframework.context.annotation._
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer

@Configuration
@Import(Array(classOf[PersistenceConfig]))
class ApplicationConfig {

  @Bean def propertyPlaceHolderConfigurer: PropertySourcesPlaceholderConfigurer = {
    new PropertySourcesPlaceholderConfigurer
  }

}