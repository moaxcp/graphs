#! /usr/bin/env nix-shell
#! nix-shell --show-trace --pure --keep encrypted_de4911fc7d4a_key --keep encrypted_de4911fc7d4a_iv --keep TRAVIS_PULL_REQUEST --keep TRAVIS_TAG --keep TRAVIS_BRANCH --keep SIGNING_PASSWORD --keep SONAR_TOKEN --keep NEXUS_PASSWORD -i bash -p git cacert graphviz adoptopenjdk-bin openssl

set -euo pipefail

./gradlew -version
dot -V
neato -V
openssl version

JAVA_HOME=$(readlink -e $(type -p javac) | sed  -e 's/\/bin\/javac//g')
GIT_SSL_CAINFO=~/.nix-profile/etc/ssl/certs/ca-bundle.crt

echo "JAVA_HOME is ${JAVA_HOME}"