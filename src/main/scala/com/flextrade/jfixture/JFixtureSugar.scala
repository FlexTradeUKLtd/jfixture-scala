package com.flextrade.jfixture

import com.flextrade.jfixture.internal.ScalaRelays

import scala.reflect.ClassTag
import scala.reflect.runtime.universe._

trait JFixtureSugar {
  val defaultFixture = new JFixture()
    .addBuilderToStartOfPipeline(ScalaRelays.collections)
    .addBuilderToStartOfPipeline(ScalaRelays.primitives)
    .addBuilderToStartOfPipeline(ScalaRelays.reflection)

  def fixture[T : ClassTag:TypeTag]: T = {
    defaultFixture.create(typeOf[T]).asInstanceOf[T]
  }
}
