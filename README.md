
### criando uma fila

aws --endpoint-url=http://localhost:4576 sqs create-queue --queue-name teste

### postando uma mensagem
aws --endpoint-url=http://localhost:4576 sqs send-message --queue-url http://localhost:4576/queue/teste --message-body "AAA123"

### lendo mensagens

aws --endpoint-url=http://localhost:4576 sqs receive-message --queue-url http://localhost:4576/queue/teste --max-number-of-messages 10                                                              