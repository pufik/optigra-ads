package org.optigra.ads.integration.common

import com.excilys.ebi.gatling.core.Predef.checkBuilderToCheck
import com.excilys.ebi.gatling.core.Predef.csv
import com.excilys.ebi.gatling.core.Predef.data2FeederBuiltIns
import com.excilys.ebi.gatling.core.Predef.extractorCheckBuilderToMatcherCheckBuilder
import com.excilys.ebi.gatling.core.Predef.scenario
import com.excilys.ebi.gatling.core.Predef.stringToEvaluatableString
import com.excilys.ebi.gatling.core.Predef.toSessionFunction
import com.excilys.ebi.gatling.core.scenario.configuration.Simulation
import com.excilys.ebi.gatling.core.structure.ScenarioBuilder.configureScenario
import com.excilys.ebi.gatling.http.Predef.http
import com.excilys.ebi.gatling.http.Predef.httpConfig
import com.excilys.ebi.gatling.http.Predef.httpProtocolConfigurationBuilder2HttpProtocolConfiguration
import com.excilys.ebi.gatling.http.Predef.jsonPath
import com.excilys.ebi.gatling.http.Predef.requestBuilder2ActionBuilder
import com.excilys.ebi.gatling.http.Predef.status

import akka.util.duration.intToDurationInt

class ErrorScenario extends Simulation{
val httpConf = httpConfig
    .baseURL("http://localhost:8080/optigra-ads-rest-api")
    .acceptHeader("application/json")

  val scn = scenario("Manage Device Scenario")
		.exec(
            http("Authorization Error")
	      	.get("/v1/application")
	      	.asJSON
	      	.check(status.is(401))
	      	.check(jsonPath("$.type").find.is("ERROR"))
	      	.check(jsonPath("$.status").find.is("401"))
	      	.check(jsonPath("$.message").find.is("Full authentication is required to access this resource")))
		.exec(
            http("Not found Error")
	      	.post("/noresource")
	      	.asJSON
	      	.check(status.is(404))
	      	.check(jsonPath("$.type").find.is("ERROR"))
	      	.check(jsonPath("$.status").find.is("404"))
	      	.check(jsonPath("$.message").find.is("/optigra-ads-rest-api/noresource")))

  setUp(
    scn.users(1).protocolConfig(httpConf)
  )
}