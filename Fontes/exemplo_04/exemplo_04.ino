/**
 * Exemplo de Interação com o Usuário
 * Prof. Paulo Barreto
 * Data 14/09/2019
 */
//Receberá opção do usuário
int val;

void setup() {
  Serial.begin(9600);
  pinMode(13, OUTPUT);
  menu(); //Chamada da função que interage com usuário    
}

void loop() {
   if(Serial.available()){
      val = (int)Serial.read();
    
      if(val == 49){//Liga Rele 1
        digitalWrite(13, HIGH);
      }
      if(val == 50){//Desliga Rele 1
        digitalWrite(13, LOW);
      }
   }else{
      delay(1000);
   }
}

void menu(){
  Serial.println("******* MENU ********");
  Serial.println("** 1 - Ligar       **");
  Serial.println("** 2 - Desligar    **");
  Serial.println("*********************");
}

