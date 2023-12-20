sudo apt-get update
sudo apt-get install openjdk-17-jdk -y
echo "JAVA_HOME='/usr/lib/jvm/java-17-openjdk-amd64'" >> /etc/environment
source /etc/environment