package org.optigra.ads.integration.application

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import com.excilys.ebi.gatling.http.Headers._
import akka.util.duration._
import com.excilys.ebi.gatling.core.Predef.bootstrap._
import com.excilys.ebi.gatling.core.Predef.assertions._
import com.excilys.ebi.gatling.core.structure.ScenarioBuilder.configureScenario

class DeviceApplicationFlowScenario extends Simulation {

  val httpConf = httpConfig
    .baseURL("http://localhost:8080/optigra-ads-rest-api/v1")
    .acceptHeader("application/json")

  val deviceDetails = csv("device-application.csv").queue

  val scn = scenario("Device setup scenario")
	.feed(deviceDetails)
			.exec(
                http("Insert Application")
		      	.post("/application")
		      	.basicAuth("admin", "admin")
		      	.body("""{"url":"${applicationUrl}","name":"${applicationName}","groupName":"${groupName}","groupId":"${groupId}","status":"${applicationStatus}","imageUrl":"${applicationImageUrl}"}""")
		      	.asJSON
		      	.check(status.is(200))
		      	.check(jsonPath("$.applicationId").find.saveAs("appId")))
			.exec(
                http("Get device for application first login")
		      	.get("/device/${deviceUid}/application/${appId}")
		      	.check(status.is(400)))
		    .exec(
		    	http("Get device")
        		.get("/device/${deviceUid}")
        		.check(status.is(400)))
			.exec(
                http("Insert Device")
		      	.post("/device")
		      	.body("""{"deviceToken":"${deviceToken}","deviceUid":"${deviceUid}"}""")
		      	.asJSON
		      	.check(status.is(200))
		      	.check(jsonPath("$.deviceUid").find.is("${deviceUid}"))
		      	.check(jsonPath("$.deviceToken").find.is("${deviceToken}")))
		    .exec(
                http("Attach application to device")
		      	.post("/device/${deviceUid}/application/${appId}")
		      	.body("")
		      	.asJSON
		      	.check(status.is(200))
		      	.check(jsonPath("$.type").find.is("INFO"))
		      	.check(jsonPath("$.message").find.is("Application was attached to device")))
	.pause(500 milliseconds, 2 seconds)
    	   .exec(
                http("Get device for application")
		      	.get("/device/${deviceUid}/application/${appId}")
		      	.check(status.is(200))
		      	.check(jsonPath("$.deviceUid").find.is("${deviceUid}"))
		      	.check(jsonPath("$.deviceToken").find.is("${deviceToken}")))
		  //Clear test data
		  .exec(
				http("Delete device")
        		.delete("/device/${deviceUid}")
        		.check(status.is(200)))
          .exec(
      		http("Get defunct device")
        		.get("/device/${deviceUid}")
        		.check(status.is(400)))
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
