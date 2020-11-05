echo "Building samples"

do_dir () {
  cd $1
  echo "Building $(pwd)"
  chmod +x ./gradlew || exit
  ./gradlew build || exit
}

do_dir ./samples/android-library
do_dir ../js-library
do_dir ../jvm-library
do_dir ../mpp-library