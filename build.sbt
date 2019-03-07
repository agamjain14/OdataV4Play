import sbt.ModuleID

name := "OdataV4Play"

version := "0.1"
// "2.12.8"
scalaVersion := "2.12.4"
//0.9.3, 0.10.0
libraryDependencies ++= Seq (
  "com.typesafe" % "config" % "1.3.3",
  "org.apache.olingo" % "odata-client-core" % "4.5.0",
  "org.apache.olingo" % "odata-client-api" % "4.5.0",
  "io.circe" %% "circe-core" % "0.11.1",
  "io.circe" %% "circe-generic" % "0.11.1",
  "io.circe" %% "circe-parser" % "0.11.1",
  "io.circe" %% "circe-config" % "0.6.1",

  "org.apache.httpcomponents" % "httpclient" % "4.5.7"
  /*"com.typesafe.play" %% "play" % "2.4.6",
  "com.typesafe.play" %% "play-json" % "2.4.6",
  "com.typesafe.play" %% "play-ws" % "2.4.6",
  "com.typesafe.play" %% "play-cache" % "2.4.6"*/
)

