package cn.okline.opaydemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.okline.opaysdk.OPayManager;
import cn.okline.opaysdk.OPayTask;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 217;
    private ArrayList<PriceInfo> priceInfos;
    private EditText edit_text;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edit_text = (EditText) findViewById(R.id.edit_text);
        edit_text.setHintTextColor(Color.parseColor("#aaaaab"));
        GridView gv = (GridView) findViewById(R.id.gv);
        priceInfos = new ArrayList<>();
        priceInfos.add(new PriceInfo("10元","售价:10.00元","1000"));
        priceInfos.add(new PriceInfo("20元","售价:20.00元","2000"));
        priceInfos.add(new PriceInfo("30元","售价:29.90元","2990"));
        priceInfos.add(new PriceInfo("50元","售价:49.80元","4980"));
        priceInfos.add(new PriceInfo("100元","售价:99.30元","9930"));
        priceInfos.add(new PriceInfo("200元","售价:198.00元","19800"));
        priceInfos.add(new PriceInfo("300元","售价:295.00元","29500"));
        priceInfos.add(new PriceInfo("500元","售价:490.00元","49000"));

        findViewById(R.id.open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RecyclerTestActivity.class));
            }
        });
        findViewById(R.id.card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CardViewTestActivity.class));
            }
        });
        findViewById(R.id.notify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NotificationTestActivity.class));
            }
        });
        gv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return priceInfos.size();
            }

            @Override
            public PriceInfo getItem(int position) {
                return priceInfos.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = View.inflate(MainActivity.this,R.layout.view_grid_item,null);
                TextView subtitle = (TextView) convertView.findViewById(R.id.subtitle);
                TextView title = (TextView) convertView.findViewById(R.id.title);
                PriceInfo item = getItem(position);
                title.setText(item.getTitle());
                subtitle.setText(item.getSubTitle());
                return convertView;
            }
        });

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String orderNumber = edit_text.getText().toString().trim();
                if (TextUtils.isEmpty(orderNumber)) {
                    Toast.makeText(MainActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else if(!Utils.isMobile(orderNumber)){
                    Toast.makeText(MainActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                } else {
                    String orderAmount = priceInfos.get(position).getPrice();
                    String orderDesc = "手机号" + orderNumber + "充值" + priceInfos.get(position).getTitle();
                    String signature = "6";
                    OPayTask oPayTask = new OPayTask(orderAmount, orderDesc, orderNumber, REQUEST_CODE);
                    OPayManager.executeTask(MainActivity.this, oPayTask, signature);
                }
            }
        });
    }

    /**
     * 处理支付完成之后的返回结果
     * @param requestCode   跳转请求码
     * @param resultCode    支付结果回传
     * @param data  回传数据（视需求而定）
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Utils.showLog(TAG,"requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                new AlertDialog.Builder(MainActivity.this).setTitle("支付成功，话费会立即到账，请稍候！"+data.getStringExtra("tn"))
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                edit_text.setText("");
                            }
                        }).create().show();
            } else {
                String msg = data!=null?data.getStringExtra("msg"):"支付已取消";
                new AlertDialog.Builder(MainActivity.this).setTitle(msg)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create().show();
            }
        }
    }

    private static final String TAG = "MainActivity";
}
