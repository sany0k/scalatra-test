import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object ScalatraBuild extends Build {
  val Organization = "com.example"
  val Name = "scalatra"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.10.0"
  val ScalatraVersion = "2.2.0"

  lazy val project = Project(
    "scalatra",
    file("."),
    settings = Defaults.defaultSettings ++ ScalatraPlugin.scalatraWithJRebel ++ scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      libraryDependencies ++= Seq(
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
        "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
        "ch.qos.logback" % "logback-classic" % "1.0.6" % "runtime",
        "org.neo4j" % "neo4j" % "1.9.RC1",
        "org.neo4j" % "neo4j-scala" % "0.2.0-M2-SNAPSHOT",
        "org.eclipse.jetty" % "jetty-webapp" % "8.1.8.v20121106" % "container",
        "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
      ),
      scalateTemplateConfig in Compile <<= (sourceDirectory in Compile) {
        base =>
          Seq(
            TemplateConfig(
              base / "webapp" / "WEB-INF" / "templates",
              Seq.empty,
              Seq(
                Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
              ),
              Some("templates")
            )
          )
      },
      resolvers ++= Seq(
        "Maven Central" at "http://repo1.maven.org/maven2/",
        "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
        "Sonatype releases" at "http://oss.sonatype.org/content/repositories/releases/",
        "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
        "Neo4J" at "http://m2.neo4j.org/",
        "fakod-releases" at "https://raw.github.com/FaKod/fakod-mvn-repo/master/releases",
        "fakod-snapshots" at "https://raw.github.com/FaKod/fakod-mvn-repo/master/snapshots"
      )
    )
  )
}
