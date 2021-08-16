MIGRATION_USERNAME=banktransactions
MIGRATION_PASSWORD=banktransactions
DATABASE_URL=jdbc:postgresql://localhost:5432/banktransactions

# Run migration for development environment
migration:
	 mvn flyway:migrate -Dflyway.user=$(MIGRATION_USERNAME) -Dflyway.url=$(DATABASE_URL) -Dflyway.password=$(MIGRATION_PASSWORD) -f ../pom.xml

# Run validate for development environment
validate-migration:
	 mvn flyway:validate -Dflyway.user=$(MIGRATION_USERNAME) -Dflyway.url=$(DATABASE_URL) -Dflyway.password=$(MIGRATION_PASSWORD) -f ../pom.xml

# execute build without tests and run the application with development profile
run:
	mvn spring-boot:run -f ../pom.xml

# create the containers for development environment
compose-up:
	docker-compose -p bank_env up -d

# remove the containers
compose-down:
	docker-compose -p bank_env down

# Start the application locally and environment
start: compose-down compose-up migration run

# Start  The Environment
start-environment: compose-down compose-up migration

