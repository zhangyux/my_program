<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	<title>支付宝条码支付测试页面</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/index.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$('input[name=out_trade_no]').val(new Date().getTime());
			$('input[name=nonce_str]').val(new Date().getTime());
			$('.hideClass').hide();
			
			$('input[name=time_start]').val(getCurrentDate()); 
		});
		function getCurrentDate(){
			var date = new Date();
			return date.getFullYear() + '' + formatString(date.getMonth() + 1) + formatString(date.getDay()) + formatString(date.getHours()) + formatString(date.getMinutes()) + formatString(date.getSeconds()); 
		}
		function formatString(value){
			if(parseInt(value) < 10){
				return  0 + '' + value; 
			}
			return value;
		}
		//验证ip
		function isIP(ip) {  
		    var reSpaceCheck = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/;  
		    if (reSpaceCheck.test(ip)) {  
		        ip.match(reSpaceCheck);  
		        if (RegExp.$1<=255&&RegExp.$1>=0  
		          &&RegExp.$2<=255&&RegExp.$2>=0  
		          &&RegExp.$3<=255&&RegExp.$3>=0  
		          &&RegExp.$4<=255&&RegExp.$4>=0) {  
		            return true;   
		        } else {  
		            return false;  
		        }  
		    } else {  
		        return false;  
		    }  
		}  
		function doSubmit(){
			
			var auth_code = $.trim($('input[name=auth_code]').val());
			if(auth_code == ''){
				alert('授权码不能为空');   
				return false; 
			}
			var out_trade_no = $.trim($('input[name=out_trade_no]').val());
			if(out_trade_no == ''){
				alert('商户订单号不能为空');   
				return false; 
			}
			var body = $.trim($('input[name=body]').val());
			if(body == ''){
				alert('商品描述不能为空');
				return false;
			}
			var total_fee = $.trim($('input[name=total_fee]').val());
			if(total_fee == ''){
				alert('总金额不能为空');
				return false;
			}
			//var spbill_create_ip = $.trim($('input[name=spbill_create_ip]').val());
			//if(spbill_create_ip == ''){
			//	alert('终端ip不能为空');
			//	return false;
			//}
			//if(!isIP(spbill_create_ip)){
			//	alert("ip格式不正确");
			//	return false;
			// }
			$('form').submit();
		}
	</script>
</head>
<body text=#000000 bgColor="#ffffff" leftMargin=0  topMargin=4>
	<div id="main">
        <div class="cashier-nav">
            <ol>
				<li class="current">支付宝条码支付测试</li> 
            </ol>
        </div>
        <form action="testPay" method="post" >
            <div id="body" style="clear:left">
                <dl class="content">
					<dt class="hideClass">设备号：</dt>
					<dd class="hideClass">
						<span class="null-star"></span>
						<input size="30" name="device_info" value="WEB" readonly="readonly" maxlength="8"  placeholder="长度32"/>
						<span>(长度32)</span>
						<span></span>
					</dd>
					<dt class="hideClass">版本号：</dt>
					<dd class="hideClass">
						<span class="null-star"></span>
						<input size="30" name="version" value="1.0.4" readonly="readonly" maxlength="8"  placeholder="长度8"/>
						<span>(长度8)</span>
						<span></span>
					</dd>
                    <dt>授权码：</dt>
                    <dd>
                        <span class="null-star"></span>
                        <input name="auth_code" value="130422398425092133" maxlength="32" size="30"  placeholder="长度18"/>
                        <span class="null-star">(长度32)*</span>
                        <span></span>
                    </dd>
                    <dt>随机字符串：</dt>
                    <dd>
                        <span class="null-star"></span>
                        <input name="nonce_str" value="" maxlength="32" size="30"  placeholder="长度18"/>
                        <span class="null-star">(长度32)*</span>
                        <span></span>
                    </dd>
                    <dt>商户订单号：</dt>
                    <dd>
                        <span class="null-star"></span>
                        <input name="out_trade_no" value="123123" maxlength="32" size="30"  placeholder="长度32"/>
                        <span class="null-star">(长度32)*</span>
                        <span></span>
                    </dd>
                    <dt>商品描述：</dt>
                    <dd>
                        <span class="null-star"></span>
                        <input name="body" value="测试购买商品" maxlength="127" size="30"  placeholder="长度127"/>
                        <span class="null-star">(长度127)*</span>
                        <span></span>
                    </dd>
                    <dt>附加信息：</dt>
                    <dd>
                        <span class="null-star"></span>     
                        <input name="attach" value="`store_appid=s20160610000000406#store_name=国融测试门店02#op_user=001" maxlength="128" size="30"  placeholder="长度128"/>
                        <span>(长度128)</span>
                        <span></span>
                    </dd>
                    <dt>总金额：</dt>
                    <dd>
                        <span class="null-star"></span>
                        <input name="total_fee" value="1"  placeholder="单位：分"/> 
                        <span class="null-star">(单位：分 整型)*</span>  
                        <span></span>
                    </dd>
                    
                    <dd>
                        <span class="new-btn-login-sp">
                            <button class="new-btn-login" type="button" onclick="doSubmit()" style="text-align:center;">提 交</button>
                        </span>
                    </dd>
                </dl>
            </div>
		</form>
	</div>
</body>
</html>