### criando uma fila

aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name fila

awslocal sqs create-queue --queue-name fila

### postando uma mensagem

aws --endpoint-url=http://localhost:4566 sqs send-message --queue-url http://localhost:4566/queue/fila --message-body "AAA123"

awslocal sqs send-message --queue-url http://localhost:4566/queue/fila --message-body "AAA123"

### lendo mensagens

aws --endpoint-url=http://localhost:4566 sqs receive-message --queue-url http://localhost:4566/queue/fila --max-number-of-messages 10 

awslocal sqs receive-message --queue-url http://localhost:4566/queue/fila --max-number-of-messages 10                                                              

### criando bucket

aws --endpoint-url=http://localhost:4566 --region=us-east-1 s3 mb s3://test-bucket

awslocal s3 mb s3://test-bucket

### listando chave kms
aws kms list-keys --endpoint-url http://localhost:4566

awslocal kms list-keys

### criando chave kms
aws kms create-key --endpoint=http://localhost:4566

awslocal kms create-key

### criar topico sns
awslocal sns create-topic --name meu-topico

### listar topicos
awslocal sns list-topics

### inscreve fila ao topico
awslocal sns subscribe --topic-arn arn:aws:sns:us-east-1:000000000000:meu-topico --protocol sqs --notification-endpoint http://localhost:4566/000000000000/fila-1

### lista subscriptions
awslocal sns list-subscriptions

### 
awslocal sns publish --topic-arn arn:aws:sns:us-east-1:000000000000:meu-topico --message "FAAAAALA PESSOAL"

