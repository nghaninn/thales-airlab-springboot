import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.4"
	id("io.spring.dependency-management") version "1.0.14.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "com.nghaninn.thales"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-hateoas")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.squareup.okhttp3:okhttp:4.10.0")
	implementation("com.google.code.gson:gson:2.9.0")
	// https://mvnrepository.com/artifact/org.springframework/spring-context
	implementation("org.springframework:spring-context:5.3.23")
	// https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-bootstrap
	implementation("org.springframework.cloud:spring-cloud-starter-bootstrap:3.1.3")
	// https://mvnrepository.com/artifact/com.google.cloud/spring-cloud-gcp-starter
	implementation("com.google.cloud:spring-cloud-gcp-starter:3.3.0")
	// https://mvnrepository.com/artifact/com.google.cloud/spring-cloud-gcp-secretmanager
	implementation("com.google.cloud:spring-cloud-gcp-secretmanager:3.3.0")
	// https://mvnrepository.com/artifact/com.google.cloud/google-cloud-logging-logback
	implementation("com.google.cloud:google-cloud-logging-logback:0.127.10-alpha")
	runtimeOnly("com.h2database:h2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webflux")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

//sourceSets {
//	test {
//		// before 7.1
////		withConvention(KotlinSourceSet::class) {
////			kotlin.setSrcDirs(listOf("src/test/intg"))
////		}
//
//		java {
//			setSrcDirs(listOf("src/test/intg"))
//		}
//	}
//}
