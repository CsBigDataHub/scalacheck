import com.typesafe.tools.mima.plugin.MimaKeys.previousArtifact
import VersionKeys.scalaParserCombinatorsVersion

name := "scalacheck"

version := "1.11.6"

organization := "org.scalacheck"

licenses := Seq("BSD-style" -> url("http://www.opensource.org/licenses/bsd-license.php"))

homepage := Some(url("http://www.scalacheck.org"))

scalaVersion := "2.11.1"

scalaParserCombinatorsVersion := "1.0.4"

crossScalaVersions := Seq("2.9.3", "2.10.5", "2.11.6", "2.12.0-M1")

mimaDefaultSettings

previousArtifact := Some("org.scalacheck" % "scalacheck_2.11" % "1.11.5")

resolvers += "sonatype" at "https://oss.sonatype.org/content/repositories/releases"

libraryDependencies += "org.scala-sbt" %  "test-interface" % "1.0"

libraryDependencies ++= {
  scalaVersion.value match {
    case v if (v startsWith "2.9") || (v startsWith "2.10") => Seq.empty
    case _ => Seq("org.scala-lang.modules" %% "scala-parser-combinators" % scalaParserCombinatorsVersion.value)
  }
}

javacOptions += "-Xmx1024M"

scalacOptions += "-deprecation"

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  val (name, path) = if (isSnapshot.value) ("snapshots", "content/repositories/snapshots")
                     else ("releases", "service/local/staging/deploy/maven2")
  Some(name at nexus + path)
}

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := {
  <scm>
    <url>https://github.com/rickynils/scalacheck</url>
    <connection>scm:git:git@github.com:rickynils/scalacheck.git</connection>
  </scm>
  <developers>
    <developer>
      <id>rickynils</id>
      <name>Rickard Nilsson</name>
    </developer>
  </developers>
}
