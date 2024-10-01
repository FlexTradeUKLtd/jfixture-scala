package com.flextrade.jfixture

import scala.reflect.ClassTag
import scala.reflect.runtime.universe.TypeTag

trait JFixtureSugarPlatformSpecific {
  val defaultFixture: JFixture

  final protected def typeOf[T: ClassTag : TypeTag]: Any =
    scala.reflect.runtime.universe.typeOf[T]

  def fixture[T: ClassTag : TypeTag]: T =
    defaultFixture.create(typeOf[T]).asInstanceOf[T]
}
