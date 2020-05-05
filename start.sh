#!/usr/bin/env bash

if [ $# -eq 0 ] || [ $# -gt 1 ]; then
  printf "Wrong number of arguments, use \"./start.sh java\" to run java version or \".start.sh kotlin\" to run kotlin version"
  exit 1
fi

case $1 in
  "java")
    docker-compose -f docker/java-compose.yml up --build
    ;;
  "kotlin")
    docker-compose -f docker/kotlin-compose.yml up --build
    ;;
  *)
   printf "Unknown argument, use \"./start.sh java\" to run java version or \".start.sh kotlin\" to run kotlin version"
   exit 1
esac


