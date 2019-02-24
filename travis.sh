#!/bin/bash
set -euo pipefail

if [ "$TRAVIS_PULL_REQUEST" == "true" ]; then
    echo "build for pull request not yet supported"
    exit 1
fi

if [ -n "$TRAVIS_TAG" ]; then
    echo "release for $TRAVIS_TAG"
    ./gradlew publish \
        -Dnexus.username=moaxcp \
        -Dnexus.password=$NEXUS_PASSWORD \
        -Psigning.keyId=A9A4043B \
        -Psigning.secretKeyRingFile=signingkey.gpg \
        -Psigning.password=$SIGNING_PASSWORD

    ./gradlew closeAndReleaseRepository --info --stacktrace \
        -Dnexus.username=moaxcp \
        -Dnexus.password=$NEXUS_PASSWORD

elif [ "$TRAVIS_BRANCH" == "master" ]; then
    echo "build for master branch"

    # update sonar (including failing tests)
    ./gradlew -x test build
    ./gradlew test || true
    git fetch --unshallow #get all commit history for exact blame info
    ./gradlew -x test -Dsonar.host.url=https://sonarcloud.io -Dsonar.organization=moaxcp -Dsonar.login=$SONAR_TOKEN sonarqube

    # run build
    ./gradlew build

    openssl aes-256-cbc -K $encrypted_89c9bd3bab4e_key -iv $encrypted_89c9bd3bab4e_iv -in signingkey.gpg.enc -out signingkey.gpg -d

    # publish snapshot
    ./gradlew publish
        -Dnexus.username=moaxcp \
        -Dnexus.password=$NEXUS_PASSWORD \
        -Psigning.keyId=A9A4043B \
        -Psigning.secretKeyRingFile=signingkey.gpg \
        -Psigning.password=$SIGNING_PASSWORD
else
    echo "build for different branch not yet supported"
    exit 1
fi