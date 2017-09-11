package cn.okline.opaydemo;

import android.nfc.cardemulation.HostApduService;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

/**
 * OKLine(HangZhou) co.,Ltd.
 * Author  : Zheng Jun
 * Email   : zhengjun@okline.cn
 * Date    : 2017/8/28
 * Summary : 在这里描述Class的主要功能
 */

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class MyHostApduService extends HostApduService {

    private static final String TAG = "MyHostApduService";

    @Override
    public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {
        Utils.showLog(TAG,"commandApdu = [" + commandApdu + "], extras = [" + extras + "]");
        return new byte[0];
    }

    @Override
    public void onDeactivated(int reason) {
        switch (reason) {
            case HostApduService.DEACTIVATION_DESELECTED:
                //被取消选中
                break;
            case HostApduService.DEACTIVATION_LINK_LOSS:
                //连接断开
                break;
        }
    }
}
