package com.flextrade.jfixture

import com.flextrade.jfixture.utility.SpecimenType
import org.scalatest.{MustMatchers, WordSpec}

import scala.reflect.runtime.universe.typeOf

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

      val map = fixture[Map[String, MyType]]
      map foreach { entry =>
        entry._1 mustBe a [String]
        entry._2 mustBe a [MyType]
      }
    }

    "create collections recursively" in {
      val listOfSets = fixture[List[Set[Int]]]
      listOfSets.size mustBe 3
      listOfSets foreach { item =>
        item.size mustBe 3
        item foreach { _ mustBe an [Integer] }
      }

      val setOfMaps = fixture[Set[Map[Int,String]]]
      setOfMaps.size mustBe 3
      setOfMaps foreach { item =>
        item.size mustBe 3
        item foreach { entry =>
          entry._1 mustBe an [Integer]
          entry._2 mustBe a [String]
        }
      }

      val mapOfLists = fixture[Map[String,List[MyType]]]
      mapOfLists.size mustBe 3
      mapOfLists foreach { entry =>
        entry._1 mustBe a [String]
        entry._2.size mustBe 3
        entry._2 foreach { _ mustBe a [MyType] }
      }
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
      defaultFixture.customise().repeatCount(3)
    }

    "create collections of custom types" in {
      defaultFixture.create[List[MyType]](new SpecimenType[List[MyType]]() {}) foreach { _ mustBe a [MyType] }
      defaultFixture.create[Set[MyType]](new SpecimenType[Set[MyType]]() {}) foreach { _ mustBe a [MyType] }
      defaultFixture.create[Map[MyType, MyType]](new SpecimenType[Map[MyType, MyType]]() {}) foreach { t => {
        t._1 mustBe a [MyType]
        t._2 mustBe a [MyType]
      } }
    }

    "create collections of collections" in {
      val listOfSets = defaultFixture.create(typeOf[List[Set[Int]]]).asInstanceOf[List[Set[Int]]]

      listOfSets foreach { item =>
        item mustBe a [Set[_]]
        item.size mustBe 3
        item foreach { _ mustBe an [Integer]}
      }
    }

    "create Byte values" in {
      fixture[Byte] mustBe a [java.lang.Byte]
    }
    
    "create Short values" in {
      fixture[Short] mustBe a [java.lang.Short]
    }
    
    "create Int values" in {
      fixture[Int] mustBe a [java.lang.Integer]
    }
    
    "create Long values" in {
      fixture[Long] mustBe a [java.lang.Long]
    }
    
    "create Float values" in {
      fixture[Float] mustBe a [java.lang.Float]
    }
    
    "create Double values" in {
      fixture[Double] mustBe a [java.lang.Double]
    }
    
    "create Boolean values" in {
      fixture[Boolean] mustBe a [java.lang.Boolean]
    }
    
    "create Char values" in {
      fixture[Char] mustBe a [java.lang.Character]
    }

  }
}

class MyType {}