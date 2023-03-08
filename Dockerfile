FROM openjdk:17-alpine

WORKDIR /app

ENV DB_HOST=db-mini-project
ENV DB_PORT=''
ENV DB_USER=''
ENV DB_PASSWORD=''
ENV DB_ROOT_PASSWORD=''
ENV DB_DATABASE=''

COPY ./target/cardless-withdrawal-0.0.1-SNAPSHOT.jar /app

RUN echo "DB_HOST=${DB_HOST}"  >> ".env"
RUN echo "DB_PORT=${DB_PORT}"  >> ".env"
RUN echo "DB_USER=${DB_USER}"  >> ".env"
RUN echo "DB_PASSWORD=${DB_PASSWORD}"  >> ".env"
RUN echo "DB_ROOT_PASSWORD=${DB_ROOT_PASSWORD}"  >> ".env"
RUN echo "DB_DATABASE=${DB_DATABASE}"  >> ".env"

EXPOSE 8080

CMD ["java", "-jar", "cardless-withdrawal-0.0.1-SNAPSHOT.jar"]