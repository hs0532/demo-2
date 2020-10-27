
function detectmob() {
    if (navigator.userAgent.match(/Android/i)
        || navigator.userAgent.match(/webOS/i)
        || navigator.userAgent.match(/iPhone/i)
        || navigator.userAgent.match(/iPad/i)
        || navigator.userAgent.match(/iPod/i)
        || navigator.userAgent.match(/BlackBerry/i)
        || navigator.userAgent.match(/Windows Phone/i)
    ) {
        return true;
    }
    else {
        return false;
    }
}
var userPhone
function imgVer(Config) {
    userPhone = Config.userPhone
    var el = eval(Config.el);
    var w = Config.width;
    var h = Config.height;
    var SrcImage = Config.SrcImage
    var CutImage = Config.CutImage
    var YPosition = Config.YPosition
    // var imgLibrary = Config.img;
    var SrcImageHeight = Config.SrcImageHeight;
    var SrcImageWidth = Config.SrcImageWidth;
    var wbili = w / SrcImageWidth;
    var hbili = h / SrcImageHeight;
    console.log(wbili + '\n' + hbili);

    var PL_Size = 48;
    var padding = 20;
    var MinN_X = padding + PL_Size;
    var MaxN_X = w - padding - PL_Size - PL_Size / 6;
    var MaxN_Y = padding;
    var MinN_Y = h - padding - PL_Size - PL_Size / 6;

    function RandomNum(Min, Max) {
        var Range = Max - Min;
        var Rand = Math.random();
        if (Math.round(Rand * Range) == 0) {
            return Min + 1;
        } else if (Math.round(Rand * Max) == Max) {
            return Max - 1;
        } else {
            var num = Min + Math.round(Rand * Range) - 1;
            return num;
        }
    }
    var imgSrc = SrcImage;
    var X = '1';
    var Y = '1';


    console.log('PrintLocation');
    console.log("X:" + X + "\n" + "Y:" + Y);
    var left_Num = 0;
    var html = '<div style="position:relative;padding:16px 16px 28px;border:1px solid #ddd;background:#f2ece1;border-radius:16px;">';
    html += '<div style="position:relative;overflow:hidden;width:' + w + 'px;">';
    html += '<div style="position:relative;width:' + w + 'px;height:' + h + 'px;">';
    html += '<img id="scream" src="' + imgSrc + '" style="width:' + w + 'px;height:' + h + 'px;">';
    html += '<canvas id="puzzleBox" width="' + w + '" height="' + h + '" style="position:absolute;left:0;top:0;z-index:22;"></canvas>';
    html += '</div>';
    html += '<div class="puzzle-lost-box" style="position:absolute;width:' + w + 'px;height:' + h + 'px;top:0;left:' + left_Num + 'px;z-index:111;">';
    html += '<canvas id="puzzleShadow" width="' + w + '" height="' + h + '" style="position:absolute;left:0;top:0;z-index:22;"></canvas>';
    html += '<canvas id="puzzleLost" width="' + w + '" height="' + h + '" style="position:absolute;left:0;top:0;z-index:33;"></canvas>';
    html += '</div>';
    html += '<p class="ver-tips"></p>';
    html += '</div>';
    html += '<div class="re-btn"><a></a></div>';
    html += '</div>';
    html += '<br>';
    html += '<div style="position:relative;width:' + w + 'px;margin:auto;touch-action: pan-y">';    //设置触摸区域，防止左滑返回
    html += '<div style="border:1px solid #c3c3c3;border-radius:24px;background:#ece4dd;box-shadow:0 1px 1px rgba(12,10,10,0.2) inset;">';
    html += '<p style="font-size:12px;color: #486c80;line-height:28px;margin:0;text-align:right;padding-right:22px;">按住左边滑块，拖动完成上方拼图</p>';
    html += '</div>';
    html += '<div class="slider-btn"></div>';
    html += '</div>';
    el.html(html);


    var c_l = document.getElementById("puzzleLost");
    //var c_s = document.getElementById("puzzleShadow");
    var ctx_l = c_l.getContext("2d");
    //var ctx_s = c_s.getContext("2d");
    var img = new Image();
    img.src = CutImage;
    img.onload = function () {
        // ctx_l.drawImage(img, 0, YPosition);
        ctx_l.drawImage(img, 0, YPosition * hbili, 50 * wbili, 50 * hbili);
    }

    var moveStart = '';

    if (detectmob()) {  //Mobile端
        document.getElementsByClassName('slider-btn')[0].addEventListener("touchstart", function (e) {
            e = e || window.event;
            $(this).css({ "background-position": "0 -216px" });
            moveStart = e.touches[0].pageX;
        }, false);
        var moveEnd;
        document.addEventListener("touchmove", function (e) {
            e = e || window.event;
            var moveX = e.touches[0].pageX;
            var d = moveX - moveStart;
            if (moveStart == '') {
            } else {
                if (d < 0 || d > (w - 2 * padding)) {
                } else {
                    $(".slider-btn").css({ "left": d + 'px', "transition": "inherit" });
                    $("#puzzleLost").css({ "left": d + 'px', "transition": "inherit" });
                    $("#puzzleShadow").css({ "left": d + 'px', "transition": "inherit" });
                }
            }
            moveEnd = moveX
        }, false);
        document.addEventListener("touchend", function (e) {
            e = e || window.event;
            var moveEnd_X = moveEnd - moveStart;

            if (moveStart == '') {
            } else {
                CheckResult_VerifyButton(moveEnd_X,wbili,Config);
            }
            setTimeout(function () {
                $(".slider-btn").css({ "left": '0', "transition": "left 0.5s" });
                $("#puzzleLost").css({ "left": '0', "transition": "left 0.5s" });
                $("#puzzleShadow").css({ "left": '0', "transition": "left 0.5s" });
            }, 1000);
            $(".slider-btn").css({ "background-position": "0 -84px" });
            moveStart = '';
            Refresh_VerifyButton();
            // console.log("mobile-refresh");   //mobile刷新

        }, false)
    } else {    //PC端
        $(".slider-btn").mousedown(function (e) {
            e = e || window.event;
            $(this).css({ "background-position": "0 -216px" });
            moveStart = e.pageX;
        });
        var moveEnd;
        onmousemove = function (e) {
            e = e || window.event;
            var moveX = e.pageX;
            var d = moveX - moveStart;
            if (moveStart == '') {
            } else {
                if (d < 0 || d > (w - 2 * padding)) {
                } else {
                    $(".slider-btn").css({ "left": d + 'px', "transition": "inherit" });
                    $("#puzzleLost").css({ "left": d + 'px', "transition": "inherit" });
                    $("#puzzleShadow").css({ "left": d + 'px', "transition": "inherit" });
                }
            }
            moveEnd = moveX
        };
        onmouseup = function (e) {
            e = e || window.event;
            var moveEnd_X = e.pageX - moveStart;

            if (moveStart == '') {
            } else {
                CheckResult_VerifyButton(moveEnd_X,wbili,Config);
            }
            setTimeout(function () {
                $(".slider-btn").css({ "left": '0', "transition": "left 0.5s" });
                $("#puzzleLost").css({ "left": '0', "transition": "left 0.5s" });
                $("#puzzleShadow").css({ "left": '0', "transition": "left 0.5s" });
            }, 1000);
            $(".slider-btn").css({ "background-position": "0 -84px" });
            moveStart = '';
            Refresh_VerifyButton();
            // console.log("pc-refresh");
        }
    }
}

