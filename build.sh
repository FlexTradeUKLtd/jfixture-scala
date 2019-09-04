if [[ ${TRAVIS_PULL_REQUEST} = 'false' ]];
then 
	sbt clean +compile +test +publishSigned
else 
	sbt clean +compile +test
fi
