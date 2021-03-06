# Coner Core

Coner Core is a REST API service for autocross event operations.

![Travis CI Build Status](https://travis-ci.org/caeos/coner-core.svg?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/caeos/coner-core/badge.svg?branch=master)](https://coveralls.io/github/caeos/coner-core?branch=master)

## Development Status

Coner Core resumed scaffolding in 2017 Q2. The remaining scaffolding work is split into two phases:

* [Phase 1](https://github.com/caeos/coner-core/milestone/2) aims to implement the bare minimum level of functionality in the core service such that UI development can begin
* [Phase 2](https://github.com/caeos/coner-core/milestone/3) aims to fill in the gaps left in phase 1 such that the UI can reach a bare minimum level of functionality

## Build, Test, and Run

These steps assume you've cloned this repo, and have installed Maven and JDK 8 already.

1. `mvn clean install`
2. `java -jar service/target/service-0.1-SNAPSHOT.jar server service/src/test/resources/config/test.yml`

The last few lines should resemble the following:

```
INFO  [2017-02-02 23:22:00,380] org.eclipse.jetty.server.AbstractConnector: Started application@683cd50d{HTTP/1.1,[http/1.1]}{0.0.0.0:8080}
INFO  [2017-02-02 23:22:00,380] org.eclipse.jetty.server.AbstractConnector: Started admin@280c955c{HTTP/1.1,[http/1.1]}{0.0.0.0:8081}
INFO  [2017-02-02 23:22:00,380] org.eclipse.jetty.server.Server: Started @3880ms
```

You may request a listing of Event entities like so:

```
$ curl http://localhost:8080/events
{"events":[]}
```

Check health status:

```
$ curl http://localhost:8081/healthcheck
{"deadlocks":{"healthy":true},"hibernate":{"healthy":true}}
```

Add an event:
```
$ curl -v -H "Content-Type: application/json" -d @service/src/test/resources/fixtures/api/entity/event_add.json http://localhost:8080/events
*   Trying 127.0.0.1...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> POST /events HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.47.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 60
> 
* upload completely sent off: 60 out of 60 bytes
< HTTP/1.1 201 Created
< Date: Thu, 02 Feb 2017 23:27:25 GMT
< Location: http://localhost:8080/events/74c28128-0548-43c3-9a4c-a8d909d4effe
< Content-Length: 0
< 
* Connection #0 to host localhost left intact
```

## API Spec

Start the service and access the [local Swagger UI](http://localhost:8080/swagger).

## Contributing

Interested in contributing to Coner? Please take a look at our [contributing guidlines](https://github.com/carltonwhitehead/coner/blob/master/CONTRIBUTING.md)
