ROOT_DIR=/home/ubuntu;

sudo chmod +x $ROOT_DIR/build/libs/rmsoft-0.0.1-SNAPSHOT.jar
nohup java -jar $ROOT_DIR/build/libs/rmsoft-0.0.1-SNAPSHOT.jar &

echo "$(date) | run deploy.sh"
echo "$(pwd) | run deploy.sh"