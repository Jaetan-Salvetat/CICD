#!/usr/bin/env bash

GRADLE_FILE="./gradle.properties"

function getProperty {
    PROP_KEY=$1
    # shellcheck disable=SC2002
    PROP_VALUE=$(cat $GRADLE_FILE | grep "$PROP_KEY" | cut -d'=' -f2)
    echo "$PROP_VALUE"
}

echo "VERSION=$(getProperty "appVersion")" >> "$GITHUB_ENV"
echo "TAG=jaetan/ktor-sample:$VERSION" >> "$GITHUB_ENV"
