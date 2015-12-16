package com.flextrade.jfixture

import com.flextrade.jfixture.utility.SpecimenType

class ScalaPrimitivesBuilder extends SpecimenBuilder {
  override def create(request: scala.Any, context: SpecimenContext): AnyRef = {
//    println("REQUEST "+request)
    request match {
      case request: SpecimenType[_] if request.toString == "scala.Int" => context.resolve(classOf[Integer])
      case _ => new NoSpecimen
    }
  }
}
