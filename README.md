
### criando uma fila

aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name fila
awslocal sqs create-queue --queue-name fila

### postando uma mensagem

aws --endpoint-url=http://localhost:4566 sqs send-message --queue-url http://localhost:4576/queue/fila --message-body "AAA123"
awslocal sqs send-message --queue-url http://localhost:4566/queue/fila --message-body "AAA123"

### lendo mensagens

aws --endpoint-url=http://localhost:4566 sqs receive-message --queue-url http://localhost:4576/queue/fila --max-number-of-messages 10                                                              
awslocal sqs receive-message --queue-url http://localhost:4566/queue/fila --max-number-of-messages 10                                                              

### criando bucket

aws --endpoint-url=http://localhost:4566 --region=us-east-1 s3 mb s3://test-bucket
awslocal s3 mb s3://test-bucket