package com.flextrade.jfixture

import java.lang.reflect.{Type, ParameterizedType}

import com.flextrade.jfixture.utility.SpecimenType
import org.scalatest.{MustMatchers, WordSpec}

class MySpec extends WordSpec with MustMatchers with JFixtureSugar {
  "The JFixtureSugar" should {

    "be very ergonomic to use" in {
      val i = fixture[Int]
      val s = fixture[String]
      assert(i > 0)
      s mustBe a [String]
    }

    "create collections ergonomically" in {
      val list = fixture[List[Int]]
      list foreach { i => assert(i > 0) }

      val set = fixture[Set[String]]
      set foreach { _ mustBe a [String]}
    }

    "create random integers" in {
      val int1 = fixture[Int]
      val int2 = fixture[Int]

      int1 must not be int2
      (int1 + int2 - int1) mustEqual int2
    }

    "create random collections of ints" in {
      val list1 = defaultFixture.create[List[Int]](new SpecimenType[List[Int]]{})
      val list2 = defaultFixture.create[List[Int]](new SpecimenType[List[Int]]{})

      list1 must not be list2
    }

    "create random sets of ints" in {
      val set1 = defaultFixture.create[Set[Int]](new SpecimenType[Set[Int]]() {})
      val set2 = defaultFixture.create[Set[Int]](new SpecimenType[Set[Int]]() {})

      set1 must not be set2
    }

    type MapType = Map[Int, String]

    "create random maps of things" in {
      val map1 = defaultFixture.create[MapType](new SpecimenType[MapType]() {})
      val map2 = defaultFixture.create[MapType](new SpecimenType[MapType]() {})

      map1 must not be map2
    }

    "create collections with the specified size" in {
      val count = 10
      defaultFixture.customise().repeatCount(count)
      val map = defaultFixture.create[MapType](new SpecimenType[MapType]() {})
      val set = defaultFixture.create[Set[Int]](new SpecimenType[Set[Int]]() {})
      val list = defaultFixture.create[List[Int]](new SpecimenType[List[Int]]{})

      List(map, set, list) foreach { _.size mustEqual count }
    }

    "create collections of custom types" in {
      defaultFixture.create[List[MyType]](new SpecimenType[List[MyType]]() {}) foreach { _ mustBe a [MyType] }
      defaultFixture.create[Set[MyType]](new SpecimenType[Set[MyType]]() {}) foreach { _ mustBe a [MyType] }
      defaultFixture.create[Map[MyType, MyType]](new SpecimenType[Map[MyType, MyType]]() {}) foreach { _ mustBe a [(MyType, MyType)] }
    }

    "create collections of collections" in {
      val listOfSets = defaultFixture.create[List[Set[Int]]](SpecimenType.of(new SimpleCollectionType(classOf[List[_]], new SimpleCollectionType(classOf[Set[_]], classOf[Int])))
)
      listOfSets foreach { item =>
        item mustBe a [Set[_]]
        item foreach { _ mustBe an [Integer]}
      }
    }
  }
}

class MyType {}