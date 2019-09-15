/*
 * Exemplo de Execução Inicial
 * Prof. Paulo Barreto
 * Data 14/09/2019
 */
void setup() {
  Serial.begin(9600);
  Serial.println("Execução do Setup");
}

void loop() {
  Serial.println("Execução do Looping");
  
  delay(1000); //tempo estabelecido em milisegundos
}
