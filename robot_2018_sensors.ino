/*
   Robot 2018 Sensors
   Sends sensor information to the RoboRio via I2C
   Andrew Sealing
*/

#include <Wire.h>               //includes library for using I2C
#include <Adafruit_NeoPixel.h>  //includes library for using LED strip

#define ledStripPin 6     //pins used for the LED strip, D6?
#define IRSensorPin 8       //the IR sensor,
#define audioInputPin A0   //and the sound sensor

Adafruit_NeoPixel strip = Adafruit_NeoPixel(20, ledStripPin, NEO_RGB + NEO_KHZ800);  //initializes LED strip object

byte sensorData[1];                   //array of data collected from sensors to be sent to the RoboRio
byte pattern = 0;                     //number that controls what light pattern will be displayed
byte prevPattern;                     //previous value of pattern to set pattern back to if it is equal to 1
byte prevCorrectPattern;              //previous correct value of pattern to set pattern back to after case 4 (five white flashes)
uint32_t off = strip.Color(0, 0, 0);  //constant RGB values for different colors (max is 255) (for some reason the order is "GRB" ???)
uint32_t red = strip.Color(0, 255, 0);
//uint32_t orange = strip.Color(63, 255, 0);
uint32_t yellow = strip.Color(0, 255, 127);
uint32_t green = strip.Color(0, 0, 255);
uint32_t blue = strip.Color(255, 0, 0);
uint32_t purple = strip.Color(255, 127, 0);
uint32_t white = strip.Color(255, 255, 255);
                                      //__variables for light patterns__
                                      //_autonomous (case 2)_
int reading;                          //input from microphone
float amplitude = 0;                  //smoothed value of reading
byte pixels;                          //number of pixels to light up
                                      //_red or blue alliance (cases 3 and 4)_
const byte pixelSpacing = 5;          //spacing between on pixels
                                      //_cube acquired_
boolean case5flash = true;            //used to make it run only once

void setup() {                        //everything in here runs once, every time the program is started
  Serial.begin(9600);                 //initializes the serial monitor
  Serial.println("program started");  //prints to the serial monitor
  pinMode(IRSensorPin, INPUT_PULLUP); //sets the pin used for the IR sensor as and input with a virtual pullup resistor
  Wire.begin(1);                      //initializes I2C communication on port 1
  Wire.onReceive(receiveEvent);       //sets a method to call when the Arduino receives data
  Wire.onRequest(requestEvent);       //sets a method to call when data is requested from the Arduino
  strip.begin();                      //initializes LED strip
  strip.setBrightness(127);           //sets brightness (out of 255)
  strip.show();                       //sets all pixels to "off" state
}

void loop() { //loops infinitely until power supply is removed
  lights();   //constantly calls lights() in order to give every onlooker's eyes undivided satisfaction
}

void receiveEvent() {       //called when the Arduino receives data from the RoboRio
  prevPattern = pattern;
  pattern = Wire.read();    //sets the value of <pattern> to the correct value
  if (pattern == 1)
    pattern = prevPattern;
  if (pattern != 4)
    prevCorrectPattern = pattern;
    Serial.println("Pattern:" + pattern);
}

void requestEvent() {         //called when data is requested from the Arduino by the RoboRio
  getSensorData();            //gets data from the IR sensor
  Wire.write(sensorData[0]);  //sends data to the RoboRio
}

void getSensorData() {
  sensorData[0] = IRSensor();
}

byte IRSensor() { //returns input from IR sensor as a byte
  if (digitalRead(IRSensorPin))
    return 0;
  else
    return 1;
}

