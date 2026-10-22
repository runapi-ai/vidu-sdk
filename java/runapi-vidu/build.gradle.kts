plugins {
  `java-library`
  `maven-publish`
}

extra["runapiSlug"] = "vidu"

description = "RunAPI Vidu Java SDK for Vidu workflows."

java {
  withSourcesJar()
  withJavadocJar()
}

dependencies {
  api("ai.runapi:runapi-core:0.10.0")

  testImplementation(platform("org.junit:junit-bom:5.10.3"))
  testImplementation("org.junit.jupiter:junit-jupiter")
}

publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      from(components["java"])
      artifactId = "runapi-vidu"
      pom {
        name = "RunAPI Vidu Java SDK"
        description = "RunAPI Vidu Java SDK for Vidu workflows."
        url = "https://runapi.ai/models/vidu"
        licenses {
          license {
            name = "Apache License, Version 2.0"
            url = "https://www.apache.org/licenses/LICENSE-2.0"
          }
        }
        developers {
          developer {
            id = "runapi"
            name = "RunAPI"
            email = "contact@runapi.ai"
          }
        }
        scm {
          url = "https://github.com/runapi-ai/vidu-sdk"
          connection = "scm:git:https://github.com/runapi-ai/vidu-sdk.git"
          developerConnection = "scm:git:ssh://git@github.com/runapi-ai/vidu-sdk.git"
        }
      }
    }
  }
}
