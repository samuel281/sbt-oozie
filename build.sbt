sbtPlugin := true

name := "sbt-oozie"

version := "0.1.1-cdh5.5.2"

scalaVersion := "2.10.5"

organization := "com.kakao.mimi"

lazy val oozieVersion = "4.1.0-cdh5.5.2"

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.11" % "test",
  "org.apache.oozie" % "oozie-client" % oozieVersion,
  "org.eclipse.jgit" % "org.eclipse.jgit.console" % "3.7.0.201502260915-r",
  "org.eclipse.jgit" % "org.eclipse.jgit" % "3.7.0.201502260915-r"
)

resolvers ++= Seq(
  "cloudera" at "https://repository.cloudera.com/artifactory/cloudera-repos/"
)
