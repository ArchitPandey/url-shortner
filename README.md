# URL Shortner

This is a basic implementation of URL Shortner (like tinyUrl)

## Installation
- Mysql
  - https://artifacthub.io/packages/helm/wso2/mysql 
  - helm repo add wso2 https://helm.wso2.com
  - helm install my-mysql wso2/mysql --version 1.6.9 -f ./mysql-helmchart-customvalues.yml
  - To connect to mysql: 
    - kubectl run -i --tty ubuntu --image=ubuntu:16.04 --restart=Never -- bash -il
    - apt-get update && apt-get install mysql-client -y
    - mysql -h my-mysql -p (enter-pwd when prompted)