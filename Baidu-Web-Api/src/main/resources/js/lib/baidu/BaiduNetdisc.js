/* Baidu网盘js库文件*/
var BaiduNetdisc = {
    batchFileSignParams: function (sign1, sign2, sign3) {
        var batchFileSignFun = new Function("return " + sign2)();
        return this.base64Encode(batchFileSignFun(sign3, sign1));
    },
    base64Encode: function (t) {
        var e, r, a, o, n, i, s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        for (a = t.length,
                 r = 0,
                 e = ""; a > r;) {
            if (o = 255 & t.charCodeAt(r++),
            r === a) {
                e += s.charAt(o >> 2),
                    e += s.charAt((3 & o) << 4),
                    e += "==";
                break
            }
            if (n = t.charCodeAt(r++),
            r === a) {
                e += s.charAt(o >> 2),
                    e += s.charAt((3 & o) << 4 | (240 & n) >> 4),
                    e += s.charAt((15 & n) << 2),
                    e += "=";
                break
            }
            i = t.charCodeAt(r++),
                e += s.charAt(o >> 2),
                e += s.charAt((3 & o) << 4 | (240 & n) >> 4),
                e += s.charAt((15 & n) << 2 | (192 & i) >> 6),
                e += s.charAt(63 & i)
        }
        return e
    },
    // 方法名请与Java中保持一致
    encryptMd5: function (t) {
        if (t & 32 != t.length)
            return t;
        for (var e = 0; e < t.length; e++)
            if (!(t[e] >= 0 && t[e] <= 9 || t[e] >= "a" && t[e] <= "f"))
                return t;
        for (var r = t.substr(8, 8) + t.substr(0, 8) + t.substr(24, 8) + t.substr(16, 8), n = "", e = 0; e < r.length; e++)
            n += (parseInt(r[e], 16) ^ 15 & e).toString(16);
        var o = String.fromCharCode("g".charCodeAt() + parseInt(n[9], 16))
            , i = n.substr(0, 9) + o + n.substr(10);
        return i
    },
    getLogId: function (baidUid) {
        var t = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/~！@#￥%……&"
            , e = String.fromCharCode
            , r = function (t) {
            var r = "";
            return t.length < 2 ? (r = t.charCodeAt(0),
                128 > r ? t : 2048 > r ? e(192 | r >>> 6) + e(128 | 63 & r) : e(224 | r >>> 12 & 15) + e(128 | r >>> 6 & 63) + e(128 | 63 & r)) : (r = 65536 + 1024 * (t.charCodeAt(0) - 55296) + (t.charCodeAt(1) - 56320),
            e(240 | r >>> 18 & 7) + e(128 | r >>> 12 & 63) + e(128 | r >>> 6 & 63) + e(128 | 63 & r))
        }
            , n = /[\uD800-\uDBFF][\uDC00-\uDFFFF]|[^\x00-\x7F]/g
            , i = function (t) {
            return (t + "" + Math.random()).replace(n, r)
        }
            , a = function (e) {
            var r = [0, 2, 1][e.length % 3]
                ,
                n = e.charCodeAt(0) << 16 | (e.length > 1 ? e.charCodeAt(1) : 0) << 8 | (e.length > 2 ? e.charCodeAt(2) : 0)
                ,
                o = [t.charAt(n >>> 18), t.charAt(n >>> 12 & 63), r >= 2 ? "=" : t.charAt(n >>> 6 & 63), r >= 1 ? "=" : t.charAt(63 & n)];
            return o.join("")
        }
            , c = function (t) {
            return t.replace(/[\s\S]{1,3}/g, a)
        }
            , l = function () {
            return c(i((new Date).getTime()))
        }
            , s = function (t, e) {
            return e ? l(String(t)).replace(/[+\/]/g, function (t) {
                return "+" === t ? "-" : "_"
            }).replace(/=/g, "") : l(String(t))
        };
        return s(baidUid)
    },
    getDataOffset: function (makeMD5Sorce, fileSize) {
        return parseInt(this.makeMD5(makeMD5Sorce).substr(0, 8), 16) % (fileSize - 262144 + 1);
    },
    makeMD5: function (e) {
        function o(e, t) {
            var n = (65535 & e) + (65535 & t)
                , i = (e >> 16) + (t >> 16) + (n >> 16);
            return i << 16 | 65535 & n
        }

        function a(e, t) {
            return e << t | e >>> 32 - t
        }

        function s(e, t, n, i, r, s) {
            return o(a(o(o(t, e), o(i, s)), r), n)
        }

        function c(e, t, n, i, r, o, a) {
            return s(t & n | ~t & i, e, t, r, o, a)
        }

        function l(e, t, n, i, r, o, a) {
            return s(t & i | n & ~i, e, t, r, o, a)
        }

        function u(e, t, n, i, r, o, a) {
            return s(t ^ n ^ i, e, t, r, o, a)
        }

        function d(e, t, n, i, r, o, a) {
            return s(n ^ (t | ~i), e, t, r, o, a)
        }

        function A(e, t) {
            var n, i, r, a, s;
            e[t >> 5] |= 128 << t % 32,
                e[14 + (t + 64 >>> 9 << 4)] = t;
            var A = 1732584193
                , f = -271733879
                , p = -1732584194
                , h = 271733878;
            for (n = 0; n < e.length; n += 16)
                i = A,
                    r = f,
                    a = p,
                    s = h,
                    A = c(A, f, p, h, e[n], 7, -680876936),
                    h = c(h, A, f, p, e[n + 1], 12, -389564586),
                    p = c(p, h, A, f, e[n + 2], 17, 606105819),
                    f = c(f, p, h, A, e[n + 3], 22, -1044525330),
                    A = c(A, f, p, h, e[n + 4], 7, -176418897),
                    h = c(h, A, f, p, e[n + 5], 12, 1200080426),
                    p = c(p, h, A, f, e[n + 6], 17, -1473231341),
                    f = c(f, p, h, A, e[n + 7], 22, -45705983),
                    A = c(A, f, p, h, e[n + 8], 7, 1770035416),
                    h = c(h, A, f, p, e[n + 9], 12, -1958414417),
                    p = c(p, h, A, f, e[n + 10], 17, -42063),
                    f = c(f, p, h, A, e[n + 11], 22, -1990404162),
                    A = c(A, f, p, h, e[n + 12], 7, 1804603682),
                    h = c(h, A, f, p, e[n + 13], 12, -40341101),
                    p = c(p, h, A, f, e[n + 14], 17, -1502002290),
                    f = c(f, p, h, A, e[n + 15], 22, 1236535329),
                    A = l(A, f, p, h, e[n + 1], 5, -165796510),
                    h = l(h, A, f, p, e[n + 6], 9, -1069501632),
                    p = l(p, h, A, f, e[n + 11], 14, 643717713),
                    f = l(f, p, h, A, e[n], 20, -373897302),
                    A = l(A, f, p, h, e[n + 5], 5, -701558691),
                    h = l(h, A, f, p, e[n + 10], 9, 38016083),
                    p = l(p, h, A, f, e[n + 15], 14, -660478335),
                    f = l(f, p, h, A, e[n + 4], 20, -405537848),
                    A = l(A, f, p, h, e[n + 9], 5, 568446438),
                    h = l(h, A, f, p, e[n + 14], 9, -1019803690),
                    p = l(p, h, A, f, e[n + 3], 14, -187363961),
                    f = l(f, p, h, A, e[n + 8], 20, 1163531501),
                    A = l(A, f, p, h, e[n + 13], 5, -1444681467),
                    h = l(h, A, f, p, e[n + 2], 9, -51403784),
                    p = l(p, h, A, f, e[n + 7], 14, 1735328473),
                    f = l(f, p, h, A, e[n + 12], 20, -1926607734),
                    A = u(A, f, p, h, e[n + 5], 4, -378558),
                    h = u(h, A, f, p, e[n + 8], 11, -2022574463),
                    p = u(p, h, A, f, e[n + 11], 16, 1839030562),
                    f = u(f, p, h, A, e[n + 14], 23, -35309556),
                    A = u(A, f, p, h, e[n + 1], 4, -1530992060),
                    h = u(h, A, f, p, e[n + 4], 11, 1272893353),
                    p = u(p, h, A, f, e[n + 7], 16, -155497632),
                    f = u(f, p, h, A, e[n + 10], 23, -1094730640),
                    A = u(A, f, p, h, e[n + 13], 4, 681279174),
                    h = u(h, A, f, p, e[n], 11, -358537222),
                    p = u(p, h, A, f, e[n + 3], 16, -722521979),
                    f = u(f, p, h, A, e[n + 6], 23, 76029189),
                    A = u(A, f, p, h, e[n + 9], 4, -640364487),
                    h = u(h, A, f, p, e[n + 12], 11, -421815835),
                    p = u(p, h, A, f, e[n + 15], 16, 530742520),
                    f = u(f, p, h, A, e[n + 2], 23, -995338651),
                    A = d(A, f, p, h, e[n], 6, -198630844),
                    h = d(h, A, f, p, e[n + 7], 10, 1126891415),
                    p = d(p, h, A, f, e[n + 14], 15, -1416354905),
                    f = d(f, p, h, A, e[n + 5], 21, -57434055),
                    A = d(A, f, p, h, e[n + 12], 6, 1700485571),
                    h = d(h, A, f, p, e[n + 3], 10, -1894986606),
                    p = d(p, h, A, f, e[n + 10], 15, -1051523),
                    f = d(f, p, h, A, e[n + 1], 21, -2054922799),
                    A = d(A, f, p, h, e[n + 8], 6, 1873313359),
                    h = d(h, A, f, p, e[n + 15], 10, -30611744),
                    p = d(p, h, A, f, e[n + 6], 15, -1560198380),
                    f = d(f, p, h, A, e[n + 13], 21, 1309151649),
                    A = d(A, f, p, h, e[n + 4], 6, -145523070),
                    h = d(h, A, f, p, e[n + 11], 10, -1120210379),
                    p = d(p, h, A, f, e[n + 2], 15, 718787259),
                    f = d(f, p, h, A, e[n + 9], 21, -343485551),
                    A = o(A, i),
                    f = o(f, r),
                    p = o(p, a),
                    h = o(h, s);
            return [A, f, p, h]
        }

        function f(e) {
            var t, n = "", i = 32 * e.length;
            for (t = 0; t < i; t += 8)
                n += String.fromCharCode(e[t >> 5] >>> t % 32 & 255);
            return n
        }

        function p(e) {
            var t, n = [];
            for (n[(e.length >> 2) - 1] = void 0,
                     t = 0; t < n.length; t += 1)
                n[t] = 0;
            var i = 8 * e.length;
            for (t = 0; t < i; t += 8)
                n[t >> 5] |= (255 & e.charCodeAt(t / 8)) << t % 32;
            return n
        }

        function h(e) {
            return f(A(p(e), 8 * e.length))
        }

        function v(e, t) {
            var n, i, r = p(e), o = [], a = [];
            for (o[15] = a[15] = void 0,
                 r.length > 16 && (r = A(r, 8 * e.length)),
                     n = 0; n < 16; n += 1)
                o[n] = 909522486 ^ r[n],
                    a[n] = 1549556828 ^ r[n];
            return i = A(o.concat(p(t)), 512 + 8 * t.length),
                f(A(a.concat(i), 640))
        }

        function m(e) {
            var t, n, i = "0123456789abcdef", r = "";
            for (n = 0; n < e.length; n += 1)
                t = e.charCodeAt(n),
                    r += i.charAt(t >>> 4 & 15) + i.charAt(15 & t);
            return r
        }

        function g(e) {
            return unescape(encodeURIComponent(e))
        }

        function y(e) {
            return h(g(e))
        }

        function b(e) {
            return m(y(e))
        }

        function w(e, t) {
            return v(g(e), g(t))
        }

        function C(e, t) {
            return m(w(e, t))
        }

        function x(e, t, n) {
            return t ? n ? w(t, e) : C(t, e) : n ? y(e) : b(e)
        }

        return x(e);
    },
    loginGid: function () {
        return "xxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function (e) {
            var t = 16 * Math.random() | 0
                , n = "x" === e ? t : 3 & t | 8;
            return n.toString(16)
        }).toUpperCase()
    },
    loginTraceId: function () {
        return "pc_loginv5_" + ((new Date).getTime() / 1e3).toFixed();
    },
    loginShaOne: function (t) {
        function u(t, r) {
            var e = (65535 & t) + (65535 & r);
            return (t >> 16) + (r >> 16) + (e >> 16) << 16 | 65535 & e
        }

        function s(t, r, e, n, i, o) {
            return u(function a(t, r) {
                return t << r | t >>> 32 - r
            }(u(u(r, t), u(n, o)), i), e)
        }

        function l(t, r, e, n, i, o, a) {
            return s(r & e | ~r & n, t, r, i, o, a)
        }

        function d(t, r, e, n, i, o, a) {
            return s(r & n | e & ~n, t, r, i, o, a)
        }

        function p(t, r, e, n, i, o, a) {
            return s(r ^ e ^ n, t, r, i, o, a)
        }

        function v(t, r, e, n, i, o, a) {
            return s(e ^ (r | ~n), t, r, i, o, a)
        }

        function c(t, r) {
            t[r >> 5] |= 128 << r % 32,
                t[14 + (r + 64 >>> 9 << 4)] = r;
            var e, n, i, o, a, s = 1732584193, c = -271733879, h = -1732584194, f = 271733878;
            for (e = 0; e < t.length; e += 16)
                c = v(c = v(c = v(c = v(c = p(c = p(c = p(c = p(c = d(c = d(c = d(c = d(c = l(c = l(c = l(c = l(i = c, h = l(o = h, f = l(a = f, s = l(n = s, c, h, f, t[e], 7, -680876936), c, h, t[e + 1], 12, -389564586), s, c, t[e + 2], 17, 606105819), f, s, t[e + 3], 22, -1044525330), h = l(h, f = l(f, s = l(s, c, h, f, t[e + 4], 7, -176418897), c, h, t[e + 5], 12, 1200080426), s, c, t[e + 6], 17, -1473231341), f, s, t[e + 7], 22, -45705983), h = l(h, f = l(f, s = l(s, c, h, f, t[e + 8], 7, 1770035416), c, h, t[e + 9], 12, -1958414417), s, c, t[e + 10], 17, -42063), f, s, t[e + 11], 22, -1990404162), h = l(h, f = l(f, s = l(s, c, h, f, t[e + 12], 7, 1804603682), c, h, t[e + 13], 12, -40341101), s, c, t[e + 14], 17, -1502002290), f, s, t[e + 15], 22, 1236535329), h = d(h, f = d(f, s = d(s, c, h, f, t[e + 1], 5, -165796510), c, h, t[e + 6], 9, -1069501632), s, c, t[e + 11], 14, 643717713), f, s, t[e], 20, -373897302), h = d(h, f = d(f, s = d(s, c, h, f, t[e + 5], 5, -701558691), c, h, t[e + 10], 9, 38016083), s, c, t[e + 15], 14, -660478335), f, s, t[e + 4], 20, -405537848), h = d(h, f = d(f, s = d(s, c, h, f, t[e + 9], 5, 568446438), c, h, t[e + 14], 9, -1019803690), s, c, t[e + 3], 14, -187363961), f, s, t[e + 8], 20, 1163531501), h = d(h, f = d(f, s = d(s, c, h, f, t[e + 13], 5, -1444681467), c, h, t[e + 2], 9, -51403784), s, c, t[e + 7], 14, 1735328473), f, s, t[e + 12], 20, -1926607734), h = p(h, f = p(f, s = p(s, c, h, f, t[e + 5], 4, -378558), c, h, t[e + 8], 11, -2022574463), s, c, t[e + 11], 16, 1839030562), f, s, t[e + 14], 23, -35309556), h = p(h, f = p(f, s = p(s, c, h, f, t[e + 1], 4, -1530992060), c, h, t[e + 4], 11, 1272893353), s, c, t[e + 7], 16, -155497632), f, s, t[e + 10], 23, -1094730640), h = p(h, f = p(f, s = p(s, c, h, f, t[e + 13], 4, 681279174), c, h, t[e], 11, -358537222), s, c, t[e + 3], 16, -722521979), f, s, t[e + 6], 23, 76029189), h = p(h, f = p(f, s = p(s, c, h, f, t[e + 9], 4, -640364487), c, h, t[e + 12], 11, -421815835), s, c, t[e + 15], 16, 530742520), f, s, t[e + 2], 23, -995338651), h = v(h, f = v(f, s = v(s, c, h, f, t[e], 6, -198630844), c, h, t[e + 7], 10, 1126891415), s, c, t[e + 14], 15, -1416354905), f, s, t[e + 5], 21, -57434055), h = v(h, f = v(f, s = v(s, c, h, f, t[e + 12], 6, 1700485571), c, h, t[e + 3], 10, -1894986606), s, c, t[e + 10], 15, -1051523), f, s, t[e + 1], 21, -2054922799), h = v(h, f = v(f, s = v(s, c, h, f, t[e + 8], 6, 1873313359), c, h, t[e + 15], 10, -30611744), s, c, t[e + 6], 15, -1560198380), f, s, t[e + 13], 21, 1309151649), h = v(h, f = v(f, s = v(s, c, h, f, t[e + 4], 6, -145523070), c, h, t[e + 11], 10, -1120210379), s, c, t[e + 2], 15, 718787259), f, s, t[e + 9], 21, -343485551),
                    s = u(s, n),
                    c = u(c, i),
                    h = u(h, o),
                    f = u(f, a);
            return [s, c, h, f]
        }

        function h(t) {
            var r, e = "";
            for (r = 0; r < 32 * t.length; r += 8)
                e += String.fromCharCode(t[r >> 5] >>> r % 32 & 255);
            return e
        }

        function f(t) {
            var r, e = [];
            for (e[(t.length >> 2) - 1] = undefined,
                     r = 0; r < e.length; r += 1)
                e[r] = 0;
            for (r = 0; r < 8 * t.length; r += 8)
                e[r >> 5] |= (255 & t.charCodeAt(r / 8)) << r % 32;
            return e
        }

        function o(t) {
            var r, e, n = "0123456789abcdef", i = "";
            for (e = 0; e < t.length; e += 1)
                r = t.charCodeAt(e),
                    i += n.charAt(r >>> 4 & 15) + n.charAt(15 & r);
            return i
        }

        function e(t) {
            return unescape(encodeURIComponent(t))
        }

        function a(t) {
            return function r(t) {
                return h(c(f(t), 8 * t.length))
            }(e(t))
        }

        function _(t, r) {
            return function s(t, r) {
                var e, n, i = f(t), o = [], a = [];
                for (o[15] = a[15] = undefined,
                     16 < i.length && (i = c(i, 8 * t.length)),
                         e = 0; e < 16; e += 1)
                    o[e] = 909522486 ^ i[e],
                        a[e] = 1549556828 ^ i[e];
                return n = c(o.concat(f(r)), 512 + 8 * r.length),
                    h(c(a.concat(n), 640))
            }(e(t), e(r))
        }

        return o(a(t));
    },
    loginSig: function (t, r) {
        var o = function (t, r) {
            var d = {
                a: "3",
                b: "4",
                c: "5",
                d: "9",
                e: "8",
                f: "7",
                g: "1",
                h: "2",
                i: "6",
                j: "0",
                k: "a",
                l: "b",
                m: "c",
                n: "d",
                o: "e",
                p: "f",
                q: "g",
                r: "z",
                s: "y",
                t: "x",
                u: "w",
                v: "v",
                w: "u",
                x: "o",
                y: "p",
                z: "q",
                0: "s",
                1: "t",
                2: "r",
                3: "h",
                4: "i",
                5: "j",
                6: "k",
                7: "l",
                8: "m",
                9: "n"
            };

            var e = [];
            for (var n in t)
                t.hasOwnProperty(n) && e.push(n);
            e.sort();
            for (var i = [], o = 0, a = e.length; o < a; o++) {
                var s = e[o];
                i.push(s + "=" + t[s])
            }
            var c = BaiduNetdisc.loginShaOne(i.join("&"))
                , h = ""
                , f = (1920 + "" + 1200).split("");
            for (o = 0; o < f.length; o++)
                h += d[f[o]];
            return function u(t, r) {
                var e, n = "", i = t.split(""), o = r.split("");
                if (i.length >= o.length) {
                    for (e = 0; e < o.length; e++)
                        n += i[e] + o[e];
                    n += t.substring(e)
                } else {
                    for (e = 0; e < i.length; e++)
                        n += i[e] + o[e];
                    n += r.substring(e)
                }
                return n
            }(c, h);
        }
        var init = function () {
            if (t instanceof ArrayBuffer && (t = new Uint8Array(t)),
            (t instanceof Int8Array || "undefined" != typeof Uint8ClampedArray && t instanceof Uint8ClampedArray || t instanceof Int16Array || t instanceof Uint16Array || t instanceof Int32Array || t instanceof Uint32Array || t instanceof Float32Array || t instanceof Float64Array) && (t = new Uint8Array(t.buffer, t.byteOffset, t.byteLength)),
            t instanceof Uint8Array) {
                for (var r = t.byteLength, e = [], n = 0; n < r; n++)
                    e[n >>> 2] |= t[n] << 24 - n % 4 * 8;
                i.call(this, e, r)
            } else
                i.apply(this, arguments)
        }
        var a = {
            stringify: function (t) {
                for (var r = t.words, e = t.sigBytes, n = [], i = 0; i < e; i++) {
                    var o = r[i >>> 2] >>> 24 - i % 4 * 8 & 255;
                    n.push(String.fromCharCode(o))
                }
                return n.join("")
            },
            parse: function (t) {
                for (var r = t.length, e = [], n = 0; n < r; n++)
                    e[n >>> 2] |= (255 & t.charCodeAt(n)) << 24 - n % 4 * 8;
                return new l.init(e, r)
            }
        }
        var parse = function (t) {
            a.parse(unescape(encodeURIComponent(t)))
        }
        var a = function (t, r) {
            var e = r
                , n = (r = a.parse(unescape(encodeURIComponent(t))),
                i.enc.Utf8.parse(t));
            return i.AES.encrypt(n, r, {
                mode: i.mode.ECB,
                padding: i.pad.Pkcs7
            }).toString()
        }
        var n = o(t, r);
        return s(a(n, 'moonshad0moonsh1'))
    }
}
