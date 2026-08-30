.PHONY: run-dist build test lint

run-dist:
	./gradlew installDist
	./build/install/app/bin/app

build:
	./gradlew build

test:
	./gradlew test

lint:
	./gradlew checkstyleMain checkstyleTest
