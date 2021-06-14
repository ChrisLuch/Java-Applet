/*Compiled using CheerpJ (R) 2.2 by Leaning Technologies Ltd*/
cheerpjCL = { cl: null };
var N2ca8ucalgary4phas3map5angle5AngleG;
function N2ca8ucalgary4phas3map5angle5Angle() {
  N5javax5swing7JApplet.call(this);
  this.strJSHelpFile84 = null;
  this.pbPresent85 = 0;
}
N2ca8ucalgary4phas3map5angle5Angle.cl = cheerpjCL;
function N2ca8ucalgary4phas3map5angle5AngleX(a) {
  a.f = N2ca8ucalgary4phas3map5angle5AngleX;
  if (cheerpjSafeInitGuard("N2ca8ucalgary4phas3map5angle5Angle")) return;
  a.pc = -158;
  if ((N5javax5swing7JAppletG | 0) == 0) {
    cjG(a);
  }
  var q =
    (a.q =
    N2ca8ucalgary4phas3map5angle5Angle.prototype =
      Object.create(N5javax5swing7JApplet.prototype));
  q.constructor = N2ca8ucalgary4phas3map5angle5Angle;
  q.v411 = _c4F32ca8ucalgary4phas3map5angle5AevGaXkaYdp5vd;
  N2ca8ucalgary4phas3map5angle5AngleG = 1;
  cheerpjSafeInitFinish("N2ca8ucalgary4phas3map5angle5Angle");
}
var N2ca8ucalgary4phas3map5angle10AnglePanelG;
var N2ca8ucalgary4phas3map9utilities12MapConstantsG;
var N2ca8ucalgary4phas3map9utilities10MapMenuBarG;
var N2ca8ucalgary4phas3map9utilities14ParameterUtilsG;
var N4java3awt12BorderLayoutG;
var N4java4lang12StringBufferG;
var N5javax5swing7JAppletG;
function _c4F32ca8ucalgary4phas3map5angle5AevGaciShn(d, p) {
  _c4pdzZ8$O2ZZcXeNThn(d, p);
}
function _c4F32ca8ucalgary4phas3map5angle5AevGaXkaYdp5vd(l, p) {
  var g = null,
    h = 0,
    d = null,
    j = 0,
    e = null,
    c = null;
  var a = new createStacklet_c4F32ca8ucalgary4phas3map5angle5AevGaXkaYdp5vd(
    p,
    l
  );
  a.pc = -179;
  if ((N2ca8ucalgary4phas3map9utilities12MapConstantsG | 0) == 0) {
    cjG(a);
  }
  a.pc = -258;
  cjMethodDynamic(
    "ZN2ca8ucalgary4phas3map9utilities12MapConstants17setMapLookAndFeelE" +
      cheerpjResolveMethod(
        cheerpjGetClass("ca/ucalgary/phas/map/utilities/MapConstants"),
        "setMapLookAndFeel",
        "()V"
      ).i
  )(a);
  a.pc = -471;
  if ((N2ca8ucalgary4phas3map9utilities10MapMenuBarG | 0) == 0) {
    cjG(a);
  }
  a.g = g = new N2ca8ucalgary4phas3map9utilities10MapMenuBar();
  a.pc = -605;
  cjMethodDynamic(
    "ZN2ca8ucalgary4phas3map9utilities10MapMenuBarC2E" +
      cheerpjResolveMethod(
        cheerpjGetClass("ca/ucalgary/phas/map/utilities/MapMenuBar"),
        "<init>",
        "(Ljava/applet/Applet\x3b)V"
      ).i
  )(g, l, a);
  a.pc = -813;
  h =
    cjMethodDynamic(
      "ZN2ca8ucalgary4phas3map9utilities12MapConstants15isMenuBarHiddenE" +
        cheerpjResolveMethod(
          cheerpjGetClass("ca/ucalgary/phas/map/utilities/MapConstants"),
          "isMenuBarHidden",
          "()Z"
        ).i
    )(a) | 0;
  if ((h | 0) === 0) {
    a.pc = -1040;
    l.v418(l, g, a);
  }
  a.pc = -1066;
  if ((N2ca8ucalgary4phas3map5angle10AnglePanelG | 0) == 0) {
    cjG(a);
  }
  a.g = g = new N2ca8ucalgary4phas3map5angle10AnglePanel();
  a.pc = -1193;
  _c4V32ca8ucalgary4phas3map5angle10AezqYPanelC2E0(g, l, a);
  a.d = d = cheerpjInternString("VERBOSE");
  a.pc = -1297;
  if ((N2ca8ucalgary4phas3map9utilities14ParameterUtilsG | 0) == 0) {
    cjG(a);
  }
  a.pc = -1379;
  a.h = h =
    cjMethodDynamic(
      "ZN2ca8ucalgary4phas3map9utilities14ParameterUtils19getBooleanParameterE" +
        cheerpjResolveMethod(
          cheerpjGetClass("ca/ucalgary/phas/map/utilities/ParameterUtils"),
          "getBooleanParameter",
          "(Ljava/applet/Applet\x3bLjava/lang/String\x3bZ)Z"
        ).i
    )(l, d, 0, a) | 0;
  a.pc = -1660;
  j =
    cjMethodDynamic(
      "ZN2ca8ucalgary4phas3map9utilities14ParameterUtils19getBooleanParameterE" +
        cheerpjResolveMethod(
          cheerpjGetClass("ca/ucalgary/phas/map/utilities/ParameterUtils"),
          "getBooleanParameter",
          "(Ljava/applet/Applet\x3bLjava/lang/String\x3bZ)Z"
        ).i
    )(l, cheerpjInternString("PAGE_BROWSER_PRESENT"), 1, a) | 0;
  l.pbPresent85 = j;
  d = cheerpjInternString("INFO_URL");
  a.pc = -2029;
  a.d = d = cjMethodDynamic(
    "ZN2ca8ucalgary4phas3map9utilities14ParameterUtils18getStringParameterE" +
      cheerpjResolveMethod(
        cheerpjGetClass("ca/ucalgary/phas/map/utilities/ParameterUtils"),
        "getStringParameter",
        "(Ljava/applet/Applet\x3bLjava/lang/String\x3bLjava/lang/String\x3b)Ljava/lang/String\x3b"
      ).i
  )(l, d, cheerpjInternString("info.html"), a);
  if ((h | 0) !== 0) {
    a.e = e = N4java4lang6System.out1;
    a.pc = -2421;
    if ((N4java4lang12StringBufferG | 0) == 0) {
      cjG(a);
    }
    a.c = c = new N4java4lang12StringBuffer();
    a.pc = -2518;
    _m4pJgeVYBufferC2E0(c, a);
    a.pc = -2554;
    c = c.v66(c, cheerpjInternString("PAGE_BROWSER_PRESENT = "), a);
    a.pc = -2625;
    c = c.v73(c, (l.pbPresent85 | 0) & 255, a);
    a.pc = -2673;
    c = c.v4(c, a);
    a.pc = -2696;
    e.v36(e, c, a);
    a.e = e = N4java4lang6System.out1;
    a.c = c = new N4java4lang12StringBuffer();
    a.pc = -2788;
    _m4pJgeVYBufferC2E0(c, a);
    a.pc = -2824;
    c = c.v66(c, cheerpjInternString("INFO_URL = "), a);
    a.pc = -2883;
    d = c.v66(c, d, a);
    a.pc = -2909;
    d = d.v4(d, a);
    a.pc = -2932;
    e.v36(e, d, a);
  }
  a.pc = -2957;
  a.d = d = l.v424(l, a);
  a.pc = -2986;
  if ((N4java3awt12BorderLayoutG | 0) == 0) {
    cjG(a);
  }
  a.e = e = new N4java3awt12BorderLayout();
  a.pc = -3081;
  _h4V13awt12BorderLayoutC2E0(e, a);
  a.pc = -3125;
  d.v353(d, e, a);
  a.pc = -3150;
  d = l.v424(l, a);
  d.v342(d, g, cheerpjInternString("Center"), p);
}
function createStacklet_c4F32ca8ucalgary4phas3map5angle5AevGaXkaYdp5vd(p, l) {
  this.p = p;
  this.pc = 0;
  this.f = _c4F32ca8ucalgary4phas3map5angle5AevGaXkaYdp5vd;
  this.l = l;
  this.g = null;
  this.h = 0;
  this.d = null;
  this.e = null;
  this.c = null;
}
var N2ca8ucalgary4phas3map5angle16AnglePanel$PopupG;
function N2ca8ucalgary4phas3map5angle16AnglePanel$Popup() {
  N5javax5swing10JPopupMenu.call(this);
  this.reset109 = null;
  this.help110 = null;
  this.this$0111 = null;
}
N2ca8ucalgary4phas3map5angle16AnglePanel$Popup.cl = cheerpjCL;
function N2ca8ucalgary4phas3map5angle16AnglePanel$PopupX(a) {
  a.f = N2ca8ucalgary4phas3map5angle16AnglePanel$PopupX;
  if (cheerpjSafeInitGuard("N2ca8ucalgary4phas3map5angle16AnglePanel$Popup"))
    return;
  a.pc = -194;
  if ((N5javax5swing10JPopupMenuG | 0) == 0) {
    cjG(a);
  }
  var q =
    (a.q =
    N2ca8ucalgary4phas3map5angle16AnglePanel$Popup.prototype =
      Object.create(N5javax5swing10JPopupMenu.prototype));
  q.constructor = N2ca8ucalgary4phas3map5angle16AnglePanel$Popup;
  q.ifs = [
    "java/awt/MenuContainer",
    "java/awt/event/ActionListener",
    "java/awt/image/ImageObserver",
    "java/io/Serializable",
    "java/util/EventListener",
    "javax/accessibility/Accessible",
    "javax/swing/MenuElement",
    "javax/swing/TransferHandler$HasGetTransferHandler",
  ];
  q.culb8KCkruHtedXGb938nG1uorfrZuDTwrGeShrmiS3l = q.v543 =
    _c4V32ca8ucalgary4phas3map5angle16AezWZPanel$Popup15aceWZYPerformedE1;
  N2ca8ucalgary4phas3map5angle16AnglePanel$PopupG = 1;
  cheerpjSafeInitFinish("N2ca8ucalgary4phas3map5angle16AnglePanel$Popup");
}
var N2ca8ucalgary4phas3map9utilities23MapButtonPropertySetterG;
var N5javax5swing9JMenuItemG;
var N5javax5swing10JPopupMenuG;
function _c4V32ca8ucalgary4phas3map5angle16AezWZPanel$PopupC2E0(h, g, p) {
  var c = null;
  var a = {
    p: p,
    pc: 0,
    f: _c4V32ca8ucalgary4phas3map5angle16AezWZPanel$PopupC2E0,
    h: h,
    g: g,
    c: null,
  };
  a.pc = -171;
  _c4Fdz_m9yQZd1JjrdfNTNn(h, a);
  h.this$0111 = g;
  a.pc = -224;
  if ((N5javax5swing9JMenuItemG | 0) == 0) {
    cjG(a);
  }
  a.c = c = new N5javax5swing9JMenuItem();
  a.pc = -316;
  _c4pdz7C_OjrdL4ebdNThn(c, a);
  h.reset109 = c;
  a.c = c = new N5javax5swing9JMenuItem();
  a.pc = -403;
  _c4pdz7C_OjrdL4ebdNThn(c, a);
  h.help110 = c;
  a.c = c = h.help110;
  a.pc = -469;
  if ((N2ca8ucalgary4phas3map9utilities23MapButtonPropertySetterG | 0) == 0) {
    cjG(a);
  }
  a.pc = -559;
  cjMethodDynamic(
    "ZN2ca8ucalgary4phas3map9utilities23MapButtonPropertySetter7setHelpE" +
      cheerpjResolveMethod(
        cheerpjGetClass(
          "ca/ucalgary/phas/map/utilities/MapButtonPropertySetter"
        ),
        "setHelp",
        "(Ljavax/swing/AbstractButton\x3b)V"
      ).i
  )(c, a);
  a.pc = -806;
  cjMethodDynamic(
    "ZN2ca8ucalgary4phas3map9utilities23MapButtonPropertySetter8setResetE" +
      cheerpjResolveMethod(
        cheerpjGetClass(
          "ca/ucalgary/phas/map/utilities/MapButtonPropertySetter"
        ),
        "setReset",
        "(Ljavax/swing/AbstractButton\x3b)V"
      ).i
  )(h.reset109, a);
  a.pc = -1064;
  h.v501(h, h.reset109, a);
  a.pc = -1098;
  h.v501(h, h.help110, a);
  a.pc = -1131;
  h.reset109.v568(h.reset109, h, a);
  h.help110.v568(h.help110, h, p);
}
function _c4V32ca8ucalgary4phas3map5angle16AezWZPanel$Popup15aceWZYPerformedE1(
  j,
  h,
  p
) {
  var c = null,
    d = 0;
  var a = {
    p: p,
    pc: 0,
    f: _c4V32ca8ucalgary4phas3map5angle16AezWZPanel$Popup15aceWZYPerformedE1,
    j: j,
    c: null,
  };
  a.pc = -201;
  a.c = c = h.v11(h, a);
  a.pc = -228;
  d = c.v2(c, j.reset109, a) | 0;
  if ((d | 0) !== 0) {
    a.pc = -277;
    j.this$0111.v499(j.this$0111, a);
  }
  a.pc = -320;
  d = c.v2(c, j.help110, a) | 0;
  if ((d | 0) !== 0) {
    c = N4java4lang6System.out1;
    a.pc = -394;
    c.v36(c, cheerpjInternString("FIX ME!!"), a);
  }
}
var N2ca8ucalgary4phas3map5angle25AnglePanel$MyMouseAdapterG;
function N2ca8ucalgary4phas3map5angle25AnglePanel$MyMouseAdapter() {
  N5javax5swing5event17MouseInputAdapter.call(this);
  this.currentPoint0 = null;
  this.oldPoint1 = null;
  this.translatePoint2 = null;
  this.parent3 = null;
  this.this$04 = null;
}
N2ca8ucalgary4phas3map5angle25AnglePanel$MyMouseAdapter.cl = cheerpjCL;
function N2ca8ucalgary4phas3map5angle25AnglePanel$MyMouseAdapterX(a) {
  a.f = N2ca8ucalgary4phas3map5angle25AnglePanel$MyMouseAdapterX;
  if (
    cheerpjSafeInitGuard(
      "N2ca8ucalgary4phas3map5angle25AnglePanel$MyMouseAdapter"
    )
  )
    return;
  a.pc = -221;
  if ((N5javax5swing5event17MouseInputAdapterG | 0) == 0) {
    cjG(a);
  }
  var q =
    (a.q =
    N2ca8ucalgary4phas3map5angle25AnglePanel$MyMouseAdapter.prototype =
      Object.create(N5javax5swing5event17MouseInputAdapter.prototype));
  q.constructor = N2ca8ucalgary4phas3map5angle25AnglePanel$MyMouseAdapter;
  q.kZ4qjry6urmyq_nFdynLtur0mvtBs0gb7r0cc79c = q.v18 =
    _c4V32ca8ucalgary4phas3map5angle25AezW1Panel$MyMouseAdapter10me1GqsiWkdp4vd;
  q.mZ4qjr0fbyyqmyq_nFdynLtur0mvtBsuhb7r0cc79c = q.v17 =
    _c4V32ca8ucalgary4phas3map5angle25AezW1Panel$MyMouseAdapter12me1qYDraggedE2;
  q.nZ4qjrSsSqairmyq_nFdynLtur0mvtBsKhb7r0cc79c = q.v13 =
    _c4V32ca8ucalgary4phas3map5angle25AezW1Panel$MyMouseAdapter13me1GYReleasedE3;
  N2ca8ucalgary4phas3map5angle25AnglePanel$MyMouseAdapterG = 1;
  cheerpjSafeInitFinish(
    "N2ca8ucalgary4phas3map5angle25AnglePanel$MyMouseAdapter"
  );
}
var N2ca8ucalgary4phas3map9utilities7vectors10VectorUtilG;
var N4java3awt6CursorG;
var N4java3awt5PointG;
var N5javax5swing5event17MouseInputAdapterG;
function _c4V32ca8ucalgary4phas3map5angle25Aeza2Panel$MyMouseAdapterC2E0(
  k,
  j,
  h,
  p
) {
  var c = null;
  var a = {
    p: p,
    pc: 0,
    f: _c4V32ca8ucalgary4phas3map5angle25Aeza2Panel$MyMouseAdapterC2E0,
    k: k,
    j: j,
    h: h,
    c: null,
  };
  a.pc = -195;
  _c4pdz3DorfrZu9UPdLeHyZd1K2aWZereNThn(k, a);
  k.this$04 = j;
  a.pc = -260;
  if ((N4java3awt5PointG | 0) == 0) {
    cjG(a);
  }
  a.c = c = new N4java3awt5Point();
  a.pc = -338;
  _h4VZ3awt5PointC2E0(c, a);
  k.translatePoint2 = c;
  k.parent3 = h;
}
function _c4V32ca8ucalgary4phas3map5angle25AezW1Panel$MyMouseAdapter10me1GqsiWkdp4vd(
  k,
  j,
  p
) {
  var d = 0,
    c = null,
    e = null;
  var a = {
    p: p,
    pc: 0,
    f: _c4V32ca8ucalgary4phas3map5angle25AezW1Panel$MyMouseAdapter10me1GqsiWkdp4vd,
    d: 0,
    k: k,
    c: null,
  };
  a.pc = -224;
  c = j.v35(j, a);
  k.oldPoint1 = c;
  a.c = c = k.this$04;
  a.pc = -277;
  if ((N2ca8ucalgary4phas3map5angle10AnglePanelG | 0) == 0) {
    cjG(a);
  }
  c.onL1114 = 0;
  k.this$04.onL2115 = 0;
  k.this$04.onRadius116 = 0;
  c = k.this$04.elL1108;
  d = k.oldPoint1.x0 | 0;
  a.pc = -445;
  d = c.v35(c, +(d | 0), 0, +(k.oldPoint1.y1 | 0 | 0), 0, a) | 0;
  c = k.this$04;
  if ((d | 0) === 0) {
    c = c.elL2109;
    d = k.oldPoint1.x0 | 0;
    a.pc = -566;
    d = c.v35(c, +(d | 0), 0, +(k.oldPoint1.y1 | 0 | 0), 0, a) | 0;
    c = k.this$04;
    if ((d | 0) === 0) {
      c = c.elRadius110;
      d = k.oldPoint1.x0 | 0;
      a.pc = -691;
      d = c.v35(c, +(d | 0), 0, +(k.oldPoint1.y1 | 0 | 0), 0, a) | 0;
      if ((d | 0) === 0) {
        a.d = d = 0;
      } else {
        k.this$04.onRadius116 = 1;
        a.d = d = 12;
      }
    } else {
      c.onL2115 = 1;
      a.d = d = 13;
    }
  } else {
    c.onL1114 = 1;
    a.d = d = 13;
  }
  a.c = c = k.this$04;
  a.pc = -889;
  if ((N4java3awt6CursorG | 0) == 0) {
    cjG(a);
  }
  a.pc = -939;
  e = _h4V23awt6Cursor19getPredefinedgrHWE3(d, a);
  c.v125(c, e, p);
}
function _c4V32ca8ucalgary4phas3map5angle25AezW1Panel$MyMouseAdapter12me1qYDraggedE2(
  o,
  n,
  p
) {
  var c = null,
    k = 0,
    g = null,
    e = null,
    j = null,
    h = -0,
    d = -0;
  var a =
    new createStacklet_c4V32ca8ucalgary4phas3map5angle25AezW1Panel$MyMouseAdapter12me1qYDraggedE2(
      p,
      o
    );
  a.pc = -245;
  c = n.v35(n, a);
  o.currentPoint0 = c;
  c = o.translatePoint2;
  k = o.currentPoint0.x0 | 0;
  c.x0 = (k - (o.oldPoint1.x0 | 0)) | 0;
  c = o.translatePoint2;
  k = o.currentPoint0.y1 | 0;
  c.y1 = (k - (o.oldPoint1.y1 | 0)) | 0;
  o.oldPoint1 = o.currentPoint0;
  a.c = c = o.this$04;
  a.pc = -476;
  if ((N2ca8ucalgary4phas3map5angle10AnglePanelG | 0) == 0) {
    cjG(a);
  }
  if (((c.onL1114 | 0) & 255) !== 0) {
    c = o.this$04.ptL1111;
    d = +c.x0;
    c.x0 = d + +(o.translatePoint2.x0 | 0 | 0);
    c = o.this$04.ptL1111;
    d = +c.y1;
    c.y1 = d + +(o.translatePoint2.y1 | 0 | 0);
    a.c = c = o.this$04;
    a.g = g = o.this$04.center107;
    a.e = e = o.this$04.center107;
    a.j = j = o.this$04.ptL1111;
    a.pc = -803;
    if ((N2ca8ucalgary4phas3map9utilities7vectors10VectorUtilG | 0) == 0) {
      cjG(a);
    }
    a.pc = -888;
    a.h = h = +cjMethodDynamic(
      "ZN2ca8ucalgary4phas3map9utilities7vectors10VectorUtil9findAngleE" +
        cheerpjResolveMethod(
          cheerpjGetClass("ca/ucalgary/phas/map/utilities/vectors/VectorUtil"),
          "findAngle",
          "(Ljava/awt/geom/Point2D$Double\x3bLjava/awt/geom/Point2D$Double\x3b)D"
        ).i
    )(e, j, a);
    e = o.this$04.center107;
    d = +o.this$04.ptRadius113.x0;
    a.pc = -1223;
    d = +e.v17(e, d, 0, +o.this$04.ptRadius113.y1, 0, a);
    a.pc = -1283;
    g = cjMethodDynamic(
      "ZN2ca8ucalgary4phas3map9utilities7vectors10VectorUtil16translatePoint2DE" +
        cheerpjResolveMethod(
          cheerpjGetClass("ca/ucalgary/phas/map/utilities/vectors/VectorUtil"),
          "translatePoint2D",
          "(Ljava/awt/geom/Point2D$Double\x3bDD)Ljava/awt/geom/Point2D$Double\x3b"
        ).i
    )(g, h, 0, d, 0, a);
    c.ptRadius113 = g;
    a.pc = -1604;
    _c4V32ca8ucalgary4phas3map5angle10Aezq0Panel9calculateE4(o.this$04, a);
  }
  if (((o.this$04.onL2115 | 0) & 255) !== 0) {
    c = o.this$04.ptL2112;
    d = +c.x0;
    c.x0 = d + +(o.translatePoint2.x0 | 0 | 0);
    c = o.this$04.ptL2112;
    d = +c.y1;
    c.y1 = d + +(o.translatePoint2.y1 | 0 | 0);
    a.pc = -1856;
    _c4V32ca8ucalgary4phas3map5angle10Aezq0Panel9calculateE4(o.this$04, a);
  }
  if (((o.this$04.onRadius116 | 0) & 255) !== 0) {
    c = o.this$04.ptRadius113;
    c.x0 = +(o.currentPoint0.x0 | 0 | 0);
    c = o.this$04.ptRadius113;
    c.y1 = +(o.currentPoint0.y1 | 0 | 0);
    a.c = c = o.this$04;
    a.g = g = o.this$04.center107;
    a.e = e = o.this$04.center107;
    a.j = j = o.this$04.ptL1111;
    a.pc = -2184;
    if ((N2ca8ucalgary4phas3map9utilities7vectors10VectorUtilG | 0) == 0) {
      cjG(a);
    }
    a.pc = -2270;
    a.h = h = +cjMethodDynamic(
      "ZN2ca8ucalgary4phas3map9utilities7vectors10VectorUtil9findAngleE" +
        cheerpjResolveMethod(
          cheerpjGetClass("ca/ucalgary/phas/map/utilities/vectors/VectorUtil"),
          "findAngle",
          "(Ljava/awt/geom/Point2D$Double\x3bLjava/awt/geom/Point2D$Double\x3b)D"
        ).i
    )(e, j, a);
    e = o.this$04.center107;
    d = +o.this$04.ptRadius113.x0;
    a.pc = -2606;
    d = +e.v17(e, d, 0, +o.this$04.ptRadius113.y1, 0, a);
    a.pc = -2666;
    g = cjMethodDynamic(
      "ZN2ca8ucalgary4phas3map9utilities7vectors10VectorUtil16translatePoint2DE" +
        cheerpjResolveMethod(
          cheerpjGetClass("ca/ucalgary/phas/map/utilities/vectors/VectorUtil"),
          "translatePoint2D",
          "(Ljava/awt/geom/Point2D$Double\x3bDD)Ljava/awt/geom/Point2D$Double\x3b"
        ).i
    )(g, h, 0, d, 0, a);
    c.ptRadius113 = g;
    a.pc = -2987;
    _c4V32ca8ucalgary4phas3map5angle10Aezq0Panel9calculateE4(o.this$04, a);
  }
}
function _c4V32ca8ucalgary4phas3map5angle25AezW1Panel$MyMouseAdapter13me1GYReleasedE3(
  l,
  k,
  p
) {
  var c = 0,
    g = null,
    e = null,
    d = 0;
  var a = {
    p: p,
    pc: 0,
    f: _c4V32ca8ucalgary4phas3map5angle25AezW1Panel$MyMouseAdapter13me1GYReleasedE3,
    l: l,
    k: k,
    c: 0,
    g: null,
    e: null,
  };
  a.pc = -241;
  l.v18(l, k, a);
  a.pc = -264;
  c = k.v39(k, a) | 0;
  if ((c | 0) !== 0) {
    a.g = g = l.this$04.myPopup132;
    a.e = e = l.parent3;
    a.pc = -346;
    a.c = c = k.v33(k, a) | 0;
    a.pc = -375;
    d = k.v34(k, a) | 0;
    a.pc = -400;
    g.v526(g, e, c, d, a);
  }
}
function createStacklet_c4V32ca8ucalgary4phas3map5angle25AezW1Panel$MyMouseAdapter12me1qYDraggedE2(
  p,
  o
) {
  this.p = p;
  this.pc = 0;
  this.f =
    _c4V32ca8ucalgary4phas3map5angle25AezW1Panel$MyMouseAdapter12me1qYDraggedE2;
  this.o = o;
  this.c = null;
  this.g = null;
  this.e = null;
  this.j = null;
  this.h = -0;
}
var N2ca8ucalgary4phas3map5angle29AnglePanel$MyComponentAdapterG;
function N2ca8ucalgary4phas3map5angle29AnglePanel$MyComponentAdapter() {
  N4java3awt5event16ComponentAdapter.call(this);
  this.this$00 = null;
}
N2ca8ucalgary4phas3map5angle29AnglePanel$MyComponentAdapter.cl = cheerpjCL;
function N2ca8ucalgary4phas3map5angle29AnglePanel$MyComponentAdapterX(a) {
  a.f = N2ca8ucalgary4phas3map5angle29AnglePanel$MyComponentAdapterX;
  if (
    cheerpjSafeInitGuard(
      "N2ca8ucalgary4phas3map5angle29AnglePanel$MyComponentAdapter"
    )
  )
    return;
  a.pc = -233;
  if ((N4java3awt5event16ComponentAdapterG | 0) == 0) {
    cjG(a);
  }
  var q =
    (a.q =
    N2ca8ucalgary4phas3map5angle29AnglePanel$MyComponentAdapter.prototype =
      Object.create(N4java3awt5event16ComponentAdapter.prototype));
  q.constructor = N2ca8ucalgary4phas3map5angle29AnglePanel$MyComponentAdapter;
  q.dKEZponentResizedg0FZ3awt5event14CifsWEe9GWEV = q.v11 =
    _c4V32ca8ucalgary4phas3map5angle29AezW2Panel$MyComponentAdapter16cifrYResizedE1;
  N2ca8ucalgary4phas3map5angle29AnglePanel$MyComponentAdapterG = 1;
  cheerpjSafeInitFinish(
    "N2ca8ucalgary4phas3map5angle29AnglePanel$MyComponentAdapter"
  );
}
var N4java3awt5event16ComponentAdapterG;
function _c4V32ca8ucalgary4phas3map5angle29Aeza3Panel$MyComponentAdapterC2E0(
  g,
  e,
  p
) {
  var a = {
    p: p,
    pc: 0,
    f: _c4V32ca8ucalgary4phas3map5angle29Aeza3Panel$MyComponentAdapterC2E0,
    g: g,
    e: e,
  };
  a.pc = -179;
  _h4p43awt5event16ComponentAdapterC2E0(g, a);
  g.this$00 = e;
}
function _c4V32ca8ucalgary4phas3map5angle29AezW2Panel$MyComponentAdapter16cifrYResizedE1(
  g,
  e,
  p
) {
  g.this$00.v499(g.this$00, p);
}
function N2ca8ucalgary4phas3map5angle10AnglePanel() {
  N5javax5swing6JPanel.call(this);
  this.parent99 = null;
  this.screenFont100 = null;
  this.fontColor101 = null;
  this.arcColor102 = null;
  this.pointColor103 = null;
  this.arc104 = null;
  this.leg1105 = null;
  this.leg2106 = null;
  this.center107 = null;
  this.elL1108 = null;
  this.elL2109 = null;
  this.elRadius110 = null;
  this.ptL1111 = null;
  this.ptL2112 = null;
  this.ptRadius113 = null;
  this.onL1114 = 0;
  this.onL2115 = 0;
  this.onRadius116 = 0;
  this.panelWidth117 = -0;
  this.panelHeight118 = -0;
  this.radius119 = -0;
  this.theta120 = -0;
  this.span121 = -0;
  this.df122 = null;
  this.ptThetaLabel123 = null;
  this.ptRadiusLabel124 = null;
  this.ptSpanLabel125 = null;
  this.stroke126 = null;
  this.lblL1127 = null;
  this.lblL2128 = null;
  this.lblTheta129 = null;
  this.lblSpan130 = null;
  this.lblRadius131 = null;
  this.myPopup132 = null;
}
N2ca8ucalgary4phas3map5angle10AnglePanel.cl = cheerpjCL;
function N2ca8ucalgary4phas3map5angle10AnglePanelX(a) {
  a.f = N2ca8ucalgary4phas3map5angle10AnglePanelX;
  if (cheerpjSafeInitGuard("N2ca8ucalgary4phas3map5angle10AnglePanel")) return;
  a.pc = -176;
  if ((N5javax5swing6JPanelG | 0) == 0) {
    cjG(a);
  }
  var q =
    (a.q =
    N2ca8ucalgary4phas3map5angle10AnglePanel.prototype =
      Object.create(N5javax5swing6JPanel.prototype));
  q.constructor = N2ca8ucalgary4phas3map5angle10AnglePanel;
  q.v499 = _c4V32ca8ucalgary4phas3map5angle10AezqZPanel5resetE1;
  q.v130 = _c4V32ca8ucalgary4phas3map5angle10AezqZPanel5paintE2;
  q.v500 = _c4V32ca8ucalgary4phas3map5angle10AezqZPanel5paintE3;
  q.v501 = _c4V32ca8ucalgary4phas3map5angle10Aeza1Panel11getArcColorE7;
  q.v502 = _c4V32ca8ucalgary4phas3map5angle10Aeza1Panel11setArcColorE8;
  q.v503 = _c4V32ca8ucalgary4phas3map5angle10Aezq0Panel9getRadiusE9;
  q.v504 = _c4V32ca8ucalgary4phas3map5angle10Aeza0Panel7getSpanE10;
  q.v505 = _c4V32ca8ucalgary4phas3map5angle10Aezq0Panel8getThetaE11;
  N2ca8ucalgary4phas3map5angle10AnglePanel.POINT_RADIUS0 = 3.5;
  N2ca8ucalgary4phas3map5angle10AnglePanelG = 1;
  cheerpjSafeInitFinish("N2ca8ucalgary4phas3map5angle10AnglePanel");
}
var N2ca8ucalgary4phas3map5angle6Label2G;
var N2ca8ucalgary4phas3map9utilities7MapMathG;
var N4java3awt11BasicStrokeG;
var N4java3awt5ColorG;
var N4java3awt4FontG;
var N4java3awt10Graphics2DG;
var N4java3awt4geom12Arc2D$DoubleG;
var N4java3awt4geom16Ellipse2D$DoubleG;
var N4java3awt4geom13Line2D$DoubleG;
var N4java3awt4geom14Point2D$DoubleG;
var N4java4text13DecimalFormatG;
var N5javax5swing6JPanelG;
function _c4V32ca8ucalgary4phas3map5angle10AezqYPanelC2E0(h, g, p) {
  var c = null;
  var a = {
    p: p,
    pc: 0,
    f: _c4V32ca8ucalgary4phas3map5angle10AezqYPanelC2E0,
    h: h,
    g: g,
    c: null,
  };
  a.pc = -159;
  _c4pdzVS$ykqdXcNT3n(h, a);
  a.pc = -194;
  if ((N4java3awt4FontG | 0) == 0) {
    cjG(a);
  }
  a.c = c = new N4java3awt4Font();
  a.pc = -270;
  _h4FZ3awt4FontC2E4(c, cheerpjInternString("Dialog"), 1, 12, a);
  h.screenFont100 = c;
  a.pc = -357;
  if ((N4java3awt5ColorG | 0) == 0) {
    cjG(a);
  }
  h.fontColor101 = N4java3awt5Color.black8;
  h.arcColor102 = N4java3awt5Color.blue24;
  h.pointColor103 = N4java3awt5Color.red10;
  a.pc = -522;
  if ((N4java3awt4geom12Arc2D$DoubleG | 0) == 0) {
    cjG(a);
  }
  a.c = c = new N4java3awt4geom12Arc2D$Double();
  a.pc = -626;
  _h4_23awt4geom12Arc2D$DoubleC2E1(c, 2, a);
  h.arc104 = c;
  a.pc = -687;
  if ((N4java3awt4geom13Line2D$DoubleG | 0) == 0) {
    cjG(a);
  }
  a.c = c = new N4java3awt4geom13Line2D$Double();
  a.pc = -793;
  _h4p33awt4geom13Line2D$DoubleC2E0(c, a);
  h.leg1105 = c;
  a.c = c = new N4java3awt4geom13Line2D$Double();
  a.pc = -897;
  _h4p33awt4geom13Line2D$DoubleC2E0(c, a);
  h.leg2106 = c;
  a.pc = -958;
  if ((N4java3awt4geom14Point2D$DoubleG | 0) == 0) {
    cjG(a);
  }
  a.c = c = new N4java3awt4geom14Point2D$Double();
  a.pc = -1066;
  _h4F33awt4geom14Point2D$DoubleC2E0(c, a);
  h.center107 = c;
  a.pc = -1131;
  if ((N4java3awt4geom16Ellipse2D$DoubleG | 0) == 0) {
    cjG(a);
  }
  a.c = c = new N4java3awt4geom16Ellipse2D$Double();
  a.pc = -1244;
  _h4_33awt4geom16Ellipse2D$DoubleC2E0(c, a);
  h.elL1108 = c;
  a.c = c = new N4java3awt4geom16Ellipse2D$Double();
  a.pc = -1355;
  _h4_33awt4geom16Ellipse2D$DoubleC2E0(c, a);
  h.elL2109 = c;
  a.c = c = new N4java3awt4geom16Ellipse2D$Double();
  a.pc = -1466;
  _h4_33awt4geom16Ellipse2D$DoubleC2E0(c, a);
  h.elRadius110 = c;
  a.c = c = new N4java3awt4geom14Point2D$Double();
  a.pc = -1579;
  _h4F33awt4geom14Point2D$DoubleC2E0(c, a);
  h.ptL1111 = c;
  a.c = c = new N4java3awt4geom14Point2D$Double();
  a.pc = -1686;
  _h4F33awt4geom14Point2D$DoubleC2E0(c, a);
  h.ptL2112 = c;
  a.c = c = new N4java3awt4geom14Point2D$Double();
  a.pc = -1793;
  _h4F33awt4geom14Point2D$DoubleC2E0(c, a);
  h.ptRadius113 = c;
  h.onL1114 = 0;
  h.onL2115 = 0;
  h.onRadius116 = 0;
  h.panelWidth117 = 0;
  h.panelHeight118 = 0;
  h.radius119 = 0;
  h.theta120 = 0;
  h.span121 = 0;
  a.pc = -1976;
  if ((N4java4text13DecimalFormatG | 0) == 0) {
    cjG(a);
  }
  a.c = c = new N4java4text13DecimalFormat();
  a.pc = -2075;
  _i4pb31y0DecimalFormatC2E1(c, cheerpjInternString(" 0.00\x3b-0.00"), a);
  h.df122 = c;
  a.c = c = new N4java3awt4geom14Point2D$Double();
  a.pc = -2210;
  _h4F33awt4geom14Point2D$DoubleC2E0(c, a);
  h.ptThetaLabel123 = c;
  a.c = c = new N4java3awt4geom14Point2D$Double();
  a.pc = -2325;
  _h4F33awt4geom14Point2D$DoubleC2E0(c, a);
  h.ptRadiusLabel124 = c;
  a.c = c = new N4java3awt4geom14Point2D$Double();
  a.pc = -2441;
  _h4F33awt4geom14Point2D$DoubleC2E0(c, a);
  h.ptSpanLabel125 = c;
  a.pc = -2511;
  if ((N4java3awt11BasicStrokeG | 0) == 0) {
    cjG(a);
  }
  a.c = c = new N4java3awt11BasicStroke();
  a.pc = -2604;
  _h4F13awt11BasicStrokeC2E3(c, cjF(5), a);
  h.stroke126 = c;
  a.pc = -2668;
  if ((N2ca8ucalgary4phas3map5angle6Label2G | 0) == 0) {
    cjG(a);
  }
  a.c = c = new N2ca8ucalgary4phas3map5angle6Label2();
  a.pc = -2785;
  _c4V52ca8ucalgary4phas3map5angle6Label2C2E0(
    c,
    cheerpjInternString("L1"),
    0,
    0,
    0,
    0,
    h,
    a
  );
  h.lblL1127 = c;
  a.c = c = new N2ca8ucalgary4phas3map5angle6Label2();
  a.pc = -2944;
  _c4V52ca8ucalgary4phas3map5angle6Label2C2E0(
    c,
    cheerpjInternString("L2"),
    0,
    0,
    0,
    0,
    h,
    a
  );
  h.lblL2128 = c;
  a.c = c = new N2ca8ucalgary4phas3map5angle6Label2();
  a.pc = -3103;
  _c4V52ca8ucalgary4phas3map5angle6Label2C2E0(
    c,
    cheerpjInternString("\u03b8"),
    0,
    0,
    0,
    0,
    h,
    a
  );
  h.lblTheta129 = c;
  a.c = c = new N2ca8ucalgary4phas3map5angle6Label2();
  a.pc = -3269;
  _c4V52ca8ucalgary4phas3map5angle6Label2C2E0(
    c,
    cheerpjInternString("s"),
    0,
    0,
    0,
    0,
    h,
    a
  );
  h.lblSpan130 = c;
  a.c = c = new N2ca8ucalgary4phas3map5angle6Label2();
  a.pc = -3429;
  _c4V52ca8ucalgary4phas3map5angle6Label2C2E0(
    c,
    cheerpjInternString("r"),
    0,
    0,
    0,
    0,
    h,
    a
  );
  h.lblRadius131 = c;
  a.pc = -3543;
  if ((N2ca8ucalgary4phas3map5angle16AnglePanel$PopupG | 0) == 0) {
    cjG(a);
  }
  a.c = c = new N2ca8ucalgary4phas3map5angle16AnglePanel$Popup();
  a.pc = -3682;
  _c4V32ca8ucalgary4phas3map5angle16AezWZPanel$PopupC2E0(c, h, a);
  h.myPopup132 = c;
  a.pc = -3770;
  if ((N2ca8ucalgary4phas3map5angle25AnglePanel$MyMouseAdapterG | 0) == 0) {
    cjG(a);
  }
  a.c = c = new N2ca8ucalgary4phas3map5angle25AnglePanel$MyMouseAdapter();
  a.pc = -3927;
  _c4V32ca8ucalgary4phas3map5angle25Aeza2Panel$MyMouseAdapterC2E0(c, h, h, a);
  h.parent99 = g;
  a.pc = -4024;
  h.v193(h, c, a);
  a.pc = -4049;
  h.v196(h, c, a);
  a.pc = -4074;
  if ((N2ca8ucalgary4phas3map5angle29AnglePanel$MyComponentAdapterG | 0) == 0) {
    cjG(a);
  }
  a.c = c = new N2ca8ucalgary4phas3map5angle29AnglePanel$MyComponentAdapter();
  a.pc = -4239;
  _c4V32ca8ucalgary4phas3map5angle29Aeza3Panel$MyComponentAdapterC2E0(c, h, a);
  h.v174(h, c, p);
}
function _c4V32ca8ucalgary4phas3map5angle10AezqZPanel5resetE1(k, p) {
  var g = 0,
    d = -0,
    c = null,
    h = null,
    e = -0;
  var a = {
    p: p,
    pc: 0,
    f: _c4V32ca8ucalgary4phas3map5angle10AezqZPanel5resetE1,
    k: k,
  };
  a.pc = -177;
  g = k.v92(k, a) | 0;
  k.panelWidth117 = +(g | 0);
  a.pc = -227;
  g = k.v93(k, a) | 0;
  k.panelHeight118 = +(g | 0);
  d = +k.panelWidth117;
  a.pc = -297;
  d = +_m4pZ4Math3minE51(d, 0, +k.panelHeight118, 0, a);
  c = k.center107;
  c.x0 = +k.panelWidth117 * 0.5;
  c = k.center107;
  h = k.ptRadius113;
  e = +k.panelHeight118 * 0.5;
  h.y1 = e;
  c.y1 = e;
  c = k.ptRadius113;
  c.x0 = d / 6 + +k.center107.x0;
  c = k.ptL1111;
  d *= 0.125;
  c.x0 = d + +k.center107.x0;
  c = k.ptL1111;
  c.y1 = +k.center107.y1;
  c = k.ptL2112;
  c.x0 = +k.center107.x0;
  c = k.ptL2112;
  c.y1 = +k.center107.y1 - d;
  k.elL1108.height3 = 7;
  k.elL1108.width2 = 7;
  k.elL2109.height3 = 7;
  k.elL2109.width2 = 7;
  k.elRadius110.width2 = 7;
  k.elRadius110.height3 = 7;
  _c4V32ca8ucalgary4phas3map5angle10Aezq0Panel9calculateE4(k, p);
}
function _c4V32ca8ucalgary4phas3map5angle10AezqZPanel5paintE2(g, e, p) {
  var a = {
    p: p,
    pc: 0,
    f: _c4V32ca8ucalgary4phas3map5angle10AezqZPanel5paintE2,
    g: g,
    e: e,
  };
  a.pc = 2;
  if (
    e &&
    !((N4java3awt10Graphics2DG | 0 && e instanceof N4java3awt10Graphics2D) >> 0)
  ) {
    cjCastFailure(a, e);
  }
  g.v500(g, e, p);
}
function _c4V32ca8ucalgary4phas3map5angle10AezqZPanel5paintE3(r, q, p) {
  var h = null,
    m = null,
    l = null,
    c = null,
    k = 0,
    j = 0,
    g = -0,
    e = -0,
    d = null;
  var a =
    new createStacklet_c4V32ca8ucalgary4phas3map5angle10AezqZPanel5paintE3(
      p,
      r,
      q
    );
  a.pc = -212;
  a.h = h = q.v90(q, a);
  a.pc = 4;
  if (
    h &&
    !(
      (N4java3awt11BasicStrokeG | 0 && h instanceof N4java3awt11BasicStroke) >>
      0
    )
  ) {
    cjCastFailure(a, h);
  }
  a.pc = -350;
  a.m = m = q.v18(q, a);
  a.pc = -377;
  a.l = l = q.v14(q, a);
  c = r.elL1108;
  c.x0 = +r.ptL1111.x0 + -3.5;
  c = r.elL1108;
  c.y1 = +r.ptL1111.y1 + -3.5;
  c = r.elL2109;
  c.x0 = +r.ptL2112.x0 + -3.5;
  c = r.elL2109;
  c.y1 = +r.ptL2112.y1 + -3.5;
  c = r.elRadius110;
  c.x0 = +r.ptRadius113.x0 + -3.5;
  c = r.elRadius110;
  c.y1 = +r.ptRadius113.y1 + -3.5;
  a.pc = -660;
  q.v19(q, r.screenFont100, a);
  a.pc = -697;
  if ((N4java3awt5ColorG | 0) == 0) {
    cjG(a);
  }
  a.pc = -746;
  q.v15(q, N4java3awt5Color.white0, a);
  a.pc = -791;
  a.k = k =
    _c4V32ca8ucalgary4phas3map5angle10AezqZPanel5toIntE6(
      +r.panelWidth117,
      0,
      a
    ) | 0;
  a.pc = -886;
  j =
    _c4V32ca8ucalgary4phas3map5angle10AezqZPanel5toIntE6(
      +r.panelHeight118,
      0,
      a
    ) | 0;
  a.pc = -978;
  q.v29(q, 0, 0, k, j, a);
  a.pc = -1007;
  q.v72(q, r.stroke126, a);
  a.pc = -1041;
  q.v15(q, r.arcColor102, a);
  a.pc = -1077;
  q.v59(q, r.arc104, a);
  a.pc = -1108;
  q.v59(q, r.leg1105, a);
  a.pc = -1140;
  q.v59(q, r.leg2106, a);
  a.pc = -1172;
  q.v15(q, N4java3awt5Color.white0, a);
  a.pc = -1218;
  q.v59(q, r.elRadius110, a);
  a.pc = -1254;
  q.v59(q, r.elL1108, a);
  a.pc = -1286;
  q.v59(q, r.elL2109, a);
  a.pc = -1318;
  q.v15(q, r.pointColor103, a);
  a.pc = -1356;
  q.v67(q, r.elRadius110, a);
  a.pc = -1392;
  q.v67(q, r.elL1108, a);
  a.pc = -1424;
  q.v67(q, r.elL2109, a);
  a.c = c = r.lblL1127;
  a.g = g = +r.ptL1111.x0;
  a.pc = -1493;
  e = +r.lblL1127.v13(r.lblL1127, a);
  a.pc = -1536;
  c.v17(c, g - e * 0.5, 0, +r.ptL1111.y1 + 7, 0, a);
  a.c = c = r.lblL2128;
  a.g = g = +r.ptL2112.x0;
  a.pc = -1629;
  e = +r.lblL2128.v13(r.lblL2128, a);
  a.pc = -1672;
  c.v17(c, g - e * 0.5, 0, +r.ptL2112.y1 + 7, 0, a);
  c = r.lblTheta129;
  a.pc = -1744;
  c.v16(c, r.ptThetaLabel123, a);
  a.c = c = r.lblRadius131;
  a.g = g = +r.ptRadius113.x0;
  a.pc = -1829;
  e = +r.lblRadius131.v13(r.lblRadius131, a);
  a.pc = -1880;
  c.v17(c, g - e * 0.5, 0, +r.ptRadius113.y1 + 7, 0, a);
  c = r.lblSpan130;
  a.pc = -1955;
  c.v16(c, r.ptSpanLabel125, a);
  a.pc = -1994;
  q.v72(q, h, a);
  a.pc = -2018;
  r.lblL1127.v12(r.lblL1127, q, a);
  a.pc = -2060;
  r.lblL2128.v12(r.lblL2128, q, a);
  a.pc = -2102;
  r.lblTheta129.v12(r.lblTheta129, q, a);
  a.pc = -2150;
  r.lblRadius131.v12(r.lblRadius131, q, a);
  a.pc = -2200;
  r.lblSpan130.v12(r.lblSpan130, q, a);
  a.pc = -2246;
  q.v15(q, r.fontColor101, a);
  a.pc = -2283;
  q.v45(q, cheerpjInternString("s : "), 5, 15, a);
  a.pc = -2338;
  if ((N4java4lang12StringBufferG | 0) == 0) {
    cjG(a);
  }
  a.c = c = new N4java4lang12StringBuffer();
  a.pc = -2435;
  _m4pJgeVYBufferC2E0(c, a);
  d = r.df122;
  a.pc = -2481;
  d = d.v20(d, +r.span121 / 10, 0, a);
  a.pc = -2525;
  d = c.v66(c, d, a);
  a.pc = -2551;
  d = d.v66(d, cheerpjInternString(" m"), a);
  a.pc = -2601;
  d = d.v4(d, a);
  a.pc = -2624;
  q.v45(q, d, 50, 15, a);
  a.pc = -2654;
  q.v45(q, cheerpjInternString("r : "), 5, 30, a);
  a.c = c = new N4java4lang12StringBuffer();
  a.pc = -2747;
  _m4pJgeVYBufferC2E0(c, a);
  d = r.df122;
  a.pc = -2793;
  d = d.v20(d, +r.radius119 / 10, 0, a);
  a.pc = -2839;
  d = c.v66(c, d, a);
  a.pc = -2865;
  d = d.v66(d, cheerpjInternString(" m"), a);
  a.pc = -2915;
  d = d.v4(d, a);
  a.pc = -2938;
  q.v45(q, d, 50, 30, a);
  a.pc = -2968;
  q.v45(q, cheerpjInternString("\u03b8 = s/r : "), 5, 45, a);
  a.c = c = new N4java4lang12StringBuffer();
  a.pc = -3072;
  _m4pJgeVYBufferC2E0(c, a);
  d = r.df122;
  a.pc = -3118;
  d = d.v20(d, +r.theta120, 0, a);
  a.pc = -3158;
  d = c.v66(c, d, a);
  a.pc = -3184;
  d = d.v66(d, cheerpjInternString(" rad"), a);
  a.pc = -3236;
  d = d.v4(d, a);
  a.pc = -3259;
  q.v45(q, d, 50, 45, a);
  a.pc = -3289;
  q.v15(q, l, a);
  a.pc = -3313;
  q.v19(q, m, a);
  q.v72(q, h, p);
}
function _c4V32ca8ucalgary4phas3map5angle10Aezq0Panel9calculateE4(m, p) {
  var c = null,
    e = null,
    k = -0,
    j = -0,
    d = -0,
    h = null,
    g = null;
  var a =
    new createStacklet_c4V32ca8ucalgary4phas3map5angle10Aezq0Panel9calculateE4(
      p,
      m
    );
  a.c = c = m.center107;
  a.e = e = m.ptL1111;
  a.pc = -241;
  if ((N2ca8ucalgary4phas3map9utilities7vectors10VectorUtilG | 0) == 0) {
    cjG(a);
  }
  a.pc = -326;
  a.k = k = +cjMethodDynamic(
    "ZN2ca8ucalgary4phas3map9utilities7vectors10VectorUtil9findAngleE" +
      cheerpjResolveMethod(
        cheerpjGetClass("ca/ucalgary/phas/map/utilities/vectors/VectorUtil"),
        "findAngle",
        "(Ljava/awt/geom/Point2D$Double\x3bLjava/awt/geom/Point2D$Double\x3b)D"
      ).i
  )(c, e, a);
  c = m.center107;
  a.pc = -625;
  a.j = j = +cjMethodDynamic(
    "ZN2ca8ucalgary4phas3map9utilities7vectors10VectorUtil9findAngleE" +
      cheerpjResolveMethod(
        cheerpjGetClass("ca/ucalgary/phas/map/utilities/vectors/VectorUtil"),
        "findAngle",
        "(Ljava/awt/geom/Point2D$Double\x3bLjava/awt/geom/Point2D$Double\x3b)D"
      ).i
  )(c, m.ptL2112, a);
  c = m.ptRadius113;
  a.pc = -934;
  d = +c.v18(c, m.center107, a);
  m.radius119 = d;
  c = m.arc104;
  d = +m.center107.y1;
  c.y2 = d - +m.radius119;
  c = m.arc104;
  d = +m.center107.x0;
  c.x1 = d - +m.radius119;
  a.c = c = m.arc104;
  a.e = e = m.arc104;
  a.pc = -1118;
  d = +_m4pZ4Math3absE43(+m.radius119 * 2, 0, a);
  e.width3 = d;
  c.height4 = d;
  a.c = c = m.arc104;
  e = m.center107;
  a.pc = -1226;
  d = +cjMethodDynamic(
    "ZN2ca8ucalgary4phas3map9utilities7vectors10VectorUtil9findAngleE" +
      cheerpjResolveMethod(
        cheerpjGetClass("ca/ucalgary/phas/map/utilities/vectors/VectorUtil"),
        "findAngle",
        "(Ljava/awt/geom/Point2D$Double\x3bLjava/awt/geom/Point2D$Double\x3b)D"
      ).i
  )(e, m.ptL1111, a);
  a.pc = -1516;
  d = +_m4F04Math9toDegreesE8(d, 0, a);
  c.start5 = d;
  a.c = c = m.arc104;
  a.e = e = m.center107;
  a.h = h = m.ptL1111;
  a.g = g = m.ptL2112;
  a.pc = -1637;
  if ((N2ca8ucalgary4phas3map9utilities7MapMathG | 0) == 0) {
    cjG(a);
  }
  a.pc = -1711;
  d = +cjMethodDynamic(
    "ZN2ca8ucalgary4phas3map9utilities7MapMath12findPosAngleE" +
      cheerpjResolveMethod(
        cheerpjGetClass("ca/ucalgary/phas/map/utilities/MapMath"),
        "findPosAngle",
        "(Ljava/awt/geom/Point2D$Double\x3bLjava/awt/geom/Point2D$Double\x3bLjava/awt/geom/Point2D$Double\x3b)D"
      ).i
  )(e, h, g, a);
  a.pc = -2012;
  d = +_m4F04Math9toDegreesE8(d, 0, a);
  c.extent6 = d;
  a.c = c = m.center107;
  d = +m.arc104.start5;
  a.pc = -2106;
  d = +_m4F04Math9toRadiansE7(d + +m.arc104.extent6 * 0.5, 0, a);
  a.pc = -2175;
  c = cjMethodDynamic(
    "ZN2ca8ucalgary4phas3map9utilities7vectors10VectorUtil16translatePoint2DE" +
      cheerpjResolveMethod(
        cheerpjGetClass("ca/ucalgary/phas/map/utilities/vectors/VectorUtil"),
        "translatePoint2D",
        "(Ljava/awt/geom/Point2D$Double\x3bDD)Ljava/awt/geom/Point2D$Double\x3b"
      ).i
  )(c, d, 0, +m.radius119 * 0.5, 0, a);
  m.ptThetaLabel123 = c;
  a.c = c = m.center107;
  e = m.center107;
  a.pc = -2549;
  d = +cjMethodDynamic(
    "ZN2ca8ucalgary4phas3map9utilities7vectors10VectorUtil9findAngleE" +
      cheerpjResolveMethod(
        cheerpjGetClass("ca/ucalgary/phas/map/utilities/vectors/VectorUtil"),
        "findAngle",
        "(Ljava/awt/geom/Point2D$Double\x3bLjava/awt/geom/Point2D$Double\x3b)D"
      ).i
  )(e, m.ptRadius113, a);
  a.pc = -2843;
  c = cjMethodDynamic(
    "ZN2ca8ucalgary4phas3map9utilities7vectors10VectorUtil16translatePoint2DE" +
      cheerpjResolveMethod(
        cheerpjGetClass("ca/ucalgary/phas/map/utilities/vectors/VectorUtil"),
        "translatePoint2D",
        "(Ljava/awt/geom/Point2D$Double\x3bDD)Ljava/awt/geom/Point2D$Double\x3b"
      ).i
  )(c, d + -0.1745, 0, +m.radius119 * 0.5, 0, a);
  m.ptRadiusLabel124 = c;
  a.c = c = m.center107;
  d = +m.arc104.start5;
  a.pc = -3233;
  d = +_m4F04Math9toRadiansE7(d + +m.arc104.extent6 * 0.5, 0, a);
  a.pc = -3302;
  c = cjMethodDynamic(
    "ZN2ca8ucalgary4phas3map9utilities7vectors10VectorUtil16translatePoint2DE" +
      cheerpjResolveMethod(
        cheerpjGetClass("ca/ucalgary/phas/map/utilities/vectors/VectorUtil"),
        "translatePoint2D",
        "(Ljava/awt/geom/Point2D$Double\x3bDD)Ljava/awt/geom/Point2D$Double\x3b"
      ).i
  )(c, d, 0, +m.radius119 + 10, 0, a);
  m.ptSpanLabel125 = c;
  c = m.center107;
  a.pc = -3657;
  a.c = c = cjMethodDynamic(
    "ZN2ca8ucalgary4phas3map9utilities7vectors10VectorUtil16translatePoint2DE" +
      cheerpjResolveMethod(
        cheerpjGetClass("ca/ucalgary/phas/map/utilities/vectors/VectorUtil"),
        "translatePoint2D",
        "(Ljava/awt/geom/Point2D$Double\x3bDD)Ljava/awt/geom/Point2D$Double\x3b"
      ).i
  )(c, k, 0, +m.radius119, 0, a);
  e = m.center107;
  a.pc = -3992;
  a.e = e = cjMethodDynamic(
    "ZN2ca8ucalgary4phas3map9utilities7vectors10VectorUtil16translatePoint2DE" +
      cheerpjResolveMethod(
        cheerpjGetClass("ca/ucalgary/phas/map/utilities/vectors/VectorUtil"),
        "translatePoint2D",
        "(Ljava/awt/geom/Point2D$Double\x3bDD)Ljava/awt/geom/Point2D$Double\x3b"
      ).i
  )(e, j, 0, +m.radius119, 0, a);
  a.pc = -4313;
  a.h = h = cjMethodDynamic(
    "ZN2ca8ucalgary4phas3map9utilities7vectors10VectorUtil16translatePoint2DE" +
      cheerpjResolveMethod(
        cheerpjGetClass("ca/ucalgary/phas/map/utilities/vectors/VectorUtil"),
        "translatePoint2D",
        "(Ljava/awt/geom/Point2D$Double\x3bDD)Ljava/awt/geom/Point2D$Double\x3b"
      ).i
  )(c, k, 0, +m.radius119 * 1.25, 0, a);
  a.pc = -4641;
  a.g = g = cjMethodDynamic(
    "ZN2ca8ucalgary4phas3map9utilities7vectors10VectorUtil16translatePoint2DE" +
      cheerpjResolveMethod(
        cheerpjGetClass("ca/ucalgary/phas/map/utilities/vectors/VectorUtil"),
        "translatePoint2D",
        "(Ljava/awt/geom/Point2D$Double\x3bDD)Ljava/awt/geom/Point2D$Double\x3b"
      ).i
  )(e, j, 0, +m.radius119 * 1.25, 0, a);
  a.pc = -4969;
  m.leg1105.v18(m.leg1105, c, h, a);
  a.pc = -5011;
  m.leg2106.v18(m.leg2106, e, g, a);
  a.pc = -5053;
  d = +_m4F04Math9toRadiansE7(+m.arc104.extent6, 0, a);
  m.theta120 = d;
  d = +m.theta120;
  m.span121 = d * +m.radius119;
  m.v135(m, p);
}
function _c4V32ca8ucalgary4phas3map5angle10AezWYPanel3sqrE5(g, e, p) {
  return g * g;
}
function _c4V32ca8ucalgary4phas3map5angle10AezqZPanel5toIntE6(g, e, p) {
  if (g < 0) {
    return ~~(g + -0.5) | 0;
  }
  return ~~(g + 0.5) | 0;
}
function _c4V32ca8ucalgary4phas3map5angle10Aeza1Panel11getArcColorE7(d, p) {
  return d.arcColor102;
}
function _c4V32ca8ucalgary4phas3map5angle10Aeza1Panel11setArcColorE8(g, e, p) {
  g.arcColor102 = e;
}
function _c4V32ca8ucalgary4phas3map5angle10Aezq0Panel9getRadiusE9(d, p) {
  return +d.radius119;
}
function _c4V32ca8ucalgary4phas3map5angle10Aeza0Panel7getSpanE10(d, p) {
  return +d.span121;
}
function _c4V32ca8ucalgary4phas3map5angle10Aezq0Panel8getThetaE11(d, p) {
  return +d.theta120;
}
function _c4V32ca8ucalgary4phas3map5angle10Aeza1Panel10access$002E12(g, e, p) {
  g.onL1114 = e;
  return (e & 255) | 0;
}
function _c4V32ca8ucalgary4phas3map5angle10Aeza1Panel10access$102E13(g, e, p) {
  g.onL2115 = e;
  return (e & 255) | 0;
}
function _c4V32ca8ucalgary4phas3map5angle10Aeza1Panel10access$202E14(g, e, p) {
  g.onRadius116 = e;
  return (e & 255) | 0;
}
function _c4V32ca8ucalgary4phas3map5angle10Aeza1Panel10access$300E15(d, p) {
  return d.elL1108;
}
function _c4V32ca8ucalgary4phas3map5angle10Aeza1Panel10access$400E16(d, p) {
  return d.elL2109;
}
function _c4V32ca8ucalgary4phas3map5angle10Aeza1Panel10access$500E17(d, p) {
  return d.elRadius110;
}
function _c4V32ca8ucalgary4phas3map5angle10Aeza1Panel10access$000E18(d, p) {
  return ((d.onL1114 | 0) & 255) | 0;
}
function _c4V32ca8ucalgary4phas3map5angle10Aeza1Panel10access$600E19(d, p) {
  return d.ptL1111;
}
function _c4V32ca8ucalgary4phas3map5angle10Aeza1Panel10access$702E20(g, e, p) {
  g.ptRadius113 = e;
  return e;
}
function _c4V32ca8ucalgary4phas3map5angle10Aeza1Panel10access$800E21(d, p) {
  return d.center107;
}
function _c4V32ca8ucalgary4phas3map5angle10Aeza1Panel10access$700E22(d, p) {
  return d.ptRadius113;
}
function _c4V32ca8ucalgary4phas3map5angle10Aeza1Panel10access$900E23(d, p) {
  _c4V32ca8ucalgary4phas3map5angle10Aezq0Panel9calculateE4(d, p);
}
function _c4V32ca8ucalgary4phas3map5angle10Aeza1Panel10access$100E24(d, p) {
  return ((d.onL2115 | 0) & 255) | 0;
}
function _c4V32ca8ucalgary4phas3map5angle10Aezq1Panel11access$1000E25(d, p) {
  return d.ptL2112;
}
function _c4V32ca8ucalgary4phas3map5angle10Aeza1Panel10access$200E26(d, p) {
  return ((d.onRadius116 | 0) & 255) | 0;
}
function createStacklet_c4V32ca8ucalgary4phas3map5angle10Aezq0Panel9calculateE4(
  p,
  m
) {
  this.p = p;
  this.pc = 0;
  this.f = _c4V32ca8ucalgary4phas3map5angle10Aezq0Panel9calculateE4;
  this.m = m;
  this.c = null;
  this.e = null;
  this.k = -0;
  this.j = -0;
  this.h = null;
  this.g = null;
}
function createStacklet_c4V32ca8ucalgary4phas3map5angle10AezqZPanel5paintE3(
  p,
  r,
  q
) {
  this.p = p;
  this.pc = 0;
  this.f = _c4V32ca8ucalgary4phas3map5angle10AezqZPanel5paintE3;
  this.r = r;
  this.q = q;
  this.h = null;
  this.m = null;
  this.l = null;
  this.c = null;
  this.k = 0;
  this.g = -0;
}
function N2ca8ucalgary4phas3map5angle6Label2() {
  N4java4lang6Object.call(this);
  this.bg0 = null;
  this.border1 = null;
  this.fontColor2 = null;
  this.font3 = null;
  this.fm4 = null;
  this.text5 = null;
  this.loc6 = null;
  this.dim7 = null;
  this.bounds8 = null;
  this.parent9 = null;
}
N2ca8ucalgary4phas3map5angle6Label2.cl = cheerpjCL;
function N2ca8ucalgary4phas3map5angle6Label2X(a) {
  a.f = N2ca8ucalgary4phas3map5angle6Label2X;
  if (cheerpjSafeInitGuard("N2ca8ucalgary4phas3map5angle6Label2")) return;
  var q =
    (a.q =
    N2ca8ucalgary4phas3map5angle6Label2.prototype =
      Object.create(N4java4lang6Object.prototype));
  q.constructor = N2ca8ucalgary4phas3map5angle6Label2;
  q.v11 = _c4V62ca8ucalgary4phas3map5angle6Label25paintE3;
  q.v12 = _c4V62ca8ucalgary4phas3map5angle6Label25paintE4;
  q.v13 = _c4F72ca8ucalgary4phas3map5angle6Label28getWidthE6;
  q.v14 = _c4V72ca8ucalgary4phas3map5angle6Label29getHeightE7;
  q.v15 = _c4_62ca8ucalgary4phas3map5angle6Label26getLocE8;
  q.v16 = _c4_62ca8ucalgary4phas3map5angle6Label26setLocE9;
  q.v17 = _c4p72ca8ucalgary4phas3map5angle6Label26setLocE10;
  q.v18 = _c4F72ca8ucalgary4phas3map5angle6Label27getTextE11;
  q.v19 = _c4F72ca8ucalgary4phas3map5angle6Label27setTextE12;
  q.v20 = _c4F72ca8ucalgary4phas3map5angle6Label27getFontE13;
  q.v21 = _c4F72ca8ucalgary4phas3map5angle6Label27setFontE14;
  N2ca8ucalgary4phas3map5angle6Label2G = 1;
  cheerpjSafeInitFinish("N2ca8ucalgary4phas3map5angle6Label2");
}
var N4java3awt9DimensionG;
var N4java3awt4geom18Rectangle2D$DoubleG;
var N4java4lang6ObjectG;
function _c4V52ca8ucalgary4phas3map5angle6Label2C2E0(u, t, s, r, q, o, n, p) {
  var c = null,
    d = 0;
  var a = new createStacklet_c4V52ca8ucalgary4phas3map5angle6Label2C2E0(
    p,
    u,
    t,
    s,
    q,
    n
  );
  a.pc = -166;
  _n4VYObjectC2E0(u, a);
  a.pc = -197;
  if ((N4java3awt5ColorG | 0) == 0) {
    cjG(a);
  }
  a.c = c = new N4java3awt5Color();
  a.pc = -275;
  _h4VZ3awt5ColorC2E5(c, 16777164, a);
  u.bg0 = c;
  u.border1 = N4java3awt5Color.black8;
  u.fontColor2 = N4java3awt5Color.black8;
  a.pc = -398;
  if ((N4java3awt4FontG | 0) == 0) {
    cjG(a);
  }
  a.c = c = new N4java3awt4Font();
  a.pc = -474;
  _h4FZ3awt4FontC2E4(c, cheerpjInternString("Dialog"), 0, 12, a);
  u.font3 = c;
  a.pc = -553;
  if ((N4java3awt4geom14Point2D$DoubleG | 0) == 0) {
    cjG(a);
  }
  a.c = c = new N4java3awt4geom14Point2D$Double();
  a.pc = -661;
  _h4F33awt4geom14Point2D$DoubleC2E0(c, a);
  u.loc6 = c;
  a.pc = -720;
  if ((N4java3awt9DimensionG | 0) == 0) {
    cjG(a);
  }
  a.c = c = new N4java3awt9Dimension();
  a.pc = -806;
  _h4V03awt9DimensionC2E1(c, a);
  u.dim7 = c;
  a.pc = -854;
  if ((N4java3awt4geom18Rectangle2D$DoubleG | 0) == 0) {
    cjG(a);
  }
  a.c = c = new N4java3awt4geom18Rectangle2D$Double();
  a.pc = -970;
  _h4F43awt4geom18Rectangle2D$DoubleC2E0(c, a);
  u.bounds8 = c;
  u.parent9 = n;
  u.text5 = t;
  a.pc = -1058;
  c = n.v124(n, u.font3, a);
  u.fm4 = c;
  a.c = c = u.dim7;
  a.pc = -1112;
  d = u.fm4.v23(u.fm4, t, a) | 0;
  c.width0 = ~~(+(d | 0) * 1.5 + 0.5);
  a.c = c = u.dim7;
  a.pc = -1190;
  d = u.fm4.v16(u.fm4, a) | 0;
  c.height1 = d;
  u.loc6.x0 = s;
  u.loc6.y1 = q;
}
function _c4V52ca8ucalgary4phas3map5angle6Label2C2E1(j, h, g, p) {
  _c4V52ca8ucalgary4phas3map5angle6Label2C2E0(j, h, 0, 0, 0, 0, g, p);
}
function _c4V52ca8ucalgary4phas3map5angle6Label2C2E2(g, e, p) {
  _c4V52ca8ucalgary4phas3map5angle6Label2C2E0(
    g,
    cheerpjInternString(""),
    0,
    0,
    0,
    0,
    e,
    p
  );
}
function _c4V62ca8ucalgary4phas3map5angle6Label25paintE3(g, e, p) {
  var a = {
    p: p,
    pc: 0,
    f: _c4V62ca8ucalgary4phas3map5angle6Label25paintE3,
    g: g,
    e: e,
  };
  a.pc = 2;
  if (
    e &&
    !((N4java3awt10Graphics2DG | 0 && e instanceof N4java3awt10Graphics2D) >> 0)
  ) {
    cjCastFailure(a, e);
  }
  g.v12(g, e, p);
}
function _c4V62ca8ucalgary4phas3map5angle6Label25paintE4(r, q, p) {
  var m = null,
    l = null,
    c = null,
    k = -0,
    j = 0,
    h = null,
    g = 0,
    e = -0,
    d = 0;
  var a = new createStacklet_c4V62ca8ucalgary4phas3map5angle6Label25paintE4(
    p,
    r,
    q
  );
  a.pc = -199;
  a.m = m = q.v14(q, a);
  a.pc = -226;
  a.l = l = q.v18(q, a);
  a.pc = -253;
  q.v19(q, r.font3, a);
  c = r.bounds8;
  c.x0 = +r.loc6.x0;
  c = r.bounds8;
  c.y1 = +r.loc6.y1;
  c = r.bounds8;
  c.width2 = +(r.dim7.width0 | 0 | 0);
  c = r.bounds8;
  c.height3 = +(r.dim7.height1 | 0 | 0);
  a.pc = -430;
  q.v15(q, r.bg0, a);
  a.pc = -457;
  q.v67(q, r.bounds8, a);
  a.pc = -488;
  q.v15(q, r.border1, a);
  a.pc = -519;
  q.v59(q, r.bounds8, a);
  a.pc = -550;
  q.v15(q, r.fontColor2, a);
  a.c = c = r.text5;
  a.k = k = +r.loc6.x0;
  a.j = j = r.dim7.width0 | 0;
  h = r.fm4;
  a.pc = -645;
  a.g = g = h.v23(h, r.text5, a) | 0;
  a.e = e = +r.loc6.y1;
  a.pc = -699;
  d = r.fm4.v14(r.fm4, a) | 0;
  a.pc = -732;
  q.v45(
    q,
    c,
    ~~(k + +((((j - g) | 0) / 2) | 0) + 0.5),
    ~~(e + +(d | 0) + 0.5),
    a
  );
  a.pc = -799;
  q.v19(q, l, a);
  q.v15(q, m, p);
}
function _c4V62ca8ucalgary4phas3map5angle6Label25toIntE5(g, e, p) {
  return ~~(g + 0.5) | 0;
}
function _c4F72ca8ucalgary4phas3map5angle6Label28getWidthE6(d, p) {
  return +(d.dim7.width0 | 0 | 0);
}
function _c4V72ca8ucalgary4phas3map5angle6Label29getHeightE7(d, p) {
  return +(d.dim7.height1 | 0 | 0);
}
function _c4_62ca8ucalgary4phas3map5angle6Label26getLocE8(d, p) {
  return d.loc6;
}
function _c4_62ca8ucalgary4phas3map5angle6Label26setLocE9(h, g, p) {
  var c = -0;
  c = +g.x0;
  h.v17(h, c, 0, +g.y1, 0, p);
}
function _c4p72ca8ucalgary4phas3map5angle6Label26setLocE10(n, m, l, k, j, p) {
  n.loc6.x0 = m;
  n.loc6.y1 = k;
}
function _c4F72ca8ucalgary4phas3map5angle6Label27getTextE11(d, p) {
  return d.text5;
}
function _c4F72ca8ucalgary4phas3map5angle6Label27setTextE12(j, h, p) {
  var d = null,
    c = 0;
  var a = {
    p: p,
    pc: 0,
    f: _c4F72ca8ucalgary4phas3map5angle6Label27setTextE12,
    d: null,
  };
  j.text5 = h;
  a.d = d = j.dim7;
  a.pc = -182;
  c = j.fm4.v23(j.fm4, h, a) | 0;
  d.width0 = ~~(+(c | 0) * 1.5 + 0.5);
}
function _c4F72ca8ucalgary4phas3map5angle6Label27getFontE13(d, p) {
  return d.font3;
}
function _c4F72ca8ucalgary4phas3map5angle6Label27setFontE14(k, j, p) {
  var c = null,
    e = null,
    d = 0;
  var a = {
    p: p,
    pc: 0,
    f: _c4F72ca8ucalgary4phas3map5angle6Label27setFontE14,
    k: k,
    c: null,
  };
  k.font3 = j;
  a.pc = -180;
  c = k.parent9.v124(k.parent9, j, a);
  k.fm4 = c;
  a.c = c = k.dim7;
  e = k.fm4;
  a.pc = -251;
  d = e.v23(e, k.text5, a) | 0;
  c.width0 = ~~(+(d | 0) * 1.5 + 0.5);
  a.c = c = k.dim7;
  a.pc = -326;
  d = k.fm4.v16(k.fm4, a) | 0;
  c.height1 = d;
}
function createStacklet_c4V62ca8ucalgary4phas3map5angle6Label25paintE4(
  p,
  r,
  q
) {
  this.p = p;
  this.pc = 0;
  this.f = _c4V62ca8ucalgary4phas3map5angle6Label25paintE4;
  this.r = r;
  this.q = q;
  this.m = null;
  this.l = null;
  this.c = null;
  this.k = -0;
  this.j = 0;
  this.g = 0;
  this.e = -0;
}
function createStacklet_c4V52ca8ucalgary4phas3map5angle6Label2C2E0(
  p,
  u,
  t,
  s,
  q,
  n
) {
  this.p = p;
  this.pc = 0;
  this.f = _c4V52ca8ucalgary4phas3map5angle6Label2C2E0;
  this.u = u;
  this.t = t;
  this.s = +s;
  this.q = +q;
  this.n = n;
  this.c = null;
}
