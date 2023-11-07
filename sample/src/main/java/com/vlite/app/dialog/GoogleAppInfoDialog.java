package com.vlite.app.dialog;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;

import com.vlite.app.bean.GoogleInstallInfo;
import com.vlite.app.sample.SampleUtils;
import com.vlite.sdk.VLite;

import java.util.ArrayList;
import java.util.List;

public class GoogleAppInfoDialog extends InstallKitDialog {

    public GoogleAppInfoDialog(@NonNull Context context) {
        super(context,"谷歌三件套信息");
    }

    @Override
    protected List<GoogleInstallInfo> getInstallKitInfos() {
        //此下载地址仅用于DEMO测试用，请勿在生产环境使用
        final List<GoogleInstallInfo> list = new ArrayList<>();
        list.add(new GoogleInstallInfo("com.android.vending", "Google Play商店",
                "https://files.vmos.pro/sample/gpstore.apk"));
        list.add(new GoogleInstallInfo("com.google.android.gms", "Google Play服务",
                "https://files.vmos.pro/sample/gpservice.apk"));
        list.add(new GoogleInstallInfo("com.google.android.gsf", "Google Services Framework",
                "https://files.vmos.pro/sample/gsf.apk"));
        return list;
    }

    @Override
    protected void onPreinstallGoogleServiceKit() {
        // 安装谷歌服务之前要先卸载microG
        final PackageInfo packageInfo = VLite.get().getPackageInfo(SampleUtils.GMS_PACKAGE_NAME, PackageManager.GET_PROVIDERS);
        if (SampleUtils.isMicroG(packageInfo)){
            VLite.get().uninstallPackage(SampleUtils.GMS_PACKAGE_NAME);
        }
    }

    @Override
    protected PackageInfo getPackageInfo(String packageName) {
        final PackageInfo packageInfo = VLite.get().getPackageInfo(packageName, PackageManager.GET_PROVIDERS);
        if (SampleUtils.GMS_PACKAGE_NAME.equals(packageName)) {
            return SampleUtils.isMicroG(packageInfo) ? null : packageInfo;
        } else {
            return packageInfo;
        }
    }
}
