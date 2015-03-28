package com.polyglot.scala.ssd.persistence.repositories

import org.springframework.data.repository.CrudRepository
import com.polyglot.scala.ssd.persistence.entities.Customer
import java.lang.Long

trait CustomerRepository extends CrudRepository[Customer, Long] 