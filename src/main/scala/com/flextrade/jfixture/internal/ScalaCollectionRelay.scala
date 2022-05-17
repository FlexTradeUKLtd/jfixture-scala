package com.flextrade.jfixture.internal

import com.flextrade.jfixture.requests.MultipleRequest
import com.flextrade.jfixture.utility.SpecimenType
import com.flextrade.jfixture.{NoSpecimen, SpecimenBuilder, SpecimenContext}

import java.util

class ScalaCollectionRelay extends SpecimenBuilder {
  override def create(request: scala.Any, context: SpecimenContext): AnyRef = request match {
    case request: SpecimenType[_] if request.getGenericTypeArguments.getLength >= 1 =>
      val itemType = request.getGenericTypeArguments.get(0).getType
      val collection = context.resolve(new MultipleRequest(SpecimenType.of(itemType))).asInstanceOf[util.Collection[_]].toArray
      request.getRawType match {
        case ScalaRelays.ListClass => collection.toList
        case ScalaRelays.SetClass => collection.toSet
        case ScalaRelays.MapClass =>
          val valueType = request.getGenericTypeArguments.get(1).getType
          collection.map(_ -> context.resolve(valueType)).toMap
        case _ => new NoSpecimen
      }
    case _ => new NoSpecimen
  }
}