void lights() { //displays different patterns on the LED strip depending on the value of <pattern>
  /*
     0 - off
     2 - autonomous mode
     3 - red alliance
     4 - blue alliance
     5 - cube acquired
     6 - climbing
     7 - drawing too much current from battery
     8 - demo
  */
 // pattern = 8;
  if (!case5flash) {
    if (pattern == 5)               //if pattern is "five white flashes" but it already ran...
      pattern = prevCorrectPattern; //set pattern to what it was before
    else                            //otherwise...
      case5flash = true;            //set it to true for next time
  }
  switch (pattern) {                //selects the correct pattern
    case 0:                         //off - all off
      for (byte i = 0; i < strip.numPixels(); i ++)
        strip.setPixelColor(i, off);
      strip.show();
      break;
    case 2:                         //autonomous mode - music visualizer
      reading = abs(analogRead(audioInputPin) - 512);
      if (reading < amplitude)
        amplitude -= (amplitude - reading) / 100;
      else if (reading > amplitude)
        amplitude += (reading - amplitude) / 10;
      pixels = (byte) (pow(amplitude / 511, 2) * strip.numPixels());
      for (byte i = 0; i < strip.numPixels(); i ++) {
        if (i < pixels)
          strip.setPixelColor(i, purple);
        else
          strip.setPixelColor(i, off);
      }
      strip.show();
      break;
    case 3:                         //red alliance - red chasing
      for (byte i = 0; i < pixelSpacing; i ++) {
        for (byte j = i; j <= strip.numPixels(); j += pixelSpacing) {
          strip.setPixelColor(j, red);
          strip.setPixelColor(j - 1, off);
        }
        strip.show();
        delay(30);
      }
      break;
    case 4:                         //blue alliance - blue chasing
      for (byte i = 0; i < pixelSpacing; i ++) {
        for (byte j = i; j <= strip.numPixels(); j += pixelSpacing) {
          strip.setPixelColor(j, blue);
          strip.setPixelColor(j - 1, off);
        }
        strip.show();
        delay(30);
      }
      break;
    case 5:                         //cube acquired - 5 fast white flashes
      if (case5flash) {
        for (byte i = 0; i < 5; i ++) {
          for (byte j = 0; j < strip.numPixels(); j ++)
            strip.setPixelColor(j, white);
          strip.show();
          delay(50);
          for (byte j = 0; j < strip.numPixels(); j ++)
            strip.setPixelColor(j, off);
          strip.show();
          delay(50);
        }
        case5flash = false;
      }
      break;
    case 6:                         //climbing - green moving up and down
      for (byte i = 0; i < strip.numPixels(); i ++) {
        strip.setPixelColor(i, green);
        strip.show();
        delay(10);
      }
      for (byte i = strip.numPixels() - 1; i > 0; i --) {
        strip.setPixelColor(i, off);
        strip.show();
        delay(10);
      }
      break;
    case 7:                         //drawing too much current from battery - yellow alternating
//      for (byte i = 0; i < strip.numPixels(); i ++) {
//       if (i % 2 == 0)
//         strip.setPixelColor(i, yellow);
//        else
//          strip.setPixelColor(i, off);
//      }
//     strip.show();
//     delay(500);
//      for (byte i = 0; i < strip.numPixels(); i ++) { //       if (i % 2 == 0)
//          strip.setPixelColor(i, off);
//        else
//          strip.setPixelColor(i, yellow);
//      }
//      strip.show();
//      delay(500);

        for (byte i = 0; i < 5; i ++) {
          for (byte j = 0; j < strip.numPixels(); j ++)
            strip.setPixelColor(j, yellow);
          strip.show();
          delay(100);
          for (byte j = 0; j < strip.numPixels(); j ++)
            strip.setPixelColor(j, off);
          strip.show();
          delay(100);
        }

      

      break;
    case 8:                         //demo - chasing rainbow pattern (I have no idea how this works...)
      for (byte i = 0; i < 256; i ++) {
        for (byte j = 0; j < strip.numPixels(); j ++) {
          strip.setPixelColor(j, colorWheel(((j * 256 / strip.numPixels()) + i) & 255));
        }
        strip.show();
        delay(20);
      }
      break;
  }
}

uint32_t colorWheel(byte wheelPosition) {
  wheelPosition = 255 - wheelPosition;
  if (wheelPosition < 85)
    return strip.Color(255 - wheelPosition * 3, 0, wheelPosition * 3);
  if (wheelPosition < 170) {
    wheelPosition -= 85;
    return strip.Color(0, wheelPosition * 3, 255 - wheelPosition * 3);
  }
  wheelPosition -= 170;
  return strip.Color(wheelPosition * 3, 255 - wheelPosition * 3, 0);
}


