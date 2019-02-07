#!/bin/bash
set -euo pipefail

./gradlew build
./gradlew -Dsonar.host.url=https://sonarcloud.io -Dsonar.organization=moaxcp -Dsonar.login=$SONAR_TOKEN sonarqube