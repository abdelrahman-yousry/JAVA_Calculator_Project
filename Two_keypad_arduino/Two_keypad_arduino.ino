#include <Keypad.h>
//author Abdelrahman Yosury
const int ROW_NUM = 4; //four rows
const int COLUMN_NUM = 4; //four columns

char keys1[ROW_NUM][COLUMN_NUM] = {
  {'<','u','>', 'A'},
  {'l','o','r', 'B'},
  {'%','k','i', 'C'},
  {'c','F','E', 'D'}
};
char keys2[ROW_NUM][COLUMN_NUM]= {
  {'1','2','3', '+'},
  {'4','5','6', '*'},
  {'7','8','9', '/'},
  {'.','0','=', '-'}
};
byte pin_rows[ROW_NUM] = {4, 3, 2, 1}; //connect to the row pinouts of the keypad
byte pin_column[COLUMN_NUM] = {8, 7, 6, 5}; //connect to the column pinouts of the keypad
byte pin_rows[ROW_NUM] = {12, 11, 10, 9}; //connect to the row pinouts of the keypad
byte pin_column1[COLUMN_NUM] = {16, 15, 14, 13}; //connect to the column pinouts of the keypad
Keypad keypad1 = Keypad( makeKeymap(keys), pin_rows, pin_column, ROW_NUM, COLUMN_NUM );
Keypad keypad2= Keypad( makeKeymap(keys1), pin_rows, pin_column1, ROW_NUM, COLUMN_NUM );
void setup(){
  Serial.begin(9600);
}

void loop(){
  char key1 = keypad.getKey();
  char key2= keypad1.getKey();
  if (key1){
    Serial.println(key);
  }
  if(key2)
  {
    Serial.println(key1);
  }
  }
