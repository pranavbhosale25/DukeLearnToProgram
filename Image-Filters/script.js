// global vars for cross-function access
var fgImage = null;
var bgImage = null;
var cc1;
var cc2;

function uploadFg(){
  cc1 = document.getElementById("c1");
  var f1 = document.getElementById("finput");
  fgImage = new SimpleImage(f1);
  fgImage.drawTo(cc1);
}

function doGray(){
  //check validity of fg image
  if(fgImage == null || !fgImage.complete()){
    alert("Image missing!");
  }
  //clear canvases
  doClear(cc1);
  //display the output
  var final =makeGray();
  final.drawTo(cc1);
}

function doRed(){
  if(fgImage == null || !fgImage.complete()){
    alert("Image missing!");
  }
  //clear canvases
  doClear(cc1);
  //display the output
  var final =makeRed();
  final.drawTo(cc1);
}

function doExchange(){
  //check validity of fg image
  if(fgImage == null || !fgImage.complete()){
    alert("Image missing!");
  }
  //clear canvases
  doClear(cc1);
  //display the output
  var final =Exchange();
  final.drawTo(cc1);
}


function doClear(canvas) {
  var context = canvas.getContext("2d"); context.clearRect(0,0,canvas.width,canvas.height);
}

function clearCanvas(){
  doClear(cc1);
  doClear(cc2);
}

function makeGray(){
  var result = new SimpleImage(fgImage.width(),fgImage.height());
  for( var pixel of result.values){
    var gray = (pixel.getRed + pixel.getBlue + pixel.getGreen)/3;
    pixel.setRed(gray);
    pixel.setBlue(gray);
    pixel.setGreen(gray);
  }
  return result;
}

function makeRed(){
    var result = new SimpleImage(fgImage.width(),fgImage.height());
  for (var pixel of result.values()) {
    var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
    if (avg < 128) {
      pixel.setRed(2 * avg);
      pixel.setGreen(0);
      pixel.setBlue(0);
    } else {
      pixel.setRed(255);
      pixel.setGreen(2 * avg - 255);
      pixel.setBlue(2 * avg - 255);
    }
  }
  return result;
}

function Exchange(){
  var result = new SimpleImage(fgImage.width(),fgImage.height());
  for( var pixel of result.values){
    pixel.setRed(pixel.getGreen());
    pixel.setBlue(pixel.getRed());
    pixel.setGreen(pixel.getBlue());
  }
  return result;
}