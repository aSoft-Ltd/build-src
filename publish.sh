echo "publishing publish-src"
chmod +x gradlew
./gradlew wrapper || exit
./gradlew :publish || exit
echo "Finished publishing publish-src"