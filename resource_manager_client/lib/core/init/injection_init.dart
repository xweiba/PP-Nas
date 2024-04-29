import 'getx_container_init.dart';
import 'global_environment_init.dart';

class InjectionInit {
  static Future<void> init() async {
    // 初始化GetX容器
    await GetXContainerInit.init();
    await GlobalEnvironmentInit.init();
  }
}
