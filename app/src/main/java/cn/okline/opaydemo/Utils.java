package cn.okline.opaydemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * OKLine(HangZhou) co.,Ltd.
 * Author  : Zheng Jun
 * Email   : zhengjun@okline.cn
 * Date    : 2017/4/6
 * Summary : 实现跳转的辅助类
 */

class Utils {

    /**
     * @param context 调用方上下文
     * @param amout 需支付金额，以分为单位
     * @param desc  交易内容简述
     * @param number    卡号
     * @param requestCode 跳转动作的请求码requestCode
     */
    static void startOPaySDKPayment(Context context, String amout, String desc, String number, int requestCode) {
    }

    /**
     * 将EditText输入的数字内容整理成APP适用的支付数据
     * @param context 上下文
     * @param edit_text 输入框
     */
    static String processEnteredAmout(Context context, EditText edit_text) {
        String trim = edit_text.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            Toast.makeText(context, "输入金额不得为空", Toast.LENGTH_SHORT).show();
            return null;
        }
        if (trim.contains(".")) {
            int i = trim.indexOf(".");
            if (i == trim.length()-2) {
                trim = trim.replace(".", "");
                trim = trim+"0";
            } else if (i < trim.length() - 2) {
                trim = trim.replace(".","");
                trim = trim.substring(0,i+1);
            }
        } else {
            trim = trim+"00";
        }
        return trim;
    }

    /**
     * 手机号验证
     * @param  str  输入的手机号
     * @return 验证通过返回true
     */
     static boolean isMobile(final String str) {
        Pattern p ;
        Matcher m ;
        boolean b ;
        p = Pattern.compile("^[1][34578][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    public static void showLog(String tag,String s){
        Log.i(tag,">>>"+s+"<<<");
    }
}
