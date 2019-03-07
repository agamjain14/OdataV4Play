package net.ajn.olingo.client.config

final case class SSOConfig (username: String, pwd: String, grant_type: String, scope: ScopeConfig)
