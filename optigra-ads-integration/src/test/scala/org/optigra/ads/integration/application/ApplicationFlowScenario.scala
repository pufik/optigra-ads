package org.optigra.ads.integration.application

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import com.excilys.ebi.gatling.http.Headers._
import akka.util.duration._
import com.excilys.ebi.gatling.core.Predef.bootstrap._
import com.excilys.ebi.gatling.core.Predef.assertions._
import com.excilys.ebi.gatling.core.structure.ScenarioBuilder.configureScenario

class ApplicationFlowScenario extends Simulation {

  val httpConf = httpConfig
    .baseURL("http://localhost:8080/optigra-ads-rest-api/v1")
    .acceptHeader("application/json")

  val applicationDetails = csv("application.csv").queue

  val scn = scenario("Insert Application Scenario")
	.feed(applicationDetails)
	.exec(
                http("Insert Application")
		      	.post("/application")
			.basicAuth("admin", "admin")
			.body("""{"url":"${url}","name":"${name}","groupName":"${groupName}","groupId":"${groupId}","status":"${status}","imageUrl":"${imageUrl}"}""")
			.asJSON
			.check(status.is(200))
			.check(jsonPath("$.applicationId").find.saveAs("appId")))
	.pause(500 milliseconds, 2 seconds)
    	.exec(
      		http("Get application")
        		.get("/application/${appId}")
			.basicAuth("admin", "admin")
        		.check(status.is(200))
			.check(jsonPath("$.url").find.is("${url}"))
			.check(jsonPath("$.name").find.is("${name}"))
			.check(jsonPath("$.groupName").find.is("${groupName}"))
			.check(jsonPath("$.status").find.is("${status}"))
			.check(jsonPath("$.imageUrl").find.is("${imageUrl}")))
    	.exec(
      		http("Update application")
        		.put("/application/${appId}")
			.basicAuth("admin", "admin")
			.body("""{"url":"${url}","name":"${newName}","groupName":"${groupName}","groupId":"${groupId}","status":"${status}","imageUrl":"${imageUrl}"}""")
			.asJSON
        		.check(status.is(200)))
    	.exec(
      		http("Get updated application")
        		.get("/application/${appId}")
			.basicAuth("admin", "admin")
        		.check(status.is(200))
			.check(jsonPath("$.name").find.is("${newName}")))
    	.exec(
      		http("Delete application")
        		.delete("/application/${appId}")
			.basicAuth("admin", "admin")
        		.check(status.is(200)))
    	.exec(
      		http("Get defunct application")
        		.get("/application/${appId}")
			.basicAuth("admin", "admin")
        		.check(status.is(400)))

  setUp(
    scn.users(1).protocolConfig(httpConf)
  )
}
