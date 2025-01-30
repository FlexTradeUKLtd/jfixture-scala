#!/bin/bash

VERSION=${1?"Usage: $0 <VERSION>"}

echo Updating version to v${VERSION}

echo "Updating SBT file"
echo "ThisBuild / version := \"${VERSION}\"" > version.sbt
