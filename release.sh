#!/bin/bash

if [ $# -ne 1 ] 
then 
	echo Error: provide the version to release, e.g., 1.2.3
	echo Latest version is: $(git fetch --tag && git tag --sort=committerdate | tail -n 1)
	exit 1
fi 

RELEASE_VERSION=$1

echo Releasing v${RELEASE_VERSION}

# Make sure everything is as it should be
CURRENT_BRANCH=$(git branch | grep '*' | cut -c 3-)
git checkout .
# Switch to a release branch
git checkout -b release
# Move both poms to release versions
./updateVersion.sh ${RELEASE_VERSION}
# Commit changes
git add -u
git commit -m "Release ${RELEASE_VERSION}"
# Tag and push tag
git tag v${RELEASE_VERSION}
git push origin v${RELEASE_VERSION}
# Switch back to CURRENT_BRANCH and delete release branch
git checkout ${CURRENT_BRANCH}
git branch -D release


# Update to next snapshot version
MAJOR=$(cut -f 1 -d "." <<< ${RELEASE_VERSION})
MINOR=$(cut -f 2 -d "." <<< ${RELEASE_VERSION})
NEXT_VERSION=${MAJOR}.$((${MINOR} + 1)).0-SNAPSHOT
./updateVersion.sh ${NEXT_VERSION}
