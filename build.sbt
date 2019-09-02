lazy val scala212 = "2.12.9"
lazy val scala211 = "2.11.7"
lazy val supportedScalaVersions = List(scala212, scala211)

ThisBuild / organization := "com.flextrade.jfixture"
ThisBuild / name         := "jfixture-scala"
ThisBuild / version      := "1.0.1-SNAPSHOT"

ThisBuild / organizationName := "FlexTrade"
ThisBuild / organizationHomepage := Some(url("http://www.flextrade.com/"))
ThisBuild / description  := "JFixture-Scala adds support for scala types and collections to JFixture"
ThisBuild / homepage     := Some(url("https://github.com/FlexTradeUKLtd/jfixture-scala"))

ThisBuild / startYear    := Some(2008)
ThisBuild / licenses     += "MIT License" -> url("http://www.opensource.org/licenses/mit-license.php")

ThisBuild / developers := List(
  Developer(
    id = "albx79", name = "Alberto Colombo", email = "albx79@gmail.com", url = url("https://github.com/albx79")
  )
)

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("http://github.com/FlexTradeUKLtd/jfixture-scala.git"), "scm:git@github.com:FlexTradeUKLtd/jfixture-scala.git"
  )
)

ThisBuild / scalaVersion := scala211
ThisBuild / crossScalaVersions := supportedScalaVersions

ThisBuild / libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "com.flextrade.jfixture" % "jfixture" % "2.7.2" % "provided",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test"
)

ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
ThisBuild / publishMavenStyle := true

(sys.env.get("CI_DEPLOY_USERNAME"), sys.env.get("CI_DEPLOY_PASSWORD")) match {
  case (Some(username), Some(password)) =>
    credentials += Credentials("sonatype-nexus-snapshots", "oss.sonatype.org", username, password)
  case _ =>
    println("CI_DEPLOY_USERNAME and/or CI_DEPLOY_PASSWORD is missing")
    credentials ++= Seq()
}