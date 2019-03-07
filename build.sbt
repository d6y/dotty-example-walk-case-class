val dottyVersion = "0.14.0-bin-20190306-9f5b3c3-NIGHTLY" // Until https://github.com/lampepfl/dotty/issues/5924 lands

lazy val root = project
  .in(file("."))
  .settings(
    name := "example-cc",
    version := "1.0.0",

    scalaVersion := dottyVersion,

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
  )


