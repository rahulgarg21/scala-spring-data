package com.polyglot.scala.ssd.persistence.repositories

import scala.collection.JavaConversions.iterableAsScalaIterable

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.TestContextManager

import com.polyglot.scala.ssd.common.BaseTest
import com.polyglot.scala.ssd.persistence.entities.Customer

class CustomerRepositoryTest extends BaseTest {

  @Autowired val customerRepository: CustomerRepository = null
  //required for DI
  new TestContextManager(this.getClass).prepareTestInstance(this)

  "Customer Repository" must {

    "be able to save given Customer" in {

      val customer1: Customer = Customer("Rajiv", "Singla")
      val customer2: Customer = Customer("Vishal", "Singla")

      val savedCustomer1: Customer = customerRepository.save(customer1)
      val savedCustomer2: Customer = customerRepository.save(customer2)

      savedCustomer1.id should not equal null
      savedCustomer2.id should not equal null

      val customers: Iterable[Customer] = customerRepository.findAll()
      customers.toList should contain(savedCustomer1)
      customers.toList should contain(savedCustomer2)

      customers.toList.foreach(customer => logger.debug(customer.toString()))
    }

  }

}
