(this.webpackJsonpReactOnAEM=this.webpackJsonpReactOnAEM||[]).push([[5],{289:function(e,t,n){"undefined"!=typeof self&&self,e.exports=function(e){var t={};function n(r){if(t[r])return t[r].exports;var s=t[r]={i:r,l:!1,exports:{}};return e[r].call(s.exports,s,s.exports,n),s.l=!0,s.exports}return n.m=e,n.c=t,n.d=function(e,t,r){n.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:r})},n.r=function(e){"undefined"!=typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},n.t=function(e,t){if(1&t&&(e=n(e)),8&t)return e;if(4&t&&"object"==typeof e&&e&&e.__esModule)return e;var r=Object.create(null);if(n.r(r),Object.defineProperty(r,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var s in e)n.d(r,s,function(t){return e[t]}.bind(null,s));return r},n.n=function(e){var t=e&&e.__esModule?function(){return e.default}:function(){return e};return n.d(t,"a",t),t},n.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},n.p="",n(n.s=33)}({0:function(e,t){e.exports=n(1)},1:function(e,t,n){"use strict";n.d(t,"b",(function(){return o})),n.d(t,"a",(function(){return c}));var r=n(0),s=n.n(r);const a=e=>{const t=(null!=e.componentTitle&&e.componentTitle.length>0?e.componentTitle+" - ":"")+(null!=e.emptyTextAppend?e.emptyTextAppend:"Please configure the component");return s.a.createElement("div",{className:"cq-placeholder"+(null!=e.classAppend?" "+e.classAppend:"")},t)},o=(e,t)=>n=>{const s=n.baseCssClass,a=s&&s.trim().length>0?s:t,o={...n,baseCssClass:a};return r.createElement(e,Object.assign({},o))},c=(e,t,n,s)=>o=>{const c=t(o),{hidePlaceHolder:l=!1,isInEditor:i=!1}=o;return r.createElement(r.Fragment,null,!c&&r.createElement(e,Object.assign({},o)),c&&i&&!l&&r.createElement(a,{emptyTextAppend:s,componentTitle:n}))}},2:function(e,t,n){"use strict";n.d(t,"a",(function(){return o}));var r=n(0),s=n.n(r),a=n(3);const o=e=>{const{to:t,isRouted:n,...r}=e,o="boolean"!=typeof e.isRouted||e.isRouted;return void 0===t||0===t.trim().length?s.a.createElement("a",Object.assign({href:"#"},r)):/^https?:\/\//.test(t)||!o?s.a.createElement("a",Object.assign({href:t},r)):s.a.createElement(a.Link,Object.assign({},r,{to:t}))}},3:function(e,t){e.exports=n(55)},33:function(e,t,n){e.exports=n(7)},4:function(e,t,n){"use strict";function r(e){return null==e.text||0===e.text.trim().length}n.d(t,"a",(function(){return r}))},7:function(e,t,n){"use strict";n.r(t),n.d(t,"TitleV2Link",(function(){return i})),n.d(t,"TitleV2Contents",(function(){return u}));var r=n(0),s=n.n(r),a=n(1),o=n(2),c=n(4);const l=e=>e.nested?"-":"__",i=e=>s.a.createElement(o.a,{className:e.baseCssClass+l(e)+"link",isRouted:e.routed,to:e.linkURL},e.text),u=e=>e.linkDisabled?s.a.createElement(s.a.Fragment,null,e.text):s.a.createElement(i,Object.assign({},e)),p=e=>{const t=e.type?e.type.toString():"h3";return s.a.createElement("div",{className:e.baseCssClass},s.a.createElement(t,{className:e.baseCssClass+l(e)+"text"},s.a.createElement(u,Object.assign({},e))))};t.default=e=>{const t=Object(a.a)(Object(a.b)(p,"cmp-title"),c.a,"TitleV2");return s.a.createElement(t,Object.assign({},e))}}})}}]);
//# sourceMappingURL=5.0651e27b.chunk.js.map