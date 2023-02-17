docker run -d --name elasticsearch --net elastic -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -t docker.elastic.co/elasticsearch/elasticsearch:8.6.1
docker run --name kibana --net elastic -p 5601:5601 docker.elastic.co/kibana/kibana:8.6.1
