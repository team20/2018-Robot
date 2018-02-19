/*
   Robot 2018 Sensors
   Sends sensor information to the RoboRio via I2C
   Andrew Sealing
*/

#include <Wire.h>               //includes library for using I2C
#include <Adafruit_NeoPixel.h>  //includes library for using LED strip

#define ledStripPin 6     //pins used for the LED strip,
//#define audioInputPin A0  //and the sound sensor

Adafruit_NeoPixel strip = Adafruit_NeoPixel(20, ledStripPin, NEO_RGB + NEO_KHZ800);  //initializes LED strip object

byte pattern = 0;                     //number that controls what light pattern will be displayed
byte prevPattern;                     //previous value of pattern to set pattern back to if it is equal to 1
byte prevCorrectPattern;              //previous correct value of pattern to set pattern back to after case 4 (five white flashes)
uint32_t off = strip.Color(0, 0, 0);  //constant RGB values for different colors (max is 255) (for some reason the order is "BRG" ???)
uint32_t red = strip.Color(0, 255, 0);
uint32_t orange = strip.Color(0, 255, 63);
uint32_t yellow = strip.Color(0, 255, 127);
uint32_t green = strip.Color(0, 0, 255);
uint32_t blue = strip.Color(255, 0, 0);
uint32_t purple = strip.Color(255, 255, 0);
uint32_t white = strip.Color(255, 255, 255);
                                      //__variables for light patterns__
//                                      //_autonomous (case 22)_
//int reading;                          //input from microphone
//float amplitude = 0;                  //smoothed value of reading
//byte pixels;                          //number of pixels to light up
                                      //_red or blue alliance (cases 23 and 24)_
const byte pixelSpacing = 4;          //spacing between on pixels
                                      //_cube acquired_
boolean case25flash = true;            //used to make it run only once
                                      //_elevator_
byte elevatorNum;                     //number of pixels to light up for elevator measurement

void setup() {                        //everything in here runs once, every time the program is started
  Serial.begin(9600);
  Serial.println("program started");
  Wire.begin(1);                      //initializes I2C communication on port 1
  Wire.onReceive(receiveEvent);       //sets a method to call when the Arduino receives data
  strip.begin();                      //initializes LED strip
  strip.setBrightness(63);            //sets brightness (out of 255)
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
  if (pattern != 25)
    prevCorrectPattern = pattern;
  if (pattern < 20)
    elevatorNum = pattern;
  Serial.println(pattern);
}

void lights() { //displays different patterns on the LED strip depending on the value of <pattern>
  /*
     21 - off
     22 - autonomous mode
     23 - red alliance
     24 - blue alliance
     25 - cube acquired
     26 - climbing
     27 - drawing too much current from battery
     28 - demo
     0 to 20 - elevator
  */
  if (!case25flash) {
    if (pattern == 25)              //if pattern is "five white flashes" but it already ran...
      pattern = prevCorrectPattern; //set pattern to what it was before
    else                            //otherwise...
      case25flash = true;           //set it to true for next time
  }
  switch (pattern) {                //selects the correct pattern
    case 21:                        //off - all off
      for (byte i = 0; i < strip.numPixels(); i ++)
        strip.setPixelColor(i, off);
      strip.show();
      break;
    case 1:                         //meaningless value - do nothing
      delay(1);
      break;
//    case 22:                         //autonomous mode - music visualizer          //TODO: make this run all the time*******************************************************
//      reading = abs(analogRead(audioInputPin) - 512);
//      if (reading < amplitude)
//        amplitude -= (amplitude - reading) / 100;
//      else if (reading > amplitude)
//        amplitude += (reading - amplitude) / 10;
//      pixels = (byte) (pow(amplitude / 511, 2) * strip.numPixels());
//      for (byte i = 0; i < strip.numPixels(); i ++) {
//        if (i < pixels)
//          strip.setPixelColor(i, purple);
//        else
//          strip.setPixelColor(i, off);
//      }
//      strip.show();
//      break;
    case 23:                        //red alliance - red chasing
      for (byte i = 0; i < pixelSpacing; i ++) {
        for (byte j = i; j <= strip.numPixels(); j += pixelSpacing) {
          strip.setPixelColor(j, red);
          strip.setPixelColor(j - 1, off);
        }
        strip.show();
        delay(70);
      }
      break;
    case 24:                        //blue alliance - blue chasing
      for (byte i = 0; i < pixelSpacing; i ++) {
        for (byte j = i; j <= strip.numPixels(); j += pixelSpacing) {
          strip.setPixelColor(j, blue);
          strip.setPixelColor(j - 1, off);
        }
        strip.show();
        delay(70);
      }
      break;
    case 25:                        //cube acquired - 5 fast white flashes
      if (case25flash) {
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
        case25flash = false;
      }
      break;
    case 26:                        //climbing - green moving up and down
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
    case 27:                        //drawing too much current from battery - yellow flashing
      for (byte i = 0; i < strip.numPixels(); i ++)
        strip.setPixelColor(i, yellow);
      strip.show();
      delay(100);
      for (byte i = 0; i < strip.numPixels(); i ++)
        strip.setPixelColor(i, off);
      strip.show();
      delay(100);
      break;
    case 28:                         //demo - chasing rainbow pattern (I have no idea how this works...)
      for (byte i = 0; i < 256; i ++) {
        for (byte j = 0; j < strip.numPixels(); j ++) {
          strip.setPixelColor(j, colorWheel(((j * 256 / strip.numPixels()) + i) & 255));
        }
        strip.show();
        delay(20);
      }
      break;
    default:                         //elevator - shows the height of the elevator
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

