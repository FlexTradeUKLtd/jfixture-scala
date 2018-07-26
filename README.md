# JFixture Scala
JFixture Scala contains a customisation to [JFixture](https://github.com/FlexTradeUKLtd/jfixture) that adds support for many of Scala's built in types

[![Build Status](https://travis-ci.org/FlexTradeUKLtd/jfixture-scala.svg?branch=master)](https://travis-ci.org/FlexTradeUKLtd/jfixture-scala)

Currently, Scala Lists, Sets, Maps and primitives (Int, Byte etc) are supported.

Case classes already work with the base version of JFixture.

# SBT
```xml
libraryDependencies += "com.flextrade.jfixture" % "jfixture-scala" % "0.0.3"
```

# Maven
```xml
<dependency>
	<groupId>com.flextrade.jfixture</groupId>
	<artifactId>jfixture-scala</artifactId>
	<version>0.0.3</version>
</dependency>
```

# Example

Include the `JFixtureSugar` trait in your test class/test fixture. Then, fixtured values can be created as follows -

```scala
val integer = fixture[Int]
val listOfStrings = fixture[List[String]]
```
