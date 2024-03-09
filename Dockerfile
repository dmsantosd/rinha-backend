# Use a new stage to create a clean final image
FROM ghcr.io/graalvm/jdk-community:21

WORKDIR /app

# Copy the built application from the build stage
COPY ./build/native/nativeCompile/rinha-backend .

# Set the command to start the application
CMD ["./rinha-backend"]