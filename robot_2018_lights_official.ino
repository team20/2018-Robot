/*
   Robot 2018 Sensors
   Shows various patterns on LED strips in different situations
   Andrew Sealing
*/


/*
   Inputs from RoboRio:
   0 to 20 - elevator
   21 - off
   22 - red alliance, low gear
   23 - red alliance, high gear
   24 - blue alliance, low gear
   25 - blue alliance, high gear
   26 - cube acquired
   27 - climbing
   28 - drawing too much current from battery
   29 - rainbow
*/

#include <Wire.h>               //includes library for using I2C
#include <Adafruit_NeoPixel.h>  //includes library for using LED strip

#define ledStripPin 6 //pin used for the long LED strip

Adafruit_NeoPixel strip = Adafruit_NeoPixel(20, ledStripPin, NEO_RGB + NEO_KHZ800);  //initializes LED strip objects

byte I2CData;                         //number received from RoboRio
byte prevI2CData = 21;                //previous value of I2CData
byte pattern = 21;                    //number that controls what light pattern will be displayed
byte prevPattern;                     //previous value of pattern to set pattern back to after case 25 (five white flashes)
uint32_t off = strip.Color(0, 0, 0);  //constant RGB values for different colors (max is 255) (for some reason the order is "BRG" ???)
uint32_t red = strip.Color(0, 255, 0);
uint32_t orange = strip.Color(0, 255, 63);
uint32_t yellow = strip.Color(0, 255, 127);
uint32_t green = strip.Color(0, 0, 255);
uint32_t blue = strip.Color(255, 0, 0);
uint32_t purple = strip.Color(255, 255, 0);
uint32_t white = strip.Color(255, 255, 255);
                                      //__variables for light patterns__
                                      //_red or blue alliance_
const byte pixelSpacing = 5;          //spacing between on pixels
                                      //_cube acquired_
boolean flashed = true;               //used to make it run only once
                                      //_elevator_
byte elevatorNum;                     //number of pixels to light up for elevator measurement

void setup() {                  //everything in here runs once, every time the program is started
  Wire.begin(1);                //initializes I2C communication on port 1
  Wire.onReceive(receiveEvent); //sets a method to call when the Arduino receives data
  strip.begin();                //initializes LED strip
  strip.setBrightness(63);      //sets brightness (out of 255)
  strip.show();                 //sets all pixels to "off" state
}

void loop() { //loops infinitely until power supply is removed
  lights();   //constantly calls lights() in order to give every onlooker's eyes undivided satisfaction
}

void receiveEvent() { //called when the Arduino receives data from the RoboRio in order to show the correct pattern
  //  prevPattern = pattern;
  //  pattern = Wire.read();
  //  if (pattern == 1)
  //    pattern = prevPattern;
  //  if (pattern != 25)
  //    prevCorrectPattern = pattern;
  //  if (pattern <= 20)
  //    elevatorNum = pattern;
  prevI2CData = I2CData;
  I2CData = Wire.read();
  if (pattern != 25)
    prevPattern = pattern;
  if (I2CData == 1 && prevI2CData != 1)
    I2CData = prevI2CData;
  if (I2CData >= 0 && I2CData <= 20) {
    elevatorNum = I2CData;
    pattern = 0;
  }
  if (I2CData >= 21 && I2CData <= 28)
    pattern = I2CData;
}

void lights() {               //displays different patterns on the LED strip depending on the value of pattern
  if (!flashed) {
    if (pattern == 25)        //if pattern is "five white flashes" but it already ran...
      pattern = prevPattern;  //set pattern to what it was before
    else                      //otherwise...
      flashed = true;         //set it to true for next time
  }
  switch (pattern) {          //selects the correct pattern
    case 1:                   //meaningless value - do nothing
      delay(1);
      break;
    case 21:                  //off - all off
      for (byte i = 0; i < strip.numPixels(); i ++)
        strip.setPixelColor(i, off);
      strip.show();
      break;
    case 22:                  //red alliance, low gear - red chasing down
      for (byte i = 0; i < pixelSpacing; i ++) {
        for (int j = strip.numPixels() - i; j >= -1; j -= pixelSpacing) {
          strip.setPixelColor(j, red);
          strip.setPixelColor(j + 1, off);
        }
        strip.show();
        delay(80);
      }
      break;
    case 23:                  //red alliance, high gear - red chasing up
      for (byte i = 0; i < pixelSpacing; i ++) {
        for (byte j = i; j <= strip.numPixels(); j += pixelSpacing) {
          strip.setPixelColor(j, red);
          strip.setPixelColor(j - 1, off);
        }
        strip.show();
        delay(80);
      }
      break;
    case 24:                  //blue alliance, low gear - blue chasing down
      for (byte i = 0; i < pixelSpacing; i ++) {
        for (int j = strip.numPixels() - i; j >= -1; j -= pixelSpacing) {
          strip.setPixelColor(j, blue);
          strip.setPixelColor(j + 1, off);
        }
        strip.show();
        delay(80);
      }
      break;
    case 25:                  //blue alliance, high gear - blue chasing up
      for (byte i = 0; i < pixelSpacing; i ++) {
        for (byte j = i; j <= strip.numPixels(); j += pixelSpacing) {
          strip.setPixelColor(j, blue);
          strip.setPixelColor(j - 1, off);
        }
        strip.show();
        delay(80);
      }
      break;
    case 26:                  //cube acquired - 5 fast white flashes
      if (flashed) {
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
        flashed = false;
      }
      break;
    case 27:                  //climbing - green moving up and down
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
    case 28:                  //drawing too much current from battery - yellow flashing
      for (byte i = 0; i < strip.numPixels(); i ++)
        strip.setPixelColor(i, yellow);
      strip.show();
      delay(100);
      for (byte i = 0; i < strip.numPixels(); i ++)
        strip.setPixelColor(i, off);
      strip.show();
      delay(100);
      break;
    case 29:                   //demo - chasing rainbow pattern (I have no idea how this works...)
      for (byte i = 0; i < 256; i ++) {
        for (byte j = 0; j < strip.numPixels(); j ++)
          strip.setPixelColor(j, colorWheel(((j * 256 / strip.numPixels()) + i) & 255));
        strip.show();
        delay(20);
      }
      break;
    default:                   //elevator - shows the height of the elevator
      for (byte i = 0; i < strip.numPixels(); i ++) {
        if (i < elevatorNum)
          strip.setPixelColor(i, purple);
        else
          strip.setPixelColor(i, off);
      }
      strip.show();
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
