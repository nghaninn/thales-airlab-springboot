package com.nghaninn.thales.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.cloud.context.config.annotation.RefreshScope

@ConfigurationProperties("application")
@RefreshScope
class SecretConfiguration (
    secret: String?
)