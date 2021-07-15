# order
cd Order
mvn package
docker build -t 879772956301.dkr.ecr.ap-northeast-2.amazonaws.com/user01-order:v1 .
docker build -t 879772956301.dkr.ecr.ap-northeast-2.amazonaws.com/user01-order:v2 .
docker push 879772956301.dkr.ecr.ap-northeast-2.amazonaws.com/user01-order:v1

# payment
cd ..
cd Payment
mvn package
docker build -t 879772956301.dkr.ecr.ap-northeast-2.amazonaws.com/user01-payment:v1 .
docker build -t 879772956301.dkr.ecr.ap-northeast-2.amazonaws.com/user01-payment:v2 .
docker push 879772956301.dkr.ecr.ap-northeast-2.amazonaws.com/user01-payment:v1

# rent
cd ..
cd Rent
mvn package
docker build -t 879772956301.dkr.ecr.ap-northeast-2.amazonaws.com/user01-rent:v1 .
docker build -t 879772956301.dkr.ecr.ap-northeast-2.amazonaws.com/user01-rent:v2 .
docker push 879772956301.dkr.ecr.ap-northeast-2.amazonaws.com/user01-rent:v1

# stock
cd ..
cd Stock
mvn package
docker build -t 879772956301.dkr.ecr.ap-northeast-2.amazonaws.com/user01-stock:v1 .
docker build -t 879772956301.dkr.ecr.ap-northeast-2.amazonaws.com/user01-stock:v2 .
docker push 879772956301.dkr.ecr.ap-northeast-2.amazonaws.com/user01-stock:v1

# dashboard
cd ..
cd Dashboard
mvn package
docker build -t 879772956301.dkr.ecr.ap-northeast-2.amazonaws.com/user01-dashboard:v1 .
docker build -t 879772956301.dkr.ecr.ap-northeast-2.amazonaws.com/user01-dashboard:v2 .
docker push 879772956301.dkr.ecr.ap-northeast-2.amazonaws.com/user01-dashboard:v1

# gateway
cd ..
cd gateway
mvn package
docker build -t 879772956301.dkr.ecr.ap-northeast-2.amazonaws.com/user01-gateway:v1 .
docker build -t 879772956301.dkr.ecr.ap-northeast-2.amazonaws.com/user01-gateway:v2 .
docker push 879772956301.dkr.ecr.ap-northeast-2.amazonaws.com/user01-gateway:v1
