
### criando uma fila

aws --endpoint-url=http://localhost:4576 sqs create-queue --queue-name fila

### postando uma mensagem
aws --endpoint-url=http://localhost:4576 sqs send-message --queue-url http://localhost:4576/queue/fila --message-body "AAA123"

### lendo mensagens

aws --endpoint-url=http://localhost:4576 sqs receive-message --queue-url http://localhost:4576/queue/fila --max-number-of-messages 10                                                              