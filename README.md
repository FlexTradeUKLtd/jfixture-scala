# JFixture Scala
JFixture Scala contains a customisation to [JFixture](https://github.com/FlexTradeUKLtd/jfixture) that adds support for many of Scala's built in types

[![Build Status](https://travis-ci.org/FlexTradeUKLtd/jfixture-scala.svg?branch=master)](https://travis-ci.org/FlexTradeUKLtd/jfixture-scala)

Currently, Scala Lists, Sets, Maps and primitives (Int, Byte etc) are supported.

Case classes already work with the base version of JFixture.

# SBT
```sbt
libraryDependencies += "com.flextrade.jfixture" %% "jfixture-scala" % "1.1.0"
```

# Maven
```xml
<dependency>
	<groupId>com.flextrade.jfixture</groupId>
	<artifactId>jfixture-scala_2.11</artifactId>
	<version>1.1.0</version>
</dependency>
```

# Example

Include the `JFixtureSugar` trait in your test class/test fixture. Then, fixtured values can be created as follows -

```scala
val integer = fixture[Int]
val listOfStrings = fixture[List[String]]
```
