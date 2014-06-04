package org.optigra.ads.integration.user

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import com.excilys.ebi.gatling.http.Headers._
import akka.util.duration._
import com.excilys.ebi.gatling.core.Predef.bootstrap._
import com.excilys.ebi.gatling.core.Predef.assertions._
import com.excilys.ebi.gatling.core.structure.ScenarioBuilder.configureScenario

class UserFlowScenario extends Simulation {

  val httpConf = httpConfig
    .baseURL("http://localhost:8080/optigra-ads-rest-api/v1")
    .acceptHeader("application/json")

  val userDetails = csv("user.csv").queue

  val scn = scenario("user Scenario")
	.feed(userDetails)
	.exec(
            http("Insert user")
		      	.post("/user")
			.basicAuth("admin", "admin")
			.body			("""{"login":"${login}","email":"${email}","fullName":"${fullName}","imageUrl":"${imageUrl}","password":"${password}","role":"USER"}""")
			.asJSON
			.check(status.is(200))
			.check(jsonPath("$.id").find.saveAs("userId")))
	.pause(500 milliseconds, 2 seconds)
    	.exec(
      		http("Get user")
        		.get("/user/${userId}")
			.basicAuth("admin", "admin")
        		.check(status.is(200))
			.check(jsonPath("$.login").find.is("${login}"))
			.check(jsonPath("$.email").find.is("${email}"))
			.check(jsonPath("$.fullName").find.is("${fullName}"))
			.check(jsonPath("$.imageUrl").find.is("${imageUrl}")))
    	.exec(
      		http("Update user")
        		.put("/user/${userId}")
			.basicAuth("admin", "admin")
			.body("""{"login":"${login}","email":"${email}","fullName":"${fullName1}","imageUrl":"${imageUrl}","password":"${password}","role":"USER"}""")
			.asJSON
        		.check(status.is(200)))
    	.exec(
      		http("Get updated user")
        		.get("/user/${userId}")
			.basicAuth("admin", "admin")
        		.check(status.is(200))
			.check(jsonPath("$.fullName").find.is("${fullName1}")))
    	.exec(
      		http("Delete user")
        		.delete("/user/${userId}")
			.basicAuth("admin", "admin")
        		.check(status.is(200)))
    	.exec(
      		http("Get defunct user")
        		.get("/user/${userId}")
			.basicAuth("admin", "admin")
        		.check(status.is(400)))

  setUp(
    scn.users(1).protocolConfig(httpConf)
  )
}
