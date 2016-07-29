val commonSettings = Seq(
  organization := "com.michaelpollmeier",
  licenses +=("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html")),
  homepage := Some(url("https://github.com/mpollmeier/gremlin-scala")),
  version := "3.0.1",
  scalaVersion := "2.10.6",
  crossScalaVersions := Seq("2.10.6", scalaVersion.value),
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),

  // if (scalaVersion.value.startsWith("2.10"))
  //   deps :+ compilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
  // else
  //   deps

  publishTo := {
    val sonatype = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at sonatype + "content/repositories/snapshots")
    else
      Some("releases" at sonatype + "service/local/staging/deploy/maven2")
  },
  publishMavenStyle := true,
  publishArtifact in Test := false,
  pomIncludeRepository := { _ => false },
  pomExtra :=
    <scm>
      <url>git@github.com:mpollmeier/gremlin-scala.git</url>
      <connection>scm:git:git@github.com:mpollmeier/gremlin-scala.git</connection>
    </scm>
      <developers>
        <developer>
          <id>mpollmeier</id>
          <name>Michael Pollmeier</name>
          <url>http://www.michaelpollmeier.com</url>
        </developer>
      </developers>
)

lazy val macros = project.in(file("macros"))
  .settings(commonSettings: _*)

lazy val gremlinScala = project.in(file("gremlin-scala"))
  .dependsOn(macros)
  .settings(commonSettings: _*)

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)
