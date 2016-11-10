package com.flextrade.jfixture.internal

import com.flextrade.jfixture.{NoSpecimen, SpecimenBuilder, SpecimenContext}

import scala.reflect.runtime.universe.{runtimeMirror, Type => ScalaType}

class ScalaPrimitivesBuilder extends SpecimenBuilder {

  private val mirror = runtimeMirror(classOf[ScalaPrimitivesBuilder].getClassLoader)

  override def create(request: Any, context: SpecimenContext): AnyRef = {
    request match {
      case tpe: ScalaType => context.resolve(mirror.runtimeClass(tpe.typeSymbol.asClass))
      case _ => new NoSpecimen
    }
  }

}
