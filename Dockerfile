# Use the latest version of an Ubuntu-based image as the base
FROM ubuntu:latest

# Set environment variables to non-interactive mode to avoid prompts
ENV DEBIAN_FRONTEND noninteractive

# Install basic tools and dependencies
RUN apt-get update && \
    apt-get install -y wget unzip openjdk-18-jdk

# Set JAVA_HOME environment variable
ENV JAVA_HOME /usr/lib/jvm/java-18-openjdk-amd64
ENV PATH ${PATH}:${JAVA_HOME}/bin

# Download and install Android SDK
ENV ANDROID_SDK_ROOT /usr/local/android-sdk
RUN mkdir ${ANDROID_SDK_ROOT}
WORKDIR ${ANDROID_SDK_ROOT}
RUN wget -q https://dl.google.com/android/repository/commandlinetools-linux-8512546_latest.zip -O commandlinetools.zip && \
    unzip commandlinetools.zip && \
    rm commandlinetools.zip

# Set PATH for Android SDK
ENV PATH ${PATH}:${ANDROID_SDK_ROOT}/cmdline-tools/bin

# Accept licenses before installing components
RUN yes | sdkmanager --licenses --sdk_root=${PATH}

# Install Android SDK (API Level 34) components
RUN sdkmanager "platform-tools" "platforms;android-34" "build-tools;33.0.1" --sdk_root=${PATH}

# Accepts licenses
RUN mkdir -p ${ANDROID_SDK_ROOT}/licenses/ \
    && echo -e "\n24333f8a63b6825ea9c5514f83c2829b004d1fee" > "${ANDROID_SDK_ROOT}/licenses/android-sdk-license" \
    && echo -e "\n84831b9409646a918e30573bab4c9c91346d8abd" > "${ANDROID_SDK_ROOT}/licenses/android-sdk-preview-license"

# Creates a work directory for the project
WORKDIR /project

# Copies project files to Docker Image
COPY . /project

# Builds the .apk file
RUN ./gradlew assembleDebug

CMD ["echo", "Build complete"]
