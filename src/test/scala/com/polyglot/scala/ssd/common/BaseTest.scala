package com.polyglot.scala.ssd.common

import com.polyglot.scala.ssd.config.TestApplicationConfig
import org.scalatest.{ShouldMatchers, WordSpecLike}
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.AnnotationConfigContextLoader
import org.springframework.test.context.transaction.TransactionConfiguration
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@ContextConfiguration(classes = Array(classOf[TestApplicationConfig]),
  loader = classOf[AnnotationConfigContextLoader])
@TransactionConfiguration
@RunWith(classOf[JUnitRunner])
class BaseTest extends WordSpecLike with ShouldMatchers {

    val logger: Logger = LoggerFactory.getLogger(this.getClass)

}
