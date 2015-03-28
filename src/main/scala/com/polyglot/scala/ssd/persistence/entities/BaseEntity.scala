package com.polyglot.scala.ssd.persistence.entities

import java.time.ZonedDateTime
import javax.persistence._
import java.lang.Long

import org.hibernate.annotations.Type
import org.springframework.data.annotation.{LastModifiedDate, CreatedDate}

import scala.beans.BeanProperty

@SerialVersionUID(1)
trait BaseEntity {

  @BeanProperty @Id @GeneratedValue(strategy = GenerationType.AUTO)  var id:Long = _

  @BeanProperty
  @CreatedDate
  @Type(`type`="org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
  var creationTime:ZonedDateTime = _

  @BeanProperty
  @LastModifiedDate
  @Type(`type`="org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
  var lastModifiedDate:ZonedDateTime = _

  @BeanProperty @Version var version:Long = _

}
