# URL Shortner

This is a basic implementation of URL Shortner (like tinyUrl). This implementation depends on my-sql to store short url mappings. And id-generator-service to generate unique ids fast.

## Installation
- Mysql
  - https://artifacthub.io/packages/helm/wso2/mysql 
  - helm repo add wso2 https://helm.wso2.com
  - helm install my-mysql wso2/mysql --version 1.6.9 -f ./mysql-helmchart-customvalues.yml
  - To connect to mysql: 
    - kubectl run -i --tty ubuntu --image=ubuntu:16.04 --restart=Never -- bash -il
    - apt-get update && apt-get install mysql-client -y
    - mysql -h my-mysql -p (enter-pwd when prompted)
- id-generator service
  - clone https://github.com/ArchitPandey/unique-id-generator.git
  - kubectl apply ./svc-spec.yml and then ./statefulset-spec.yml files in project root to create cluster-ip service and stateful-set
- url-shortner
  - create node-port (./svc-spec.yml) service and then deployment (./deployment-spec.yml) 