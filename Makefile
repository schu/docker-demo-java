all: build

.PHONY: all

build:
	docker run --rm \
		-v $(shell pwd):/source \
		-w /source \
		java:9-jdk \
		javac HelloServer.java

.PHONY: build

run:
	docker run --rm \
		-v $(shell pwd):/source:ro \
		-w /source \
		-p 8000:8000 \
		java:9-jdk \
		java HelloServer

.PHONY: run

run-container:
	docker run --rm -p 8000:8000 docker-demo-java

.PHONY: run-container

container: HelloServer.java
	docker build -t docker-demo-java .

.PHONY: container

clean:
	rm -vf *.class

.PHONY: clean
