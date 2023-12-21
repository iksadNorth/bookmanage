sudo chmod +x build/libs/rmsoft-0.0.1-SNAPSHOT.jar
nohup java -jar build/libs/rmsoft-0.0.1-SNAPSHOT.jar &

echo "$(date) | run deploy.sh"
echo "$(pwd) | run deploy.sh"