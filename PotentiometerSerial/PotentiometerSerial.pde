int sensorPin = 0;
int value = 0;

void setup() {
  Serial.begin(9600);
}

void loop(){
  value = analogRead(sensorPin);
  Serial.print(value, DEC);  // print as an ASCII-encoded decimal
  Serial.print("\n");    // prints a tab
  delay(200);
}

