package net.ajn.olingo

import com.typesafe.config.ConfigFactory
import net.ajn.olingo.client.config.ClientConfig
import net.ajn.olingo.client.LearningClient

object RunLearningAdapter {
  private val config = ConfigFactory.load()


  def main(args: Array[String]): Unit = {

    init
  }


  private def init = {
    val clientConfig = ClientConfig.getConfig.right.get
    val client = LearningClient.getInstance(clientConfig)

  }

}
