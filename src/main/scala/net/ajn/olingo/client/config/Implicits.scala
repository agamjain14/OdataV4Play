package net.ajn.olingo.client.config

import java.net.{InetSocketAddress, URI}

import io.circe.{Decoder, HCursor}

import scala.util.{Failure, Success, Try}

object Implicits {
  implicit val uriDecoder: Decoder[URI] = Decoder.decodeString.emap { str =>
    Try(URI.create(str)) match {
      case Success(value)     => Right(value)
      case Failure(throwable) => Left(throwable.getMessage)
    }
  }
  implicit object InetSocketAddressDecoder extends Decoder[InetSocketAddress] {
    final def apply(c: HCursor): Decoder.Result[InetSocketAddress] =
      for {
        host <- c.downField("host").as[String].right
        port <- c.downField("port").as[Int].right
      } yield InetSocketAddress.createUnresolved(host, port)
  }
}
