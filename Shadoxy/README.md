# Shadoxy

## Table of Content

### Project structure

````
proxy/
├── proxy-core/                          # Kern-Modul
│   └── src/main/java/com/proxy/
│       ├── ProxyApplication.java        # Einstiegspunkt (Main)
│       │
│       ├── config/
│       │   ├── ProxyConfig.java         # Konfigurationsklassen (@ConfigurationProperties)
│       │   └── ProxyConfigLoader.java   # YAML/ENV-Loader
│       │
│       ├── server/
│       │   ├── ProxyServer.java         # Server-Bootstrap (Netty)
│       │   ├── HttpProxyHandler.java    # HTTP-Request-Handler
│       │   ├── HttpsConnectHandler.java # CONNECT-Tunneling für HTTPS
│       │   └── ProxyChannelInitializer.java  # Netty Pipeline Setup
│       │
│       ├── anonymizer/
│       │   ├── HeaderSanitizer.java     # Header-Stripping/-Rewriting
│       │   ├── IpAnonymizer.java        # X-Forwarded-For Unterdrückung
│       │   ├── UserAgentRotator.java    # User-Agent-Rotation
│       │   └── FingerprintRandomizer.java  # TLS-Fingerprint
│       │
│       ├── filter/
│       │   ├── BlocklistFilter.java     # Domain-/IP-Blockliste
│       │   ├── ContentFilter.java       # Tracker-/Ad-Filterung
│       │   └── RuleEngine.java          # Regelengine (Regex/Glob)
│       │
│       ├── transport/
│       │   ├── TlsContextFactory.java   # TLS 1.3-Konfiguration
│       │   ├── UpstreamProxyConnector.java  # SOCKS5/HTTP Upstream-Chaining
│       │   └── DnsOverHttpsResolver.java    # DoH – verhindert DNS-Leaks
│       │
│       ├── mitm/                        # Optional: HTTPS-Inspektion
│       │   ├── CertificateAuthority.java    # CA-Verwaltung (Bouncy Castle)
│       │   ├── DynamicCertGenerator.java    # Zertifikat pro Host generieren
│       │   └── MitmInterceptor.java         # Traffic-Inspektion
│       │
│       ├── logging/
│       │   ├── AccessLogger.java        # Access-Log (opt. deaktivierbar)
│       │   └── AuditLogger.java         # Audit-Trail
│       │
│       └── metrics/
│           ├── MetricsRegistry.java     # Micrometer-Registry
│           └── ProxyStats.java          # Interne Zähler/Statistiken
│
├── proxy-common/                        # Shared Utilities
│   └── src/main/java/com/proxy/common/
│       ├── util/
│       │   ├── CertUtil.java
│       │   ├── NetUtil.java
│       │   └── HttpUtil.java
│       └── model/
│           ├── ProxyRequest.java
│           └── ProxyResponse.java
│
├── proxy-filter-plugin/                 # Optionales Plugin-Modul (SPI)
│   └── src/main/java/com/proxy/plugin/
│       ├── FilterPlugin.java            # Plugin-Interface
│       └── PluginLoader.java            # SPI-basierter Plugin-Loader
│
├── configs/
│   ├── application.yaml                 # Standardkonfiguration
│   └── blocklist.txt
│
├── deployments/
│   ├── Dockerfile
│   ├── docker-compose.yml
│   └── k8s/
│
├── docs/
│   └── architecture.md
│
├── pom.xml                              # Root-POM (Multi-Module)
└── README.md
````