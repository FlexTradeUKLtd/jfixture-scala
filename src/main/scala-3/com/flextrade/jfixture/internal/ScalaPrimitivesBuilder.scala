package com.flextrade.jfixture.internal

import com.flextrade.jfixture.{NoSpecimen, SpecimenBuilder, SpecimenContext}
import io.github.gaeljw.typetrees.TypeTreeTag

class ScalaPrimitivesBuilder extends SpecimenBuilder {
  private val NoSpecimen = new NoSpecimen

  def create(request: Any, context: SpecimenContext): AnyRef = {
    request match {
      case tpe: TypeTreeTag => context.resolve(tpe.self.runtimeClass)
      case _ => NoSpecimen
    }
  }
}
