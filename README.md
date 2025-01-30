# JFixture Scala
JFixture Scala contains a customisation to [JFixture](https://github.com/FlexTradeUKLtd/jfixture) that adds support for many of Scala's built in types

[![Build Status](https://github.com/FlexTradeUKLtd/jfixture-scala/actions/workflows/scalaTest.yml/badge.svg)](https://github.com/FlexTradeUKLtd/jfixture-scala/actions/workflows/scalaTest.yml)

## Usage

Currently, Scala Lists, Sets, Maps and primitives (Int, Byte etc) are supported.

Case classes already work with the base version of JFixture.

Available on Maven Central for:
* Scala 2.11: [![maven central](https://maven-badges.herokuapp.com/maven-central/com.flextrade.jfixture/jfixture-scala_2.11/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.flextrade.jfixture/jfixture-scala_2.11)
* Scala 2.12: [![maven central](https://maven-badges.herokuapp.com/maven-central/com.flextrade.jfixture/jfixture-scala_2.12/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.flextrade.jfixture/jfixture-scala_2.12)
* Scala 2.13: [![maven central](https://maven-badges.herokuapp.com/maven-central/com.flextrade.jfixture/jfixture-scala_2.13/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.flextrade.jfixture/jfixture-scala_2.13)

### SBT
```sbt
libraryDependencies += "com.flextrade.jfixture" %% "jfixture-scala" % "1.2.0"
```

### Maven
```xml
<dependency>
	<groupId>com.flextrade.jfixture</groupId>
	<artifactId>jfixture-scala_2.13</artifactId>
	<version>1.2.0</version>
</dependency>
```

### Example

Include the `JFixtureSugar` trait in your test class/test fixture. Then, fixtured values can be created as follows -

```scala
val integer = fixture[Int]
val listOfStrings = fixture[List[String]]
```

## Release Process

* Run `./release.sh x.y.z`
