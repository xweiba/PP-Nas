import 'package:get/get.dart';
import 'package:shared_preferences/shared_preferences.dart';

class GetXContainerInit {
  static Future<void> init() async {
    // 初始化 SharedPreferences
    await Get.putAsync(() => SharedPreferences.getInstance());
  }
}
