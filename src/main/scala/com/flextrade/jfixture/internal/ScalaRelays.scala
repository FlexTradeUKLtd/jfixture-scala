package com.flextrade.jfixture.internal

object ScalaRelays {
  val ListClass = classOf[List[_]]
  val SetClass = classOf[Set[_]]
  val MapClass = classOf[Map[_,_]]

  lazy val collections = new ScalaCollectionRelay
  lazy val primitives = new ScalaPrimitivesBuilder
  lazy val reflection = new ClassTagCollectionRelay
}
