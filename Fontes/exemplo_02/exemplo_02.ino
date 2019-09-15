/**
 * Declaração de Variáveis
 * Prof. Paulo Barreto
 * Data 14/09/2019
 */
//Variavel Global
int valorMedio = 0;
int contagem;

void setup() {
  Serial.begin(9600);
  pinMode(A0, INPUT);
  contagem = 0;
}

void loop() {
  //Variavel Local
  int medida;

  medida = analogRead(A0);  
  contagem = contagem + 1;

  valorMedio += medida;

  Serial.println(medida);
  delay(1000);
}

float calcularMedia(){
  Serial.print("Media = ");
  Serial.println(valorMedio/contagem);
}

