package net.ajn.olingo.client.http

import java.net.{InetSocketAddress, URI}

import com.typesafe.config.Config
import net.ajn.olingo.client.config.ClientConfig
import org.apache.http.client.HttpClient
import org.apache.http.{HttpHost, HttpRequest, HttpResponse}

import scala.concurrent.Future
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.config.RequestConfig

import org.apache.http.conn.params.ConnRoutePNames
import org.apache.http.impl.client.DefaultHttpClient


object HttpService {

  def getInstance(config: ClientConfig): HttpClient = {

    config.proxyEnable match {
      case true => {
        config.proxy match {
          case Some(value) => {
            val proxy = new HttpHost(value.getHostName, value.getPort, "http")
            val httpclient = new DefaultHttpClient
            httpclient.getParams.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy)
            httpclient
          }
          case None => null
        }
      }
      case false => {
        new DefaultHttpClient()
      }
    }
  }
}
