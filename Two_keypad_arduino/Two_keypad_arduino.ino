#include <Keypad.h>
//author Abdelrahman Yosury
const int ROW_NUM = 4; //four rows
const int COLUMN_NUM = 4; //four columns

char keys_1[ROW_NUM][COLUMN_NUM] = {
  {'<','u','>', 'A'},
  {'l','o','r', 'B'},
  {'t','d','a', 'C'},
  {'c','F','E', 'D'}
};
char keys_2[ROW_NUM][COLUMN_NUM]= {
  {'1','2','3', '/'},
  {'4','5','6', '*'},
  {'7','8','9', '-'},
  {'.','0','=', '+'}
};
byte pin_rows_1[ROW_NUM] = {23, 25, 27, 29}; //connect to the row pinouts of the keypad
byte pin_column_1[COLUMN_NUM] = {31, 33, 35, 37}; //connect to the column pinouts of the keypad
byte pin_rows_2[ROW_NUM] = {39, 41, 43, 45}; //connect to the row pinouts of the keypad
byte pin_column_2[COLUMN_NUM] = {47, 49, 51, 53}; //connect to the column pinouts of the keypad
Keypad keypad_1 = Keypad( makeKeymap(keys_1), pin_rows_1, pin_column_1, ROW_NUM, COLUMN_NUM );
Keypad keypad_2 = Keypad( makeKeymap(keys_2), pin_rows_2, pin_column_2, ROW_NUM, COLUMN_NUM );
void setup(){
  Serial.begin(9600);
}

void loop(){
  char key_1 = keypad_1.getKey();
  char key_2 = keypad_2.getKey();
  
  if (key_1)
    Serial.write(key_1);
  if(key_2)
    Serial.write(key_2);
}
