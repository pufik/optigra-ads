package org.optigra.ads.integration.application

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import com.excilys.ebi.gatling.http.Headers._
import akka.util.duration._
import com.excilys.ebi.gatling.core.Predef.bootstrap._
import com.excilys.ebi.gatling.core.Predef.assertions._
import com.excilys.ebi.gatling.core.structure.ScenarioBuilder.configureScenario

class DeviceFlowScenario extends Simulation {

  val httpConf = httpConfig
    .baseURL("http://localhost:8080/optigra-ads-rest-api/api")
    .acceptHeader("application/json")

  val deviceDetails = csv("device.csv").queue

  val scn = scenario("Insert Device Scenario")
	.feed(deviceDetails)
	.exec(
                http("Insert Device")
		      	.post("/device")
			.basicAuth("admin", "admin")
			.body("""{"deviceToken":"${deviceToken}","deviceUid":"${deviceUid}"}""")
			.asJSON
			.check(status.is(200)))
	.pause(500 milliseconds, 2 seconds)
    	.exec(
      		http("Get device")
        		.get("/device/${deviceUid}")
			.basicAuth("admin", "admin")
        		.check(status.is(200))
			.check(jsonPath("$.deviceUid").find.is("${deviceUid}"))
			.check(jsonPath("$.deviceToken").find.is("${deviceToken}")))
    	.exec(
      		http("Update device")
        		.put("/device/${deviceUid}")
			.basicAuth("admin", "admin")
			.body("""{"deviceToken":"${newDeviceToken}","deviceUid":"${deviceUid}"}""")
			.asJSON
        		.check(status.is(200)))
    	.exec(
      		http("Get updated device")
        		.get("/device/${deviceUid}")
			.basicAuth("admin", "admin")
        		.check(status.is(200))
			.check(jsonPath("$.deviceToken").find.is("${newDeviceToken}")))
    	.exec(
      		http("Delete device")
        		.delete("/device/${deviceUid}")
			.basicAuth("admin", "admin")
        		.check(status.is(200)))
    	.exec(
      		http("Get defunct device")
        		.get("/device/${deviceUid}")
			.basicAuth("admin", "admin")
        		.check(status.is(400)))

  setUp(
    scn.users(1).protocolConfig(httpConf)
  )
}
