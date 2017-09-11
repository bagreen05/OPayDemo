package cn.okline.opaysdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.text.format.DateFormat;
import android.widget.Toast;

/**
 * OKLine(HangZhou) co.,Ltd.
 * Author  : Zheng Jun
 * Email   : zhengjun@okline.cn
 * Date    : 2017/4/8
 * Summary : 在这里描述Class的主要功能
 */

public class OPayManager {

    private static final String PACKAGE_NAME = "com.vboss.okline";
    private static final String CLS_NAME = "com.okline.vboss.assistant.ui.opay.OPaySDKActivity";
    private static final String INTENT = "intent";
    private static final String ORDER_AMOUNT = "orderAmount";
    private static final String ORDER_DESC = "orderDesc";
    private static final String ORDER_NUMBER = "orderNumber";
    private static final String MER_NO = "merNo";
    private static final String CARD_ID = "cardId";
    private static final String CARD_TYPE = "cardType";
    private static final String SHOW_RESULT_PAGE = "showResultPage";
    private static final String REQUEST_CODE = "requestCode";

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static void executeTask(Activity context, OPayTask task, String signatural) {
        // LAUNCHER Intent
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        // 设置ComponentName参数1:packagename参数2:目标Activity路径
        ComponentName cn = new ComponentName(PACKAGE_NAME, CLS_NAME);

        intent.setComponent(cn);
        intent.putExtra(INTENT,"OPaySDK");
        intent.putExtra(ORDER_AMOUNT,task.getOrderAmount());
        intent.putExtra(ORDER_DESC,task.getOrderDesc());
        long inTimeInMillis = System.currentTimeMillis();
        String s = String.valueOf(inTimeInMillis);
        intent.putExtra(ORDER_NUMBER, DateFormat.format("yyyyMMddhhmmss"+ s.substring(s.length()-3), inTimeInMillis));
        intent.putExtra(MER_NO,signatural);
        intent.putExtra(CARD_ID, "1");
        intent.putExtra(CARD_TYPE, 0);
        intent.putExtra(SHOW_RESULT_PAGE, false);
        intent.putExtra(REQUEST_CODE, task.getRequestCode());
        Activity context1 = (Activity) context;
        try {
            context1.startActivityForResult(intent, task.getRequestCode());
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            System.out.println(">>>您的手机尚未安装OKLineAPP，请安装之后再试<<<");
        }
    }
}
