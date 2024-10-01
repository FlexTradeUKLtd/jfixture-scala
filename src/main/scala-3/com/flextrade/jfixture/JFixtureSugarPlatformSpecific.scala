package com.flextrade.jfixture

import io.github.gaeljw.typetrees.TypeTree

import scala.reflect.ClassTag

trait JFixtureSugarPlatformSpecific {
  val defaultFixture: JFixture

  final protected def typeOf[T: TypeTree]: Any =
    summon[TypeTree[T]].tag

  def fixture[T: ClassTag : TypeTree]: T =
    defaultFixture.create(typeOf[T]).asInstanceOf[T]

}
