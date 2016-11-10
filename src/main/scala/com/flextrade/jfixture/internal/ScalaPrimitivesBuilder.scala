package com.flextrade.jfixture.internal

import com.flextrade.jfixture.{NoSpecimen, SpecimenBuilder, SpecimenContext}

import scala.reflect.runtime.universe.{runtimeMirror, typeTag, Type => ScalaType}

class ScalaPrimitivesBuilder extends SpecimenBuilder {

  private val mirror = runtimeMirror(classOf[ScalaPrimitivesBuilder].getClassLoader)

  override def create(request: Any, context: SpecimenContext): AnyRef = {
    request match {
      case sym: ScalaType => context.resolve(typeToClass(sym))
      case _ => new NoSpecimen
    }
  }

  private def typeToClass(tpe: ScalaType): Class[_] = tpe match {
    case x if x == typeTag[Int].tpe => classOf[Int]
    case x if x == typeTag[Double].tpe => classOf[Double]
    case x if x == typeTag[Byte].tpe => classOf[Byte]
    case x if x == typeTag[Long].tpe => classOf[Long]
    case _ => mirror.runtimeClass(tpe.typeSymbol.asClass)
  }

}