function CheckResult_VerifyButton(moveEnd_X,wbili,Config) {
    $.ajax({
        url: "/check/rstImgSwipe",
        data: { moveEnd_X:  moveEnd_X, wbili: wbili },
        type: "POST",
        cache: false,
        success: function (data) {
            // var dataDecode = decode(data);
            if (data.success) {
                //    console.log(data);
                $(".ver-tips").html('<i style="background-position:-4px -1207px;"></i><span style="color:#42ca6b;">验证通过</span><span></span>');
                $(".ver-tips").addClass("slider-tips");
                $(".puzzle-lost-box").addClass("hidden");
                $("#puzzleBox").addClass("hidden");
                setTimeout(function () {
                    $(".ver-tips").removeClass("slider-tips");
                    imgVer(Config);
                }, 2000);
                Config.success();
            } else {
                // console.log(data);
                $(".ver-tips").html('<i style="background-position:-4px -1229px;"></i><span style="color:red;">验证失败:</span><span style="margin-left:4px;">拖动滑块将悬浮图像正确拼合</span>');
                $(".ver-tips").addClass("slider-tips");
                setTimeout(function () {
                    $(".ver-tips").removeClass("slider-tips");
                }, 2000);
                // Config.error();
            }
        }
    })
}

function Refresh_VerifyButton() {
    $(".re-btn a").unbind('click').click(function () {
        // imgVer(Config);
        $.ajax({
            url: "/check/getImgSwipe",
            data: {},
            cache: false,
            type: "GET",
            success: function (data) {
                console.log(data)
                var SrcImage = "data:image/jpg;base64," + data.SrcImage
                var CutImage = "data:image/jpg;base64," + data.CutImage
                var YPosition = data.YPosition
                var SrcImageWidth = data.SrcImageWidth;
                var SrcImageHeight = data.SrcImageHeight;
                imgVer({
                    el: '$("#imgVer")',
                    width: '220',
                    height: '116',
                    SrcImage: SrcImage,
                    CutImage: CutImage,
                    YPosition: YPosition,
                    SrcImageWidth: SrcImageWidth,
                    SrcImageHeight: SrcImageHeight,
                    userPhone: userPhone,
                    success: function () {
                        //验证成功可自定义后续逻辑
                    }
                });
            },
            error: function () {
                alert("error");
            }
        });
    })
}

