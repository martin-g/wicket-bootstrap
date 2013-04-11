(function($,document,undefined){var pluses=/\+/g;function raw(s){return s;}
function decoded(s){return unRfc2068(decodeURIComponent(s.replace(pluses,' ')));}
function unRfc2068(value){if(value.indexOf('"')===0){value=value.slice(1,-1).replace(/\\"/g,'"').replace(/\\\\/g,'\\');}
return value;}
function fromJSON(value){return config.json?JSON.parse(value):value;}
var config=$.cookie=function(key,value,options){if(value!==undefined){options=$.extend({},config.defaults,options);if(value===null){options.expires=-1;}
if(typeof options.expires==='number'){var days=options.expires,t=options.expires=new Date();t.setDate(t.getDate()+ days);}
value=config.json?JSON.stringify(value):String(value);return(document.cookie=[encodeURIComponent(key),'=',config.raw?value:encodeURIComponent(value),options.expires?'; expires='+ options.expires.toUTCString():'',options.path?'; path='+ options.path:'',options.domain?'; domain='+ options.domain:'',options.secure?'; secure':''].join(''));}
var decode=config.raw?raw:decoded;var cookies=document.cookie.split('; ');var result=key?null:{};for(var i=0,l=cookies.length;i<l;i++){var parts=cookies[i].split('=');var name=decode(parts.shift());var cookie=decode(parts.join('='));if(key&&key===name){result=fromJSON(cookie);break;}
if(!key){result[name]=fromJSON(cookie);}}
return result;};config.defaults={};$.removeCookie=function(key,options){if($.cookie(key)!==null){$.cookie(key,null,options);return true;}
return false;};})(jQuery,document);