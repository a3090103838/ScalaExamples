name := "scala_examples"

version := "0.0.1"

organization := "com.umeng" 

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

resolvers ++= Seq(
  "Umeng Public Maven Repository" at "http://122.11.52.227:8088/nexus/content/groups/public",
  "clojars.org" at "http://clojars.org/repo"
)

libraryDependencies ++= {
  val finagleVersion = "6.5.2"
  Seq(
    "com.typesafe" % "config" % "1.0.0",
    "com.twitter" %% "finagle-core" % finagleVersion,
    "com.twitter" %% "finagle-http" % finagleVersion,
    "com.twitter" %% "finagle-mysql" % finagleVersion
  ) 
}
