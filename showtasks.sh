#!/usr/bin/env bash

fail() {
  echo "There were errors"
}


if ./runcrud.sh; then
   /usr/bin/firefox http://localhost:8080/crud/v1/task/getTasks
else
   fail
fi


