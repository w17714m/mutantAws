@ECHO OFF
mvn clean install && sls deploy
echo serverless invoke local --function mutant --path lib/data.json
