package cn.okline.opaydemo;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NfcActivity extends AppCompatActivity {
    private static final String TAG = "NfcActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        Intent intent = getIntent();
        Utils.showLog(TAG,"onCreate intent = [" + intent + "]");//onCreate intent = [Intent { act=android.nfc.action.TECH_DISCOVERED flg=0x3000000 cmp=cn.okline.opaydemo/.NfcActivity (has extras) }]
        if (intent != null) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            Utils.showLog(TAG,tag.toString());//杭州一卡通 Tech [android.nfc.tech.IsoDep, android.nfc.tech.NfcA]
            switch (intent.getAction()) {
                case NfcAdapter.ACTION_NDEF_DISCOVERED:
                    Parcelable[] rawMessages =
                            intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
                    if (rawMessages != null) {
                        NdefMessage[] messages = new NdefMessage[rawMessages.length];
                        for (int i = 0; i < rawMessages.length; i++) {
                            messages[i] = (NdefMessage) rawMessages[i];
                        }
                        Utils.showLog(TAG,"-----------PAYLOAD内容-----------");
                        for (NdefMessage message : messages) {
                            Utils.showLog(TAG,message.toString());
                        }
                        Utils.showLog(TAG,"-----------PAYLOAD内容-----------");
                    }
                    break;
                case NfcAdapter.ACTION_TECH_DISCOVERED:

                    break;
                case NfcAdapter.ACTION_TAG_DISCOVERED:

                    break;
            }
            Utils.showLog(TAG,intent.getAction());
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Utils.showLog(TAG,"onNewIntent intent = [" + intent + "]");
        if (intent != null) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            Utils.showLog(TAG,tag.toString());
            switch (intent.getAction()) {
                case NfcAdapter.ACTION_NDEF_DISCOVERED:
                    Parcelable[] rawMessages =
                            intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
                    if (rawMessages != null) {
                        NdefMessage[] messages = new NdefMessage[rawMessages.length];
                        for (int i = 0; i < rawMessages.length; i++) {
                            messages[i] = (NdefMessage) rawMessages[i];
                        }
                        Utils.showLog(TAG,"-----------PAYLOAD内容-----------");
                        for (NdefMessage message : messages) {
                            Utils.showLog(TAG,message.toString());
                        }
                        Utils.showLog(TAG,"-----------PAYLOAD内容-----------");
                    }
                    break;
                case NfcAdapter.ACTION_TECH_DISCOVERED:

                    break;
                case NfcAdapter.ACTION_TAG_DISCOVERED:

                    break;
            }
        }
    }
}
