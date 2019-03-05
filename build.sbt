name := "OdataV4Play"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq (
  "org.apache.olingo" % "odata-client-core" % "4.5.0",
  "org.apache.olingo" % "odata-client-api" % "4.5.0",
  "io.circe" %% "circe-core" % "0.10.0",
  "io.circe" %% "circe-generic" % "0.10.0",
  "io.circe" %% "circe-parser" % "0.10.0",
  "com.typesafe" % "config" % "1.3.3",
  "org.apache.httpcomponents" % "httpclient" % "4.5.7"

)