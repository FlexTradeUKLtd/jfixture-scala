if [[ ${TRAVIS_PULL_REQUEST} = 'false' ]];
then 
	sbt clean compile test publish
else 
	sbt clean compile test
fi
