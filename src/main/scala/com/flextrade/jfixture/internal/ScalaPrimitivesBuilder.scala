package com.flextrade.jfixture.internal

import com.flextrade.jfixture.utility.SpecimenType
import com.flextrade.jfixture.{NoSpecimen, SpecimenBuilder, SpecimenContext}

import scala.reflect.runtime.universe.{Type => ScalaType}

class ScalaPrimitivesBuilder extends SpecimenBuilder {
  override def create(request: Any, context: SpecimenContext): AnyRef = {
    request match {
      case request: SpecimenType[_] => request.toString match {
        case "scala.Int" => context.resolve(classOf[Integer])
        case "scala.Double" => context.resolve(classOf[Double])
        case "scala.Byte" => context.resolve(classOf[Byte])
        case "scala.Long" => context.resolve(classOf[Long])
        case _ => new NoSpecimen
      }

      case sym: ScalaType => context.resolve(typeToClass(sym))
      case _ => new NoSpecimen
    }
  }

  private def typeToClass(tpe: ScalaType) = Class.forName(tpe.typeSymbol.fullName)

}
