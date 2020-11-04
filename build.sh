echo "Building build-src"
chmod +x gradlew
./gradlew wrapper || exit
./gradlew :build || exit
echo "Finished building build-src"