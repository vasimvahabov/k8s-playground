#!/bin/bash

if [ -z "$1" ] || [ -z "$2" ]; then
  echo >&2 "Missing parameters ..."
  exit 1
fi

if ! rundate=$(<$1); then
  echo >&2 "Failed: unable to read timestamp.current ..."
  exit 2
fi

curdate=$(date +'%s')
time_difference=$((curdate-rundate))

if [ $time_difference -gt $2 ]
then
  echo >&2 "Liveness failing, timestamp too old..."
  exit 1
fi

exit 0
