package net.ajn.olingo.client

import net.ajn.olingo.client.config.{ClientConfig, SSOConfig, ScopeConfig, Token}
import org.apache.http.impl.client.HttpClients
import net.ajn.olingo.client.http.HttpService
import org.apache.http.{HttpHeaders, HttpHost}
import org.apache.http.client.methods.HttpPost
import java.net.URI
import java.nio.charset.StandardCharsets

import io.circe.generic.auto._
import io.circe.parser.decode
import io.circe.syntax._
import net.ajn.olingo.client.credential.CredentialService
import org.apache.http.entity.StringEntity
import org.apache.http.util.EntityUtils


object LearningClient {

  def getInstance(config: ClientConfig) = {

    // 1 create HTTP Client
    val httpClient = HttpService.getInstance(config)

    // 2 pass the client to Credential service
    val credentialsService = CredentialService.getCredentials(httpClient, config)

  }

}

