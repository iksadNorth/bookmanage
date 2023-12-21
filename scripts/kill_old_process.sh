ps -ef | grep jar | grep -v 'grep' | awk '{print $2}' | xargs --replace=@ kill -9 @

echo "$(date) | run kill_old_process.sh"