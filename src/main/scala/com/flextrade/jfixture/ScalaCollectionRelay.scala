package com.flextrade.jfixture

import java.util

import com.flextrade.jfixture.requests.MultipleRequest
import com.flextrade.jfixture.utility.SpecimenType

import scala.collection.JavaConversions._

class ScalaCollectionRelay extends SpecimenBuilder {
  val ListClass = classOf[List[_]]
  val SetClass = classOf[Set[_]]
  val MapClass = classOf[Map[_,_]]

  override def create(request: scala.Any, context: SpecimenContext): AnyRef = request match {
    case request: SpecimenType[_] if request.getGenericTypeArguments.getLength >= 1 =>
      val itemType = request.getGenericTypeArguments.get(0).getType
      val collection = context.resolve(new MultipleRequest(SpecimenType.of(itemType))).asInstanceOf[util.Collection[_]]
      request.getRawType match {
        case ListClass => collection.toList
        case SetClass => collection.toSet
        case MapClass =>
          val valueType = request.getGenericTypeArguments.get(1).getType
          collection.map(_ -> context.resolve(valueType)).toMap
        case _ => new NoSpecimen
      }
    case _ => new NoSpecimen
  }
}
