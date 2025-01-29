package com.flextrade.jfixture.internal

import com.flextrade.jfixture.requests.MultipleRequest
import com.flextrade.jfixture.{NoSpecimen, SpecimenBuilder, SpecimenContext}
import io.github.gaeljw.typetrees.TypeTreeTag

import java.util

class ClassTagCollectionRelay extends SpecimenBuilder {
  private val NoSpecimen = new NoSpecimen

  def create(request: Any, context: SpecimenContext): AnyRef =
    request match {
      case tpe: TypeTreeTag =>
        val args = tpe.args
        if (args.isEmpty) return NoSpecimen
        val collection = context.resolve(new MultipleRequest(args.head)).asInstanceOf[util.Collection[_]].toArray

        tpe.self.runtimeClass match {
          case c if c == classOf[List[_]] => collection.toList
          case c if c == classOf[Set[_]] => collection.toSet
          case c if c == classOf[Map[_, _]] => collection.map {
            _ -> context.resolve(args.tail.head)
          }.toMap
          case _ => NoSpecimen
        }

      case _ => NoSpecimen
    }

}
