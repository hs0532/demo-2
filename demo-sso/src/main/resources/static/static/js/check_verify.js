document.write('<script src="/static/js/img_ver.js" type="text/javascript" charset="utf-8"></script>');

layui.use(['layer', 'form', 'element'], function () {
    window.layer = layui.layer;
    window.form = layui.form;
    window.element = layui.element;
});

var imgBase64 = ''
// 登陆前清空Session
sessionStorage.clear();
//图形验证码获取func
function VerifyImg(){
    $.ajax({
        url: "/check/getImg",
        data: {},
        cache:false,
        type: "GET",
        success: function (data) {
            console.log(data)
            imgBase64 = data["img"];
            console.log(imgBase64);
            document.getElementById("img").setAttribute("src","data:image/png;base64,"+imgBase64);
            return imgBase64;
        },
        error: function(){
            alert("error");
        }
    });
}



/*图形验证码获取与弹出*/
function layerVerifyCodeImg(userPhone){
    // console.log(imgBase64);
    layer.open({
        id: 3,
        offset: 'auto',
        height:'200px',
        type: 1,
        title: '图形验证',

        content:
            '<div class="verBox" style="height: 220px;width: 300px;text-align:center">'
            +'<div id="imgVer" style="display:inline-block;"></div>'
            +'</div>'
        ,
        //btn: ['确认'],
        btnAlign: 'c', //按钮居中
        success:function(){
            $.ajax({
                url:  "/check/getImgSwipe",
                data: {},
                cache:false,
                type: "GET",
                // async:false,
                success: function (data) {
                    console.log(data)
                    var SrcImage = "data:image/jpg;base64,"+data.SrcImage
                    var CutImage = "data:image/jpg;base64,"+data.CutImage
                    var YPosition = data.YPosition
                    var SrcImageWidth  = data.SrcImageWidth;
                    var SrcImageHeight = data.SrcImageHeight;
                    //console.log(imgBase64);

                    imgVer({
                        el:'$("#imgVer")',
                        width:'220',
                        height:'116',
                        SrcImage:SrcImage,
                        CutImage:CutImage,
                        YPosition:YPosition,
                        SrcImageWidth:SrcImageWidth,
                        SrcImageHeight:SrcImageHeight,
                        userPhone:userPhone,

                        success:function () {
                            //验证成功可自定义后续逻辑
                        },
                        error:function () {
                            //alert('错误什么都不执行')
                        }
                    });

                },
                error: function(){
                    alert("error");
                }
            });


        }
    })

}


var InterValObj; //timer变量，控制时间
var count = 60; //间隔函数，1秒执行
var curCount = 0;//当前剩余秒数

/*短信验证码弹出层 */
function layerVerifyCode(phoneNumber) {
    var phoneNumberDecode = phoneNumber;
    // console.log(phoneNumberDecode);
    var fillNum;
    //判断是11位手机号码
    if(checkLegal(phoneNumberDecode)){
        fillNum = phoneNumberDecode;
    }else{
        fillNum='';
    }
    console.log(fillNum);

    layer.open({
        id: 2,
        type: 1,
        title: '请输入验证码',
        area: ['auto', 'auto'],
        content:
            '<form class="layui-form" style="margin-top:30px;padding-right:30px;">'
            + '<div class="layui-form-item">'
            + '<label class="layui-form-label">手机号码</label>'
            + '<div class="layui-input-block">'
            + '<input id="user_phone_verify" class="layui-input" maxlength="11" placeholder="请输入手机号" oninput="clearNum(this)" value='+phoneNumberDecode+'>'
            + '</div>'
            +'</div>'
            +'<div class="layui-form-item" style="margin-top:10px;display:inline" >'
            + '<label class="layui-form-label">验证码</label>'
            + '<div class="layui-input-block">'
            + '<input id="user_verify" class="layui-input" maxlength="6" placeholder="请输入验证码" oninput="clearNum(this)" value="" style="width: 120px;float:left;">'
            + '<input id="btnSendCode" type="button" value="获取" onclick="sendMessage()" class="layui-btn layui-btn-sm layui-btn-danger" autocomplete="off" style="float:left;margin:5px" />'//验证码input + button发送 调用sendMessage()
            + '</div>'
            + '</div>'
            + '</form>'
        ,
        btn: ['确认'],
        btnAlign: 'c', //按钮居中
        btn1: function (index, layero) {
            chcek_confirm();
        }
    })
    //限制反复点击弹窗的按钮可用
    if(curCount > 0){
        $("#btnSendCode").attr("disabled", "true");
        console.log('disabled');
    }else if(curCount == 0){
        console.log('enabled');
    }else{
        console.log('error');
    }
}


/*发送验证码 */
function sendMessage() {
    if(curCount == 0){
        curCount = count;   //设置button效果，开始计时,防止关闭重置
    }

    $("#btnSendCode").attr("disabled", "true");
    $("#btnSendCode").val(curCount + "s");
    InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
    var userPhone = $("#user_phone_verify").val();
    /*向后台发送处理数据*/
    console.log(userPhone);
    $.ajax({
        url: "/code/sendCode",
        data: { MOBILE_NUMBER: encode(userPhone) },
        type: "POST",
        cache: false,
        success: function (data) {
            if (decode(data).success) {
                layer.msg(decode(data).data);
            } else {
                layer.msg(decode(data).errorMessage);
            }
        }
    })
}
/*timer处理函数*/
function SetRemainTime() {
    if (curCount == 0) {
        window.clearInterval(InterValObj);//停止计时器
        $("#btnSendCode").removeAttr("disabled");//启用按钮
        $("#btnSendCode").val("重新获取");
    }
    else {
        curCount--;
        $("#btnSendCode").val(curCount + "s");
    }
}

function chcek_confirm () {
    var phoneNumber = document.getElementById('user_phone_verify');
    if (phoneNumber.value.length != 11) {
        layer.alert("手机号不足11位");
    } else if (!checkLegal(phoneNumber.value)) {
        layer.alert("手机号输入不正确");
    } else {
        checkVerifyCode(phoneNumber.value,$("#user_verify").val());
    }
}


