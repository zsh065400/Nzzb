package zzbcar.cckj.com.nzzb.map;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.widget.Toast;

import com.baidu.mapapi.navi.BaiduMapAppNotSupportNaviException;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviParaOption;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class APPUtil {
	
	public static String[] paks = new String[]{"com.baidu.BaiduMap",
	 "com.autonavi.minimap"};
	

	
	public static void startNative_Baidu(Context context,Location loc1,Location loc2){
		if (loc1==null || loc2==null) {
			return;
		}
		if (loc1.getAddress()==null || "".equals(loc1.getAddress())) {
			loc1.setAddress("我的位置");
		}
		if (loc2.getAddress()==null || "".equals(loc2.getAddress())) {
			loc2.setAddress("目的地");
		}
		try {
			Intent intent = Intent.getIntent("baidumap://map/show?center=40.057406655722,116.29644071728&zoom=11&traffic=on&bounds=37.8608310000,112.5963090000,42.1942670000,118.949126000");
			context.startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(context, "地址解析错误", Toast.LENGTH_SHORT).show();
		}
	}
	
	public static void startNative_Gaode(Context context,Location loc){

		StringBuffer stringBuffer = new StringBuffer("androidamap://route?sourceApplication=").append("amap");



		if (loc==null) {
			return;
		}
		if (loc.getAddress()==null || "".equals(loc.getAddress())) {
			loc.setAddress("目的地");
		}
		try {
			Intent intent = new Intent("android.intent.action.VIEW",
			Uri.parse("androidamap://showTraffic?sourceApplication=softname&amp;poiid=BGVIS1&amp;lat=36.2&amp;lon=116.1&amp;level=10&amp;dev=0"));
			intent.setPackage("com.autonavi.minimap");
			context.startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(context, "地址解析错误", Toast.LENGTH_SHORT).show();
		}
	}
	
	public static void startNative_Tengxun(Context context,Location loc1,Location loc2){

		return;
	}
	public static void startNativeBySDK_Baidu(Context context,Location loc1,Location loc2){
//		double mLat1 = 39.915291;
//		double mLon1 = 116.403857;
//		double mLat2 = 40.056858;
//		double mLon2 = 116.308194;
//
//		double mLat1 = 30.679318;
//		double mLon1 = 104.104604;
//		double mLat2 = 29.450532;
//		double mLon2 = 105.971314;
//
//		loc1 = new Location(mLat1, mLon1);
//		loc2 = new Location(mLat2, mLon2);
		
		if (loc1==null || loc2==null) {
			return;
		}
		if (loc1.getAddress()==null || "".equals(loc1.getAddress())) {
			loc1.setAddress("我的位置");
		}
		if (loc2.getAddress()==null || "".equals(loc2.getAddress())) {
			loc2.setAddress("目的地");
		}
		
		NaviParaOption para = new NaviParaOption().startPoint(MyDistanceUtil.entity2Baidu(loc1))
												  .startName(loc1.getAddress())
												  .endPoint(MyDistanceUtil.entity2Baidu(loc2))
												  .endName(loc2.getAddress());
		try {
			BaiduMapNavigation.openBaiduMapNavi(para, context);
		} catch (BaiduMapAppNotSupportNaviException e) {
			e.printStackTrace();
			Toast.makeText(context, "解析失败", Toast.LENGTH_SHORT).show();
		}
	}
	
	//######################################
	//ͨ����web��ͼ
	//######################################
	
	public static String getWebUrl_Baidu(Location loc1,Location loc2){
		if (loc1==null || loc2==null) {
			return null;
		}
		if (loc1.getAddress()==null || "".equals(loc1.getAddress())) {
			loc1.setAddress("�ҵ�λ��");
		}
		if (loc2.getAddress()==null || "".equals(loc2.getAddress())) {
			loc2.setAddress("Ŀ�ĵ�");
		}
		                                                                 	 		//http://api.map.baidu.com/direction?origin=latlng:34.264642646862,108.95108518068|name:�Ҽ�&destination=������&mode=driving&region=����&output=html&src=yourCompanyName|yourAppName
		return "http://api.map.baidu.com/direction?origin=latlng:"+loc1.getStringLatLng()+"|name:"+loc1.getAddress()+"&destination=latlng:"+loc2.getStringLatLng()+"|name:"+loc2.getAddress()+"&mode=driving&src=������׿Ƽ�|CC����-����";
	}

	/**
	 * ����ֻ����Ƿ�װ��ָ�������
	 * @param context
	 * @param packageName��Ӧ�ð���
	 * @return
	 */
	public static boolean isAvilible(Context context, String packageName) {
		// ��ȡpackagemanager
		final PackageManager packageManager = context.getPackageManager();

		List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
		
		packageManager.getInstalledApplications(packageManager.GET_META_DATA);
		// ���ڴ洢�����Ѱ�װ����İ���
		List<String> packageNames = new ArrayList<String>();

		if (packageInfos != null) {
			for (int i = 0; i < packageInfos.size(); i++) {
				String packName = packageInfos.get(i).packageName;
				packageNames.add(packName);
			}
		}
		// �ж�packageNames���Ƿ���Ŀ�����İ�������TRUE��û��FALSE
		return packageNames.contains(packageName);
	}
	
	/**
	 * ͨ��������ȡӦ����Ϣ
	 * @param context
	 * @param packageName
	 * @return
	 */
	public static AppInfo getAppInfoByPak(Context context, String packageName){
		PackageManager packageManager = context.getPackageManager();
		List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
		for (PackageInfo packageInfo : packageInfos) {
			if (packageName.equals(packageInfo.packageName)) {
				AppInfo tmpInfo =new AppInfo(); 
				tmpInfo.setAppName(packageInfo.applicationInfo.loadLabel(packageManager).toString()); 
				tmpInfo.setPackageName(packageInfo.packageName); 
				tmpInfo.setVersionName(packageInfo.versionName); 
				tmpInfo.setVersionCode(packageInfo.versionCode); 
				tmpInfo.setAppIcon(packageInfo.applicationInfo.loadIcon(packageManager));
				return tmpInfo;
			}
		}
		return null;
	}


	public static List<AppInfo> getMapApps(Context context) {
		LinkedList<AppInfo> apps = new LinkedList<AppInfo>();
		
		for (String pak : paks) {
			AppInfo appinfo = getAppInfoByPak(context,pak);
			if (appinfo!=null) {
				apps.add(appinfo);
			}
		}
		return apps;
	}
	

	public static List<AppInfo> getWebApps(Context context){
		LinkedList<AppInfo> apps = new LinkedList<AppInfo>();
		
		String default_browser = "android.intent.category.DEFAULT";
        String browsable = "android.intent.category.BROWSABLE";
        String view = "android.intent.action.VIEW";
		
		Intent intent = new Intent(view);
        intent.addCategory(default_browser);
        intent.addCategory(browsable);
        Uri uri = Uri.parse("http://");
        intent.setDataAndType(uri, null);
		
		PackageManager packageManager = context.getPackageManager();
		List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, PackageManager.GET_INTENT_FILTERS);
		
		for (ResolveInfo resolveInfo : resolveInfoList) {
			AppInfo tmpInfo =new AppInfo();
			tmpInfo.setAppName(resolveInfo.loadLabel(packageManager).toString());
			tmpInfo.setAppIcon(resolveInfo.loadIcon(packageManager));
			tmpInfo.setPackageName(resolveInfo.activityInfo.packageName);
			apps.add(tmpInfo);
		}
		
		return apps;
	}
}
