
package net.ajn.olingo.client.config

import java.net.InetSocketAddress

import com.typesafe.config.ConfigFactory
import io.circe.config.syntax._
import io.circe.generic.auto._

import net.ajn.olingo.client.config.Implicits._

/*final case class ClientConfig(
                               grant_type: String,

                               scope: ScopeConfig)*/

final case class ClientConfig(serviceUri: String,
                              proxyEnable: Boolean,
                              proxy: Option[InetSocketAddress],
                              sso: SSOConfig)

//
object ClientConfig {
  def getConfig: Either[io.circe.Error, ClientConfig] = {
    val config = ConfigFactory.load()
    config.as[ClientConfig]("learning.client")
  }
}
