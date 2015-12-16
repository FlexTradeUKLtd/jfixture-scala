package com.flextrade.jfixture

import java.lang.reflect.{ParameterizedType, Type}

import com.flextrade.jfixture.utility.SpecimenType

import scala.reflect.runtime.universe.{Type => ScalaType, _}
import scala.reflect.{ClassTag, classTag}

trait JFixtureSugar {
  private val relay = new ScalaCollectionRelay
  private val primitives = new ScalaPrimitivesBuilder
  val defaultFixture = new JFixture().addBuilderToStartOfPipeline(relay).addBuilderToStartOfPipeline(primitives)

  def fixture[T : ClassTag:TypeTag]: T = {
    val clsTag = classTag[T]
    val tpeTag = typeOf[T]
    val args = tpeTag.typeArgs

    val specimenType = clsTag.runtimeClass match {
      case c @ relay.ListClass => SpecimenType.of(SimpleCollectionType(relay.ListClass, typeToClass(args.head)))
      case c @ relay.SetClass => SpecimenType.of(SimpleCollectionType(relay.SetClass, typeToClass(args.head)))
      case c @ relay.MapClass => SpecimenType.of(MapCollectionType(relay.MapClass, typeToClass(args.head), typeToClass(args(1))))
      case c => SpecimenType.of(c)
    }
    defaultFixture.create(specimenType).asInstanceOf[T]
  }

  private def typeToClass(tpe: ScalaType) = Class.forName(tpe.typeSymbol.fullName)

  case class SimpleCollectionType[C](collectionType: Class[C], itemType: Type) extends ParameterizedType {
    override def getRawType: Type = collectionType
    override def getActualTypeArguments: Array[Type] = Array(itemType)
    override def getOwnerType: Type = null
  }

  case class MapCollectionType[C](collectionType: Class[C], keyType: Type, valueType: Type) extends ParameterizedType {
    override def getRawType: Type = collectionType
    override def getActualTypeArguments: Array[Type] = Array(keyType, valueType)
    override def getOwnerType: Type = null
  }
}
