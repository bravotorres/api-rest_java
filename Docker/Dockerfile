FROM rhel9-openjdk17:1.0

ENV ENVIRONMENT dev
ENV WEBAPP_DIR /var/www/api

# Configure work directory
COPY target/api-demo.jar $WEBAPP_DIR

# Configure user and group
# RUN useradd api-rest
# USER api-rest

WORKDIR $WEBAPP_DIR

# VOLUME $WEBAPP_DIR/storage

EXPOSE 8080/tcp

CMD [ \
	"java", \
	# "-javaagent:<agent-path>/dd-java-agent.jar", \
	"-Xms256m", \
	"-Xmx320m", \
	"-jar", \
	"api-demo.jar" \
]
