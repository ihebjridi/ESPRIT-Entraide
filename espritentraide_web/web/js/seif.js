/* Usage: */
// Grab your <use> tags and loop over them with something like this
var useTag = document.querySelectorAll('use')
if(useTag && useTag.length) {
  // This is just checking first <use>
  const dimenisons = useTag[0].getBoundingClientRect();
  // If use tag is 0px tall, the inlined <use> reference failed.
  if(dimenisons.height === 0) {
    fixVisiblyEmptyUseTag(useTag[0])
  }
}

function fixVisiblyEmptyUseTag(useTag) {
  // Grab the use tag xlink:href for finding the corresponding symbol
  var id = useTag.getAttribute('xlink:href').replace("#", '')
  // Grab parentNode to inline the already inlined SVG
  var parentNode = useTag.parentNode
  // kill broken use tag
  parentNode.removeChild(useTag);
  // Set class on parentNode to find this later
  parentNode.setAttribute('class', 'svg-' + id)
  // grab the actual SVG from the <defs>
  var inlineSVG = document.getElementById(id)
  // grab the actual SVG paths to inject in place of <use> tag
  var inlineSVGPaths = inlineSVG.innerHTML
  // grab viewbox attributes to make sure icons are correct size
  var inlineSVGViewBox = inlineSVG.getAttribute('viewBox')
  parentNode.setAttribute('viewBox', inlineSVGViewBox)
  // Create a dummy receptacle
  var receptacle = document.createElement('div');
  // Wrap the svg string to a svg object (string)
  var svgfragment = '<svg>' + inlineSVGPaths + '</svg>';
  // Add all svg to the receptacle
  receptacle.innerHTML = '' + svgfragment;
  // Splice the childs of the SVG inside the receptacle to the SVG at the body
  Array.prototype.slice.call(receptacle.childNodes[0].childNodes).forEach(function (el) {
    // grab the matching parentNodes and insert the SVG inline in place of <use> tag
    var SVGsOnPage = document.querySelectorAll('.svg-' + id)
    for (var i = 0; i < SVGsOnPage.length; i++) {
      SVGsOnPage[i].appendChild(el);
    }
  });
}