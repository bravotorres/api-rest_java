FROM rhel9-openjdk17:1.0

ENV PYTHONUNBUFFERED 1
ENV WEBAPP_DIR /var/www/api

# Configure work directory
COPY . $WEBAPP_DIR

# Configure user and group
# RUN useradd api-rest
# USER api-rest

WORKDIR $WEBAPP_DIR

# VOLUME $WEBAPP_DIR/storage

# Install Python environment dependencies.
RUN pip3 install pip setuptools && \
    pip3 install -r resources/requirements.txt

EXPOSE 8080/tcp

CMD ["python", "main.py"]
