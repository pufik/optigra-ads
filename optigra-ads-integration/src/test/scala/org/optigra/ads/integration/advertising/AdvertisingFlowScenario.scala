package org.optigra.ads.integration.advertising

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import com.excilys.ebi.gatling.http.Headers._
import akka.util.duration._
import com.excilys.ebi.gatling.core.Predef.bootstrap._
import com.excilys.ebi.gatling.core.Predef.assertions._
import com.excilys.ebi.gatling.core.structure.ScenarioBuilder.configureScenario

class AdvertisingFlowScenario extends Simulation {

  val httpConf = httpConfig
    .baseURL("http://localhost:8080/optigra-ads-rest-api/api")
    .acceptHeader("application/json")

  val advertisingDetails = csv("advertising.csv").queue

  val scn = scenario("Advertising Scenario")
	.feed(advertisingDetails)
	.exec(
            http("Insert Advertising")
		      	.post("/advertising")
			.basicAuth("admin", "admin")
			.body("""{"title":"${title}","description":"${description}","logoUrl":"${logoUrl}","imageUrl":"${imageUrl}","destinationUrl":"${destinationUrl}"}""")
			.asJSON
			.check(status.is(200))
			.check(jsonPath("$.uid").find.saveAs("advertisingId")))
	.pause(500 milliseconds, 2 seconds)
    	.exec(
      		http("Get advertising")
        		.get("/advertising/${advertisingId}")
			.basicAuth("admin", "admin")
        		.check(status.is(200))
			.check(jsonPath("$.title").find.is("${title}"))
			.check(jsonPath("$.description").find.is("${description}"))
			.check(jsonPath("$.logoUrl").find.is("${logoUrl}"))
			.check(jsonPath("$.imageUrl").find.is("${imageUrl}"))
			.check(jsonPath("$.destinationUrl").find.is("${destinationUrl}")))
    	.exec(
      		http("Update advertising")
        		.put("/advertising/${advertisingId}")
			.basicAuth("admin", "admin")
			.body("""{"title":"${title}","description":"${newDescription}","logoUrl":"${logoUrl}","imageUrl":"${imageUrl}","destinationUrl":"${destinationUrl}"}""")
			.asJSON
        		.check(status.is(200)))
    	.exec(
      		http("Get updated advertising")
        		.get("/advertising/${advertisingId}")
			.basicAuth("admin", "admin")
        		.check(status.is(200))
			.check(jsonPath("$.description").find.is("${newDescription}")))
    	.exec(
      		http("Delete advertising")
        		.delete("/advertising/${advertisingId}")
			.basicAuth("admin", "admin")
        		.check(status.is(200)))
    	.exec(
      		http("Get defunct advertising")
        		.get("/advertising/${advertisingId}")
			.basicAuth("admin", "admin")
        		.check(status.is(400)))

  setUp(
    scn.users(1).protocolConfig(httpConf)
  )
}
