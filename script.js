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

function uploadBg(){
  cc2 = document.getElementById("c2");
  var f2 = document.getElementById("binput");
  bgImage = new SimpleImage(f2);
  fbgImage.drawTo(cc2);
}

function transformImage(){
  //create output image of fg dimensions
  var result = new SimpleImage(fgImage.getWidth(),fgImage.getHeight());
  var greenThreshold = 240;
  //replace green pixels
  for(var pixel of fgImage.values){
    var x = pixel.getX();
    var y = pixel.getY();
    if(pixel.getGreen() > greenThreshold){
      var bgp = bgImage.getPixel(x,y);
      result.setPixel(x,y,bgp);
    }
    else{
      result.setPixel(x,y,pixel);
    }
  }
  return result;
}

function greenScreenMain(){
  //check validity of fg image
  if(fgImage == null || !fgImage.complete()){
    alert("Foreground missing!");
  }
  //check validity of bg image
  if(bgImage == null || !bgImage.complete()){
    alert("Background missing!");
  }
  //clear canvases
  doClear(cc1);
  doClear(cc2);
  //display the output
  var final = transformImage();
  final.drawTo(cc1);
}


function doClear(canvas) {
  var context = canvas.getContext("2d");
  context.clearRect(0,0,canvas.width,canvas.height);
}

function clearCanvas(){
  doClear(cc1);
  doClear(cc2);
}