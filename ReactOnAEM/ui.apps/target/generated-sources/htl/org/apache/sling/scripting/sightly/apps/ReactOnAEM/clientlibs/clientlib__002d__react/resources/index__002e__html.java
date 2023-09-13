/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ******************************************************************************/
package org.apache.sling.scripting.sightly.apps.ReactOnAEM.clientlibs.clientlib__002d__react.resources;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class index__002e__html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

out.write("<!doctype html><html lang=\"en\"><head><meta charset=\"utf-8\"/><meta name=\"viewport\" content=\"width=device-width,initial-scale=1\"/><meta name=\"theme-color\" content=\"#000000\"/><meta name=\"description\" content=\"React-On-AEM\"/><link rel=\"icon\" href=\"/etc.clientlibs/ReactOnAEM/clientlibs/clientlib-react/resources/favicon.ico\"/><link rel=\"apple-touch-icon\" href=\"/etc.clientlibs/ReactOnAEM/clientlibs/clientlib-react/resources/logo192.png\"/><link rel=\"manifest\" href=\"/etc.clientlibs/ReactOnAEM/clientlibs/clientlib-react/resources/manifest.json\"/><title>React-On-AEM</title><meta property=\"cq:pagemodel_root_url\" content=\"\"/><link rel=\"stylesheet\" href=\"/etc.clientlibs/ReactOnAEM/clientlibs/clientlib-base.css\"/><script>0</script><link href=\"/etc.clientlibs/ReactOnAEM/clientlibs/clientlib-react/resources/static/css/main.617f8f3f.chunk.css\" rel=\"stylesheet\"/></head><body class=\"page basicpage\"><noscript>You need to enable JavaScript to run this app.</noscript><div id=\"spa-root\"></div><script>!function(e){function t(t){for(var n,o,c=t[0],i=t[1],l=t[2],s=0,d=[];s<c.length;s++)o=c[s],Object.prototype.hasOwnProperty.call(a,o)&&a[o]&&d.push(a[o][0]),a[o]=0;for(n in i)Object.prototype.hasOwnProperty.call(i,n)&&(e[n]=i[n]);for(f&&f(t);d.length;)d.shift()();return u.push.apply(u,l||[]),r()}function r(){for(var e,t=0;t<u.length;t++){for(var r=u[t],n=!0,o=1;o<r.length;o++){var i=r[o];0!==a[i]&&(n=!1)}n&&(u.splice(t--,1),e=c(c.s=r[0]))}return e}var n={},o={1:0},a={1:0},u=[];function c(t){if(n[t])return n[t].exports;var r=n[t]={i:t,l:!1,exports:{}};return e[t].call(r.exports,r,r.exports,c),r.l=!0,r.exports}c.e=function(e){var t=[];o[e]?t.push(o[e]):0!==o[e]&&{4:1}[e]&&t.push(o[e]=new Promise((function(t,r){for(var n=\"static/css/\"+({}[e]||e)+\".\"+{3:\"31d6cfe0\",4:\"269a8ef5\",5:\"31d6cfe0\",6:\"31d6cfe0\"}[e]+\".chunk.css\",a=c.p+n,u=document.getElementsByTagName(\"link\"),i=0;i<u.length;i++){var l=(f=u[i]).getAttribute(\"data-href\")||f.getAttribute(\"href\");if(\"stylesheet\"===f.rel&&(l===n||l===a))return t()}var s=document.getElementsByTagName(\"style\");for(i=0;i<s.length;i++){var f;if((l=(f=s[i]).getAttribute(\"data-href\"))===n||l===a)return t()}var d=document.createElement(\"link\");d.rel=\"stylesheet\",d.type=\"text/css\",d.onload=t,d.onerror=function(t){var n=t&&t.target&&t.target.src||a,u=new Error(\"Loading CSS chunk \"+e+\" failed.\\n(\"+n+\")\");u.code=\"CSS_CHUNK_LOAD_FAILED\",u.request=n,delete o[e],d.parentNode.removeChild(d),r(u)},d.href=a,document.getElementsByTagName(\"head\")[0].appendChild(d)})).then((function(){o[e]=0})));var r=a[e];if(0!==r)if(r)t.push(r[2]);else{var n=new Promise((function(t,n){r=a[e]=[t,n]}));t.push(r[2]=n);var u,i=document.createElement(\"script\");i.charset=\"utf-8\",i.timeout=120,c.nc&&i.setAttribute(\"nonce\",c.nc),i.src=function(e){return c.p+\"static/js/\"+({}[e]||e)+\".\"+{3:\"813be4da\",4:\"041c5389\",5:\"0651e27b\",6:\"b8de5fa9\"}[e]+\".chunk.js\"}(e);var l=new Error;u=function(t){i.onerror=i.onload=null,clearTimeout(s);var r=a[e];if(0!==r){if(r){var n=t&&(\"load\"===t.type?\"missing\":t.type),o=t&&t.target&&t.target.src;l.message=\"Loading chunk \"+e+\" failed.\\n(\"+n+\": \"+o+\")\",l.name=\"ChunkLoadError\",l.type=n,l.request=o,r[1](l)}a[e]=void 0}};var s=setTimeout((function(){u({type:\"timeout\",target:i})}),12e4);i.onerror=i.onload=u,document.head.appendChild(i)}return Promise.all(t)},c.m=e,c.c=n,c.d=function(e,t,r){c.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:r})},c.r=function(e){\"undefined\"!=typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:\"Module\"}),Object.defineProperty(e,\"__esModule\",{value:!0})},c.t=function(e,t){if(1&t&&(e=c(e)),8&t)return e;if(4&t&&\"object\"==typeof e&&e&&e.__esModule)return e;var r=Object.create(null);if(c.r(r),Object.defineProperty(r,\"default\",{enumerable:!0,value:e}),2&t&&\"string\"!=typeof e)for(var n in e)c.d(r,n,function(t){return e[t]}.bind(null,n));return r},c.n=function(e){var t=e&&e.__esModule?function(){return e.default}:function(){return e};return c.d(t,\"a\",t),t},c.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},c.p=\"/etc.clientlibs/ReactOnAEM/clientlibs/clientlib-react/resources/\",c.oe=function(e){throw console.error(e),e};var i=this.webpackJsonpReactOnAEM=this.webpackJsonpReactOnAEM||[],l=i.push.bind(i);i.push=t,i=i.slice();for(var s=0;s<i.length;s++)t(i[s]);var f=l;r()}([])</script><script src=\"/etc.clientlibs/ReactOnAEM/clientlibs/clientlib-react/resources/static/js/2.e3a14858.chunk.js\"></script><script src=\"/etc.clientlibs/ReactOnAEM/clientlibs/clientlib-react/resources/static/js/main.ac033361.chunk.js\"></script></body></html>");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

