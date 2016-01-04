package com.flextrade.jfixture.internal

import java.util

import com.flextrade.jfixture.requests.MultipleRequest
import com.flextrade.jfixture.{NoSpecimen, SpecimenBuilder, SpecimenContext}

import scala.collection.JavaConversions._
import scala.reflect.runtime.universe.{Type => ScalaType, _}

class ClassTagCollectionRelay extends SpecimenBuilder {
  val noSpecimen = new NoSpecimen

  val listSymbol = typeOf[List[_]].typeSymbol
  val setSymbol = typeOf[Set[_]].typeSymbol
  val mapSymbol = typeOf[Map[_,_]].typeSymbol

  override def create(request: scala.Any, context: SpecimenContext): AnyRef = request match {
    case tpeTag: ScalaType =>
      val args = tpeTag.typeArgs
      if (args.size < 1) return noSpecimen
      val collection = context.resolve(new MultipleRequest(args.head)).asInstanceOf[util.Collection[_]]

      tpeTag.typeSymbol match {
        case c @ `listSymbol` => collection.toList
        case c @ `setSymbol` => collection.toSet
        case c @ `mapSymbol` => collection.map{_ -> context.resolve(args.tail.head)}.toMap
        case _ => noSpecimen
      }

    case _ => noSpecimen
  }

}
