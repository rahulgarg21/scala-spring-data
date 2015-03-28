package com.polyglot.scala.ssd.persistence.entities

import javax.persistence.{Table, Entity}

import scala.beans.BeanProperty

@Entity
@Table(name="CUSTOMER")
case class Customer(@BeanProperty var firstName:String, @BeanProperty var lastName:String) extends BaseEntity {
  def this() = this(null,null)

}