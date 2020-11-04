echo "publishing publish-src"
chmod +x gradlew
./gradlew wrapper || exit
./gradlew :publishToMavenLocal || exit
echo "Finished publishing publish-src"