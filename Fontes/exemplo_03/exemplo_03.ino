/**
 * Exemplo de Decisão - Desvio de Fluxo
 * Prof. Paulo Barreto
 * Data 14/09/2019
 */

int sensor = A0;
int led = 13;
 
void setup() {
  Serial.begin(9600);
  pinMode(sensor, INPUT);
  pinMode(led, OUTPUT);
}

void loop() {
  //Input de medidas
  int medida = analogRead(sensor);

  //Decisão
  if(medida > 200){
    Serial.println("Luminosidade Adequada");
    digitalWrite(led, LOW);
  }else{
    Serial.println("Luminosidade Inadequada");
    digitalWrite(led, HIGH);
  }
  
  delay(1000);
}
