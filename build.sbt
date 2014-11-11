name := "spray-sample"

organization := "com.mlh"

version := "0.1.0-SNAPSHOT"

homepage := Some(url("https://github.com/mhamrah/spray-sample"))

startYear := Some(2013)

scmInfo := Some(
  ScmInfo(
    url("https://github.com/mhamrah/spray-sample"),
    "scm:git:https://github.com/mhamrah/spray-sample.git",
    Some("scm:git:git@github.com:mhamrah/spray-sample.git")
  )
)

/* scala versions and options */
scalaVersion := "2.11.4"

// These options will be used for *all* versions.
scalacOptions ++= Seq(
  "-deprecation"
  ,"-unchecked"
  ,"-encoding", "UTF-8"
  ,"-target:jvm-1.7"
  // "-optimise"   // this option will slow your build
)

scalacOptions ++= Seq(
  "-Yclosure-elim",
  "-Yinline"
)

javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation")

val akkaVersion = "2.3.6"
val sprayVersion = "1.3.1"

/* dependencies */
libraryDependencies ++= Seq (
  "com.github.nscala-time" %% "nscala-time" % "1.4.0"
  // -- testing --
  , "org.scalatest" %% "scalatest" % "2.2.2" % "test"
  , "org.scalamock" %% "scalamock-scalatest-support" % "3.1.4" % "test"
  // -- Logging --
  ,"ch.qos.logback" % "logback-classic" % "1.1.2"
  // -- Akka --
  ,"com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test"
  ,"com.typesafe.akka" %% "akka-actor" % akkaVersion
  ,"com.typesafe.akka" %% "akka-slf4j" % akkaVersion
  // -- Spray --
  ,"io.spray" %% "spray-routing" % sprayVersion
  ,"io.spray" %% "spray-can" % sprayVersion
  ,"io.spray" %% "spray-httpx" % sprayVersion
  ,"io.spray" %% "spray-testkit" % sprayVersion % "test"
  // -- Json --
  ,"org.json4s" %% "json4s-native" % "3.2.11"
  ,"com.typesafe.play" %% "play-json" % "2.4.0-M1"
)
