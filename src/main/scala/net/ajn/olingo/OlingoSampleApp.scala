package net.ajn.olingo
import java.net.URI
import java.util
import java.util.Base64

// import net.ajn.olingo.client.config.ClientConfig
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse
import org.apache.olingo.client.api.domain.ClientServiceDocument
import org.apache.olingo.client.core.ODataClientFactory
import org.apache.olingo.client.core.http.{NTLMAuthHttpClientFactory, ProxyWrappingHttpClientFactory}



import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClients


import scala.collection.JavaConverters._

import org.apache.http.util.EntityUtils
import java.nio.charset.StandardCharsets

import org.apache.http.HttpHost
import org.apache.http.client.config.RequestConfig
import net.ajn.olingo.client.config._

import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._



object OlingoSampleApp {


  val username: String = "atosinter"
  val pwd: String = "d1ec824e7519f7b3641f5baa1d9d17a50dd89d6adf9361028926fa9491a2390f40a7103f0bb20c506905cd9143c6a92d"


  def main(args: Array[String]): Unit = {
    // getServiceMetadata(createOdataInstance())
    apacheHttpClient()
  }


private def apacheHttpClient(): Unit = {
  val tokenEndPoint = "https://atosstaging.plateau.com/learning/oauth-api/rest/v1/token"

  val proxy = new HttpHost("193.56.47.20", 8080, "http")

  val config = RequestConfig.custom.setProxy(proxy).build
  val client = HttpClients.createDefault()
  val uri = new URI(tokenEndPoint)
  val post = new HttpPost(uri)
  post.setConfig(config)
  post.addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
  post.addHeader(HttpHeaders.AUTHORIZATION, builtBasicAuthHeader)
  println(post.toString)


  val clientConfig = ClientConfig(grant_type = "client_credentials",scope = ScopeConfig(userId = "NL06715",companyId = "atosinter",userType = "user",resourceType = "learning_public_api"))
  val jsonString: String = clientConfig.asJson.noSpaces
  post.setEntity(new StringEntity(jsonString))

  val resp = client.execute(post)
  try {
    val entity = resp.getEntity
    println(resp.getStatusLine.getStatusCode, resp.getStatusLine.getReasonPhrase)

    val tokenString = EntityUtils.toString(entity, StandardCharsets.UTF_8)
    val token = decode[Token](tokenString)

    println(token)
  } finally {
    resp.close()
  }
}

  private def getMetadata(): Unit = {
    val endPoint: String = "https://atosstaging.plateau.com/learning/odatav4/public/user/learningPlan/v1/"
  }



private def builtBasicAuthHeader()= {
  val auth: String = s"$username:$pwd"
  val encodedAuth: String = org.apache.commons.codec.binary.Base64.encodeBase64String(auth.getBytes("UTF-8"))

  s"Basic $encodedAuth"
}

/*  private def getServiceMetadata(res: ODataRetrieveResponse[ClientServiceDocument]) = {
    val serviceDocument = res.getBody
    println(serviceDocument)
    val entitySetNames : util.Collection[String]  = serviceDocument.getEntitySetNames()
    val mapEntitySet : util.Map[String,URI] = serviceDocument.getEntitySets()
    mapEntitySet.asScala.map(t => println(t))

    val mapSingletons : util.Map[String,URI] = serviceDocument.getSingletons()
    mapSingletons.asScala.map(t => {
      println("in Singletons")
      println(t)})

    val mapfunctionImports : util.Map[String,URI] = serviceDocument.getFunctionImports()
    mapfunctionImports.asScala.map(t => {
      println("in functionImport")
      println(t)}
    )
  }

  private def createOdataInstance() = {
    val client = ODataClientFactory.getClient()
    val serviceRoot = "https://services.odata.org/TripPinRESTierService/(S(zqhzqhdx3dvw0dlxsby4akwa))/"
    val ntlm = new NTLMAuthHttpClientFactory("A674634", "Atosforce@12","MC0W7F5C","ww930")
    client.getConfiguration.setHttpClientFactory(ntlm)
    val path = "http://193.56.47.20:8080"
    client.getConfiguration.setHttpClientFactory(new ProxyWrappingHttpClientFactory(URI.create(path)))
    //ProxyWrappingHttpClientFactory(URI.create("http://localhost:3128")));
    val req = client.getRetrieveRequestFactory().getServiceDocumentRequest(serviceRoot)
    val res = req.execute
    println(res)
    res
  }*/
}
