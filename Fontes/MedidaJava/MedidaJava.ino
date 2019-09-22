/**
 * Aquisição de Medidas via Portas Análogicas
 * Prof. Paulo Barreto
 * Data 21/09/2019
 */
#define SENSOR A0
 
void setup(){
  Serial.begin(9600);  
  pinMode(SENSOR, INPUT);
}

void loop(){
  int medida = analogRead(SENSOR);
  
  Serial.print("$");
  Serial.print(medida);
  Serial.println("#");
  delay(1000);
}
