name := """classroomPages"""
organization := "org.crystaltreeconcept"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.5" % Test
libraryDependencies += "org.mockito" % "mockito-core" % "2.22.0" % Test
libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "2.35.0" % "test"


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
