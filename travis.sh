#!/bin/bash
set -euo pipefail

if [ "$TRAVIS_PULL_REQUEST" == "true" ]; then
    echo "build for pull request not yet supported"
    exit 1
fi

scan() {
    # update sonar (including failing tests)
    ./gradlew -x test build
    ./gradlew test || true
    git fetch --unshallow #get all commit history for exact blame info
    ./gradlew -x test -Dsonar.host.url=https://sonarcloud.io -Dsonar.organization=moaxcp -Dsonar.login=$SONAR_TOKEN sonarqube
}

build() {
    # run build
    ./gradlew build
}

publish() {
    # publish
    ./gradlew publish \
        -Dnexus.username=moaxcp \
        -Dnexus.password=$NEXUS_PASSWORD \
        -Psigning.keyId=A9A4043B \
        -Psigning.secretKeyRingFile=signingkey.gpg \
        -Psigning.password=$SIGNING_PASSWORD
}

if [ -n "$TRAVIS_TAG" ]; then
    echo "release for $TRAVIS_TAG"
    build
    publish

    ./gradlew closeAndReleaseRepository --info --stacktrace \
        -Dnexus.username=moaxcp \
        -Dnexus.password=$NEXUS_PASSWORD

elif [ "$TRAVIS_BRANCH" == "master" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ]; then
    echo "build for master branch"
    scan
    build
else
    echo "build for different branch not yet supported"
    exit 1
fi