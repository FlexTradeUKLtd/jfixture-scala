package com.flextrade.jfixture.internal

import java.util

import com.flextrade.jfixture.requests.MultipleRequest
import com.flextrade.jfixture.{NoSpecimen, SpecimenBuilder, SpecimenContext}

import scala.collection.JavaConversions._
import scala.reflect.runtime.universe.{Type => ScalaType, _}

class ClassTagCollectionRelay extends SpecimenBuilder {
  private val NoSpecimen = new NoSpecimen

  private val ListSymbol = typeOf[List[_]].typeSymbol
  private val SetSymbol = typeOf[Set[_]].typeSymbol
  private val MapSymbol = typeOf[Map[_,_]].typeSymbol

  override def create(request: scala.Any, context: SpecimenContext): AnyRef = request match {
    case tpeTag: ScalaType =>
      val args = tpeTag.typeArgs
      if (args.size < 1) return NoSpecimen
      val collection = context.resolve(new MultipleRequest(args.head)).asInstanceOf[util.Collection[_]]

      tpeTag.typeSymbol match {
        case ListSymbol => collection.toList
        case SetSymbol => collection.toSet
        case MapSymbol => collection.map{_ -> context.resolve(args.tail.head)}.toMap
        case _ => NoSpecimen
      }

    case _ => NoSpecimen
  }

}
