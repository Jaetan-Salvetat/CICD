GRADLE_FILE=build.gralde.kts

function getProperty {
    PROP_KEY=$1
    # shellcheck disable=SC2002
    PROP_VALUE=$(cat $GRADLE_FILE | grep "$PROP_KEY" | cut -d'=' -f2)
    echo "$PROP_VALUE"
}

echo "version=$(getProperty "version")" >> "$GITHUB_ENV"