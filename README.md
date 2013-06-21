# spray-sample #

An example api app that leverages [Spray](spray.io) and [akka](akka.io) 

Clone to your computer and run via sbt:

```
sbt run
```

The app simply exposes some basic endpoints.  A POST request to /entity with a Content-Type header of application/json will use Spray's json4s marhsalling support to create a JObject which gets passed to another Actor, with the HttpResponse returned to Spray via a future.
