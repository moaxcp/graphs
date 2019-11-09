#! /usr/bin/env nix-shell
#! nix-shell --show-trace --pure --keep encrypted_de4911fc7d4a_key --keep TRAVIS_PULL_REQUEST --keep TRAVIS_TAG --keep TRAVIS_BRANCH -i bash -p graphviz adoptopenjdk-bin openssl

set -euo pipefail

scan() {
    # update sonar (including failing tests)
    echo "building skipping tests"
    ./gradlew -x test build
    echo " running tests"
    ./gradlew test || true
    git fetch --unshallow #get all commit history for exact blame info
    echo "running sonar skipping tests"
    ./gradlew -x test -Dsonar.host.url=https://sonarcloud.io -Dsonar.organization=moaxcp -Dsonar.login=$SONAR_TOKEN sonarqube
}

build() {
    # run build
    ./gradlew build
    ./gradlew javadoc
}

publish() {
    # publish
    ./gradlew publish \
        -Dnexus.username=moaxcp \
        -Dnexus.password="$NEXUS_PASSWORD" \
        -Psigning.keyId=A9A4043B \
        -Psigning.secretKeyRingFile="$PWD"/signingkey.gpg \
        -Psigning.password="$SIGNING_PASSWORD"
}

./gradlew -version
dot -V
neato -V
openssl version

JAVA_HOME=$(readlink -e $(type -p javac) | sed  -e 's/\/bin\/javac//g')

echo "JAVA_HOME is ${JAVA_HOME}"

openssl aes-256-cbc -K "$encrypted_de4911fc7d4a_key" -iv "$encrypted_de4911fc7d4a_iv" -in signingkey.gpg.enc -out signingkey.gpg -d

if [ "$TRAVIS_PULL_REQUEST" == "true" ]; then
    echo "build for pull request not yet supported"
    exit 1
fi

if [ -n "$TRAVIS_TAG" ]; then
    echo "release for $TRAVIS_TAG"
    build
    publish

    ./gradlew closeAndReleaseRepository --info --stacktrace \
        -Dnexus.username=moaxcp \
        -Dnexus.password="$NEXUS_PASSWORD"

elif [ "$TRAVIS_BRANCH" == "master" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ]; then
    echo "build for master branch"
    scan
    build
else
    echo "build for different branch not yet supported"
    exit 1
fi