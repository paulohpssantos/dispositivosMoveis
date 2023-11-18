#include <SoftwareSerial.h>
#include <Servo.h>

SoftwareSerial BT(2, 6); //TX, RX do HC05


String device;
//DEFINE A PINAGEM DOS MOTORES
Servo servoBase;  // Objeto servo para controlar a base --> esquerda/direita
Servo servoGarra;  //Objeto servo para controlar a garra --> abrir/fechar
Servo servoAltura; //Objeto servo para controlar a altura do braço --> subir/descer
Servo servoProfundidade; //Objeto servo para profundidade a altura do braço  --> frente/tras

int posBase = 0;
int posGarra = 0;
int posAltura = 0;
int posProfundidade = 0;

void setup() {  
  
  BT.begin(9600);
  Serial.begin(9600);

  //Associa cada objeto a um pino pwm
  servoBase.attach(3);
  servoGarra.attach(5);
  servoAltura.attach(9);
  servoProfundidade.attach(11);

  posBase = 0;
  posGarra = 0;
  posAltura = 0;
  posProfundidade = 0;

} 
void loop() { 
  
  while (BT.available()) //Check if there is an available byte to read
  {
    delay(10); //Delay added to make thing stable 
    char c = BT.read(); //Conduct a serial read
    device += c; //build the string.
  } 
  if (device.length() > 0) 
  {
    Serial.println(device); 
    
    if (device == "direita") {

      posBase += 1;
      servoBase.write(posBase);      
      
    }
    else if (device == "esquerda") {
      
      posBase -= 1;
      servoBase.write(posBase);
      
      
    }
    else if (device == "frente") {

      posProfundidade += 1;
      servoProfundidade.write(posProfundidade);
      
    }
    else if (device == "tras") {
      
      posProfundidade -= 1;
      servoProfundidade.write(posProfundidade);
      
    }
    else if (device == "cima") {

      posAltura += 1;
      servoAltura.write(posAltura);
      
    }
    else if (device == "baixo") {
      
      posAltura -= 1;
      servoAltura.write(posAltura);
      
    }
    else if (device == "abrir") {

      posGarra += 1;
      servoGarra.write(180);
      
    }
    else if (device == "fechar") {
      
      posGarra -= 1;
      servoGarra.write(0);
      
    }

    
    delay(20);
    
    device=""; //Reset the variable
  }
}   

  // parar motor
  /*digitalWrite(3, LOW); //Motor_1 
  digitalWrite(4, LOW); //Motor_1 
  digitalWrite(5, LOW); //Motor_2 
  digitalWrite(6, LOW); //Motor_2
  delay(2000);

  //Virar para direita (MOTO 1 PARA TRAS E MOTOR 2 PARA FRENTE )
  digitalWrite(motor1Pin3, LOW); //Motor_1 
  digitalWrite(motor1Pin4, HIGH); //Motor_1
  digitalWrite(motor2Pin5, HIGH); //Motor_2 
  digitalWrite(motor2Pin6, LOW); //Motor_2
  analogWrite(speedM1Pin, 255);
  analogWrite(speedM2Pin, 255);
  delay(2000);

  // parar motor
  digitalWrite(3, LOW); //Motor_1 
  digitalWrite(4, LOW); //Motor_1 
  digitalWrite(5, LOW); //Motor_2 
  digitalWrite(6, LOW); //Motor_2
  delay(500);

  //ir pra frente
  digitalWrite(motor1Pin3, LOW); //Motor_1 
  digitalWrite(motor1Pin4, HIGH); //Motor_1
  digitalWrite(motor2Pin5, LOW); //Motor_2 
  digitalWrite(motor2Pin6, HIGH); //Motor_2
  analogWrite(speedM1Pin, 127);
  analogWrite(speedM2Pin, 127);
  delay(2000);

  // parar motor
  digitalWrite(3, LOW); //Motor_1 
  digitalWrite(4, LOW); //Motor_1 
  digitalWrite(5, LOW); //Motor_2 
  digitalWrite(6, LOW); //Motor_2
  delay(2000);

  
  //Virar para direita (MOTO 1 PARA FRENTE E MOTOR 2 PARA TRAZ )
  digitalWrite(motor1Pin3, HIGH); //Motor_1 
  digitalWrite(motor1Pin4, LOW); //Motor_1
  digitalWrite(motor2Pin5, LOW); //Motor_2 
  digitalWrite(motor2Pin6, HIGH); //Motor_2
  analogWrite(speedM1Pin, 255);
  analogWrite(speedM2Pin, 255);
  delay(2000);

  // parar motor
  digitalWrite(3, LOW); //Motor_1 
  digitalWrite(4, LOW); //Motor_1 
  digitalWrite(5, LOW); //Motor_2 
  digitalWrite(6, LOW); //Motor_2
  delay(2000);
  */
  // Motor p/ Direita   (PARA TRAS)
  /*digitalWrite(3, HIGH); //Motor_1 
  digitalWrite(4, LOW); //Motor_1
  analogWrite(speedM1Pin, 255);  //velocidades: 127, 191, 255
  //digitalWrite(5, HIGH); //Motor_2 
  //digitalWrite(6, LOW); //Motor_2
  delay(2000);
  // parar motor
  digitalWrite(3, LOW); //Motor_1 
  digitalWrite(4, LOW); //Motor_1 
  digitalWrite(5, LOW); //Motor_2 
  digitalWrite(6, LOW); //Motor_2
  delay(2000);*/

  
  // Motor p/ Esquerda  (PARA FRENTE)
  //digitalWrite(3, LOW); //Motor_1 
  //digitalWrite(4, HIGH); //Motor_1
  //digitalWrite(5, LOW); //Motor_2 
  //digitalWrite(6, HIGH); //Motor_2
  
