ROOT_DIR=/home/ubuntu;

sudo chmod +x $ROOT_DIR/build/libs/rmsoft-0.0.1-SNAPSHOT.jar
source $ROOT_DIR/scripts/set_env.sh
nohup java -jar $ROOT_DIR/build/libs/rmsoft-0.0.1-SNAPSHOT.jar >> $ROOT_DIR/nohup.out 2> $ROOT_DIR/nohup.err &